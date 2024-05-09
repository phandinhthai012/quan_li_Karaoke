package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.DichVu;
import entity.LoaiPhong;
import entity.Phong;

public class DAO_Phong {

	private DAO_LoaiPhong daoLoaiPhong = new DAO_LoaiPhong();

	public DAO_Phong() {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public ArrayList<Phong> getALLPhong() {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			String sql = "select * from PHONG AS p join LOAIPHONG as lp on p.MALOAIPHONG=lp.MALOAIPHONG";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("MAPHONG"), rs.getString("TENPHONG"), rs.getString("TRANGTHAI"),
						new LoaiPhong(rs.getString("MALOAIPHONG"), rs.getString("TENLOAIPHONG")), rs.getInt("SUCCHUA"),
						rs.getLong("DONGIA")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}

	public boolean capNhapPhong(Phong x) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement(
					"update PHONG set TENPHONG = ?, TRANGTHAI =? ,SUCCHUA = ?, MALOAIPHONG= ?, DONGIA = ? where  MAPHONG = ?");
			psm.setString(1, x.getTenPhong());
			psm.setString(2, x.getTrangThai());
			psm.setInt(3, x.getSucChua());
			psm.setString(4, x.getLoaiPhong().getMaLoaiPhong());
			psm.setDouble(5, x.getDonGia());
			psm.setString(6, x.getMaPhong());
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public Phong timPhongTheoMaPhong(String maPhong) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm;
		Phong ph = null;
		try {
			String sql = "select * from PHONG AS p join LOAIPHONG as lp on p.MALOAIPHONG=lp.MALOAIPHONG where p.MAPHONG = ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, maPhong);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				ph = new Phong(rs.getString("MAPHONG"), rs.getString("TENPHONG"), rs.getString("TRANGTHAI"),
						new LoaiPhong(rs.getString("MALOAIPHONG"), rs.getString("TENLOAIPHONG")), rs.getInt("SUCCHUA"),
						rs.getLong("DONGIA"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ph;
	}

	public boolean capNhapTrangThaiPhong(String trangThai, String maPhong) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm;
		try {
			String sql = "update PHONG set TRANGTHAI = ? where MAPHONG = ?";
			psm = con.prepareStatement(sql);
			psm.setString(1, trangThai);
			psm.setString(2, maPhong);
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<String> getDSTrangThai() {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm;
		ArrayList<String> dsTrangThai= new ArrayList<String>();
		try {
			String sql = "select * from TRANGTHAIPHONG";
			psm = con.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while(rs.next()) {
				dsTrangThai.add(rs.getString("tenTrangThaiPhong"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTrangThai;
	}
	public boolean themPhong(Phong x) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm=null;
		try {
			String sql = "INSERT [dbo].[Phong] ([maPhong], [TENPHONG], [TRANGTHAI], [MALOAIPHONG], [SUCCHUA],[DONGIA]) VALUES(?,?,?,?,?,?)";
			psm = con.prepareStatement(sql);
			psm.setString(1, x.getMaPhong());
			psm.setString(2, x.getTenPhong());
			psm.setString(3, x.getTrangThai());
			psm.setString(4, x.getLoaiPhong().getMaLoaiPhong());
			psm.setInt(5, x.getSucChua());
			psm.setDouble(6, x.getDonGia());
			return psm.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Phong> search(String giaTriTimKiem) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<Phong> ds = new ArrayList<Phong>();
		try {
			int so = -1;
			if (giaTriTimKiem.isEmpty() == false && giaTriTimKiem.chars().allMatch(Character::isDigit)) {
				so = Integer.parseInt(giaTriTimKiem);
			}
			String query = "select a.maPhong, a.TENPHONG, a.MALOAIPHONG,a.DONGIA ,a.SUCCHUA ,a.TRANGTHAI  from Phong  a inner join  LOAIPHONG  b on a.MALOAIPHONG = b.MALOAIPHONG where maPhong like N'%"
					+ giaTriTimKiem + "%' or TENPHONG like N'%" + giaTriTimKiem + "%' or TENLOAIPHONG like '%"
					+ giaTriTimKiem + "%' or DONGIA =" + so + " or SUCCHUA = " + so + "or TRANGTHAI like N'%"
					+ giaTriTimKiem + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				LoaiPhong loai = daoLoaiPhong.getLoaiTheoMa(rs.getString("MALOAIPHONG"));
				ds.add(new Phong(rs.getString("maPhong"), rs.getString("TENPHONG"), loai, rs.getDouble("DONGIA"),
						rs.getInt("SUCCHUA"), rs.getString("TRANGTHAI")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
