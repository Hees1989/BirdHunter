package nl.han.ica.birdhunter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Crow extends Bird{
	
	/**
	 * Constructor
	 * @param bh referentie van BirdHunter.
	 * @param speed de snelheid van de vogel.
	 */
	public Crow(BirdHunter bh, int speed) {
		super(bh, speed, new Sprite("src/main/java/nl/han/ica/birdhunter/media/bird.png"));
	}

}
