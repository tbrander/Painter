package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Triangle extends Shape {
	
	private int size;

	public Triangle(double x, double y,int size, Color color, int strokeThickness,
			boolean isFilled) {
		super(x, y, color, strokeThickness, isFilled);
		this.size=size;
	}

	@Override
	protected void drawStep(Graphics2D g) {
		int[] xPoa = { (int) super.getX() - size, (int) super.getX(),
				(int) super.getX() + size };
		int[] yPoa = { (int) super.getY(), (int) super.getY() - size,
				(int) super.getY() };

		if (super.isFilled())
			g.fillPolygon(xPoa, yPoa, 3);
		g.drawPolygon(xPoa, yPoa, 3);

	}

}
