import java.util.ArrayList;
import java.util.List;

/**
 * This class tracks queen parameters and values.
 */
public class Queen extends Piece{

	/**
	 * Constructor with null position.
	 * @param player the player of queen
	 * @param frame frame of chess board
	 */
	Queen(int player ,Frame frame)
	{
		super(player,frame);
	}

	/**
	 * Parameterized constructor of queen at the chess board.
	 * @param player the player of queen
	 * @param position the position of queen
	 * @param frame frame of chess board
	 */
	Queen(int player,Position position ,Frame frame)
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
		int rank = position.getRank();			// To get the rank of queen 
		char file = position.getFile();			// To get the file of queen
	
		
		//available lower moves of queen
		for(int i=rank-1;i>0;i--)
		{
			Position finalPosition = new Position(file, i);
			
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
		
		//available upper moves of queen
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
		
		//available left moves of queen
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
		
		//available right moves of queen
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
		
		//lower-left moves of queen
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
		
		//lower-right moves of queen 
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
		
		//upper-left moves of queen
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
		
		//upper-right moves of queen
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
		return "Queen"+Integer.toString(getPlayer());
	}
}
