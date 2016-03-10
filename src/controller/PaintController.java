package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JColorChooser;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Arc;
import model.Circle;
import model.EnumShapes;
import model.Line;
import model.PaintModel;
import model.PaintModelInterface;
import model.Rectangle;
import model.Shape;
import model.Square;
import model.Triangle;
import view.UserInterface;

public class PaintController implements Observer {

	private UserInterface ui;
	private PaintModelInterface paintModel;
	private Shape currentShape;
	private EnumShapes currentShapeFlag;
	private tools selectedTool;
	private double pressedX, pressedY, releasedX, releasedY;
	

	public PaintController(UserInterface userInterface) {

		selectedTool=tools.NONE;
		currentShapeFlag = EnumShapes.CIRCLE;
		paintModel = new PaintModel(this); // :P
		ui = userInterface;
		ui.addPanelListeners(new AddMouseListenerDrawPanel(),
				new ColorLinePanelListener());

		ui.addButtonAndSlideListeners(new BtnCircleListener(),
				new BtnLineListener(), new BtnRectangleListener(),
				new BtnSquareListener(), new BtnTriangleListener(),
				new LineThicknessSliderListener(), new BtnArcListener());

		ui.addMenyItemListeners(new MenyItemNewDocListener(), new MenyItemSelectorListener(), new MenyItemUndoListener());
		
	}
	
	public enum tools {
		 NONE, SELECTION,DELETE;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		List<Shape> shapeList = (List<Shape>) arg;
		
		System.out.println(shapeList.size());
		ui.getDrawPanel().paintAll(ui.getDrawPanel().getGraphics());
		
		for (Shape s : shapeList) {
			s.draw(ui.getDrawPanel().getGraphics());
		}
		
	}
	
	
	public void drawSelected() {
		for (Shape s : paintModel.getShapeList()) {
			s.draw(ui.getDrawPanel().getGraphics());
		}
		ui.getMntmDelete().setEnabled(true);
		
	}


	// ***************  Btn listeners **************** //
	
	class BtnCircleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.CIRCLE;
			ui.setLblSelectedShape("Circle");
		}
	}

	class BtnSquareListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.SQUARE;
			ui.setLblSelectedShape("Square");
		}
	}

	class BtnRectangleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.RECTANGLE;
			ui.setLblSelectedShape("Rectangle");
		}
	}
	
	class BtnArcListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.ARC;
			ui.setLblSelectedShape("Arc");
		}
	}

	class BtnTriangleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.TRIANGLE;
			ui.setLblSelectedShape("Triangle");
		}
	}

	class BtnLineListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = EnumShapes.LINE;
			ui.setLblSelectedShape("Line");
		}
	}
	
	// **************** MenuItem listeners *************** //

	class MenyItemSelectorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(selectedTool!=tools.SELECTION){
				selectedTool=tools.SELECTION;
				ui.setLblSelectedTool("Marquee");
			}else{
				selectedTool=tools.NONE;
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

			Color newColor = JColorChooser.showDialog(ui, "Choose line Color",
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
			
			
			
			if(selectedTool==tools.SELECTION){
				
				boolean isFilled = ui.getRadioBtnOption();
				Color color = ui.getColorPanelPaint();
				int lineThickness = ui.getLineThickness();
				
				if(paintModel.getIndexOfSelectedShape() >=0){
					paintModel.updateShape(color,lineThickness, isFilled);
					ui.getRdBtnItemSelect().setSelected(false);
					ui.getMntmDelete().setEnabled(false);
					selectedTool = tools.NONE;
					ui.setLblSelectedTool("none");
				}else
					paintModel.selectShape(e.getX(), e.getY());
				
			}else if(selectedTool==tools.DELETE){
				
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

			if(selectedTool != tools.NONE) return;
			
			boolean isFilled = ui.getRadioBtnOption();
			Color color = ui.getColorPanelPaint();
			int lineThickness = ui.getLineThickness();
			
			
			Shape newShape=paintModel.makeShape(currentShapeFlag,pressedX,pressedY,e.getX(), e.getY(), color,
					lineThickness, isFilled);
			
			paintModel.addShape(newShape);
		}
	}

	
}
