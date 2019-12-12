public class Game extends Program {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    void algorithm(){
	for(int k = 0 ;k < 13;k++){
	    for(int i = 0 ;i < 1000; i++){
		
		if( (char)(i+1) != ' '){
		    print("  >>"+(i+1+(1000*k)) +ANSI_CYAN +" => "+ ANSI_RESET +(char)(i+1+1000*k));
		}
		if( i%10 ==0){ 
		    println("");
		}
	    }
        delay(10000);
	}
    }
    
    
}
