package pt.tecnico.aasma.wireflag.environment.controller;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import pt.tecnico.aasma.wireflag.agent.Agent;
import pt.tecnico.aasma.wireflag.agent.architecture.Reactive;
import pt.tecnico.aasma.wireflag.agent.team.DemocraticalTeam;
import pt.tecnico.aasma.wireflag.agent.team.Team;
import pt.tecnico.aasma.wireflag.agent.type.Builder;
import pt.tecnico.aasma.wireflag.agent.type.Doctor;
import pt.tecnico.aasma.wireflag.agent.type.Patrol;
import pt.tecnico.aasma.wireflag.agent.type.Soldier;
import pt.tecnico.aasma.wireflag.exception.InvalidTeamSizeException;

import java.util.ArrayList;
import java.util.List;

public class AgentController implements IController {

	private int actualTeamId;
	private List<Team> teams = new ArrayList<Team>();
	private static final AgentController INSTANCE = new AgentController();

	private AgentController() {
		actualTeamId = 0;
	}

	/***************
	 *** GETTERS ***
	 ***************/

	public static AgentController getAgents() {
		return INSTANCE;
	}

	public int getNextTeamId() {
		return actualTeamId++;
	}

	public List<Team> getTeams() {
		return teams;
	}

    /**
     *  FIXME: If TeamController is going to be use, this method should go there.
     *
     * Gets the team matching the id.
     *
     * @param id the ID of the team to be retrieved
     * @return the actual team or null if not founds
     */
    public Team getTeamById(int id) {
        for (Team team : teams) {
            if (team.getID() == id) {
                return team;
            }
        }
        return null;
    }

	/***********************
	 *** STATE MODIFIERS ***
	 **********************/

	public void addTeam(Team team) {
		teams.add(team);
	}

	/**********************
	 *** GAME RELATED *****
	 **********************/

	@Override
	public void init() throws SlickException, InvalidTeamSizeException {

		Team t1 = new DemocraticalTeam(getNextTeamId());
		Agent d1 = new Doctor(t1.getID(), t1.getMemberID(), new Reactive());
		Agent b1 = new Builder(t1.getID(), t1.getMemberID(), new Reactive());
		Agent p1 = new Patrol(t1.getID(), t1.getMemberID(), new Reactive());
		Agent s1 = new Soldier(t1.getID(), t1.getMemberID(), new Reactive());
		t1.addAgent(b1);
		t1.addAgent(d1);
		t1.addAgent(p1);
		t1.addAgent(s1);
		t1.setTeamUp();
		addTeam(t1);

		Team t2 = new DemocraticalTeam(getNextTeamId());
		Agent d2 = new Doctor(t2.getID(), t2.getMemberID(), new Reactive());
		Agent b2 = new Builder(t2.getID(), t2.getMemberID(), new Reactive());
		Agent p2 = new Patrol(t2.getID(), t2.getMemberID(), new Reactive());
		Agent s2 = new Soldier(t2.getID(), t2.getMemberID(), new Reactive());
		t2.addAgent(b2);
		t2.addAgent(d2);
		t2.addAgent(p2);
		t2.addAgent(s2);
		t2.setTeamUp();
		addTeam(t2);

		Team t3 = new DemocraticalTeam(getNextTeamId());
		Agent d3 = new Doctor(t3.getID(), t3.getMemberID(), new Reactive());
		Agent b3 = new Builder(t3.getID(), t3.getMemberID(), new Reactive());
		Agent p3 = new Patrol(t3.getID(), t3.getMemberID(), new Reactive());
		Agent s3 = new Soldier(t3.getID(), t3.getMemberID(), new Reactive());
		t3.addAgent(b3);
		t3.addAgent(d3);
		t3.addAgent(p3);
		t3.addAgent(s3);
		t3.setTeamUp();
		addTeam(t3);

		Team t4 = new DemocraticalTeam(getNextTeamId());
		Agent d4 = new Doctor(t4.getID(), t4.getMemberID(), new Reactive());
		Agent b4 = new Builder(t4.getID(), t4.getMemberID(), new Reactive());
		Agent p4 = new Patrol(t4.getID(), t4.getMemberID(), new Reactive());
		Agent s4 = new Soldier(t4.getID(), t4.getMemberID(), new Reactive());
		t4.addAgent(b4);
		t4.addAgent(d4);
		t4.addAgent(p4);
		t4.addAgent(s4);
		t4.setTeamUp();
		addTeam(t4);

		for (Team t : getTeams()) {
			for (Agent a : t.getMembers()) {
				a.init();
			}
		}

		/*
		 * Agent leader = agentController.getAgents().get(0); List<Agent>
		 * members = agentController.getAgents().subList(1,
		 * agentController.getAgents().size());
		 * 
		 * try { teamController.createDemocraticalTeam(leader, members); Team
		 * team = teamController.getTeams().get(0);
		 * 
		 * MapController.getMap().getLandscape(team.getTeamPosition())
		 * .setAgent(team.getLeader()); for (Agent agent : team.getMembers()) {
		 * MapController.getMap().getLandscape(team.getTeamPosition())
		 * .setAgent(agent); } } catch (InvalidTeamSizeException e1) {
		 * e1.printStackTrace(); }
		 */

	}

	public void update(int delta) {
		for (Team t : getTeams()) {
			for (Agent a : t.getMembers()) {
				a.getAgentThread().setDelta(delta);
			}
		}
	}

	@Override
	public void render(Graphics g) {
	}

}
