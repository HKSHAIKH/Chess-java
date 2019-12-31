import java.util.ArrayList;
import java.util.List;

/**
 * This class tracks everything about any player  
 */

public class Player {
	
	private int player;
	private Frame frame;
	
	private Piece[] pieces;
	
	private List<Position> totalAvailableMoves;
	private List<Position> totalAvailableAttacks;
	private List<Position> totalAvailableDefence;
	
	
	/**
	 * constructor of player
	 * @param player player number of chess player
	 * @param frame back end frame of chess board
	 */
	Player(int player,Frame frame)
	{
		this.player = player;
		this.frame = frame;
		this.initializepieces();
		this.calculateTotalAvailableMovesandAttacks();
	}
	
	
	/**
	 * Method to initialize all pieces before beginning of the game
	 */
	void initializepieces()
	{
		pieces = new Piece[16];
		
		
		if(player == 1) {
			pieces[0] = new Rook(1,new Position('A',1),frame);
			pieces[1] = new Knight(1,new Position('B',1),frame);
			pieces[2] = new Bishop(1,new Position('C',1),frame);
			pieces[3] = new Queen(1,new Position('D',1),frame);
			pieces[4] = new King(1,new Position('E',1),frame);
			pieces[5] = new Bishop(1,new Position('F',1),frame);
			pieces[6] = new Knight(1,new Position('G',1),frame);
			pieces[7] = new Rook(1,new Position('H',1),frame);
			
			for(int i=0;i<8;i++) {
				pieces[i+8] = new Pawn(1,new Position((char)(65+i),2),frame);
			}
		}
		else {
			pieces[0] = new Rook(2,new Position('A',8),frame);
			pieces[1] = new Knight(2,new Position('B',8),frame);
			pieces[2] = new Bishop(2,new Position('C',8),frame);
			pieces[3] = new Queen(2,new Position('D',8),frame);
			pieces[4] = new King(2,new Position('E',8),frame);
			pieces[5] = new Bishop(2,new Position('F',8),frame);
			pieces[6] = new Knight(2,new Position('G',8),frame);
			pieces[7] = new Rook(2,new Position('H',8),frame);
			
			for(int i=0;i<8;i++) {
				pieces[i+8] = new Pawn(2,new Position((char)(65+i),7),frame);
			}
		}
		
	}
	
	/**
	 * @return the king
	 */
	public Piece getKing() {
		return pieces[4];
	}

	public List<Position> getTotalAvailableMoves() {
		return totalAvailableMoves;
	}


	public void setTotalAvailableMoves(List<Position> totalAvailableMoves) {
		this.totalAvailableMoves = totalAvailableMoves;
	}


	public List<Position> getTotalAvailableAttacks() {
		return totalAvailableAttacks;
	}


	public void setTotalAvailableAttacks(List<Position> totalAvailableAttacks) {
		this.totalAvailableAttacks = totalAvailableAttacks;
	}


	/**
	 * @return the totalAvailableDefence
	 */
	public List<Position> getTotalAvailableDefence() {
		return totalAvailableDefence;
	}


	/**
	 * @param totalAvailableDefence the totalAvailableDefence to set
	 */
	public void setTotalAvailableDefence(List<Position> totalAvailableDefence) {
		this.totalAvailableDefence = totalAvailableDefence;
	}


	/**
	 * to calculate available moves and attacks for all piece.
	 */
	public void calculateTotalAvailableMovesandAttacks() {
		this.totalAvailableAttacks = new ArrayList<>();
		this.totalAvailableMoves = new ArrayList<>();
		this.totalAvailableDefence = new ArrayList<>();
		
		
		for(int i=0;i<8;i++) {
			pieces[i].calculateAvailableMovesandAttacks();
			
			totalAvailableAttacks.addAll(pieces[i].getAvailableAttacks());
			totalAvailableMoves.addAll(pieces[i].getAvailableMoves());	
			totalAvailableDefence.addAll(pieces[i].getAvailableDefence());
			
		}
		
		
		for(int i=8;i<16;i++) {
			pieces[i].calculateAvailableMovesandAttacks();
			
			totalAvailableAttacks.addAll(pieces[i].getAvailableAttacks());
			totalAvailableMoves.addAll(pieces[i].getAvailableDefence());
			totalAvailableDefence.addAll(pieces[i].getAvailableDefence());
		}
		
		pieces[4].calculateAvailableMovesandAttacks();
		
		totalAvailableAttacks.addAll(pieces[4].getAvailableAttacks());
		totalAvailableMoves.addAll(pieces[4].getAvailableMoves());
		totalAvailableMoves.addAll(pieces[4].getAvailableDefence());
		totalAvailableDefence.addAll(pieces[4].getAvailableDefence());
	}
	
	
}
