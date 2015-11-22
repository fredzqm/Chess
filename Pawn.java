import javax.swing.JOptionPane;

/**
 * Pawn in the chess
 * 
 * @author zhangq2
 *
 */
public class Pawn extends Piece {
	/**
	 * constructs a Pawn with initial square
	 * 
	 * @param type
	 * @param wb
	 * @param p
	 */
	public Pawn(char type, boolean wb, Square p) {
		super(type, wb, p);
		name = "Pawn";
		value = 1;
	}

	@Override
	public boolean legalPosition(Square end) {

		if (end.occupied() || spot == null)
			return false;
		if (spot.X() == end.X()) {
			if (wb) {
				if (end.Y() - spot.Y() == 1)
					return true;
				if (end.Y() == 4 && spot.Y() == 2)
					if (!chess.spotAt(spot.X(), 3).occupied())
						return true;
			} else {

				if (end.Y() - spot.Y() == -1)
					return true;
				if (end.Y() == 5 && spot.Y() == 7)
					if (!chess.spotAt(spot.X(), 6).occupied())
						return true;
			}
		}
		return false;
	}

	public boolean canAttack(Square end, Chess chess) {

		if (spot == null)
			return false;

		if (Math.abs(end.X() - spot.X()) == 1) {
			if (wb) {
				if (end.Y() - spot.Y() == 1)
					return true;
			} else {
				if (end.Y() - spot.Y() == -1)
					return true;
			}
		}
		return false;
	}

	public boolean canCapture(Square end, Chess chess) {
		if (!canAttack(end, chess))
			return false;

		if (end.occupiedBy(!wb))
			return canAttack(end, chess) && end.occupiedBy(!wb)
					&& !chess.giveAwayKing(this, spot, end.getPiece(), end, wb);
		else
			return chess.canEnPassant(end);
	}

	public String capture(Square end, Piece taken, Chess chess) {
		String s = "";
		if (taken == null) {
			s = "En Passant! ";
			taken = chess.spotAt(end.X(), spot.Y()).getPiece();
			if (taken == null)
				System.out.println("En Passant error!");
		}
		return s + makeMove(end, taken);
	}

	protected boolean canPromote(Square end) {
		boolean promotion = false;
		if (wb) {
			if (end.Y() == 8)
				promotion = true;
		} else {
			if (end.Y() == 1)
				promotion = true;
		}
		return promotion;
	}

	public Piece promotion(Square end, Chess chess) {
		chess.printInBox("Please choose one kind of piece to promote to -- Q, N, R, B");
		String s = JOptionPane.showInputDialog("Promotion to !?");
		if (!s.isEmpty()) {
			s = s.toUpperCase();
			char a = s.charAt(0);
			if (a == 'Q')
				return new Queen('Q', wb, end);
			else if (a == 'R')
				return new Rook('R', wb, end);
			else if (a == 'B')
				return new Bishop('B', wb, end);
			else if (a == 'N')
				return new Knight('N', wb, end);
		}
		return promotion(end, chess);
	}

}