/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.game;

import java.io.IOException;
import ija.game.board.MazeBoard;
/**
 *
 * @author Jan
 */


public class Main {
    
    public static void main(String argv[]) throws IOException{
        
        MazeBoard board = MazeBoard.createMazeBoard(7);
        TextUI textUI = new TextUI();
        textUI.start(board);
    }
            
    
}
