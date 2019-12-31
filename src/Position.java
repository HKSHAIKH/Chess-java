/**
 * This class is for getting the position of individual pieces.
 */
public class Position {
	
	private char file;			//file variable varies from A to H in English language
	private int rank;			//rank variable varies from 1 to 8
	 
	
	/**
	 * Initializing the variable through default constructor.
	 */
	Position()
	{
		setFile('\u0000');
		setRank(0);
	}
	
	/**
	 * Parameterized constructor of Position.
	 * @param file initial file of the Position.
	 * @param rank initial rank of the Position.
	 */
	Position(char file,int rank)
	{
		this.setFile(file);
		this.setRank(rank);
	}

	/**
	 * @return the file
	 */
	public char getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(char file) {
		this.file = file;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/**
	 * this method compares two positions.
	 * @param position position to compare 
	 * @return status of equality
	 */
	public boolean isequal(Position position) {
		if(this.getFile() == position.getFile() && this.getRank() == position.getRank()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "["+ Character.toString(this.file) + ","+Integer.toString(this.rank) +"]";
	}
}
