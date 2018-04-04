package nl.han.ica.birdhunter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Chest extends SpriteObject {


	/**
	 * Constructor
	 * @param bh referentie van BirdHunter
	 */
	public Chest() {
		super(new Sprite("src/main/java/nl/han/ica/birdhunter/media/chest.png"));
	}

	/* (non-Javadoc)
	 * @see nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject#update()
	 */
	@Override
	public void update() {
	}

}
