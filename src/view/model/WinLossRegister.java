package view.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.interfaces.Player;

public class WinLossRegister {
	private Map<String, Integer> playerPrevPoints;
	
	public WinLossRegister() {
		playerPrevPoints = new HashMap<String, Integer>();
	}
	
	public void addToMap(String id, int amount) {
		playerPrevPoints.put(id, amount);
	}

	public void removePlayer(String id) {
		playerPrevPoints.remove(id);
	}

	public int getWinLoss(String playerId, int currentPoints) {
		return currentPoints - playerPrevPoints.get(playerId);
	}
	
	public void removeALL() {
		playerPrevPoints = new HashMap<String, Integer>();
	}
	
	public void updateRegisterBeforeRoll(Collection<Player> players) {
		for (Player player: players) {
			addToMap(player.getPlayerId(), player.getPoints());
		}
	}
}
