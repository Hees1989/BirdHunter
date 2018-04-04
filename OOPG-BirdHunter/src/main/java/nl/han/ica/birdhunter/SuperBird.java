package nl.han.ica.birdhunter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class SuperBird extends Bird {
	
	/**
	 * Constructor
	 * @param bh referentie van BirdHunter.
	 * @param speed snelheid van de vogel.
	 */

	public SuperBird(BirdHunter bh, int speed) {
		super(bh, speed, new Sprite("src/main/java/nl/han/ica/birdhunter/media/super-bird.png"));
	}
}
