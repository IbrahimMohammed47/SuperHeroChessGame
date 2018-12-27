package graphics;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import model.game.Game;
public class DisplayT {

	private JFrame frame;
	
	private Canvas canvas;
	private int width, height;
	Game g ;
	JPanel cards ;
	JPanel menuPanel ;
	JPanel gamePanel ;
	JPanel HowToPlayPanel ;
	JPanel CreditsPanel ;
	CardLayout cardLayout ;
	
	
	JLabel info ;
	JPanel upPanel ;
	JPanel upRightPanelp1 ;
	JPanel upLeftPanelp2 ;
	JPanel cemetryRightp1 ;
	JPanel cemetryLeftp2 ;
	JPanel skillsPanelLeftp2 ;
	JPanel skillsPanelRightp1 ;
	JLabel upRightPanelLabel ;
	JLabel upLeftPanelLabel ;
	
	public JLabel getInfo() {
		return info;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JPanel getCards() {
		return cards;
	}
	public JPanel getUpRightPanelp1() {
		return upRightPanelp1;
	}
	public JPanel getUpLeftPanelp2() {
		return upLeftPanelp2;
	}
	public JPanel getCemetryRightp1() {
		return cemetryRightp1;
	}
	public JPanel getCemetryLeftp2() {
		return cemetryLeftp2;
	}
	public JLabel getUpRightPanelLabel() {
		return upRightPanelLabel;
	}
	public JLabel getUpLeftPanelLabel() {
		return upLeftPanelLabel;
	}
	public JPanel getMenuPanel() {
		return menuPanel ;
	}
	public void setMenuPanel(JPanel menuT) {
		this.menuPanel = menuT;
	}
	public void setHowToPlayPanel(JPanel HowToPlayPanel) {
		this.HowToPlayPanel = HowToPlayPanel;
	}
	public void setCreditsPanel(JPanel Credits) {
		this.CreditsPanel = Credits;
	}
	public DisplayT(Game g){
		this.width = 1400;
		this.height = 870;
		this.g=g;
		createDisplay();
		
	}
	public Canvas getCanvas() {
		return canvas ;
	}
	public void ShowGameorMenu(String x) {
		cardLayout.show(cards, x);
	}
	private void createDisplay(){
		frame = new JFrame("Super Hero Chess");
		frame.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		cards = new JPanel();
		cards.setLayout(new CardLayout());
		
		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		
		setMenuPanel(new MenuT(g));
		setHowToPlayPanel(new HowToPlay(g));
		setCreditsPanel(new Credits(g));
		
		upPanel = new JPanel();
		upPanel.setLayout(new GridLayout(1,2));
		
		upRightPanelp1 = new JPanel();
		upRightPanelp1.setLayout(new FlowLayout(1,2,15));
		upRightPanelp1.setBackground(new Color(18, 203, 196));     
		
		upLeftPanelp2 = new JPanel();
		upLeftPanelp2.setLayout(new FlowLayout(1,2,15));
		upLeftPanelp2.setBackground(new Color(18, 203, 196).darker().darker()); //because player one starts
		
		upPanel.add(upLeftPanelp2);
		upPanel.add(upRightPanelp1);
		
		
		upRightPanelLabel = new JLabel(g.getPlayer1().getName()) ;
		upLeftPanelLabel = new JLabel(g.getPlayer2().getName()) ;
		upRightPanelLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		upRightPanelLabel.setForeground(Color.BLACK);
		upLeftPanelLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		upLeftPanelLabel.setForeground(Color.BLACK);
		
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension(80,50));
		space.setBackground(new Color(153, 0, 0,0));
		JPanel space2 = new JPanel();
		space2.setPreferredSize(new Dimension(80,50));
		space2.setBackground(new Color(153, 0, 0,0));
		

		
		
		upPanel.setPreferredSize(new Dimension(width,80));
		upRightPanelp1.setPreferredSize(new Dimension(width/2,100));
		upLeftPanelp2.setPreferredSize(new Dimension(width/2,100));
		
		upLeftPanelp2.add(upLeftPanelLabel);
		upLeftPanelp2.add(space);
		for(int i = 0 ; i<6 ; i++) {     //payloads
			 
			JPanel Shot1 = new JPanel();
			Shot1.setPreferredSize(new Dimension(80,50));
			Shot1.setBackground((new Color(153, 0, 0)).darker());
			upRightPanelp1.add(Shot1);
			g.getP1payLoadPans()[i] = Shot1 ;

			JPanel Shot2= new JPanel();
			Shot2.setPreferredSize(new Dimension(80,50));
			Shot2.setBackground((new Color(153, 0, 0)).darker());
			upLeftPanelp2.add(Shot2);
			g.getP2payLoadPans()[i] = Shot2 ;

		}
		upRightPanelp1.add(space2);
		upRightPanelp1.add(upRightPanelLabel);
		
		upPanel.add(upLeftPanelp2);
		upPanel.add(upRightPanelp1);
		
		JPanel cemetry = new JPanel();
		cemetry.setLayout(new GridLayout(1,3));
		cemetry.setPreferredSize(new Dimension(width,120));
		cemetry.setBackground(Color.GRAY);
		
		cemetryRightp1 = new JPanel();
		cemetryRightp1.setLayout(new FlowLayout(1,2,10));
		cemetryRightp1.setBackground(Color.GRAY); 
		cemetryRightp1.setPreferredSize(new Dimension((width/2)-25,120));
		
		info = new JLabel();
		info.setBackground(new Color(0, 148, 50)); 
		info.setForeground(Color.WHITE);
		info.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		info.setOpaque(true);
		info.setHorizontalTextPosition(SwingConstants.CENTER);
		info.setPreferredSize(new Dimension(50,120));
		
		cemetryLeftp2 = new JPanel();
		cemetryLeftp2.setLayout(new FlowLayout(1,2,10));
		cemetryLeftp2.setBackground(Color.GRAY); 
		cemetryLeftp2.setPreferredSize(new Dimension((width/2)-25,120));
		
		for(int i = 0 ; i<12 ; i++) {     //cemetry
			
			JButton Shot1 = new JButton();
			Shot1.setPreferredSize(new Dimension(60,60));
			cemetryRightp1.add(Shot1);
			g.getP1cemetryButs().add(Shot1) ;
			Shot1.setBackground(Shot1.getParent().getBackground());
			Shot1.setBorderPainted(false);
			
			JButton Shot2= new JButton();
			Shot2.setPreferredSize(new Dimension(60,60));
			cemetryLeftp2.add(Shot2);
			g.getP2cemetryButs().add(Shot2) ;
			Shot2.setBackground(Shot2.getParent().getBackground());
			Shot2.setBorderPainted(false);
		}
		
		cemetry.add(cemetryLeftp2);
		cemetry.add(info);
		cemetry.add(cemetryRightp1);
			
		
		skillsPanelLeftp2 = new JPanel() ;
		skillsPanelLeftp2.setLayout(new FlowLayout(5,1,10));
		skillsPanelLeftp2.setBackground(new Color(18, 203, 196)); 
		skillsPanelLeftp2.setPreferredSize(new Dimension(100, height-450));
		
		skillsPanelRightp1 = new JPanel() ;
		skillsPanelRightp1.setLayout(new FlowLayout(5,1,10));
		skillsPanelRightp1.setBackground(new Color(18, 203, 196).darker().darker()); 
		skillsPanelRightp1.setPreferredSize(new Dimension(100, height-450));

		for(int i = 0 ; i<6 ; i++) {     //skills
			
			String x = "" ;
			switch(i) {
				case 0 : x="Teleport" ; break ;
				case 1 : x="Restore" ; break ;
				case 2 : x="Hack" ; break ;
				case 3 : x="Smash" ; break ;
				case 4 : x="Resurrect" ; break ;
				case 5 : x="Shoot" ; break ;
			}
			JButton Shot1 = new JButton();
			Shot1.setPreferredSize(new Dimension(100,60));
			Shot1.setText(x);
			skillsPanelRightp1.add(Shot1);
			g.getP1skillsButs()[i]=Shot1 ;
			Shot1.setBackground(new Color(241, 196, 15));
			JButton Shot2= new JButton();
			Shot2.setPreferredSize(new Dimension(100,60));
			Shot2.setText(x);
			skillsPanelLeftp2.add(Shot2);
			g.getP2skillsButs()[i]=Shot2 ;
			Shot2.setBackground(new Color(241, 196, 15));
		}
		
		/////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width-100, height-200));

		canvas.setMaximumSize(new Dimension(width-100, height-100));
		canvas.setMinimumSize(new Dimension(width-100, height-100));
		
		gamePanel.add(canvas,BorderLayout.CENTER);
		gamePanel.add(cemetry,BorderLayout.SOUTH);
		gamePanel.add(upPanel,BorderLayout.NORTH);
		gamePanel.add(skillsPanelRightp1,BorderLayout.WEST);
		gamePanel.add(skillsPanelLeftp2,BorderLayout.EAST);
		
		
		cards.add(gamePanel,"game");
		cards.add(menuPanel,"menu");
		cards.add(HowToPlayPanel,"HowToPlayPanel");
		cards.add(CreditsPanel,"credits");
		
		cardLayout = (CardLayout) cards.getLayout();
		
		
		frame.setLocation(10, 10);
		frame.add(cards);
		
		
		frame.pack();     //to guarantee seeing all the canvas (not important)
		
	}
	public JPanel getSkillsPanelLeftp2() {
		return skillsPanelLeftp2;
	}
	public JPanel getSkillsPanelRightp1() {
		return skillsPanelRightp1;
	}
}