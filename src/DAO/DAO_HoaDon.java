package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import connectDB.connectDB;
import entity.HoaDon;

public class DAO_HoaDon {
	private DAO_KhachHang khachHang_DAO = new DAO_KhachHang();
	private DAO_NhanVien nhanVien_DAO = new DAO_NhanVien();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	DateFormat dfJava = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public DAO_HoaDon() {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<HoaDon> getALLHoaDon() {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from HOADON");
			while (rs.next()) {
				Timestamp BD = rs.getTimestamp("THOIGIANBD");
				Timestamp KT = rs.getTimestamp("THOIGIANKT");
				Timestamp nt = rs.getTimestamp("NGAYTAO");
				LocalDateTime thoiGianBD = BD.toLocalDateTime();
				LocalDateTime thoiGianKT = KT.toLocalDateTime();
				LocalDateTime ngayTao = nt.toLocalDateTime();

				HoaDon x = new HoaDon(rs.getString("MAHOADON"), thoiGianBD, thoiGianKT, ngayTao, rs.getString("MANV"),
						rs.getString("MAKH"));
				dsHoaDon.add(x);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	public boolean themHoaDon(HoaDon X) {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			Timestamp BD = Timestamp.valueOf(X.getThoiGianBatDau());
			Timestamp KT = Timestamp.valueOf(X.getThoiGianKetThuc());
			Timestamp nt = Timestamp.valueOf(X.getNgayTao());
			String sql = "insert into HOADON ([MAHOADON],[THOIGIANBD],[THOIGIANKT],[NGAYTAO],[MANV],[MAKH]) values (?,?,?,?,?,?)";
			psm = con.prepareStatement(sql);
			psm.setString(1, X.getMaHoaDon());
			psm.setTimestamp(2, BD);
			psm.setTimestamp(3, KT);
			psm.setTimestamp(4, nt);
			psm.setString(5, X.getMaNhanVien());
			psm.setString(6, X.getMaKhachHang());
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public HoaDon timHoaDonTheoMaHD(String maHD) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		HoaDon x = null;
		try {
			String sql = "select * from HOADON where MAHOADON = ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maHD);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Timestamp BD = rs.getTimestamp("THOIGIANBD");
				Timestamp KT = rs.getTimestamp("THOIGIANKT");
				Timestamp nt = rs.getTimestamp("NGAYTAO");
				LocalDateTime thoiGianBD = BD.toLocalDateTime();
				LocalDateTime thoiGianKT = KT.toLocalDateTime();
				LocalDateTime ngayTao = nt.toLocalDateTime();

				x = new HoaDon(rs.getString("MAHOADON"), thoiGianBD, thoiGianKT, ngayTao, rs.getString("MANV"),
						rs.getString("MAKH"));
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return x;
	}

//	public boolean capNhapTrangThaiHoaDon(String maHD, boolean trangThai) {
//		connectDB.getInstance();
//		Connection con = connectDB.getConnection();
//		PreparedStatement psm;
//		try {
//			String sql = "update HOADON set TRANGTHAI = ? where MAHOADON = ?";
//			psm = con.prepareStatement(sql);
//			psm.setBoolean(1, trangThai);
//			psm.setString(2, maHD);
//			return psm.executeUpdate() > 0;
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return false;
//	}

	public ArrayList<HoaDon> getDSTimKiemHoaDon(String txt) {
		ArrayList<HoaDon> dstimKiemHoaDon = new ArrayList<HoaDon>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm;
		try {
			String sql = "select * from HOADON as hd join KhachHang as kh on hd.MAKH=kh.MaKH where hd.MAKH like N'%"
					+ txt + "%' or kh.TenKH like N'%" + txt + "%' or SDT like N'%" + txt + "%'";
			psm = con.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Timestamp BD = rs.getTimestamp("THOIGIANBD");
				Timestamp KT = rs.getTimestamp("THOIGIANKT");
				Timestamp nt = rs.getTimestamp("NGAYTAO");
				LocalDateTime thoiGianBD = BD.toLocalDateTime();
				LocalDateTime thoiGianKT = KT.toLocalDateTime();
				LocalDateTime ngayTao = nt.toLocalDateTime();

				HoaDon x = new HoaDon(rs.getString("MAHOADON"), thoiGianBD, thoiGianKT, ngayTao, rs.getString("MANV"),
						rs.getString("MAKH"));
				dstimKiemHoaDon.add(x);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dstimKiemHoaDon;
	}

	public ArrayList<HoaDon> getDSHoaDonTheoThang(String thang, String nam) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(
					"select * from HOADON where MONTH(NGAYTAO) = " + thang + "AND YEAR(NGAYTAO) = " + nam);
			while (rs.next()) {
				Timestamp BD = rs.getTimestamp("THOIGIANBD");
				Timestamp KT = rs.getTimestamp("THOIGIANKT");
				Timestamp nt = rs.getTimestamp("NGAYTAO");
				LocalDateTime thoiGianBD = BD.toLocalDateTime();
				LocalDateTime thoiGianKT = KT.toLocalDateTime();
				LocalDateTime ngayTao = nt.toLocalDateTime();
				HoaDon x = new HoaDon(rs.getString("MAHOADON"), thoiGianBD, thoiGianKT, ngayTao, rs.getString("MANV"),
						rs.getString("MAKH"));
				dsHoaDon.add(x);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHoaDon;

	}

	public ArrayList<HoaDon> getDSHoaDonTheoNgay(String ngay, String thang, String nam) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from HOADON where DAY(NGAYTAO) = " + ngay
					+ "and MONTH(NGAYTAO) = " + thang + "AND YEAR(NGAYTAO) = " + nam);
			while (rs.next()) {
				Timestamp BD = rs.getTimestamp("THOIGIANBD");
				Timestamp KT = rs.getTimestamp("THOIGIANKT");
				Timestamp nt = rs.getTimestamp("NGAYTAO");
				LocalDateTime thoiGianBD = BD.toLocalDateTime();
				LocalDateTime thoiGianKT = KT.toLocalDateTime();
				LocalDateTime ngayTao = nt.toLocalDateTime();
				HoaDon x = new HoaDon(rs.getString("MAHOADON"), thoiGianBD, thoiGianKT, ngayTao, rs.getString("MANV"),
						rs.getString("MAKH"));
				dsHoaDon.add(x);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHoaDon;

	}

	public ArrayList<HoaDon> getDSHoaDonTheoNam(String nam) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from HOADON where YEAR(NGAYTAO) = " + nam);
			while (rs.next()) {
				Timestamp BD = rs.getTimestamp("THOIGIANBD");
				Timestamp KT = rs.getTimestamp("THOIGIANKT");
				Timestamp nt = rs.getTimestamp("NGAYTAO");
				LocalDateTime thoiGianBD = BD.toLocalDateTime();
				LocalDateTime thoiGianKT = KT.toLocalDateTime();
				LocalDateTime ngayTao = nt.toLocalDateTime();
				HoaDon x = new HoaDon(rs.getString("MAHOADON"), thoiGianBD, thoiGianKT, ngayTao, rs.getString("MANV"),
						rs.getString("MAKH"));
				dsHoaDon.add(x);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHoaDon;

	}

	public int getSoluongHoaDonTheoGioTrongNgay(String ngay, String thang, String nam, String gio) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		HoaDon hd = null;
		PreparedStatement psm = null;
		int soLuong = 0;
		try {
			String sql = "select  COUNT(*) as soluong from HOADON where DAY(NGAYTAO) = " + ngay + "and MONTH(NGAYTAO) = " + thang
					+ "AND YEAR(NGAYTAO) = " + nam + "AND datepart(hour,THOIGIANBD)=" + gio;
			psm = con.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				soLuong = rs.getInt("soluong");
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
		return soLuong;
	}
}
