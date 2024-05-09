package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import connectDB.connectDB;
import entity.KhachHang;
import entity.NhanVien;

public class DAO_TaiKhoan {
	public DAO_TaiKhoan() {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public ArrayList<NhanVien> getALLNhanVien() {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			String sql = "select * from NHANVIEN";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				boolean gioiTinh = false;
				if (rs.getString("GIOITINH").equalsIgnoreCase("Nam")) {
					gioiTinh = true;
				}
				dsNhanVien.add(new NhanVien(rs.getString("MANV"), rs.getString("TENNV"), rs.getString("CMND"),
						rs.getString("SDT"), gioiTinh, rs.getString("DIACHI"), rs.getString("TAIKHOAN"),
						rs.getString("MATKHAU"), rs.getString("CHUCVU")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dsNhanVien;
	}

	public NhanVien timKiemNhanVienTheoTaiKhoan(String taiKhoan, String matKhau) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		NhanVien nv = null;
		String sql = "select * from NHANVIEN where TAIKHOAN = ? AND MATKHAU = ?";
		try {
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, taiKhoan);
			psm.setString(2, matKhau);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				boolean gt = false;
				if (rs.getString("GIOITINH").equals("Nam")) {
					gt = true;
				}
				nv = new NhanVien(rs.getString("MANV"), rs.getString("TENNV"), rs.getString("CMND"),
						rs.getString("SDT"), gt, rs.getString("DIACHI"), rs.getString("TAIKHOAN"),
						rs.getString("MATKHAU"), rs.getString("CHUCVU"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return nv;
	}

	public boolean themNhanVien(NhanVien nv) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			String gt = "Nam";
			if (!nv.isGioiTinh()) {
				gt = "Nữ";
			}
			psm = con.prepareStatement(
					"insert into NHANVIEN ([MANV],[TENNV],[CMND],[SDT],[GIOITINH],[DIACHI],[CHUCVU]) values(?,?,?,?,?,?,?)");
			psm.setString(1, nv.getMaNV());
			psm.setString(2, nv.getTenNV());
			psm.setString(3, nv.getCmnd());
			psm.setString(4, nv.getSdt());
			psm.setString(5, gt);
			psm.setString(6, nv.getDiaChi());
			psm.setString(7, nv.getChucVu());
			return psm.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	public boolean capNhapNhanVien(NhanVien nv) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			String gt = "Nam";
			if (!nv.isGioiTinh()) {
				gt = "Nữ";
			}
			psm = con.prepareStatement(
					"update NHANVIEN set TENNV = ?, CMND = ?, SDT = ?, GIOITINH = ?, DIACHI = ?, CHUCVU = ? where MANV = ?");
			psm.setString(1, nv.getTenNV());
			psm.setString(2, nv.getCmnd());
			psm.setString(3, nv.getSdt());
			psm.setString(4, gt);
			psm.setString(5, nv.getDiaChi());
			psm.setString(6, nv.getChucVu());
			psm.setString(7, nv.getMaNV());
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public NhanVien timNhanVienTheoMaNV(String maNV) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		NhanVien x = null;
		try {
			String sql ="select * from NHANVIEN where MANV = ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maNV);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				boolean gt = false;
				if (rs.getString("GIOITINH").equals("Nam")) {
					gt = true;
				}
				x = new NhanVien(rs.getString("MANV"), rs.getString("TENNV"), rs.getString("CMND"),
						rs.getString("SDT"), gt, rs.getString("DIACHI"), rs.getString("TAIKHOAN"),
						rs.getString("MATKHAU"), rs.getString("CHUCVU"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return x;
	}
	public boolean capNhanTaiKhoanMatKhauChoNhanVien(String manv, String tk, String mk) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("update NHANVIEN SET TAIKHOAN = ?, MATKHAU = ? where MANV =?");
			psm.setString(1, tk);
			psm.setString(2, mk);
			psm.setString(3, manv);
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public boolean xoaTaiKhoanCuaNhanVien(String maNV) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("update NHANVIEN SET TAIKHOAN = NULL, MATKHAU = NULL where MANV =?");
			psm.setString(1, maNV);
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public boolean capNhapMatKhau(String maukhau, String maNV) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("update NHANVIEN SET MATKHAU =? where MANV =?");
			psm.setString(1, maukhau);
			psm.setString(2, maNV);
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public NhanVien	getNhanVienTheoTaiKhoanVaSDT(String tk, String sdt) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		NhanVien x = null;
		try {
			String sql ="select * from NHANVIEN where TAIKHOAN = ? and SDT =?";
			psm = con.prepareStatement(sql);
			psm.setString(1, tk);
			psm.setString(2, sdt);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				boolean gt = false;
				if (rs.getString("GIOITINH").equals("Nam")) {
					gt = true;
				}
				x = new NhanVien(rs.getString("MANV"), rs.getString("TENNV"), rs.getString("CMND"),
						rs.getString("SDT"), gt, rs.getString("DIACHI"), rs.getString("TAIKHOAN"),
						rs.getString("MATKHAU"), rs.getString("CHUCVU"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return x;
	}
}
