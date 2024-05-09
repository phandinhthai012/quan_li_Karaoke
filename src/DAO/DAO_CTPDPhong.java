package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatPhong;
import entity.HoaDon;
import entity.PhieuDat;
import entity.Phong;

public class DAO_CTPDPhong {
	private DAO_Phong phongDAO = new DAO_Phong();
	private DAO_HoaDon hoaDonDAO = new DAO_HoaDon();

	public DAO_CTPDPhong() {
		// TODO Auto-generated constructor stub
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public boolean themChiTietPhieuDatP(ChiTietPhieuDatPhong x) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			String sql = "insert into CHITIETPHIEUDATPHONG ([MAPHIEUDAT],[MAPHONG]) values (?,?)";
			psm = con.prepareStatement(sql);
			psm.setString(1, x.getMaPhieuDat());
			psm.setString(2, x.getPhong().getMaPhong());
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<ChiTietPhieuDatPhong> getDSphieuDatTheoMaPhieu(String maPhieu) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<ChiTietPhieuDatPhong> ds = new ArrayList<ChiTietPhieuDatPhong>();
		PreparedStatement psm = null;
		try {
			String sql = "select * from CHITIETPHIEUDATPHONG where MAPHIEUDAT = ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maPhieu);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString("MAPHONG");
				Phong p = phongDAO.timPhongTheoMaPhong(maPhong);
				ChiTietPhieuDatPhong ctpd = new ChiTietPhieuDatPhong(rs.getString("MAPHIEUDAT"), p);
				ds.add(ctpd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
}
