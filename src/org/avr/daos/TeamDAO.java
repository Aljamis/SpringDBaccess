package org.avr.daos;

import java.util.List;

import org.avr.beans.Team;

public interface TeamDAO {
	
	public Team getTeamByID(int i);
	public List<Team> getTeams();
}
