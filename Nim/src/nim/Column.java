package nim;

/**
 * Represents a column with balls in it as part of the Nim game.
 * 
 * @author Julian
 *
 */
public class Column {
	private String name;
	private int balls = 0;

	public Column(String name, int balls) {
		this.name = name;
		this.balls = balls;
	}

	public String getName() {
		return this.name;
	}

	public int getBalls() {
		return this.balls;
	}

	public void modifyColumn(int ballsToTake) {
		balls = balls - ballsToTake;
	}
}
