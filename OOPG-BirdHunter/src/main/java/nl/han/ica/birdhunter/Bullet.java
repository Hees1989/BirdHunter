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

	public Bullet(BirdHunter bh, Sound hitSound) {
		this.bh = bh;
		this.hitSound = hitSound;
		//setySpeed(-bulletSize/10f);
		setySpeed(-10);
		setWidth(bulletSize);
		setHeight(bulletSize);
	}
	    
	@Override
	public void update() {
		if(getY() < 0) {
			bh.deleteGameObject(this);
		}
		
	}

	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(g.CORNER);
		g.fill(0);
		g.stroke(0);
		g.ellipse(getX(), getY(), bulletSize, bulletSize);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Bird) {
				//hitSound.play();
				//bh.deleteGameObject(this);
				//System.out.println("Hit!");
			}
		}
	}
	
	

}
