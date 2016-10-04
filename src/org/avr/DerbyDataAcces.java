package org.avr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.avr.beans.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



/**
 * Access Derby without an ApplicationContext XML
 * @author axviareque
 *
 */
public class DerbyDataAcces {
	
	public static void main(String[] args) {
		/*
		 */
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setUrl("jdbc:derby:P:/WORKSPACES_win7/OEPE/ApacheDerby/DerbyDb/MyDbTest");
		DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:derby:P:/WORKSPACES_win7/OEPE/ApacheDerby/DerbyDb/MyDbTest");
		
		JdbcTemplate jTemp = new JdbcTemplate(dataSource);
		
		System.out.println("Made it this far");
		
		Team t = jTemp.queryForObject(
				"select prsn_id , name_first from person where prsn_id = ? "
				, new Object[] { 9L }
				, new RowMapper<Team>() {
					public Team mapRow(ResultSet rs , int rowNum) throws SQLException {
						Team tm = new Team();
						tm.setTeamID( rs.getInt("prsn_id") );
						tm.setTeamName( rs.getString("name_first") );
						return tm;
					}
				} );
		System.out.println( t.getTeamName() );
		
		/*
		 * Connect to a Derby DB on a different network drive.
		 * 
		Connection conn = null;
		try {
//			DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:derby:P:/WORKSPACES_win7/OEPE/ApacheDerby/DerbyDb/MyDbTest");
			conn = DriverManager.getConnection("jdbc:derby:P:/WORKSPACES_win7/OEPE/ApacheDerby/DerbyDb/MyDbTest"); 
			System.out.println("got a connection!!!!  "+ conn);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			pStmt = conn.prepareStatement("select * from person");
			
			rs = pStmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("name_first") +" "+ rs.getString("name_last"));
			}
		} catch (Exception stmtEx) {
			stmtEx.printStackTrace();
		}		
		 */
	}

}
