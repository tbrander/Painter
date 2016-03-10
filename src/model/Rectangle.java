package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;

public class Rectangle extends Shape {
	

	private double width, height;
	
	
	
	public Rectangle() {
		super();
	}


	public Rectangle(double x1, double y1, double x2, double y2,  Color color, int strokeThickness, boolean isFilled) {
		super(x1, y1,x2,y2, color, strokeThickness, isFilled);
		this.width = Math.sqrt(Math.pow(getX2()-getX1(), 2));
		this.height = Math.sqrt(Math.pow(getY2()-getY1(), 2));

	}


	@Override
	protected void drawStep(Graphics2D g) {
		
		if(super.isFilled())
			g.fillRect((int)getX1(),(int) getY1(),(int) width,(int) height);
		g.drawRect((int)getX1(),(int) getY1(),(int) width,(int) height);
		
	}

	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape rect = new Rectangle2D.Double(super.getX1(),super.getY1(),width,height);
		
		return rect;
	}
	
	@Override
	protected void updateShape(Color c, int lineThickness,
			boolean isFilled) {
		setShapeProperties(c, lineThickness, isFilled);

	}

	@Override
	protected Shape copyToHistory() {
		return new Rectangle(getX1(), getY1(), getX2(),getY2(), getColor(), getStrokeThickness(), isFilled());
	}
	
	@Override
	protected Shape clone() {
		return new Rectangle();
	}


	@Override
	protected void defineDimensions() {
		this.width = Math.sqrt(Math.pow(getX2()-getX1(), 2));
		this.height = Math.sqrt(Math.pow(getY2()-getY1(), 2));
		
	}


}
