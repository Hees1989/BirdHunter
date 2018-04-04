package nl.han.ica.birdhunter;

import java.util.List;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public class Hunter extends AnimatedSpriteObject implements ICollidableWithGameObjects {

	private BirdHunter bh;
	private Sound bulletSound;
	private Sound reload;
	private boolean isHunterOnChest;
	private int ammo;
	private int setAmmo;
	
	/**
	 * Constructor
	 * @param bh referentie van BirdHunter
	 */
	public Hunter(BirdHunter bh) {
		super(new Sprite("src/main/java/nl/han/ica/birdhunter/media/hunter-sprite.png"), 3);
		this.bh = bh;
		setCurrentFrameIndex(1);
		setFriction(0.4f);
		setAmmo = 10;
		ammo = setAmmo;
		bulletSound = new Sound(bh, "src/main/java/nl/han/ica/birdhunter/media/gun.mp3");
		reload = new Sound(bh, "src/main/java/nl/han/ica/birdhunter/media/reload.mp3");

	}

	/* (non-Javadoc)
	 * @see nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject#update()
	 */
	@Override
	public void update() {
		if (getX() <= 0) {
			setxSpeed(0);
			setX(0);
		}
		if (getX() >= bh.getWidth() - 100) {
			setxSpeed(0);
			setX(bh.getWidth() - 100);
		}
		isHunterOnChest = false;
	}

	/* (non-Javadoc)
	 * @see nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject#keyPressed(int, char)
	 */
	@Override
	public void keyPressed(int keyCode, char key) {
		boolean isGamePaused = bh.getThreadState();

		if (!isGamePaused) {
			if (keyCode == bh.LEFT) {
				setDirectionSpeed(270, 50);
				setCurrentFrameIndex(0);
			}

			if (keyCode == bh.RIGHT) {
				setDirectionSpeed(90, 50);
				setCurrentFrameIndex(2);
			}

			if (key == ' ') {
				setCurrentFrameIndex(1);
				if (ammo > 0 && !isHunterOnChest) {
					Bullet bullet = new Bullet(bh);
					bulletSound.rewind();
					bulletSound.play();
					ammo--;
					bh.addGameObject(bullet, getX() + 45 , getY());
				}
			}
			if (key == 'r' || key == 'R') {
				if (isHunterOnChest) {
					if (ammo < setAmmo) {
						ammo = setAmmo;
						reload.rewind();
						reload.play();
					}
				}
			}
		}
	}
	
	/**
	 * geeft een boolean isHunterOnChest terug wanneer de hunter op de kist staat.
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Chest) {
				isHunterOnChest = true;
			}
		}

	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;

	}

	public int getAmmo() {
		return ammo;
	}

}
