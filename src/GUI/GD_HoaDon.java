package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
//import java.lang.System.Logger;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.DAO_CTPDDichVu;
import DAO.DAO_CTPDPhong;
import DAO.DAO_ChiTietDichVu;
import DAO.DAO_ChiTietHoaDon;
import DAO.DAO_DichVu;
import DAO.DAO_HoaDon;
import DAO.DAO_KhachHang;
import DAO.DAO_NhanVien;
import DAO.DAO_PhieuDat;
import DAO.DAO_Phong;
import connectDB.connectDB;
//import org.w3c.dom.Document;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatDichVu;
import entity.ChiTietPhieuDatPhong;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDat;
import entity.Phong;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

public class GD_HoaDon extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtMaHoaDon;
	private JTextField txtNV;
	private JTextField txtKH;
	private JTextField txtNgayTao;
	private JTextField txtSDT;
	private JTable table;
	private JTextField txtTienNhan;
	private JTextField txtTienThua;
	private JTextField txtTongTien;
	private JButton btnQuayLai, btnXuatFile, btnThanhToan;
	private DefaultTableModel model;

	private DAO_ChiTietDichVu CTDVDAO;
	private DAO_KhachHang khachHangDAO;
	private DAO_HoaDon hoaDonDAO;
	private DAO_ChiTietHoaDon CTHDDAO;
	private DAO_NhanVien nhanVienDAO;
	private DAO_Phong phongDAO;
	private ArrayList<ChiTietDichVu> dsChiTietDV;
	private ArrayList<ChiTietHoaDon> dsChiTietHD;
	private ArrayList<ChiTietPhieuDatDichVu> dsPhieuDatDV;
	private ArrayList<ChiTietPhieuDatPhong> dsPhieuDatPhong;
	private DAO_PhieuDat phieuDatDao;
	private DAO_CTPDDichVu ctpdDVDao;
	private DAO_CTPDPhong ctpdPhongDao;
	private DAO_DichVu dichVuDao;
	private double tongTien = 0;
	private NhanVien nhanVienHienTai;
	private HoaDon hoaDonHienTai;
	private KhachHang khachHangThanhToan;
	private PhieuDat phieuDat;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GD_HoaDon frame = new GD_HoaDon();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GD_HoaDon(String maPhieuDat, String maKH, NhanVien nv) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 10, 842, 787);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbTenQuan = new JLabel("Tên Quán : ");
		lbTenQuan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTenQuan.setBounds(10, 10, 88, 24);
		contentPane.add(lbTenQuan);

		JLabel lbNice = new JLabel("Karaoke Nice");
		lbNice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbNice.setBounds(94, 10, 88, 24);
		contentPane.add(lbNice);

		JLabel lbDiaChi = new JLabel("Địa Chỉ :");
		lbDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDiaChi.setBounds(10, 54, 65, 24);
		contentPane.add(lbDiaChi);

		JLabel lblNguynVnBo = new JLabel("Nguyễn Văn Bảo Phường 4 Gò Vấp ");
		lblNguynVnBo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNguynVnBo.setBounds(94, 54, 263, 24);
		contentPane.add(lblNguynVnBo);

		JLabel lbMaHoaDon = new JLabel("Mã Hóa Đơn :");
		lbMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbMaHoaDon.setBounds(10, 110, 101, 24);
		contentPane.add(lbMaHoaDon);

		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setBounds(121, 110, 176, 24);
		contentPane.add(txtMaHoaDon);
		txtMaHoaDon.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(228, 228, 228));
		panel.setBounds(10, 160, 792, 417);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbHoaDonTinhTien = new JLabel("Hóa Đơn Tính Tiền");
		lbHoaDonTinhTien.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbHoaDonTinhTien.setBounds(308, -2, 121, 33);
		panel.add(lbHoaDonTinhTien);

		JLabel lbNV = new JLabel("Nhân Viên :");
		lbNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbNV.setBounds(25, 41, 88, 24);
		panel.add(lbNV);

		JLabel lbKH = new JLabel("Khách Hàng :");
		lbKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbKH.setBounds(25, 99, 99, 24);
		panel.add(lbKH);

		txtNV = new JTextField();
		txtNV.setColumns(10);
		txtNV.setBounds(123, 41, 176, 24);
		panel.add(txtNV);

		txtKH = new JTextField();
		txtKH.setColumns(10);
		txtKH.setBounds(123, 102, 176, 24);
		panel.add(txtKH);

		JLabel lbNgayTao = new JLabel("Ngày Tạo : ");
		lbNgayTao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbNgayTao.setBounds(386, 41, 88, 24);
		panel.add(lbNgayTao);

		JLabel lbSDT = new JLabel("SDT : ");
		lbSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbSDT.setBounds(386, 107, 88, 24);
		panel.add(lbSDT);

		txtNgayTao = new JTextField();
		txtNgayTao.setColumns(10);
		txtNgayTao.setBounds(486, 43, 176, 24);
		panel.add(txtNgayTao);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(484, 110, 176, 24);
		panel.add(txtSDT);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 190, 763, 227);
		panel.add(scrollPane);

		table = new JTable();
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] { { "123", "123", "{\"123\"", null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null }, },
				new String[] { "STT", "T\u00EAn", "S\u1ED1 L\u01B0\u1EE3ng / Th\u1EDDi L\u01B0\u1EE3ng",
						"\u0110\u01A1n Gi\u00E1", "\u0110\u01A1n V\u1ECB", "Th\u00E0nh Ti\u1EC1n" }));
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblNgayNhan = new JLabel("Ngày nhận phòng: ");
		lblNgayNhan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgayNhan.setBounds(25, 156, 141, 24);
		panel.add(lblNgayNhan);

		JLabel lblThoiGianNhan = new JLabel("");
		lblThoiGianNhan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThoiGianNhan.setBounds(159, 156, 141, 24);
		panel.add(lblThoiGianNhan);
		TableColumn column1 = table.getColumnModel().getColumn(0);
		column1.setPreferredWidth(50); // Độ rộng
		TableColumn column2 = table.getColumnModel().getColumn(1);
		column2.setPreferredWidth(120); // Độ rộng
		TableColumn column3 = table.getColumnModel().getColumn(2);
		column3.setPreferredWidth(170); // Độ rộng
		TableColumn column4 = table.getColumnModel().getColumn(3);
		column4.setPreferredWidth(130); // Độ rộng
		JLabel lbTienNhan = new JLabel("Tiền Nhận : ");
		lbTienNhan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTienNhan.setBounds(10, 621, 101, 24);
		contentPane.add(lbTienNhan);

		txtTienNhan = new JTextField();
		txtTienNhan.setColumns(10);
		txtTienNhan.setBounds(94, 621, 176, 24);
		contentPane.add(txtTienNhan);

		JLabel lblTienThua = new JLabel("Tiền Thừa : ");
		lblTienThua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTienThua.setBounds(10, 677, 101, 24);
		contentPane.add(lblTienThua);

		txtTienThua = new JTextField();
		txtTienThua.setColumns(10);
		txtTienThua.setBounds(94, 677, 176, 24);
		contentPane.add(txtTienThua);

		JLabel lbTongTien = new JLabel("Tổng Tiền :");
		lbTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTongTien.setBounds(300, 621, 101, 24);
		contentPane.add(lbTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(388, 621, 144, 24);
		contentPane.add(txtTongTien);

		btnQuayLai = new JButton("Quay Lại");
		btnQuayLai.setIcon(
				new ImageIcon("D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-return-24.png"));
		btnQuayLai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnQuayLai.setBackground(new Color(69, 157, 122));
		btnQuayLai.setBounds(553, 681, 130, 35);
		contentPane.add(btnQuayLai);

		btnXuatFile = new JButton("Xuất file");
		btnXuatFile.setIcon(
				new ImageIcon("D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-export-24.png"));
		btnXuatFile.setBackground(new Color(255, 128, 128));
		btnXuatFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXuatFile.setBounds(693, 681, 125, 35);
		contentPane.add(btnXuatFile);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThanhToan.setBackground(new Color(255, 127, 80));
		btnThanhToan.setBounds(693, 625, 125, 35);
		contentPane.add(btnThanhToan);

		JLabel lblNewLabel = new JLabel("VNĐ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(542, 621, 56, 21);
		contentPane.add(lblNewLabel);
		table.getTableHeader().setBackground(new Color(255, 252, 237));
		model = (DefaultTableModel) table.getModel();
		CTHDDAO = new DAO_ChiTietHoaDon();
		CTDVDAO = new DAO_ChiTietDichVu();
		hoaDonDAO = new DAO_HoaDon();
		khachHangDAO = new DAO_KhachHang();
		nhanVienDAO = new DAO_NhanVien();
		phongDAO = new DAO_Phong();
		phieuDatDao = new DAO_PhieuDat();
		ctpdDVDao = new DAO_CTPDDichVu();
		ctpdPhongDao = new DAO_CTPDPhong();
		dichVuDao = new DAO_DichVu();
		khachHangThanhToan = khachHangDAO.timKhachHangTheoMa(maKH);

//		
		btnQuayLai.addActionListener(this);
		btnXuatFile.addActionListener(this);
		btnThanhToan.addActionListener(this);
		nhanVienHienTai = nv;

		txtTienNhan.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					// Nếu không phải số hoặc xóa, thì không cho nhập
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				tinhTienThua();

			}
		});
		dsPhieuDatDV = ctpdDVDao.getDSPhieuDatDVtheoMaPhieuDat(maPhieuDat);
		dsPhieuDatPhong = ctpdPhongDao.getDSphieuDatTheoMaPhieu(maPhieuDat);
		loadTinHoaDon();
		phieuDat = phieuDatDao.getPhieuDatTheoMaPhieu(maPhieuDat);
		loadDanhSachDichVuSuDung(dsPhieuDatDV, dsPhieuDatPhong, phieuDat);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnQuayLai)) {
			setVisible(false);
		} else if (o.equals(btnThanhToan)) {
			/**
			 * phải nhập tiền nhận khách hàng mới đc thanh toán
			 */
			if (txtTienNhan.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Bạn chưa nhập tiền!");
				txtTienNhan.requestFocus();
			} else {
				String maHD = txtMaHoaDon.getText();
				if (!taoHoaDon(phieuDat, dsPhieuDatDV, dsPhieuDatPhong)) {
					thongBaoLoi("Lỗi tạo hóa đơn", "Lỗi");
					return;
				}
				if (!capNhapTrangThaiPhong2(dsPhieuDatPhong)) {
					thongBaoLoi("Lỗi tạo cập nhập phòng", "Lỗi");
					return;
				}
				if (!capNhapTrangThaiPhieu(phieuDat.getMaPhieuDat())) {
					thongBaoLoi("Lỗi tạo cập nhập phiếu đặt", "Lỗi");
					return;
				}
				JOptionPane.showMessageDialog(null, "Xác Nhận Thanh Toán thanh công");
				inHoaDon();
				int y = JOptionPane.showConfirmDialog(null, "bạn có muốn xem file", "mở file",
						JOptionPane.YES_NO_OPTION);
				if (y == JOptionPane.YES_OPTION) {
					openFile(maHD);
					dispose();

				} else {
					dispose();

				}
			}
		}
