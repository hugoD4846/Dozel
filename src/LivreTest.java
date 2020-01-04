import extensions.CSVFile;

class LivreTest extends Program{
    //global variables
    final CSVFile PageCSV = loadCSV("Page.csv");
    final CSVFile[] questionFrancais = {loadCSV("QuCPFrancais.csv")};
    int lvl;
    // main function
    void playerturn(){
        print("dans quelle classe est tu ?");
        lvl = readInt();
        int conf,saisie;
        do{
            print("imprime quel page ?  :");
            saisie = readInt();
            print(getPage(saisie));
            delay(1000);
            print("Bonne page ? ");
            conf = readInt();
        }while(conf != 1);
        Page sort = getPage(saisie);
        printTitle("Pour que ton sort fonction tu devra repondre au theme suivant ");
        println(  ANSI_GREEN+ "\t\t"+ sort.qu[0] +" en Francais" );
        println(  ANSI_RED+ "\t\t"+ sort.qu[1] + " en Histoire" );
        println(  ANSI_BLUE+ "\t\t"+ sort.qu[2] + " en Mathematique" );
        println();
        reset();
        if(repondre(sort)){
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
            tmpQuestion = getQuestion(questionFrancais[lvl],(int)(random()*rowCount(questionFrancais[lvl])-1)+1);
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
        print(ANSI_BOLD + ANSI_UNDERLINE);
        println("\t" + text + "\n");
        reset();
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
   
}