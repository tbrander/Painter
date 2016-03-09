package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;

public class Arc extends Shape {
	

	private double width, height, pressedX, pressedY;
	
	public Arc(double pressedX, double pressedY,double x, double y,  Color color, int strokeThickness, boolean isFilled) {
		super(pressedX, pressedY, color, strokeThickness,isFilled);
		this.width = Math.sqrt(Math.pow(pressedX-x, 2));
		this.height = Math.sqrt(Math.pow(pressedY-y, 2));
		this.pressedX=pressedX;
		this.pressedY=pressedY;
	}


	@Override
	protected void drawStep(Graphics2D g) {
		
		if(super.isFilled())
			g.fillArc((int)pressedX,(int) pressedY,(int) width,(int) height, -90, 180);
		g.drawArc((int)pressedX,(int) pressedY,(int) width,(int) height, -90, 180);
		
	}

	@Override
	protected java.awt.Shape getShape2D() {

		java.awt.Shape arc = new Arc2D.Double(getX(), getY(), width, height,-90,180, Arc2D.CHORD);
		
		return arc;
	}
	
	@Override
	protected void updateShape(Color c, int lineThickness,
			boolean isFilled) {
		setShapeProperties(c, lineThickness, isFilled);

	}




}
