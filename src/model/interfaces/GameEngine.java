package model.interfaces;

import java.util.Collection;

import view.interfaces.GameEngineCallback;

/**
 * Assignment interface for Further Programming providing main Dice Game model functionality s1
 * 2020
 * 
 * @author Caspar Ryan

 */
public interface GameEngine
{
   /**
    * <pre>Roll the dice progressing from the initialDelays to the finalDelays
    * in increments specified by the delayIncrements
    * 
    * <b>NOTE:</b> All delays are in milliseconds (ms)
    * 
    * 1. dice are initialised at their random starting face
    * 2. for each die start at initialDelay then increment the delays for each die on each iteration
    * 3. call GameEngineCallback.playerDieUpdate(...) for each die separately
    * 4. continue until both delays {@literal >=} finalDelay 
    * 5. call GameEngineCallback.playerResult(...) to finish
    * 
    * <b>See Also:</b>
    *  {@link view.interfaces.GameEngineCallback#playerDieUpdate(Player, Die, GameEngine)}
    *  {@link view.interfaces.GameEngineCallback#playerResult(Player, DicePair, GameEngine)}
    * 
    * @param player 
    *             the Player to roll for
    * @param initialDelay1
    *            the starting delay in ms between updates for die 1
    *            (reflects the speed at which this die is rolling across the table)
    * @param finalDelay1
    *            the final delay in ms between updates when the die has come to rest
    * @param delayIncrement1
    *            how much die 1 slows down (i.e. delay gets longer) after each individual tumble
    *            i.e. each iteration until current delay {@literal >} finalDelay
    * @param initialDelay2 
    *            same as initialDelay1 but for die 2 
    * @param finalDelay2
    *            same as finalDelay1 but for die 2 
    * @param delayIncrement2 
    *            same as delayIncrement1 but for die 2 
    * </pre>    
    * @throws IllegalArgumentException thrown when: <UL>
    * <LI> any of the delay params are {@literal <} 0
    * <LI> either of the finalDelay {@literal <} initialDelay
    * <LI> either of the delayIncrement {@literal >} (finalDelay - initialDelay)
    * </UL>
    *     
    * <br>NOTE: for assignment 1 you can assume the values passed for the delays are the same for dice 1 and 2
    * and therefore optionally use only the first three delay parameters
    * since event handling and/or threads are suggested to implement different rolling speeds
    * which will be implemented in assignment 2 
    */
   public abstract void rollPlayer(Player player, int initialDelay1, int finalDelay1,
      int delayIncrement1, int initialDelay2, int finalDelay2, int delayIncrement2);

   /** <pre>Same as rollPlayer() other than the two notes below but rolls for the house and calls the
    * house versions of the callback methods on GameEngineCallback, no player parameter is required. 
    * 
    * IMPORTANT NOTE 1: At the end of the roll this method should iterate all players and 
    * call {@link GameEngine#applyWinLoss(Player, DicePair)} to update each player's points
    * 
    * IMPORTANT NOTE 2: After calling {@link GameEngineCallback#houseResult(DicePair, GameEngine)}  
    * this method should also call {@link Player#resetBet()} on each player in preparation for 
    * the next round
    * 
    * @param initialDelay1
    *            the starting delay in ms between updates for die 1
    *            (reflects the speed at which this die is rolling across the table)
    * @param finalDelay1
    *            the final delay in ms between updates when the die has come to rest
    * @param delayIncrement1
    *            how much die 1 slows down (i.e. delay gets longer) after each individual tumble
    *            i.e. each iteration until current delay {@literal >} finalDelay
    * @param initialDelay2 
    *            same as initialDelay1 but for die 2 
    * @param finalDelay2
    *            same as finalDelay1 but for die 2 
    * @param delayIncrement2 
    *            same as delayIncrement1 but for die 2 
    *               
    * </pre>    
    * @throws IllegalArgumentException thrown when: <UL>
    * <LI> if any of the delay params are {@literal <} 0
    * <LI> either of the finalDelay {@literal <} initialDelay
    * <LI> either of the delayIncrement {@literal >} (finalDelay - initialDelay)
    * </UL>
    * 
    * <br><b>See Also:</b>
    * {@link view.interfaces.GameEngineCallback#houseDieUpdate(Die, GameEngine)}
    * {@link view.interfaces.GameEngineCallback#houseResult(DicePair, GameEngine)}
    */
   public abstract void rollHouse(int initialDelay1, int finalDelay1, int delayIncrement1,
      int initialDelay2, int finalDelay2, int delayIncrement2);

   /**
    * <pre>
    * A player's bet is settled by this method 
    * i.e. win or loss is applied to update betting points 
    * based on a comparison of the player result and the provided houseResult
    * 
    * NOTE: This method is usually called from {@link GameEngine#rollHouse(int, int, int, int, int, int)} 
    * as described above but is included in the public interface to facilitate testing
    * 
    * @param player - the Player to apply win/loss to
    * @param houseResult - a DicePair containing the house result
    * </pre>
    */
   public abstract void applyWinLoss(Player player, DicePair houseResult);

   /**
    *  <b>NOTE:</b> playerID is unique and if another player with same id is added 
    *  it replaces the previous player
    *  
    * @param player - to add to game
    */
   public abstract void addPlayer(Player player);

   /**
    * @param id - id of player to retrieve (null if not found)
    * @return the Player or null if Player does not exist
    */
   public abstract Player getPlayer(String id);

   /**
    * @param player - to remove from game
    * @return true if the player existed and was removed
    */
   public abstract boolean removePlayer(Player player);

   /**
    * the implementation should forward the call to the player class so the bet is set per player
    * @see Player#setBet(int)
    * 
    * @param player
    *            the player who is placing the bet
    * @param bet
    *            the bet in points
    * @return true if the player had sufficient points and the bet was valid and placed
    */
   public abstract boolean placeBet(Player player, int bet);

   /**
    * @param gameEngineCallback
    * <pre> a client specific implementation of GameEngineCallback used to perform display updates etc.
    * Callbacks should be called in the order they were added
    * <b>NOTE:</b> you will use a different implementation of the GameEngineCallback 
    *       for the console (assignment 1) and GUI (assignment 2) versions
    * </pre>
    * @see view.interfaces.GameEngineCallback
    */
   public abstract void addGameEngineCallback(GameEngineCallback gameEngineCallback);

   /**
    * @param gameEngineCallback - instance to be removed if no longer needed
    * @return true if the gameEngineCallback existed
    * @see view.interfaces.GameEngineCallback
    */
   public abstract boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback);

   /**
    * @return an unmodifiable collection (or a shallow copy) of all Players
    * @see model.interfaces.Player
    */
   public abstract Collection<Player> getAllPlayers();
}