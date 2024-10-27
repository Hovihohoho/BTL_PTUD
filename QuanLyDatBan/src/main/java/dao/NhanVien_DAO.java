package dao;

import java.sql.*;

import java.util.*;
import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVien_DAO {
	public ArrayList<NhanVien> getalltbNhanVien(){
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		try {
		
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String sDT = rs.getString(3);
				String email = rs.getString(4);
				NhanVien nv = new NhanVien( maNV,  tenNV, sDT, email );
				dsnv.add(nv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	
	public ArrayList<NhanVien> getNhanVienTheoMaNV(int maNhanVien){
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
	
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from NhanVien where maNV = ? ";
			statement = con.prepareStatement(sql);
			statement.setInt(1, maNhanVien);
			
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String sDT = rs.getString(3);
				String email = rs.getString(4);
				NhanVien nv = new NhanVien( maNV,  tenNV, sDT, email );
				dsnv.add(nv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsnv;
	}
	
	
	public boolean create(NhanVien nv) {
	
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		
		int n =0;
		try {
			String sql = "Insert into NhanVien values (?, ?, ?, ?) ";
			statement=con.prepareStatement(sql);
			statement.setString(1, nv.getMaNV());
			statement.setString(2, nv.getTenNV());
			statement.setString(3, nv.getsDT());
			statement.setString(4, nv.getEmail());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return n > 0; 
	}
	
	public boolean update(NhanVien nv) {
		
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		
		int n =0;
		try {
			String sql = "Update NhanVien set ten = ?, tuoi = ?  where maNV = ? ";
			statement=con.prepareStatement(sql);
			statement.setString(1, nv.getMaNV());
			statement.setString(2, nv.getTenNV());
			statement.setString(3, nv.getsDT());
			statement.setString(4, nv.getEmail());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return n > 0; 
	}
	
	public boolean delete(int maNV) {

	    Connection con = ConnectDB.getConnection();
	    PreparedStatement statement = null;
	    int n = 0;
	    try {
	        String sql = "Delete from NhanVien where maNV = ?";
	        statement = con.prepareStatement(sql);
	        statement.setInt(1, maNV);
	        n = statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return n > 0;
	}
	 public boolean register(TaiKhoan user) {
	        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
	        try (Connection con = ConnectDB.getConnection();
	             PreparedStatement statement = con.prepareStatement(sql)) {
	             
	            statement.setString(1, user.gettenTK());
	            statement.setString(2, user.getmatKhau()); 
	            return statement.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	  
	 public boolean login(String tenTK, String matKhau) {
		    String sql = "SELECT * FROM TaiKhoan WHERE tenTK = ? AND matKhau = ?";
		    Connection con = null;
		    PreparedStatement statement = null;
		    
		    try {
		        con = ConnectDB.getConnection();
		        if (con == null) {
		            ConnectDB.getInstance().connect(); // Thiết lập lại kết nối nếu nó null
		            con = ConnectDB.getConnection();
		        }
		        
		        statement = con.prepareStatement(sql);
		        statement.setString(1, tenTK);
		        statement.setString(2, matKhau);
		        
		        ResultSet rs = statement.executeQuery();
		        return rs.next();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (statement != null) statement.close();
		            if (con != null) con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return false;
		}

	
}
