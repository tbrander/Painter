package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

public class Triangle extends Shape {
	
	private int[] xPoa, yPoa;
	private double pressedX,pressedY;
	
	public Triangle(double pressedX, double pressedY,double x, double y, Color color, int strokeThickness,
			boolean isFilled) {
		super(x, y, color, strokeThickness, isFilled);
		this.pressedX=pressedX;
		this.pressedY=pressedY;
	}

	@Override
	protected void drawStep(Graphics2D g) {
		int middleX = (int) (getX()-(Math.abs(pressedX-getX())/2)), middleY=(int) (getY()-(Math.abs(pressedX-getY())/2));
		
		int[] x = { (int) (getX()), (int) pressedX, middleX };
		int[] y = { (int) getY(), (int) pressedY, (int)middleY };
		
		xPoa = x;
		yPoa = y;
		
  
		
		if (super.isFilled())
			g.fillPolygon(xPoa, yPoa, 3);
		g.drawPolygon(xPoa, yPoa, 3);
	}
	
	
	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape poly = new Polygon(xPoa, yPoa, 3);
		
		return poly;
	}

	@Override
	protected void updateShape(Color c, int lineThickness,
			boolean isFilled) {
		setShapeProperties(c, lineThickness, isFilled);
	}

}
