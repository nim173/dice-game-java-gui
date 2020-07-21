package view.model.constants;

public class CommonConstants {
	/** Constant for tooltips for Add Player actions */
	public static final String ADD = "Add a new player";
	
	/** Constant for tooltips for Remove Player actions */
	public static final String REMOVE = "Remove the currently selected player";
	
	/** Constant for tooltips for Remove All Players actions */
	public static final String REMOVE_All = "Removes all players in the game";
	
	/** Constant for information dialog indicating that there are no more players to remove from the game */
	public static final String REMOVE_PLAYER_ERROR = "There are no more players to remove";
	
	/** Constant for dialog for confirmation of removal of all players */
	public static final String REMOVE_CONFIRM = "Are you sure?";
	
	/** Constant for information dialog indicating that there are no more players to reset bet */
	public static final String REMOVE_BET_SUCCESS = "Bet removed";
	
	/** Constant for information dialog indicating that there are no more players to reset bet */
	public static final String REMOVE_BET_ERROR = "There are no more players to remove";

	/** Constant for roll player action */
	public static final String ROLL = "Place a bet to roll";

	/** Constant for bet action */
	public static final String BET = "Place a Bet";
	
	/** Constant for player who has rolled */
	public static final String ALREADY_ROLLED = "Player has already rolled";

	/** Constant for reset bet action */
	public static final String RESET_CURRENT_BET = "Reset bet for the current player";
	
	/** Constant for dialog when house is ready to roll after players have rolled */
	public static final String HOUSE_ROLL_TITLE = "House is Ready";
	
	/** Constant for dialog when house is ready to roll after players have rolled */
	public static final String HOUSE_ROLL_MESSAGE = "All players have rolled\nPress OK to Continue";
	
	/** Constant for error message when player has an invalid initital points */
	public static final String ADD_PLAYER_POINT_ERROR = "Please enter a valid number for Initial Points";
	
	/** Constant for error message when player details are left blank */
	public static final String ADD_PLAYER_DETAIL_ERROR = "Please enter all the details";
	
	/** Constant for dialog message when player has reset bet */
	public static final String BET_RESET_MESSAGE = "Bet reset to zero";
	
	/** Constant for error message when player has entered an invalid bet */
	public static final String INVALID_BET_ERROR = "Please enter a valid bet!\n(greater than zero and not higher than your current points)";
	
	/** Constant for dialog for removal of a player that has reached zero points from the game */
	public static final String ROLL_REMOVE_PLAYER_MESSAGE = "The following player(s) will be removed from the game\n(Reason: Zero Points)\n- ";
	
	/** Constant for dialog for removal of a player that has reached zero points from the game */
	public static final String ROLL_REMOVE_PLAYER_TITLE = "Player(s) Removed";
	
	/** Constant for status bar message when there are no players added */
	public static final String ADD_PLAYER_STATUS = "Add a player to start";
	
	/** Constant for status when another player is rolling */
	public static final String ROLL_WAIT = "Another player is rolling, please wait";
	
	/** Constant for play again dialog **/
	public static final String PLAY_AGAIN = "Play Again?";
	
	/** Constant for status when player is rolling **/
	public static final String PLAYER_ROLLING = "Player is rolling";
	
	/** Constant for status when a round has finished */
	public static final String NEXT_ROUND = "Next Round";
	
	/** Constant for when all players are removed */
	public static final String ALL_PLAYERS_REMOVED = "All Players Removed";
	
	/** Constant for when a player is removed */
	public static final String PLAYER_REMOVED = "Player Removed";
	
	/** Constant for status when house is rolling */
	public static final String HOUSE_ROLLING = "House is Rolling";
	
	/** Constant for status when house has finished rolling */
	public static final String HOUSE_ROLL_FINISHED = "House has rolled. Check Results";
	
	/** Constant for inital delay parameter for dice 1 */
	public static final int INITIAL_DELAY_1 = 100;
	
	/** Constant for inital delay parameter for dice 1 */
	public static final int FINAL_DELAY_1 = 1000;
	
	/** Constant for delay increment for dice 1 */
	public static final int DELAY_INCREMENT_1 = 100;
	
	/** Constant for inital delay parameter for dice 2 */
	public static final int INITIAL_DELAY_2 = 50;
	
	/** Constant for inital delay parameter for dice 2 */
	public static final int FINAL_DELAY_2 = 500;
	
	/** Constant for delay increment for dice 2 */
	public static final int DELAY_INCREMENT_2 = 50;
}
