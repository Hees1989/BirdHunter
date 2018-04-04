package nl.han.ica.birdhunter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PGraphics;

public class Bullet extends GameObject implements ICollidableWithGameObjects {
	private BirdHunter bh;
	private Sound hitSound;
	private int bulletSize = 5;

	/**
	 * Constructor
	 * 
	 * @param bh
	 *            referentie van birdhunter
	 */

	public Bullet(BirdHunter bh) {
		this.bh = bh;
		setySpeed(-15);
		setWidth(bulletSize);
		setHeight(bulletSize);
		hitSound = new Sound(bh, "src/main/java/nl/han/ica/birdhunter/media/crow.mp3");
	}

	/* (non-Javadoc)
	 * @see nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject#update()
	 */
	@Override
	public void update() {
		if (getY() < 0) {
			bh.deleteGameObject(this);
		}
	}

	/* (non-Javadoc)
	 * @see nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject#draw(processing.core.PGraphics)
	 */
	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(g.CORNER);
		g.fill(0);
		g.stroke(0);
		g.ellipse(getX(), getY(), bulletSize, bulletSize);
	}

	/**
	 * Speelt geluid af, verwijderd de kogel en verhoogt de score wanneer de vogel
	 * geraakt is.
	 * 
	 * @param Lijst
	 *            van gameobjecten gameObjects
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Crow) {
				hitSound.rewind();
				hitSound.play();
				bh.deleteGameObject(this);
				bh.increaseHits();
			}
			if (g instanceof SuperBird) {
				hitSound.rewind();
				hitSound.play();
				bh.deleteGameObject(this);
				for (int i = 0; i < 5; i++) {
					bh.increaseHits();
				}

			}
		}
	}
}
