package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Circle;
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
	private enumTools currentShapeFlag;
	private boolean activeSelection, showShapeLabels;
	private enumTools selectedTool;
	

	public PaintController(UserInterface userInterface) {

		currentShapeFlag = enumTools.CIRCLE;
		paintModel = new PaintModel(this);
		ui = userInterface;
		ui.addPanelListeners(new AddMouseListenerDrawPanel(),
				new ColorLinePanelListener());

		ui.addButtonAndSlideListeners(new BtnCircleListener(),
				new BtnLineListener(), new BtnRectangleListener(),
				new BtnSquareListener(), new BtnTriangleListener(),
				new LineThicknessSliderListener(),
				new ShapeSizeSliderListener(), new BtnSelectListener());

		ui.addMenyItemListeners(new MenyItemNewDocListener(), new MenyItemSelectorListener());
		
		ui.addComponentListeners(new ShapeLabelCheckBox());
	}
	
	
	public enum enumTools {
		 NONE,CIRCLE, SQUARE , RECTANGLE, TRIANGLE, LINE, SELECTION, MODIFY,DELETE;
	}
	
	

	@Override
	public void update() {

		if (paintModel.getShapeList().size() == 0) {
			ui.getDrawPanel().repaint();
		}
		
		for (Shape s : paintModel.getShapeList()) {
			s.draw(ui.getDrawPanel().getGraphics(), showShapeLabels);
		}

	}
	
	public void drawSelected() {
		for (Shape s : paintModel.getShapeList()) {
			s.draw(ui.getDrawPanel().getGraphics(), showShapeLabels);
		}
		
	}

	class AddMouseListenerDrawPanel implements MouseListener {

		int pressedX, pressedY, releasedX, releasedY;

		@SuppressWarnings("unchecked")
		@Override
		public void mouseClicked(MouseEvent e) {
			
			// Conditions for selected menu tools
			
			if(selectedTool==enumTools.SELECTION){
				paintModel.selectShape(e.getX(), e.getY());
				return;
			}else if(selectedTool==enumTools.MODIFY){
				
				return;
				
			}else if(selectedTool==enumTools.DELETE){
				
			}

			boolean isFilled = ui.getRadioBtnOption();
			Color color = ui.getColorPanelPaint();
			int shapeSize = ui.getShapeSize();

			System.out.println("x: " + e.getX() + " y: " + e.getY());
			switch (currentShapeFlag) {
			case CIRCLE:
				currentShape = new Circle(e.getX(), e.getY(), shapeSize, color,
						ui.getLineThickness(), isFilled);
				break;
			case SQUARE:
				currentShape = new Square(e.getX(), e.getY(), shapeSize,
						shapeSize, color, ui.getLineThickness(), isFilled);
				break;
			case RECTANGLE:
				currentShape = new Rectangle(e.getX(), e.getY(), shapeSize,
						shapeSize * 2, color, ui.getLineThickness(), isFilled);
				break;
			case TRIANGLE:
				currentShape = new Triangle(e.getX(), e.getY(), shapeSize,
						color, ui.getLineThickness(), isFilled);
				break;
			case SELECTION: // temp case, kanske ska bort sen.
				paintModel.selectShape(e.getX(), e.getY());
				return;
			default:
				System.out.println("def");
				return;
			}
			ui.getShapeComboBox().addItem(currentShape.getShapeLabel());
			paintModel.addShape(currentShape);
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
			if (currentShapeFlag == enumTools.LINE) {
				pressedX = e.getX();
				pressedY = e.getY();
			}

		}

		@SuppressWarnings("unchecked")
		@Override
		public void mouseReleased(MouseEvent e) {
			Color color = ui.getColorPanelPaint();
			if (currentShapeFlag == enumTools.LINE) {
				releasedX = e.getX();
				releasedY = e.getY();
				currentShape = new Line(pressedX, pressedY, releasedX,
						releasedY, color, ui.getLineThickness());
				ui.getShapeComboBox().addItem(currentShape.getShapeLabel());
				paintModel.addShape(currentShape);
			}

		}

	}

	class MenyItemNewDocListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			paintModel.resetDrawPanel();
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

	class BtnCircleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = enumTools.CIRCLE;
			ui.setLblSelectedTool("Circle");
		}
	}

	class BtnSquareListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = enumTools.SQUARE;
			ui.setLblSelectedTool("Square");
		}
	}

	class BtnRectangleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = enumTools.RECTANGLE;
			ui.setLblSelectedTool("Rectangle");
		}
	}

	class BtnTriangleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = enumTools.TRIANGLE;
			ui.setLblSelectedTool("Triangle");
		}
	}

	class BtnLineListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = enumTools.LINE;
			ui.setLblSelectedTool("Line");
		}
	}

	class BtnSelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = enumTools.SELECTION;
			ui.setLblSelectedTool("Selection");

		}
	}

	class LineThicknessSliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			ui.getLblLineThickness().setText(
					"Line size: " + ((JSlider) e.getSource()).getValue());
		}

	}

	class ShapeSizeSliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			ui.getlblShapeSize().setText(
					"Shape size: " + ((JSlider) e.getSource()).getValue());
		}

	}
	
	class ShapeComboBox implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			 JComboBox cb = (JComboBox)e.getSource();
			 String shape = (String)cb.getSelectedItem();
		}
	}

	class ShapeLabelCheckBox implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {

			showShapeLabels=!showShapeLabels;
			if(!showShapeLabels){
				ui.getDrawPanel().paintAll(ui.getDrawPanel().getGraphics());
			}
			update();
			
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

	class MenyItemSelectorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(selectedTool!=enumTools.SELECTION){
				selectedTool=enumTools.SELECTION;
			}else{
				selectedTool=enumTools.NONE;
			}
		}
	}

}
