import java.util.List;
import java.util.ArrayList;

/**
 * This class tracks knight parameters and values.
 */
public class Knight extends Piece {

	/**
	 * Default constructor of Knight
	 */
	public Knight(int player,Frame frame) {
		super(player,frame);
		}

	/**
	 * Parameterized constructor of knight
	 * @param frame the frame of chess board
	 * @param position initial position of knight
	 * @param player the player of chess
	 */
	public Knight(int player,Position position,Frame frame) {
		super(player,position,frame);
	}
	
	/**
	 * To calculate available moves and attacks of knight
	 */
	@Override
	public void calculateAvailableMovesandAttacks() {
		
		if(this.getPosition() == null) {
			this.setAvailableAttacks(new ArrayList<>());
			this.setAvailableMoves(new ArrayList<>());
			this.setAvailableDefence(new ArrayList<>());
			return;
		}
		
		Frame frame = super.getFrame();
		Position position = super.getPosition();
		Position finalPosition ;
		List<Position> availableMoves = new ArrayList<>();
		List<Position> availableAttacks = new ArrayList<>();
		List<Position> availableDefence = new ArrayList<>();
		
		//upper moves of knight
		if( position.getRank()+2 <= 8) {
			//upper-right move
			if( (int)position.getFile()+1 <= (int)'H' ) {
				finalPosition =  new Position( (char)( (int)position.getFile()+1 ) , position.getRank()+2 ) ;
				if(frame.isPositionEmpty(finalPosition))
					availableMoves.add(finalPosition);
				else if(super.getPlayer() != frame.getPiece(finalPosition).getPlayer())
					availableAttacks.add(finalPosition);
				else 
					availableDefence.add(finalPosition);
			}
			//upper-left move
			if( (int)position.getFile()-1 >= (int)'A' ) {
				finalPosition =  new Position( (char)( (int)position.getFile()-1 ) , position.getRank()+2 ) ;
				if(frame.isPositionEmpty(finalPosition))
					availableMoves.add(finalPosition);
				else if(super.getPlayer() != frame.getPiece(finalPosition).getPlayer())
					availableAttacks.add(finalPosition);
				else 
					availableDefence.add(finalPosition);
			}
		}
		
		//lower moves of knight
		if( position.getRank()-2 >= 1) {
			//lower-right move
			if( (int)position.getFile()+1 <= (int)'H' ) {
				finalPosition =  new Position( (char)( (int)position.getFile()+1 ) , position.getRank()-2 ) ;
				if(frame.isPositionEmpty(finalPosition))
					availableMoves.add(finalPosition);
				else if(super.getPlayer() != frame.getPiece(finalPosition).getPlayer())
					availableAttacks.add(finalPosition);
				else 
					availableDefence.add(finalPosition);
			}
			
			//lower-left move
			if( (int)position.getFile()-1 >= (int)'A' ) {
				finalPosition =  new Position( (char)( (int)position.getFile()-1 ) , position.getRank()-2 ) ;
				if(frame.isPositionEmpty(finalPosition))
					availableMoves.add(finalPosition);
				else if(super.getPlayer() != frame.getPiece(finalPosition).getPlayer())
					availableAttacks.add(finalPosition);
				else 
					availableDefence.add(finalPosition);
			}
		}
		
		//right move of knight
		if( (int)position.getFile()+2 <= (int)'H' ) {
			
			//upper-right move
			if( position.getRank()+1 <= 8 ) {
				finalPosition =  new Position( (char)( (int)position.getFile()+2 ) , position.getRank()+1 ) ;
				if(frame.isPositionEmpty(finalPosition))
					availableMoves.add( finalPosition);
				else if(super.getPlayer() != frame.getPiece(finalPosition).getPlayer())
					availableAttacks.add(finalPosition);
				else 
					availableDefence.add(finalPosition);
			}
			
			//lower-right move
			if( position.getRank()-1 >= 1 ) {
				finalPosition =  new Position( (char)( (int)position.getFile()+2 ) , position.getRank()-1 ) ;
				if(frame.isPositionEmpty(finalPosition))
					availableMoves.add( finalPosition );
				else if(super.getPlayer() != frame.getPiece(finalPosition).getPlayer())
					availableAttacks.add(finalPosition);
				else 
					availableDefence.add(finalPosition);
				
			}
		}
		
		//left move of knight
		if( (int)position.getFile()-2 >= (int)'A' ) {
			
			//upper-left move
			if( position.getRank()+1 <= 8 ) {
				finalPosition =  new Position( (char)( (int)position.getFile()-2 ) , position.getRank()+1 ) ;
				if(frame.isPositionEmpty(finalPosition))
					availableMoves.add(finalPosition);
				else if(super.getPlayer() != frame.getPiece(finalPosition).getPlayer())
					availableAttacks.add(finalPosition);
				else 
					availableDefence.add(finalPosition);
			}
			
			//lower-left move
			if( position.getRank()-1 >= 1 ) {
				finalPosition =  new Position( (char)( (int)position.getFile()-2 ) , position.getRank()-1 ) ;
				if(frame.isPositionEmpty(finalPosition))
					availableMoves.add(finalPosition);
				else if(super.getPlayer() != frame.getPiece(finalPosition).getPlayer())
					availableAttacks.add(finalPosition);
				else 
					availableDefence.add(finalPosition);
			}
		}
		
		super.setAvailableAttacks(availableAttacks);
		super.setAvailableMoves(availableMoves);
		super.setAvailableDefence(availableDefence);
	}
	
	@Override
	public String toString() {
		return "Knight"+Integer.toString(getPlayer());
	}

}
