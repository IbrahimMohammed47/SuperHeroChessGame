package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

import javax.swing.JPanel;

public class TileDraw extends JPanel {
	public static boolean colorFlag = false;
	public static int ctr = 0 ;
	
    final static BasicStroke border =new BasicStroke(4.8f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f);
	TileT t ;
	int x ;
	int y ;
	public TileDraw(Graphics g ,int x , int y) {
		this.x = x ;
		this.y = y ;
		this.setBackground(Color.RED);
		paintComponent(g) ;
		
    }
	
	
	public void paintComponent (Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g ;
		t = new TileT(x,y,this);
		if ( colorFlag == false ) {
			g2.setPaint(new Color(232, 65, 24));
			colorFlag = !colorFlag ; 
		}else {
			g2.setPaint(new Color(6, 82, 221));
			colorFlag = !colorFlag ;
		}
		ctr++ ;
		if (ctr%6==0) {
			colorFlag = !colorFlag;
		}

	    g2.setStroke(border);
		
		
        g2.fillPolygon(t);
        g2.drawPolygon(t);   //border of tile
        repaint();
        
    }

	
	
	public TileT getTile() {
		return t ;
	}


}
