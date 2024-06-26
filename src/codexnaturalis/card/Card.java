package codexnaturalis.card;

import java.awt.Color;
import fr.umlv.zen5.ApplicationContext;

public interface Card {
	public void draw(ApplicationContext context, Coordinates coordinates);

	public static Color getColor(Collectible c) {
		return c.getColor();
	}

	public boolean isValidCorner(int corner);
	
	default public int width() {
		return 200;
	}
	
	default public int height() {
		return 80;
	}
	
	default public int cornerSize() {
		return 20;
	}
	
	default public int bordersize() {
		return 2;
	}
}