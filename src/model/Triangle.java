package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Triangle extends Shape {
	

	
	public Triangle(double x, double y,  Color fillColor, int strokeThickness) {
		super(x, y, fillColor, strokeThickness);
	}


	@Override
	protected void drawStep(Graphics2D g) {
		  int [] xPoa = {(int)super.getX()-35,(int) super.getX(), (int)super.getX()+35}; 
          int [] yPoa = {(int) super.getY(), (int)super.getY()-35, (int) super.getY()}; 
		g.drawPolygon(xPoa, yPoa, 3);
		
	}
	
	

}
