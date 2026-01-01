package judahzone.gui;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.UIManager;

/** Look and Feel */
public class Nimbus {

	public static final int SCROLL_BTN = 8;
	public static void start() {
		try {
			UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("ScrollBar.buttonSize", new Dimension(SCROLL_BTN, SCROLL_BTN));
            UIManager.put("nimbusBase", Pastels.EGGSHELL);
            UIManager.put("control", Pastels.EGGSHELL);
            UIManager.put("nimbusBlueGrey", Pastels.MY_GRAY);
            UIManager.getLookAndFeel().getDefaults().put("Button.contentMargins", new Insets(5, 5, 5, 5));
            UIManager.getLookAndFeel().getDefaults().put("JToggleButton.contentMargins", new Insets(1, 1, 1, 1));
            Thread.sleep(1); // let nimbus start up
		} catch (Exception e) { System.err.println(e.getMessage()); }
	}

}
