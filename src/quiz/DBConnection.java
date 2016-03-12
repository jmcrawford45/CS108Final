package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {

	public static final String MYSQL_USERNAME = "ccs108jared13";
	public static final String MYSQL_PASSWORD = "2zVt6gGlm5GtBhMf";
	public static final String MYSQL_DATABASE_SERVER = "mysql-user.stanford.edu";
	public static final String MYSQL_DATABASE_NAME = "c_cs108_jared13";
	
	public static Connection connect() {
		Connection con = null;
		try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + 
            MYSQL_DATABASE_SERVER, MYSQL_USERNAME , MYSQL_PASSWORD);
            Statement stmt = con.createStatement();
            stmt.executeQuery("USE " + MYSQL_DATABASE_NAME);
         
    		} catch (SQLException e) {
            e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
		return con;
	}
	
	
	
}
