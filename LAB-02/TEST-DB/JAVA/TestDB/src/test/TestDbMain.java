package test;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class TestDbMain {

	public static void main(String[] args) throws Exception {
		
		System.out.println(" >> " + TestDbMain.class.getSimpleName() + ": START");
		
		// DB Connection Data
		final String dbUrl = "jdbc:mysql://localhost:3306/LAB1DB";
		final Properties dbProp = new Properties();
		dbProp.setProperty("user"	, "root");
		dbProp.setProperty("password", "");
		dbProp.setProperty("charSet", "UTF-8");
		
		// Ensure that the DB Connector (i.e., Java Class) is available in your CLASSPATH
		// If not, write click to Project, go to Properties, then go to Java Build Path 
		// Select and Add the DB Connector JAR File to the Classpath
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// Get DB Connection
		final Connection conn = DriverManager.getConnection(dbUrl, dbProp);
		
		// Για την εκτύπωση Ελληνικών Χαρακτήρων στην οθόνη μας 
		// Βεβαιωθείτε ότι το format του αρχείου αυτού είναι UTF-8
		final PrintStream out = new PrintStream(System.out, true, "UTF-8");	    
		
		// Execute SELECT SQL Query & Present Data Retrieved
		final String sqlQuery = "select * from TABLE1";
		final PreparedStatement ps = conn.prepareStatement(sqlQuery);
		final ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			final int ID = rs.getInt(1);
			final String str1 = rs.getString(2);
			final String str2 = rs.getString(3);
			final Integer val = (Integer) rs.getObject(4);
			out.println(ID + " " + str1 + " " + str2 + " " + val);
		}
		
		// Release Resources (especially DB connection)
		rs.close();
		ps.close();
		conn.close();
		
		System.out.println(" >> " + TestDbMain.class.getSimpleName() + ": END");
	}

}
