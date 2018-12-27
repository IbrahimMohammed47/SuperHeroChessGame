package model.game;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;

import graphics.Assets;
import graphics.DisplayT;
import graphics.MenuT;
import graphics.TileDraw;
import graphics.TileT;
import input.KeyboardManager;
import input.MouseManager;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public class Game
{
	private final int payloadPosTarget = 6;
	private final int boardWidth = 6;
	private final int boardHeight = 7;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Cell[][] board;
	private MouseManager mouseManager ;
	private KeyboardManager keyboardManager;
	private Graphics gr;
	private BufferStrategy bs ;
	
	Polygon plg ;              
	public TileT[][] tiles;
	private JPanel[] p1payLoadPans;
	private JPanel[] p2payLoadPans;
	
	private ArrayList<JButton>  p1cemetryButs;
	private ArrayList<JButton>  p2cemetryButs;
	
	private JButton[]  p1skillsButs;
	private JButton[]  p2skillsButs;

	final int[] xl= {517,42,597,1072} ;                                 //board borders coordiantes
	final int[] yl= {50,287,565,327} ;
	
	private DisplayT display;
	private MenuT menu;
	Color curTurn = new Color(46, 204, 113) ;
	
	
	public Game(Player player1, Player player2)
	{
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
		board = new Cell[boardHeight][boardWidth];
		tiles = new TileT[boardHeight][boardWidth];
		p1payLoadPans = new JPanel[payloadPosTarget] ;
		p2payLoadPans = new JPanel[payloadPosTarget] ;
		p1cemetryButs = new ArrayList<JButton>();
		p2cemetryButs = new ArrayList<JButton>();
		p1skillsButs = new JButton[6];
		p2skillsButs = new JButton[6];
		mouseManager = new MouseManager(this) ;
		keyboardManager = new KeyboardManager(this) ;
		
		
		init();		                   //initialize Frames and panels (board not drawn )
		createCells();
		assemblePieces();
		render();                      //Draw things first time

	}
	public KeyboardManager getKeyboardManager() {
		
		return keyboardManager ;
	}
	public MouseManager getMouseManager() {
		return mouseManager ;
	}
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer)
	{
		this.currentPlayer = currentPlayer;
	}

	public Player getPlayer1()
	{
		return player1;
	}

	public Player getPlayer2()
	{
		return player2;
	}
	public int getPayloadPosTarget()
	{
		return payloadPosTarget;
	}
	public int getBoardWidth() {
		return boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}
	
	public void createCells()
	{
		for(int i = 0; i < boardHeight; i++) {
			for(int j = 0; j < boardWidth; j++) {
				board[i][j] = new Cell();
			}
		}
	}
	
	public void assemblePieces() 
	{
		ArrayList<Piece> Heros_P1 = new ArrayList<Piece>();
		Heros_P1.add(new Super(player1, this, "Om el3arees-Super"));    //  Super
		Heros_P1.add(new Armored(player1, this, "Abo el3aroorsa-Armored"));  // Armored
		Heros_P1.add(new Medic(player1, this, "Okht el3aroosa-Medic"));  // Medic
		Heros_P1.add(new Ranged(player1, this, "El3aroosa-Ranged"));  // Ranged
		Heros_P1.add(new Speedster(player1, this, "Akho el3arees-Speedster"));  //  Speedster
		Heros_P1.add(new Tech(player1, this, "El3arees-Tech"));  //Tech 
		
		ArrayList<Piece> Heros_P2 = new ArrayList<Piece>();
		Heros_P2.add(new Armored(player2, this, "Abo el3aroorsa-Armored"));
		Heros_P2.add(new Super(player2, this, "Om el3arees-Super"));
		Heros_P2.add(new Medic(player2, this, "Okht el3aroosa-Medic"));
		Heros_P2.add(new Speedster(player2, this, "Akho el3arees-Speedster"));
		Heros_P2.add(new Tech(player2, this, "El3arees-Tech"));
		Heros_P2.add(new Ranged(player2, this, "El3aroosa-Ranged"));
		
		Collections.shuffle(Heros_P2);
		Collections.shuffle(Heros_P1);
		for(int j = 0; j < boardWidth; j++)
		{
			board[1][j].setPiece(Heros_P2.get(j)); 
			getCellAt(1, j).getPiece().setPosI(1);
			getCellAt(1, j).getPiece().setPosJ(j);
			
			board[2][j].setPiece(new SideKickP2(this, "Ma3zoom-Sidekick"));
			getCellAt(2, j).getPiece().setPosI(2);
			getCellAt(2, j).getPiece().setPosJ(j);
			
			board[4][j].setPiece(new SideKickP1(this, "Ma3zoom-Sidekick"));
			getCellAt(4, j).getPiece().setPosI(4);
			getCellAt(4, j).getPiece().setPosJ(j);
			
			board[5][j].setPiece(Heros_P1.get(j));
			getCellAt(5, j).getPiece().setPosI(5);
			getCellAt(5, j).getPiece().setPosJ(j);
			
		}
		
	}
	public Cell getCellAt(int i, int j) {
		return board[i][j]; 
	}
	public void switchTurns () {
			currentPlayer = currentPlayer == player1 ? player2 : player1;
			if(getCurrentPlayer() == getPlayer1()) {
				toggleTurnColor(getDisplay().getUpRightPanelp1(),getDisplay().getUpLeftPanelp2(),getDisplay().getSkillsPanelRightp1(),getDisplay().getSkillsPanelLeftp2());
			}else {
				toggleTurnColor(getDisplay().getUpLeftPanelp2(), getDisplay().getUpRightPanelp1(),getDisplay().getSkillsPanelLeftp2(),getDisplay().getSkillsPanelRightp1());
			}
	}
	public boolean checkWinner() {
		if (currentPlayer.getPayloadPos() == 6) {
			LauncherT.brk = true ;
			return true ;
		}
		return false ;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void init(){              //initialize the graphics
		
		setDisplay(new DisplayT(this));
		
		getDisplay().getCanvas().createBufferStrategy(3);
		bs = getDisplay().getCanvas().getBufferStrategy();
		
		getDisplay().getFrame().addMouseListener(mouseManager);
		getDisplay().getFrame().addMouseMotionListener(mouseManager);
		getDisplay().getCanvas().addMouseListener(mouseManager);
		getDisplay().getCanvas().addMouseMotionListener(mouseManager);
		getDisplay().getCanvas().addKeyListener(keyboardManager);
		
		Assets.init();	
	}
	
	


	public void render () {
		TileDraw.colorFlag = false;
		TileDraw.ctr = 0 ;
		gr=(Graphics2D)bs.getDrawGraphics();
		gr.clearRect(0, 0, 1400, 750);                               //Clear Canvas
		gr.drawImage(Assets.canbg, 0, 0,getDisplay().getCanvas().getWidth(),getDisplay().getCanvas().getHeight()+5, null);
		
		gr.setColor(new Color(0,0,0,1));
		plg = new Polygon(xl, yl, 4) ;
		gr.fillPolygon(plg);                                           // surrounds the board
														
		gr.setColor(new Color(44,62,80));
		for(int i = 0 ;i<boardHeight;i++) {                           //Draw Empty Tiles                             
			for(int j = 0 ;j<boardWidth;j++) {
				int offsetx = 350+80*(i+1) ;                             //-250 -180
				int offsety = -280+80*(j+1) ;		
				TileDraw t = new TileDraw(gr,offsetx,offsety);
				tiles[i][j] = t.getTile() ;
				
				

				if(board[i][j].getPiece()!=null) {                       //Draw characters
					if (board[i][j].getPiece().getOwner()==getPlayer1() || board[i][j].getPiece() instanceof SideKick) {
						gr.drawImage(board[i][j].getPiece().getImage(),(int)tiles[i][j].getimagePoint().getX(),(int)tiles[i][j].getimagePoint().getY(),85,150,null);
					}else {
						gr.drawImage(board[i][j].getPiece().getImage(),(int)tiles[i][j].getimagePoint().getX(),(int)tiles[i][j].getimagePoint().getY(),105,155,null);
					}
				}
				
				tiles[i][j].getTileDraw().addMouseMotionListener(getMouseManager());//////////////////////////////////////////////
			}
		}
		
		int xpoints[] = {0, 0, 0, 0};  
	    int ypoints[] = {0, 0, 0, 0};
		xpoints[0]=265-69+80*0-158 ; ypoints[0]=548-215+40*0 ;           //Drawing Edges
		xpoints[1]=265-69+80*0-158; ypoints[1]=501-215+40*0 ;
		xpoints[2]=345-69+80*6-158 ; ypoints[2]=542-215+40*6 ;
		xpoints[3]=345-69+80*6-158 ; ypoints[3]=598-215+40*6 ;
		gr.setColor((new Color (44, 62, 80)).darker().darker());
		gr.fillPolygon(new Polygon(xpoints,ypoints,4));
		

		xpoints[0]=734-69+90+80*0-158 ; ypoints[0]=798-215+40-40*0 ;
		xpoints[1]=734-69+90+80*0-158 ; ypoints[1]=741-215+40-40*0 ;
		xpoints[2]=814-69+90+80*5-158 ; ypoints[2]=701-215+40-40*5 ;
		xpoints[3]=814-69+90+80*5-158 ; ypoints[3]=748-215+40-40*5 ;
		gr.setColor((new Color (44, 62, 80)));
		gr.fillPolygon(new Polygon(xpoints,ypoints,4));
		

		
		
		
		((Graphics2D)gr).setComposite(AlphaComposite.Src);
		((Graphics2D)gr).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); // Supposed to improve graphics 
		((Graphics2D)gr).setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		((Graphics2D)gr).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		gr.dispose();
		
		bs.show();
		
	}
	
	public void toggleTurnColor(JPanel pl1,JPanel pl2,JPanel sm1,JPanel sm2) {
			pl1.setBackground(pl1.getBackground().brighter().brighter());
			pl2.setBackground(pl2.getBackground().darker().darker());
			sm1.setBackground(pl1.getBackground().darker().darker());
			sm2.setBackground(pl2.getBackground().brighter().brighter());

	}



	public JPanel[] getCurrentpayLoadPans() {
		return getCurrentPlayer()==player1? p1payLoadPans : p2payLoadPans ;
	}

	public DisplayT getDisplay() {
		return display;
	}

	public void setDisplay(DisplayT display) {
		this.display = display;
	}
	
	public MenuT getMenu() {
		return menu;
	}
	
	public JPanel[] getP1payLoadPans() {
		return p1payLoadPans ;
	}
	public JPanel[] getP2payLoadPans() {
		return p2payLoadPans ;
	}
	public ArrayList<JButton> getP1cemetryButs() {
		return p1cemetryButs;
	}
	public ArrayList<JButton> getP2cemetryButs() {
		return p2cemetryButs;
	}
	public JButton[] getP1skillsButs() {
		return p1skillsButs;
	}
	public JButton[] getP2skillsButs() {
		return p2skillsButs;
	}
	public ArrayList<JButton> getCurrentCemetryButs() {
		if (getCurrentPlayer()==getPlayer1()) {
			return getP1cemetryButs() ;
		}else {
			return getP2cemetryButs() ;
		}
	}
	public JButton[] getCurrentSkillsButs() {
		if (getCurrentPlayer()==getPlayer1()) {
			return getP1skillsButs() ;
		}else {
			return getP2skillsButs() ;
		}
	}

	
	public Direction getDirection() {
		while(true) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);       
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch(keyboardManager.getPressedKey()) {
				case KeyEvent.VK_NUMPAD8 : return Direction.UP ; 
				case KeyEvent.VK_NUMPAD2 : return Direction.DOWN  ;
				case KeyEvent.VK_NUMPAD6 : return Direction.LEFT  ;
				case KeyEvent.VK_NUMPAD4 : return Direction.RIGHT  ;
				case KeyEvent.VK_NUMPAD7 : return Direction.UPRIGHT  ;
				case KeyEvent.VK_NUMPAD9 : return Direction.UPLEFT  ;
				case KeyEvent.VK_NUMPAD1 : return Direction.DOWNRIGHT  ;
				case KeyEvent.VK_NUMPAD3 : return Direction.DOWNLEFT  ;
			}
		}
	}

	public Point getSelectedCell() {
		int x ;
		int y ;
		while(true) {
			try {
				TimeUnit.MILLISECONDS.sleep(5);       
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			if(getMouseManager().isLeftPressed()) {       //Get where (x,y) of a mouse left click
				x = getMouseManager().getMouseX() ;         
				y = getMouseManager().getMouseY() ; 
															
				if(plg.contains(new Point(x,y))) {          //Check if the click is inside the board
					break ;
				}
			}
		}
		for(int i = 0 ; i<getBoardHeight() ; i++ ) {      //Get the cell that lies around (x,y)
			for(int j = 0 ; j<getBoardWidth() ; j++ ) {
				if(tiles[i][j].contains(x,y)) {
					if(board[i][j].getPiece()!=null) {
						if(board[i][j].getPiece() instanceof Armored) {
							getDisplay().getInfo().setText("<html>"+"Name : "+board[i][j].getPiece().getName()
															+" <br/> Owner : "+board[i][j].getPiece().getOwner().getName()
															+" <br/> Shield is Up: "+((Armored)board[i][j].getPiece()).isArmorUp()+ "</html>");
						}else if(board[i][j].getPiece() instanceof ActivatablePowerHero) {
							getDisplay().getInfo().setText("<html>"+"Name : "+board[i][j].getPiece().getName()
															+" <br/> Owner : "+board[i][j].getPiece().getOwner().getName()
															+" <br/> Power is Used: "+((ActivatablePowerHero)board[i][j].getPiece()).isPowerUsed()+ "</html>");
						}else {
							getDisplay().getInfo().setText("<html>"+"Name : "+board[i][j].getPiece().getName()
															+" <br/> Owner : "+board[i][j].getPiece().getOwner().getName()+ "</html>");
						}
					}
					return new Point(i,j);
					
				}
			}
		}
		return null;
		
	}
	
	

	
}
