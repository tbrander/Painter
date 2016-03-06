package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;

import controller.PaintController;

import javax.swing.JRadioButton;

public class UserInterface extends JFrame {

	private static final long serialVersionUID = -6596013991710495573L;

	private JMenuBar menuBar;
	private JMenu file, mnOptions;
	private JMenuItem mntmLoad, mntmSave, mntmNewDocument, mntmUndo, mntmRedo;
	private JPanel contentPane, toolboxPanel, colorPanel, drawPanel;
	private JButton btnCircle, btnSquare, btnRectangle, btnTriangle, btnSelect,	btnLine, btnReset;
	private JSlider sliderLineThickness, sliderShapeSize;
	private JLabel lblLineThickness, lblShapeSize;
	private PaintController paintController;
	private JRadioButton rdbtnOutline, rdbtnFilled;

	public UserInterface() {
		
		setTitle("Labb2 Painter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 607);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		file = new JMenu("File");
		menuBar.add(file);

		mntmLoad = new JMenuItem("Load");
		file.add(mntmLoad);

		mntmSave = new JMenuItem("Save");
		file.add(mntmSave);

		mntmNewDocument = new JMenuItem("New document");
		file.add(mntmNewDocument);

		mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		mntmUndo = new JMenuItem("Undo");
		mnOptions.add(mntmUndo);

		mntmRedo = new JMenuItem("Redo");
		mnOptions.add(mntmRedo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		toolboxPanel = new JPanel();
		toolboxPanel.setBorder(new LineBorder(new Color(105, 105, 105), 1, true));
		toolboxPanel.setBounds(0, 0, 118, 534);
		contentPane.add(toolboxPanel);
		toolboxPanel.setLayout(null);

		btnCircle = new JButton("");
		btnCircle.setToolTipText("Circle");
		btnCircle.setIcon(new ImageIcon("C:\\Users\\Thomas\\workspace\\HI2011_Lab2\\src\\img\\circle.jpg"));
		btnCircle.setBounds(12, 13, 41, 25);
		toolboxPanel.add(btnCircle);

		btnSquare = new JButton("");

		btnSquare.setToolTipText("Square");
		btnSquare.setIcon(new ImageIcon("C:\\Users\\Thomas\\workspace\\HI2011_Lab2\\src\\img\\square.jpg"));
		btnSquare.setBounds(61, 13, 41, 25);
		toolboxPanel.add(btnSquare);

		btnRectangle = new JButton("");
		btnRectangle.setToolTipText("Rectangle");
		btnRectangle.setIcon(new ImageIcon("C:\\Users\\Thomas\\workspace\\HI2011_Lab2\\src\\img\\rectangle.jpg"));


		btnRectangle.setBounds(12, 51, 41, 25);
		toolboxPanel.add(btnRectangle);

		btnTriangle = new JButton("");
		btnTriangle.setToolTipText("Triangle");
		btnTriangle.setIcon(new ImageIcon("C:\\Users\\Thomas\\workspace\\HI2011_Lab2\\src\\img\\triangle.jpg"));
		btnTriangle.setBounds(61, 51, 41, 25);
		toolboxPanel.add(btnTriangle);

		btnSelect = new JButton("");
		btnSelect.setToolTipText("Marker");
		btnSelect.setIcon(new ImageIcon("C:\\Users\\Thomas\\workspace\\HI2011_Lab2\\src\\img\\marker.jpg"));
		btnSelect.setBounds(61, 88, 41, 25);
		toolboxPanel.add(btnSelect);

		btnLine = new JButton("");
		btnLine.setToolTipText("Line");
		btnLine.setIcon(new ImageIcon("C:\\Users\\Thomas\\workspace\\HI2011_Lab2\\src\\img\\line.jpg"));
		btnLine.setBounds(12, 88, 41, 25);
		toolboxPanel.add(btnLine);

		sliderLineThickness = new JSlider();
		sliderLineThickness.setMinimum(1);
		sliderLineThickness.setToolTipText("Line thickness");
		sliderLineThickness.setValue(3);
		sliderLineThickness.setMaximum(10);
		sliderLineThickness.setPaintTicks(true);
		sliderLineThickness.setSnapToTicks(true);
		sliderLineThickness.setPaintLabels(true);
		sliderLineThickness.setMinorTickSpacing(1);
		sliderLineThickness.setBounds(12, 197, 90, 26);
		toolboxPanel.add(sliderLineThickness);

		colorPanel = new JPanel();
		colorPanel.setToolTipText("Line color");
		colorPanel.setBackground(new Color(50, 205, 50));
		colorPanel.setBorder(new LineBorder(new Color(105, 105, 105), 1, true));
		colorPanel.setBounds(12, 403, 90, 95);
		toolboxPanel.add(colorPanel);

		lblLineThickness = new JLabel("Line size: "+sliderLineThickness.getValue());
		lblLineThickness.setBounds(12, 176, 90, 16);
		toolboxPanel.add(lblLineThickness);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(12, 383, 90, 16);
		toolboxPanel.add(lblColor);
		
		btnReset = new JButton("Reset");
		btnReset.setToolTipText("Reset");
		btnReset.setBounds(12, 125, 90, 26);
		toolboxPanel.add(btnReset);
		
		rdbtnOutline = new JRadioButton("Outline");
		rdbtnOutline.setSelected(true);
		rdbtnOutline.setBounds(12, 308, 71, 24);
		toolboxPanel.add(rdbtnOutline);
		
		rdbtnFilled = new JRadioButton("Filled");
		rdbtnFilled.setBounds(12, 336, 71, 24);
		toolboxPanel.add(rdbtnFilled);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnOutline);
	    group.add(rdbtnFilled);
	    
	   
	    
	    sliderShapeSize = new JSlider();
	    sliderShapeSize.setPaintTicks(true);
	    sliderShapeSize.setMaximum(70);
	    sliderShapeSize.setValue(10);
	    sliderShapeSize.setToolTipText("Shape size");
	    sliderShapeSize.setPaintLabels(true);
	    sliderShapeSize.setMinorTickSpacing(5);
	    sliderShapeSize.setMinimum(5);
	    sliderShapeSize.setBounds(12, 256, 90, 26);
	    toolboxPanel.add(sliderShapeSize);
	    
	    lblShapeSize = new JLabel("Shape size: "+sliderShapeSize.getValue());
	    lblShapeSize.setBounds(12, 235, 90, 16);
	    toolboxPanel.add(lblShapeSize);

		drawPanel = new JPanel();
		drawPanel.setBorder(new LineBorder(new Color(105, 105, 105), 1, true));

		drawPanel.setBackground(Color.WHITE);
		drawPanel.setForeground(Color.BLACK);
		drawPanel.setBounds(117, 0, 790, 534);
		contentPane.add(drawPanel);
		
		paintController = new PaintController(this);
		
	}
	
	
	public JPanel getDrawPanel(){
		return drawPanel;
	}
	
	public boolean getRadioBtnOption(){
		return rdbtnFilled.isSelected();
	}
	
	public int getShapeSize(){
		
		return sliderShapeSize.getValue();
		
	}
	
	
	public void addPanelListeners(MouseListener addMouseListenerDrawPanel, MouseListener colorLinePanelListener) {
		
		drawPanel.addMouseListener(addMouseListenerDrawPanel);
		colorPanel.addMouseListener(colorLinePanelListener);
		
	}
	
	public void addButtonAndSlideListeners(ActionListener btnCircleListener, ActionListener btnLineListener, ActionListener btnRectangleListener, ActionListener btnSquareListener, ActionListener btnTriangleListener, ActionListener btnResetListener, ChangeListener lineThicknessSliderListener, ChangeListener shapeSizeSliderListener){
		btnCircle.addActionListener(btnCircleListener);
		btnSquare.addActionListener(btnSquareListener);
		btnLine.addActionListener(btnLineListener);
		btnRectangle.addActionListener(btnRectangleListener);
		btnTriangle.addActionListener(btnTriangleListener);
		sliderLineThickness.addChangeListener(lineThicknessSliderListener);
		sliderShapeSize.addChangeListener(shapeSizeSliderListener);
		btnReset.addActionListener(btnResetListener);
	}

	public Color getColorPanelPaint() {
		return colorPanel.getBackground();
	}

	public int getLineThickness() {
		return sliderLineThickness.getValue();
	}

	public JPanel getColorPanel() {
		return colorPanel;
	}


	public JLabel getlblShapeSize() {
		return lblShapeSize;
	}

	public JLabel getLblLineThickness() {
		return lblLineThickness;
	}
}
