package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.ChiTietDichVu;
import entity.ChiTietPhieuDatDichVu;
import entity.DichVu;
import entity.HoaDon;


public class DAO_ChiTietDichVu {
	private DAO_DichVu dichVuDAO = new DAO_DichVu();
	private DAO_HoaDon hoaDonDAO = new DAO_HoaDon();
	public DAO_ChiTietDichVu() {
		// TODO Auto-generated constructor stub
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public ArrayList<ChiTietDichVu> getALLChiTietDVTheoMaHD(String maHD) { 
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<ChiTietDichVu> dsChiTietDV = new ArrayList<ChiTietDichVu>();
		PreparedStatement psm =null;
		try {
			String	sql = "select * from CHITIETDICHVU where MAHOADON = ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maHD);
			ResultSet rs = psm.executeQuery();
			while(rs.next()) {
				String maHD1 = rs.getString("MAHOADON");
				String maDV = rs.getString("MADV");
				HoaDon hd = hoaDonDAO.timHoaDonTheoMaHD(maHD1);
				DichVu x = dichVuDAO.timDVTheoMaDV(rs.getString("MADV"));
				ChiTietDichVu ctDv = new ChiTietDichVu(hd, x, rs.getInt("SOLUONG"));
				dsChiTietDV.add(ctDv);		
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsChiTietDV;
	}
	public boolean themChiTietDichVu(ChiTietDichVu x) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			String sql = ("insert into CHITIETDICHVU ([MADV],[MAHOADON],[SOLUONG]) values(?,?,?)");
			psm = con.prepareStatement(sql);
			psm.setString(1, x.getDichVu().getMaDV());
			psm.setString(2, x.getHoaDon().getMaHoaDon());
			psm.setInt(3, x.getSoLuong());
			return psm.executeUpdate()>0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
}
