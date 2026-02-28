package judahzone.widgets;

import judahzone.api.FX;
import judahzone.gui.Updateable;

public class DoubleSlider extends RangeSlider implements Updateable {

	private final FX lower;
	private final int lowIdx;
	private final FX upper;
	private final int upperIdx;

	public DoubleSlider(FX lower, int lowIdx, FX upper, int upperIdx) {
		super(0, 100, lower.get(lowIdx), upper.get(upperIdx));
		this.lower = lower;
		this.lowIdx = lowIdx;
		this.upper = upper;
		this.upperIdx = upperIdx;
		update();
		addChangeListener(e->fireChange());

	}

	private void fireChange() {
		if (lower.get(lowIdx) != getValue())
			lower.set(lowIdx, getValue());
		if (upper.get(upperIdx) != getValue() + getExtent())
			upper.set(upperIdx, getValue() + getExtent());
	}

	@Override public void update() {
		int low = lower.get(lowIdx);
		if (getValue() != low)
			setValue(low);
		int hi = upper.get(upperIdx);
		int extent = hi - low;
		if (getExtent() != extent)
			setExtent(extent);
	}

}
