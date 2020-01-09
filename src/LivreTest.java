import extensions.CSVFile;

class LivreTest extends Program{
    //global variables
    final CSVFile PageCSV = loadCSV("Page.csv");
    final CSVFile[] questionFrancais = {loadCSV("QuCPFrancais.csv")};
    final CSVFile[] questionHistoire = {loadCSV("QuCpHistoire.csv")};
    final CSVFile[] questionMaths = {loadCSV("QuCPMaths.csv")};
    final CSVFile[] Logos = {loadCSV("Titre.csv")};
    final CSVFile[] Landscape = {loadCSV("Landscape1.csv")};
    Player joueur = new Player();
    // main function
    
   
    void algorithm(){
        clearScreen();
        cursor(0, 0);
        printLogo(Landscape[0],0,0);
        printLogo(Logos[0],20,60);
        cursor(37,70);
        print("Appuie sur entré pour commencer");
        
        joueur = newPlayer();
        
        printResumerQuestionPage(
            choixPage(
                choixTome(
                    joueur.Book
                )
            )       
        );
    }
    //answers manager functions
    boolean repondre(Page p){
        //francais
        int tmprep;
        Question tmpQuestion;
        for(int idxfr = 0; idxfr < p.qu[0]; idxfr++){
            printTitle("Question " + idxfr + " en Francais");
            delay(1000);
            tmpQuestion = getQuestion(questionFrancais[joueur.lvl],(int)(random()*rowCount(questionFrancais[joueur.lvl])-1)+1);
            print(tmpQuestion);
            print("reponse :");
            tmprep = readInt();
            if(tmprep != tmpQuestion.idxbon){
                println("MAUVAISE REPONSE!!");
                return false;
            }
            println("BONNE RÉPONSE!!");
        }
        for(int idxhist = 0; idxhist < p.qu[1]; idxhist++){
            printTitle("Question " + idxhist + " en Francais");
            delay(1000);
            tmpQuestion = getQuestion(questionHistoire[joueur.lvl],(int)(random()*rowCount(questionHistoire[joueur.lvl])-1)+1);
            print(tmpQuestion);
            print("reponse :");
            tmprep = readInt();
            if(tmprep != tmpQuestion.idxbon){
                println("MAUVAISE REPONSE!!");
                return false;
            }
            println("BONNE RÉPONSE!!");
        }
        for(int idxmath = 0; idxmath < p.qu[2]; idxmath++){
            printTitle("Question " + idxmath + " en Mathematiques");
            delay(1000);
            tmpQuestion = getQuestion(questionMaths[joueur.lvl],(int)(random()*rowCount(questionMaths[joueur.lvl])-1)+1);
            print(tmpQuestion);
            print("reponse :");
            tmprep = readInt();
            if(tmprep != tmpQuestion.idxbon){
                println("MAUVAISE REPONSE!!");
                return false;
            }
            println("BONNE RÉPONSE!!");
        }
        return true;
    }
    // usefull function
    void printLogo(CSVFile file,int row,int col){
        for(int line = 0; line < rowCount(file);line ++){
            cursor(row+line,col);
            print(getCell(file, line, 0));
        }
    }
    Tome choixTome(Livre l){
        Tome t;
        do{
            print(l);
            print("quelle tome ?\t>");
            t = l.Tomes[readInt()];
            clearScreen();
            print(t);
            print("\nBon Tome? 0/1\t>");
        }while(readInt() != 1);

        return t;
    }
    Page choixPage(Tome t){
        Page p;

        do{
            clearScreen();
            print(t);
            print("\nimprime quel page ?  :\t>");
            p = t.Pages[readInt()];
            clearScreen();
            print(p);
            delay(200);
            print("\nBonne page ?0/1\t>");
        }while(readInt() != 1);

        return p;
    }
    void linePrint(int length, char cara){
        for(int len = 0 ; len < length; len ++){
            print(cara);
        }
        println();
    }
    void printTitle(String text){
        println("\t" + text);
    }
    // question function
    void print(Question q){
        println();
        clearScreen();
        cursor(0,0);
        printTitle(q.qu);
        for(int i = 0; i < length(q.proposition); i ++){
            println("\t"+i+".\t"+q.proposition[i]);
        }
        println();
    }
    Question getQuestion(CSVFile matiere,int row){
        return NewQuestion(getCell(matiere,row,0), 
            new String[]{
                getCell(matiere,row,1),
                getCell(matiere,row,2),
                getCell(matiere,row,3)
            }
        );
    }
    Question NewQuestion(String qu,String[] proposition){
        Question q = new Question();
        q.qu = qu;
        q.idxbon = (int)(random()*3);
        for(int i = 0; i < 3; i ++){
            q.proposition[(i+q.idxbon)%3] = proposition[i]; 
        }

        return q;
    }
    //pages function
    void print(Page p){
        println();
        clearScreen();
        cursor(0,0);
        int llength = 80;
        print(ANSI_YELLOW_BG + ANSI_BLACK);
        linePrint(llength, '=');
        printTitle(p.Titre);
        print(ANSI_YELLOW_BG + ANSI_BLACK);
        println("\t\t" + p.Description + "\n");
        linePrint(llength, '_');
        printTitle("Question à repondre :");
        print(ANSI_YELLOW_BG + ANSI_BLACK);
        println(  ANSI_GREEN+ "\t\tFrancais : "+ p.qu[0] );
        println(  ANSI_RED+ "\t\tHistoire : "+ p.qu[1] );
        println(  ANSI_BLUE+ "\t\tMathematique : "+ p.qu[2] );
        println();
        reset();
        print(ANSI_YELLOW_BG + ANSI_BLACK);
        linePrint(llength, '_');
        printTitle("BONUS :");
        print(ANSI_YELLOW_BG + ANSI_BLACK);
        println(  ANSI_GREEN + "\t\tVIE : "+ p.bonus[0] );
        println(  ANSI_RED + "\t\tDEGAT : "+ p.bonus[1] );
        println(  ANSI_BLUE + "\t\tBOUCLIER : "+ p.bonus[2] );
        println();
        reset();
        print(ANSI_YELLOW_BG + ANSI_BLACK);
        linePrint(llength, '=');
        reset();
        println();
    }
    Page NewPage(String Titre , String description,int fr,int hist, int math,int vie, int dmg ,int shield , int prix , int cout){
        Page p = new Page();
        p.Titre = Titre;
        p.Description = description;
        p.bonus[0] = vie;
        p.bonus[1] = dmg;
        p.bonus[2] = shield;
        p.qu[0] = fr;
        p.qu[1] = hist;
        p.qu[2] = math;
        p.prix = prix;
        p.cout = cout;
        
        return p;
    }
    Page getPage(int row){
        if(
            row > 0 
            &&
            row < rowCount(PageCSV)
        ){
        return NewPage(getCell(PageCSV,row,0),
        getCell(PageCSV,row,1),
        stringToInt(getCell(PageCSV,row,2)),
        stringToInt(getCell(PageCSV,row,3)),
        stringToInt(getCell(PageCSV,row,4)),
        stringToInt(getCell(PageCSV,row,5)),
        stringToInt(getCell(PageCSV,row,6)),
        stringToInt(getCell(PageCSV,row,7)),
        stringToInt(getCell(PageCSV,row,8)),
        stringToInt(getCell(PageCSV,row,9))
                              );
        };
        return null;
    }
    void printResumerQuestionPage(Page p){
        clearScreen();    
        printTitle("Pour que ton sort fonction tu devra repondre au question du theme suivant ");
        println(  ANSI_GREEN+ "\t\t"+ p.qu[0] +" en Francais" );
        println(  ANSI_RED+ "\t\t"+ p.qu[1] + " en Histoire" );
        println(  ANSI_BLUE+ "\t\t"+ p.qu[2] + " en Mathematique" );
        println();
        reset();
        delay(3000);
        if(repondre(p)){
            println("Abraca dabroum lancé du sort");
        }else{
            println("*Pshhhhhh* *Boom* *clap* le Sort echoue  ");
        }
    }
    // tome function
    Tome newTome(String Titre,String color){
        Tome t = new Tome();
        t.Titre = color+Titre+ ANSI_TEXT_DEFAULT_COLOR ;
        t.sommet = 0;
        
        return t;
    }
    void addToTome(Tome t,Page p){
        t.Pages[t.sommet]=p;
        t.sommet ++;
    }
    void print(Tome t){
        println();
        clearScreen();
        cursor(0,0);
        print( ANSI_YELLOW_BG + ANSI_BLACK);
        linePrint(80, '-');
        printTitle(t.Titre);
        for(int i = 0;i < t.sommet ; i ++){
            print( ANSI_YELLOW_BG + ANSI_BLACK);
            print(ajuster(i +".\t"+t.Pages[i].Titre,20));
            println(
                ANSI_GREEN + "\t＋ "+t.Pages[i].bonus[0]
                +ANSI_RED +"\t⚔ "+t.Pages[i].bonus[1]
                +ANSI_BLUE+"\t🛡 "+t.Pages[i].bonus[2] 
                +ANSI_TEXT_DEFAULT_COLOR
            );
        }
        linePrint(80, '-');
        print( ANSI_BG_DEFAULT_COLOR + ANSI_TEXT_DEFAULT_COLOR);
    }
    String ajuster(String txt,int len){
        String t = txt;
        for(int i = 0; i<len-length(t);i++){
            t+=" ";
        }
        return t;
    }
    // book function 
    Livre newLivre(){
        Livre l = new Livre();
        l.Tomes[0] = newTome("GÉNERAL",ANSI_WHITE);
        l.Tomes[1] = newTome("SOIN",ANSI_GREEN);
        l.Tomes[2] = newTome("DEGAT",ANSI_RED);
        l.Tomes[3] = newTome("PROTECTION",ANSI_BLUE);
        for(int pageb = 0;pageb < 3; pageb ++){
            addToLivre(l, getPage(pageb+1));
            }   
        return l;
    }
    void print(Livre l){
        clearScreen();
        cursor(0,0);
        println(ANSI_WHITE_BG);
        print( ANSI_YELLOW_BG + ANSI_BLACK );
        linePrint(80, '=');
        for(int t = 0 ; t < length(l.Tomes) ; t++){
            linePrint(80, '_');
            printTitle(t + ".\t" + l.Tomes[t].Titre);
            print( ANSI_YELLOW_BG + ANSI_BLACK );
            println("\tNombre de pages : " + l.Tomes[t].sommet + "\n");
            linePrint(80, '_');
        }
        linePrint(80, '=');
        print( ANSI_BG_DEFAULT_COLOR + ANSI_TEXT_DEFAULT_COLOR );
        println();
    }
    void addToLivre(Livre l, Page p){
        addToTome(l.Tomes[0], p);
        if(p.bonus[0] > 0){
            addToTome(l.Tomes[1], p);
        }
        if(p.bonus[1] > 0){
            addToTome(l.Tomes[2], p);
        }
        if(p.bonus[2] > 0){
            addToTome(l.Tomes[3], p);
        }
    }
    // playerr function
    Player newPlayer(){
        Player p = new Player();
        printTitle("Quelle est ton prenom ?");
        p.Prenom = readString();
        printTitle("dans quelle classe est tu ?");
        p.lvl = readInt();
        p.Book = newLivre();

        return p;
    }
}