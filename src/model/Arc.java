package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;

public class Arc extends Shape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4492176253773548476L;
	private double width, height;

	public Arc() {
		super();
	}

	public Arc(double x1, double y1, double x2, double y2, Color color,
			int strokeThickness, boolean isFilled) {
		super(x1, y1, x2, y2, color, strokeThickness, isFilled);
		this.width = Math.sqrt(Math.pow(x1 - x2, 2));
		this.height = Math.sqrt(Math.pow(y1 - y2, 2));
	}

	@Override
	protected void drawStep(Graphics2D g) {

		if (super.isFilled())
			g.fillArc((int) getX1(), (int) getY1(), (int) width, (int) height,
					-90, 180);
		g.drawArc((int) getX1(), (int) getY1(), (int) width, (int) height, -90,
				180);

	}

	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape arc = new Arc2D.Double(getX1(), getY1(), width, height,
				-90, 180, Arc2D.CHORD);

		return arc;
	}

	@Override
	protected void updateShape(Color c, int lineThickness, boolean isFilled) {
		setShapeProperties(c, lineThickness, isFilled);

	}

	@Override
	protected Shape copyToHistory() {
		return new Arc(getX1(), getY1(), getX2(), getY2(), getColor(),
				getStrokeThickness(), isFilled());
	}

	@Override
	protected Shape clone() {
		return new Arc();
	}

	@Override
	protected void defineDimensions() {
		this.width = Math.sqrt(Math.pow(getX1() - getX2(), 2));
		this.height = Math.sqrt(Math.pow(getY1() - getY2(), 2));

	}

}
