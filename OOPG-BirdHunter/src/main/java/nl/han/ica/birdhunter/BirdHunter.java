package nl.han.ica.birdhunter;

import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;

public class BirdHunter extends GameEngine {
	Sound hitSound;
	int countDown = 60;

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
		
		Runnable timerRunnable = new Runnable() {
		    public void run() {
		        System.out.println(countDown);
		        text(countDown, 100, 100);
		        countDown--;
		    }
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(timerRunnable, 0, 1, TimeUnit.SECONDS);
		
		Bullet b = new Bullet(this, hitSound);
		addGameObject(b, 250, this.getHeight());
		BirdSpawner bird = new BirdSpawner(this, 5);
		Hunter h = new Hunter(this);
		 addGameObject(h, 100, 370);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	private void createView(int worldWidth, int worldHeight) {
		View view = new View(worldWidth, worldHeight);
		size(worldWidth, worldHeight);
		view.setBackground(loadImage("src/main/java/nl/han/ica/birdhunter/media/background2.jpg"));
		setView(view);
	}
	
	private void createDashboard(int dashboardWidth, int dashboardHeight) {
		Dashboard db = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
		TextObject timeText = new TextObject("test");
		db.setBackground(255, 0, 0);
		db.addGameObject(timeText);
		this.addDashboard(db);
	}
	
	private void initializeSounds() {
		hitSound = new Sound(this, "src/main/java/nl/han/ica/birdhunter/media/pop.mp3");
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_E) {
			this.pauseGame();
			System.out.println("Paused");
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			this.resumeGame();
			System.out.println("Resume");
		}
	}
}
