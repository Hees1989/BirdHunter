package nl.han.ica.birdhunter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.waterworld.Bubble;
import nl.han.ica.waterworld.tiles.BoardsTile;
import processing.core.PVector;

public class Hunter extends AnimatedSpriteObject implements ICollidableWithGameObjects {

	private BirdHunter bh;
	private Sound bulletSound;
	private Sound reload;
	private boolean isHunterOnChest;
	private int ammo;
	private int setAmmo;

	public Hunter(BirdHunter bh) {
		super(new Sprite("src/main/java/nl/han/ica/birdhunter/media/hunter-sprite.png"), 3);
		this.bh = bh;
		setCurrentFrameIndex(1);
		setFriction(0.1f);
		setAmmo = 10;
		ammo = setAmmo;
		bulletSound = new Sound(bh, "src/main/java/nl/han/ica/birdhunter/media/gun.mp3");
		reload = new Sound(bh, "src/main/java/nl/han/ica/birdhunter/media/reload.mp3");

	}

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

	@Override
	public void keyPressed(int keyCode, char key) {
		boolean isGamePaused = bh.getThreadState();

		if (!isGamePaused) {
			if (keyCode == bh.LEFT) {
				setDirectionSpeed(270, 15);
				setCurrentFrameIndex(0);
			}

			if (keyCode == bh.RIGHT) {
				setDirectionSpeed(90, 15);
				setCurrentFrameIndex(2);
			}

			if (key == ' ') {
				setCurrentFrameIndex(1);
				if (ammo > 0 && !isHunterOnChest) {
					Bullet b = new Bullet(bh);
					;
					bulletSound.rewind();
					bulletSound.play();
					ammo--;
					bh.addGameObject(b, getX() + 55, getY());
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
