package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D.Double;

public class Circle extends Shape {
	
	private double radius;
	
	

	public Circle() {
		super();
	}

	public Circle(double x1, double y1, double x2, double y2,  Color color, int strokeThickness, boolean isFilled) {
		super(x1, y1,x2,y2, color, strokeThickness, isFilled);
		
		this.radius = Math.sqrt(Math.pow(getX2()-getX1(), 2)+Math.pow(getY2()-getY1(), 2));

	}

	@Override
	protected void drawStep(Graphics2D g) {
		
		
		if(super.isFilled()){
			g.fillOval((int)getX1(), (int)getY1(), (int)radius, (int)radius);
		}
		g.drawOval((int)getX1(), (int)getY1(), (int)radius, (int)radius);
	}

	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape oval = new Ellipse2D.Double(getX1(),getY1(),radius,radius);
		
		return oval;
	}

	@Override
	protected void updateShape(Color c, int lineThickness,
			boolean isFilled) {
		setShapeProperties(c, lineThickness, isFilled);
	}
	
	@Override
	protected Shape copyToHistory() {
		return new Circle(getX1(), getY1(), getX2(),getY2(), getColor(), getStrokeThickness(), isFilled());
	}

	@Override
	protected Shape clone() {
		return new Circle();
	}

	@Override
	protected void defineDimensions() {
		this.radius = Math.sqrt(Math.pow(getX2()-getX1(), 2)+Math.pow(getY2()-getY1(), 2));
		
	}
	

}
