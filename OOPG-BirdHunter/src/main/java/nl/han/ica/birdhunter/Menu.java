package nl.han.ica.birdhunter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;
import processing.core.PImage;

public class Menu extends GameObject {

	private BirdHunter bh;

	private boolean isDark = false;

	public Menu(BirdHunter bh) {
		this.bh = bh;

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(PGraphics g) {
		int width = bh.getWidth();
		int height = bh.getHeight();

		g.fill(0);
		g.rect(width / 2 - 200, height / 2 - 200, 400, 400);

		g.stroke(255, 0, 0);
		g.rect(width / 2 - 100, height / 2 - 100, 200, 50);
		g.rect(width / 2 - 100, height / 2, 200, 50);
		g.rect(width / 2 - 100, height / 2 + 100, 200, 50);

		g.fill(255);
		g.textSize(30);
		g.textAlign(CENTER);
		g.text("Pause", width / 2, height / 2 - 150);
		if (isDark) {
			g.text("Dark", width / 2, height / 2 - 65);
		} else
			g.text("Normal", width / 2, height / 2 - 65);
		g.text("knop 2", width / 2, height / 2 + 35);
		g.text("Afsluiten", width / 2, height / 2 + 135);
	}

	@Override
	public void mousePressed(int x, int y, int button) {
		super.mouseClicked(x, y, button);
		int width = bh.getWidth();
		int height = bh.getHeight();

		if (x > width / 2 - 100 && x < width / 2 + 100) {
			if (y > height / 2 - 100 && y < height / 2 - 50) {
				isDark = !isDark;
				System.out.println("Knop 1!");
			} else if (y > height / 2 && y < height / 2 + 50) {
				System.out.println("Knop 2!");
			} else if (y > height / 2 + 100 && y < height / 2 + 150) {
				System.out.println("Knop 3!");
				System.exit(0);
			}
		} else {
			System.out.println("Buiten de knop");
		}
		System.out.println(x);
		System.out.println(y);
	}

	public boolean isDark() {
		return isDark;
	}
}
