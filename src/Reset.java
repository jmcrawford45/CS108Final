package listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Reset {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String MYSQL_USERNAME = "ccs108jared13";
		final String MYSQL_PASSWORD = "2zVt6gGlm5GtBhMf";
		final String MYSQL_DATABASE_SERVER = "mysql-user.stanford.edu";
		final String MYSQL_DATABASE_NAME = "c_cs108_jared13";
		Connection con = null;
    	try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + 
            MYSQL_DATABASE_SERVER, MYSQL_USERNAME , MYSQL_PASSWORD);
            Statement stmt = con.createStatement();
            stmt.executeQuery("USE " + MYSQL_DATABASE_NAME);
            stmt.executeUpdate("DROP TABLE IF EXISTS users;");
            stmt.executeUpdate("CREATE TABLE users(uid INT, id varchar(255), user BLOB);");
            stmt.executeUpdate("DROP TABLE IF EXISTS stats;");
            stmt.executeUpdate("CREATE TABLE stats(stats BLOB);");
            stmt.executeUpdate("DROP TABLE IF EXISTS questions;");
            stmt.executeUpdate("CREATE TABLE questions(question_id INT, type varchar(20), question varchar(1000), answer varchar(1000), additional(1000)");
    		} catch (SQLException e) {
            e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
            e.printStackTrace();
            }

	}

}
