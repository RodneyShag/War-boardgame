package part1;

import java.util.Comparator;

/* Can sort an array of boards so that the moves that immediately give Green the largest point gain come first */
public class ScoreComparatorGreen implements Comparator<Board> {
	@Override
	public int compare(Board b1, Board b2){
		int b1_bluePointsAhead = b1.blueScore - b1.greenScore;
		int b2_bluePointsAhead = b2.blueScore - b2.greenScore;
		return b1_bluePointsAhead - b2_bluePointsAhead; //Notice we want the LOWER blue scores first
	}
}
