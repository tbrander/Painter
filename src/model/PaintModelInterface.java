package model;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.List;

import model.EnumShapes;



public interface PaintModelInterface {
	
	void addShape(Shape s);
	void notifyObservers();
	List<Shape> getShapeList();
	void resetDrawPanel();
	void selectShape(double x, double y);
	int getIndexOfSelectedShape();
	void updateShape(Color background, int lineThickness,boolean radioBtnOption);
	void undo();
	Shape makeShape(EnumShapes shape, double pressedX, double pressedY, int i, int j, Color color, int lineThickness, boolean isFilled);
	void delete();
	void redo();
	void saveToFile(String path);
	void loadFromFile(String path) throws FileNotFoundException;

}
