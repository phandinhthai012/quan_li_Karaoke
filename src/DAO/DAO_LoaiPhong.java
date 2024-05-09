package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.LoaiPhong;

public class DAO_LoaiPhong {
	public DAO_LoaiPhong() {
		// TODO Auto-generated constructor stub
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public ArrayList<LoaiPhong> getAllLoaiPhong() {
		ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			String sql = "select * from LOAIPHONG";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsLoaiPhong.add(new LoaiPhong(rs.getString("MALOAIPHONG"), rs.getString("TENLOAIPHONG")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}

	public LoaiPhong getLoaiTheoMa(String ma) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		LoaiPhong loai = null;
		try {
			PreparedStatement pstm = con.prepareStatement("select * from LOAIPHONG where MALOAIPHONG = ?");
			pstm.setString(1, ma);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				loai = new LoaiPhong(rs.getString(1), rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loai;
	}
}
