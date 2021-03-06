package pt.tecnico.aasma.wireflag.agent.architecture.deliberative.intention;

import java.util.LinkedList;

import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.Beliefs;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.action.Action;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.plan.Plan;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.plan.SurviveFirePlan;
import pt.tecnico.aasma.wireflag.util.position.MapPosition;

public class SurviveFireIntention extends Intention {

	@Override
	protected int getIntentionId() {
		return FIRE;
	}

	@Override
	public boolean suceeded(LinkedList<Action> actions, Beliefs beliefs) {
		return !beliefs.getWorldState(beliefs.getAgentPos().getX(),
				beliefs.getAgentPos().getY()).hasFire();
	}

	@Override
	public boolean impossible(LinkedList<Action> actions, Beliefs beliefs) {
		MapPosition pos;
		for (Action action : actions) {
			pos = action.getPos();
			if (beliefs.blockedWay(pos.getX(), pos.getY())
					&& !beliefs.getWorldState(pos.getX(), pos.getY()).hasFire()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Plan getPlan(Beliefs beliefs) {
		return new SurviveFirePlan(beliefs);
	}

}
