package pt.tecnico.aasma.wireflag.agent.architecture.deliberative.intention;

import java.util.LinkedList;
import java.util.List;

import pt.tecnico.aasma.wireflag.agent.Agent;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.Beliefs;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.action.Action;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.plan.FleePlan;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.plan.Plan;
import pt.tecnico.aasma.wireflag.util.position.MapPosition;

public class FleeIntention extends Intention {

	@Override
	public boolean suceeded(LinkedList<Action> actions, Beliefs beliefs) {

		return beliefs.getLife() < Agent.LOW_LIFE
				|| Math.abs(beliefs.getEnemyState().getPosition().getX()
						- beliefs.getAgentPos().getX()) > beliefs
							.getAgentVisibilityRange()
				|| Math.abs(beliefs.getEnemyState().getPosition().getY()
						- beliefs.getAgentPos().getY()) > beliefs
							.getAgentVisibilityRange();
	}

	@Override
	public boolean impossible(LinkedList<Action> actions, Beliefs beliefs) {
		MapPosition pos;
		boolean result = false;
		for (Action action : actions) {
			pos = action.getPos();

			result = result || beliefs.blockedWay(pos.getX(), pos.getY());
		}

		return result;
	}

	@Override
	public Plan getPlan(Beliefs beliefs) {
		return new FleePlan(beliefs);
	}

	@Override
	protected int getIntentionId() {
		return FLEE;
	}
}