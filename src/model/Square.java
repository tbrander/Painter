package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Square extends Shape {
	

	private double width, height;
	
	public Square(double x, double y,double width, double height,  Color fillColor, int strokeThickness) {
		super(x, y, fillColor, strokeThickness);
		this.width = width;
		this.height = height;
	}


	@Override
	protected void drawStep(Graphics2D g) {
		g.drawRect((int)super.getX(),(int) super.getY(),(int) width,(int) height);
		
	}
	
	

}
