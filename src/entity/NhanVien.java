package entity;

import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String cmnd;
	private String sdt;
	private String diaChi;
	private boolean gioiTinh;
	private String taiKhoan;
	private String matKhau;
	private String chucVu;
	public NhanVien(String maNV, String tenNV, String cmnd, String sdt, String diaChi, boolean gioiTinh,
			String taiKhoan, String matKhau, String chucVu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.cmnd = cmnd;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.chucVu = chucVu;
	}
	
	public NhanVien(String maNV, String tenNV, String cmnd, String sdt, String diaChi, boolean gioiTinh,
			String chucVu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.cmnd = cmnd;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.chucVu = chucVu;
	}
	public NhanVien(String maNV, String tenNV,  String sdt,String cmnd,   boolean gioiTinh,String diaChi,
			String chucVu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.cmnd = cmnd;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.chucVu = chucVu;
	}
	public NhanVien(String maNV, String tenNV, String sdt, String cmnd, boolean gioiTinh,String diaChi,
			String taiKhoan, String matKhau, String chucVu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.cmnd = cmnd;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.chucVu = chucVu;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", cmnd=" + cmnd + ", sdt=" + sdt + ", diaChi=" + diaChi
				+ ", gioiTinh=" + gioiTinh + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", chucVu=" + chucVu
				+ "]";
	}
	
	
}
