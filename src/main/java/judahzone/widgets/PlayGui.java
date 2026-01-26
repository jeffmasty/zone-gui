package judahzone.widgets;

import java.awt.Dimension;
import java.io.Closeable;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import judahzone.api.Asset;
import judahzone.api.PlayAudio;
import judahzone.api.Played;
import judahzone.gui.Gui;
import judahzone.util.RTLogger;

/**BoomBox — GUI wrapper.  Provides Play and Loop btns, simple gain.
 * Two-way traffic messages down to low-level and up to GUI,
 * filtering for the controls BoomBox manages. */
public class PlayGui extends JPanel implements PlayAudio, Played {

	private final PlayAudio player;
	private Played played;

    private final JToggleButton playButton;
    private final JSlider gainSlider;
    private final JToggleButton loopButton;

	public PlayGui(PlayAudio player, Played played, Dimension SLIDER) {
		this.player = player;
		this.played = played;
		playButton = new JToggleButton("▶️");
		gainSlider = new JSlider(0, 100, 50); // 0..100 -> 0.0..1.0 (silence..unity)
		loopButton = new JToggleButton("🔁");

		playButton.addActionListener(a -> play(!player.isPlaying()));
		Gui.resize(gainSlider, SLIDER);
		gainSlider.setToolTipText("Output gain: 0% (silence) .. 100% (unity)");
		gainSlider.addChangeListener(e -> {
			float m = gainSlider.getValue() / 100f;
			player.setEnv(m);
		});
		gainSlider.setValue(50); // start at 50% (half / -6dB)

		loopButton.addActionListener(a -> {
			player.setType(loopButton.isSelected() ? PlayAudio.Type.LOOP : PlayAudio.Type.ONE_SHOT);
		});

		setBorder(Gui.SUBTLE);
		add(playButton);
		add(gainSlider);
		add(loopButton);
	}

	@Override
	public void setRecording(Asset a) {
		player.setRecording(a);
	}

	@Override
	public void play(boolean onOrOff) {
		player.play(onOrOff);
		playState();
	}

	@Override
	public boolean isPlaying() {
		return player.isPlaying();
	}

	@Override
	public int getLength() {
		return player.getLength();
	}

	@Override
	public float seconds() {
		return player.seconds();
	}

	@Override
	public void rewind() {
		player.rewind();
	}

	@Override
	public void setEnv(float env) {
		player.setEnv(env);
	}

	@Override
	public void setSample(long sampleFrame) {
		player.setSample(sampleFrame);
	}

	@Override
	public void setType(Type type) {
		player.setType(type);
	}

    public void close() {
        player.play(false);
        if (player instanceof Closeable close)
            try { close.close(); } catch (Exception e) {RTLogger.warn(this, e);}
    }

	@Override
	public void setPlayed(Played p) {
		player.setPlayed(p);
	}

	@Override
	public void setHead(long sample) {
		if (played != null)
			played.setHead(sample);
		// No current/length display to update here (for now)
	}

	@Override
	public void playState() {
		if (played != null)
			played.playState();
		SwingUtilities.invokeLater(() -> {
			boolean playing = player.isPlaying();
			playButton.setSelected(playing);
			playButton.setText(playing ? "❚❚" : "▶️");
		});
	}


}
