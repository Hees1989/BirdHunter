package nl.han.ica.birdhunter;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.waterworld.tiles.BoardsTile;
import processing.core.PVector;

public class Hunter extends AnimatedSpriteObject {
	
	private BirdHunter bh;
	Sound hitSound;
	
	public Hunter(BirdHunter bh) {
		super(new Sprite("src/main/java/nl/han/ica/birdhunter/media/hunter-sprite.png"),3);
		this.bh = bh;
		setCurrentFrameIndex(1);
		setFriction(0.1f);
		hitSound = new Sound(bh, "src/main/java/nl/han/ica/birdhunter/media/pop.mp3");
	}
	
	@Override
	public void update() {
        if (getX()<=0) {
            setxSpeed(0);
            setX(0);
        }
        if (getX()>=bh.getWidth()-100) {
            setxSpeed(0);
            setX(bh.getWidth() - 100);
        }

    }
	
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
	        	Bullet b = new Bullet(bh, hitSound);
	    		bh.addGameObject(b, getX() + 55, getY());
	        }
		}
    }
}
