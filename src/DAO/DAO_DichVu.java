package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import connectDB.connectDB;
import entity.DichVu;

public class DAO_DichVu {
	public DAO_DichVu() {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public ArrayList<DichVu> getALLDichVu() {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			String sql = "select * from DICHVU";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsDV.add(new DichVu(rs.getString("MADV"), rs.getString("TENDV"), rs.getLong("DONGIA"),
						rs.getString("DONVI"), rs.getInt("SOLUONGTONKHO")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsDV;
	}

	public boolean xoaDichVu(String maDV) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("delete from DICHVU where MADV = ?");
			psm.setString(1, maDV);
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean themDichVu(DichVu x) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("insert into DichVu values(?,?,?,?,?)");
			psm.setString(1, x.getMaDV());
			psm.setString(2, x.getTenDichVu());
			psm.setDouble(3, x.getDonGia());
			psm.setString(4, x.getDonVi());
			psm.setInt(5, x.getSoLuongTonKho());
			return psm.executeUpdate() >0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateDichVu(DichVu x) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("update DICHVU SET TENDV = ?, DONGIA = ?, SOLUONGTONKHO = ?, DONVI =? WHERE MADV =?");
			psm.setString(1, x.getTenDichVu());
			psm.setDouble(2, x.getDonGia());
			psm.setInt(3, x.getSoLuongTonKho());
			psm.setString(4, x.getDonVi());
			psm.setString(5, x.getMaDV());
			return psm.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public DichVu timDVTheoMaDV(String maDV) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm=null;
		DichVu dv = null;
		try {
			String sql = "select * from DICHVU where maDV = ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maDV);
			ResultSet rs = psm.executeQuery();
			while(rs.next()) {
				dv = new DichVu(rs.getString("MADV"),rs.getString("TENDV"),rs.getDouble("DONGIA"),rs.getString("DONVI"),rs.getInt("SOLUONGTONKHO"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dv;
	}
	public boolean capNhapSoLuongTonDV(String madv, int slMoi) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("update DICHVU SET SOLUONGTONKHO = ? WHERE MADV =?");
			psm.setInt(1, slMoi);
			psm.setString(2, madv);
			return psm.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<DichVu> search(String giaTriTimKiem) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<DichVu> ds = new ArrayList<DichVu>();
		try {
			int so = -1;
			if (giaTriTimKiem.isEmpty() == false && giaTriTimKiem.chars().allMatch(Character::isDigit)) {
				so = Integer.parseInt(giaTriTimKiem);
			}
			String query = "select maDV, TENDV, donGia, donVi, SOLUONGTONKHO from DichVu  where maDV like N'%"
					+ giaTriTimKiem + "%' or TENDV like N'%" + giaTriTimKiem+ "%' or donGia = " + so
					+ " or donVi like N'%" + giaTriTimKiem+ "%' or SOLUONGTONKHO = " + so + "";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				
				ds.add(new DichVu(rs.getString("maDV"), rs.getString("TENDV"), rs.getDouble("donGia"),
						rs.getString("donVi"), rs.getInt("SOLUONGTONKHO")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
