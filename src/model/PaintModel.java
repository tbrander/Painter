package model;

import java.util.ArrayList;

public class PaintModel implements PaintModelInterface{

	
	private ArrayList<Shape> shapes = new ArrayList<>();

	
	public PaintModel() {
		// TODO Auto-generated constructor stub
	}


	public void addShape(Shape s) {
		shapes.add(s);
		//notify observers
		
	}
	
	
}
