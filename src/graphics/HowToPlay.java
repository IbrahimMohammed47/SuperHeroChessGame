package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.game.Game;

public class HowToPlay extends JPanel {
	private int width, height;
	Game g ;



	public HowToPlay(Game g){
		this.g= g;
		this.width = 1400;
		this.height = 850;
		createHowToPlay();
		
	}
	

	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	    	g.drawImage(Assets.bg1, 0, 0,width,height, null);
	        g.drawImage(Assets.controls, 800, 250,300,300, null);
	}
	
	

	private void createHowToPlay(){
		
		setPreferredSize(new Dimension(width, height));
		setVisible(true);
		setLayout(new GridLayout(3,1,50,50));
		setBackground(new Color(44, 62, 80));  
		
		JPanel upSpace = new JPanel();
		upSpace.setPreferredSize(new Dimension(width,80));
		upSpace.setOpaque(false);
		
		

		JPanel upPanel = new JPanel();
		upPanel.setPreferredSize(new Dimension(width,80));
		upPanel.setOpaque(false);
		
		JLabel text = new JLabel () ;
		text.setText("<html><br/><br/><br/> CONTROLS </html>");
		text.setForeground(Color.WHITE);
		text.setFont(new Font("SansSerif", Font.BOLD, 28));
		text.setVerticalTextPosition(SwingConstants.TOP);
		
		JLabel txt = new JLabel () ;
		txt.setText("<html> <br/> &nbsp; &nbsp; 1- Select a piece by clicking on the cell it's on. <br/> &nbsp; &nbsp; 2- Use Num Pad to select the direction. <br/> &nbsp; &nbsp;  3- If you need to use A power,<br/> &nbsp; &nbsp; &nbsp; &nbsp; click on the power button <br/>  &nbsp; &nbsp; &nbsp; &nbsp; before selecting the hero piece . </html>");
		txt.setForeground(Color.WHITE);
		txt.setFont(new Font("SansSerif", Font.BOLD, 20));
		txt.setVerticalTextPosition(SwingConstants.TOP);
		
		
		
		
		
		JLabel img = new JLabel () ;
		img.setPreferredSize(new Dimension(width/2,80));
		
		upSpace.add(text);
		upPanel.add(txt);
		upPanel.add(img);
		
		
		
		
		
		
		
		
		
		
		
		JPanel downPanel = new JPanel();
		downPanel.setPreferredSize(new Dimension(width,80));
		downPanel.setOpaque(false);
		
		
		JButton back = new JButton();
		back.setPreferredSize(new Dimension(350,80));
		back.setFont(new Font("SansSerif", Font.BOLD, 24));
		back.setText("Back");
		back.setBackground(new Color(231, 76, 60));
		back.setForeground(Color.WHITE);
		downPanel.add(back);	
		

	
		

		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.getDisplay().ShowGameorMenu("menu"); 
			}
		});
		
			

		
		
		/////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////

		
		
		add(upSpace);
		add(upPanel);
		add(downPanel);
		
	}

}
