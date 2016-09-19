package nim;

/**
 * Holds all of the data used in the Nim game.
 * 
 * @author Julian
 *
 */
public class Model {

	static final int MAX_COLUMN_BALLS = 100;
	private boolean running = true;
	private int turnCount = 1;
	private Column lastModifiedColumn;
	private PlayerType currentPlayerTurn;
	private final Column colA, colB, colC;

	public enum PlayerType {
		USER, COMPUTER
	};

	public Model() {
		this.colA = new Column("Column A", MAX_COLUMN_BALLS);
		this.colB = new Column("Column B", MAX_COLUMN_BALLS);
		this.colC = new Column("Column C", MAX_COLUMN_BALLS);
	}

	public boolean getRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getTurnCount() {
		return turnCount;
	}

	public Column getColA() {
		return colA;
	}

	public Column getColB() {
		return colB;
	}

	public Column getColC() {
		return colC;
	}

	public Column getLastModifiedColumn() {
		return lastModifiedColumn;
	}

	public void setLastModifiedColumn(Column lastModifiedColumn) {
		this.lastModifiedColumn = lastModifiedColumn;
	}

	public void incrementTurnCount() {
		turnCount = turnCount++;
	}

	public PlayerType getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(PlayerType currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}

}
