package com.battleship.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.battleship.exception.InvalidBattleFieldInfoException;
import com.battleship.exception.InvalidMissileInfoException;
import com.battleship.exception.InvalidShipPositionException;
import com.battleship.model.BattleField;
import com.battleship.model.Player;
import com.battleship.model.Ship;
import com.battleship.model.ShipCell;
import com.battleship.validation.BattleShipValidation;

public class BattleShipService {
	
	Logger logger = Logger.getLogger(BattleShipService.class);

	//fetches battleField info
	public BattleField fetchBattleFieldInfo(List<String> lines) throws InvalidBattleFieldInfoException{
		Integer widthOfBattleField = Integer.parseInt(lines.get(0).split(" ")[0]);
		Character heightOfBattleField = lines.get(0).split(" ")[1].charAt(0);
		Integer noOfShips = Integer.parseInt(lines.get(1));

		BattleField battleField = new BattleField();
		battleField.setBattleFieldHeight(heightOfBattleField);
		battleField.setBattleFieldWidth(widthOfBattleField);
		battleField.setNoOfShips(noOfShips);

		if(!BattleShipValidation.validateBattleFieldInfo(battleField))
			throw new InvalidBattleFieldInfoException("Battle Field parameters are incorrect.");
		return battleField;
	}
	
	//assigns file data to both the player objects
	public List<Player> assignPlayerObjects(List<String> lines, BattleField battleField) throws InvalidMissileInfoException, InvalidShipPositionException{

		List<Player> playerList = new ArrayList<Player>();

		Player player1 = new Player();
		player1.setPlayerName("Player-1");

		Player player2 = new Player();
		player2.setPlayerName("Player-2");

		//assign missiles to players
		player1 = assignMissilesToPlayer(player1, lines.get(battleField.getNoOfShips()+2), battleField);
		player2 = assignMissilesToPlayer(player2, lines.get(battleField.getNoOfShips()+3), battleField);

		List<Ship> player1ShipList = new ArrayList<Ship>();
		List<Ship> player2ShipList = new ArrayList<Ship>();
		for(int i=0;i<battleField.getNoOfShips();i++){
			String[] shipInfo = lines.get(i+2).split(" ");
			Ship shipOfPlayer1 = new Ship();
			shipOfPlayer1.setShipType(shipInfo[0].charAt(0));
			shipOfPlayer1.setShipWidth(Integer.parseInt(shipInfo[1]));
			shipOfPlayer1.setShipHeight(Integer.parseInt(shipInfo[2]));

			Ship shipOfPlayer2 = new Ship();
			shipOfPlayer2.setShipType(shipInfo[0].charAt(0));
			shipOfPlayer2.setShipWidth(Integer.parseInt(shipInfo[1]));
			shipOfPlayer2.setShipHeight(Integer.parseInt(shipInfo[2]));

			String positionOfShipForPlayer1 = shipInfo[3];
			String positionOfShipForPlayer2 = shipInfo[4];
			if(BattleShipValidation.validateShipPositions(lines, positionOfShipForPlayer1, positionOfShipForPlayer2)){
				shipOfPlayer1 = assignShipCells(shipOfPlayer1, positionOfShipForPlayer1);
				shipOfPlayer2 = assignShipCells(shipOfPlayer2, positionOfShipForPlayer2);
				player1ShipList.add(shipOfPlayer1);
				player2ShipList.add(shipOfPlayer2);
			}
			else
				throw new InvalidShipPositionException("Ship position not in range of battleField");
		}
		
		player1.setShips(player1ShipList);
		player2.setShips(player2ShipList);

		playerList.add(player1);
		playerList.add(player2);

		return playerList;
	}
	
