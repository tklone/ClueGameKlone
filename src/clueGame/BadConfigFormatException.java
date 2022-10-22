package clueGame;

import java.io.FileNotFoundException;

public class BadConfigFormatException extends RuntimeException{

	public BadConfigFormatException() {
		super("File error");
	}

	public BadConfigFormatException(String invalidCell, String message) {
		super("Invalid cell" + invalidCell + "contains error: " + message);
	}

}
