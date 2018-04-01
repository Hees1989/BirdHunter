package nl.han.ica.birdhunter;

import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.waterworld.Player;
import nl.han.ica.waterworld.Swordfish;

public class Bird extends AnimatedSpriteObject implements ICollidableWithGameObjects {

	private BirdHunter bh;
	private boolean vanRechts;
	private Random random;
	private float birdY;
	private boolean isShot;
	private int speed;

	public Bird(BirdHunter world, int speed) {
		super(new Sprite("src/main/java/nl/han/ica/birdhunter/media/bird.png"), 4);
		this.bh = world;
		this.speed = speed;
		random = new Random();
		setCurrentFrameIndex(1);
		vanRechts = Math.random() < 0.5;
		direction();
		
	}

	private void direction() {
		birdY = random.nextInt(250) + 10;
		setY(birdY);
		if (vanRechts) {
			setX(0);
			setCurrentFrameIndex(2);
		} else {
			setX(1920);
			setCurrentFrameIndex(1);
		}

	}

	@Override
	public void update() {
		if (vanRechts) {
			setDirectionSpeed(90, speed);
			if (getX() > bh.getWidth()) {
				bh.deleteGameObject(this);
			}
			
			if(isShot) {
				setDirectionSpeed(160, 20);
				setCurrentFrameIndex(3);
				if (getY() > bh.getHeight() - 200) {
					bh.deleteGameObject(this);
				}
			}

		} else {
			setDirectionSpeed(270, speed);
			if (getX() < -100) {
				bh.deleteGameObject(this);
			}
			
			if(isShot) {
				setDirectionSpeed(200, 20);
				setCurrentFrameIndex(0);
				if (getY() > bh.getHeight() - 200) {
					bh.deleteGameObject(this);
				}
			}
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Bullet) {
				isShot = true;
			}
		}
	}

}
