package pt.tecnico.aasma.wireflag.environment.landscape.factory;

import pt.tecnico.aasma.wireflag.environment.landscape.Forest;
import pt.tecnico.aasma.wireflag.environment.landscape.Landscape;

public class ForestFactory extends LandscapeFactory {

	@Override
	public Landscape createLandscape() {
		return new Forest();
	}
}