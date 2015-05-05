
package ija.homework2;

import java.io.*;
import ija.homework2.board.*;
import Java.lang.*;

/**
 *
 * @author Jan
 */
public class TextUI {
    
  
    public void start(MazeBoard board) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean quit = false;
        while (!quit){
            System.out.print("Pormpt>");
            String str = in.readLine();
            quit = processString(str, board);
        }    
    }
    
    private boolean processString(String str, MazeBoard board){
        boolean quit = false;
        switch(str){
            case "p":
                System.out.print("Hraci deska:\n");
                for (int i = 1; i <= 7; i++){
                    System.out.print("\n");
                    for(int j = 1; j <= 7; j++){
                        System.out.print(board.get(i, j).getCard().get_type()); 
                    }
                }
                System.out.print("\n\nVolny kamen:");
                System.out.print(board.getFreeCard().get_type());
                System.out.print("\n");
                break;
            case "n":
                board.newGame();
                break;
            case "q":
                quit = true;
                break;  
            default:
                if (str.length() == 3){
                    if ("s".equals(str.substring(0,1))) {
                        int R =(int)str.substring(1,2).charAt(0) - 48;
                        int C =(int)str.substring(2,3).charAt(0) - 48;
                        board.shift(new MazeField(R, C));
                    }    
                }
                else{
                    System.out.print("Zadan spatny prikaz\n");
                }
                break;
        }
        return quit;
    }
    
}
