import java.awt.Color;

public class Block {
	final boolean wall;
	String label;

	boolean ball;
	Color color;
	
	public Block(boolean wall) {
		this.wall = wall;
		label = null;
		color = null;
		ball = false;
	}
	public Block(
		boolean wall,
		String label,
		boolean ball,
		Color color
	) {
		this.wall = wall;
		this.label = label;
		this.color = color;
		this.ball = ball;
	}

	Block copyOf() {
		return new Block(
			this.wall,
			this.label,
			this.ball,
			this.color
		);
	}
}