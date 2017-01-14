package part1;
import java.util.ArrayList;
import java.util.Collections;

/* There are 2 pseudocode examples of this:
 * 1) Lecture 8, Slides 21-22: Made sense, but tricky since 2 recursive functions calling each other
 * 2) Wikipedia: Great simplification!
 */
public class AlphaBeta {
	
	public int depth;
	public int nodesExpanded;
	public double elapsedTime;
	
	/* Constructor - Important: depth must be at least 1 */
	public AlphaBeta(int depth){
		this.depth = depth;
		this.nodesExpanded = 0;
		this.elapsedTime = 0;
	}
	
	public Board alphaBeta(Board board){
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		long startTime = System.currentTimeMillis();
		Board finalBoard = alphaBeta(board, 0, depth, alpha, beta);
		long endTime = System.currentTimeMillis();
		elapsedTime += (endTime - startTime) / 1000.0;
		return finalBoard;
	}
	
	public Board alphaBeta(Board board, int currLevel, int maxDepth, int alpha, int beta){
		if (board.gameEnded || (currLevel == maxDepth))
			return board;
		ArrayList<Board> successors = board.getAdjacentBoards();
		
		if (board.playerTurn == Color.BLUE){
			//Collections.sort(successors, new ScoreComparatorBlue());
			int max = Integer.MIN_VALUE;
			Board bestBoard = null;
			for (Board successor : successors){
				nodesExpanded++;
				Board lookaheadBoard = alphaBeta(successor, currLevel + 1, maxDepth, alpha, beta);
				if (lookaheadBoard.utility() > max){
					max = lookaheadBoard.utility();
					bestBoard = successor;
				}
				alpha = Math.max(alpha, lookaheadBoard.utility());
				if (lookaheadBoard.utility() >= beta)
					return lookaheadBoard;
			}
			return bestBoard;
		}
		else{
			//Collections.sort(successors, new ScoreComparatorGreen());
			int min = Integer.MAX_VALUE;
			Board bestBoard = null;
			for (Board successor : successors){
				nodesExpanded++;
				Board lookaheadBoard = alphaBeta(successor, currLevel + 1, maxDepth, alpha, beta);
				if (lookaheadBoard.utility() < min){
					min = lookaheadBoard.utility();
					bestBoard = successor;
				}
				beta = Math.min(beta, lookaheadBoard.utility());
				if (lookaheadBoard.utility() <= alpha)
					return lookaheadBoard;
			}
			return bestBoard;
		}
	}
	
}
