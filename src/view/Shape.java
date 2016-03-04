package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Shape {
	
	private double x,y;
	private Color fillColor;
	private int strokeThickness;
	

	public Shape(double x, double y, Color fillColor, int strokeThickness) {
		super();
		this.x = x;
		this.y = y;
		this.fillColor = fillColor;
		this.strokeThickness  = strokeThickness;
	}
	
	final public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	
		g2.setColor(fillColor);
		g2.setStroke(new BasicStroke(strokeThickness));
		drawStep(g2);
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	abstract protected void drawStep(Graphics2D g);

}
