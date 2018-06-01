package com.battleship.model;

import java.util.List;

public class Ship {
	
	private Character shipType;
	
	private Integer shipWidth;
	
	private Integer shipHeight;
	
	private List<ShipCell> shipCells;

	public Character getShipType() {
		return shipType;
	}

	public void setShipType(Character shipType) {
		this.shipType = shipType;
	}

	public Integer getShipWidth() {
		return shipWidth;
	}

	public void setShipWidth(Integer shipWidth) {
		this.shipWidth = shipWidth;
	}

	public Integer getShipHeight() {
		return shipHeight;
	}

	public void setShipHeight(Integer shipHeight) {
		this.shipHeight = shipHeight;
	}

	public List<ShipCell> getShipCells() {
		return shipCells;
	}

	public void setShipCells(List<ShipCell> shipCells) {
		this.shipCells = shipCells;
	}
	
}