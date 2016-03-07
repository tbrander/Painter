package model;

import java.util.List;

import controller.Observer;

public interface PaintModelInterface {
	
	void addShape(Shape s);
	void notifyObservers();
	void addObserver(Observer o);
	List<Shape> getShapeList();
	Shape getCurrentShape();
	void resetDrawPanel();
	void selectShape(double x, double y);

}
