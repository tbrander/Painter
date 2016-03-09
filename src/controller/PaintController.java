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

import model.Arc;
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
	private tools currentShapeFlag;
	private tools selectedTool;
	private double pressedX, pressedY, releasedX, releasedY;
	

	public PaintController(UserInterface userInterface) {

		selectedTool=tools.NONE;
		currentShapeFlag = tools.NONE;
		paintModel = new PaintModel(this);
		ui = userInterface;
		ui.addPanelListeners(new AddMouseListenerDrawPanel(),
				new ColorLinePanelListener());

		ui.addButtonAndSlideListeners(new BtnCircleListener(),
				new BtnLineListener(), new BtnRectangleListener(),
				new BtnSquareListener(), new BtnTriangleListener(),
				new LineThicknessSliderListener(), new BtnArcListener());

		ui.addMenyItemListeners(new MenyItemNewDocListener(), new MenyItemSelectorListener());
		
	}
	
	
	public enum tools {
		 NONE, CIRCLE, SQUARE , RECTANGLE, TRIANGLE, LINE, ARC, SELECTION, MODIFY,DELETE;
	}
	
	

	@Override
	public void update() {

		ui.getDrawPanel().paintAll(ui.getDrawPanel().getGraphics());
		
		for (Shape s : paintModel.getShapeList()) {
			s.draw(ui.getDrawPanel().getGraphics());
		}
	}
	
	public void drawSelected() {
		for (Shape s : paintModel.getShapeList()) {
			s.draw(ui.getDrawPanel().getGraphics());
		}
		
	}

	class AddMouseListenerDrawPanel implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			boolean isFilled = ui.getRadioBtnOption();
			Color color = ui.getColorPanelPaint();
			int lineThickness = ui.getLineThickness();
			
			// Conditions for selected menu tools
			
			if(selectedTool==tools.SELECTION){
				if(paintModel.getIndexOfSelectedShape() >=0){
					paintModel.updateShape(color,lineThickness, isFilled);
					
				}else
					paintModel.selectShape(e.getX(), e.getY());
				return;
			}else if(selectedTool==tools.MODIFY){
				
				return;
				
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
			
			
			switch (currentShapeFlag) {
			case CIRCLE:
				currentShape = new Circle(pressedX,pressedY,e.getX(), e.getY(), color,
						lineThickness, isFilled);
				break;
			case SQUARE:
				currentShape = new Square(pressedX,pressedY,e.getX(), e.getY(),
						color, lineThickness, isFilled);
				break;
			case RECTANGLE:
				currentShape = new Rectangle(pressedX,pressedY,e.getX(), e.getY(),
						color, lineThickness, isFilled);
				break;
			case TRIANGLE:
				
				currentShape = new Triangle(pressedX, pressedY, e.getX(), e.getY(),
						color, lineThickness, isFilled);
				break;
			case LINE:
				currentShape = new Line(pressedX, pressedY, releasedX,
						releasedY, color, lineThickness);
				break;
			
			case ARC:
				currentShape = new Arc(pressedX, pressedY, e.getX(), e.getY(), color, lineThickness,isFilled);
				break;
			case SELECTION: // temp case, kanske ska bort sen.
				paintModel.selectShape(e.getX(), e.getY());
				return;
			default:
				System.out.println("def");
				return;
			}
			System.out.println("flag: "+selectedTool);
			paintModel.addShape(currentShape);
			
			

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
			currentShapeFlag = tools.CIRCLE;
			ui.setLblSelectedShape("Circle");
		}
	}

	class BtnSquareListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = tools.SQUARE;
			ui.setLblSelectedShape("Square");
		}
	}

	class BtnRectangleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = tools.RECTANGLE;
			ui.setLblSelectedShape("Rectangle");
		}
	}
	
	class BtnArcListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = tools.ARC;
			ui.setLblSelectedShape("Arc");
		}
	}

	class BtnTriangleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = tools.TRIANGLE;
			ui.setLblSelectedShape("Triangle");
		}
	}

	class BtnLineListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentShapeFlag = tools.LINE;
			ui.setLblSelectedShape("Line");
		}
	}

	class LineThicknessSliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			ui.getLblLineThickness().setText(
					"Line size: " + ((JSlider) e.getSource()).getValue());
		}

	}

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

}
