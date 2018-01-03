package org.avr;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.avr.beans.PersonColumns;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



/**
 * Perform "select * ..." and get the column names.
 * @author axviareque
 *
 */
public class ColNameToEnum {

	public static void main(String[] args) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:derby:P:/WORKSPACES_win7/OEPE/ApacheDerby/DerbyDb/MyDbTest");
		
		JdbcTemplate jTemp = new JdbcTemplate(dataSource);
		
		System.out.println("Made it this far");
		/*  Get Column Names  */
		List<String> colNames = jTemp.query("select * from person2 fetch first row only"
				, new ResultSetExtractor<List<String>>() {
					@Override
					public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<String> columnNames = new ArrayList<String>();
						for (int i = 1; i <= rs.getMetaData().getColumnCount() ; i++) {
							columnNames.add( rs.getMetaData().getColumnName( i ) );
						}
						return columnNames;
					}
				});
		
		for (String colName : colNames) {
			String x = "";
			try {
				PersonColumns enumName = PersonColumns.valueOf(colName);
				x = enumName.toString();
			} catch (IllegalArgumentException iaEx) {
				x="enum not defined";
			}
			System.out.println( colName +"  \t"+ x );
		}
		
		
//		jTemp.queryForObject("", String.class);
	}
}
