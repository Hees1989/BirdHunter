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
	
	/**
	 * Constructor
	 * @param bh referentie van birdhunter
	 * @param birdsPerSecond vogels per seconde
	 * @param speed vliegsnelheid van de kraai
	 * @param speedSuperBird vliegsnelheid van de supervogel
	 */

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

	/* (non-Javadoc)
	 * @see nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener#triggerAlarm(java.lang.String)
	 */
	@Override
	public void triggerAlarm(String alarmName) {
		boolean isGamePaused = bh.getThreadState();
		if (!isGamePaused) {
			isSuperBird = Math.random() < 0.1;
			if (isSuperBird) {
				Bird superBird = new SuperBird(bh, speedSuperBird);
				bh.addGameObject(superBird);
			} else {
				Bird bird = new Crow(bh, speed);
				bh.addGameObject(bird);
			}

		}
		startAlarm();
	}

	/**
	 * @return speed
	 * 		Retourneert de snelheid van de vogel
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 * 		Stel de snelheid van de vogel in
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return speedSuperBird
	 * 		Retourneert snelheid van de supervogel
	 */
	public int getspeedSuperBird() {
		return speedSuperBird;
	}

	public void setSpeedSuperBird(int speedSuperBird) {
		this.speedSuperBird = speedSuperBird;
	}
	
	public float getbirdsPerSecond() {
		return birdsPerSecond;
	}
	
	public void setbirdsPerSecond(float birdsPerSecond) {
		this.birdsPerSecond = birdsPerSecond;
	}

}
