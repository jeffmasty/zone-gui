package judahzone.widgets;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;

import judahzone.gui.Gui;

public class SingleRow extends Box {
    public static final int SLIDER_WIDTH = 128;
    public static final int LABEL_WIDTH = 70;
    public static final int VALUE_WIDTH = 50;
    public static final int SPACING = 8;
    public static final int ROW_HEIGHT = 25;
    public static final Dimension VAL_DIM = new Dimension(VALUE_WIDTH, ROW_HEIGHT);
    public static final Dimension SLIDER_DIM = new Dimension(SLIDER_WIDTH, ROW_HEIGHT);
    public static final Dimension LABEL_DIM = new Dimension(LABEL_WIDTH, ROW_HEIGHT);

	private static class LBL extends JLabel {
	    public LBL(String txt) {
	        super(txt, JLabel.CENTER);
	        Gui.resize(this, LABEL_DIM);
	    }
	}


	public SingleRow(String lbl, JSlider slider, JLabel value) {
		this (new LBL(lbl), slider, value);
	}


	public SingleRow(JLabel lbl, JSlider slider, JLabel value) {
	    super(BoxLayout.LINE_AXIS);
	    add(Box.createHorizontalGlue());
	    add(lbl);
	    add(Box.createHorizontalStrut(SPACING));
	    Gui.resize(slider, SLIDER_DIM);
	    add(slider);
	    add(Box.createHorizontalStrut(SPACING));
	    Gui.resize(value, VAL_DIM);
	    add(value);
	    add(Box.createHorizontalGlue());
	}



}
