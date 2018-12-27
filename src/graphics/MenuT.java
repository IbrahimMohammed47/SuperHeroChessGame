package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.game.Game;
public class MenuT extends JPanel {

	
	private int width, height;
	Game g ;
	String p1 = "" ;
	String p2 = "" ;
	boolean showing = true ;


	public MenuT(Game g){
		this.g= g;
		this.width = 1400;
		this.height = 850;
		createMenu();
		
	}
	
	public boolean isShowing() {
		return showing ;
	}
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(Assets.bg, 0, 0,width,height, null);
	}

	private void createMenu(){
		
		setSize(new Dimension(width, height));
		setVisible(true);
		setLayout(new GridLayout(0,10));        //6,150,50
		//setBackground(new Color(44, 62, 80));  
		
		JPanel upSpace = new JPanel();
		//upSpace.setPreferredSize(new Dimension(width,80));
		upSpace.setPreferredSize(new Dimension(50,height));
		upSpace.setOpaque(false);
		
		JPanel downSpace = new JPanel();
		//downSpace.setPreferredSize(new Dimension(width,80));
		downSpace.setOpaque(false);
		
		

		JPanel upPanel = new JPanel();
		//upPanel.setPreferredSize(new Dimension(width,80));
		upPanel.setOpaque(false);
		
		JPanel midPanel = new JPanel();
		//midPanel.setPreferredSize(new Dimension(width,80));
		midPanel.setOpaque(false);
		
		JPanel downPanel = new JPanel();
		//downPanel.setPreferredSize(new Dimension(width,80));
		downPanel.setOpaque(false);
		
		
		
		
		JButton start = new JButton();
		//start.setPreferredSize(new Dimension(350,80));
		start.setBorderPainted(false);
		start.setFont(new Font("SansSerif", Font.BOLD, 24));
		//start.setText("Start game");
		start.setBackground(new Color(231, 76, 60));
		start.setForeground(Color.WHITE);
		start.setOpaque(false);
		//upPanel.add(start);	
		
		JButton credits = new JButton();
		//credits.setPreferredSize(new Dimension(350,80));
		credits.setBorderPainted(false);
		credits.setFont(new Font("SansSerif", Font.BOLD, 24));
		//credits.setText("Credits");
		credits.setBackground(new Color(231, 76, 60));
		credits.setForeground(Color.WHITE);
		credits.setOpaque(false);
		//midPanel.add(credits);	
		
		JButton exit = new JButton();
		//exit.setPreferredSize(new Dimension(350,80));
		exit.setBorderPainted(false);
		exit.setFont(new Font("SansSerif", Font.BOLD, 24));
		//exit.setText("Exit");
		exit.setBackground(new Color(231, 76, 60));
		exit.setForeground(Color.WHITE);
		exit.setOpaque(false);
		//downPanel.add(exit);	
		
		JButton howtoplay = new JButton();
		//howtoplay.setPreferredSize(new Dimension(350,80));
		howtoplay.setBorderPainted(false);
		howtoplay.setFont(new Font("SansSerif", Font.BOLD, 24));
		//howtoplay.setText("How to Play");
		howtoplay.setBackground(new Color(231, 76, 60));
		howtoplay.setForeground(Color.WHITE);
		howtoplay.setOpaque(false);
		//downSpace.add(howtoplay);
	
		
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p1 =JOptionPane.showInputDialog("enter player1 name");
				p2 =JOptionPane.showInputDialog("enter player2 name");
				g.getPlayer1().setName(p1);
				g.getPlayer2().setName(p2);
				g.getDisplay().getUpLeftPanelLabel().setText(p2);
				g.getDisplay().getUpRightPanelLabel().setText(p1);
				
				showing = false ;
			}
		});
		
	
		credits.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getDisplay().ShowGameorMenu("credits"); 

				
			}
		});
		
		howtoplay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getDisplay().ShowGameorMenu("HowToPlayPanel"); 

				
			}
		});
		
			
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		/////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////

		
		
//		add(upSpace);
//		add(upPanel);
//		add(midPanel);
//		add(downSpace);
//		add(downPanel);
//		

		for(int i = 0 ;i<3 ; i++) {
			JPanel x = new JPanel();
			x.setOpaque(false);
			add(x);
		}
		
		add(start);
		add(credits);
		add(howtoplay);
		add(exit);
	}
}