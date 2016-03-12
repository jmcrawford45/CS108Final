package id;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;



public class IdManager {
	
	public Connection con;

	public IdManager(Connection con) {
		this.con = con;
	}
	
	public int getNextID(int id) {
		int result = -1;
		
		try {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ids ORDER BY id DESC");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int first = rs.getInt(1);
				result = first + 1;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean isInUse(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ids WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return false;
			} else return true;
		} catch (SQLException e){
			e.printStackTrace();
			return true;
		}
	}

}
