package model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.PaintController;

public class PaintModel extends Observable implements PaintModelInterface {

	private List<Shape> shapes;
	private List<Observer> observers;

	private Shape selectedShape;
	private Undo undo;
	private Redo redo;
	private boolean activeSelection;
	private int indexOfselectedShape;
	private ShapeFactory avaliableShapes;

	// ********** Observers ****************
	private PaintController paintController;

	// *************************************

	public PaintModel(PaintController paintController) {

		avaliableShapes = new ShapeFactory();
		undo = new Undo();
		redo = new Redo();
		indexOfselectedShape = -1;
		this.paintController = paintController;
		shapes = new ArrayList<>();

		observers = new ArrayList<>();
		observers.add(paintController);
		/*
		 * addObserver(paintController); addObserver(undo);
		 */

	}

	@Override
	public void addShape(Shape s) {
		shapes.add(s);
		undo.addToHistory(null, shapes.size() - 1);
		redo.addToHistory(s.copyToHistory(), shapes.size() - 1);
		setChanged();
		notifyObservers();
	}

	@Override
	public void notifyObservers() {

		for (Observer o : observers) {
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
		indexOfselectedShape = -1;
		setChanged();
		notifyObservers();
	}

	@Override
	public void selectShape(double x, double y) {

		if (!activeSelection) {
			indexOfselectedShape = shapes.size() - 1;
			for (; indexOfselectedShape >= 0; --indexOfselectedShape) {
				if (shapes.get(indexOfselectedShape).getShape2D()
						.contains(new Point2D.Double(x, y))) {

					undo.addToHistory(shapes.get(indexOfselectedShape)
							.copyToHistory(), indexOfselectedShape);

					selectedShape = shapes.get(indexOfselectedShape);
					selectedShape.setSelected();
					activeSelection = true;
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
	public void updateShape(Color c, int lineThickness, boolean isFilled) {

		shapes.get(indexOfselectedShape)
				.updateShape(c, lineThickness, isFilled);
		redo.addToHistory(shapes.get(indexOfselectedShape).copyToHistory(),
				indexOfselectedShape);
		indexOfselectedShape = -1;
		selectedShape = null;
		activeSelection = false;
		//
		setChanged();
		notifyObservers();
	}

	public void undo() {
		try {
			UndoInterface u = undo.undoChange();

			if(u.getIndex() <= shapes.size()-1)
				shapes.remove(u.getIndex());
			if (u.undo() != null)
				shapes.add(u.getIndex(), u.undo());

			setChanged();
			notifyObservers();

		} catch (EmptyStackException e) {
			System.out.println("Error: empty stack!");
		}

	}

	@Override
	public Shape makeShape(EnumShapes s, double x1, double y1, int x2, int y2,
			Color color, int lineThickness, boolean isFilled) {

		return avaliableShapes.makeShape(s, x1, y1, x2, y2, color,
				lineThickness, isFilled);
	}

	@Override
	public void delete() {

		undo.addToHistory(shapes.get(indexOfselectedShape).copyToHistory(),
				indexOfselectedShape);
		shapes.remove(indexOfselectedShape);
		//shapes.add(indexOfselectedShape, null);
		indexOfselectedShape = -1;
		selectedShape = null;
		activeSelection = false;
		setChanged();
		notifyObservers();
	}

	@Override
	public void redo() {
		try {
			RedoInterface u = redo.redoChange();

			shapes.add(u.redo());

			setChanged();
			notifyObservers();

		} catch (EmptyStackException e) {
			System.out.println("Error: empty stack!");
		}
	}

	@Override
	public void saveToFile(String toPath) {

		SaveLoadDrawings.saveToFile(shapes, toPath);

	}

	@Override
	public void loadFromFile(String fromPath) throws FileNotFoundException {
		shapes.clear();
		shapes.addAll(SaveLoadDrawings.loadFromFile(fromPath));

		setChanged();
		notifyObservers();

	}

}
