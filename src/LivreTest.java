import extensions.CSVFile;

class LivreTest extends Program{
    //global variables
    final CSVFile PageCSV = loadCSV("Page.csv");
    final CSVFile[] questionFrancais = {loadCSV("QuCPFrancais.csv"),loadCSV("QuCE2Français.csv"),loadCSV("QuCM2Français.csv")};
    final CSVFile[] questionHistoire = {loadCSV("QuCpHistoire.csv"),loadCSV("QuCE2Histoire.csv"),loadCSV("QuCM2Histoire.csv")};
    final CSVFile[] questionMaths = {loadCSV("QuCPMaths.csv"),loadCSV("QuCE2Maths.csv"),loadCSV("QuCM2Maths.csv")};
    final CSVFile[] Logos = {loadCSV("Titre.csv"),loadCSV("heart.csv")};
    final CSVFile[] Landscape = {loadCSV("Landscape1.csv")};
    final CSVFile[] knight = {loadCSV("Knight1.csv"),loadCSV("Knight2.csv"),loadCSV("knight3.csv")};
    final CSVFile[] cases = {loadCSV("Case1.csv")};
    CSVFile Save = loadCSV("Save.csv");
    Player joueur;
    // main function
    
   
    void _algorithm(){
        introduction();
        while(true){
        playerChose();
        }
    }
    void jeu(){
        boolean play = true;
        while(play){
            printResumerQuestionPage(
                choixPage(
                    choixTome(
                        joueur.Book
                    )
                )      
            );
        }
    }
    void introduction(){
        clearScreen();
        cursor(0, 0);
        printLogo(Landscape[0],0,0,0);
        printLogo(Logos[0],20,60,0);
        cursor(0, 0);
        delay(1000);
    }
    void playerChose(){
        clearScreen();
        print(ANSI_BLUE);
        for(int c = 0 ; c < 2 ; c ++){
            printLogo(cases[0], 5 + 20 * c, 23,0);
        }
        print(ANSI_TEXT_DEFAULT_COLOR);
        saveprint();
        cursor(22, 85);
        print("Quel sauvegarde ?\t>");
        JouerCreer(readInt());      
        
    }
    void JouerCreer(int exist){
        if(equals(getCell(Save, exist+1, 0),"null")){
            creePerso(exist);

        }else{
            menucara(exist);
        }
    }
    void printcaraicon(int p,int k){
        printLogo(knight[k], 6 + 20 * p, 24,0);
        cursor(0,0);
    }
    void menucara(int id){
        drawRect(60, 6+20*id, 95, 7, ANSI_BG_DEFAULT_COLOR);
        cursor(6+20*id, 70);
        println("Hey salut ," + getCell(Save,id+1,0) + " heureux de te revoir ^^" );
        cursor(8+20*id, 70);
        printTitle("1.\tJouer");
        cursor(9+20*id, 70);
        printTitle("2.\tSupprimer");
        cursor(10+20*id, 70);
        printTitle("3.\tRetour");
        cursor(11+20*id, 70);
        print("que faire?\t>");
        switch (readInt()){
            case 1: 
            joueur = CSVToPlayer(id); 
            jeu();
            saveGame(joueur);
            break;
            case 2:
            supprSave("Save.csv",id);
            break;
        }
    }
    void saveprint(){
        for(int s = 0 ; s < 2; s++){
            if(!equals(getCell(Save, s+1, 0),"null")){
                printcarac(s);
            }
        }
    }
    void printcarac(int nbr){
        printcaraicon(nbr, stringToInt(getCell(Save, nbr+1,7)));
        printlife(stringToInt(getCell(Save,nbr+1,1)),13+20*nbr, 80);
        cursor(8+ 20 * nbr, 100);
        print(nbr+":\t"+ getCell(Save,nbr+1,0));
        cursor(0, 0);
    }
    void printlife(int pdv,int row , int col){
        print(pdv);
        print(ANSI_RED);
        for(int h = 0 ; h < 3 ; h ++){
            if(h < pdv){
                printLogo(Logos[1], row , col+ 20 * h,0);
            }else{
                printLogo(Logos[1], row , col+ 20 * h,1);
            }
        }
        print(ANSI_TEXT_DEFAULT_COLOR);
        cursor(0, 0);
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
    void printLogo(CSVFile file,int row,int col,int cell){
        for(int line = 0; line < rowCount(file);line ++){
            cursor(row+line,col);
            print(getCell(file, line, cell));
        }
    }
    void drawbox(int x, int y , int width , int height , String color){
        for(int h = 0 ; h <  height ; h ++){
            cursor(y+h, x);
            print("|");
            for(int w = 0 ; w < width - 2 ; w ++){
                if(h == 0 || h == height-1){
                    print("=");
                }else{
                    print(" ");
                }
            }
            print("|");
        }
    }
    void drawRect(int x , int y , int width , int height, String color){
        for(int h = 0 ; h <  height ; h ++){
            cursor(y+h, x);
            for(int w = 0 ; w < width ; w ++){
                print(" ");
            }
        }
    }
    boolean stringToBoolean(String msg){
        if(msg=="true"){
            return true;
        }
        return false;
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
    Page NewPage(String Titre , String description,int fr,int hist, int math,int vie, int dmg ,int shield , int prix , int cout,int idx){
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
        p.idx = idx;

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
        stringToInt(getCell(PageCSV,row,9)),
        row
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
    Player newPlayer(String prenom ,int pdv, int lvl , int save,int x , int y , int mapX , int mapY, int knight, boolean[]Boss){
        Player p = new Player();
        p.Prenom = prenom;
        p.pdv = pdv;
        p.lvl = lvl;
        p.Book = newLivre();
        p.save = save;
        p.x = x;
        p.y = y;
        p.mapX = mapX;
        p.mapY = mapY;
        p.knight = knight;
        p.Boss[0] = Boss[0];
        p.Boss[1] = Boss[1];
        p.Boss[2] = Boss[2];

        return p;
    }
    void creePerso(int save){
        clearScreen();
        drawbox(40, 4, 100, 100, ANSI_BLUE);
        cursor(10,50);
        print("Quelle est ton prenom ?\t>");
        String tmpName  = readString();
        cursor(13,50);
        print("dans quelle classe est tu ?"+ANSI_GREEN+"0:CP 1:CE1-CE2 2:CM1-CM2"+ANSI_TEXT_DEFAULT_COLOR+"\t>");
        int tmplvl = readInt();
        for(int k = 0 ; k < length(knight); k ++){
            printLogo(knight[k], 15 , 41+ 35 * k,0);
            cursor(30,57+32*k);
            print(ANSI_GREEN +">>"+k+"<<"+ANSI_TEXT_DEFAULT_COLOR);
        }
        cursor(32,50);
        print("choisis ton chevalier\t>");
        int tmpknight = readInt(); 
        joueur = newPlayer(tmpName,3,tmplvl,save,10,10,1,1,tmpknight,new boolean []{false,false,false});
        for(int pageb = 0;pageb < 3; pageb ++){
            addToLivre(joueur.Book, getPage(pageb+1));
        }   
        print("c'est creer");
        addToSave(save+1, CSVToString(Save), joueur);
    }
    String[][] CSVToString(CSVFile file){
        String[][] S = new String[rowCount(file)][columnCount(file)];
        for(int y = 0 ; y < length(S,1) ; y ++){
            for(int x = 0 ; x < length(S,2) ; x ++){
                S[y][x] = getCell(file, y, x);
            }
        }
        return S;
    }
    void saveGame(Player p){
        addToSave(p.save,CSVToString(Save), p);
    }
    void addToSave(int id,String[][] save,Player p){
        String[][] tmpsave = save;
        tmpsave[id][0] = p.Prenom ;
        tmpsave[id][1] = ""+p.pdv ;
        tmpsave[id][2] = ""+p.lvl;
        tmpsave[id][3] = ""+p.mapX;
        tmpsave[id][4] = ""+p.mapY;
        tmpsave[id][5] = ""+p.x;
        tmpsave[id][6] = ""+p.y;
        tmpsave[id][7] = ""+p.knight;
        tmpsave[id][8] = ""+p.Book.Tomes[0].sommet;
        tmpsave[id][9] = ""+p.Boss[0];
        tmpsave[id][10] = ""+p.Boss[1];
        tmpsave[id][11] = ""+p.Boss[2];
        for(int pa = 0; pa < 25; pa++){
            if( pa < p.Book.Tomes[0].sommet){
                tmpsave[id][12+pa] = ""+p.Book.Tomes[0].Pages[pa].idx;
            }else{
                tmpsave[id][12+pa] = "null";
            }
        }
        saveCSV(tmpsave, "Save.csv");
        Save = loadCSV("Save.csv");
    }
    void supprSave(String filename,int save){
        String[][] tmpsave = CSVToString(loadCSV(filename)); 
        for(int x = 0 ; x < length(tmpsave, 2) ; x++){
            tmpsave[save+1][x] = "null";
        }
        saveCSV(tmpsave, filename);
        Save = loadCSV(filename);
    }
    Player CSVToPlayer(int id){ 
        Player p = newPlayer(
                    getCell(Save, id+1, 0),//prenom
        stringToInt(getCell(Save, id+1, 1)),//point de vie
        stringToInt(getCell(Save, id+1, 2)),//niveau
        id+1,//save
        stringToInt(getCell(Save, id+1, 5)), //x
        stringToInt(getCell(Save, id+1, 6)),//y
        stringToInt(getCell(Save, id+1, 3)),//MapX
        stringToInt(getCell(Save, id+1, 4)),//MapY
        stringToInt(getCell(Save, id+1, 7)),//Knight
        new boolean []{
        stringToBoolean(getCell(Save, id+1, 9)),
        stringToBoolean(getCell(Save, id+1, 10)),
        stringToBoolean(getCell(Save, id+1, 11))}
        );

        for(int s = 0; s < stringToInt(getCell(Save, id+1, 8)) ; s ++){
            addToLivre(p.Book, getPage(stringToInt(getCell(Save, id+1, s+12))));
        }
        return p;

    }
    void testSave(){
        //test cree perso
        String tPrenom = "Paul";
        int tlvl = 0;
        int tsave = 0;
        int tx = 10;
        int ty = 20;
        int tmapX = 1;
        int tmapY = 2;
        int tpdv = 3;
        boolean[] tBoss = {false,false,false};

        Player p = newPlayer(tPrenom, tpdv,tlvl, tsave, tx, ty, tmapX, tmapY,1,tBoss);
        assertEquals(tPrenom, p.Prenom);
        assertEquals(tlvl, p.lvl);
        assertEquals(tsave, p.save);
        assertEquals(tx, p.x);
        assertEquals(ty, p.y);
        assertEquals(tmapX, p.mapX);
        assertEquals(tmapY, p.mapY);
        // test addto save
        addToSave(1, CSVToString(Save) , p);

        String [][] tmpSave = CSVToString(Save);

        assertEquals(tmpSave[1][0], p.Prenom);
        assertEquals(tmpSave[1][1], ""+p.lvl);
        assertEquals(tmpSave[1][4], ""+p.x);
        assertEquals(tmpSave[1][5], ""+p.y);
        assertEquals(tmpSave[1][2], ""+p.mapX);
        assertEquals(tmpSave[1][3], ""+p.mapY);


        // test suppr save
        

    }
}