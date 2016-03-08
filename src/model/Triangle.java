package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

public class Triangle extends Shape {
	
	private int size;
	private int[] xPoa, yPoa;
	
	public Triangle(double x, double y,int size, Color color, int strokeThickness,
			boolean isFilled) {
		super(x, y, color, strokeThickness, isFilled);
		this.size=size;
		setShapeLabel("Triangle_"+(++shapeCounter));
	}

	@Override
	protected void drawStep(Graphics2D g) {
		int[] x = { (int) super.getX() - size, (int) super.getX(), (int) super.getX() + size };
		int[] y = { (int) super.getY(), (int) super.getY() - size, (int) super.getY() };
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

	

}
