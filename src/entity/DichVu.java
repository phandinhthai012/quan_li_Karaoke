package entity;

import java.util.Objects;

public class DichVu {
	private String maDV;
	private String tenDichVu;
	private double donGia;
	private String donVi;
	private int soLuongTonKho;
	public DichVu(String maDV, String tenDichVu, double donGia, String donVi, int soLuongTonKho) {
		setMaDV(maDV);
		this.tenDichVu = tenDichVu;
		this.donGia = donGia;
		this.donVi = donVi;
		this.soLuongTonKho = soLuongTonKho;
	}
	public DichVu() {

	}
	public String getMaDV() {
		return maDV;
	}
	public void setMaDV(String maDV) {
		this.maDV = maDV;
		
	}
	public String getTenDichVu() {
		return tenDichVu;
	}
	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		if(donGia<0) {
			this.donGia =0;
		}
		else {
			this.donGia = donGia;
		}
		
	}
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	public int getSoLuongTonKho() {
		return soLuongTonKho;
	}
	public void setSoLuongTonKho(int soLuongTonKho) {
		this.soLuongTonKho = soLuongTonKho;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maDV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDV, other.maDV);
	}
	@Override
	public String toString() {
		return "DichVu [maDV=" + maDV + ", tenDichVu=" + tenDichVu + ", donGia=" + donGia + ", donVi=" + donVi
				+ ", soLuongTonKho=" + soLuongTonKho + "]";
	}
	
	
	
}
