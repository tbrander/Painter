package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
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

public class UserInterface extends JFrame {

	private static final long serialVersionUID = -6596013991710495573L;

	private JMenuBar menuBar;
	private JMenu file, mnOptions;
	private JMenuItem mntmLoad, mntmSave, mntmNewDocument, mntmUndo, mntmRedo;
	private JPanel contentPane, toolboxPanel, lineColorPanel, drawPanel, fillColorPanel;
	private JButton btnCircle, btnSquare, btnRectangle, btnTriangle, btnMarker,	btnLine, btnFill, btnColorPicker;
	private JSlider slider;
	private JLabel lblLineThickness;
	private PaintController paintController;
	

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

		btnMarker = new JButton("");
		btnMarker.setToolTipText("Marker");
		btnMarker.setIcon(new ImageIcon("C:\\Users\\Thomas\\workspace\\HI2011_Lab2\\src\\img\\marker.jpg"));
		btnMarker.setBounds(61, 88, 41, 25);
		toolboxPanel.add(btnMarker);

		btnLine = new JButton("");
		btnLine.setToolTipText("Line");
		btnLine.setIcon(new ImageIcon("C:\\Users\\Thomas\\workspace\\HI2011_Lab2\\src\\img\\line.jpg"));
		btnLine.setBounds(12, 88, 41, 25);
		toolboxPanel.add(btnLine);

		btnFill = new JButton("");
		btnFill.setToolTipText("Fill");
		btnFill.setIcon(new ImageIcon("C:\\Users\\Thomas\\workspace\\HI2011_Lab2\\src\\img\\fill.jpg"));
		btnFill.setBounds(12, 127, 41, 25);
		toolboxPanel.add(btnFill);

		btnColorPicker = new JButton("");
		btnColorPicker.setToolTipText("Pencil");
		btnColorPicker.setIcon(new ImageIcon("C:\\Users\\Thomas\\workspace\\HI2011_Lab2\\src\\img\\penn.jpg"));
		btnColorPicker.setBounds(61, 127, 41, 25);
		toolboxPanel.add(btnColorPicker);

		slider = new JSlider();
		slider.setMinimum(1);
		slider.setToolTipText("Line thickness");
		slider.setValue(3);
		slider.setMaximum(10);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(1);
		slider.setBounds(12, 236, 90, 26);
		toolboxPanel.add(slider);

		lineColorPanel = new JPanel();
		lineColorPanel.setToolTipText("Line color");
		lineColorPanel.setBackground(new Color(50, 205, 50));
		lineColorPanel.setBorder(new LineBorder(new Color(105, 105, 105), 1, true));
		lineColorPanel.setBounds(12, 303, 90, 45);
		toolboxPanel.add(lineColorPanel);

		lblLineThickness = new JLabel("Line size: "+slider.getValue());
		lblLineThickness.setBounds(12, 215, 90, 16);
		toolboxPanel.add(lblLineThickness);

		JLabel lblColor = new JLabel("Line color:");
		lblColor.setBounds(12, 283, 90, 16);
		toolboxPanel.add(lblColor);

		JLabel lblFillColor = new JLabel("Fill color:");
		lblFillColor.setBounds(12, 360, 90, 16);
		toolboxPanel.add(lblFillColor);

		fillColorPanel = new JPanel();

		fillColorPanel.setToolTipText("Fill color");

		fillColorPanel.setBorder(new LineBorder(new Color(105, 105, 105), 1, true));
		fillColorPanel.setBackground(Color.ORANGE);
		fillColorPanel.setBounds(12, 380, 90, 45);
		toolboxPanel.add(fillColorPanel);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setToolTipText("Reset");
		btnReset.setBounds(12, 164, 90, 26);
		toolboxPanel.add(btnReset);

		drawPanel = new JPanel();
		drawPanel.setBorder(new LineBorder(new Color(105, 105, 105), 1, true));

		drawPanel.setBackground(Color.WHITE);
		drawPanel.setForeground(Color.BLACK);
		drawPanel.setBounds(117, 0, 790, 534);
		contentPane.add(drawPanel);
		
		paintController = new PaintController(this);
		
	}

	public void addPanelListeners(MouseListener addMouseListenerDrawPanel, MouseListener colorFillPanelListener, MouseListener colorLinePanelListener) {
		

		drawPanel.addMouseListener(addMouseListenerDrawPanel);
		lineColorPanel.addMouseListener(colorLinePanelListener);
		fillColorPanel.addMouseListener(colorFillPanelListener);
		
	}
	
	public void addButtonAndSlideListeners(ActionListener btnCircleListener, ActionListener btnLineListener, ActionListener btnRectangleListener, ActionListener btnSquareListener, ActionListener btnTriangleListener, ChangeListener lineThicknessSliderListener){
		btnCircle.addActionListener(btnCircleListener);
		btnSquare.addActionListener(btnSquareListener);
		btnLine.addActionListener(btnLineListener);
		btnRectangle.addActionListener(btnRectangleListener);
		btnTriangle.addActionListener(btnTriangleListener);
		slider.addChangeListener(lineThicknessSliderListener);
	}

	public Color getLinePanelColor() {
		return lineColorPanel.getBackground();
	}

	public int getLineThickness() {
		return slider.getValue();
	}

	public JPanel getLineColorPanel() {
		return lineColorPanel;
	}

	public JPanel getFillColorPanel() {
		return fillColorPanel;
	}

	public JLabel getLblLineThickness() {
		return lblLineThickness;
	}
	
	
	
	
}
