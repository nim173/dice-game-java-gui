package view.interfaces;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * Assignment interface for Further Programming to notify client of GameEngine events<br>
 * i.e. the state of the dice as roll and when they have landed and stopped
 * 
 * @author Caspar Ryan
 */
public interface GameEngineCallback
{
   /**
    * called as the Player's die turns during a roll<br>
    * use this to update your GUI or log to console
    * 
    * @param player - the Player who rolled
    * @param die - die representing the current state of one of the Player's dice 
    * @param gameEngine - a convenience reference to the engine so the receiver can call methods if necessary
    * @see model.interfaces.GameEngine
    */
   public void playerDieUpdate(Player player, Die die, GameEngine gameEngine);

   /**
    * called as the House's die turns during a roll<br>
    * use this to update your GUI or log to console
    * 
    * @param die - die representing the current state of one of the House's dice
    * @param gameEngine - a convenience reference to the engine so the receiver can call methods if necessary
    * @see model.interfaces.GameEngine
    */
   public void houseDieUpdate(Die die, GameEngine gameEngine);

   /**
    * called when the Player's dice have stopped rolling<br>
    * 
    * @param player - the Player who rolled
    * @param result - the state of the Player's dice when rolling has stopped
    * @param gameEngine - a convenience reference to the GameEngine so the receiver can call methods if necessary
    * @see model.interfaces.GameEngine
    */
   public void playerResult(Player player, DicePair result, GameEngine gameEngine);

   /**
    * 
    * <pre>called when HOUSE dice have stopped rolling with the final resting dice values
    * 
    * PRE-CONDITION: This method should only be called after bet results have been applied to all Players 
    * so this callback can log Player results. However, bets should not be reset yet so they are still visible.
    * </pre>
    * 
    * @param result - the state of the House's dice when landed and stopped
    * @param gameEngine - a convenience reference to the engine so the receiver can call methods if necessary
    * @see model.interfaces.GameEngine
    */
   public void houseResult(DicePair result, GameEngine gameEngine);
}
