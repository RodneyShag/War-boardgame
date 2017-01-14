package part1;
import java.io.FileNotFoundException;
import java.io.IOException;

import animation.Animation;

public class Main {
	public static void main (String [] args) throws FileNotFoundException, IOException{
		Board board1 = new Board("Keren");
		Board board2 = new Board("Narvik");
		Board board3 = new Board("Sevastopol");
		Board board4 = new Board("Smolensk");
		Board board5 = new Board("Westerplatte");
		
		/* Board 1 */
		minimax_minimax(board1, 3, 3);
//		alphaBeta_alphaBeta(board1, 4, 4);
//		minimax_alphaBeta(board1, 3, 4);
//		alphaBeta_minimax(board1, 4, 3);
//		
//		/* Board 2 */
//		minimax_minimax(board2, 3, 3);
//		alphaBeta_alphaBeta(board2, 4, 4);
//		minimax_alphaBeta(board2, 3, 4);
//		alphaBeta_minimax(board2, 4, 3);
//		
//		/* Board 3 */
//		minimax_minimax(board3, 3, 3);
//		alphaBeta_alphaBeta(board3, 4, 4);
//		minimax_alphaBeta(board3, 3, 4);
//		alphaBeta_minimax(board3, 4, 3);
//		
//		/* Board 4 */
//		minimax_minimax(board4, 3, 3);
//		alphaBeta_alphaBeta(board4, 4, 4);
//		minimax_alphaBeta(board4, 3, 4);
//		alphaBeta_minimax(board4, 4, 3);
//		
//		/* Board 5 */
//		minimax_minimax(board5, 3, 3);
//		alphaBeta_alphaBeta(board5, 4, 4);
//		minimax_alphaBeta(board5, 3, 4);
//		alphaBeta_minimax(board5, 4, 3);
		
		/* Custom 4x4 Board: Change the Board Size before running code. */
//		Board custom4x4 = new Board("custom4x4");
//		minimax_minimax(custom4x4, 5, 5);
//		alphaBeta_alphaBeta(custom4x4, 7, 7);
//		minimax_alphaBeta(custom4x4, 5, 7);
//		alphaBeta_minimax(custom4x4, 7, 5);
		
		/* Custom 8x8 Board: Change the Board Size before running code. */
//		Board custom8x8 = new Board("custom8x8");
//		minimax_minimax(custom8x8, 2, 2);
//		alphaBeta_alphaBeta(custom8x8, 3, 3);
//		minimax_alphaBeta(custom8x8, 2, 3);
//		alphaBeta_minimax(custom8x8, 3, 2);
		
		/* Custom 16x16 Board: Change the Board Size before running code. */
//		Board custom16x16 = new Board("custom16x16");
//		alphaBeta_alphaBeta(custom16x16, 2, 2);

	}
	
	public static void minimax_minimax(Board board, int blueDepth, int greenDepth){
		Minimax blueStrategy = new Minimax(blueDepth);
		Minimax greenStrategy = new Minimax(greenDepth);
		int turn = 0;
		Animation.drawBoard(board);
		while(!board.gameEnded){
			if (turn % 2 == 0)
				board = blueStrategy.minimax(board);
			else
				board = greenStrategy.minimax(board);
			Animation.updateBoard(board);
			turn++;
		}
		Animation.updateBoard(board);
		
		System.out.println(board);
		System.out.println("Blue Player: total nodes expanded = " + blueStrategy.nodesExpanded);
		System.out.println("Blue Player: nodes expanded per move = " + blueStrategy.nodesExpanded / 18);
		System.out.println("Blue Player: Time per move = " + blueStrategy.elapsedTime / 18 + " seconds");
		System.out.println();
		System.out.println("Green Player: total nodes expanded = " + greenStrategy.nodesExpanded);
		System.out.println("Green Player: nodes expanded per move = " + greenStrategy.nodesExpanded / 18);
		System.out.println("Green Player: Time per move = " + greenStrategy.elapsedTime / 18 + " seconds");
		System.out.println();	
	}
	
