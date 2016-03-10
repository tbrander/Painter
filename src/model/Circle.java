package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D.Double;

public class Circle extends Shape {
	
	private double radius, pressedX, pressedY,x,y;
	
	public Circle(double pressedX, double pressedY, double x, double y,  Color color, int strokeThickness, boolean isFilled) {
		super(pressedX, pressedY, color, strokeThickness, isFilled);
		this.pressedX=pressedX;
		this.pressedY=pressedY;
		this.radius = Math.sqrt(Math.pow(x-pressedX, 2)+Math.pow(y-pressedY, 2));
		this.x=x;
		this.y=y;
	}

	@Override
	protected void drawStep(Graphics2D g) {
		
		
		if(super.isFilled()){
			g.fillOval((int)pressedX, (int)pressedY, (int)radius, (int)radius);
		}
		g.drawOval((int)pressedX, (int)pressedY, (int)radius, (int)radius);
	}

	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape oval = new Ellipse2D.Double(getX(),getY(),radius,radius);
		
		return oval;
	}

	@Override
	protected void updateShape(Color c, int lineThickness,
			boolean isFilled) {
		setShapeProperties(c, lineThickness, isFilled);
	}

	@Override
	protected Shape copyShape() {
		return new Circle(pressedX, pressedY, x,y, getColor(), getStrokeThickness(), isFilled());
	}
	

}
