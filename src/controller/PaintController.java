package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
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

public class PaintController implements Observer{

	private UserInterface ui;
	private PaintModelInterface paintModel;
	private Shape currentShape;
	private int currentFlagShape;

	public PaintController(UserInterface userInterface) {

		paintModel = new PaintModel(this);
		ui = userInterface;
		ui.addPanelListeners(new AddMouseListenerDrawPanel(), new ColorLinePanelListener());
		ui.addButtonAndSlideListeners(new BtnCircleListener(),
				new BtnLineListener(), new BtnRectangleListener(),
				new BtnSquareListener(), new BtnTriangleListener(), new BtnResetListener(), new LineThicknessSliderListener(), new ShapeSizeSliderListener());
	}
	
	@Override
	public void update() {
		
		if(paintModel.getShapeList().size()==0)
			ui.getDrawPanel().repaint();
		
		for(Shape s : paintModel.getShapeList()){
			s.draw(ui.getDrawPanel().getGraphics());
		}
		
		//paintModel.getShapeList().get(paintModel.getShapeList().size()-1).draw(ui.getDrawPanel().getGraphics());
		
		
		System.out.println("Shape count: "+paintModel.getShapeList().size());
		
	}

	class AddMouseListenerDrawPanel implements MouseListener {

		int pressedX, pressedY, releasedX, releasedY;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			boolean isFilled =ui.getRadioBtnOption();
			Color color=ui.getColorPanelPaint();
			int shapeSize = ui.getShapeSize();
			
			System.out.println("x: " + e.getX() + " y: " + e.getY());
			switch (currentFlagShape) {
			case 1:
				currentShape = new Circle(e.getX(), e.getY(), shapeSize, color, ui.getLineThickness(), isFilled);
				break;
			case 2:
				currentShape = new Square(e.getX(), e.getY(), shapeSize, shapeSize, color, ui.getLineThickness(), isFilled);
				break;
			case 3:
				currentShape = new Rectangle(e.getX(), e.getY(), shapeSize/2, shapeSize, color, ui.getLineThickness(), isFilled);
				break;
			case 4:
				currentShape = new Triangle(e.getX(), e.getY(),shapeSize, color, ui.getLineThickness(), isFilled);
				break;
			default:
				System.out.println("def");
				return;
			}
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
			if (currentFlagShape == 5) {
				pressedX = e.getX();
				pressedY = e.getY();
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			Color color=ui.getColorPanelPaint();
			if (currentFlagShape == 5) {
				releasedX = e.getX();
				releasedY = e.getY();
				currentShape = new Line(pressedX, pressedY, releasedX,
						releasedY, color,
						ui.getLineThickness());
				paintModel.addShape(currentShape);
			}

		}

	}

	class ColorLinePanelListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			Color color=ui.getColorPanelPaint();

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
			currentFlagShape = 1;
		}
	}

	class BtnSquareListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentFlagShape = 2;
		}
	}

	class BtnRectangleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentFlagShape = 3;
		}
	}

	class BtnTriangleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentFlagShape = 4;
		}
	}

	class BtnLineListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentFlagShape = 5;
		}
	}
	
	class BtnResetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			paintModel.resetDrawPanel();
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

}
