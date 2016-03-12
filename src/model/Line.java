package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Line extends Shape {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4577707906447731440L;

	public Line() {
		super();
	}

	public Line(double x1, double y1, double x2, double y2,  Color color, int strokeThickness, boolean isFilled) {
		super(x1, y1,x2,y2, color, strokeThickness, isFilled);
	
	}

	@Override
	protected void drawStep(Graphics2D g) {
		g.drawLine((int) (int) super.getX1(),  (int) super.getY1(), (int) getX2(),(int) getY2());
	}
	
	
	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape line = new Line2D.Double(super.getX2(),super.getY2(),getX2(),getY2());
		return line;
	}

	@Override
	protected void updateShape(Color c, int lineThickness,
			boolean isFilled) {
		setShapeProperties(c, lineThickness, isFilled);

	}
	
	@Override
	protected Shape copyToHistory() {
		return new Line(getX1(), getY1(), getX2(),getY2(), getColor(), getStrokeThickness(), false);
	}
	
	@Override
	protected Shape clone() {
		return new Line();
	}

	@Override
	protected void defineDimensions() {
		// TODO Auto-generated method stub
		
	}
	
}
