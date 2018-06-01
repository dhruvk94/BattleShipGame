package com.battleship.validation;

import java.util.List;

import com.battleship.model.BattleField;

public class BattleShipValidation {

	private BattleShipValidation(){
		
	}
	
	public static Boolean validateMissileInfo(BattleField battleField, String[] missileArray){

		for(String missile: missileArray){
			Character yCoordinate = missile.charAt(0);
			Integer xCoordinate = Character.getNumericValue(missile.charAt(1));
			if(xCoordinate > 0 && xCoordinate <= battleField.getBattleFieldWidth() && yCoordinate>='A' && yCoordinate<=battleField.getBattleFieldHeight())
				continue;
			else
				return false;		
		}
		return true;
	}
	
	public static Boolean validateShipPositions(List<String> lines,String positionOfShipForPlayer1,String positionOfShipForPlayer2){
		Integer widthOfBattleField = Integer.parseInt(lines.get(0).split(" ")[0]);
		Character heightOfBattleField = lines.get(0).split(" ")[1].charAt(0);

		Integer xCoordinatePlayer1 = Character.getNumericValue(positionOfShipForPlayer1.charAt(1));
		Character yCoordinatePlayer1 = positionOfShipForPlayer1.charAt(0);
	
		Integer xCoordinatePlayer2 = Character.getNumericValue(positionOfShipForPlayer2.charAt(1));
		Character yCoordinatePlayer2 = positionOfShipForPlayer2.charAt(0);
			
		if(xCoordinatePlayer1 > 0 && xCoordinatePlayer1 <= widthOfBattleField && yCoordinatePlayer1>='A' && yCoordinatePlayer1<=heightOfBattleField && 
		   xCoordinatePlayer2 > 0 && xCoordinatePlayer2 <= widthOfBattleField && yCoordinatePlayer2>='A' && yCoordinatePlayer2<=heightOfBattleField)
		   return true;
		return false;
	}
	
	public static Boolean validateBattleFieldInfo(BattleField battleField){
		Boolean validateHeight = false;
		Boolean validateWidth = false;
		Boolean validateNoOfShips = false;
		if(battleField.getBattleFieldHeight()>='A' && battleField.getBattleFieldHeight()<='Z')
			validateHeight = true;
		if(battleField.getBattleFieldWidth()>=1 && battleField.getBattleFieldWidth()<=9)
			validateWidth = true;
		if(battleField.getNoOfShips()>=1 && battleField.getNoOfShips()<=(battleField.getBattleFieldHeight()-'A'+1)*battleField.getBattleFieldWidth())
			validateNoOfShips = true;
		if(validateHeight && validateWidth && validateNoOfShips)
			return true;
		return false;
	}
}