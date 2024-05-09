package entity;

public class ChiTietPhieuDatDichVu {
	private String maPhieuDatDV;
	private String maDV;
	private int soLuong;

	public ChiTietPhieuDatDichVu() {
		super();
	}

	public ChiTietPhieuDatDichVu(String maPhieuDatDV, String maDV, int soLuong) {
		super();
		this.maPhieuDatDV = maPhieuDatDV;
		this.maDV = maDV;
		this.soLuong = soLuong;
	}

	public String getMaPhieuDatDV() {
		return maPhieuDatDV;
	}

	public void setMaPhieuDatDV(String maPhieuDatDV) {
		this.maPhieuDatDV = maPhieuDatDV;
	}

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "ChiTietPhieuDatDichVu [maPhieuDatDV=" + maPhieuDatDV + ", maDV=" + maDV + ", soLuong=" + soLuong + "]";
	}

}
