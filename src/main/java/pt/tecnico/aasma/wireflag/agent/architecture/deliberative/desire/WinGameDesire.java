package pt.tecnico.aasma.wireflag.agent.architecture.deliberative.desire;

import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.Beliefs;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.intention.Intention;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.intention.WinGameIntention;
import pt.tecnico.aasma.wireflag.util.position.MapPosition;

/**
 * 
 * @author Jmcc - Falta perceber se vale a pena truncar a distancia a 20, de
 *         modo a que o rate nunca baixe de 80
 * 
 */

public class WinGameDesire implements Desire {

	@Override
	public double getRate(Beliefs state) {
		MapPosition agentPos = state.getAgentPos();

		if (state.carriesFlag()) {
			return 100 - state.getTeamBase().getDistanceFrom(agentPos) / 3.0;
		} else {
			return 0;
		}
	}

	@Override
	public Intention getIntention() {
		return new WinGameIntention();
	}
}
