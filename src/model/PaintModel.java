package model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import controller.Observer;
import controller.PaintController;

public class PaintModel implements PaintModelInterface {

	private List<Shape> shapes;
	private List<Observer> observers;
	private Shape selectedShape;
	private boolean activeSelection;
	private PaintController paintController;
	private int indexOfselectedShape;

	public PaintModel(PaintController paintController) {

		indexOfselectedShape=-1;
		this.paintController=paintController;
		shapes = new ArrayList<>();
		observers = new ArrayList<>();
		observers.add(paintController);
	}

	@Override
	public void addShape(Shape s) {
		shapes.add(s);
		notifyObservers();
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers() {

		for (Observer o : observers) {
			o.update();
		}
	}

	@Override
	public List<Shape> getShapeList() {

		return shapes;
	}



	@Override
	public void resetDrawPanel() {
		shapes.clear();
		notifyObservers();
	}

	@Override
	public void selectShape(double x, double y) {

		
		
		if (!activeSelection) {
			indexOfselectedShape=shapes.size() - 1;
			for (; indexOfselectedShape >= 0; --indexOfselectedShape) {
				if (shapes.get(indexOfselectedShape).getShape2D()
						.contains(new Point2D.Double(x, y))) {
					
					System.out.println(shapes.get(indexOfselectedShape).getShape2D()
							.contains(new Point2D.Double(x, y)));
					
					selectedShape = shapes.get(indexOfselectedShape);
					selectedShape.setSelected();
					activeSelection=true;
					paintController.drawSelected();
					break;
				}
			}
			
			
		}
	}

	@Override
	public int getIndexOfSelectedShape() {
		
		return indexOfselectedShape;
	}

	@Override
	public void updateShape(Color c, int lineThickness,
			boolean isFilled) {
		
		// skapa undo-objekt och spara undan lista eller 
		shapes.get(indexOfselectedShape).updateShape(c, lineThickness, isFilled);
		indexOfselectedShape=-1;
		selectedShape=null;
		activeSelection=false;
		// 
		notifyObservers();
	}

}
