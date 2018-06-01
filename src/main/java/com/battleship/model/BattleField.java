package com.battleship.model;

public class BattleField {

	private Integer battleFieldWidth;
	
	private Character battleFieldHeight;
	
	private Integer noOfShips;
	
	public Integer getBattleFieldWidth() {
		return battleFieldWidth;
	}

	public void setBattleFieldWidth(Integer battleFieldWidth) {
		this.battleFieldWidth = battleFieldWidth;
	}

	public Character getBattleFieldHeight() {
		return battleFieldHeight;
	}

	public void setBattleFieldHeight(Character battleFieldHeight) {
		this.battleFieldHeight = battleFieldHeight;
	}

	

	public Integer getNoOfShips() {
		return noOfShips;
	}

	public void setNoOfShips(Integer noOfShips) {
		this.noOfShips = noOfShips;
	}
}