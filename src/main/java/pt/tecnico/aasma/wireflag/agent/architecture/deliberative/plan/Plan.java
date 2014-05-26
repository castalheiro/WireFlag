package pt.tecnico.aasma.wireflag.agent.architecture.deliberative.plan;

import java.util.LinkedList;
import java.util.Random;

import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.Beliefs;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.action.Action;
import pt.tecnico.aasma.wireflag.agent.architecture.deliberative.action.sequence.ActionSequence;
import pt.tecnico.aasma.wireflag.test.DeliberativeArchTest;
import pt.tecnico.aasma.wireflag.util.position.MapPosition;

public abstract class Plan {

	protected LinkedList<ActionSequence> actSequences;
	private double[][] usedPerception;
	Beliefs beliefs;
	int xCoords[];
	int yCoords[];
	int coords[];

	public Plan(Beliefs beliefs) {
		xCoords = new int[] { 1, -1, 0, 0 };
		yCoords = new int[] { 0, 0, 1, -1 };
		coords = new int[] { 0, 1, 2, 3 };

		actSequences = new LinkedList<ActionSequence>();
		usedPerception = new double[beliefs.getHorizontalSize()][beliefs
				.getVerticalSize()];
		this.beliefs = beliefs;

		for (int i = 0; i < beliefs.getHorizontalSize(); i++) {
			for (int j = 0; j < beliefs.getVerticalSize(); j++) {
				if (beliefs.getWorldState(i, j).isBlocked()) {
					usedPerception[i][j] = 0.001;
				}
			}
		}
	}

	public void addAction(ActionSequence actionSeq, int x, int y) {
		MapPosition pos = new MapPosition(actionSeq.getTailPos().getX() + x,
				actionSeq.getTailPos().getY() + y);
		if (pos.isValid() && !(usedPerception[pos.getX()][pos.getY()] > 0)
				&& !actionSeq.isFinished()) {
			createNewAction(pos, actionSeq);
		}
	}

	public LinkedList<Action> makePlan(MapPosition initialPosition) {

		createNewAction(initialPosition, null);

		ActionSequence bestSequence = null;

		while (!actSequences.isEmpty()) {
			ActionSequence a = actSequences.removeFirst();

			if (usedPerception[a.getTailPos().getX()][a.getTailPos().getY()] < a
					.getSequenceValue()) {
				usedPerception[a.getTailPos().getX()][a.getTailPos().getY()] = a
						.getSequenceValue();
			}

			if (a.isFinished()
					&& (bestSequence == null || a.getSequenceValue() > bestSequence
							.getSequenceValue())) {
				if (a.getSequenceValue() >= 0) {
					bestSequence = a;
				}
			} else if (bestSequence != null
					&& (a.getActions().size() > bestSequence.getActions()
							.size())) {
				continue;
			} else if (a.getSequenceValue() < 0) {
				continue;
			}

			shuffleArray(coords);
			for (int i : coords) {
				addAction(a, xCoords[i], yCoords[i]);
			}
		}

		if (bestSequence == null) {
			return new LinkedList<Action>();
		} else {
			return bestSequence.getActions();
		}

	}

	public void shuffleArray(int[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public abstract void createNewAction(MapPosition pos,
			ActionSequence actionSeq);
}
