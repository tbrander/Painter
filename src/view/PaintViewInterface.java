package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeListener;

public interface PaintViewInterface {

	
	JPanel getDrawPanel();
	boolean getRadioBtnOption();
	void addPanelListeners(MouseListener addMouseListenerDrawPanel,	MouseListener colorLinePanelListener);
	void addButtonAndSlideListeners(ActionListener btnListener, String label);
	void addMenyItemListeners(
			ActionListener addMouseListenerFileMenyItemNewDoc,
			ActionListener menyItemSelectorListener,
			ActionListener menyItemUndoListener,
			ChangeListener lineThicknessSliderListener,
			ActionListener menyItemDeleteListener,
			ActionListener menyItemRedoListener,
			ActionListener menyItemSaveListener,
			ActionListener menyItemLoadListener);
	Color getColorPanelPaint();
	int getLineThickness();
	JPanel getColorPanel();
	JLabel getLblLineThickness();
	JRadioButtonMenuItem getRdBtnItemSelect();
	JMenuItem getMntmDelete();
	void setLblSelectedShape(String currentShape);
	void setLblSelectedTool(String currentTool);
	JMenu getShapeJMenu();
	JFileChooser getJFileChooser();
	void showErrorDialog(String msg);
}
