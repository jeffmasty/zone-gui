package judahzone.gui;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/** Look and Feel */
public class Nimbus {

	public static final int SCROLL_BTN = 8;
	public static void start() {
		try {
			UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("ScrollBar.buttonSize", new Dimension(SCROLL_BTN, SCROLL_BTN));
            UIManager.put("nimbusBase", Pastels.BG);
            UIManager.put("control", Pastels.BG);
            UIManager.put("nimbusBlueGrey", Pastels.MY_GRAY);
            UIManager.getLookAndFeel().getDefaults().put("Button.contentMargins", new Insets(5, 5, 5, 5));
            UIManager.getLookAndFeel().getDefaults().put("JToggleButton.contentMargins", new Insets(1, 1, 1, 1));

            // applyDarkNimbusDefaults();

            Thread.sleep(1); // let nimbus start up
		} catch (Exception e) { System.err.println(e.getMessage()); }
	}

	// call this after UIManager.setLookAndFeel(...) for Nimbus
	@SuppressWarnings("unused")
	private static void applyDarkNimbusDefaults() {
	    ColorUIResource white = new ColorUIResource(255, 255, 255);
	    ColorUIResource bg = new ColorUIResource(Pastels.BG);
	    ColorUIResource surface = new ColorUIResource(Pastels.BUTTONS);
	    ColorUIResource faint = new ColorUIResource(180, 180, 180);

	    // general Nimbus / Swing keys
	    UIManager.put("control", bg);
	    UIManager.put("info", bg);
	    UIManager.put("nimbusLightBackground", bg);
	    UIManager.put("text", white);
	    UIManager.put("nimbusBase", surface);
	    UIManager.put("nimbusFocus", new ColorUIResource(120, 120, 120));

	    // common component foregrounds (make font color white)
	    String[] fgKeys = {
	        "Label.foreground", "Button.foreground", "ToggleButton.foreground",
	        "ComboBox.foreground", "List.foreground", "Table.foreground",
	        "Table.selectionForeground", "Tree.textForeground",
	        "Menu.foreground", "MenuItem.foreground", "CheckBox.foreground",
	        "RadioButton.foreground", "TabbedPane.foreground",
	        "ToolTip.foreground", "TextField.foreground", "TextArea.foreground",
	        "EditorPane.foreground", "PasswordField.foreground"
	    };
	    for (String k : fgKeys)
	        UIManager.put(k, white);

	    // background / surface tweaks
	    UIManager.put("Panel.background", bg);
	    UIManager.put("Button.background", surface);
	    UIManager.put("ToggleButton.background", surface);
	    UIManager.put("Menu.background", surface);
	    UIManager.put("ToolTip.background", new ColorUIResource(40, 40, 44));
	    UIManager.put("ScrollBar.thumb", faint);

	    // small readable default for disabled text
	    UIManager.put("Label.disabledForeground", new ColorUIResource(140, 140, 140));
	}

}
