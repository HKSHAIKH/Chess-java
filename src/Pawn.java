import java.util.ArrayList;
import java.util.List;

/**
 * This class tracks pawn parameters and values.
 */
public class Pawn extends Piece {
	
	/**
	 * Default constructor of Pawn
	 */
	public Pawn(int player,Frame frame) {
		super(player,frame);
	}

	/**
	 * @param frame the frame of chess board
	 * @param position the initial position of Pawn
	 * @param player the player of chess
	 */
	public Pawn(int player,Position position,Frame frame) {
		super(player,position,frame);
	}


	/**
	 * To calculate available moves and attacks of pawn
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
		
		//moves for player one
		if(getPlayer() == 1 && position.getRank()+1 <= 8) {
			
			finalPosition = new Position( position.getFile() , position.getRank()+1 ) ;
			if(frame.isPositionEmpty(finalPosition)) {
				availableMoves.add(finalPosition);
				if(position.getRank() == 2) {
					finalPosition = new Position( position.getFile() , position.getRank()+2 ) ;
					if(frame.isPositionEmpty(finalPosition))
						availableMoves.add(finalPosition);
				}
			}
			
			//available attacks
			if((int)position.getFile()+1 <= (int)'H') {
				finalPosition = new Position( (char)( (int)position.getFile()+1 ),position.getRank()+1);
				if(!frame.isPositionEmpty(finalPosition) && frame.pieceOwner(finalPosition)==2) {
					availableAttacks.add(finalPosition);
				}
				else
					availableDefence.add(finalPosition);
			}
			if((int)position.getFile()-1 >= (int)'A') {
				finalPosition = new Position( (char)( (int)position.getFile()-1 ),position.getRank()+1);
				if(!frame.isPositionEmpty(finalPosition) && frame.pieceOwner(finalPosition)==2) {
					availableAttacks.add(finalPosition);
				}
				else
					availableDefence.add(finalPosition);
			}
		}
		
		//moves for player two
		else if(getPlayer() ==2 && position.getRank()-1 >= 1) {
			
			finalPosition = new Position( position.getFile() , position.getRank()-1 ) ;
			if(frame.isPositionEmpty(finalPosition)) {
				availableMoves.add(finalPosition);
				if(position.getRank() == 7) {
					finalPosition = new Position( position.getFile() , position.getRank()-2 ) ;
					if(frame.isPositionEmpty(finalPosition))
						availableMoves.add(finalPosition);
				}
			}
			
			//available attacks
			if((int)position.getFile()+1 <= 'H') {
				finalPosition = new Position( (char)( (int)position.getFile()+1 ),position.getRank()-1);
				if(!frame.isPositionEmpty(finalPosition) && frame.getPiece(finalPosition).getPlayer()==1) {
					availableAttacks.add(finalPosition);
				}
				else
					availableDefence.add(finalPosition);
			}
			if((int)position.getFile()-1 >= 'A') {
				finalPosition = new Position( (char)( (int)position.getFile()-1 ),position.getRank()-1);
				if(!frame.isPositionEmpty(finalPosition) && frame.getPiece(finalPosition).getPlayer()==1) {
					availableAttacks.add(finalPosition);
				}
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
		return "Pawn"+Integer.toString(getPlayer());
	}
}
