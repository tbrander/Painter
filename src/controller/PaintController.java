package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.EnumShapes;
import model.PaintModel;
import model.PaintModelInterface;
import model.Shape;
import view.PaintViewInterface;
import view.UserInterface;

public class PaintController implements Observer {

	private PaintViewInterface ui;
	private PaintModelInterface paintModel;
	private EnumShapes currentShapeFlag;
	private tools selectedTool;
	private double pressedX, pressedY;
	private List<ShapeMenuItems> listeners;

	public PaintController(UserInterface userInterface) {

		selectedTool = tools.NONE;
		currentShapeFlag = EnumShapes.CIRCLE;
		paintModel = new PaintModel(this); // :P
		ui = userInterface;

		listeners = new ArrayList<>();
		listeners.add(new BtnCircleListener());
		listeners.add(new BtnLineListener());
		listeners.add(new BtnRectangleListener());
		listeners.add(new BtnSquareListener());
		listeners.add(new BtnTriangleListener());
		listeners.add(new BtnArcListener());
		initializeShapeMenuItems();

		ui.addPanelListeners(new AddMouseListenerDrawPanel(),
				new ColorLinePanelListener());

		ui.addMenyItemListeners(new MenyItemNewDocListener(),
				new MenyItemSelectorListener(), new MenyItemUndoListener(),
				new LineThicknessSliderListener(),
				new MenyItemDeleteListener(), new MenyItemRedoListener(),
				new MenyItemSaveListener(), new MenyItemLoadListener());

	}

	private void initializeShapeMenuItems() {
		for (int i = 0; i < EnumShapes.values().length; ++i) {
			for (int j = 0; j < EnumShapes.values().length; ++j) {
				if (listeners.get(i).getType().equals(EnumShapes.values()[j])) {
					ui.addButtonAndSlideListeners(
							(ActionListener) listeners.get(i),
							EnumShapes.values()[j].toString());

				}
			}
		}
	}

	public enum tools {
		NONE, SELECTION;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		List<Shape> shapeList = (List<Shape>) arg;

		ui.getDrawPanel().paintAll(ui.getDrawPanel().getGraphics());

		for (Shape s : shapeList) {
			if (s != null)
				s.draw(ui.getDrawPanel().getGraphics());
		}

	}

	public void drawSelected() {
		for (Shape s : paintModel.getShapeList()) {
			s.draw(ui.getDrawPanel().getGraphics());
		}
		ui.getMntmDelete().setEnabled(true);

	}

	// *************** Btn listeners **************** //

	class BtnCircleListener extends ShapeMenuItems implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.CIRCLE;
			ui.setLblSelectedShape("Circle");
		}

		@Override
		public EnumShapes getType() {
			return EnumShapes.CIRCLE;
		}
	}

	class BtnSquareListener extends ShapeMenuItems implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.SQUARE;
			ui.setLblSelectedShape("Square");
		}

		public EnumShapes getType() {
			return EnumShapes.SQUARE;
		}
	}

	class BtnRectangleListener extends ShapeMenuItems implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.RECTANGLE;
			ui.setLblSelectedShape("Rectangle");
		}

		public EnumShapes getType() {
			return EnumShapes.RECTANGLE;
		}
	}

	class BtnArcListener extends ShapeMenuItems implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.ARC;
			ui.setLblSelectedShape("Arc");
		}

		public EnumShapes getType() {
			return EnumShapes.ARC;
		}
	}

	class BtnTriangleListener extends ShapeMenuItems implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.TRIANGLE;
			ui.setLblSelectedShape("Triangle");
		}

		public EnumShapes getType() {
			return EnumShapes.TRIANGLE;
		}
	}

	class BtnLineListener extends ShapeMenuItems implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.LINE;
			ui.setLblSelectedShape("Line");
		}

		public EnumShapes getType() {
			return EnumShapes.LINE;
		}
	}

	// **************** MenuItem listeners *************** //

	class MenyItemSelectorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (selectedTool != tools.SELECTION) {
				selectedTool = tools.SELECTION;
				ui.setLblSelectedTool("Marquee");
			} else {
				selectedTool = tools.NONE;
				ui.setLblSelectedTool("none");
			}
		}
	}

	class MenyItemNewDocListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			paintModel.resetDrawPanel();
		}
	}

	class MenyItemUndoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			paintModel.undo();
		}
	}

	class MenyItemRedoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			paintModel.redo();
		}
	}

	class MenyItemDeleteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			paintModel.delete();
			ui.getMntmDelete().setEnabled(false);
			selectedTool = tools.NONE;
			ui.setLblSelectedTool("none");
			ui.getRdBtnItemSelect().setSelected(false);;
		}
	}

	class MenyItemSaveListener implements ActionListener {

		private String filename, path;

		public void actionPerformed(ActionEvent e) {

			// present "Save" dialog:
			int rVal = ui.getJFileChooser().showSaveDialog((JFrame)ui);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				filename = ui.getJFileChooser().getSelectedFile().getName();
				if (!filename.contains(".draw")) {
					filename = filename.concat(".draw");
				}
				path = ui.getJFileChooser().getCurrentDirectory().toString()
						.concat("\\" + filename);
				paintModel.saveToFile(path);
			}
		}
	}

	class MenyItemLoadListener implements ActionListener {

		private String filename, path;

		public void actionPerformed(ActionEvent e) {
			// present "load" dialog:
			int rVal = ui.getJFileChooser().showOpenDialog((JFrame)ui);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				filename = (ui.getJFileChooser().getSelectedFile().getName());
				path = ui.getJFileChooser().getCurrentDirectory().toString()
						.concat("\\" + filename);
				try {
					paintModel.loadFromFile(path);
				} catch (FileNotFoundException e1) {
					ui.showErrorDialog("File not found!");
					e1.printStackTrace();
				}
			}
		}
	}

	// **************** Other listeners ******************* //

	class LineThicknessSliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			ui.getLblLineThickness().setText(
					"Line size: " + ((JSlider) e.getSource()).getValue());
		}

	}

	class ColorLinePanelListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			Color color = ui.getColorPanelPaint();

			Color newColor = JColorChooser.showDialog((JFrame)ui, "Choose line Color",
					color);
			if (newColor != null)
				ui.getColorPanel().setBackground(newColor);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class AddMouseListenerDrawPanel implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (selectedTool == tools.SELECTION) {
				boolean isFilled = ui.getRadioBtnOption();
				Color color = ui.getColorPanelPaint();
				int lineThickness = ui.getLineThickness();

				if (paintModel.getIndexOfSelectedShape() >= 0) {
					paintModel.updateShape(color, lineThickness, isFilled);
					ui.getRdBtnItemSelect().setSelected(false);
					ui.getMntmDelete().setEnabled(false);
					selectedTool = tools.NONE;
					ui.setLblSelectedTool("none");
				} else
					paintModel.selectShape(e.getX(), e.getY());
			} 
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			pressedX = e.getX();
			pressedY = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			if (selectedTool != tools.NONE)
				return;

			boolean isFilled = ui.getRadioBtnOption();
			Color color = ui.getColorPanelPaint();
			int lineThickness = ui.getLineThickness();

			Shape newShape = paintModel.makeShape(currentShapeFlag, pressedX,
					pressedY, e.getX(), e.getY(), color, lineThickness,
					isFilled);

			paintModel.addShape(newShape);
		}
	}

}
