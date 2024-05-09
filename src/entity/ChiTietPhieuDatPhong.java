package entity;

import java.sql.Date;

public class ChiTietPhieuDatPhong {
	private String maPhieuDat;
	private Phong phong;
	public ChiTietPhieuDatPhong() {
		super();
	}
	public ChiTietPhieuDatPhong(String maPhieuDat, Phong phong) {
		super();
		this.maPhieuDat = maPhieuDat;
		this.phong = phong;
	}
	public String getMaPhieuDat() {
		return maPhieuDat;
	}
	public void setMaPhieuDat(String maPhieuDat) {
		this.maPhieuDat = maPhieuDat;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	@Override
	public String toString() {
		return "ChiTietPhieuDatPhong [maPhieuDat=" + maPhieuDat + ", phong=" + phong + "]";
	}
	
}
