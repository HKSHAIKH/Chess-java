/**
* The back end frame of chess board.
*/

public class Frame  {
	
	private Piece[][] boardPieces;
	private Piece[][] capturedPieces;
	private Player playerOne;
	private Player playerTwo;
	
	Frame()
	{
		this.boardPieces = new Piece[8][8];
		this.capturedPieces = new Piece[2][16];
		this.playerOne = new Player(1,this);
		this.playerTwo = new Player(2,this);
		this.playerOne.calculateTotalAvailableMovesandAttacks();
		this.playerTwo.calculateTotalAvailableMovesandAttacks();
	}
	
	
	/**
	 * @return the playerOne
	 */
	public Player getPlayerOne() {
		return playerOne;
	}


	/**
	 * @return the playerTwo
	 */
	public Player getPlayerTwo() {
		return playerTwo;
	}


	/**
	 * to get piece from given position. 
	 * @param position the position of piece
	 * @return the piece at given position
	 */
	public Piece getPiece(Position position){
		Piece piece= boardPieces[(int)(position.getFile()-'A')][position.getRank()-1];
		return piece;
	}
	
	/**
	 * To set the selected piece at the given position
	 * @param position The position at which the player desires to set the chosen piece
	 * @param piece The selected piece
	 */
	public void setPiece(Position position,Piece piece)
	{
		boardPieces[(int)position.getFile()-'A'][position.getRank()-1]=piece;
	}
	
	
	public Piece getCapturedPiece(int player ,int index) {
		return capturedPieces[player-1][index];
	}
	
	public void setCapturedPiece(Piece piece) {
		for(int i=0 ; i<16 ; i++) {
			if(capturedPieces[2-piece.getPlayer()][i] == null) {
				capturedPieces[2-piece.getPlayer()][i] = piece;
				break;
			}
		}
	}
	
	
	/**
	 * to get player of piece at position
	 * @param position the position of piece
	 * @return the owner of piece at given position
	 */
	public int pieceOwner(Position position)
	{
		return getPiece(position).getPlayer();
	}
	
	/**
	 * To check if the position is empty
	 * @param position
	 * @return Status of given position
	 */
	public boolean isPositionEmpty(Position position) {
		if(getPiece(position) == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * To check that given position is available for  king to move  or not 
	 * @param player player of king
	 * @param position  
	 * @return
	 */
	public boolean isMoveAvailableForKing(int player,Position position) {
		if(player == 1) {
			for(int i=0;i<playerTwo.getTotalAvailableMoves().size();i++) {
				if(playerTwo.getTotalAvailableMoves().get(i).isequal(position)) {
					return false;
				}
			}
			return true;
		}
		else {
			for(int i=0;i<playerOne.getTotalAvailableMoves().size();i++) {
				if(playerOne.getTotalAvailableMoves().get(i).isequal(position)) {
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * To check that given position is available for  king to attack  or not 
	 * @param player player of king
	 * @param position  
	 * @return
	 */
	public boolean isAttackAvailableForKing(int player,Position position) {
		if(player == 1) {
			for(int i=0;i<playerTwo.getTotalAvailableDefence().size();i++) {
				if(playerTwo.getTotalAvailableDefence().get(i).isequal(position)) {
					return false;
				}
			}
			return true;
		}
		else {
			for(int i=0;i<playerOne.getTotalAvailableDefence().size();i++) {
				if(playerOne.getTotalAvailableDefence().get(i).isequal(position)) {
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 *To remove the selected piece
	 * @param position The selected position
	 */
	public void removePiece(Position position){
		setPiece(position,null);
	}
	
	/**
	 * Method to move the selected piece from its initial position to some other position
	 * @param initialPosition The position at which the piece initially stands
	 * @param finalPosition The position at which the player desires to move the chosen piece
	 */
	public boolean movePiece(Position initialPosition , Position finalPosition) {
		
		if(getPiece(initialPosition).move(finalPosition)) {
			setPiece(finalPosition,getPiece(initialPosition));
			removePiece(initialPosition);
			playerOne.calculateTotalAvailableMovesandAttacks();
			playerTwo.calculateTotalAvailableMovesandAttacks();
			return true;
		}
		return false;
	}
	
	/**
	 * To attack the piece at final position by the piece at initial position
	 * @param initialPosition The position from which the attack comes
	 * @param finalPosition The position which is attacked
	 */
	public boolean attackPiece(Position initialPosition , Position finalPosition)
	{
		if(getPiece(initialPosition).attack(finalPosition)) {
			getPiece(finalPosition).remove();
			capturePiece(finalPosition);
			setPiece(finalPosition,getPiece(initialPosition));
			removePiece(initialPosition);
			playerOne.calculateTotalAvailableMovesandAttacks();
			playerTwo.calculateTotalAvailableMovesandAttacks();
			return true;
		}
		return false;
	}
	
	/**
	 * To get the piece attacked
	 * @param position
	 */
	public void capturePiece(Position position)
	{
		Piece capturedPiece = this.getPiece(position);
		removePiece(position);
		this.setCapturedPiece(capturedPiece);
	}
	
	public int isCheak() {
			if(playerTwo.getKing() == null || playerOne.getKing() == null) {
				throw new NullPointerException("Game finished");
			}
			for(int i=0 ; i<this.playerOne.getTotalAvailableAttacks().size() ; i++) {
				if(this.playerTwo.getKing().getPosition().isequal(playerOne.getTotalAvailableAttacks().get(i)))
					return 2;
			}
			
			for(int i=0 ; i<this.playerTwo.getTotalAvailableAttacks().size() ; i++) {
				if(this.playerOne.getKing().getPosition().isequal(playerTwo.getTotalAvailableAttacks().get(i)))
					return 1;
			}
			return 0;
		
	}

	
}
