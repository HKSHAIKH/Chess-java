import java.util.ArrayList;
import java.util.List;

/**
 * This class tracks king parameters and values.
 */
public class King extends Piece {

	/**
	 * Default constructor of king 
	 */
	public King(int player,Frame frame) {
		super(player,frame);
	}

	/**
	 * Parameterized constructor
	 * @param frame frame of the chess board
	 * @param position initial position of King
	 * @param player the player of chess
	 */
	public King(int player,Position position,Frame frame) {
		super(player,position,frame);
	}
	
	
	/**
	 * To calculate available moves and attacks of king
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
		List<Position> availableMoves = new ArrayList<Position>();
		List<Position> availableAttacks = new ArrayList<>();
		List<Position> availableDefence = new ArrayList<>();
		
		//upper moves
		if(position.getRank()+1 <= 8) {
			finalPosition = new Position( position.getFile() , position.getRank()+1);
			if(frame.isPositionEmpty(finalPosition) && frame.isMoveAvailableForKing(getPlayer(), finalPosition))
				availableMoves.add(finalPosition);
			else if(frame.getPiece(finalPosition) != null && super.getPlayer() != frame.pieceOwner(finalPosition)){
				if(frame.isAttackAvailableForKing(getPlayer(), finalPosition))
					availableAttacks.add(finalPosition);
			}
			else {
				availableDefence.add(finalPosition);
			}
			
			//upper-right move
			if( (int)position.getFile()+1 <= (int)'H') {
				finalPosition = new Position( (char)( (int)position.getFile()+1 ), position.getRank()+1);
				if(frame.isPositionEmpty(finalPosition) && frame.isMoveAvailableForKing(getPlayer(), finalPosition))
					availableMoves.add(finalPosition);
				else if(frame.getPiece(finalPosition) != null && super.getPlayer() != frame.pieceOwner(finalPosition)) {
					if(frame.isAttackAvailableForKing(getPlayer(), finalPosition))
						availableAttacks.add(finalPosition);
				}
				else {
					availableDefence.add(finalPosition);
				}
			}
			
			//upper-left move
			if( (int)position.getFile()-1 >= (int)'A') {
				finalPosition = new Position( (char)( (int)position.getFile()-1 ), position.getRank()+1);
				if(frame.isPositionEmpty(finalPosition) && frame.isMoveAvailableForKing(getPlayer(), finalPosition))
					availableMoves.add(finalPosition);
				else if(frame.getPiece(finalPosition) != null && super.getPlayer() != frame.pieceOwner(finalPosition)){
					if(frame.isAttackAvailableForKing(getPlayer(), finalPosition))
						availableAttacks.add(finalPosition);
				}
				else {
					availableDefence.add(finalPosition);
				}
			}
		}
		
		//lower moves
		if(position.getRank()-1 >= 1) {
			finalPosition = new Position( position.getFile() , position.getRank()-1);
			if(frame.isPositionEmpty(finalPosition) && frame.isMoveAvailableForKing(getPlayer(), finalPosition))
				availableMoves.add(finalPosition);
			else if(frame.getPiece(finalPosition) != null && super.getPlayer() != frame.pieceOwner(finalPosition)) {
				if(frame.isAttackAvailableForKing(getPlayer(), finalPosition))
					availableAttacks.add(finalPosition);
			}
			else {
				availableDefence.add(finalPosition);
			}
			
			//lower-right move
			if( (int)position.getFile()+1 <= (int)'H') {
				finalPosition = new Position( (char)( (int)position.getFile()+1 ), position.getRank()-1);
				if(frame.isPositionEmpty(finalPosition) && frame.isMoveAvailableForKing(getPlayer(), finalPosition))
					availableMoves.add(finalPosition);
				else if(frame.getPiece(finalPosition) != null && super.getPlayer() != frame.pieceOwner(finalPosition)){
					if(frame.isAttackAvailableForKing(getPlayer(), finalPosition))
						availableAttacks.add(finalPosition);
				}
				else {
					availableDefence.add(finalPosition);
				}
			}
			
			//lower-left move
			if( (int)position.getFile()-1 >= (int)'A') {
				finalPosition = new Position( (char)( (int)position.getFile()-1 ), position.getRank()-1);
				if(frame.isPositionEmpty(finalPosition) && frame.isMoveAvailableForKing(getPlayer(), finalPosition))
					availableMoves.add(finalPosition);
				else if(frame.getPiece(finalPosition) != null && super.getPlayer() != frame.pieceOwner(finalPosition)) {
					if(frame.isAttackAvailableForKing(getPlayer(), finalPosition))
						availableAttacks.add(finalPosition);
				}
				else {
					availableDefence.add(finalPosition);
				}
			}
		}
			
		
		//right move
		if( (int)position.getFile()+1 <= (int)'H') {
			finalPosition = new Position( (char)( (int)position.getFile()+1 ), position.getRank() );
			if(frame.isPositionEmpty(finalPosition) && frame.isMoveAvailableForKing(getPlayer(), finalPosition))
				availableMoves.add(finalPosition);
			else if(frame.getPiece(finalPosition) != null && super.getPlayer() != frame.pieceOwner(finalPosition)) {
				if(frame.isAttackAvailableForKing(getPlayer(), finalPosition))
					availableAttacks.add(finalPosition);
			}
			else {
				availableDefence.add(finalPosition);
			}
		}
		
		//left move
		if( (int)position.getFile()-1 >= (int)'A') {
			finalPosition = new Position( (char)( (int)position.getFile()-1 ), position.getRank() );
			if(frame.isPositionEmpty(finalPosition) && frame.isMoveAvailableForKing(getPlayer(), finalPosition))
				availableMoves.add(finalPosition);
			else if(frame.getPiece(finalPosition) != null && super.getPlayer() != frame.pieceOwner(finalPosition)) {
				if(frame.isAttackAvailableForKing(getPlayer(), finalPosition))
					availableAttacks.add(finalPosition);
			}
			else {
				availableDefence.add(finalPosition);
			}
		}
		
		super.setAvailableAttacks(availableAttacks);
		super.setAvailableMoves(availableMoves);
		super.setAvailableDefence(availableDefence);
		
	}
	
	@Override
	public String toString() {
		return "King"+Integer.toString(getPlayer());
	}
	
}
