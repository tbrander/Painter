package view;

import java.awt.Color;
import java.awt.Graphics2D;

public class Line extends Shape {
	
	private double x2, y2;
	
	public Line(double x1, double y1,double x2, double y2, Color fillColor, int strokeThickness) {
		super(x1, y1, fillColor, strokeThickness);
		this.x2 = x2;
		this.y2 = y2;
		
	}


	@Override
	protected void drawStep(Graphics2D g) {
		g.drawLine((int) (int) super.getX(),  (int) super.getY(), (int) x2,(int) y2);
	}
	
	

}
