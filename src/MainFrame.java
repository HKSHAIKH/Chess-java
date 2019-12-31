import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * This class is responsible for GUI.
 */


public class MainFrame implements ActionListener{
	
	Frame frame;
	JFrame  mainFrame;
	
	JPanel boardPanel;
	JPanel scorePanel;
	JPanel deathPanelOne;
	JPanel deathPanelTwo;
	
	JButton[][] mainFrameButtons;
	JTextField[] textFields;
	JButton[][] deathFrameButtons;
	
	Position positionClicked;
	Position positioncoloured;
	int lastplayerPlayed;
	
	List<Position> colouredMoves;
	List<Position> colouredAttacks;
	
	/**
	 * Default constructor of MainFrame 
	 */
	MainFrame(Frame frame) {
		
		this.frame = frame;
		positionClicked = null;
		positioncoloured = null;
		lastplayerPlayed = 2;
		prepareGUI();
		this.colouredMoves = new ArrayList<>();
		this.colouredAttacks = new ArrayList<>();
	}
	
	/**
	 * to prepare GUI.
	 */
	public void prepareGUI() {
		
		this.mainFrame = new JFrame("Chess");
		this.boardPanel = new JPanel();
		this.scorePanel = new JPanel();
		this.deathPanelOne = new JPanel();
		this.deathPanelTwo = new JPanel();
		this.mainFrameButtons = new JButton[8][8];
		this.deathFrameButtons = new JButton[2][16];
		this.textFields = new JTextField[2];
		textFields[0] = new JTextField("Player 1");
		textFields[1] = new JTextField("Player 2");
		
		
		mainFrame.setSize(1300,900);
		mainFrame.setLayout(new GridLayout(1,2));
		
		boardPanel.setSize(600, 600);
		boardPanel.setLayout(new GridLayout(8,8));
		
		scorePanel.setSize(600, 600);
		scorePanel.setLayout(new GridLayout(4,1));
		
		textFields[0].setSize(600,150);
		textFields[1].setSize(600,150);
		
		deathPanelOne.setSize(600,150);
		deathPanelOne.setLayout(new GridLayout(2,8));
		
		deathPanelTwo.setSize(600,150);
		deathPanelTwo.setLayout(new GridLayout(2,8));
		
		
		for(int rank=7; rank>=0 ;rank--) {
			for(int file=0; file<8 ; file++) {
				if(frame.getPiece(new Position((char)(file+65),rank+1) )  == null) {
					mainFrameButtons[rank][file] = new JButton();
				}
				else {
					mainFrameButtons[rank][file] = new JButton();
					insertImage(mainFrameButtons[rank][file],frame.getPiece(new Position((char)(file+65),rank+1)));
					
				}
				
				if((rank+file)%2 == 0) {
					mainFrameButtons[rank][file].setBackground(new Color(125,51,20));
				}
				else
					mainFrameButtons[rank][file].setBackground(new Color(255,180,100));
				
				mainFrameButtons[rank][file].addActionListener(this);
				boardPanel.add(mainFrameButtons[rank][file]);
				
			}
		}

		
		for(int i=0; i<16 ; i++) {
			
			deathFrameButtons[0][i] = new JButton();
			deathFrameButtons[1][i] = new JButton();
			
			deathFrameButtons[0][i].setBackground(new Color(255,180,100));
			deathFrameButtons[1][i].setBackground(new Color(125,51,20));
			
			deathPanelOne.add(deathFrameButtons[1][i]);
			deathPanelTwo.add(deathFrameButtons[0][i]);
			
		}
		
		
		scorePanel.add(textFields[1]);
		deathPanelOne.setVisible(true);
		scorePanel.add(deathPanelOne);
		
		scorePanel.add(textFields[0]);
		deathPanelTwo.setVisible(true);
		scorePanel.add(deathPanelTwo);
		
		
		scorePanel.setVisible(true);
		boardPanel.setVisible(true);
		mainFrame.add(boardPanel);
		mainFrame.add(scorePanel);
		mainFrame.setVisible(true);
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(e.getSource() == mainFrameButtons[i][j]) {
					if(positionClicked == null) {
						if(!frame.isPositionEmpty(new Position((char)(j+65),i+1)) && frame.pieceOwner(new Position((char)(j+65),i+1)) != lastplayerPlayed) {
							
							positionClicked = new Position((char)(j+65),i+1);
							
							removeColour();
							colourMoves(frame.getPiece(positionClicked));
							
						}
					}
					else{
						if(!frame.isPositionEmpty(new Position((char)(j+65),i+1)) && frame.pieceOwner(new Position((char)(j+65),i+1)) != frame.pieceOwner(positionClicked)) {
							if(frame.attackPiece(positionClicked, new Position((char)(j+65),i+1))) {
								lastplayerPlayed = frame.pieceOwner(new Position((char)(j+65),i+1));
								mainFrameButtons[positionClicked.getRank()-1][(int)(positionClicked.getFile()-'A')].setIcon(null);
								
								insertImage(mainFrameButtons[i][j],frame.getPiece(new Position((char)(j+65),i+1)));
								
								for(int m=0;m<16;m++) {
									if((deathFrameButtons[lastplayerPlayed-1][m]).getIcon() == null) {
										insertImage(deathFrameButtons[lastplayerPlayed-1][m],frame.getCapturedPiece(lastplayerPlayed, m));
										break;
									}
								}
								
								cheak();
							}
							removeColour();
						}
						else if(!frame.isPositionEmpty(new Position((char)(j+65),i+1)) && frame.pieceOwner(new Position((char)(j+65),i+1)) == frame.pieceOwner(positionClicked)) {
							
							positionClicked = new Position((char)(j+65),i+1);
							
							removeColour();
							colourMoves(frame.getPiece(positionClicked));
							
							
						}
						else {
							if(frame.movePiece(positionClicked, new Position((char)(j+65),i+1))) {
								lastplayerPlayed = frame.pieceOwner(new Position((char)(j+65),i+1));
								mainFrameButtons[positionClicked.getRank()-1][(int)(positionClicked.getFile()-'A')].setIcon(null);;
								
								insertImage(mainFrameButtons[i][j],frame.getPiece(new Position((char)(j+65),i+1)));
								cheak();
							}
							removeColour();
						}
						
						
						positionClicked = null;
						
					}
				}
			}
		}
	}
	
	
	public void colourMoves(Piece piece) {
		for(int i=0; i<piece.getAvailableMoves().size(); i++) {
			 
			Position position= piece.getAvailableMoves().get(i);
			mainFrameButtons[position.getRank()-1][(position.getFile()-'A')].setBackground(Color.YELLOW);
		}
		colouredMoves = piece.getAvailableMoves();
		
		for(int i=0; i<piece.getAvailableAttacks().size(); i++) {
			Position position= piece.getAvailableAttacks().get(i);
			mainFrameButtons[position.getRank()-1][(position.getFile()-'A')].setBackground(Color.RED);
		}
		this.colouredAttacks = piece.getAvailableAttacks();
	}
	
	public void removeColour() {
		for(int i=0; i<colouredMoves.size(); i++) {
			Position position= colouredMoves.get(i);
			if(  ((position.getRank()-1)+(position.getFile()-'A'))%2 ==0  ) {
				mainFrameButtons[position.getRank()-1][(position.getFile()-'A')].setBackground(new Color(125,51,20));

			}
			else
				mainFrameButtons[position.getRank()-1][(position.getFile()-'A')].setBackground(new Color(255,180,100));
		}
		
		for(int i=0; i<colouredAttacks.size(); i++) {
			Position position= colouredAttacks.get(i);
			if(  ((position.getRank()-1)+(position.getFile()-'A'))%2 ==0  ) {
				mainFrameButtons[position.getRank()-1][(position.getFile()-'A')].setBackground(new Color(125,51,20));

			}
			else
				mainFrameButtons[position.getRank()-1][(position.getFile()-'A')].setBackground(new Color(255,180,100));
		}
	}
	
	
	public void insertImage(JButton button,Piece piece) {
		
		
		
		Icon rook1 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/wR.png"); 
		Icon knight1 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/wN.png"); 
		Icon bishop1 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/wB.png"); 
		Icon queen1 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/wQ.png"); 
		Icon king1 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/wK.png"); 
		Icon pawn1 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/wP.png"); 
		
		Icon rook2 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/bR.png"); 
		Icon knight2 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/bN.png"); 
		Icon bishop2 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/bB.png"); 
		Icon queen2 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/bQ.png"); 
		Icon king2 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/bK.png"); 
		Icon pawn2 = new ImageIcon("/home/ubuntu/Desktop/Chess/src/bP.png");
		
		
		if(piece.toString().equals("Rook1"))
			button.setIcon(rook1);
		else if(piece.toString().equals("Knight1"))
			button.setIcon(knight1);
		else if(piece.toString().equals("Bishop1"))
			button.setIcon(bishop1);
		else if(piece.toString().equals("Queen1"))
			button.setIcon(queen1);
		else if(piece.toString().equals("King1"))
			button.setIcon(king1);
		else if(piece.toString().equals("Pawn1"))
			button.setIcon(pawn1);
		
		else if(piece.toString().equals("Rook2"))
			button.setIcon(rook2);
		else if(piece.toString().equals("Knight2"))
			button.setIcon(knight2);
		else if(piece.toString().equals("Bishop2"))
			button.setIcon(bishop2);
		else if(piece.toString().equals("Queen2"))
			button.setIcon(queen2);
		else if(piece.toString().equals("King2"))
			button.setIcon(king2);
		else if(piece.toString().equals("Pawn2"))
			button.setIcon(pawn2);
			
	}
	
	
	public void cheak() {
		int player = frame.isCheak();
		Position kingPosition;
		if(player == 1) {
			kingPosition = frame.getPlayerOne().getKing().getPosition();
			positioncoloured = kingPosition;
			mainFrameButtons[kingPosition.getRank()-1][(int)(kingPosition.getFile()-'A')].setBackground(Color.RED);
		}
		else if(player == 2) {
			kingPosition = frame.getPlayerTwo().getKing().getPosition();
			positioncoloured = kingPosition;
			mainFrameButtons[kingPosition.getRank()-1][(int)(kingPosition.getFile()-'A')].setBackground(Color.RED);
		}
		else {
			kingPosition = frame.getPlayerOne().getKing().getPosition();
			if(( (kingPosition.getRank()-1)+ ((int)(kingPosition.getFile()-'A')) )%2 == 0) {
				mainFrameButtons[kingPosition.getRank()-1][kingPosition.getFile()-'A'].setBackground(new Color(125,51,20));
			}
			else
				mainFrameButtons[kingPosition.getRank()-1][kingPosition.getFile()-'A'].setBackground(new Color(255,180,100));
			
			kingPosition = frame.getPlayerTwo().getKing().getPosition();
			if(( (kingPosition.getRank()-1)+ ((int)(kingPosition.getFile()-'A')) )%2 == 0) {
				mainFrameButtons[kingPosition.getRank()-1][kingPosition.getFile()-'A'].setBackground(new Color(125,51,20));
			}
			else
				mainFrameButtons[kingPosition.getRank()-1][kingPosition.getFile()-'A'].setBackground(new Color(255,180,100));
			
			if(positioncoloured != null) {
			kingPosition = positioncoloured;
			if(( (kingPosition.getRank()-1)+ ((int)(kingPosition.getFile()-'A')) )%2 == 0) {
				mainFrameButtons[kingPosition.getRank()-1][kingPosition.getFile()-'A'].setBackground(new Color(125,51,20));
			}
			else
				mainFrameButtons[kingPosition.getRank()-1][kingPosition.getFile()-'A'].setBackground(new Color(255,180,100));
			}
		}
	}
	
}
