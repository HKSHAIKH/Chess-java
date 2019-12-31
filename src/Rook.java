import java.util.ArrayList;
import java.util.List;

/**
 * this class tracks rook parameters and values.
 */
public class Rook extends Piece{

	/**
	 * Constructor with null position.
	 * @param player the player of rook
	 * @param frame frame of chess board
	 */
	Rook(int player ,Frame frame)
	{
		super(player,frame);
	}
	
	
	/**
	 * Parameterized constructor of rook at the chess board.
	 * @param player the player of rook
	 * @param position the position of rook
	 * @param frame frame of chess board
	 */
	Rook(int player,Position position ,Frame frame)
	{
		super(player,position,frame);
	}
	
	
	@Override
	public void calculateAvailableMovesandAttacks() {
	
		if(this.getPosition() == null) {
			this.setAvailableAttacks(new ArrayList<>());
			this.setAvailableMoves(new ArrayList<>());
			this.setAvailableDefence(new ArrayList<>());
			return;
		}
		
		Frame frame = getFrame();
		List<Position> availableMoves = new ArrayList<Position>();
		List<Position> availableAttacks = new ArrayList<Position>();
		List<Position> availableDefence = new ArrayList<Position>();
		
		int player = this.getPlayer();
		Position position = this.getPosition();
		int rank = position.getRank();			// To get the rank of rook 
		char file = position.getFile();			// To get the file of rook
	
		
		//available lower moves of rook
		for(int i=rank-1;i>0;i--)
		{
			Position finalPosition = new Position(file, i);
			
			
			if(frame.isPositionEmpty(finalPosition))
				availableMoves.add(finalPosition);
			else 
			{
				if(frame.getPiece(finalPosition).getPlayer() != player) {
					availableAttacks.add(finalPosition);
					if(frame.getPiece(finalPosition).toString() == "King"+Integer.toString(frame.pieceOwner(finalPosition))) {
						availableDefence.add(finalPosition);
					}
				}
				
				else
					availableDefence.add(finalPosition);
				break;
			}
			
		}
		
		//available upper moves of rook
		for(int i=rank+1;i<=8;i++)
		{
			Position finalPosition = new Position(file , i);
			
			
			if(frame.isPositionEmpty(finalPosition))
				availableMoves.add(finalPosition);
			else 
			{
				if(frame.getPiece(finalPosition).getPlayer() != player) 
					availableAttacks.add(finalPosition);
				else
					availableDefence.add(finalPosition);
				break;
			}
		}
		
		//available left moves of rook
		for(int i=(int)file-1;i>=(int)'A';i--)
		{
			Position finalPosition = new Position((char)i,rank);
			
			
			if(frame.isPositionEmpty(finalPosition))
				availableMoves.add(finalPosition);
			else 
			{
				if(frame.getPiece(finalPosition).getPlayer() != player) 
					availableAttacks.add(finalPosition);
				else
					availableDefence.add(finalPosition);
				break;
			}
			
		}
		
		//available right moves of rook
		for(int i=(int)file + 1;i<=(int)'H';i++)
		{
			Position finalPosition = new Position((char)i,rank);
			
			if(frame.isPositionEmpty(finalPosition))
				availableMoves.add(finalPosition);
			else 
			{
				if(frame.getPiece(finalPosition).getPlayer() != player) 
					availableAttacks.add(finalPosition);
				else
					availableDefence.add(finalPosition);
				break;
			}
			
		}
		
		setAvailableMoves(availableMoves);
		setAvailableAttacks(availableAttacks);
		setAvailableDefence(availableDefence);
	}
	
	@Override
	public String toString() {
		return "Rook"+Integer.toString(getPlayer());
	}
}
