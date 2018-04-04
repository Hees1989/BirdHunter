package nl.han.ica.birdhunter;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;

@SuppressWarnings("serial")
public class BirdHunter extends GameEngine {
	boolean isGamePaused = false;
	Sound hitSound;
	Hunter h;
	IPersistence persistence;
	int countDown = 60;
	int numberOfHits = 0;
	int ammo;
	int level = 1;
	int seconds = 60;
	Menu menu;
	TextObject scoreText;
	TextObject timeText;
	TextObject ammoText;
	TextObject levelText;
	BirdSpawner bs;
	
	TextObject resumeText;
	
	Timer timer;
	Sound backgroundSound;

	public static void main(String[] args) {
		BirdHunter bh = new BirdHunter();
		bh.runSketch();
	}

	@Override
	public void setupGame() {
		int worldWidth = 1366;
		int worldHeight = 768;

		createView(worldWidth, worldHeight);
		createDashboard(worldWidth, 50);
		createMenu();
		initializeSounds();
		intializeObjects();
		initializePersistence();
		startTimer();
	}

	private void createMenu() {
		Dashboard menu = new Dashboard(this.getWidth() / 2 - 200, this.getHeight() / 2 - 300, 400, 400);
		//menu.setBackground(0, 0, 0);
		this.addDashboard(menu);
	}

	private void initializePersistence() {
		persistence = new FilePersistence("main/java/nl/han/ica/birdhunter/media/saveGame.txt");
		if (persistence.fileExists()) {
			level = Integer.parseInt(persistence.loadDataString());
		}
	}

	private void startTimer() {
		timer = new Timer();
		isGamePaused = true;
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				timeText.setText("Time left: " + seconds);
				if (!isGamePaused) {
					
				} else if (seconds != 0) {
					seconds--;
				} else {
					JOptionPane.showMessageDialog(frame, "Helaas.. De tijd is op");
					persistence.saveData(Integer.toString(1));
					System.exit(0);
				}
			}
		}, 0, 1000);
	}

	@Override
	public void update() {
		refreshDashboard();
		ammo = h.getAmmo();
	}

	private void createView(int worldWidth, int worldHeight) {
		View view = new View(worldWidth, worldHeight);
		size(worldWidth, worldHeight);
		view.setBackground(loadImage("src/main/java/nl/han/ica/birdhunter/media/dark-background.jpg"));
		setView(view);
	}

	private void createDashboard(int dashboardWidth, int dashboardHeight) {
		Dashboard db = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
		
		scoreText = new TextObject("score: " + numberOfHits, 20, 0);
		timeText = new TextObject("Time left: ", 200, 0);
		ammoText = new TextObject("ammo: " + ammo, 450, 0);
		levelText = new TextObject("level: " + level, 700, 0);
		
		db.addGameObject(scoreText);
		db.addGameObject(timeText);
		db.addGameObject(ammoText);
		db.addGameObject(levelText);
		
		this.addDashboard(db);
	}

	private void initializeSounds() {
		backgroundSound = new Sound(this, "src/main/java/nl/han/ica/birdhunter/media/dark-background.mp3");
		backgroundSound.loop(-1);

	}

	private void intializeObjects() {
		Chest c = new Chest(this);
		addGameObject(c, 50, height - height / 4);
		bs = new BirdSpawner(this, 50, 5, 10);
		h = new Hunter(this);
		addGameObject(h, width / 2, height - (height / 3) - 15);
		menu = new Menu(this);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isGamePaused = this.getThreadState();
		if (e.getKeyCode() == KeyEvent.VK_E) {
			if (!isGamePaused) {
				this.pauseGame();
				addGameObject(menu);
			} else {
				this.resumeGame();
				deleteGameObject(menu);
			}
		}
	}

	public void increaseHits() {
		if (numberOfHits != level * 10 - 1) {
			numberOfHits++;
		} else {
			numberOfHits = 0;
			level++;
			bs.setSpeed(level);
			seconds = 60;
			persistence.saveData(Integer.toString(level));
		}
	}

	private void refreshDashboard() {
		scoreText.setText("score: " + numberOfHits + "/" + level * 10);
		ammoText.setText("ammo: " + ammo);
		levelText.setText("level: " + level);
	}

}
