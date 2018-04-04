package nl.han.ica.birdhunter;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class BirdSpawner implements IAlarmListener {

	private float birdsPerSecond;
	private BirdHunter bh;
	private int speed;
	private int speedSuperBird;
	private boolean isSuperBird;

	public BirdSpawner(BirdHunter bh, float birdsPerSecond, int speed, int speedSuperBird) {
		this.bh = bh;
		this.birdsPerSecond = birdsPerSecond;
		this.speed = speed;
		this.speedSuperBird = speedSuperBird;
		startAlarm();

	}

	private void startAlarm() {
		Alarm alarm = new Alarm("New bird", 10 / birdsPerSecond);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		boolean isGamePaused = bh.getThreadState();
		if (!isGamePaused) {
			isSuperBird = Math.random() < 0.1;
			if(isSuperBird) {
			Bird b1 = new Bird(bh, speedSuperBird, new Sprite("src/main/java/nl/han/ica/birdhunter/media/super-bird.png"));
			bh.addGameObject(b1);
			}else {
			Bird b2 = new Bird(bh, speed, new Sprite("src/main/java/nl/han/ica/birdhunter/media/bird.png"));
			bh.addGameObject(b2);
			}

		}
		startAlarm();
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	
}
