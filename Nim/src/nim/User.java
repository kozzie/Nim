package nim;

import java.util.InputMismatchException;

import nim.Column;

/**
 * Represents a user player and prompts the user for input to carry out turn
 * actions.
 * 
 * @author Julian
 *
 */
public class User extends Player {

	public User(Model model) {
		super(model);
	}

	/**
	 * Plays the user's move by prompting the user for a Column to take balls from
	 * and the number of balls to take.
	 */
	public void makeMove() {
		Column columnToModify = getColumnFromUser();
		int ballsToTake = getHowManyBallsToTakeFromUser(columnToModify);
		takeBallsFromColumn(columnToModify, ballsToTake);
	}

	/**
	 * Prompts user to select a Column to take balls from. The input from the user
	 * is taken as a single letter 'a', 'b' or 'c'.
	 */
	private Column getColumnFromUser() {
		while (true) {
			Column column = null;
			System.out.println("It is now your turn. Which column would you like to take from? Please Enter a, b or c.");
			String stringToConvert = scanner.next();
			char[] convertToChar = stringToConvert.toCharArray();
			column = getColumnByName(convertToChar[0]);
			if (column == null) {
				System.out.println("You must enter a, b or c in lower case, you also can not choose a column that is empty");
			} else if (column.getBalls() == 0) {
				System.out.println("You can not take from a column that is empty. Please try again.");
			} else {
				return column;
			}
		}
	}

	/**
	 * Matches the user input to a Column name and returns the Column.
	 */
	private Column getColumnByName(char name) {
		switch (name) {
		case 'a':
			return model.getColA();
		case 'b':
			return model.getColB();
		case 'c':
			return model.getColC();
		default:
			return null;
		}
	}

	/**
	 * Prompts user to select the amount of balls to take from the column. The
	 * input from the user is taken as an integer that must be between 1 and the
	 * total amount of balls in the column.
	 */
	private int getHowManyBallsToTakeFromUser(Column column) {
		int ballsLeft = column.getBalls();
		System.out.println("How many balls would you like to take from " + column.getName() + "?");
		while (true) {
			try {
				int ballsToTake = scanner.nextInt();
				while (ballsToTake < 1 || ballsToTake > ballsLeft) {
					System.out.println("You must choose between 1(inclusive) and " + ballsLeft + " (inclusive). Try Again.");
					scanner.nextInt();
				}
				return ballsToTake;

			} catch (InputMismatchException e) {
				System.out.println("You must enter an integer only.");
				scanner.next();
			}
		}
	}

}
