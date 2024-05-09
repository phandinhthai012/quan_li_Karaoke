package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import connectDB.connectDB;
import entity.KhachHang;
import entity.NhanVien;

public class DAO_NhanVien {
	public DAO_NhanVien() {
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
						rs.getString("SDT"), rs.getString("DIACHI"), gioiTinh, rs.getString("TAIKHOAN"),
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
						rs.getString("SDT"), rs.getString("DIACHI"), gt, rs.getString("TAIKHOAN"),
						rs.getString("MATKHAU"), rs.getString("CHUCVU"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
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
			String cv = "QL";
			if(nv.getChucVu().equalsIgnoreCase("Nhân viên")) {
				cv= "NV";
			}
			psm = con.prepareStatement(
					"INSERT [dbo].[NHANVIEN] ([MANV], [TENNV], [TAIKHOAN], [MATKHAU], [CMND], [SDT],[GIOITINH],[DIACHI],[CHUCVU]) VALUES (?,?,?,?,?,?,?,?,?)");
			psm.setString(1, nv.getMaNV());
			psm.setString(2, nv.getTenNV());
			psm.setString(5, nv.getCmnd());
			psm.setString(6, nv.getSdt());
			psm.setString(7, gt);
			psm.setString(8, nv.getDiaChi());
			psm.setString(9, cv);
			psm.setString(3, nv.getTaiKhoan());
			psm.setString(4, nv.getMatKhau());
			return psm.executeUpdate() >0;
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
			String cv = "QL";
			if(nv.getChucVu().equalsIgnoreCase("Nhân viên")) {
				cv= "NV";
			}
			psm = con.prepareStatement(
					"update NHANVIEN set TENNV = ?, CMND = ?, SDT = ?, GIOITINH = ?, DIACHI = ?, CHUCVU = ? where MANV = ?");
			psm.setString(1, nv.getTenNV());
			psm.setString(2, nv.getCmnd());
			psm.setString(3, nv.getSdt());
			psm.setString(4, gt);
			psm.setString(5, nv.getDiaChi());
			psm.setString(6, cv);
			psm.setString(7, nv.getMaNV());
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
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

	public NhanVien timNhanVienTheoMaNV(String maNV) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		NhanVien nv = null;
		try {
			psm = con.prepareStatement("select * from NHANVIEN where MANV = ?");
			psm.setString(1, maNV);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				boolean gt = false;
				if (rs.getString("GIOITINH").equals("Nam")) {
					gt = true;
				}
				nv = new NhanVien(rs.getString("MANV"), rs.getString("TENNV"), rs.getString("CMND"),
						rs.getString("SDT"), rs.getString("DIACHI"), gt, rs.getString("TAIKHOAN"),
						rs.getString("MATKHAU"), rs.getString("CHUCVU"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nv;
	}

	public ArrayList<NhanVien> getTimKiemDSNV(String maNV, String tenNV, String sdt, String cmnd, String gt,
			String diaChi, String chucVu) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			String query = "SELECT * FROM NHANVIEN where MANV like N'%" + maNV + "%' AND TENNV like N'%" + tenNV
					+ "%' AND CMND  like N'%" + cmnd + "%' AND SDT like N'%" + sdt + "%' AND DIACHI like N'%" + diaChi
					+ "%' AND  GIOITINH like N'%" + gt + "%' AND CHUCVU like N'%"+chucVu+"%'";

			// pstm.setString(1, ten.getMaLoaiKhachHang());
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				boolean gioiTinh = false;
//				boolean gioiTinh = rs.getBoolean("GIOITINH");
				if (rs.getString("GIOITINH").equalsIgnoreCase("Nam")) {
					gioiTinh = true;
				}
				ds.add(new NhanVien(rs.getString("MANV"), rs.getString("TENNV"), rs.getString("SDT"),
						rs.getString("CMND"), gioiTinh, rs.getString("DIACHI"), rs.getString("CHUCVU")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
