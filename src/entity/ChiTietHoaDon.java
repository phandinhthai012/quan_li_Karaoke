package entity;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private Phong phong;
	private int thoiLuong;
	public ChiTietHoaDon() {
		super();
	}
	public ChiTietHoaDon(HoaDon hoaDon, Phong phong, int thoiLuong) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
		this.thoiLuong = thoiLuong;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	
	public int getThoiLuong() {
		return thoiLuong;
	}
	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", phong=" + phong + ", thoiLuong=" + thoiLuong + "]";
	}

	
	
	
}
