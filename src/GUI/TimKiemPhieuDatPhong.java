package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.DAO_CTPDPhong;
import DAO.DAO_CTPhieuDatPhongCho;
import DAO.DAO_KhachHang;
import DAO.DAO_NhanVien;
import DAO.DAO_PhieuDat;
import DAO.DAO_PhieuDatPhongCho;
import DAO.DAO_Phong;
import entity.ChiTietPhieuDatPhong;
import entity.ChiTietPhieuDatPhongCho;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDat;
import entity.PhieuDatPhongCho;
import entity.Phong;

import javax.swing.ImageIcon;

public class TimKiemPhieuDatPhong extends JPanel implements ActionListener {
	private JTextField txtMaPhieuDat;
	private JTextField txtSDTKhachHang;
	private JTable table;
	private JButton btnHuyPhong, btnNhanPhong, btnLamMoi, btnXuatFile, btnTimKiem;
	private DAO_PhieuDatPhongCho pdPhongChoDAO;
	private DAO_KhachHang khachHangDAO;
	private DAO_NhanVien nhanVienDAO;
	private DAO_Phong phongDAO;
	private DAO_CTPhieuDatPhongCho CTPDPhongChoDAO;
	private DefaultTableModel model;
	private NhanVien nhavienHienTai;
	private DAO_PhieuDat phieuDatDAO;
	private DAO_CTPDPhong CTPDPhongDao;

	/**
	 * Create the panel.
	 */
	public TimKiemPhieuDatPhong(NhanVien nv) {
		setLayout(null);
		setBounds(0, 0, 1540, 787);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1520, 755);
		add(panel);
		panel.setLayout(null);

		JLabel lblTimKiemPhieuDatPhong = new JLabel("Tìm Kiếm Phiếu Đặt Phòng");
		lblTimKiemPhieuDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTimKiemPhieuDatPhong.setBounds(622, 10, 277, 25);
		panel.add(lblTimKiemPhieuDatPhong);

		JLabel lblDSPhieuDat = new JLabel("Dang Sách Phiếu Đặt");
		lblDSPhieuDat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDSPhieuDat.setBounds(674, 161, 146, 25);
		panel.add(lblDSPhieuDat);

		JPanel pnlThongTinTim = new JPanel();
		pnlThongTinTim.setLayout(null);
		pnlThongTinTim.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinTim.setBounds(10, 45, 1500, 79);
		panel.add(pnlThongTinTim);

		JLabel lblMaPhieuDat = new JLabel("Mã Phiếu Đặt : ");
		lblMaPhieuDat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMaPhieuDat.setBounds(26, 20, 122, 35);
		pnlThongTinTim.add(lblMaPhieuDat);

		txtMaPhieuDat = new JTextField();
		txtMaPhieuDat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaPhieuDat.setColumns(10);
		txtMaPhieuDat.setBounds(139, 20, 492, 35);
		pnlThongTinTim.add(txtMaPhieuDat);

		JLabel lblSDTKhachHang = new JLabel("SDT Khách Hàng : ");
		lblSDTKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSDTKhachHang.setBounds(641, 19, 155, 35);
		pnlThongTinTim.add(lblSDTKhachHang);

