package org.avr.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.avr.beans.Team;
import org.avr.daos.TeamDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;




/**
 * Spring example using a JDBCTemplate.  
 * ==>  Spring handles all the boilerplate code
 * @author axviareque
 *
 */
public class TeamDAOderbyImpl implements TeamDAO {
	private JdbcTemplate jdbcTemplate;
	
	
	public void setDataSrc(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	

	@Override
	public Team getTeamByID(int i) {
		Team t = this.jdbcTemplate.queryForObject(
				"select prsn_id , name_first from person where prsn_id = ? "
				, new Object[] { 1L }
				, new RowMapper<Team>() {
					public Team mapRow(ResultSet rs , int rowNum) throws SQLException {
						Team tm = new Team();
						tm.setTeamID( rs.getInt("prsn_id") );
						tm.setTeamName( rs.getString("name_first") );
						return tm;
					}
				} );
		return t;
	}

	@Override
	public List<Team> getTeams() {
		// TODO Auto-generated method stub
		return null;
	}

}
