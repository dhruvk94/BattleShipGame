package com.battleship.client;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.battleship.exception.InvalidBattleFieldInfoException;
import com.battleship.exception.InvalidMissileInfoException;
import com.battleship.exception.InvalidShipPositionException;
import com.battleship.model.BattleField;
import com.battleship.model.Player;
import com.battleship.service.BattleShipService;
import com.battleship.util.BattleShipUtil;

public class BattleShipClient {
	
	private static final Logger logger = Logger.getLogger(BattleShipClient.class);
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		
		BattleShipUtil battleShipUtil = new BattleShipUtil();
		BattleShipService battleShipService = new BattleShipService();

		try {
			List<String> lines = battleShipUtil.readInputFromFile();
			BattleField battleField = battleShipService.fetchBattleFieldInfo(lines);
			List<Player> players = battleShipService.assignPlayerObjects(lines, battleField);
			String result = battleShipService.attackShips(players);
			logger.info(result);
		} catch (IOException e) {
			logger.error("Unable to read file");
		} catch (InvalidMissileInfoException e) {
			logger.error(e.getMessage());
		} catch (InvalidShipPositionException e) {
			logger.error(e.getMessage());
		} catch (InvalidBattleFieldInfoException e) {
			logger.error(e.getMessage());
		}
		
	}
}
