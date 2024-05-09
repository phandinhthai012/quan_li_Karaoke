package entity;

import java.util.Objects;

public class Phong {
	private String maPhong;
	private String tenPhong;
	private String trangThai;
	private LoaiPhong loaiPhong;
	private int sucChua;
	private double DonGia;
	public Phong() {
		super();
	}
	public Phong(String maPhong, String tenPhong, String trangThai, LoaiPhong loaiPhong, int sucChua, double donGia) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.trangThai = trangThai;
		this.loaiPhong = loaiPhong;
		this.sucChua = sucChua;
		this.DonGia = donGia;
	}
	public Phong(String maPhong, String tenPhong, LoaiPhong loaiPhong, double donGia, int sucChua,String trangThai) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.trangThai = trangThai;
		this.loaiPhong = loaiPhong;
		this.sucChua = sucChua;
		this.DonGia = donGia;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}
	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}
	public int getSucChua() {
		return sucChua;
	}
	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}
	public double getDonGia() {
		return DonGia;
	}
	public void setDonGia(double donGia) {
		DonGia = donGia;
	}
	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", trangThai=" + trangThai + ", loaiPhong="
				+ loaiPhong + ", sucChua=" + sucChua + ", DonGia=" + DonGia + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}
	
	
}
