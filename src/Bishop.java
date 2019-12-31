import java.util.ArrayList;
import java.util.List;

/**
 * this class tracks bishop parameters and values.
 */

public class Bishop extends Piece{

	/**
	 * Constructor with null position.
	 * @param player the player of bishop
	 * @param frame frame of chess board
	 */
	Bishop(int player ,Frame frame)
	{
		super(player,frame);
	}
	
	/**
	 * Parameterized constructor of bishop at the chess board.
	 * @param player the player of bishop
	 * @param position the position of bishop
	 * @param frame frame of chess board
	 */
	Bishop(int player,Position position ,Frame frame)
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
		int rank = position.getRank();			// To get the rank of bishop
		char file = position.getFile();			// To get the file of bishop
	
		//lower-left moves of bishop
		for(int i=rank-1,f=(int)file-1;i>0 && f>=(int)'A';i--,f--)
		{
			Position finalPosition = new Position((char)f,i);
			
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
		
		//lower-right moves of bishop 
		for(int i=rank-1,f=(int)file+1;i>0 && f<=(int)'H';i--,f++)
		{
			Position finalPosition = new Position((char)f,i);
			
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
		
		//upper-left moves of bishop
		for(int i=rank+1,f=(int)file-1;i<=8 && f>=(int)'A';i++,f--)
		{
			Position finalPosition = new Position((char)f,i);
			
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
		
		//upper-right moves of bishop
		for(int i=rank+1,f=(int)file+1;i<=8 && f<=(int)'H';i++,f++)
		{
			Position finalPosition = new Position((char)f,i);
			
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
		return "Bishop"+Integer.toString(getPlayer());
	}
}
