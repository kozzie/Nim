package nim;

import java.util.Scanner;

import nim.Column;
import nim.Model.PlayerType;

/**
 * Represents a general player of the game with general player actions.
 * 
 * @author Julian
 *
 */
public class Player {
	protected Model model;
	Scanner scanner = new Scanner(System.in);

	public Player(Model model) {
		this.model = model;
	}

	// modifies the actual columns for each turn and updates game state variables
	protected void takeBallsFromColumn(Column col, int balls) {
		col.modifyColumn(balls);
		System.out.println(getPlayerTurnMsgStr() + " take " + balls + " ball(s) from " + col.getName());
		model.setLastModifiedColumn(col);
		model.incrementTurnCount();
		int columnsLeft = countColumnsLeft();

		if (columnsLeft == 0) {
			System.out.println(getPlayerTurnMsgStr() + " win! There are no more balls in any of the columns. End of game.");
			model.setRunning(false);
			scanner.close();
		}
		if (model.getCurrentPlayerTurn() == PlayerType.USER) {
			model.setCurrentPlayerTurn(PlayerType.COMPUTER);
		} else {
			model.setCurrentPlayerTurn(PlayerType.USER);
		}
	}

	/**
	 * Counts and returns the remaining columns in play.
	 */
	protected int countColumnsLeft() {
		int columnsLeft = 3;
		if (model.getColA().getBalls() <= 0) {
			columnsLeft--;
		}
		if (model.getColB().getBalls() <= 0) {
			columnsLeft--;
		}
		if (model.getColC().getBalls() <= 0) {
			columnsLeft--;
		}
		return columnsLeft;
	}

	private String getPlayerTurnMsgStr() {
		if (model.getCurrentPlayerTurn() == PlayerType.COMPUTER) {
			return "I";
		} else {
			return "You";
		}
	}

}
