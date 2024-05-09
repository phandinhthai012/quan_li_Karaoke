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

public class DAO_KhachHang {
	public DAO_KhachHang() {
		try {
			connectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<KhachHang> getALLKhachHang() {
		ArrayList<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		try {
			String sql = "select * from KHACHHANG";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsKhachHang.add(new KhachHang(rs.getString("MAKH"), rs.getString("TENKH"), rs.getString("SDT"),
						rs.getString("DIACHI")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	public boolean themKhachHang(KhachHang kh) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("insert into KHACHHANG ([MAKH],[TENKH],[SDT],[DIACHI]) values(?,?,?,?)");
			psm.setString(1, kh.getMaKH());
			psm.setString(2, kh.getTenKH());
			psm.setString(3, kh.getSdt());
			psm.setString(4, kh.getDiaChi());
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean xoaKhachHang(String maKH) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("delete from KhachHang where maKH = ?");
			psm.setString(1, maKH);
			return psm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				psm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return false;
	}

	public KhachHang timKhachHangTheoMa(String makh) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		KhachHang kh = null;
		try {
			psm = con.prepareStatement("select * from KHACHHANG where MAKH = ?");
			psm.setString(1, makh);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString("MAKH"), rs.getString("TENKH"), rs.getString("SDT"),
						rs.getString("DIACHI"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				psm.close();
//				con.close();
//			} catch (SQLException e2) {
//				e2.printStackTrace();
//			}
//		}
		return kh;
	}
	public KhachHang timKhachHangTheoSDT(String SDT) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		KhachHang kh = null;
		try {
			psm = con.prepareStatement("select * from KHACHHANG where SDT =?");
			psm.setString(1, SDT);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString("MAKH"), rs.getString("TENKH"), rs.getString("SDT"),
						rs.getString("DIACHI"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}

//	public String phatSinhMaTuDong() {
//		Session session = sessionFactory.getCurrentSession();
//		Transaction tr = session.getTransaction();
//		String maKhachH = null;
//		String sql = "Select max(maKH) from KhachHang";
//		try {
//			tr.begin();
//			maKhachH = (String) session.createNativeQuery(sql).getSingleResult();
//			tr.commit();
//			System.out.println(maKhachH);
//			maKhachH = new MaTuDong().fomatAA000(maKhachH);
////			System.out.println(maKhachH);
//			return maKhachH;
//		} catch (Exception e) {
//			e.printStackTrace();
//			tr.rollback();
//		}
//		session.close();
//		return null;
//	}
	public boolean capNhapKhachHang(KhachHang kh) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement psm = null;
		try {
			psm = con.prepareStatement("update KHACHHANG set TENKH = ?, SDT = ?, DIACHI = ? where MAKH = ?");
			psm.setString(1, kh.getTenKH());
			psm.setString(2, kh.getSdt());
			psm.setString(3, kh.getDiaChi());
			psm.setString(4, kh.getMaKH());
			return psm.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<KhachHang> getTimKiemDSKH(String maKH, String tenKH, String sdt, String diaChi) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		try {
			String query = "SELECT  * FROM KhachHang  where MAKH like N'%"
					+ maKH + "%' AND TENKH like N'%"+ tenKH +"%' AND SDT like N'%"+sdt+"%' AND DIACHI like N'%"+diaChi+"%'";
			// pstm.setString(1, ten.getMaLoaiKhachHang());
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
		
				ds.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

}
