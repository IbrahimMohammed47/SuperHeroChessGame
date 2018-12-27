package graphics;

import java.awt.Point;
import java.awt.Polygon;

public class TileT extends Polygon{
	
	Point p1 ;
	Point p2 ;
	Point p3 ;
	Point p4 ;
	Point imagePoint ;
	TileDraw tiledraw ;
	public static TileT[][] tiles;
	
	public TileT(int headx,int heady, TileDraw t) {
		p1 = new Point(headx-heady+45-158,(heady+headx)/2-65) ;             
		p2 = new Point(headx-heady+120-158,(heady+headx+75)/2-65) ;
		p3 = new Point(headx-heady+45-158,(heady+headx+150)/2-65) ;
		p4 = new Point(headx-heady-30-158,(heady+headx+75)/2-65) ;
		imagePoint = new Point(headx-heady-15-158,(heady+headx)/2-155) ;       //Modify
		addPoint(p1.x,p1.y);
		addPoint(p2.x,p2.y);
		addPoint(p3.x,p3.y);
		addPoint(p4.x,p4.y);
		tiledraw = t ;
		
	}
	public Point getimagePoint() {
		return imagePoint ;
	}
	public TileDraw getTileDraw() {
		return tiledraw ;
	}

}
