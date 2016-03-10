package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;

public class Rectangle extends Shape {
	

	private double width, height, pressedX, pressedY,x,y;
	
	public Rectangle(double pressedX, double pressedY,double x, double y,  Color color, int strokeThickness, boolean isFilled) {
		super(pressedX, pressedY, color, strokeThickness,isFilled);
		this.width = Math.sqrt(Math.pow(x-pressedX, 2));
		this.height = Math.sqrt(Math.pow(y-pressedY, 2));
		this.pressedX=pressedX;
		this.pressedY=pressedY;
		this.x=x;
		this.y=y;
	}


	@Override
	protected void drawStep(Graphics2D g) {
		
		if(super.isFilled())
			g.fillRect((int)pressedX,(int) pressedY,(int) width,(int) height);
		g.drawRect((int)pressedX,(int) pressedY,(int) width,(int) height);
		
	}

	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape rect = new Rectangle2D.Double(super.getX(),super.getY(),width,height);
		
		return rect;
	}
	
	@Override
	protected void updateShape(Color c, int lineThickness,
			boolean isFilled) {
		setShapeProperties(c, lineThickness, isFilled);

	}

	@Override
	protected Shape copyShape() {
		return new Rectangle(pressedX, pressedY, x,y, getColor(), getStrokeThickness(), isFilled());
	}


}
