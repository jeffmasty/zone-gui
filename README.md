# zone-gui
Swing helpers for the JudahZone project

judahzone.gui package

Gui.java
lightweight UI helpers and constants (fonts, borders, simple factory methods).

DialogManager.java
singleton dialog registry using ConcurrentHashMap to ensure single frames per key. Uses synchronized methods for open/close/isOpen.

Icons.java 
ImageIcons from classpath with a ConcurrentHashMap cache; fallback to UIManager icons; converts non-ImageIcon Icon into buffered ImageIcon.

Nimbus.java
sets Nimbus look-and-feel and UI defaults; small Thread.sleep(1) in start().

Pastels.java
color palette constants.

Updates.java 
a static registry with an instance that GUI modules register to receive update events via static convenience methods.

Knob.java 
complex custom knob Swing component adapted from JKnobFancy (lots of geometry, mouse handling, paint).

RangeSlider.java 
extended JSlider with dual thumbs (from upstream MIT code).

judahzone.widgets package

Double Slider  - 2 pole slider
Knob - rotary widget
RainbowFader - colorful JSlider
RangeSlider  -  Double Slider paired to FX
TapTempo - timed to clock
Integers  - JComboBox
Click -  lbl btn
CenteredCombo
Btn - action ctor
FileRender - for combos 



## Design notes
3.2026: Aggressively adopt functional/event-driven patterns on the GUI side, RT-side remains strictly procedural, allocation-free.  
	•  immutable, declarative pipeline GUI benefits: fewer state bugs, easier testing, clearer transformations  
	•  Functional/event-stream styles map well to modern UI concerns (event flows, async data, debounce, map/transform, compose).  
	•  Must respect UI threading model (Swing EDT / JavaFX application thread) — streams must marshal results back to EDT.  
	•  Use functional style for UI/model code, view-models, configuration, and non-RT pipelines.  
	•  Prefer immutable records/POJOs for model snapshots pushed to UI (less locking / fewer races).  
	•  Avoid streams/allocations in paint loops or sub-millisecond paths; use primitive-specialized APIs or hand-written hotspots  
	•  Keep APIs between GUI and RT layers explicit and minimal:  
	   pass snapshots (preallocated buffers or immutable records), not streams into audio threads.	 
	•  Use functional maps/filters for building UI models and responses.  
	•  Marshal heavy work off EDT via Executors / CompletableFuture.  
	•  explore RxJava library if needed.  



## todo  

•  DialogManager creator should create/show frames on EDT to avoid subtle issues.  
•  Icons.toImageIcon creates BufferedImage occasionally — acceptable but can be preloaded.  
•  refactor Knob.getSpotCenter() to avoid allocations and run tests,  
	•  Knob.getSpotCenter() returning new Point each paint — replace with primitive computations or reuse object fields.  
	•  Knob.JKnobHandle.getCenterEdgePoint() and other small object returns — consider reducing allocations.  
		example improvement (Knob paint micro-optim)  
	•  Replace: Point pt = thisHandle.getSpotCenter(); int xc = (int)pt.getX(); ...  
	•  to: int[] pos = thisHandle.getSpotCenterInts(); int xc = pos[0]; int yc = pos[1];  
	•  or: compute xc,yc inline to avoid object allocations.  
