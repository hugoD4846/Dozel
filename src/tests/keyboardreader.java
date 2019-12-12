public class keyboardreader extends Program{
    void keyTypedInConsole(char c){
	print(c);
	clearLine();
	backward(15);
	if(c == 'k'){
	    enableKeyTypedInConsole(false);
	    println("au revoir copaing");
	}else if(c == ANSI_UP){
	    print("avancer");
	}else if(c == ANSI_DOWN){
	    print("reculer");
	}else if(c == ANSI_LEFT){
	    print("gauche");
	}else if(c == ANSI_RIGHT){
	    print("droite");
	}
    }
    void algorithm(){
	println("salut copaing");
	
	while(true){
	}
    }
}
