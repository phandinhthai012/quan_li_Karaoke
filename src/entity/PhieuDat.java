package entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public class PhieuDat {
	private String maPhieuDat;
	private String maKhachHang;
	private String maNhanVien;
	private LocalDateTime ngayDat;
	private boolean trangThai;
	public PhieuDat() {
		super();
	}
	public PhieuDat(String maPhieuDat, String maKhachHang, String maNhanVien, LocalDateTime ngayDat,
			boolean trangThai) {
		super();
		this.maPhieuDat = maPhieuDat;
		this.maKhachHang = maKhachHang;
		this.maNhanVien = maNhanVien;
		this.ngayDat = ngayDat;
		this.trangThai = trangThai;
	}
	public String getMaPhieuDat() {
		return maPhieuDat;
	}
	public void setMaPhieuDat(String maPhieuDat) {
		this.maPhieuDat = maPhieuDat;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public LocalDateTime getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(LocalDateTime ngayDat) {
		this.ngayDat = ngayDat;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return "PhieuDat [maPhieuDat=" + maPhieuDat + ", maKhachHang=" + maKhachHang + ", maNhanVien=" + maNhanVien
				+ ", ngayDat=" + ngayDat + ", trangThai=" + trangThai + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhieuDat);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDat other = (PhieuDat) obj;
		return Objects.equals(maPhieuDat, other.maPhieuDat);
	}
	

}
