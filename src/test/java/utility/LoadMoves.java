package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Chess;
import model.InvalidMoveException;
import model.Move;

public class LoadMoves {
	/**
	 * Read a file and perform the moves recorded in the file.
	 * 
	 * @param filename
	 * @throws FileNotFoundException 
	 */
	public static boolean performRecordMoves(Chess chess, String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			if(line.startsWith("[")) continue;
			
			String[] parts = line.split("\\d+\\.| ");
			for(int i = 0; i < parts.length; i++) {
				if(parts[i].length() == 0) continue;
				
				Move move;
				try {
					move = chess.getMove(parts[i]);
					chess.makeMove(move);
				} catch (InvalidMoveException e) {
					scan.close();
					return false;
				}
			}
		}
		
		scan.close();
		return true;
	}
}
