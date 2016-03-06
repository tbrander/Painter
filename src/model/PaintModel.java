package model;

import java.util.ArrayList;
import java.util.List;

import controller.Observer;
import controller.PaintController;

public class PaintModel implements PaintModelInterface{

	
	private List<Shape> shapes;
	private List<Observer> observers;
	private Shape currentShape;
	
	public PaintModel(PaintController p) {
		
		shapes = new ArrayList<>();
		observers = new ArrayList<>();
		observers.add(p);
		
		
	}


	@Override
	public void addShape(Shape s) {
		shapes.add(s);
		currentShape=s;
		notifyObservers();
		
	}


	@Override
	public void addObserver(Observer o) {
		observers.add(o);	
	}


	@Override
	public void notifyObservers() {
		
		for(Observer o:observers){
			o.update();
		}
		
	}


	@Override
	public List<Shape> getShapeList() {
		
		return shapes;
	}


	@Override
	public Shape getCurrentShape() {
		return currentShape;
	}


	@Override
	public void resetDrawPanel() {
		shapes.clear();
		notifyObservers();
	}
	
	
	
	
}
