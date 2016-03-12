package tableabstraction;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;  

import javax.servlet.http.HttpServletRequest;

import user.User;
public class TableAbstraction {
	
public static int getID(Connection con){
	Stats stats = getStats(con);
	int id = stats.issueID();
	updateStats(stats, con);
	return id;
}
	
public static Stats getStats(Connection con){
	try{
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM stats;");
    while (rs.next()) {
      byte[] st = (byte[]) rs.getObject(1);
      ByteArrayInputStream baip = new ByteArrayInputStream(st);
      ObjectInputStream ois;
	try {
		ois = new ObjectInputStream(baip);
		return (Stats) ois.readObject();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
    rs.close();
	}catch (SQLException a) {
		a.printStackTrace();
	}
	return new Stats(0,0, new ArrayList<String>());
}
public static void updateStats(Stats stats, Connection con){
	deleteStats(con);
	try{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(stats);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] statsAsBytes = baos.toByteArray();
	    PreparedStatement pstmt = con.prepareStatement("INSERT INTO stats VALUES(?)");
	    ByteArrayInputStream bais = new ByteArrayInputStream(statsAsBytes);
	    pstmt.setBinaryStream(1, bais, statsAsBytes.length);
	    pstmt.executeUpdate();
	    pstmt.close();
	}catch (SQLException a) {
		a.printStackTrace();
	}
	
}
public static void deleteStats(Connection con){
	Statement stmt;
	try {
		stmt = con.createStatement();    
		stmt.executeUpdate("DELETE FROM stats");
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
public static User getUser(HttpServletRequest request){
	Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
	String name = (String)request.getSession().getAttribute("user");
	return getUser(name, con);
}
public static User getUser(int uid, Connection con){
	try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT user FROM users WHERE uid = '" + uid +"';");
	    while (rs.next()) {
	      byte[] st = (byte[]) rs.getObject(1);
	      ByteArrayInputStream baip = new ByteArrayInputStream(st);
	      ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(baip);
			User user = (User) ois.readObject();
			return user;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    }
	    rs.close();
		}catch (SQLException a) {
			a.printStackTrace();
		}
		return null;
}
public static User getUser(String id, Connection con){
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT user FROM users WHERE id = '" + id +"';");
	    while (rs.next()) {
	      byte[] st = (byte[]) rs.getObject(1);
	      ByteArrayInputStream baip = new ByteArrayInputStream(st);
	      ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(baip);
			User user = (User) ois.readObject();
			return user;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    }
	    rs.close();
		}catch (SQLException a) {
			a.printStackTrace();
		}
		return null;
	}
	public static void updateUser(String id, User user, Connection con){
		User before = getUser(id, con);
		if(before == null) user.setID(getID(con));
		deleteUser(id, con);
		Stats stats = getStats(con);
		stats.setUsers(stats.getUsers() + 1);
		updateStats(stats, con);
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(baos);
				oos.writeObject(user);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] userAsBytes = baos.toByteArray();
		    PreparedStatement pstmt = con.prepareStatement("INSERT INTO users VALUES(?,?,?)");
		    ByteArrayInputStream bais = new ByteArrayInputStream(userAsBytes);
		    pstmt.setInt(1, user.getID());
		    pstmt.setString(2, id);
		    pstmt.setBinaryStream(3, bais, userAsBytes.length);
		    pstmt.executeUpdate();
		    pstmt.close();
		}catch (SQLException a) {
			a.printStackTrace();
		}
	}
	public static void deleteUser(String id, Connection con){
		Statement stmt;
		Stats stats = getStats(con);
		try {
			stmt = con.createStatement();    
			if(getUser(id, con) != null) {
				stats.setUsers(stats.getUsers() - 1);
				updateStats(stats, con);
			}
			stmt.executeUpdate("DELETE FROM users WHERE id = \"" + id + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
