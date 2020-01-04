import extensions.CSVFile;

class LivreTest extends Program{
    //global variables
    final CSVFile PageCSV = loadCSV("Page.csv");
    final CSVFile[] questionFrancais = {loadCSV("QuCPFrancais.csv")};
    final CSVFile[] questionHistoire = {loadCSV("QuCpHistoire.csv")};
    final CSVFile[] questionMaths = {loadCSV("QuCPMaths.csv")};
    Player joueur = newPlayer();
    // main function
    void algorithm(){
        do{
        print(joueur.Book);
        printTitle("quelle tome ?");
        joueur.Book.currentTome = readInt();
        clearScreen();
        print(joueur.Book.Tomes[joueur.Book.currentTome]);
        printTitle("\nBon Tome?");
        }while(readInt() != 1);
        delay(200);
        do{
            clearScreen();
            print(joueur.Book.Tomes[joueur.Book.currentTome]);
            print("\nimprime quel page ?  :");
            joueur.Book.Tomes[joueur.Book.currentTome].currentPage = readInt();
            clearScreen();
            print(joueur.Book.Tomes[joueur.Book.currentTome].Pages[joueur.Book.Tomes[joueur.Book.currentTome].currentPage]);
            delay(200);
            print("\nBonne page ? ");
        }while(readInt() != 1);
        delay(1000);
        clearScreen();    
        printTitle("Pour que ton sort fonction tu devra repondre au question du theme suivant ");
        println(  ANSI_GREEN+ "\t\t"+ joueur.Book.Tomes[joueur.Book.currentTome].Pages[joueur.Book.Tomes[joueur.Book.currentTome].currentPage].qu[0] +" en Francais" );
        println(  ANSI_RED+ "\t\t"+ joueur.Book.Tomes[joueur.Book.currentTome].Pages[joueur.Book.Tomes[joueur.Book.currentTome].currentPage].qu[1] + " en Histoire" );
        println(  ANSI_BLUE+ "\t\t"+ joueur.Book.Tomes[joueur.Book.currentTome].Pages[joueur.Book.Tomes[joueur.Book.currentTome].currentPage].qu[2] + " en Mathematique" );
        println();
        reset();
        if(repondre(joueur.Book.Tomes[joueur.Book.currentTome].Pages[joueur.Book.Tomes[joueur.Book.currentTome].currentPage])){
            println("BIEN JOUER!!");
        }else{
            println("T'ES NUL!!");
        }
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
        return true;
    }
    // usefull function
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
        print( ANSI_YELLOW_BG + ANSI_BLACK);
        linePrint(80, '-');
        printTitle(t.Titre);
        for(int i = 0;i < t.sommet ; i ++){
            print( ANSI_YELLOW_BG + ANSI_BLACK);
            println(i +".\t"+t.Pages[i].Titre);
            println(
                ""
            );
        }
        linePrint(80, '-');
        print( ANSI_BG_DEFAULT_COLOR + ANSI_TEXT_DEFAULT_COLOR);
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