//				String maHD = txtMaHoaDon.getText();
//				if (capNhapHoadon(maHD) && capNhapTrangThaiPhong(dsChiTietHD)) {
//					JOptionPane.showMessageDialog(null, "Xác Nhận Thanh Toán thanh công");
//					inHoaDon();
//					String mahd = txtMaHoaDon.getText();
//					int y = JOptionPane.showConfirmDialog(null, "bạn có muốn xem file", "mở file",
//							JOptionPane.YES_NO_OPTION);
//					if (y == JOptionPane.YES_OPTION) {
//						openFile(mahd);
//						dispose();
//
//					} else {
//						dispose();
//
//					}

	}

	public void xoaALLDuLieuTable() {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	int stt = 1;

	/**
	 * load ds thông tin dịch vụ phòng số lượng mà khách hàng sử dụng
	 * 
	 * @param dsPhieuDatDV
	 * @param dsPhieuDatPhong
	 * @param phieuDat
	 */
	public void loadDanhSachDichVuSuDung(ArrayList<ChiTietPhieuDatDichVu> dsPhieuDatDV,
			ArrayList<ChiTietPhieuDatPhong> dsPhieuDatPhong, PhieuDat phieuDat) {
		xoaALLDuLieuTable();
		double tongTien = 0;
		LocalDateTime now = LocalDateTime.now();
		int soPhutLech = (int) ChronoUnit.MINUTES.between(phieuDat.getNgayDat(), now);
		double soGio = (double) soPhutLech / 60;
		double sogio2 = Math.round(soGio * 100.0) / 100.0;
		for (ChiTietPhieuDatPhong chiTietPhieuDatPhong : dsPhieuDatPhong) {
			Phong phongDat = chiTietPhieuDatPhong.getPhong();
			double thanhTien = soGio * phongDat.getDonGia();
			tongTien += thanhTien;
			Object[] data = { stt, phongDat.getTenPhong(), sogio2, phongDat.getDonGia(), "Phòng", (long) thanhTien };
			model.addRow(data);
			stt++;
		}
		for (ChiTietPhieuDatDichVu chiTietPhieuDatDichVu : dsPhieuDatDV) {
			DichVu dvSuDung = dichVuDao.timDVTheoMaDV(chiTietPhieuDatDichVu.getMaDV());
			double thanhTien = dvSuDung.getDonGia() * chiTietPhieuDatDichVu.getSoLuong();
			tongTien += thanhTien;
			Object[] data = { stt, dvSuDung.getTenDichVu(), chiTietPhieuDatDichVu.getSoLuong(), dvSuDung.getDonGia(),
					dvSuDung.getDonVi(), thanhTien };
			model.addRow(data);
			stt++;
		}
		DecimalFormat decimalFormat = new DecimalFormat("###0");

		txtTongTien.setText(decimalFormat.format(tongTien));
	}

	public void loadTinHoaDon() {
		txtKH.setText(khachHangThanhToan.getTenKH());
		txtNV.setText(nhanVienHienTai.getTenNV());
		txtSDT.setText(khachHangThanhToan.getSdt());
		String maHD = taoMaHoaDon();
		txtMaHoaDon.setText(maHD);
		LocalDate now = LocalDate.now();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		txtNgayTao.setText(now.toString());
	}

	/**
	 * tính tiền thừa ccho khách hàng
	 */
	public void tinhTienThua() {

		String tNhan = txtTienNhan.getText();
		DecimalFormat df = new DecimalFormat("###,###,###");
		DecimalFormat decimalFormat = new DecimalFormat("###0");
		if (!tNhan.isEmpty()) {
			double tienNhan = Double.parseDouble(txtTienNhan.getText().trim() + "0");
			double tongTien = Double.parseDouble(txtTongTien.getText().trim());
			double tienThus = tienNhan - tongTien;

			txtTienThua.setText(decimalFormat.format(tienThus));
		} else {
			double tienNhan = Double.parseDouble(txtTienNhan.getText().trim() + "0");
			double tongTien = Double.parseDouble(txtTongTien.getText());
			double tienThus = tienNhan - tongTien;
			txtTienThua.setText(df.format(tienThus));
		}

	}

	/**
	 * chuyển từ phiếu đặt phong(không phải đặt phòng chờ) qua hóa đơn để lưu vào
	 * csdl
	 * 
	 * @param pd
	 * @param dsPdDV
	 * @param dsPDPhong
	 * @return
	 */
	public boolean taoHoaDon(PhieuDat pd, ArrayList<ChiTietPhieuDatDichVu> dsPdDV,
			ArrayList<ChiTietPhieuDatPhong> dsPDPhong) {
		String maHd = txtMaHoaDon.getText();
		LocalDateTime now = LocalDateTime.now();
		HoaDon hoaDonThanhToan = new HoaDon(maHd, pd.getNgayDat(), now, now, nhanVienHienTai.getMaNV(),
				khachHangThanhToan.getMaKH());
		if (!hoaDonDAO.themHoaDon(hoaDonThanhToan)) {
			thongBaoLoi("lỗi tạo hóa đơn", "Lỗi");
			return false;
		}
		for (ChiTietPhieuDatDichVu ctpdDV : dsPdDV) {
			DichVu dvSuDung = dichVuDao.timDVTheoMaDV(ctpdDV.getMaDV());
			ChiTietDichVu x = new ChiTietDichVu(hoaDonThanhToan, dvSuDung, ctpdDV.getSoLuong());
			if (!CTDVDAO.themChiTietDichVu(x)) {
				thongBaoLoi("lỗi tạo chi tiết dịch vụ", "Lỗi");
				return false;
			}
		}
		int soPhutLech = (int) ChronoUnit.MINUTES.between(pd.getNgayDat(), now);
		double soGio = (double) soPhutLech / 60;
//		double soGio2 = Math.round(soGio * 100.0) / 100.0;
		for (ChiTietPhieuDatPhong chiTietPhieuDatPhong : dsPDPhong) {
//			Phong phongSD = chiTietPhieuDatPhong.getPhong();
			ChiTietHoaDon x = new ChiTietHoaDon(hoaDonThanhToan, chiTietPhieuDatPhong.getPhong(), soPhutLech);
			if (!CTHDDAO.themChitietHoaDon(x)) {
				thongBaoLoi("lỗi tạo chi tiết Hóa Đơn", "Lỗi");
				return false;
			}
		}
		return true;
	}

//	public boolean capNhapTrangThaiPhong(ArrayList<ChiTietPhieuDatPhong> dsPhieuDat) {
//		ArrayList<Phong> dsPhongSD = new ArrayList<Phong>();
//		for (ChiTietPhieuDatPhong chiTietPhieuDatPhong : dsPhieuDat) {
//			dsPhongSD.add(chiTietPhieuDatPhong.getPhong());
//		}
//		for (Phong phong : dsPhongSD) {
//			String trangThai = "TRỐNG";
//			if (!phongDAO.capNhapTrangThaiPhong(trangThai, phong.getMaPhong())) {
//				return false;
//			}
//		}
//		return true;
//
//	}
	/**
	 * cập nhập trạng thái khi thanh toán nếu phòng chỉ ở trạng thái sd thì sẽ
	 * chuyển sang trống còn ở trạng thái chờ(sử dụng) thì chuyển snag trạng thái
	 * chờ
	 * 
	 * @param dsPhieuDat
	 * @return
	 */
	public boolean capNhapTrangThaiPhong2(ArrayList<ChiTietPhieuDatPhong> dsPhieuDat) {
		ArrayList<Phong> dsPhongSD = new ArrayList<Phong>();
		for (ChiTietPhieuDatPhong chiTietPhieuDatPhong : dsPhieuDat) {
			dsPhongSD.add(chiTietPhieuDatPhong.getPhong());
		}
		for (Phong phong : dsPhongSD) {
			String trangThai = "TRỐNG";
			if (phong.getTrangThai().equalsIgnoreCase("CHỜ(sử dụng)")) {
				if (!phongDAO.capNhapTrangThaiPhong("CHỜ", phong.getMaPhong())) {
					return false;
				}
			} else {
				if (!phongDAO.capNhapTrangThaiPhong(trangThai, phong.getMaPhong())) {
					return false;
				}
			}

		}
		return true;

	}

	/**
	 * cập nhập phiếu đặt sau khi thanh toán chuyển snag hết hiệu lực (nghĩa là
	 * khách hàng đã thanh toán )
	 * 
	 * @param maPhieu
	 * @return
	 */
	public boolean capNhapTrangThaiPhieu(String maPhieu) {
		if (!phieuDatDao.capNhapTrangThaiPhieuDat(maPhieu)) {
			return false;
		}
		return true;
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}
//	public void xuatHoaDon(String path) {
//
//		if (path.equals("") == false) {
//			path = "HoaDon: " + txtMaHoaDon.getText();
//		} else {
//			path = txtMaHoaDon.getText() + "\n";
//		}
//		String path2 = txtNV.getText() + "  " + txtNgayTao.getText() + "\n";
//		MessageFormat header = new MessageFormat(path);
//		MessageFormat content = new MessageFormat(path2);
//		MessageFormat footer = new MessageFormat("trang {0,number,integer}");
//		try {
//			table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
//		} catch (PrinterException e) {
//			e.printStackTrace();
//		}
//	}

	public String taoMaHoaDon() {
		int i = 0;
		ArrayList<HoaDon> dsHD = hoaDonDAO.getALLHoaDon();
		if (dsHD == null) {
			return String.format("HD%04d", i + 1);
		}
		while (i < dsHD.size()) {
			if (Integer.parseInt(dsHD.get(i).getMaHoaDon().substring(2)) == (i + 1))
				// nếu mã khớp thì tăng i lên để xét hàng tiếp theo.
				i++;
			else
				// nếu mã không khớp thì thoát vòng lập
				break;
		}
		return String.format("HD%04d", i + 1);
	}

	/**
	 * tạo file pdf để lưu thông tin hóa đơn của khách hàng sau thuận tiền cho việc
	 * kết nối máy tính để in ra hóa đơn
	 */
	public void inHoaDon() {
		try {
			// tao tai lieu in
			String urlFont = System.getProperty("user.dir") + "\\libs\\Arial Unicode MS.ttf";
			BaseFont unicodeFont = BaseFont.createFont(urlFont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			com.itextpdf.text.Font unicodeFontObject = new com.itextpdf.text.Font(unicodeFont, 12);
			Document document = new Document();
			document.setMargins(50, 50, 10, 0);
			// chon noi luu
			String url = "";
			url = System.getProperty("user.dir") + "\\src\\hoaDon\\";
			url += txtMaHoaDon.getText() + ".pdf";
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
			Paragraph hoaDonThanhToan = new Paragraph("HÓA ĐƠN THANH TOÁN",
					new com.itextpdf.text.Font(unicodeFont, 20, com.itextpdf.text.Font.BOLD));
			Paragraph dong = new Paragraph("********************", unicodeFontObject);
			hoaDonThanhToan.setAlignment(Element.ALIGN_CENTER);
			document.add(hoaDonThanhToan);
			dong.setAlignment(Element.ALIGN_CENTER);
			document.add(dong);

			// THÔNG TIN QUÁN VÀ THÔNG TIN KHÁCH HÀNH NHÂN VIÊN
			String ngayTao = txtNgayTao.getText();
			Paragraph ngay = new Paragraph(ngayTao, unicodeFontObject);
			ngay.setAlignment(Element.ALIGN_RIGHT);
			document.add(ngay);
			String nhanVien = "Nhân Viên: ..." + txtNV.getText();
			String kh = "Khách Hàng: ..." + txtKH.getText();
			Paragraph nv = new Paragraph(nhanVien, unicodeFontObject);
			nv.setAlignment(Element.ALIGN_LEFT);
			Paragraph kh1 = new Paragraph(kh, unicodeFontObject);
			kh1.setAlignment(Element.ALIGN_LEFT);
			document.add(nv);
			document.add(kh1);
			document.add(Chunk.NEWLINE);
			Paragraph dong2 = new Paragraph("Thông Tin Hóa Dơn*",
					new com.itextpdf.text.Font(unicodeFont, 10, com.itextpdf.text.Font.BOLD));
			dong2.setAlignment(Element.ALIGN_CENTER);
			document.add(dong2);
			document.add(Chunk.NEWLINE);

			// tạo bảng
			PdfPTable table = new PdfPTable(5);
			table.setTotalWidth(new float[] { 70f, 100f, 60f, 50f, 70f });
			table.setWidthPercentage(100);
			// Thêm tiêu đề cho bảng
			table.addCell(new PdfPCell(new Phrase("Tên", unicodeFontObject)));
			table.addCell(new PdfPCell(new Phrase("Số lượng / thời lượng (p)", unicodeFontObject)));
			table.addCell(new PdfPCell(new Phrase("Đơn Giá", unicodeFontObject)));
			table.addCell(new PdfPCell(new Phrase("Đơn vị", unicodeFontObject)));
			table.addCell(new PdfPCell(new Phrase("Thành tiền", unicodeFontObject)));
			// Thêm dữ liệu
			for (int i = 0; i < model.getRowCount(); i++) {
				String ten1 = model.getValueAt(i, 1).toString();
				String soluong = model.getValueAt(i, 2).toString();
				String dongia = model.getValueAt(i, 3).toString();
				String donvi = model.getValueAt(i, 4).toString();
				String thanhTien = model.getValueAt(i, 5).toString();
				table.addCell(new PdfPCell(new Paragraph(ten1, unicodeFontObject)));
				table.addCell(new PdfPCell(new Paragraph(soluong, unicodeFontObject)));
				table.addCell(new PdfPCell(new Paragraph(dongia, unicodeFontObject)));
				table.addCell(new PdfPCell(new Paragraph(donvi, unicodeFontObject)));
				table.addCell(new PdfPCell(new Paragraph(thanhTien, unicodeFontObject)));
			}
			for (PdfPRow row : table.getRows()) {
				for (PdfPCell cell : row.getCells()) {
					cell.setBorder(Rectangle.NO_BORDER);
				}
			}
			for (PdfPRow row : table.getRows()) {
				for (PdfPCell cell : row.getCells()) {
					cell.setBorder(Rectangle.BOTTOM);
				}
			}

			document.add(table);
			document.add(Chunk.NEWLINE);
			String tongTien = "Tổng Tiền: " + txtTongTien.getText();
			String tienNhan = "Tiền Nhận: " + txtTienNhan.getText();
			String tienThua = "Tiền Thừa: " + txtTienThua.getText();

			Paragraph TongTien = new Paragraph(tongTien, unicodeFontObject);
			TongTien.setAlignment(Element.ALIGN_RIGHT);
			document.add(TongTien);
//			document.add(Chunk.NEWLINE);
			Paragraph TNhan = new Paragraph(tienNhan, unicodeFontObject);
			TNhan.setAlignment(Element.ALIGN_RIGHT);
			document.add(TNhan);
//			document.add(Chunk.NEWLINE);
			Paragraph TThua = new Paragraph(tienThua, unicodeFontObject);
			TThua.setAlignment(Element.ALIGN_RIGHT);
			document.add(TThua);

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
	/**
	 * xem file pdf hóa đơn theo mã hóa đơn
	 * @param maHD
	 */
	public void openFile(String maHD) {
		String url = "";
		url = System.getProperty("user.dir") + "\\src\\hoaDon\\" + maHD + ".pdf";
		File file = new File(url);
		Desktop dt = Desktop.getDesktop();
		try {
			dt.open(file);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
