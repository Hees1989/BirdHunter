package nl.han.ica.birdhunter;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class BirdSpawner implements IAlarmListener {

	private float birdsPerSecond;
	private BirdHunter bh;

	public BirdSpawner(BirdHunter bh, float birdsPerSecond) {
		this.bh = bh;
		this.birdsPerSecond = birdsPerSecond;
		startAlarm();

	}

	private void startAlarm() {
		Alarm alarm = new Alarm("New bird", 10 / birdsPerSecond);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		Bird b = new Bird(bh);
		bh.addGameObject(b);
		startAlarm();
	}

}
