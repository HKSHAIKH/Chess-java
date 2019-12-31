import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/**
 * This class tracks piece parameters and values.
 */
public abstract class Piece {
	
	private int player;
	private Position position ;
	private List<Position> availableMoves;
	private List<Position> availableAttacks;
	private List<Position> availableDefence;
	private Frame frame;
	
	
	/**
	 * Constructor with null position.
	 * @param player the player of piece
	 * @param frame frame of chess board
	 */
	Piece(int player,Frame frame){
		this.player = player;
		this.setPosition(null);
		this.availableMoves = new ArrayList<Position>();
		this.availableAttacks = new ArrayList<Position>();
		this.availableDefence = new ArrayList<Position>();
		this.setFrame(frame);
	}
	
	/**
	 * Parameterized constructor of piece at the chess board.
	 * @param player the player of piece
	 * @param position the position of piece
	 * @param frame frame of chess board
	 */
	Piece(int player,Position position,Frame frame){
		
		this.player = player;
		this.setPosition(position);
		this.availableMoves = new ArrayList<Position>();
		this.availableAttacks = new ArrayList<Position>();
		this.availableDefence = new ArrayList<Position>();
		this.setFrame(frame);
		
		this.frame.setPiece(position, this);
	}
	
	/*
	 * To calculate the available moves and attacks of the pieces
	 */
	public abstract void calculateAvailableMovesandAttacks();
	
	

	/**
	 * @return the availableMoves
	 */
	public List<Position> getAvailableMoves() {
		return availableMoves;
	}

	/**
	 * @param availableMoves the availableMoves to set
	 */
	public void setAvailableMoves(List<Position> availableMoves) {
		this.availableMoves = availableMoves;
	}

	/**
	 * @return the availableAttacks
	 */
	public List<Position> getAvailableAttacks() {
		return availableAttacks;
	}

	/**
	 * @param availableAttacks the availableAttacks to set
	 */
	public void setAvailableAttacks(List<Position> availableAttacks) {
		this.availableAttacks = availableAttacks;
	}

	/**
	 * @return the availableDefence
	 */
	public List<Position> getAvailableDefence() {
		return availableDefence;
	}

	/**
	 * @param availableDefence the availableDefence to set
	 */
	public void setAvailableDefence(List<Position> availableDefence) {
		this.availableDefence = availableDefence;
	}

	/**
	 * @return the player
	 */
	public int getPlayer() {
		return player;
	}

	
	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the frame
	 */
	public Frame getFrame() {
		return frame;
	}
	
	/**
	 * @param frame the frame to set
	 */
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	
	/**
	 * To move piece at finalPosition.
	 * @param finalPosition the position of piece after move.
	 * @return piece moved or not.
	 */
	public boolean move(Position finalPosition) {
		for(int i=0 ; i<availableMoves.size() ; i++) {
			if( availableMoves.get(i).isequal(finalPosition) )
			{
				this.setPosition(finalPosition);
				Toolkit.getDefaultToolkit().beep();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * To attack the piece at the desired positions
	 * @param finalPosition
	 * @return
	 */
	public boolean attack(Position finalPosition) {
		for(int i=0 ; i<availableAttacks.size() ; i++) {
			if( availableAttacks.get(i).isequal(finalPosition) )
			{
				this.setPosition(finalPosition);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * To remove piece. 
	 */
	public void remove()
	{
		this.setPosition(null);
		this.availableMoves = new ArrayList<>();
		this.availableAttacks = new ArrayList<>();
		this.availableDefence = new ArrayList<>();
	}
	
	
	
}
