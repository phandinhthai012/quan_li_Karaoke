package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.PhieuDat;

public class DAO_PhieuDat {
	private DAO_KhachHang khachHangDao = new DAO_KhachHang();
	private DAO_NhanVien nhanVienDao = new DAO_NhanVien();

	public DAO_PhieuDat() {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<PhieuDat> getALLPhieuDat() {
		ArrayList<PhieuDat> dsPhieuDat = new ArrayList<PhieuDat>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from PHIEUDAT");
			while (rs.next()) {
				Timestamp ngayDat = rs.getTimestamp("NGAYDAT");
				PhieuDat pd = new PhieuDat(rs.getString("MAPHIEUDAT"),rs.getString("MAKH"),rs.getString("MANV"),ngayDat.toLocalDateTime(),rs.getBoolean("TRANGTHAI"));
				dsPhieuDat.add(pd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhieuDat;
	}
	public boolean themPhieuDat(PhieuDat x) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			Timestamp ngayDat = Timestamp.valueOf(x.getNgayDat());
			String sql = "insert into PHIEUDAT ([MAPHIEUDAT],[MAKH],[MANV],[NGAYDAT],[TRANGTHAI]) values(?,?,?,?,?)";
			psm = con.prepareStatement(sql);
			psm.setString(1, x.getMaPhieuDat());
			psm.setString(2, x.getMaKhachHang());
			psm.setString(3, x.getMaNhanVien());
			psm.setTimestamp(4, ngayDat);
			psm.setBoolean(5, x.isTrangThai());
			return psm.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public PhieuDat getPhieuDatTheoMaPhieu(String maPhieu) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		PhieuDat pd = null;
		try {
			String sql = "select * from PHIEUDAT where MAPHIEUDAT= ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maPhieu);
			ResultSet rs = psm.executeQuery();
			while(rs.next()) {
				Timestamp ngayDat = rs.getTimestamp("NGAYDAT");
				pd = new PhieuDat(rs.getString("MAPHIEUDAT"),rs.getString("MAKH"),rs.getString("MANV"),ngayDat.toLocalDateTime(),rs.getBoolean("TRANGTHAI"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pd;
	}
	public boolean capNhapTrangThaiPhieuDat(String maPhieu) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			String sql = "update  PHIEUDAT set TRANGTHAI = 1 where MAPHIEUDAT= ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maPhieu);
			
			return psm.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
