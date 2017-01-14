package animation;

import java.awt.Font;

import part1.Board;
import part1.Color;

/* 
 * Downloading and using StdDraw to draw was so intuitive:
 * http://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html
 */

public class Animation {
	
	static int squareWidth = 10;
	static int counter = 0;
	
	public static void drawBoard(Board board){
		int xWidth = board.columns * squareWidth;
		int yHeight = board.rows * squareWidth;
		StdDraw.setCanvasSize(xWidth*12, yHeight*12);
		StdDraw.setXscale(0, xWidth);
		StdDraw.setYscale(0, yHeight + 6); // Adding 6 here magically shifts Board down.
		StdDraw.setFont(new Font("Arial Bold", Font.ITALIC, 50));
		
		for (int row = 0; row < board.rows; row++){
			for (int col = 0; col < board.columns; col++){
				int xPos = (col * squareWidth) + (squareWidth) / 2;
				int yPos = (row * squareWidth) + (squareWidth) / 2;
				yPos = yHeight - yPos;
				StdDraw.setPenColor(java.awt.Color.GRAY);
				StdDraw.filledSquare(xPos, yPos, squareWidth/2);
				StdDraw.setPenColor(java.awt.Color.BLACK);
				StdDraw.text(xPos, yPos, Integer.toString(board.tile[row][col].value));
			}
		}
		StdDraw.setFont(new Font("Arial Bold", Font.PLAIN, 30));
		StdDraw.text(28, 63, "BlueScore = " + Integer.toString(board.blueScore) 
		              + "     GreenScore = " + Integer.toString(board.greenScore));
		StdDraw.save("picture" + counter + ".jpg");
		counter++;
		StdDraw.show(50);
	}
	
	/* Not efficient. Redraws everything. */
	public static void updateBoard(Board board){
		StdDraw.clear();
		int yHeight = board.rows * squareWidth;
		StdDraw.setFont(new Font("Arial Bold", Font.ITALIC, 50));
		for (int row = 0; row < board.rows; row++){
			for (int col = 0; col < board.columns; col++){
				int xPos = (col * squareWidth) + (squareWidth) / 2;
				int yPos = (row * squareWidth) + (squareWidth) / 2;
				yPos = yHeight - yPos;
				StdDraw.setPenColor(java.awt.Color.GRAY);
				StdDraw.filledSquare(xPos, yPos, squareWidth/2);
				if (board.tile[row][col].color == Color.GREEN){
					StdDraw.setPenColor(java.awt.Color.GREEN);
					StdDraw.filledCircle(xPos, yPos, squareWidth / 2);
				}
				else if (board.tile[row][col].color == Color.BLUE){
					StdDraw.setPenColor(java.awt.Color.BLUE);
					StdDraw.filledCircle(xPos, yPos, squareWidth / 2);
				}
				StdDraw.setPenColor(java.awt.Color.BLACK);
				StdDraw.text(xPos, yPos, Integer.toString(board.tile[row][col].value));
			}
		}
		StdDraw.setFont(new Font("Arial Bold", Font.PLAIN, 30));
		StdDraw.text(28, 63, "BlueScore = " + Integer.toString(board.blueScore) 
		              + "     GreenScore = " + Integer.toString(board.greenScore));
		StdDraw.save("picture" + counter + ".jpg");
		counter++;
		StdDraw.show(50);
	}
}
