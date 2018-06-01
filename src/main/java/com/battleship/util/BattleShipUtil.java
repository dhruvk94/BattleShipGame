package com.battleship.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class BattleShipUtil {

	public List<String> readInputFromFile() throws IOException{
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/config.properties"));
		String filePath = properties.getProperty("INPUTFILE.PATH")+properties.getProperty("CHARS.FORWARD_SLASH")+properties.getProperty("INPUTFILE.NAME");

		if(filePath.startsWith("/"))
			filePath = filePath.substring(1);
		
		return Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
	}

}
