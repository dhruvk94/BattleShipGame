package com.battleship.model;

import java.util.List;

public class Player {

	private String playerName;
	
	private String[] missileList;
	
	private List<Ship> ships;
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String[] getMissileList() {
		return missileList;
	}

	public void setMissileList(String[] missileList) {
		this.missileList = missileList;
	}
	
	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}
}