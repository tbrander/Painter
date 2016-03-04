package view;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Shape {
	

	private double radius;
	
	public Circle(double x, double y,double radius,  Color fillColor, int strokeThickness) {
		super(x, y, fillColor, strokeThickness);
		this.radius = radius;
	}


	@Override
	protected void drawStep(Graphics2D g) {
		g.drawOval((int)super.getX(),(int) super.getY(),(int) radius,(int) radius);
		
	}
	
	

}
