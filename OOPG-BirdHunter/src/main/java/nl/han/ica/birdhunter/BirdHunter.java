package nl.han.ica.birdhunter;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;

@SuppressWarnings("serial")
public class BirdHunter extends GameEngine {
	Sound hitSound;
	int countDown = 60;
	int numberOfHits = 0;
	boolean gameOn;
	TextObject scoreText;
	TextObject timeText;
	Timer timer;

	public static void main(String[] args) {
		BirdHunter bh = new BirdHunter();
		bh.runSketch();
	}
	
	@Override
	public void setupGame() {
		int worldWidth = 1200;
		int worldHeight = 675;
		
		createView(worldWidth, worldHeight);
		createDashboard(worldWidth, 50);
		initializeSounds();
		startGame();
	
		BirdSpawner bird = new BirdSpawner(this, 50);
		Hunter h = new Hunter(this);
		addGameObject(h, 100, 370);
	}

	private void startGame() {
		gameOn = true;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			int seconds = 60;
			
			@Override
			public void run() {
				timeText.setText("" + seconds);
				seconds --;
			}
		}, 0, 1000);
	}

	@Override
	public void update() {
		
	}

	private void createView(int worldWidth, int worldHeight) {
		View view = new View(worldWidth, worldHeight);
		size(worldWidth, worldHeight);
		view.setBackground(loadImage("src/main/java/nl/han/ica/birdhunter/media/background2.jpg"));
		setView(view);
	}
	
	private void createDashboard(int dashboardWidth, int dashboardHeight) {
		Dashboard db = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
		scoreText = new TextObject("score", 20, 0);
		timeText = new TextObject("test", 220, 0);
		db.setBackground(255, 0, 0);
		db.addGameObject(scoreText);
		db.addGameObject(timeText);
		this.addDashboard(db);
	}
	
	private void initializeSounds() {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_E) {
			if (gameOn) {
				this.pauseGame();
				gameOn = false;
			} else {
				this.resumeGame();
				gameOn = true;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			this.resumeGame();
		}
	}

	public void increaseHits() {
		numberOfHits++;
		refreshDashboard();
	}

	private void refreshDashboard() {
		scoreText.setText("" + numberOfHits);
	}
}
