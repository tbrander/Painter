package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circle extends Shape {
	

	private double radius;
	
	
	public Circle(double x, double y,double radius,  Color color, int strokeThickness, boolean isFilled) {
		super(x, y, color, strokeThickness, isFilled);
		this.radius = radius;
	}



	@Override
	protected void drawStep(Graphics2D g) {
		Ellipse2D oval= new Ellipse2D.Double(super.getX(), super.getY(), radius, radius);
		//g.drawOval((int)super.getX(),(int) super.getY(),(int) radius,(int) radius);
		if(super.isFilled()){
			g.fill(oval);
		}
		g.draw(oval);
	}
	
	

}
