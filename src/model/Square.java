package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Square extends Shape {
	

	private double width, height;
	
	public Square(double x, double y,double width, double height,  Color color, int strokeThickness,boolean isFilled) {
		super(x, y, color, strokeThickness,isFilled);
		this.width = width;
		this.height = height;
		setShapeLabel("Square_"+(++shapeCounter));
	}


	@Override
	protected void drawStep(Graphics2D g) {
		if(super.isFilled())
			g.fillRect((int)super.getX(),(int) super.getY(),(int) width,(int) height);
		g.drawRect((int)super.getX(),(int) super.getY(),(int) width,(int) height);
		
	}


	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape rect = new Rectangle2D.Double(super.getX(),super.getY(),width,height);
		
		return rect;
	}



}
