package part2_attrition;

import java.util.Comparator;

/* Can sort an array of boards so that the moves that immediately give Green the largest point gain come first */
public class ScoreComparatorGreen implements Comparator<Board> {
	@Override
	public int compare(Board b1, Board b2){
		float b1_bluePointsAhead = b1.blueScore - b1.greenScore;
		float b2_bluePointsAhead = b2.blueScore - b2.greenScore;
		return (int) (b1_bluePointsAhead - b2_bluePointsAhead); //Notice we want the LOWER blue scores first
	}
}
