package model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine{
	private Map<String, Player> playerMap = new HashMap<String, Player>();
	private Set<GameEngineCallback> callbackCollection = new LinkedHashSet<GameEngineCallback>();
	
	@Override
	public void rollPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2){	
		this.validateRollParam(initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2, delayIncrement2);				
		
		DicePair result = this.rollPair("player", player, initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2, delayIncrement2);
				
		for (GameEngineCallback gec : callbackCollection) {
			gec.playerResult(player, result, this);
		}
		player.setResult(result);
	}

	@Override
	public void rollHouse(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2){
		this.validateRollParam(initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2, delayIncrement2);
		
		DicePair result = this.rollPair(null, null, initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2, delayIncrement2);
		
		for (Map.Entry<String, Player> entry : playerMap.entrySet()) {
		    this.applyWinLoss(entry.getValue(), result);
		}
		
		for (GameEngineCallback gec : callbackCollection) {
			gec.houseResult(result, this);
		}
		
		for (Map.Entry<String, Player> entry : playerMap.entrySet()) {
		    entry.getValue().resetBet();
		}
	}

	@Override
	public void applyWinLoss(Player player, DicePair houseResult) {
		int temp = houseResult.compareTo(player.getResult());
		if (temp == -1) {
			player.setPoints(player.getPoints() + player.getBet());
		} else if (temp == 1) {
			player.setPoints(player.getPoints() - player.getBet());
		} // else: no change cos totals are equal 
	}

	@Override
	public void addPlayer(Player player) {
		playerMap.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		return playerMap.get(id);
	}

	@Override
	public boolean removePlayer(Player player) {
		return playerMap.remove(player.getPlayerId(), player);
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.setBet(bet);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.callbackCollection.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return this.callbackCollection.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(playerMap.values());
	}
	
	private DicePair rollPair(String type, Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) {
		// die 1
		RollDieThread die1Runnable = new RollDieThread(type, 1, player, initialDelay1, finalDelay1, delayIncrement1);
	
		// die 2
		RollDieThread die2Runnable = new RollDieThread(type, 2, player, initialDelay2, finalDelay2, delayIncrement2);
		
		Thread die1Thread = new Thread(die1Runnable);
		Thread die2Thread = new Thread(die2Runnable);
		die1Thread.start();
		die2Thread.start();
		try {
			die1Thread.join();
			die2Thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new DicePairImpl(die1Runnable.getDie(), die2Runnable.getDie());	// the resulting DicePair
	}
	
	private void validateRollParam(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) {
		if (initialDelay1<0 || initialDelay2<0 || finalDelay1<0 || finalDelay2<0 || delayIncrement1<0 || delayIncrement2<0 || initialDelay1>finalDelay1 
				|| initialDelay2>finalDelay2 || delayIncrement1>(finalDelay1 - initialDelay1) || delayIncrement2>(finalDelay2 - initialDelay2))
			throw new IllegalArgumentException();
	}

	private class RollDieThread implements Runnable{
		private Die die;
		private String type;
		private int noOfDie;
		private Player player;
		private int initialDelay;
		private int finalDelay;
		private int delayIncrement;
		
		public RollDieThread(String type, int noOfDie, Player player, int initialDelay, int finalDelay, int delayIncrement) {
			this.type = type;
			this.noOfDie = noOfDie;
			this.player = player;
			this.initialDelay = initialDelay;
			this.finalDelay = finalDelay;
			this.delayIncrement = delayIncrement;
		}
		
		@Override
		public void run() {
			int currentDelay = initialDelay;
			DicePair dicePair;
			Die tempDie = null;

			while(currentDelay<finalDelay) {
				dicePair = new DicePairImpl();
				try {
					Thread.sleep(currentDelay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(noOfDie == 1) {
					tempDie = dicePair.getDie1();
				} else {
					tempDie = dicePair.getDie2();
				}
				for (GameEngineCallback gec : callbackCollection) {
					if("player".equals(type)) {
						gec.playerDieUpdate(player, tempDie, GameEngineImpl.this);
					} else{
						gec.houseDieUpdate(tempDie, GameEngineImpl.this);
					}
				}
				currentDelay+=delayIncrement;
			}
			this.die = tempDie;
		}

		public Die getDie() {
			return die;
		}	
	}
}
