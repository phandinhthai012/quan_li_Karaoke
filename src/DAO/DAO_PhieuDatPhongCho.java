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
import entity.PhieuDatPhongCho;

public class DAO_PhieuDatPhongCho {
	private DAO_KhachHang khachHangDao = new DAO_KhachHang();
	private DAO_NhanVien nhanVienDao = new DAO_NhanVien();

	public DAO_PhieuDatPhongCho() {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<PhieuDatPhongCho> getAllphieuDatPhongCho() {
		ArrayList<PhieuDatPhongCho> dsPhieuDat = new ArrayList<PhieuDatPhongCho>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from PHIEUDATPHONGCHO");
			while (rs.next()) {
				Timestamp ngayDat = rs.getTimestamp("NGAYDANGKI");
				Timestamp ngayNhanPhong = rs.getTimestamp("NGAYNHANPHONG");
				PhieuDatPhongCho pd = new PhieuDatPhongCho(rs.getString("MAPHIEUDATPHONGCHO"), rs.getString("MAKH"),
						rs.getString("MANV"), ngayDat.toLocalDateTime(), ngayNhanPhong.toLocalDateTime(),
						rs.getBoolean("TRANGTHAI"));
				dsPhieuDat.add(pd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhieuDat;
	}

	public boolean themPhieuDatPhongCho(PhieuDatPhongCho pd) {
		pd.setTinhtrang(false);
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			Timestamp ngayDangKi = Timestamp.valueOf(pd.getNgayDangKi());
			Timestamp ngayNhanPhong = Timestamp.valueOf(pd.getNgayNhanPhong());
			String sql = "insert into PHIEUDATPHONGCHO([MAPHIEUDATPHONGCHO],[MAKH],[MANV],[NGAYDANGKI],[NGAYNHANPHONG],[TRANGTHAI]) values(?,?,?,?,?,?)";
			psm = con.prepareStatement(sql);
			psm.setString(1, pd.getMaPhieuDatPhongCho());
			psm.setString(2, pd.getMaKhachHang());
			psm.setString(3, pd.getMaNhanVien());
			psm.setTimestamp(4, ngayDangKi);
			psm.setTimestamp(5, ngayNhanPhong);
			psm.setBoolean(6, pd.isTinhtrang());
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean capNhapTrangThai(String maPDP) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		// trang thai 1 là đã nhận phòng hoặc dã hết hiệu lực(hủy phòng)
		try {
			String sql = "update  PHIEUDATPHONGCHO set TRANGTHAI = 1 where MAPHIEUDATPHONGCHO= ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maPDP);
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean huyPhieuDat(String maPDP) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		// trang thai 1 là đã nhận phòng hoặc dã hết hiệu lực(hủy phòng)
		try {
			String sql = "update  PHIEUDATPHONGCHO set TRANGTHAI = 1 where MAPHIEUDATPHONGCHO= ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maPDP);
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public PhieuDatPhongCho getPhieuDat(String maPhieuDat) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		PhieuDatPhongCho pd = null;
		try {
			String sql = "Select * from PHIEUDATPHONGCHO where MAPHIEUDATPHONGCHO= ? ";
			psm = con.prepareStatement(sql);
			psm.setString(1, maPhieuDat);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Timestamp ngayDat = rs.getTimestamp("NGAYDANGKI");
				Timestamp ngayNhanPhong = rs.getTimestamp("NGAYNHANPHONG");
				pd = new PhieuDatPhongCho(rs.getString("MAPHIEUDATPHONGCHO"), rs.getString("MAKH"),
						rs.getString("MANV"), ngayDat.toLocalDateTime(), ngayNhanPhong.toLocalDateTime(),
						rs.getBoolean("TRANGTHAI"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pd;
	}

	public ArrayList<PhieuDatPhongCho> getPhieuDatTheoMaPhieu(String maPhieu) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<PhieuDatPhongCho> ds = new ArrayList<PhieuDatPhongCho>();
		PreparedStatement psm = null;
		try {
			String sql = "select * from PHIEUDATPHONGCHO where MAPHIEUDATPHONGCHO like N'%" + maPhieu + "%'";
			psm = con.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Timestamp ngayDat = rs.getTimestamp("NGAYDANGKI");
				Timestamp ngayNhanPhong = rs.getTimestamp("NGAYNHANPHONG");
				PhieuDatPhongCho pd = new PhieuDatPhongCho(rs.getString("MAPHIEUDATPHONGCHO"), rs.getString("MAKH"),
						rs.getString("MANV"), ngayDat.toLocalDateTime(), ngayNhanPhong.toLocalDateTime(),
						rs.getBoolean("TRANGTHAI"));
				ds.add(pd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ArrayList<PhieuDatPhongCho> getPhieuDatTheoSDT(String SDT) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<PhieuDatPhongCho> ds = new ArrayList<PhieuDatPhongCho>();
		PreparedStatement psm = null;
		try {
			String sql = "select * from PHIEUDATPHONGCHO as pd join KhachHang as kh on pd.MAKH= kh.MaKH where kh.SDT like N'%"
					+ SDT + "%'";
			psm = con.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Timestamp ngayDat = rs.getTimestamp("NGAYDANGKI");
				Timestamp ngayNhanPhong = rs.getTimestamp("NGAYNHANPHONG");
				PhieuDatPhongCho pd = new PhieuDatPhongCho(rs.getString("MAPHIEUDATPHONGCHO"), rs.getString("MAKH"),
						rs.getString("MANV"), ngayDat.toLocalDateTime(), ngayNhanPhong.toLocalDateTime(),
						rs.getBoolean("TRANGTHAI"));
				ds.add(pd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ArrayList<PhieuDatPhongCho> getPhieuDatTheoSDTvaMaPhieu(String SDT, String maPhieu) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<PhieuDatPhongCho> ds = new ArrayList<PhieuDatPhongCho>();
		PreparedStatement psm = null;
		try {
			String sql = "select * from PHIEUDATPHONGCHO as pd join KhachHang as kh on pd.MAKH= kh.MaKH where kh.SDT like N'%"
					+ SDT + "%' AND pd.MAPHIEUDATPHONGCHO like N'%" + maPhieu + "%'";
			psm = con.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Timestamp ngayDat = rs.getTimestamp("NGAYDANGKI");
				Timestamp ngayNhanPhong = rs.getTimestamp("NGAYNHANPHONG");
				PhieuDatPhongCho pd = new PhieuDatPhongCho(rs.getString("MAPHIEUDATPHONGCHO"), rs.getString("MAKH"),
						rs.getString("MANV"), ngayDat.toLocalDateTime(), ngayNhanPhong.toLocalDateTime(),
						rs.getBoolean("TRANGTHAI"));
				ds.add(pd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ArrayList<PhieuDatPhongCho> getAllphieuDatPhongChoConHieuLuc() {
		ArrayList<PhieuDatPhongCho> dsPhieuDat = new ArrayList<PhieuDatPhongCho>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from PHIEUDATPHONGCHO where TRANGTHAI = 0");
			while (rs.next()) {
				Timestamp ngayDat = rs.getTimestamp("NGAYDANGKI");
				Timestamp ngayNhanPhong = rs.getTimestamp("NGAYNHANPHONG");
				PhieuDatPhongCho pd = new PhieuDatPhongCho(rs.getString("MAPHIEUDATPHONGCHO"), rs.getString("MAKH"),
						rs.getString("MANV"), ngayDat.toLocalDateTime(), ngayNhanPhong.toLocalDateTime(),
						rs.getBoolean("TRANGTHAI"));
				dsPhieuDat.add(pd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhieuDat;
	}

	public PhieuDatPhongCho getPhieuDatPhongChoTheoMaPhong(String maPhong) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PhieuDatPhongCho phieuDat = null;
		PreparedStatement psm = null;
		try {
			String sql = "select top 1 * from PHIEUDATPHONGCHO as pd join CTPHIEUDATPHONGCHO as ct on pd.MAPHIEUDATPHONGCHO = ct.MAPHIEUDATPHONGCHO where pd.TRANGTHAI = 0 and ct.MAPHONG = ? order by pd.MAPHIEUDATPHONGCHO DESC";
			psm = con.prepareStatement(sql);
			psm.setString(1, maPhong);
			ResultSet rs = psm.executeQuery();
			while(rs.next()) {
				Timestamp ngayDat = rs.getTimestamp("NGAYDANGKI");
				Timestamp ngayNhanPhong = rs.getTimestamp("NGAYNHANPHONG");
				phieuDat = new PhieuDatPhongCho(rs.getString("MAPHIEUDATPHONGCHO"), rs.getString("MAKH"),
						rs.getString("MANV"), ngayDat.toLocalDateTime(), ngayNhanPhong.toLocalDateTime(),
						rs.getBoolean("TRANGTHAI"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phieuDat;
	}
}
