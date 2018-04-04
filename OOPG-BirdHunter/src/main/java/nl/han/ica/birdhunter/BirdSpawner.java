package nl.han.ica.birdhunter;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class BirdSpawner implements IAlarmListener {

	private float birdsPerSecond;
	private BirdHunter bh;
	private int speed;

	public BirdSpawner(BirdHunter bh, float birdsPerSecond, int speed) {
		this.bh = bh;
		this.birdsPerSecond = birdsPerSecond;
		this.speed = speed;
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
			Bird b = new Bird(bh, speed);
			bh.addGameObject(b);
		}
		startAlarm();
	}

}
