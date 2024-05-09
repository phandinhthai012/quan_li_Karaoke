package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.ChiTietDichVu;
import entity.ChiTietPhieuDatPhong;
import entity.ChiTietPhieuDatPhongCho;
import entity.Phong;

public class DAO_CTPhieuDatPhongCho {
	private DAO_Phong phongDAO = new DAO_Phong();
	
	public DAO_CTPhieuDatPhongCho() {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public boolean themCTPhieuDatPhongCho(ChiTietPhieuDatPhongCho ctPD) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			String sql = "insert into CTPHIEUDATPHONGCHO ([MAPHIEUDATPHONGCHO],[MAPHONG]) values (?,?)";
			psm = con.prepareStatement(sql);
			psm.setString(1, ctPD.getMaPhieuDatPhongCho());
			psm.setString(2, ctPD.getMaPhong());
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<ChiTietPhieuDatPhongCho> getDSphieuDatTheoMaPhieu(String maPhieu) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<ChiTietPhieuDatPhongCho> ds = new ArrayList<ChiTietPhieuDatPhongCho>();
		PreparedStatement psm = null;
		try {
			String sql = "select * from CTPHIEUDATPHONGCHO where MAPHIEUDATPHONGCHO = ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maPhieu);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				ChiTietPhieuDatPhongCho ctpd = new ChiTietPhieuDatPhongCho(rs.getString("MAPHIEUDATPHONGCHO"), rs.getString("MAPHONG"));
				ds.add(ctpd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
