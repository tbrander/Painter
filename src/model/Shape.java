package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Shape {

	private double x, y;
	private Color color;
	private int strokeThickness;
	static int shapeCounter;
	private boolean isFilled;
	private String shapeLabel;

	public Shape(double x, double y, Color color, int strokeThickness,
			boolean isFilled) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.isFilled = isFilled;
		this.strokeThickness = strokeThickness;
	}

	final public void draw(Graphics g, boolean showShapeLabels) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.setStroke(new BasicStroke(strokeThickness));
		drawStep(g2);
		if (showShapeLabels) {

			g2.setPaint(Color.BLACK);
			g.drawString(getShapeLabel(), (int) (getX()), (int) (getY()));
		}
	}

	protected double getX() {
		return x;
	}

	protected double getY() {
		return y;
	}

	protected boolean isFilled() {
		return isFilled;
	}

	protected void setSelected() {
		int invers = 0xFFFFFF - color.getRGB();
		color = new Color(invers);
	}

	public String getShapeLabel() {
		return shapeLabel;
	}

	public void setShapeLabel(String shapeLabel) {
		this.shapeLabel = shapeLabel;
	}

	abstract protected java.awt.Shape getShape2D();

	abstract protected void drawStep(Graphics2D g);

}