	//assigns shipCell objects to Ship Objects
	private static Ship assignShipCells(Ship ship, String positionOfShip){
		List<ShipCell> shipCellList = new ArrayList<ShipCell>();
		
		Integer xCoordinateShipCell = Character.getNumericValue(positionOfShip.charAt(1));
		Character yCoordinateShipCell = positionOfShip.charAt(0);

		for(char i=yCoordinateShipCell ; i<yCoordinateShipCell + ship.getShipHeight();i++){
			for(int j=xCoordinateShipCell ; j<xCoordinateShipCell + ship.getShipWidth();j++){
				ShipCell shipCell = new ShipCell();
				if(ship.getShipType().equals('P'))
					shipCell.setCellHealth(1);
				else if(ship.getShipType().equals('Q'))
					shipCell.setCellHealth(2);
				shipCell.setCellPosition(Character.toString(i)+j);
				shipCellList.add(shipCell);
			}
		}

		ship.setShipCells(shipCellList);
		return ship;
	}

	//assigns missile positions to player
	private static Player assignMissilesToPlayer(Player player, String listOfMissiles, BattleField battleField) throws InvalidMissileInfoException{
		String[] missileArray = listOfMissiles.split(" ");

		if(BattleShipValidation.validateMissileInfo(battleField, missileArray)){
			player.setMissileList(missileArray);
			return player;
		}
		else{
			throw new InvalidMissileInfoException("Missile Info provided is not in range of battle Field.");
		}
	}
	
	//start the game 
	public String attackShips(List<Player> playerList){
		String result = "Peace declared among the players";
		
		int counterMissileA = 0;
		int counterMissileB = 0;
		
		//To start the game with Player-1
		Boolean aAttackFlag = true;
		Boolean bAttackFlag = false;
		
		Player player1 = playerList.get(0);
		Player player2 = playerList.get(1);

		String[] missilesOfPlayerA = player1.getMissileList();
		String[] missilesOfPlayerB = player2.getMissileList();
		
		while(counterMissileA != missilesOfPlayerA.length || counterMissileB !=missilesOfPlayerB.length){
			// Player-1 gets the attacking turn.
			if(aAttackFlag && counterMissileA != missilesOfPlayerA.length){
				aAttackFlag = modifyHealth(player1.getPlayerName(), player2, missilesOfPlayerA[counterMissileA]);
				if(player2.getShips().isEmpty()){
					result="Player-1 won the game";
					break;
				}
				bAttackFlag = !aAttackFlag;
				counterMissileA++;
			}
			else if(bAttackFlag && counterMissileB != missilesOfPlayerB.length){
				bAttackFlag = modifyHealth(player2.getPlayerName(), player1, missilesOfPlayerB[counterMissileB]);
				if(player1.getShips().isEmpty()){
					result="Player-2 won the game";
					break;
				}
				aAttackFlag = !bAttackFlag;
				counterMissileB++;
			}
			else{
				if(counterMissileA == missilesOfPlayerA.length){
					//If Player-1 has no more missiles to launch
					logger.info(player1.getPlayerName()+" has no more missiles left to launch.");
					bAttackFlag = true;
					aAttackFlag = false;
				}
				else if(counterMissileB == missilesOfPlayerB.length){
					//If Player-2 has no more missiles to launch
					logger.info(player2.getPlayerName()+ " has no more missiles left to launch.");
					aAttackFlag = true;
					bAttackFlag = false;
				}
			}
		}
		return result;
	}
	
	//modify health of the ship cell based on the attack position
	public Boolean modifyHealth(String playerName, Player player, String missilePosition){
		Boolean attackFlag = false; 
		Ship shipToCheck = null;
		for(Ship ship : player.getShips()){
			for(ShipCell cell : ship.getShipCells()){
				if(cell.getCellPosition().equals(missilePosition)){
					attackFlag = true;
					cell.setCellHealth(cell.getCellHealth()-1);
					logger.info(playerName+" fires a missile with target "+missilePosition+" which got hit");
					if(cell.getCellHealth() == 0)
						ship.getShipCells().remove(cell);
					shipToCheck = ship;
					break;
				}
			}
			if(attackFlag)
				break;
		}
		if(!attackFlag)
			logger.info(playerName+" fires a missile with target "+missilePosition+" which got miss");
		else{
			if(shipToCheck.getShipCells().isEmpty()){
				player.getShips().remove(shipToCheck);
			}
		}
		return attackFlag;
	}
}