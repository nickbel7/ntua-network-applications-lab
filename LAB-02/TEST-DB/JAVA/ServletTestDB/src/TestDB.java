

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDB
 */
@WebServlet("/TestDB")
public class TestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestDB() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
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
			
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append("<h4>Table Data</h4><table>");
			
			// Execute SELECT SQL Query & Present Data Retrieved
			final String sqlQuery = "select * from TABLE1";
			final PreparedStatement ps = conn.prepareStatement(sqlQuery);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				final int ID = rs.getInt(1);
				final String str1 = rs.getString(2);
				final String str2 = rs.getString(3);
				final Integer val = (Integer) rs.getObject(4);
				response.getWriter().append("<tr><td>" + ID + "</td><td>" + str1 + "</td><td>" + str2 + "</td><td>" + val + "</td></tr>");
			}
			response.getWriter().append("</table>");
			
			// Release Resources (especially DB connection)
			rs.close();
			ps.close();
			conn.close();
			
		} catch (Throwable t) {
			throw new RuntimeException("Testing DB problem...", t);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
