package part1;


public class Tile {
	public Color color;
	public final int value; // final: can be initialized in Constructor. Then can't change.

	public Tile(Color color, int value){
		this.color = color;
		this.value = value;
	}
	
	public Tile(Tile otherTile){
		this.color = otherTile.color;
		this.value = otherTile.value;
	}
}
