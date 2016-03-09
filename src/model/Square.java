package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Square extends Shape {
	

	private double width, height, pressedX, pressedY;
	
	public Square(double pressedX, double pressedY,double x, double y, Color color, int strokeThickness,boolean isFilled) {
		super(pressedX, pressedY, color, strokeThickness,isFilled);
		this.width =  Math.min(Math.sqrt(Math.pow(x-pressedX, 2)), Math.sqrt(Math.pow(y-pressedY, 2)));
		this.height =width;
		this.pressedX=pressedX;
		this.pressedY=pressedY;
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

}
