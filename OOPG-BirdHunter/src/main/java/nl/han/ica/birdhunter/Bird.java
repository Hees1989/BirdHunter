package nl.han.ica.birdhunter;

import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.waterworld.Player;
import nl.han.ica.waterworld.Swordfish;

public class Bird extends AnimatedSpriteObject {

	private BirdHunter world;
	private boolean vanRechts;
	private Random random;
	private float birdY;
	private boolean isShot;

	public Bird(BirdHunter world) {
		super(new Sprite("src/main/java/nl/han/ica/birdhunter/media/bird.png"), 4);
		this.world = world;
		random = new Random();
		setCurrentFrameIndex(1);
		vanRechts = Math.random() < 0.5;
		direction();

	}

	private void direction() {
		birdY = random.nextInt(300) + 10;
		setY(birdY);
		if (vanRechts) {
			setX(0);
			setCurrentFrameIndex(2);
		} else {
			setX(1920);
			setCurrentFrameIndex(1);
		}

	}

	/**
	 * Maak een Bird aan met een sprite
	 * 
	 * @param sprite
	 *            De sprite die aan dit object gekoppeld moet worden
	 */

	@Override
	public void update() {
		if (vanRechts) {
			setDirectionSpeed(90, 7);
			if (getX() > world.getWidth()) {
				world.deleteGameObject(this);
			}
			
			if(isShot) {
				setDirectionSpeed(160, 20);
				setCurrentFrameIndex(3);
				if (getY() > world.getHeight() - 200) {
					world.deleteGameObject(this);
				}
			}

		} else {
			setDirectionSpeed(270, 7);
			if (getX() < -100) {
				world.deleteGameObject(this);
			}
			
			if(isShot) {
				setDirectionSpeed(200, 20);
				setCurrentFrameIndex(0);
				if (getY() > world.getHeight() - 200) {
					world.deleteGameObject(this);
				}
			}
		}
	}

	public void keyPressed(int keyCode, char key) {
		final int speed = 5;
		if (keyCode == world.DOWN) {
			isShot = true;
		}

	}

	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			// if (g instanceof Bullet) {
			world.deleteGameObject(this);
			setDirectionSpeed(180, 10);
			// }

		}
	}

}
