package part1;
import java.util.ArrayList;

/* There are 2 pseudocode examples of this:
 * 1) Lecture 8, Slide 11: Ugly pseudocode, but audio made sense, and I used this pseudocode.
 * 2) Wikipedia: Much better pseudocode.
 */
public class Minimax {
	
	public int depth;
	public int nodesExpanded;
	public double elapsedTime;
	
	/* Constructor - Important: depth must be at least 1 */
	public Minimax(int depth){
		this.depth = depth;
		this.nodesExpanded = 0;
		this.elapsedTime = 0;
	}
	
	public Board minimax(Board board){
		long startTime = System.currentTimeMillis();
		Board finalBoard = minimax(board, 0, depth);
		long endTime = System.currentTimeMillis();
		elapsedTime += (endTime - startTime) / 1000.0;
		return finalBoard;
	}
	
	public Board minimax(Board board, int currLevel, int maxDepth){
		if (board.gameEnded || (currLevel == maxDepth))
			return board;
		ArrayList<Board> successors = board.getAdjacentBoards();
		if (board.playerTurn == Color.BLUE){
			int max = Integer.MIN_VALUE;
			Board bestBoard = null;
			for (Board successor : successors){
				nodesExpanded++;
				Board lookaheadBoard = minimax(successor, currLevel + 1, maxDepth);
				if (lookaheadBoard.utility() > max){
					max = lookaheadBoard.utility();
					bestBoard = successor;
				}
			}
			return bestBoard;
		}
		else{
			int min = Integer.MAX_VALUE;
			Board bestBoard = null;
			for (Board successor : successors){
				nodesExpanded++;
				Board lookaheadBoard = minimax(successor, currLevel + 1, maxDepth);
				if (lookaheadBoard.utility() < min){
					min = lookaheadBoard.utility();
					bestBoard = successor;
				}
			}
			return bestBoard;
		}
	}
}
