package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Line extends Shape {
	
	private double x2, y2;
	
	public Line(double x1, double y1,double x2, double y2, Color color, int strokeThickness) {
		super(x1, y1, color, strokeThickness, false);
		this.x2 = x2;
		this.y2 = y2;
		setShapeLabel("Line_"+(++shapeCounter));
	}

	@Override
	protected void drawStep(Graphics2D g) {
		g.drawLine((int) (int) super.getX(),  (int) super.getY(), (int) x2,(int) y2);
	}
	
	
	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape line = new Line2D.Double(super.getX(),super.getY(),x2,y2);
		return line;
	}


}
