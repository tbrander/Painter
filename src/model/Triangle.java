package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Triangle extends Shape {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9041672334055208067L;
	private int[] xPoa, yPoa;

	public Triangle() {
		super();
	}

	public Triangle(double x1, double y1, double x2, double y2, Color color,
			int strokeThickness, boolean isFilled) {
		super(x1, y1, x2, y2, color, strokeThickness, isFilled);

	}

	@Override
	protected void drawStep(Graphics2D g) {
		int middleX = (int) (getX2() - (Math.abs(getX1() - getX2()) / 2)), middleY = (int) (getY2() - (Math
				.abs(getX1() - getY2()) / 2));

		int[] x = { (int) (getX2()), (int) getX1(), middleX };
		int[] y = { (int) getY2(), (int) getY1(), (int) middleY };

		xPoa = x;
		yPoa = y;

		if (super.isFilled())
			g.fillPolygon(xPoa, yPoa, 3);
		g.drawPolygon(xPoa, yPoa, 3);
	}

	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape poly = new Polygon(xPoa, yPoa, 3);

		return poly;
	}

	@Override
	protected void updateShape(Color c, int lineThickness, boolean isFilled) {
		setShapeProperties(c, lineThickness, isFilled);
	}

	@Override
	protected Shape copyToHistory() {

		return new Triangle(getX1(), getY1(), getX2(), getY2(), getColor(),
				getStrokeThickness(), isFilled());
	}

	@Override
	protected Shape clone() {
		return new Triangle();
	}

	@Override
	protected void defineDimensions() {
		// TODO Auto-generated method stub

	}

}
