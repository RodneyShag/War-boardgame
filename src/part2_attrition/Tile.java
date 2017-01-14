package part2_attrition;

import part1.Color;

/* Tile now uses "Float" values for "Attrition" rule to work */
public class Tile {
	public Color color;
	public float value; // final: can be initialized in Constructor. Then can't change.

	public Tile(Color color, float value){
		this.color = color;
		this.value = value;
	}
	
	public Tile(Tile otherTile){
		this.color = otherTile.color;
		this.value = otherTile.value;
	}
}