		txtSDTKhachHang = new JTextField();
		txtSDTKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSDTKhachHang.setColumns(10);
		txtSDTKhachHang.setBounds(788, 20, 492, 35);
		pnlThongTinTim.add(txtSDTKhachHang);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setIcon(new ImageIcon(TimKiemPhieuDatPhong.class.getResource("/img/icons8-search-30.png")));
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimKiem.setBackground(new Color(149, 221, 220));
		btnTimKiem.setBounds(1312, 19, 155, 35);
		pnlThongTinTim.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 185, 1500, 506);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null }, },
				new String[] { "Mã Phiếu Đặt", "Mã Phòng", "Số Diện Thoại Khách Hàng", "Ngày Đặt", "Ngày Nhận Phòng",
						"Trạng Thái" }));
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(new Color(40, 142, 144));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLamMoi.setBounds(10, 701, 127, 31);
		panel.add(btnLamMoi);

		btnHuyPhong = new JButton("Hủy phòng");
		btnHuyPhong.setBackground(new Color(205, 47, 70));
		btnHuyPhong.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHuyPhong.setBounds(1231, 701, 127, 31);
		panel.add(btnHuyPhong);

		btnNhanPhong = new JButton("Nhận phòng");
		btnNhanPhong.setBackground(new Color(61, 150, 46));
		btnNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNhanPhong.setBounds(1383, 701, 127, 31);
		panel.add(btnNhanPhong);

		btnXuatFile = new JButton("Xuất file");
		btnXuatFile.setBackground(new Color(197, 107, 42));
		btnXuatFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXuatFile.setBounds(1079, 701, 127, 31);
		panel.add(btnXuatFile);

		model = (DefaultTableModel) table.getModel();
		khachHangDAO = new DAO_KhachHang();
		nhanVienDAO = new DAO_NhanVien();
		pdPhongChoDAO = new DAO_PhieuDatPhongCho();
		CTPDPhongChoDAO = new DAO_CTPhieuDatPhongCho();
		phongDAO = new DAO_Phong();
		CTPDPhongDao = new DAO_CTPDPhong();
		phieuDatDAO = new DAO_PhieuDat();
		kiemTraThoiHanPhieuDatPhong();
		ArrayList<PhieuDatPhongCho> dsPhieuDat = pdPhongChoDAO.getAllphieuDatPhongCho();
		loadDuLieu(dsPhieuDat);
		nhavienHienTai = nv;

		btnLamMoi.addActionListener(this);
		btnXuatFile.addActionListener(this);
		btnHuyPhong.addActionListener(this);
		btnNhanPhong.addActionListener(this);
		btnTimKiem.addActionListener(this);
	}

	public void xoaALLDuLieuTable(DefaultTableModel model) {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	public void loadDuLieu(ArrayList<PhieuDatPhongCho> dss) {
		xoaALLDuLieuTable(model);
		
		for (PhieuDatPhongCho phieuDatPhongCho : dss) {
			ArrayList<ChiTietPhieuDatPhongCho> dsCT = CTPDPhongChoDAO
					.getDSphieuDatTheoMaPhieu(phieuDatPhongCho.getMaPhieuDatPhongCho());
			String maPhong = "";
			for (ChiTietPhieuDatPhongCho ct : dsCT) {
				maPhong += ct.getMaPhong() + ",";
			}
			KhachHang kh = khachHangDAO.timKhachHangTheoMa(phieuDatPhongCho.getMaKhachHang());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String trangThai = "còn hiệu lực";
			if (phieuDatPhongCho.isTinhtrang()) {
				trangThai = "hết hiệu lực";
			}
			Object[] data = { phieuDatPhongCho.getMaPhieuDatPhongCho(), maPhong, kh.getSdt(),
					phieuDatPhongCho.getNgayDangKi().format(formatter),
					phieuDatPhongCho.getNgayNhanPhong().format(formatter), trangThai };
			model.addRow(data);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLamMoi)) {
			xoaALLDuLieuTable(model);
			kiemTraThoiHanPhieuDatPhong();
			ArrayList<PhieuDatPhongCho> ds = pdPhongChoDAO.getAllphieuDatPhongCho();
			xoaALLDuLieuTable(model);
			loadDuLieu(ds);
		} else if (o.equals(btnXuatFile)) {
			int viTri = table.getSelectedRow();
			if (viTri == -1) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn phiếu đặt phòng");
			} else {
				String maphieudat = model.getValueAt(viTri, 0).toString();
				xuatFile(maphieudat);
				int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn xem file", "Phiếu đặt",
						JOptionPane.YES_NO_OPTION);
				if (xacnhan == JOptionPane.YES_OPTION) {
					openFile(maphieudat);
				}
			}
		} else if (o.equals(btnHuyPhong)) {
			boolean ktra = huyDatPhongCho();
			if (ktra) {
				JOptionPane.showMessageDialog(this, "Hủy Phiếu Đặt Thành Công");
				ArrayList<PhieuDatPhongCho> dsPhieuDat = pdPhongChoDAO.getAllphieuDatPhongCho();
				xoaALLDuLieuTable(model);
				loadDuLieu(dsPhieuDat);
			}
		} else if (o.equals(btnNhanPhong)) {
			int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn nhận phòng không?", "Nhận Phòng", JOptionPane.YES_NO_OPTION);
			if(xacnhan==JOptionPane.YES_OPTION) {
				boolean ktra = nhanPhong();
				if (ktra) {
					JOptionPane.showMessageDialog(this, "Nhận phòng Thành Công");
					ArrayList<PhieuDatPhongCho> dsPhieuDat = pdPhongChoDAO.getAllphieuDatPhongCho();
					xoaALLDuLieuTable(model);
					loadDuLieu(dsPhieuDat);
				}
			}
			
		} else if (o.equals(btnTimKiem)) {
			if (txtMaPhieuDat.getText().trim().equals("") && txtSDTKhachHang.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập thông tin tìm kiếm");
			} else if (txtMaPhieuDat.getText().trim().equals("")) {
				String sdt = txtSDTKhachHang.getText().trim();
				ArrayList<PhieuDatPhongCho> dsTheoSDT = pdPhongChoDAO.getPhieuDatTheoSDT(sdt);
				xoaALLDuLieuTable(model);
				loadDuLieu(dsTheoSDT);
			} else if (txtSDTKhachHang.getText().trim().equals("")) {
				String maPhieuDat = txtMaPhieuDat.getText().trim();
				ArrayList<PhieuDatPhongCho> dsTheoMaPhieu = pdPhongChoDAO.getPhieuDatTheoMaPhieu(maPhieuDat);
				xoaALLDuLieuTable(model);
				loadDuLieu(dsTheoMaPhieu);
			} else {
				String sdt = txtSDTKhachHang.getText().trim();
				String maPhieuDat = txtMaPhieuDat.getText().trim();
				ArrayList<PhieuDatPhongCho> ds1 = pdPhongChoDAO.getPhieuDatTheoSDTvaMaPhieu(sdt, maPhieuDat);
				xoaALLDuLieuTable(model);
				loadDuLieu(ds1);
			}
		}

	}

	public boolean huyDatPhongCho() {
		int viTri = table.getSelectedRow();
		if (viTri == -1) {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn phiếu đặt phòng");
			return false;
		} else {
			String maphieudat = model.getValueAt(viTri, 0).toString();
			PhieuDatPhongCho pd = pdPhongChoDAO.getPhieuDat(maphieudat);
			if(pd.isTinhtrang()) {
				JOptionPane.showMessageDialog(this, "Phiếu đặt đã hết hiệu lực");
				return false;
			}
			int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy phiếu đặt này không", "Phiếu đặt",
					JOptionPane.YES_NO_OPTION);
			if (xacnhan == JOptionPane.YES_OPTION) {
				if (!pdPhongChoDAO.huyPhieuDat(maphieudat)) {
					thongBaoLoi("Lỗi hủy phiếu đặt", "lỗi");
					return false;
				}
				ArrayList<ChiTietPhieuDatPhongCho> dsct = CTPDPhongChoDAO.getDSphieuDatTheoMaPhieu(maphieudat);
				for (ChiTietPhieuDatPhongCho chiTietPhieuDatPhongCho : dsct) {
					String trangThai = "TRỐNG";
					String trangThai2 = "ĐANG SỬ DỤNG";
					Phong phongSD = phongDAO.timPhongTheoMaPhong(chiTietPhieuDatPhongCho.getMaPhong());
					if (phongSD.getTrangThai().equalsIgnoreCase("CHỜ(Sử dụng)")) {
						if (!phongDAO.capNhapTrangThaiPhong(trangThai2, chiTietPhieuDatPhongCho.getMaPhong())) {
							JOptionPane.showMessageDialog(this, "Lỗi cấp nhập trạng thái phòng");
							return false;
						}
					} else {
						if (!phongDAO.capNhapTrangThaiPhong(trangThai, chiTietPhieuDatPhongCho.getMaPhong())) {
							JOptionPane.showMessageDialog(this, "Lỗi cấp nhập trạng thái phòng");
							return false;
						}
					}

				}
			}
			else {
				return false;
			}
		}
		return true;
	}

	public boolean nhanPhong() {
		int viTri = table.getSelectedRow();
		if (viTri == -1) {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn phiếu đặt phòng");
			return false;
		} else {
			String maphieudat = model.getValueAt(viTri, 0).toString();// phieu dat phòng chờ
			PhieuDatPhongCho pdPhongCho = pdPhongChoDAO.getPhieuDat(maphieudat);
			if (pdPhongCho.isTinhtrang()) {
				JOptionPane.showMessageDialog(this, "Phiếu đặt phòng đã hết hiệu lực vui lòng chọn phiếu khác");
				return false;
			}
			KhachHang kh = khachHangDAO.timKhachHangTheoMa(pdPhongCho.getMaKhachHang());
			LocalDateTime ngayDat = LocalDateTime.now();
			boolean trangthai = false;
			String maPhieuDatSuDungNgay = taoMaPhieuDat(); // phieudat cho sử dung ngay
			PhieuDat pd = new PhieuDat(maPhieuDatSuDungNgay, kh.getMaKH(), nhavienHienTai.getMaNV(), ngayDat,
					trangthai);
			if (!phieuDatDAO.themPhieuDat(pd)) {
				thongBaoLoi("Lỗi tạo phiếu đặt", "lỗi");
				return false;
			}
			ArrayList<ChiTietPhieuDatPhongCho> dsct = CTPDPhongChoDAO.getDSphieuDatTheoMaPhieu(maphieudat);
			for (ChiTietPhieuDatPhongCho chiTietPhieuDatPhongCho : dsct) {
				Phong phongSD = phongDAO.timPhongTheoMaPhong(chiTietPhieuDatPhongCho.getMaPhong());
				if (!phongDAO.capNhapTrangThaiPhong("Đang Sử Dụng", phongSD.getMaPhong())) {
					thongBaoLoi("Lỗi cập nhập trạng thái phòng", "lỗi");
					return false;
				}
				ChiTietPhieuDatPhong ctpd = new ChiTietPhieuDatPhong(maPhieuDatSuDungNgay, phongSD);
				if (!CTPDPhongDao.themChiTietPhieuDatP(ctpd)) { // CTPDPHong tạo chi tiết phieu dặt phong cho khách hàng
																// dụng ngay
					thongBaoLoi("Lỗi tạo phiếu chi tiết đặt phòng", "lỗi");
					return false;
				}
			}
			if (!pdPhongChoDAO.capNhapTrangThai(maphieudat)) {
				thongBaoLoi("Lỗi cập nhập trang Thái phiếu đặt phòng chờ", "lỗi");
				return false;
			}
		}

		return true;
	}

	public String taoMaPhieuDat() {
		int i = 0;
		ArrayList<PhieuDat> ds = phieuDatDAO.getALLPhieuDat();
		if (ds == null) {
			return String.format("PD%04d", i + 1);
		}
		while (i < ds.size()) {
			if (Integer.parseInt(ds.get(i).getMaPhieuDat().substring(2)) == (i + 1)) {
				i++;

			} else {
				break;
			}
		}
		return String.format("PD%04d", i + 1);
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	public void xuatFile(String maPhieuDat) {
		PhieuDatPhongCho pd = pdPhongChoDAO.getPhieuDat(maPhieuDat);
		try {
			// tao tai lieu in
			String urlFont = System.getProperty("user.dir") + "\\libs\\Arial Unicode MS.ttf";
			BaseFont unicodeFont = BaseFont.createFont(urlFont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			com.itextpdf.text.Font unicodeFontObject = new com.itextpdf.text.Font(unicodeFont, 15);
			Document document = new Document();
			document.setMargins(50, 50, 10, 0);
			// chon noi luu
			String url = "";
			url = System.getProperty("user.dir") + "\\src\\phieuDat\\";
			url += maPhieuDat + ".pdf";
			String filename = url;
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
			// tiêu đề
			String tenQuan = "KARAOKE NICE";
			Paragraph ten = new Paragraph(tenQuan, unicodeFontObject);
			ten.setAlignment(Element.ALIGN_CENTER);
			document.add(ten);
			String diaChi = "Nguyễn Văn Bảo Phường 4 Gò Vấp Thành phố Hồ Chí Minh\n";
			Paragraph dc = new Paragraph(diaChi, unicodeFontObject);
			dc.setAlignment(Element.ALIGN_CENTER);
			document.add(dc);
			Paragraph hoaDonThanhToan = new Paragraph("PHIẾU ĐẶT PHÒNG CHỜ",
					new com.itextpdf.text.Font(unicodeFont, 20, com.itextpdf.text.Font.BOLD));
			Paragraph dong = new Paragraph("********************", unicodeFontObject);
			hoaDonThanhToan.setAlignment(Element.ALIGN_CENTER);
			document.add(hoaDonThanhToan);
			dong.setAlignment(Element.ALIGN_CENTER);
			document.add(dong);

			// THÔNG TIN QUÁN VÀ THÔNG TIN KHÁCH HÀNH NHÂN VIÊN
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String ngayTao = "Ngày Lập Phiếu:\t		" + pd.getNgayDangKi().format(formatter);
			String ngayNhan = "Ngày Nhận Phong:\t		" + pd.getNgayNhanPhong().format(formatter);
			String phieuDat = "Mã phiếu đặt:\t		" + maPhieuDat;
			Paragraph maPhieu = new Paragraph(phieuDat, unicodeFontObject);
			maPhieu.setAlignment(Element.ALIGN_LEFT);
			document.add(maPhieu);
			Paragraph ngayTao1 = new Paragraph(ngayTao, unicodeFontObject);
			ngayTao1.setAlignment(Element.ALIGN_LEFT);
			document.add(ngayTao1);
			Paragraph ngayNhan1 = new Paragraph(ngayNhan, unicodeFontObject);
			ngayNhan1.setAlignment(Element.ALIGN_LEFT);
			document.add(ngayNhan1);
			NhanVien nv = nhanVienDAO.timNhanVienTheoMaNV(pd.getMaNhanVien());
			KhachHang kh = khachHangDAO.timKhachHangTheoMa(pd.getMaKhachHang());
			String nhanVien = "Nhân Viên:\t		" + nv.getTenNV();
			String kh1 = "Khách Hàng:\t		" + kh.getTenKH();
			String sdt = "số diện thoai:\t" + kh.getSdt();
			Paragraph sdtKH = new Paragraph(sdt, unicodeFontObject);
			sdtKH.setAlignment(Element.ALIGN_LEFT);
			Paragraph nv1 = new Paragraph(nhanVien, unicodeFontObject);
			nv1.setAlignment(Element.ALIGN_LEFT);
			Paragraph kh2 = new Paragraph(kh1, unicodeFontObject);
			kh2.setAlignment(Element.ALIGN_LEFT);
			document.add(nv1);
			document.add(kh2);
			document.add(sdtKH);

			document.add(Chunk.NEWLINE);

			document.add(Chunk.NEWLINE);
			Paragraph dong3 = new Paragraph("\nXin Cảm Ơn",
					new com.itextpdf.text.Font(unicodeFont, 10, com.itextpdf.text.Font.BOLD));
			dong3.setAlignment(Element.ALIGN_CENTER);
			document.add(dong3);

			document.close();
			System.out.println("Tạo tệp PDF hóa đơn thành công.");
		} catch (DocumentException | FileNotFoundException | MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openFile(String maPhieuDat) {
		String url = "";
		url = System.getProperty("user.dir") + "\\src\\phieuDat\\" + maPhieuDat + ".pdf";
		File file = new File(url);
		Desktop dt = Desktop.getDesktop();
		try {
			dt.open(file);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void kiemTraThoiHanPhieuDatPhong() {
		ArrayList<PhieuDatPhongCho> ds = pdPhongChoDAO.getAllphieuDatPhongChoConHieuLuc();
		for (PhieuDatPhongCho phieuDatPhongCho : ds) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime ngayNhanPhong = phieuDatPhongCho.getNgayNhanPhong();
			Duration ktra = Duration.between(ngayNhanPhong, now);

			long ktraPhut = ktra.toMinutes();
			if (ktraPhut > 30) {
				if (!pdPhongChoDAO.huyPhieuDat(phieuDatPhongCho.getMaPhieuDatPhongCho())) {
					JOptionPane.showMessageDialog(this, "lỗi phiếu đặt phòng chơ");
					return;
				}

				ArrayList<ChiTietPhieuDatPhongCho> dsCT = CTPDPhongChoDAO
						.getDSphieuDatTheoMaPhieu(phieuDatPhongCho.getMaPhieuDatPhongCho());
				for (ChiTietPhieuDatPhongCho chiTietPhieuDatPhongCho : dsCT) {
					Phong phongCho = phongDAO.timPhongTheoMaPhong(chiTietPhieuDatPhongCho.getMaPhong());
					String trangThai = "TRỐNG";
					if (phongDAO.capNhapTrangThaiPhong(trangThai, phongCho.getMaPhong())) {
						JOptionPane.showMessageDialog(this, "Lỗi Phòng");
						return;
					}
				}
			}
		}
	}
}

/*
 * còn tìm kiếm theo 2 text --> done tạo xử lý hủy phiếu đặt nếu quá thời hạn
 * nhận phòng tạo xử lí có được đặt phong chờ đó hay không --> nếu dc thì chuyen
 * sang trangthái --> chờ(sử dụng) và thanh toán phòng chuyenr trang trạng thái
 * chờ
 */
