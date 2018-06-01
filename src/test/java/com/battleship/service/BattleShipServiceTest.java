package com.battleship.service;

import java.util.ArrayList;
import java.util.List;

import com.battleship.exception.InvalidBattleFieldInfoException;
import com.battleship.exception.InvalidMissileInfoException;
import com.battleship.exception.InvalidShipPositionException;
import com.battleship.model.BattleField;
import com.battleship.model.Player;
import com.battleship.model.Ship;
import com.battleship.model.ShipCell;

import org.junit.Test;
import org.junit.Assert;

public class BattleShipServiceTest {

	BattleShipService battleShipService = new BattleShipService();
	
	@Test
	public void fetchBattleFieldInfoValidTest() throws InvalidBattleFieldInfoException{
		List<String> lines = new ArrayList<String>();
		lines.add("5 E");
		lines.add("25");
		BattleField battleField = new BattleField();
		battleField.setBattleFieldHeight('E');
		battleField.setBattleFieldWidth(5);
		battleField.setNoOfShips(1);
		Assert.assertNotNull(battleShipService.fetchBattleFieldInfo(lines));
	}
	
	@Test(expected = InvalidBattleFieldInfoException.class)
	public void fetchBattleFieldInfoInvalidHeightTest() throws InvalidBattleFieldInfoException{
		List<String> lines = new ArrayList<String>();
		lines.add("5 a");
		lines.add("1");
		battleShipService.fetchBattleFieldInfo(lines);
	}
	
	@Test(expected = InvalidBattleFieldInfoException.class)
	public void fetchBattleFieldInfoInvalidWidthTest() throws InvalidBattleFieldInfoException{
		List<String> lines = new ArrayList<String>();
		lines.add("10 E");
		lines.add("1");
		battleShipService.fetchBattleFieldInfo(lines);
	}
	
	@Test(expected = InvalidBattleFieldInfoException.class)
	public void fetchBattleFieldInfoInvalidNoOfShipsTest() throws InvalidBattleFieldInfoException{
		List<String> lines = new ArrayList<String>();
		lines.add("5 E");
		lines.add("26");
		battleShipService.fetchBattleFieldInfo(lines);
	}

	@Test(expected = InvalidShipPositionException.class)
	public void assignPlayerObjectsInvalidShipPositionTest() throws InvalidMissileInfoException, InvalidShipPositionException{
		BattleField battleField = new BattleField();
		battleField.setBattleFieldHeight('E');
		battleField.setBattleFieldWidth(5);
		battleField.setNoOfShips(1);

		List<String> lines = new ArrayList<String>();
		lines.add("5 E");
		lines.add("1");
		lines.add("P 2 1 A1 E6");
		lines.add("A1 A2 A3");
		lines.add("B1 B2 B3");
		
		battleShipService.assignPlayerObjects(lines, battleField);
	}

	@Test(expected = InvalidMissileInfoException.class)
	public void assignPlayerObjectsInvalidMissileTest() throws InvalidMissileInfoException, InvalidShipPositionException{
		BattleField battleField = new BattleField();
		battleField.setBattleFieldHeight('E');
		battleField.setBattleFieldWidth(5);
		battleField.setNoOfShips(1);

		List<String> lines = new ArrayList<String>();
		lines.add("5 E");
		lines.add("1");
		lines.add("P 2 1 A1 E5");
		lines.add("F2 A2 A3");
		lines.add("B1 B2 B3");
		
		battleShipService.assignPlayerObjects(lines, battleField);
	}
	
	@Test
	public void assignPlayerObjectsValidTest() throws InvalidMissileInfoException, InvalidShipPositionException{
		BattleField battleField = new BattleField();
		battleField.setBattleFieldHeight('E');
		battleField.setBattleFieldWidth(5);
		battleField.setNoOfShips(1);

		List<String> lines = new ArrayList<String>();
		lines.add("5 E");
		lines.add("1");
		lines.add("P 2 1 A1 E5");
		lines.add("A1 A2 A3");
		lines.add("B1 B2 B3");
		
		Assert.assertNotNull(battleShipService.assignPlayerObjects(lines, battleField));
	}

	@Test
	public void modifyHealthValidAttack(){
		List<ShipCell> shipCellList = new ArrayList<ShipCell>();
		ShipCell shipCell1 = new ShipCell();
		shipCell1.setCellPosition("A1");
		shipCell1.setCellHealth(1);
		shipCellList.add(shipCell1);

		List<Ship> shipList = new ArrayList<Ship>();
		Ship ship = new Ship();
		ship.setShipHeight(1);
		ship.setShipWidth(2);
		ship.setShipType('P');
		ship.setShipCells(shipCellList);
		shipList.add(ship);
		
		String missiles = "A1";
		String[] missileList = missiles.split(" ");
		Player player = new Player();
		player.setShips(shipList);
		player.setMissileList(missileList);
		player.setShips(shipList);

		Assert.assertEquals(new Boolean(true), battleShipService.modifyHealth("Player-1", player, missiles));
	}

