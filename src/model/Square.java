package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Square extends Shape {
	

	private double width, height;
	
	
	
	public Square() {
		super();
	}


	public Square(double x1, double y1, double x2, double y2,  Color color, int strokeThickness, boolean isFilled) {
		super(x1, y1,x2,y2, color, strokeThickness, isFilled);
		this.width =  Math.min(Math.sqrt(Math.pow(x2-x1, 2)), Math.sqrt(Math.pow(y2-y1, 2)));
		this.height =width;

	}


	@Override
	protected void drawStep(Graphics2D g) {
		if(super.isFilled())
			g.fillRect((int)getX1(),(int) getY1(),(int) width,(int) height);
		g.drawRect((int)getX1(),(int) getY1(),(int) width,(int) height);
		
	}


	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape rect = new Rectangle2D.Double(super.getX2(),super.getY2(),width,height);
		
		return rect;
	}

	@Override
	protected void updateShape(Color c, int lineThickness,
			boolean isFilled) {
		setShapeProperties(c, lineThickness, isFilled);

	}

	
	@Override
	protected Shape copyToHistory() {
		return new Square(getX1(), getY1(), getX2(),getY2(), getColor(), getStrokeThickness(), isFilled());
	}
	
	@Override
	protected Shape clone() {
		return new Square();
	}


	@Override
	protected void defineDimensions() {
		this.width =  Math.min(Math.sqrt(Math.pow(getX2()-getX1(), 2)), Math.sqrt(Math.pow(getY2()-getY1(), 2)));
		this.height =width;
		
	}
}
