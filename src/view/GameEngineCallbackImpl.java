package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

   public GameEngineCallbackImpl()
   {
      // FINE shows rolling output, INFO only shows result
      logger.setLevel(Level.FINE);
   }

   @Override
   public void playerDieUpdate(Player player, Die die, GameEngine gameEngine)
   {
      // intermediate results logged at Level.FINE
    logger.log(Level.FINE, String.format("%s die %d rolled to %s", player.getPlayerName(), die.getNumber(), die.toString()));
   }

   @Override
   public void playerResult(Player player, DicePair result, GameEngine gameEngine)
   {
      // final results logged at Level.INFO
      logger.log(Level.INFO, String.format("%s *RESULT*:  %s", player.getPlayerName(), result.toString()));
   }

	@Override
	public void houseDieUpdate(Die die, GameEngine gameEngine) {
		// intermediate results logged at Level.FINE
		logger.log(Level.FINE, String.format("House die %d rolled to %s", die.getNumber(), die.toString()));
	}
	
	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		// final results logged at Level.INFO
	    logger.log(Level.INFO, String.format("House *RESULT*:  %s", result.toString()));		
	    logger.log(Level.INFO, String.format("FINAL PLAYER RESULTS\n%s", this.finalPlayerResults(gameEngine)));
	}
	
	private String finalPlayerResults(GameEngine gameEngine) {
		String resultString = new String();
		for (Player player: gameEngine.getAllPlayers()) {
			resultString += player.toString() + "\n";
		}
		return resultString;
	}
}