	public static void alphaBeta_alphaBeta(Board board, int blueDepth, int greenDepth){
		AlphaBeta blueStrategy = new AlphaBeta(blueDepth);
		AlphaBeta greenStrategy = new AlphaBeta(greenDepth);
		int turn = 0;
		Animation.drawBoard(board);
		while(!board.gameEnded){
			if (turn % 2 == 0)
				board = blueStrategy.alphaBeta(board);
			else
				board = greenStrategy.alphaBeta(board);
			Animation.updateBoard(board);
			turn++;
		}
		Animation.updateBoard(board);
		
		System.out.println(board);
		System.out.println("Blue Player: total nodes expanded = " + blueStrategy.nodesExpanded);
		System.out.println("Blue Player: nodes expanded per move = " + blueStrategy.nodesExpanded / 18);
		System.out.println("Blue Player: Time per move = " + blueStrategy.elapsedTime / 18 + " seconds");
		System.out.println();
		System.out.println("Green Player: total nodes expanded = " + greenStrategy.nodesExpanded);
		System.out.println("Green Player: nodes expanded per move = " + greenStrategy.nodesExpanded / 18);
		System.out.println("Green Player: Time per move = " + greenStrategy.elapsedTime / 18 + " seconds");
		System.out.println();
	}
	
	public static void minimax_alphaBeta(Board board, int blueDepth, int greenDepth){
		Minimax blueStrategy = new Minimax(blueDepth);
		AlphaBeta greenStrategy = new AlphaBeta(greenDepth);
		int turn = 0;
		Animation.drawBoard(board);
		while(!board.gameEnded){
			if (turn % 2 == 0)
				board = blueStrategy.minimax(board);
			else
				board = greenStrategy.alphaBeta(board);
			Animation.updateBoard(board);
			turn++;
		}
		Animation.updateBoard(board);
		
		System.out.println(board);
		System.out.println("Blue Player: total nodes expanded = " + blueStrategy.nodesExpanded);
		System.out.println("Blue Player: nodes expanded per move = " + blueStrategy.nodesExpanded / 18);
		System.out.println("Blue Player: Time per move = " + blueStrategy.elapsedTime / 18 + " seconds");
		System.out.println();
		System.out.println("Green Player: total nodes expanded = " + greenStrategy.nodesExpanded);
		System.out.println("Green Player: nodes expanded per move = " + greenStrategy.nodesExpanded / 18);
		System.out.println("Green Player: Time per move = " + greenStrategy.elapsedTime / 18 + " seconds");
		System.out.println();	
	}
	
	public static void alphaBeta_minimax(Board board, int blueDepth, int greenDepth){
		AlphaBeta blueStrategy = new AlphaBeta(blueDepth);
		Minimax greenStrategy = new Minimax(greenDepth);
		int turn = 0;
		Animation.drawBoard(board);
		while(!board.gameEnded){
			if (turn % 2 == 0)
				board = blueStrategy.alphaBeta(board);
			else
				board = greenStrategy.minimax(board);
			Animation.updateBoard(board);
			turn++;
		}
		Animation.updateBoard(board);
		
		System.out.println(board);
		System.out.println("Blue Player: total nodes expanded = " + blueStrategy.nodesExpanded);
		System.out.println("Blue Player: nodes expanded per move = " + blueStrategy.nodesExpanded / 18);
		System.out.println("Blue Player: Time per move = " + blueStrategy.elapsedTime / 18 + " seconds");
		System.out.println();	
		System.out.println("Green Player: total nodes expanded = " + greenStrategy.nodesExpanded);
		System.out.println("Green Player: nodes expanded per move = " + greenStrategy.nodesExpanded / 18);
		System.out.println("Green Player: Time per move = " + greenStrategy.elapsedTime / 18 + " seconds");
		System.out.println();	
	}
}
