package model.game;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DebugGraphics;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import graphics.MenuT;
import model.pieces.Piece;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public class LauncherT {
	
	
	public static boolean[] skls = {false,false,false,false,false,false} ;
	public static boolean powerused = false ;
	public static Piece pieceFromCemetry = null ;
	public static boolean get1fromCemetry = false ;
	public static boolean brk = false ;
	
	public static void main(String[]args) throws UnallowedMovementException, OccupiedCellException, WrongTurnException, InterruptedException {
	
		
		
		Game g = new Game(new Player("Player1"),new Player("Player2")) ;
		g.getDisplay().ShowGameorMenu("menu");                           //Showing Menu till giving names
		while(true) {
			if(!((MenuT) g.getDisplay().getMenuPanel()).isShowing()) {
				break ;
			}
			Thread.sleep(500);
		}
		g.getDisplay().ShowGameorMenu("game");                           //Showing Game 
		g.render();
		
		
		for(JButton x: g.getP1cemetryButs()) {
			x.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (skls[4] == true && get1fromCemetry==true ) {
						pieceFromCemetry = g.getPlayer1().getDeadCharacters().get(g.getP1cemetryButs().indexOf(x));
						x.setIcon(null);
						get1fromCemetry = false ;

					}
				}
			});
		}
		for(JButton x: g.getP2cemetryButs()) {
			x.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (skls[4] == true && get1fromCemetry==true) {
						pieceFromCemetry = g.getPlayer2().getDeadCharacters().get(g.getP2cemetryButs().indexOf(x));
						x.setIcon(null);
						get1fromCemetry = false ;

					}
				}
			});
		}
		g.getP1skillsButs()[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[0] = true ;
			}
		});
		g.getP2skillsButs()[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[0] = true ;
			}
		});
		g.getP1skillsButs()[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[1] = true ;
			}
		});
		g.getP2skillsButs()[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[1] = true ;
			}
		});
		g.getP1skillsButs()[2].addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[2] = true ;
			}
		});
		g.getP2skillsButs()[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[2] = true ;
			}
		});
		g.getP1skillsButs()[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[3] = true ;
			}
		});
		g.getP2skillsButs()[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[3] = true ;
			}
		});
		g.getP1skillsButs()[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[4] = true ;
			}
		});
		g.getP2skillsButs()[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[4] = true ;
			}
		});
		g.getP1skillsButs()[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[5] = true ;
			}
		});
		g.getP2skillsButs()[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				skls[5] = true ;
			}
		});
		
		
		
		
		
		while(true)
		{ //Click on powers before selecting the cells
			
			for(int i = 0 ; i<g.getCurrentCemetryButs().size() ; i++) {
				if(g.getCurrentPlayer()==g.getPlayer1()) {
					g.getP1cemetryButs().get(i).setEnabled(true);
					g.getP2cemetryButs().get(i).setEnabled(false);
				}else {
					g.getP1cemetryButs().get(i).setEnabled(false);
					g.getP2cemetryButs().get(i).setEnabled(true);
				}
			}
			for(int i = 0 ; i<g.getCurrentSkillsButs().length ; i++) {
				if(g.getCurrentPlayer()==g.getPlayer1()) {
					g.getP2skillsButs()[i].setEnabled(true);
					g.getP1skillsButs()[i].setEnabled(false);
				}else {
					g.getP2skillsButs()[i].setEnabled(false);
					g.getP1skillsButs()[i].setEnabled(true);
				}
				
			}

			
			Point Source = g.getSelectedCell();
			Thread.sleep(500);
			try
			{
				if (g.getCellAt(Source.x, Source.y).getPiece() instanceof Tech)
				{
					if(skls[0] == true ) {
						Point CurPiece = g.getSelectedCell();
						Piece p = g.getCellAt(CurPiece.x, CurPiece.y).getPiece() ;Thread.sleep(500);
						Point NewPiecePos = g.getSelectedCell();
						try {
							((Tech)g.getCellAt(Source.x, Source.y).getPiece()).usePower(null, p, NewPiecePos);
						} catch (PowerAlreadyUsedException | InvalidPowerTargetException e1) {
						}
						Thread.sleep(500);g.render();Thread.sleep(500);
						skls[0] = false ;
						powerused = true ;
					}else if(skls[1] == true ) {
						Point CurPiece = g.getSelectedCell();
						Piece p = g.getCellAt(CurPiece.x, CurPiece.y).getPiece() ;Thread.sleep(500);
						try {
							((Tech)g.getCellAt(Source.x, Source.y).getPiece()).usePower(null, p, null);
						} catch (PowerAlreadyUsedException | InvalidPowerTargetException e1) {
						}
						Thread.sleep(500);g.render();Thread.sleep(500);
						skls[1] = false ;
						powerused = true ;
					} else if(skls[2] == true ) {
						Point CurPiece = g.getSelectedCell();
						Piece p = g.getCellAt(CurPiece.x, CurPiece.y).getPiece() ;Thread.sleep(500);
						try {
							((Tech)g.getCellAt(Source.x, Source.y).getPiece()).usePower(null, p, null);
						} catch (PowerAlreadyUsedException | InvalidPowerTargetException e1) {
						}
						Thread.sleep(500);g.render();Thread.sleep(500);
						skls[2] = false ;
						powerused = true ;
					}
	
				}
				if (g.getCellAt(Source.x, Source.y).getPiece() instanceof Super)
				{
					if(skls[3] == true ) {
						Direction d = g.getDirection() ;
						try {
							((Super)g.getCellAt(Source.x, Source.y).getPiece()).usePower(d, null, null);
						} catch (PowerAlreadyUsedException | InvalidPowerDirectionException e1) {
						}
						Thread.sleep(500);g.render();Thread.sleep(500);
						skls[3] = false ;
						powerused = true ;
					}
				}
				if (g.getCellAt(Source.x, Source.y).getPiece() instanceof Medic)
				{											
					if(skls[4] == true ) {					
						get1fromCemetry = true ;           
						Direction d = g.getDirection() ;   
						try {								
							((Medic)g.getCellAt(Source.x, Source.y).getPiece()).usePower(d, pieceFromCemetry, null);
						} catch (PowerAlreadyUsedException | InvalidPowerDirectionException | InvalidPowerTargetException e1) {
						}
						Thread.sleep(500);g.render();Thread.sleep(500);
						skls[4] = false ;
						powerused = true ;
						pieceFromCemetry = null ;
						get1fromCemetry = false ;
					}

				}
				if (g.getCellAt(Source.x, Source.y).getPiece() instanceof Ranged)
				{
					if(skls[5] == true ) {
						Direction d = g.getDirection() ;
						try {
							((Ranged)g.getCellAt(Source.x, Source.y).getPiece()).usePower(d, null, null);
						} catch (PowerAlreadyUsedException | InvalidPowerDirectionException e1) {
						}
						Thread.sleep(500);g.render();Thread.sleep(500);
						skls[5] = false ;
						powerused = true ;
					}
				}
				
				
				
				Thread.sleep(50);		//500 -> 50
				
				if((powerused == false) || (g.getCellAt(Source.x, Source.y).getPiece() instanceof Tech)) {
					Direction x = g.getDirection() ;
					g.getCellAt(Source.x, Source.y).getPiece().move(x);	
				}else {
					powerused = false ;
				}
				g.render();Thread.sleep(500);g.render();
				if(brk)
					break ;
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		
		
		DebugGraphics dg = new DebugGraphics();
		DebugGraphics.setFlashColor(Color.YELLOW);
		DebugGraphics.setFlashCount(7);
		dg.setDebugOptions(DebugGraphics.FLASH_OPTION);
		g.getDisplay().getInfo().setDebugGraphicsOptions(dg.getDebugOptions());
		g.getDisplay().getInfo().setText("<html>"+"The Winner is : " + g.getCurrentPlayer().getName()+ "</html>");

		
		

	
		

	}
	
}
