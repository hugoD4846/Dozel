public class colision extends Program {
    void coli(int x, int y){
        int Xtiles = x/8;
        int Ytiles = y/8;
        int xInTile = x%8;
        int yInTile = y%7;
        println(Xtiles);
        println(Ytiles);
        println(xInTile);
        println(yInTile);
    }
    void testcoli(){
        coli(7,22);
    }
}