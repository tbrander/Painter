package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D.Double;

public class Circle extends Shape {
	
	private double radius;
	
	public Circle(double x, double y,double radius,  Color color, int strokeThickness, boolean isFilled) {
		super(x, y, color, strokeThickness, isFilled);
		this.radius = radius;
		setShapeLabel("Circle_"+(++shapeCounter));
	}

	@Override
	protected void drawStep(Graphics2D g) {
		
		
		if(super.isFilled()){
			g.fillOval((int)getX(), (int)getY(), (int)radius, (int)radius);
		}
		g.drawOval((int)getX(), (int)getY(), (int)radius, (int)radius);
	}

	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape oval = new Ellipse2D.Double(getX(),getY(),radius,radius);
		
		return oval;
	}
	

}
