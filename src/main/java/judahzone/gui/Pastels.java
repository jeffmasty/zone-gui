package judahzone.gui;

import java.awt.Color;

public interface Pastels {

    Color RED = new Color(0xff6e8d);
    Color GREEN = new Color(0x90da6a);
    Color BLUE = new Color(189, 230, 250); //  98dafd // a4b9cb
    Color PINK = new Color(0xf4a2f9);
    Color PURPLE = new Color(0xa295ad); // 966FD6 //7955b5
    Color ORANGE = new Color(0xFFA500); // ffdf9e
    Color YELLOW = new Color(0xFFFF80);

    Color BG = new Color(252,252,246);
	Color MY_GRAY = new Color(220, 220, 210);
    Color FADED = new Color(8, 8, 8, 25);
	Color BUTTONS = new Color(237, 237, 229);

	Color SHADE = BUTTONS;
	Color ONTAPE = YELLOW;
	Color DOWNBEAT = alpha(BLUE, 90);
	Color GRID = MY_GRAY;
	Color SELECTED = ORANGE;
	Color CC = BLUE;
	Color PROGCHANGE = PINK;

	static Color alpha(Color input, int alpha) {
		return new Color(input.getRed(), input.getGreen(), input.getBlue(), alpha);
	}
}

/* DARK THEME
Color RED     = new Color(0xFF6B6B); // coral red
Color GREEN   = new Color(0x7ED957); // vivid lime/green
Color BLUE    = new Color(0x64B5F6); // light sky blue accent, use Purple
Color PINK    = new Color(0xFF4081); // magenta accent
Color PURPLE  = new Color(0x7C4DFF); // deep purple accent, fix
Color ORANGE  = new Color(0xFFA726); // warm orange
Color YELLOW  = new Color(0xFFD54F); // soft amber

// Dark UI background and surfaces
Color BG       = new Color(18, 18, 20);      // main background (very dark)
Color MY_GRAY  = new Color(55, 58, 64);      // panel/grid gray
Color FADED    = new Color(255, 255, 255, 20); // subtle highlights
Color BUTTONS  = new Color(30, 30, 34);      // button/background surfaces
*/