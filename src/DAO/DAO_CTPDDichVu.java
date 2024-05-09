package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.ChiTietPhieuDatDichVu;
import entity.ChiTietPhieuDatPhong;

public class DAO_CTPDDichVu {
	private DAO_PhieuDat phieuDatDao = new DAO_PhieuDat();
	private DAO_DichVu dichVuDAO = new DAO_DichVu();

	public DAO_CTPDDichVu() {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean themChiTietDatDV(ChiTietPhieuDatDichVu pd) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			String sql = "insert into CHITIETPHIEUDATDICHVU ([MAPHIEUDAT],[MADV],[SOLUONG]) values(?,?,?)";
			psm = con.prepareStatement(sql);
			psm.setString(1, pd.getMaPhieuDatDV());
			psm.setString(2, pd.getMaDV());
			psm.setInt(3, pd.getSoLuong());
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<ChiTietPhieuDatDichVu> getDSPhieuDatDVtheoMaPhieuDat(String maPhieu) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<ChiTietPhieuDatDichVu> ds = new ArrayList<ChiTietPhieuDatDichVu>();
		PreparedStatement psm = null;
		try {
			String sql = "select * from  CHITIETPHIEUDATDICHVU where MAPHIEUDAT = ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maPhieu);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				ChiTietPhieuDatDichVu pd = new ChiTietPhieuDatDichVu(rs.getString("MAPHIEUDAT"), rs.getString("MADV"),
						rs.getInt("SOLUONG"));
				ds.add(pd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean capNhapSoLuongDichVu(String maphieu, String maDv, int sluong) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			String sql = "update CHITIETPHIEUDATDICHVU set SOLUONG = ? where MADV=? and MAPHIEUDAT =?";
			psm = con.prepareStatement(sql);
			psm.setInt(1, sluong);
			psm.setString(2, maDv);
			psm.setString(3, maphieu);
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public ChiTietPhieuDatDichVu getChiTietPhieuDatDv(String maphieu, String maDv) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		ChiTietPhieuDatDichVu ctpd = null;
		try {

			String sql = "select * from CHITIETPHIEUDATDICHVU where  MADV=? and MAPHIEUDAT =?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maDv);
			psm.setString(2, maphieu);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				ctpd = new ChiTietPhieuDatDichVu(rs.getString("MAPHIEUDAT"), rs.getString("MADV"),
						rs.getInt("SOLUONG"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ctpd;
	}
}
