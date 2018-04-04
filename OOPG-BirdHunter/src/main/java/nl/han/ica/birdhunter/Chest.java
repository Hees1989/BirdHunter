package nl.han.ica.birdhunter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Chest extends SpriteObject {

	private BirdHunter bh;

	public Chest(BirdHunter bh) {
		super(new Sprite("src/main/java/nl/han/ica/birdhunter/media/chest.png"));
		this.bh = bh;
	}

	@Override
	public void update() {
	}

}
