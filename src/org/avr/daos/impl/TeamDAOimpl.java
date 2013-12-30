package org.avr.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.avr.beans.Team;
import org.avr.daos.TeamDAO;

public class TeamDAOimpl implements TeamDAO {
	private DataSource dataSrc;
		
	
	
	public TeamDAOimpl() {
	}
	
	public void setDataSrc(DataSource ds) {
		this.dataSrc = ds;
	}

	@Override
	public Team getTeamByID(int i) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String qry = "select name from Teams_R where id= ? ";
		
		try {
			conn = this.dataSrc.getConnection();
			ps = conn.prepareStatement(qry);
			ps.setInt( 1 , i );
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Team t = new Team();
				t.setTeamID( i );
				t.setTeamName( rs.getString(1));
				return t;
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Team> getTeams() {
		List<Team> teams = new ArrayList<Team>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String qry = "select id , name from Teams_R";
		
		try {
			conn = this.dataSrc.getConnection();
			ps = conn.prepareStatement(qry);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Team t = new Team();
				t.setTeamID( rs.getInt(1) );
				t.setTeamName( rs.getString(2));
				teams.add( t );
			}
			return teams;
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}
		return null;
	}

}
