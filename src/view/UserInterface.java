package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;

import controller.PaintController;

import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import java.awt.Font;

public class UserInterface extends JFrame implements PaintViewInterface{

	private static final long serialVersionUID = -6596013991710495573L;

	private JMenuBar menuBar;
	private JMenu mnFile, mnOptions, mnShapes;
	private JMenuItem mntmLoad, mntmSave, mntmNewDocument, mntmUndo, mntmRedo,
			mntmDelete;
	private JPanel contentPane, toolboxPanel, colorPanel, drawPanel;
	private JSlider sliderLineThickness;
	private JLabel lblLineThickness, lblSelectedShape, lblSelectedTool;
	private JRadioButton rdbtnOutline, rdbtnFilled;
	private JRadioButtonMenuItem rdBtnItemSelect;
	private JFileChooser jFile;
	private ButtonGroup shapeGroup;

	public UserInterface() {

		jFile = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Draw file", "draw");
		jFile.setFileFilter(filter);

		setTitle("Painter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 615);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);

		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);

		mntmNewDocument = new JMenuItem("New document");

		mnFile.add(mntmNewDocument);

		mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		mntmUndo = new JMenuItem("Undo");
		mnOptions.add(mntmUndo);

		mntmRedo = new JMenuItem("Redo");
		mnOptions.add(mntmRedo);

		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);

		rdBtnItemSelect = new JRadioButtonMenuItem("select");
		mnTools.add(rdBtnItemSelect);

		mntmDelete = new JMenuItem("delete");
		mntmDelete.setEnabled(false);
		mnTools.add(mntmDelete);

		mnShapes = new JMenu("Shapes");

		menuBar.add(mnShapes);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		toolboxPanel = new JPanel();
		toolboxPanel
				.setBorder(new LineBorder(new Color(105, 105, 105), 1, true));
		toolboxPanel.setBounds(0, 0, 118, 546);
		contentPane.add(toolboxPanel);
		toolboxPanel.setLayout(null);

		sliderLineThickness = new JSlider();
		sliderLineThickness.setMinimum(1);
		sliderLineThickness.setToolTipText("Line thickness");
		sliderLineThickness.setValue(3);
		sliderLineThickness.setMaximum(10);
		sliderLineThickness.setPaintTicks(true);
		sliderLineThickness.setSnapToTicks(true);
		sliderLineThickness.setPaintLabels(true);
		sliderLineThickness.setMinorTickSpacing(1);
		sliderLineThickness.setBounds(12, 54, 90, 26);
		toolboxPanel.add(sliderLineThickness);

		colorPanel = new JPanel();
		colorPanel.setToolTipText("Line color");
		colorPanel.setBackground(new Color(30, 144, 255));
		colorPanel.setBorder(new LineBorder(new Color(105, 105, 105), 1, true));
		colorPanel.setBounds(12, 197, 90, 114);
		toolboxPanel.add(colorPanel);

		lblLineThickness = new JLabel("Line size: "
				+ sliderLineThickness.getValue());
		lblLineThickness.setBounds(12, 32, 90, 16);
		toolboxPanel.add(lblLineThickness);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(12, 177, 90, 16);
		toolboxPanel.add(lblColor);

		rdbtnOutline = new JRadioButton("Outline");
		rdbtnOutline.setSelected(true);
		rdbtnOutline.setBounds(12, 102, 71, 24);
		toolboxPanel.add(rdbtnOutline);

		rdbtnFilled = new JRadioButton("Filled");
		rdbtnFilled.setBounds(12, 130, 71, 24);
		toolboxPanel.add(rdbtnFilled);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnOutline);
		group.add(rdbtnFilled);
		shapeGroup = new ButtonGroup();

		drawPanel = new JPanel();
		drawPanel.setBorder(new LineBorder(new Color(105, 105, 105), 1, true));

		drawPanel.setBackground(Color.WHITE);
		drawPanel.setForeground(Color.BLACK);
		drawPanel.setBounds(121, 29, 799, 517);
		contentPane.add(drawPanel);

		lblSelectedShape = new JLabel("Selected shape: Circle");
		lblSelectedShape.setForeground(new Color(30, 144, 255));
		lblSelectedShape.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
		lblSelectedShape.setBounds(130, 13, 225, 16);
		contentPane.add(lblSelectedShape);

		lblSelectedTool = new JLabel("Selected tool: None");
		lblSelectedTool.setForeground(new Color(30, 144, 255));
		lblSelectedTool.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
		lblSelectedTool.setBounds(352, 12, 202, 16);
		contentPane.add(lblSelectedTool);

		@SuppressWarnings("unused")
		PaintController paintController = new PaintController(this);
	}

	public JPanel getDrawPanel() {
		return drawPanel;
	}

	public boolean getRadioBtnOption() {
		return rdbtnFilled.isSelected();
	}

	public void addPanelListeners(MouseListener addMouseListenerDrawPanel,
			MouseListener colorLinePanelListener) {

		drawPanel.addMouseListener(addMouseListenerDrawPanel);
		colorPanel.addMouseListener(colorLinePanelListener);

	}

	public void addButtonAndSlideListeners(ActionListener btnListener,
			String label) {

		JRadioButtonMenuItem jm = new JRadioButtonMenuItem(label);
		jm.addActionListener(btnListener);
		mnShapes.add(jm);
		shapeGroup.add(jm);
	}

	public void addMenyItemListeners(
			ActionListener addMouseListenerFileMenyItemNewDoc,
			ActionListener menyItemSelectorListener,
			ActionListener menyItemUndoListener,
			ChangeListener lineThicknessSliderListener,
			ActionListener menyItemDeleteListener,
			ActionListener menyItemRedoListener,
			ActionListener menyItemSaveListener,
			ActionListener menyItemLoadListener) {

		mntmNewDocument.addActionListener(addMouseListenerFileMenyItemNewDoc);
		rdBtnItemSelect.addActionListener(menyItemSelectorListener);
		mntmUndo.addActionListener(menyItemUndoListener);
		sliderLineThickness.addChangeListener(lineThicknessSliderListener);
		mntmDelete.addActionListener(menyItemDeleteListener);
		mntmRedo.addActionListener(menyItemRedoListener);
		mntmSave.addActionListener(menyItemSaveListener);
		mntmLoad.addActionListener(menyItemLoadListener);
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

	public JLabel getLblLineThickness() {
		return lblLineThickness;
	}

	public JRadioButtonMenuItem getRdBtnItemSelect() {
		return rdBtnItemSelect;
	}

	public JMenuItem getMntmDelete() {
		return mntmDelete;
	}
	

	public void setLblSelectedShape(String currentShape) {
		lblSelectedShape.setText("Selected shape: " + currentShape);
	}

	public void setLblSelectedTool(String currentTool) {
		lblSelectedTool.setText("Selected tool: " + currentTool);
	}

	public JMenu getShapeJMenu() {
		return mnShapes;
	}

	public JFileChooser getJFileChooser() {
		return jFile;
	}

	public void showErrorDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

}
