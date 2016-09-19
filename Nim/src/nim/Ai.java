package nim;

import java.util.ArrayList;
import java.util.Random;

import nim.Column;

/**
 * Represents a computer player and controls the move logic and actions.
 * 
 * @author Julian
 *
 */
public class Ai extends Player {
	private final Random rand = new Random();

	public Ai(Model model) {
		super(model);
	}

	/**
	 * Plays the computer opponent's move.
	 */
	public void makeMove() {
		switch (countColumnsLeft()) {
		case 1:
			// If there's only one column left, take all the balls.
			Column column = getNonEmptyColumn();
			takeBallsFromColumn(column, column.getBalls());
			break;
		case 2:
			Column mostBalls = getColumnWithMostBalls();
			Column leastBalls = getColumnWithLeastBalls();
			int ballsToTake = mostBalls.getBalls() - leastBalls.getBalls();
			if (mostBalls.getBalls() == 1) {
				ballsToTake = 1;
			} else if (ballsToTake == 0) {
				ballsToTake = rand.nextInt(mostBalls.getBalls() - 1) + 1;
			}
			takeBallsFromColumn(mostBalls, ballsToTake);
			break;
		// If there are 3 columns left and it is turn one, take all balls from a
		// random column.
		case 3:
			if (model.getTurnCount() == 1) {
				// Take all of the balls from a random column.
				takeBallsFromColumn(selectRandomColumn(), Model.MAX_COLUMN_BALLS);
			} else {
				// If it is not the first turn, take the remaining balls from the
				// column that was last modified.
				takeBallsFromColumn(model.getLastModifiedColumn(), model.getLastModifiedColumn().getBalls());
			}
			break;
		}
	}

	/**
	 * Randomly returns a non-empty column.
	 */
	private Column getNonEmptyColumn() {
		ArrayList<Column> columns = getNonEmptyColumns();
		return columns.get(rand.nextInt(columns.size()));
	}

	/**
	 * Creates and returns a list of columns that are not empty.
	 */
	private ArrayList<Column> getNonEmptyColumns() {
		ArrayList<Column> nonEmptyColumnsList = new ArrayList<Column>();
		if (model.getColA().getBalls() > 0) {
			nonEmptyColumnsList.add(model.getColA());
		}
		if (model.getColB().getBalls() > 0) {
			nonEmptyColumnsList.add(model.getColB());
		}
		if (model.getColC().getBalls() > 0) {
			nonEmptyColumnsList.add(model.getColC());
		}
		return nonEmptyColumnsList;
	}

	/**
	 * Returns the Column that has the most balls remaining.
	 */
	private Column getColumnWithMostBalls() {
		Column largestSoFar = model.getColA();
		if (model.getColA().getBalls() < model.getColB().getBalls()) {
			largestSoFar = model.getColB();
		}
		if (model.getColB().getBalls() < model.getColC().getBalls()) {
			largestSoFar = model.getColC();
		}
		return largestSoFar;
	}

	/**
	 * Compares three Columns to return the one with the least amount of balls
	 * remaining.
	 */
	private Column getColumnWithLeastBalls() {
		return getSmallest(model.getColA(), getSmallest(model.getColB(), model.getColC()));
	}

	/**
	 * Compares two Columns and returns the one with the least amount of balls
	 * remaining.
	 */
	private Column getSmallest(Column c1, Column c2) {
		if (c1.getBalls() == 0) {
			return c2;
		}
		if (c2.getBalls() == 0) {
			return c1;
		}
		if (c1.getBalls() < c2.getBalls()) {
			return c1;
		} else {
			return c2;
		}
	}

	/**
	 * Selects a random column to take balls from.
	 */
	private Column selectRandomColumn() {
		Random r = new Random();
		int i = r.nextInt(3);
		if (i == 0) {
			return model.getColA();
		} else if (i == 1) {
			return model.getColB();
		} else {
			return model.getColC();
		}
	}
}
