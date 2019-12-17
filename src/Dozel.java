public class Dozel extends Program {

    // VARIABLE //
    enemies [] enemies;
    int playerX = 5;
    int playerY = 5;
    boolean running = true;
    boolean goodkey = true;
    int currentTile;
    int mapY = 0;
    int mapX = 0;
    int deplacement = 1;

    // COLOR VARIABLE //
    final String defaultc = ANSI_BLACK_BG;
    final String defaultbg = ANSI_BG_DEFAULT_COLOR;
    final String carcolor = ANSI_RED + ANSI_BOLD;
    final String enecolor = ANSI_PURPLE;
    final String gndcolor = ANSI_YELLOW_BG + ANSI_GREEN;
    final String treecolor = ANSI_GREEN + ANSI_BOLD + ANSI_GREEN_BG;
    final String watercolor = ANSI_BLUE_BG + ANSI_CYAN;

    // SKIN VARIABLE //
    String[][] skin = { { "" + (char) 9617, gndcolor },
                        { "" + (char) 492, carcolor },
                        { "" + (char) 26, enecolor },
                        { "" + (char) 9035, treecolor },
                        { " ", watercolor } 
    };

    // FUNCTION DEFINITION //

    // control function//
    void getkeyinput() {
        goodkey = true;
        enableKeyTypedInConsole(true);
        while (goodkey) {
            delay(10);
        }
        enableKeyTypedInConsole(false);
    }

    // FONCTION DE COLISION //
    boolean collision(int x, int y) {
        boolean rescoli = true;
        if (playerY <= 1) {
            playerY = length(fullset[0]) * (length(tiles[fullset[0][0][0]])) - 1;
            mapY--;
        } else if (playerY >= length(fullset[0]) * (length(tiles[fullset[0][0][0]]))) {
            playerY = 1;
            mapY++;
            print(mapY);
        } else if (playerX <= 1) {
            playerX = ((fullset[0].length) * ((tiles[fullset[0][0][0]][0].length) - 1)) - 1;
            mapX--;
        } else if (playerX >= (fullset[0].length) * (tiles[fullset[0][0][0]][0].length) - 1) {
            playerX = 1;
            mapX++;
        } else {
            int Xtiles = x / 8;
            int Ytiles = y / 8;
            int xInTile = x % 8;
            int yInTile = y % 8;
            int collisiontile = tiles[fullset[map[mapY][mapX]][Ytiles][Xtiles]][yInTile][xInTile];
            rescoli = (collisiontile == 0);
        }
        return rescoli;
    }

    // FONCTION DE RECUPERATION D'INPUT //
    void keyTypedInConsole(char c) {
        clearLine();
        backward(15);
        if (c == 'k') {
            print(defaultbg + defaultc);
            enableKeyTypedInConsole(false);
            println("au revoir copaing");
        } else if ((c == ANSI_UP || c == 'z') && (collision(playerX, playerY - deplacement))) {
            // avancer
            playerY -= deplacement;
            goodkey = !goodkey;
        } else if ((c == ANSI_DOWN || c == 's') && (collision(playerX, playerY + deplacement))) {
            // reculer
            playerY += deplacement;
            goodkey = !goodkey;
        } else if ((c == ANSI_LEFT || c == 'q') && (collision(playerX - deplacement, playerY))) {
            // gauche
            playerX -= deplacement;
            goodkey = !goodkey;
        } else if ((c == ANSI_RIGHT || c == 'd') && (collision(playerX + deplacement, playerY))) {
            // droite
            playerX += deplacement;
            goodkey = !goodkey;
        }
    }

    // FONCTION MOTEUR GRAPHIQUE //
    void printTiles(int tile) {

        for (int r = 0; r < fullset[tile].length; r++) {
            for (int y = 0; y < tiles[fullset[tile][r][0]].length; y++) {
                print(gndcolor);
                for (int l = 0; l < fullset[tile].length; l++) {
                    for (int x = 0; x < tiles[fullset[tile][r][l]][y].length; x++) {
                        print(skin[tiles[fullset[tile][r][l]][y][x]][1]);
                        if ((playerX == ((x + ((tiles[0][0].length) * l))))
                                && (playerY == (y + ((tiles[0].length) * r)))) {
                            print(skin[1][1] + skin[1][0] + " " + defaultc);
                        } else {
                            print(skin[tiles[fullset[tile][r][l]][y][x]][1] + skin[tiles[fullset[tile][r][l]][y][x]][0]
                                    + " ");
                        }
                    }
                    ;
                }
                print(defaultbg);
                println("");
            }
        }
        ;
        print(ANSI_BG_DEFAULT_COLOR);
    };

    void rescursor() {
        for (int i = 0; i < length(tiles, 2) * length(tiles, 1); i++) {
            print(ANSI_CURSOR_UP);
        }
    }

    
    //
    // TESTS //

    // ==========================================//

    // MAIN FUNCTION //
    void algorithm() {
        while (running) {
            printTiles(map[mapY][mapX]);
            getkeyinput();
            rescursor();
        }
    }

    // TILES DATA //
    int[][][] tiles = {
            // 0° empty tile
            { 
                { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                { 0, 0, 0, 0, 0, 0, 0, 0 } 
            },
            // 1° top lrft corner
            { 
                { 3, 3, 3, 3, 3, 3, 3, 3 },
                { 3, 3, 3, 3, 3, 0, 0, 0 },
                { 3, 3, 3, 3, 0, 0, 0, 0 },
                { 3, 3, 3, 0, 0, 0, 0, 0 },
                { 3, 3, 0, 0, 0, 0, 0, 0 }, 
                { 3, 0, 0, 0, 0, 0, 0, 0 },
                { 3, 0, 0, 0, 0, 0, 0, 0 }, 
                { 3, 0, 0, 0, 0, 0, 0, 0 } 
            },
            // 2° top right corner
            { { 3, 3, 3, 3, 3, 3, 3, 3 }, { 0, 0, 0, 3, 3, 3, 3, 3 }, { 0, 0, 0, 0, 3, 3, 3, 3 },
                    { 0, 0, 0, 0, 0, 3, 3, 3 }, { 0, 0, 0, 0, 0, 0, 3, 3 }, { 0, 0, 0, 0, 0, 0, 0, 3 },
                    { 0, 0, 0, 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 0, 0, 0, 3 } },
            // 3° bottom right corner
            { { 0, 0, 0, 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 0, 0, 0, 3 },
                    { 0, 0, 0, 0, 0, 0, 3, 3 }, { 0, 0, 0, 0, 0, 3, 3, 3 }, { 0, 0, 0, 0, 3, 3, 3, 3 },
                    { 0, 0, 0, 3, 3, 3, 3, 3 }, { 3, 3, 3, 3, 3, 3, 3, 3 } },
            // 4° bottom left corner
            { { 3, 0, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0, 0, 0, 0 },
                    { 3, 3, 0, 0, 0, 0, 0, 0 }, { 3, 3, 3, 0, 0, 0, 0, 0 }, { 3, 3, 3, 3, 0, 0, 0, 0 },
                    { 3, 3, 3, 3, 3, 0, 0, 0 }, { 3, 3, 3, 3, 3, 3, 3, 3 } },
            // 5° river left
            { { 0, 0, 0, 0, 4, 4, 4, 4 }, { 0, 0, 0, 0, 4, 4, 4, 4 }, { 0, 0, 0, 0, 4, 4, 4, 4 },
                    { 0, 0, 0, 0, 4, 4, 4, 4 }, { 0, 0, 0, 0, 4, 4, 4, 4 }, { 0, 0, 0, 0, 4, 4, 4, 4 },
                    { 0, 0, 0, 0, 4, 4, 4, 4 }, { 0, 0, 0, 0, 4, 4, 4, 4 } },
            // 6° river right
            { { 4, 4, 4, 4, 0, 0, 0, 0 }, { 4, 4, 4, 4, 0, 0, 0, 0 }, { 4, 4, 4, 4, 0, 0, 0, 0 },
                    { 4, 4, 4, 4, 0, 0, 0, 0 }, { 4, 4, 4, 4, 0, 0, 0, 0 }, { 4, 4, 4, 4, 0, 0, 0, 0 },
                    { 4, 4, 4, 4, 0, 0, 0, 0 }, { 4, 4, 4, 4, 0, 0, 0, 0 } },
            // 7° top wall
            { { 3, 3, 3, 3, 3, 3, 3, 3 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } },
            // 8° right wall
            { { 0, 0, 0, 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 0, 0, 0, 3 },
                    { 0, 0, 0, 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 0, 0, 0, 3 },
                    { 0, 0, 0, 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 0, 0, 0, 3 } },
            // 9° bottom wall
            { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0 }, { 3, 3, 3, 3, 3, 3, 3, 3 } },
            // 10° left wall
            { { 3, 0, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0, 0, 0, 0 },
                    { 3, 0, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0, 0, 0, 0 },
                    { 3, 0, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0, 0, 0, 0 } } };
    /* RAPPEL: */
    /*
     * - 0° empty tile - 1° top lrft corner - 2° top right corner - 3° bottom right
     * corner - 4° bottom left corner - 5° river left - 6° river right - 7° top wall
     * - 8° right wall - 9° bottom wall - 10° left wall
     */
    int[][][] fullset = {
            // 0° circle
            { { 1, 0, 0, 2 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 4, 0, 0, 3 } },
            // 1° point
            { { 0, 0, 0, 0 }, { 0, 3, 4, 0 }, { 0, 2, 1, 0 }, { 0, 0, 0, 0 } },
            // 2° point circle
            { { 1, 0, 0, 2 }, { 0, 3, 4, 0 }, { 0, 2, 1, 0 }, { 4, 0, 0, 3 } },
            // 3° circle river vertical
            { { 1, 5, 6, 2 }, { 0, 5, 6, 0 }, { 0, 5, 6, 0 }, { 4, 5, 6, 3 } },
            // 4° circle left wall
            { { 1, 0, 0, 2 }, { 10, 0, 0, 0 }, { 10, 0, 0, 0 }, { 4, 0, 0, 3 } },
            // 5° circle top wall
            { { 1, 7, 7, 2 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 4, 0, 0, 3 } },
            // 6° circle left wall
            { { 1, 0, 0, 2 }, { 0, 0, 0, 8 }, { 0, 0, 0, 8 }, { 4, 0, 0, 3 } },
            // 7° circle bottom wall
            { { 1, 0, 0, 2 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 4, 9, 9, 3 } },
            // 8° circle top left wall
            { { 1, 7, 7, 2 }, { 10, 0, 0, 0 }, { 10, 0, 0, 0 }, { 4, 0, 0, 3 } },
            // 9° circle top right wall
            { { 1, 7, 7, 2 }, { 0, 0, 0, 8 }, { 0, 0, 0, 8 }, { 4, 0, 0, 3 } },
            // 10° circle bottom right wall
            { { 1, 0, 0, 2 }, { 0, 0, 0, 8 }, { 0, 0, 0, 8 }, { 4, 9, 9, 3 } },
            // 11° circle bottom left wall
            { { 1, 0, 0, 2 }, { 10, 0, 0, 0 }, { 10, 0, 0, 0 }, { 4, 9, 9, 3 } }, };
    /* RAPPEL: */
    /*
     * - 0° circle - 1° point - 2° point circle - 3° circle river vertical - 4°
     * circle left wall - 5° circle top wall - 6° circle left wall - 7° circle
     * bottom wall - 8° circle top left wall - 9° circle top right wall - 10° circle
     * bottom right wall - 11° circle bottom left wall
     */
    int[][] map = { { 8, 5, 5, 9 }, { 4, 2, 0, 6 }, { 4, 0, 2, 6 }, { 11, 7, 7, 10 } };
}
