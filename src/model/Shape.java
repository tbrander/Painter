package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Shape {
	
	private double x,y;
	private Color color;
	private int strokeThickness;
	private boolean isFilled;
	

	public Shape(double x, double y, Color color, int strokeThickness, boolean isFilled) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.isFilled=isFilled;
		this.strokeThickness  = strokeThickness;
	}
	
	
	final public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	
		g2.setColor(color);
		g2.setStroke(new BasicStroke(strokeThickness));
		drawStep(g2);
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public boolean isFilled() {
		return isFilled;
	}


	abstract protected void drawStep(Graphics2D g);

}
