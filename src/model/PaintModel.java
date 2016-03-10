package model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.PaintController;

public class PaintModel extends Observable implements PaintModelInterface  {

	private List<Shape> shapes;
	private List<Observer> observers;

	private Shape selectedShape;
	private Undo undo;
	private boolean activeSelection;
	private int indexOfselectedShape;

	// ********** Observers ****************
	private PaintController paintController;
	// *************************************
	
	
	
	public PaintModel(PaintController paintController) {

		undo = new Undo();
		indexOfselectedShape=-1;
		this.paintController=paintController;
		shapes = new ArrayList<>();
		
		observers = new ArrayList<>();
		observers.add(paintController);
		/*
		addObserver(paintController);
		addObserver(undo);
		*/
		
	}

	@Override
	public void addShape(Shape s) {
		shapes.add(s);
		undo.addToHistory(null, shapes.size()-1);
		setChanged();
		notifyObservers();
	}

	@Override
	public void notifyObservers() {

		for(Observer o : observers){
			o.update(null, shapes);
		}
		
	}

	@Override
	public List<Shape> getShapeList() {

		return shapes;
	}

	@Override
	public void resetDrawPanel() {
		shapes.clear();
		undo = new Undo();
		indexOfselectedShape=-1;
		setChanged();
		notifyObservers();
	}

	@Override
	public void selectShape(double x, double y) {

		if (!activeSelection) {
			indexOfselectedShape=shapes.size() - 1;
			for (; indexOfselectedShape >= 0; --indexOfselectedShape) {
				if (shapes.get(indexOfselectedShape).getShape2D()
						.contains(new Point2D.Double(x, y))) {
					
					undo.addToHistory(shapes.get(indexOfselectedShape).copyShape(), indexOfselectedShape);
					
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
		
		shapes.get(indexOfselectedShape).updateShape(c, lineThickness, isFilled);
		indexOfselectedShape=-1;
		selectedShape=null;
		activeSelection=false;
		// 
		setChanged();
		notifyObservers();
	}
	
	public void undo(){
		try{
		UndoInterface u=undo.undoChange();
		shapes.remove(u.getIndex());
		if(u.undo()!=null)
			shapes.add(u.getIndex(), u.undo());
		
		setChanged();
		notifyObservers();
		
		}catch(EmptyStackException e){
			System.out.println("Error: empty stack!");
		}
		
	}
	
}
