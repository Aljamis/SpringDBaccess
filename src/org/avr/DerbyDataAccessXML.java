package org.avr;

import org.avr.beans.Team;
import org.avr.daos.TeamDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Access Derby WITH an ApplicationContext XML
 * @author axviareque
 *
 */
public class DerbyDataAccessXML {
	private static String ctxFileName = "DerbyAccess.xml";
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext( ctxFileName );
		
		TeamDAO dao = (TeamDAO)ctx.getBean("TeamDAO");
		Team t = dao.getTeamByID(6);
		System.out.println( t.getTeamName() );
		
		((ClassPathXmlApplicationContext)ctx).close();

	}

}
