package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.Serializable;

public abstract class Shape implements Cloneable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5565048844507033259L;
	private double x1, y1,x2,y2;
	private Color color;
	private int strokeThickness;
	private boolean isFilled;
	
	

	public Shape() {
		super();
	}

	public Shape(double x1, double y1,double x2, double y2, Color color, int strokeThickness,
			boolean isFilled) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.isFilled = isFilled;
		this.strokeThickness = strokeThickness;
	}

	final public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.setStroke(new BasicStroke(strokeThickness));
		 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		            RenderingHints.VALUE_ANTIALIAS_ON);
		drawStep(g2);
	}

	protected double getX1() {
		return x1;
	}

	protected double getY1() {
		return y1;
	}
	
	protected double getX2() {
		return x2;
	}

	protected double getY2() {
		return y2;
	}

	protected boolean isFilled() {
		return isFilled;
	}

	protected void setSelected() {
		int invers = 0xFFFFFF - color.getRGB();
		color = new Color(invers);
	}
	
	protected void setShapeProperties(Color c, int lineThickness, boolean isFilled){
		this.color=c;
		this.strokeThickness=lineThickness;
		this.isFilled=isFilled;
	}

	protected Color getColor() {
		return color;
	}
	
	

	protected int getStrokeThickness() {
		return strokeThickness;
	}

	protected void setNewShapeAttributes(double x1, double y1, double x2, double y2,  Color color, int lineThickness, boolean isFilled){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.isFilled = isFilled;
		this.strokeThickness = lineThickness;
	}
	
	abstract protected void defineDimensions();
	
	abstract protected Shape clone();
	
	abstract protected Shape copyToHistory();
	
	abstract protected void updateShape(Color c, int lineThickness, boolean isFilled);
	
	abstract protected java.awt.Shape getShape2D();

	abstract protected void drawStep(Graphics2D g);

}
