package model;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;

import controller.Observer;

public interface PaintModelInterface {
	
	void addShape(Shape s);
	void notifyObservers();
	void addObserver(Observer o);
	List<Shape> getShapeList();
	void resetDrawPanel();
	void selectShape(double x, double y);
	int getIndexOfSelectedShape();
	void updateShape(Color background, int lineThickness,boolean radioBtnOption);

}
