package judahzone.gui;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 * Icon utility that loads icons from the classpath (src/main/resources/icons).
 * - Uses a thread-safe cache
 * - Falls back to a sensible UI icon if resource not found
 * - Accepts names with or without extension (will try the literal name, then name + ".png")
 *
 * Usage:
 *   ImageIcon icon = Icons.get("save.png");
 *   // or
 *   ImageIcon icon = Icons.get("save"); // will try "save" then "save.png"
 */
public final class Icons {

    private Icons() { /* utility */ }

    // Classpath folder under src/main/resources
    private static final String ICONS_PATH = "icons/";

    public static final Icon SAVE = UIManager.getIcon("FileView.floppyDriveIcon");
    public static final Icon NEW_FILE = UIManager.getIcon("FileView.fileIcon");
    public static final Icon DETAILS_VIEW = UIManager.getIcon("FileChooser.detailsViewIcon");
    public static final Icon HOME = UIManager.getIcon("FileChooser.homeFolderIcon");

    private static final Map<String, ImageIcon> CACHE = new ConcurrentHashMap<>();

    /**
     * Load an ImageIcon from classpath "icons/<name>".
     * If the resource is missing, return a default UI icon converted to ImageIcon.
     *
     * @param name filename (with or without extension) relative to resources/icons/
     * @return cached ImageIcon
     */
    public static ImageIcon get(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        return CACHE.computeIfAbsent(name, Icons::loadIcon);
    }

    private static ImageIcon loadIcon(String name) {
        // try exact name
        URL url = Icons.class.getClassLoader().getResource(ICONS_PATH + name);
        if (url == null && !name.contains(".")) {
            // try common extension
            url = Icons.class.getClassLoader().getResource(ICONS_PATH + name + ".png");
        }
        if (url != null) {
            return new ImageIcon(url);
        }

        // fallback: convert a UIManager icon into an ImageIcon so callers always get ImageIcon
        return toImageIcon(UIManager.getIcon("FileView.fileIcon"));
    }

    private static ImageIcon toImageIcon(Icon icon) {
        if (icon == null) {
            return new ImageIcon(); // empty icon
        }
        if (icon instanceof ImageIcon) {
            return (ImageIcon) icon;
        }
        int w = Math.max(icon.getIconWidth(), 16);
        int h = Math.max(icon.getIconHeight(), 16);
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            g.setComposite(AlphaComposite.SrcOver);
            icon.paintIcon(null, g, 0, 0);
        } finally {
            g.dispose();
        }
        return new ImageIcon(img);
    }
}