package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class PhieuDatPhongCho {
	private String maPhieuDatPhongCho;
	private String maKhachHang;
	private String maNhanVien;
	private LocalDateTime ngayDangKi;
	private LocalDateTime ngayNhanPhong;
	private boolean tinhtrang; // 0 = còn hiệu lực , 1 = hết hiệu lực, 2= dã nhận phòng

	public PhieuDatPhongCho() {
		super();
	}
	public PhieuDatPhongCho(String maPhieuDatPhongCho, String maKhachHang, String maNhanVien, LocalDateTime ngayDangKi,
			LocalDateTime ngayNhanPhong, boolean tinhtrang) {
		super();
		this.maPhieuDatPhongCho = maPhieuDatPhongCho;
		this.maKhachHang = maKhachHang;
		this.maNhanVien = maNhanVien;
		this.ngayDangKi = ngayDangKi;
		this.ngayNhanPhong = ngayNhanPhong;
		this.tinhtrang = tinhtrang;
	}
	public String getMaPhieuDatPhongCho() {
		return maPhieuDatPhongCho;
	}
	public void setMaPhieuDatPhongCho(String maPhieuDatPhongCho) {
		this.maPhieuDatPhongCho = maPhieuDatPhongCho;
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
	public LocalDateTime getNgayDangKi() {
		return ngayDangKi;
	}
	public void setNgayDangKi(LocalDateTime ngayDangKi) {
		this.ngayDangKi = ngayDangKi;
	}
	public LocalDateTime getNgayNhanPhong() {
		return ngayNhanPhong;
	}
	public void setNgayNhanPhong(LocalDateTime ngayNhanPhong) {
		this.ngayNhanPhong = ngayNhanPhong;
	}
	public boolean isTinhtrang() {
		return tinhtrang;
	}
	public void setTinhtrang(boolean tinhtrang) {
		this.tinhtrang = tinhtrang;
	}
	
	@Override
	public String toString() {
		return "PhieuDatPhongCho [maPhieuDatPhongCho=" + maPhieuDatPhongCho + ", maKhachHang=" + maKhachHang
				+ ", maNhanVien=" + maNhanVien + ", ngayDangKi=" + ngayDangKi + ", ngayNhanPhong=" + ngayNhanPhong
				+ ", tinhtrang=" + tinhtrang + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhieuDatPhongCho);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDatPhongCho other = (PhieuDatPhongCho) obj;
		return Objects.equals(maPhieuDatPhongCho, other.maPhieuDatPhongCho);
	}

}
