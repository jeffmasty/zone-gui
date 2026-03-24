package judahzone.gui;

import judahzone.api.FX;

public abstract class Updates {

	private static Updates instance;

	public static void register(Updates updates) {
		instance = updates;
	}

	public static enum Track {
		PLAY, CAPTURE, CURRENT, CYCLE, AMP, ARP, PROGRAM,
		CUE, GATE, FILE, REFILL, RANGE, LAUNCH, REZ
	}

    public static enum Ch { MUTE, MUTE_RECORD, PRESET, RMS, LOOP, SOLO } // TODO updateCh


    protected abstract void updateFx(String channel, FX fx);
	public static void fxUpdate(String channel, FX fx) {
		if (instance == null)
			return;
		instance.updateFx(channel, fx);
    }

	protected abstract void updateChannel(String channel, Ch type);
    public static void channelUpdate(String channel, Ch type) {
    	if (instance != null)
			instance.updateChannel(channel, type);
    }

    protected abstract void updateTrack(String channel, Track type);
    public static void trackUpdate(String channel, Track type) {
    	if (instance != null)
    		instance.updateTrack(channel, type);
    }

    public abstract void updateKnob(int idx, int data2);
    public static void knobUpdate(int idx, int data2) {
    	if (instance != null)
    		instance.updateKnob(idx, data2);
    }


}
