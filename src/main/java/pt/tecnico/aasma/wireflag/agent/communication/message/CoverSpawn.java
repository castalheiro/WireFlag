package pt.tecnico.aasma.wireflag.agent.communication.message;

import pt.tecnico.aasma.wireflag.util.position.MapPosition;

public class CoverSpawn {
	
	public final MapPosition position;
	
	public CoverSpawn(MapPosition position) {
		this.position = position;
	}

}
