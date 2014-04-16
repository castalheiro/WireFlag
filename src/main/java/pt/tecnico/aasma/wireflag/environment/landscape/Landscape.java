package pt.tecnico.aasma.wireflag.environment.landscape;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import pt.tecnico.aasma.wireflag.GameElement;
import pt.tecnico.aasma.wireflag.agent.Agent;
import pt.tecnico.aasma.wireflag.environment.Animal;
import pt.tecnico.aasma.wireflag.environment.Fire;
import pt.tecnico.aasma.wireflag.environment.Flag;
import pt.tecnico.aasma.wireflag.environment.weather.Sunny;
import pt.tecnico.aasma.wireflag.environment.weather.Weather;

public abstract class Landscape implements GameElement {

	protected final static float NORMALSPD = 1.0f;
	protected final static float REDUCEDSPD = 0.5f;
	protected final static float VREDUCEDSPD = 0.1f;
	protected final static float NOSPD = 0f;

	protected int xCoord;
	protected int yCoord;
	protected float movementSpeed;
	protected Weather weather;
	protected Flag flag;
	protected Agent agent;
	protected Fire fire;
	protected Animal animal;

	public Landscape(float movementSpeed, int xCoord, int yCoord) {
		this.movementSpeed = movementSpeed;
		weather = new Sunny(0, xCoord, yCoord);
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	public boolean hasAgent() {
		return agent != null;
	}

	public boolean hasFlag() {
		return flag != null;
	}

	public boolean hasFire() {
		return fire != null;
	}

	public boolean hasAnimal() {
		return animal != null;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public void setFlag(Flag flag) {
		this.flag = flag;
	}

	public void setOnFire(Fire fire) {
		this.fire = fire;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public float getMovementSpeed() {
		return movementSpeed;
	}

	public Weather getWeather() {
		return weather;
	}

	public abstract void setExtremeWeather(int duration) throws SlickException;
	public Agent getAgent() {
		return agent;
	}

	public void setSunnyWeather() {
		weather = new Sunny(0, xCoord, yCoord);
	}

	@Override
	public void init() {
	}

	@Override
	public void update(int delta) {

		if (hasFire()) {
			fire.update(delta);
		}

		if (hasAgent()) {
			agent.update(delta);
		}

		weather.update(delta);
	}

	@Override
	public void render(Graphics g) {
		weather.render(g);

		if (hasFire()) {
			fire.render(g);
		}

		if (hasFlag()) {
			flag.render(g);
		}

		if (hasAgent()) {
			agent.render(g);
		}
	}
}