	@Test
	public void modifyHealthInvalidAttack(){
		List<ShipCell> shipCellList = new ArrayList<ShipCell>();
		ShipCell shipCell1 = new ShipCell();
		shipCell1.setCellPosition("A1");
		shipCell1.setCellHealth(1);
		shipCellList.add(shipCell1);

		List<Ship> shipList = new ArrayList<Ship>();
		Ship ship = new Ship();
		ship.setShipHeight(1);
		ship.setShipWidth(2);
		ship.setShipType('P');
		ship.setShipCells(shipCellList);
		shipList.add(ship);
		
		String missiles = "B1";
		String[] missileList = missiles.split(" ");
		Player player = new Player();
		player.setShips(shipList);
		player.setMissileList(missileList);
		player.setShips(shipList);

		Assert.assertEquals(new Boolean(false), battleShipService.modifyHealth("Player-1", player, missiles));
	}

	@Test
	public void attackShipsPeaceTest(){
		List<ShipCell> shipCellList1 = new ArrayList<ShipCell>();
		ShipCell shipCell1 = new ShipCell();
		shipCell1.setCellPosition("A1");
		shipCell1.setCellHealth(1);
		shipCellList1.add(shipCell1);

		List<Ship> shipList1 = new ArrayList<Ship>();
		Ship ship1 = new Ship();
		ship1.setShipHeight(1);
		ship1.setShipWidth(2);
		ship1.setShipType('P');
		ship1.setShipCells(shipCellList1);
		shipList1.add(ship1);
		
		String missiles = "A1";
		String[] missileList = missiles.split(" ");
		Player player1 = new Player();
		player1.setShips(shipList1);
		player1.setMissileList(missileList);

		List<ShipCell> shipCellList2 = new ArrayList<ShipCell>();
		ShipCell shipCell2 = new ShipCell();
		shipCell2.setCellPosition("B1");
		shipCell2.setCellHealth(1);
		shipCellList2.add(shipCell2);

		List<Ship> shipList2 = new ArrayList<Ship>();
		Ship ship2 = new Ship();
		ship2.setShipHeight(1);
		ship2.setShipWidth(2);
		ship2.setShipType('P');
		ship2.setShipCells(shipCellList2);
		shipList2.add(ship2);
		
		String missiles2 = "A2";
		String[] missileList2 = missiles2.split(" ");
		Player player2 = new Player();
		player2.setShips(shipList2);
		player2.setMissileList(missileList2);
		player2.setShips(shipList2);

		List<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		
		Assert.assertEquals("Peace declared among the players", battleShipService.attackShips(playerList));
	}
	
	@Test
	public void attackShipsPlayer1VictoryTest(){
		List<ShipCell> shipCellList1 = new ArrayList<ShipCell>();
		ShipCell shipCell1 = new ShipCell();
		shipCell1.setCellPosition("A2");
		shipCell1.setCellHealth(1);
		shipCellList1.add(shipCell1);

		List<Ship> shipList1 = new ArrayList<Ship>();
		Ship ship1 = new Ship();
		ship1.setShipHeight(1);
		ship1.setShipWidth(1);
		ship1.setShipType('P');
		ship1.setShipCells(shipCellList1);
		shipList1.add(ship1);
		
		String missiles = "A1";
		String[] missileList = missiles.split(" ");
		Player player1 = new Player();
		player1.setShips(shipList1);
		player1.setMissileList(missileList);

		List<ShipCell> shipCellList2 = new ArrayList<ShipCell>();
		ShipCell shipCell2 = new ShipCell();
		shipCell2.setCellPosition("A1");
		shipCell2.setCellHealth(1);
		shipCellList2.add(shipCell2);

		List<Ship> shipList2 = new ArrayList<Ship>();
		Ship ship2 = new Ship();
		ship2.setShipHeight(1);
		ship2.setShipWidth(2);
		ship2.setShipType('P');
		ship2.setShipCells(shipCellList2);
		shipList2.add(ship2);
		
		String missiles2 = "B1";
		String[] missileList2 = missiles2.split(" ");
		Player player2 = new Player();
		player2.setShips(shipList2);
		player2.setMissileList(missileList2);
		player2.setShips(shipList2);

		List<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		
		Assert.assertEquals("Player-1 won the game", battleShipService.attackShips(playerList));
	}
}
