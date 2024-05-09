package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import connectDB.connectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Phong;

public class DAO_ChiTietHoaDon {
	private DAO_Phong phongDAO = new DAO_Phong();
	private DAO_HoaDon hoaDonDAO = new DAO_HoaDon();

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	DateFormat dfJava = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public DAO_ChiTietHoaDon() {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static LocalDateTime chuyenDateTimeSangLocalDateTime(String chuoiSQL) {
		if (chuoiSQL == null) {
			return null;
		}
		LocalDateTime x = LocalDateTime.parse(chuoiSQL);
		String dateSQL = chuoiSQL.substring(0, 10);
		String timeSQL = chuoiSQL.substring(11, 19);
		LocalDate date = LocalDate.parse(dateSQL);
		LocalTime time = LocalTime.parse(timeSQL);
		return LocalDateTime.of(date, time);
	}

	// fix lại hàm này
	public ArrayList<ChiTietHoaDon> layDSTheoMaHD(String maHD) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement(
					"select *from CHITIETHOADON as ct join HOADON as hd on ct.MAHOADON=hd.MAHOADON where ct.MAHOADON = ?");
			psm.setString(1, maHD);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {

				HoaDon hd = hoaDonDAO.timHoaDonTheoMaHD(maHD);
				Phong p = phongDAO.timPhongTheoMaPhong(rs.getString("MAPHONG"));
				ChiTietHoaDon ct = new ChiTietHoaDon(hd, p,rs.getInt("THOILUONG"));
				ds.add(ct);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	public boolean themChitietHoaDon(ChiTietHoaDon ct) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("insert into CHITIETHOADON ([MAHOADON],[MAPHONG],[THOILUONG]) values(?,?,?) ");
			psm.setString(1, ct.getHoaDon().getMaHoaDon());
			psm.setString(2, ct.getPhong().getMaPhong());
			psm.setInt(3, ct.getThoiLuong());
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public ChiTietHoaDon getChiTietHDTheoMaPhong(String maPhong) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		ChiTietHoaDon x = null;
		try {
			psm = con.prepareStatement("select * from HOADON as hd join CHITIETHOADON as ct on hd.MAHOADON=ct.MAHOADON where TRANGTHAI =0 AND MAPHONG = ?");
			psm.setString(1, maPhong);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				HoaDon hd = hoaDonDAO.timHoaDonTheoMaHD(rs.getString("MAHOADON"));
				Phong p = phongDAO.timPhongTheoMaPhong(rs.getString("MAPHONG"));
				x = new ChiTietHoaDon(hd, p, rs.getInt("THOILUONG"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return x;
	}
	public ArrayList<ChiTietHoaDon> getDSChiTietHoaDonTheoKh(String maKH) {
		ArrayList<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("select * from HOADON as hd join CHITIETHOADON as ct on hd.MAHOADON = ct.MAHOADON where hd.MAKH =?");
			psm.setString(1, maKH);
			ResultSet rs = psm.executeQuery();
			while(rs.next()) {
				HoaDon hd = hoaDonDAO.timHoaDonTheoMaHD(rs.getString("MAHOADON"));
				Phong p = phongDAO.timPhongTheoMaPhong(rs.getString("MAPHONG"));
				ChiTietHoaDon ct = new ChiTietHoaDon(hd, p,rs.getInt("THOILUONG"));
				ds.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
