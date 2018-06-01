package com.battleship.exception;

@SuppressWarnings("serial")
public class InvalidShipPositionException extends Exception{

	public InvalidShipPositionException(String message){
		super(message);
	}
	
}