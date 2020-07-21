package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player{
	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private DicePair result;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String getPlayerId() {
		return this.playerId;
	}

	@Override
	public boolean setBet(int bet) {
		if (bet == 0) {								// according to the spec: must use resetBet() for 0 bet since not valid for this method 
			this.resetBet();
			return true;
		} else {
			if (bet>0 && bet<=this.points) {
				this.bet = bet;
				return true;
			} else
				return false;
		}
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
	}

	@Override
	public DicePair getResult() {
		return result;
	}

	@Override
	public void setResult(DicePair rollResult) {
		this.result = rollResult;
	}
	
	@Override
	public String toString() {
		return String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT .. %s", 
				this.playerId, this.playerName, this.bet, this.points, this.result.toString());
	}

}
