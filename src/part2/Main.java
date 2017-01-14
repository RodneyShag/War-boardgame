package part2;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main (String [] args) throws FileNotFoundException, IOException{
		Board board3 = new Board("Sevastopol");
		Board board4 = new Board("Smolensk");
		Board board5 = new Board("Westerplatte");
		
		/* Battle */
//		alphaBeta_alphaBeta(board3, 4, 4);
//		alphaBeta_alphaBeta(board4, 4, 4);
//		alphaBeta_alphaBeta(board5, 4, 4);
		
		/* Duel: 
		 * 1) In class "Board", find "moveBattle()" and change Battle() to Duel().
		 * 2) In class "Board", find "utility()" and change the evaluation function "to blueUnitStrength - greenUnitStrength"
		 */
		alphaBeta_alphaBeta(board3, 4, 4);
		alphaBeta_alphaBeta(board4, 4, 4);
		alphaBeta_alphaBeta(board5, 4, 4);
		
	}
	
	public static void alphaBeta_alphaBeta(Board board, int blueDepth, int greenDepth){
		AlphaBeta blueStrategy = new AlphaBeta(blueDepth);
		AlphaBeta greenStrategy = new AlphaBeta(greenDepth);
		int turn = 0;
		while(!board.gameEnded){
			if (turn % 2 == 0)
				board = blueStrategy.alphaBeta(board);
			else
				board = greenStrategy.alphaBeta(board);
			turn++;
		}
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
