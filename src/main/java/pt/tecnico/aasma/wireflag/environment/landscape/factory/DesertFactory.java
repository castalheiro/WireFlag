package pt.tecnico.aasma.wireflag.environment.landscape.factory;

import pt.tecnico.aasma.wireflag.environment.landscape.Desert;
import pt.tecnico.aasma.wireflag.environment.landscape.Landscape;

public class DesertFactory extends LandscapeFactory {

	@Override
	public Landscape createLandscape() {
		return new Desert();
	}
}
