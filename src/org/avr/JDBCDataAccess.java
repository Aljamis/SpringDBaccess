package org.avr;

import java.util.List;

import org.avr.beans.Team;
import org.avr.daos.TeamDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JDBCDataAccess {
	private static String ctxFileName = "DataBaseAccess.xml";

	public static void main(String[] args) {
		System.out.println( System.getProperty("pwd"));
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext( ctxFileName );
		TeamDAO dao = (TeamDAO)ctx.getBean("TeamDAO");
		
		Team t = dao.getTeamByID(1);
		System.out.println(t.getTeamName());
		
		List<Team> teams = dao.getTeams();
		for (Team team : teams) {
			System.out.println( team.getTeamID() +" - "+ team.getTeamName() );
		}
	}
}
