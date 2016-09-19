package nim;

import java.util.Random;

import nim.Model.PlayerType;

/**
 * Nim is a mathematical game of strategy in which two players take turns
 * removing objects from distinct heaps. On each turn, a player must remove at
 * least one object, and may remove any number of objects provided they all come
 * from the same heap. The goal of the game is to be the player to remove the
 * last object.
 * 
 * @author Julian
 *
 */
public class Nim {

	private final Ai comp;
	private final User user;
	private final Model model;

	public Nim() {
		this.model = new Model();
		this.comp = new Ai(model);
		this.user = new User(model);

	}

	/**
	 * Randomly chooses a starting player and then oscillates between a user and a
	 * computer player's turn. This continues until all balls have been removed
	 * from all columns.
	 */
	public static void main(String[] args) {
		System.out.println("The game starts with " + Model.MAX_COLUMN_BALLS + " balls in 3 columns. During a players \n"
				+ "turn, they can take any amount of balls from a column provided \n"
				+ "it is greater than 0. Turns alternate and the player who takes \n"
				+ "the last ball wins. The player who moves first is chosen at \n" + "random. Here goes! \n");
		Nim nim = new Nim();
		nim.chooseStartingPlayer();
		while (nim.model.getRunning()) {
			nim.playTurn();
		}
	}

	/**
	 * Randomly chooses the player to start the game.
	 */
	private void chooseStartingPlayer() {
		Random rand = new Random();
		if (rand.nextInt(2) == 0) {
			model.setCurrentPlayerTurn(PlayerType.USER);
			System.out.println("Your turn to start first!");
		} else {
			model.setCurrentPlayerTurn(PlayerType.COMPUTER);
			System.out.println("AI (Computer) starts first!");
		}
	}

	/**
	 * Shows the game state and then plays a player's turn.
	 */
	private void playTurn() {
		showGameState();
		if (model.getCurrentPlayerTurn() == PlayerType.USER) {
			user.makeMove();
		} else {
			comp.makeMove();
		}
	}

	/**
	 * Prints game state to console.
	 */
	private void showGameState() {
		System.out.println("The current board: \n Column A: " + model.getColA().getBalls() + "\n Column B: "
				+ model.getColB().getBalls() + "\n Column C: " + model.getColC().getBalls() + "\n");
	}
}
