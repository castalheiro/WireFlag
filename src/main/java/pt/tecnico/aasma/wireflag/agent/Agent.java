package pt.tecnico.aasma.wireflag.agent;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import pt.tecnico.aasma.wireflag.GameElement;
import pt.tecnico.aasma.wireflag.WireFlagGame;
import pt.tecnico.aasma.wireflag.environment.controller.MapController;

public class Agent implements GameElement {

	private Animation up;
	private Animation down;
	private Animation right;
	private Animation left;
	private Animation sprite;
	private int play;
	private float x;
	private float y;
	private Random random;

	public Agent() {
		random = new Random();
	}

	public void update(int delta) {

		if (random.nextInt(10000) > 9990)
			play = random.nextInt(4);

		if (play == 0) {
			sprite = up;
			if (!MapController.getMap().isBlocked(x, y - delta * 0.05f)) {
				moveUp(delta);
			} else {
				play = random.nextInt(4);
			}
		} else if (play == 1) {
			sprite = down;
			if (!MapController.getMap().isBlocked(x,
					y + MapController.getMap().getNTiles() + delta * 0.05f)) {
				moveDown(delta);
			} else {
				play = random.nextInt(4);
			}
		} else if (play == 2) {
			sprite = left;
			if (!MapController.getMap().isBlocked(x - delta * 0.05f, y)) {
				moveLeft(delta);
			} else {
				play = random.nextInt(4);
			}

		} else if (play == 3) {
			sprite = right;
			if (!MapController.getMap().isBlocked(
					x + MapController.getMap().getNTiles() + delta * 0.05f, y)) {
				moveRight(delta);
			} else {
				play = random.nextInt(4);
			}
		}

	}

	public void moveDown(int delta) {
		MapController.getMap().getLandscape(x, y).setAgent(null);
		sprite.update(delta);
		y += delta * 0.05f
				* MapController.getMap().getMovementSpeed(x, y + delta * 0.05f);
		MapController.getMap().getLandscape(x, y).setAgent(this);
	}

	public void moveUp(int delta) {
		MapController.getMap().getLandscape(x, y).setAgent(null);
		sprite.update(delta);
		y -= delta * 0.05f
				* MapController.getMap().getMovementSpeed(x, y - delta * 0.05f);
		MapController.getMap().getLandscape(x, y).setAgent(this);
	}

	public void moveRight(int delta) {
		MapController.getMap().getLandscape(x, y).setAgent(null);
		sprite.update(delta);
		x += delta * 0.05f
				* MapController.getMap().getMovementSpeed(x + delta * 0.05f, y);
		MapController.getMap().getLandscape(x, y).setAgent(this);
	}

	public void moveLeft(int delta) {
		MapController.getMap().getLandscape(x, y).setAgent(null);
		sprite.update(delta);
		x -= delta * 0.05f
				* MapController.getMap().getMovementSpeed(x - delta * 0.05f, y);
		MapController.getMap().getLandscape(x, y).setAgent(this);
	}

	public void init() throws SlickException {

		Image[] movementUp = { new Image("data/grey-back.png") };
		Image[] movementDown = { new Image("data/grey-front.png") };
		Image[] movementLeft = { new Image("data/grey-left.png") };
		Image[] movementRight = { new Image("data/grey-right.png") };
		int[] duration = { 300 };

		up = new Animation(movementUp, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		right = new Animation(movementRight, duration, false);

		sprite = right;

		play = 0;
		x = 34f;
		y = 34f;
	}

	@Override
	public void render(Graphics g) {
		sprite.draw((int) x, (int) y);

		g.setColor(new Color(1f, 1f, 1f, 0.4f));
		Circle circle = new Circle(x + 15, y + 15, 40.5f);

		g.draw(circle);
		// g.setColor(Color.transparent);
		g.fill(circle);
	}

}