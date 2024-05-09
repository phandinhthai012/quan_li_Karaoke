package entity;

public class ChiTietPhieuDatPhongCho {
	private String maPhieuDatPhongCho;
	private String maPhong;
	public ChiTietPhieuDatPhongCho() {
		super();
	}
	public ChiTietPhieuDatPhongCho(String maPhieuDatPhongCho, String maPhong) {
		super();
		this.maPhieuDatPhongCho = maPhieuDatPhongCho;
		this.maPhong = maPhong;
	}
	public String getMaPhieuDatPhongCho() {
		return maPhieuDatPhongCho;
	}
	public void setMaPhieuDatPhongCho(String maPhieuDatPhongCho) {
		this.maPhieuDatPhongCho = maPhieuDatPhongCho;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	@Override
	public String toString() {
		return "ChiTietPhieuDatPhongCho [maPhieuDatPhongCho=" + maPhieuDatPhongCho + ", maPhong=" + maPhong + "]";
	}
	
	
}
