package nl.han.ica.birdhunter;

import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.swing.JOptionPane;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;

@SuppressWarnings("serial")
public class BirdHunter extends GameEngine {
	private boolean isGamePaused = false;
	private Sound hitSound, backgroundNormal, backgroundDark;
	private Hunter h;
	private IPersistence persistence;
	private int countDown = 60;
	private int numberOfHits = 0;
	private int ammo;
	private int level = 1;
	private int seconds = 60;
	private Menu menu;
	private TextObject scoreText, ammoText, levelText, resumeText;
	private BirdSpawner bs;
	private Thread t;
	private View viewNormal, viewDark;
	private boolean isDark;
	private Timer timer;
	private float birdsPerSecond;

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
		initializePersistence();
		intializeObjects();
	}
	
	@Override
	public void update() {
		refreshDashboard();
		ammo = h.getAmmo();
		soundLoop();	
	}

	private void createMenu() {
		Dashboard menu = new Dashboard(this.getWidth() / 2 - 200, this.getHeight() / 2 - 300, 400, 400);
		this.addDashboard(menu);
	}

	private void initializePersistence() {
		persistence = new FilePersistence("main/java/nl/han/ica/birdhunter/media/saveGame.txt");
		if (persistence.fileExists()) {
			level = Integer.parseInt(persistence.loadDataString());
		}
	}

	private void createView(int worldWidth, int worldHeight) {
		viewNormal = new View(worldWidth, worldHeight);
		viewDark = new View(worldWidth, worldHeight);
		size(worldWidth, worldHeight);
		viewNormal.setBackground(loadImage("src/main/java/nl/han/ica/birdhunter/media/background1.jpg"));
		viewDark.setBackground(loadImage("src/main/java/nl/han/ica/birdhunter/media/dark-background.jpg"));
		setView(viewNormal);
	}
	
	private void createDashboard(int dashboardWidth, int dashboardHeight) {
		Dashboard db = new Dashboard(0, 0, dashboardWidth, dashboardHeight);

		scoreText = new TextObject("score: " + numberOfHits, 20, 0);
		ammoText = new TextObject("ammo: " + ammo, 450, 0);
		levelText = new TextObject("level: " + level, 700, 0);

		db.addGameObject(scoreText);
		db.addGameObject(ammoText);
		db.addGameObject(levelText);
		this.addDashboard(db);
	}

	private void initializeSounds() {
		backgroundDark = new Sound(this, "src/main/java/nl/han/ica/birdhunter/media/dark-background.mp3");
		backgroundNormal = new Sound(this, "src/main/java/nl/han/ica/birdhunter/media/background-music2.mp3");
	}
	
	private void soundLoop() {
		isDark = menu.isDark();

		if (isDark) {
			setView(viewDark);
			backgroundNormal.pause();
			if(!backgroundDark.isPlaying()) {
				backgroundDark.loop(-1);
			}
			

		} else {
			setView(viewNormal);
			backgroundDark.pause();
			if(!backgroundNormal.isPlaying()) {
				backgroundNormal.loop(-1);
			}

		}
	}

	private void intializeObjects() {
		Chest c = new Chest(this);
		addGameObject(c, 50, height - height / 4);
		birdsPerSecond = 25;
		bs = new BirdSpawner(this, birdsPerSecond, level+1,  15);
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
		if (numberOfHits != 19) {
			numberOfHits++;
		} else {
			if(birdsPerSecond > 4) {
				birdsPerSecond = birdsPerSecond -2;
			}
			numberOfHits = 0;
			level++;
			bs.setbirdsPerSecond(birdsPerSecond);
			bs.setSpeed((level * 2) - 1);
			bs.setSpeedSuperBird(level * 3);
			persistence.saveData(Integer.toString(level));
		}
	}

	private void refreshDashboard() {
		scoreText.setText("score: " + numberOfHits + "/" + 20);
		ammoText.setText("ammo: " + ammo);
		levelText.setText("level: " + level);
	}

}
