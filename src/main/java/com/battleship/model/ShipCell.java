package com.battleship.model;

public class ShipCell {

	private String cellPosition;

	private Integer cellHealth;

	public String getCellPosition() {
		return cellPosition;
	}

	public void setCellPosition(String cellPosition) {
		this.cellPosition = cellPosition;
	}

	public Integer getCellHealth() {
		return cellHealth;
	}

	public void setCellHealth(Integer cellHealth) {
		this.cellHealth = cellHealth;
	}
	
}