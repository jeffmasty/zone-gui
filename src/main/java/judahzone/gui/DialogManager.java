package judahzone.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import judahzone.util.Constants;

/**Simple singleton registry for modal frames. Ensures a single dialog per key,
 * brings existing dialog to front and removes entries when window closes. */
public final class DialogManager {
    private static final Map<String, JFrame> OPEN = new ConcurrentHashMap<>();
    private DialogManager() {}

    /**Open or bring-to-front a dialog keyed by `key`. The supplier must create
     * and return the dialog's JFrame (already configured and shown).*/
    public static synchronized JFrame open(String key, Supplier<JFrame> creator) {
        JFrame existing = OPEN.get(key);
        if (existing != null) {
            // bring existing to front on EDT
            SwingUtilities.invokeLater(() -> {
                if (!existing.isVisible())
                    existing.setVisible(true);
                existing.toFront();
                existing.requestFocus();
            });
            return existing;
        }
        JFrame frame = creator.get();
        if (frame == null)
            return null;
        OPEN.put(key, frame);
        // remove mapping when closed/disposed
        frame.addWindowListener(new WindowAdapter() {
            @Override public void windowClosed(WindowEvent e) { OPEN.remove(key); }
            @Override public void windowClosing(WindowEvent e) { /* keep remove on closed */ }
        });
        return frame;
    }

    public static synchronized boolean isOpen(String key) { return OPEN.containsKey(key); }

    public static synchronized void close(String key) {
        JFrame f = OPEN.remove(key);
        if (f != null)
            EventQueue.invokeLater(() -> {
                if (f.isDisplayable())
                    f.dispose();
            });
    }

	  /** Create and show a frame containing the given content. Returns the frame. */
    public static JFrame show(JComponent content, Dimension size) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(content);
        frame.setSize(size);
        frame.setLocationRelativeTo(null);
        frame.setTitle(Constants.APP_NAME);
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
        return frame;
    }



}
