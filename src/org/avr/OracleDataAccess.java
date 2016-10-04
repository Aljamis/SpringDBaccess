package org.avr;

import org.avr.beans.Team;
import org.avr.daos.TeamDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * using Spring 4.3.3 .. But it didn't come with org/springframework/dao.
 * Had to download spring-dao.2.0.8
 * @author axviareque
 *
 */
public class OracleDataAccess {
	private static String ctxFileName = "OracleAccess.xml";
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext( ctxFileName );
		
		TeamDAO dao = (TeamDAO)ctx.getBean("TeamDAO");
		Team t = dao.getTeamByID(6);
		System.out.println( t.getTeamName() );
		
		((ClassPathXmlApplicationContext)ctx).close();
	}
}
