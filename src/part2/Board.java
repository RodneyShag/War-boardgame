package part2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;

import part1.Tile;
import part1.Color;

public class Board {
	/* Essential Info */
	public int rows = 6;		
	public int columns = 6;
	public Tile[][] tile = new Tile[6][6];
	
	/* Statuses */
	public Color playerTurn = Color.BLUE;
	public int blueScore = 0;
	public int greenScore = 0;
	public boolean gameEnded = false;
	public Color winner = Color.NONE;
	public ArrayList<Point> battlePoints = new ArrayList<>();
	
	/* Statuses for Part 2.2 */
	public int bluePieces  = 0;
	public int greenPieces = 0;
	
	/* Constructor: Read in a board from a file */
	public Board(String filename) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader("game_boards/" + filename + ".txt"));	//can throw FileNotFoundException
		String line;
		int row = 0;
        while ((line = br.readLine()) != null) { //can throw IOException
        	String[] values = line.split("\t");
        	for (int col = 0; col < columns; col++){
        		tile[row][col] = new Tile(Color.NONE, Integer.parseInt(values[col]));
        	}
        	row++;
        }
        br.close();
	}
	
	/* Copy constructor */
	public Board(Board otherBoard){
		/* Essential Info */
		this.rows = otherBoard.rows;
		this.columns = otherBoard.columns;
		this.tile = new Tile[rows][columns];
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
				tile[row][col] = new Tile(otherBoard.tile[row][col]);
			}
		}
		
		/* Statuses */
		this.playerTurn = otherBoard.playerTurn;
		this.blueScore  = otherBoard.blueScore;
		this.greenScore = otherBoard.greenScore;
		this.gameEnded  = otherBoard.gameEnded;
		this.winner     = otherBoard.winner;
		updateBattlePoints();
		
		/* Statuses for Part 2.2 */
		this.bluePieces  = otherBoard.bluePieces;
		this.greenPieces = otherBoard.greenPieces;
	}
	
	public boolean tileEmpty(Point pos){
		return tile[pos.y][pos.x].color == Color.NONE;
	}
	
	public void moveDrop(Color playerColor, Point p){
		if (!tileEmpty(p)){
			System.out.println("Error: Tried to DROP piece on occupied square");
			return;
		}
		tile[p.y][p.x].color = playerColor;
		if (playerColor == Color.BLUE)
			bluePieces++;
		else
			greenPieces++;
		updateBoard();
	}
	
	public void moveBattle(Color playerColor, Point p){
		if (!tileEmpty(p)){
			System.out.println("Error: Tried to place piece on occupied square");
			return;
		}
		tile[p.y][p.x].color = playerColor;
		if (playerColor == Color.BLUE)
			bluePieces++;
		else
			greenPieces++;
		
		/* Capture neighboring pieces */
		ArrayList<Point> points = getAdjacentPoints(p);
		for (Point battlePoint : points){
			Color tileColor = tile[battlePoint.y][battlePoint.x].color;
			if (tileColor != Color.NONE && tileColor != playerColor)
				Battle(battlePoint);
		}	
		updateBoard();
	}
	
	/* Does not do "Simultaneous" Battles */
	public void Battle(Point battlePoint){
		/* Get Unit Strengths */
		float blueUnitStrength  = (float) blueScore / bluePieces;
		float greenUnitStrength = (float) greenScore / greenPieces;
		
		/* Count neighbors */
		int blueNeighbors  = 0;
		int greenNeighbors = 0;
		ArrayList<Point> neighbors = getAdjacentPoints(battlePoint);
		for (Point neighbor : neighbors){
			if (tile[neighbor.y][neighbor.x].color == Color.BLUE)
				blueNeighbors++;
			else if (tile[neighbor.y][neighbor.x].color == Color.GREEN)
				greenNeighbors++;
		}
		
		/* Compare Cumulative Strengths to decide winner */
		float blueCumulativeStrength  = blueUnitStrength  * (blueNeighbors  + 1);
		float greenCumulativeStrength = greenUnitStrength * (greenNeighbors + 1);
		if (blueCumulativeStrength > greenCumulativeStrength)
			convertPiece(new Point(battlePoint.x, battlePoint.y), Color.BLUE);
		else
			convertPiece(new Point(battlePoint.x, battlePoint.y), Color.GREEN);
	}
	
	/* Extended Rule for Part 2.2 */
	public void Duel(Point battlePoint){
		/* Get Unit Strengths */
		float blueUnitStrength  = (float) blueScore / bluePieces;
		float greenUnitStrength = (float) greenScore / greenPieces;
		
		/* Compare Unit Strengths to decide winner */
		if (blueUnitStrength > greenUnitStrength)
			convertPiece(new Point(battlePoint.x, battlePoint.y), Color.BLUE);
		else
			convertPiece(new Point(battlePoint.x, battlePoint.y), Color.GREEN);
	}
	
	/* Converts a piece to a certain color */
	public void convertPiece(Point p, Color color){
		if (color == Color.BLUE){
			tile[p.y][p.x].color = Color.BLUE;
			bluePieces++;
			greenPieces--;
		}
		else if (color == Color.GREEN){
			tile[p.y][p.x].color = Color.GREEN;
			bluePieces--;
			greenPieces++;
		}
	}
	
	/* A point is valid if it is on the Board */
	public boolean validPoint(Point p){
		return (p.x >= 0 && p.y >= 0 && p.x < columns && p.y < rows);
	}
	
	/* Returns up to 4 adjacent points that are valid (on Board) */
	public ArrayList<Point> getAdjacentPoints(Point p){
		ArrayList<Point> points = new ArrayList<>();
		/* Left Point */
		Point leftPoint = new Point(p.x - 1, p.y);
		if (validPoint(leftPoint))
			points.add(leftPoint);
		/* Right Point */
		Point rightPoint = new Point(p.x + 1, p.y);
		if (validPoint(rightPoint))
			points.add(rightPoint);
		/* Bottom Point */
		Point bottomPoint = new Point(p.x, p.y - 1);
		if (validPoint(bottomPoint))
			points.add(bottomPoint);
		/* Top Point */
		Point topPoint = new Point(p.x, p.y + 1);
		if (validPoint(topPoint))
			points.add(topPoint);
		return points;
	}
	
	public void updateBoard(){
		updateTurn();
		updateScores(); //Must be done before updateGameStatus()
		updateBattlePoints();
		updateGameStatus();
	}
	
	public void updateTurn(){
		if (playerTurn == Color.BLUE)
			playerTurn = Color.GREEN;
		else
			playerTurn = Color.BLUE;
	}
	
	/* Recalculates scores from scratch */
	public void updateScores(){
		blueScore = 0;
		greenScore = 0;
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
				if (tile[row][col].color == Color.BLUE)
					blueScore += tile[row][col].value;
				else if (tile[row][col].color == Color.GREEN)
					greenScore += tile[row][col].value;
			}
		}
	}
	
	/* Updates gameEnded and winner. Assumes updateScores() was just called */
	public void updateGameStatus(){
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
				if (tileEmpty(new Point(col, row)))
					return;
			}
		}
		/* If code reaches here, Board is completely full */
		gameEnded = true;
		if (blueScore > greenScore)
			winner = Color.BLUE;
		else if (blueScore < greenScore)
			winner = Color.GREEN;
		else
			winner = Color.NONE;
	}
	
	/* A point with a BLUE and GREEN neighbor is a "Battle" Point */
	public void updateBattlePoints(){
		battlePoints.clear();
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
				if (tileEmpty(new Point(col, row))){
					boolean blueNeighbor = false;
					boolean greenNeighbor = false;
					ArrayList<Point> neighbors = getAdjacentPoints(new Point(col, row));
					for (Point p : neighbors){
						if (tile[p.y][p.x].color == Color.BLUE)
							blueNeighbor = true;
						else if (tile[p.y][p.x].color == Color.GREEN)
							greenNeighbor = true;
					}
					if (blueNeighbor && greenNeighbor){
						battlePoints.add(new Point(col, row));
					}
				}
			}
		}
	}
	
	public ArrayList<Board> getAdjacentBoards(){
		ArrayList<Board> boards = new ArrayList<>();
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
				Point p = new Point(col, row);
				if (battlePoints.contains(p)){
					Board board = new Board(this);
					board.moveBattle(playerTurn, p);
					boards.add(board);
				}
				else if (tileEmpty(p)){
					Board board = new Board(this);
					board.moveDrop(playerTurn, p);
					boards.add(board);
				}
			}
		}
		return boards;
	}
	
	public float utility(){
		int maxScore = getMaxScore();
		if (gameEnded){
			if (winner == Color.BLUE)
				return 10 * maxScore; //pretty arbitrary. Just has to be bigger than Absolute Value of inner nodes
			else if (winner == Color.GREEN)
				return -10 * maxScore; //pretty arbitrary. Just has to be bigger than Absolute Value of inner nodes
			else
				return 0;
		}
		float blueUnitStrength  = 0;
		float greenUnitStrength = 0;
		if (bluePieces > 0)
			blueUnitStrength  = (float) blueScore  / bluePieces;	
		if (greenPieces > 0)
			greenUnitStrength = (float) greenScore / greenPieces;
		return (blueScore - greenScore) + 3 * (blueUnitStrength - greenUnitStrength);
	}
	
	private int getMaxScore(){
		int maxScore = 0;
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
				maxScore += tile[row][col].value;
			}
		}
		return maxScore;
	}
	
    /* Enables us to print Board information with System.out.println() */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("*** blueScore = " + blueScore + "   greenScore = " + greenScore + " ***\n");
		/* Append original puzzle */
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
				sb.append(tile[row][col].value + "\t");
			}
			sb.append("\n");
		}
		sb.append("\n");
		/* Append who owns which squares */
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
				if (tile[row][col].color == Color.BLUE)
					sb.append("B");
				else if (tile[row][col].color == Color.GREEN)
					sb.append("G");
				else
					sb.append("-");
				sb.append("\t");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
