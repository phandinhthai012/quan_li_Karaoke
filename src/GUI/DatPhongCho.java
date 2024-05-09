package GUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

import DAO.DAO_CTPhieuDatPhongCho;
import DAO.DAO_KhachHang;
import DAO.DAO_NhanVien;
import DAO.DAO_PhieuDatPhongCho;
import DAO.DAO_Phong;
import entity.ChiTietPhieuDatPhongCho;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhongCho;
import java.awt.Toolkit;

public class DatPhongCho extends JDialog implements ActionListener {

	private JButton btnDatPhong;
	private JButton btnHuy;
	private JTextField tfSDTKhach;

	private JLabel lblTenKhach, lblMaPhieu;
	private JLabel lblIconKiemTraSDT;
	private JButton btnKiemTraSDTKKhach;
	private JComboBox<String> cbPhut;
	private JComboBox<String> cbGio;
	private JRadioButton radioNgayMai;
	private JRadioButton radioHomNay;
	boolean trangThai = false;
	private DefaultComboBoxModel<String> gioModel;

	private DefaultComboBoxModel phutModel;
	private JCheckBox cbInPhieuDat;
	private DAO_PhieuDatPhongCho PDPCDao;
	private final JPanel contentPanel = new JPanel();

	private DAO_KhachHang khachHangDAO;
	private DAO_PhieuDatPhongCho pdPhongChoDAO;
	private DAO_CTPhieuDatPhongCho CTPDPhongChoDAO;
	private DAO_Phong phongDAO;
	private DAO_NhanVien nvDAO;
	private ArrayList<ChiTietPhieuDatPhongCho> dsCTPD;
	private NhanVien nv;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			DatPhongCho dialog = new DatPhongCho();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public DatPhongCho(ArrayList<ChiTietPhieuDatPhongCho> dsCTPD, NhanVien nvHienTai) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatPhongCho.class.getResource("/img/logo-Nice-karaoke.jpg")));
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 744, 59);
		setSize(758, 510);
		getContentPane().add(panel);
		setLocationRelativeTo(null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u0110\u1EB7t ph\u00F2ng ch\u1EDD");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(140, 0, 476, 59);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ngày nhận phòng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(33, 273, 157, 35);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Giờ nhận phòng");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(33, 318, 157, 35);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Giờ");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(264, 318, 57, 35);
		getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Phút");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1.setBounds(383, 318, 55, 35);
		getContentPane().add(lblNewLabel_1_1_1_1);

		cbGio = new JComboBox<String>();
		cbGio.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbGio.setModel(gioModel = new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
		cbGio.setBounds(194, 318, 65, 35);
		getContentPane().add(cbGio);

		cbPhut = new JComboBox<String>();
		cbPhut.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbPhut.setModel(phutModel = new DefaultComboBoxModel(
				new String[] { "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
		cbPhut.setBounds(306, 318, 65, 35);
		getContentPane().add(cbPhut);

		btnDatPhong = new JButton("Đặt phòng");
		btnDatPhong.setBackground(new Color(65, 105, 225));
		btnDatPhong.setForeground(new Color(255, 255, 255));
		btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDatPhong.setBounds(313, 376, 135, 35);
		getContentPane().add(btnDatPhong);

		btnHuy = new JButton("Hủy");
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHuy.setBackground(new Color(128, 128, 128));
		btnHuy.setBounds(32, 376, 78, 35);
		getContentPane().add(btnHuy);

		cbInPhieuDat = new JCheckBox("In phiếu đặt");
		cbInPhieuDat.setSelected(true);
		cbInPhieuDat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbInPhieuDat.setBounds(199, 381, 106, 21);
		getContentPane().add(cbInPhieuDat);

		tfSDTKhach = new JTextField();
		tfSDTKhach.setColumns(10);
		tfSDTKhach.setBounds(194, 191, 150, 35);
		getContentPane().add(tfSDTKhach);

		lblIconKiemTraSDT = new JLabel("");
		lblIconKiemTraSDT.setBounds(177, 128, 22, 33);
		getContentPane().add(lblIconKiemTraSDT);

		JLabel lblStKhach = new JLabel("SĐT Khách");
		lblStKhach.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStKhach.setBounds(33, 188, 157, 35);
		getContentPane().add(lblStKhach);

		btnKiemTraSDTKKhach = new JButton("Kiểm tra");
		btnKiemTraSDTKKhach.setBounds(381, 191, 90, 35);
		getContentPane().add(btnKiemTraSDTKKhach);

		lblTenKhach = new JLabel("");
		lblTenKhach.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenKhach.setBounds(197, 234, 220, 35);
		getContentPane().add(lblTenKhach);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel("Tên Khách");
		lblNewLabel_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(33, 234, 157, 35);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1 = new JLabel("Số Phòng:");
		lblNewLabel_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_1_1_1_1_1.setBounds(33, 128, 157, 35);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_1);

		radioHomNay = new JRadioButton("Hôm nay");
		radioHomNay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioHomNay.setBounds(190, 278, 103, 21);
		getContentPane().add(radioHomNay);

		radioNgayMai = new JRadioButton("Ngày mai");
		radioNgayMai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioNgayMai.setBounds(333, 278, 103, 21);
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioNgayMai);
		bg.add(radioHomNay);
		getContentPane().add(radioNgayMai);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1 = new JLabel("Mã phiếu: ");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_1_1_1_1_1_1.setBounds(33, 83, 157, 35);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_1_1);

		lblMaPhieu = new JLabel("PDP0001");
		lblMaPhieu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaPhieu.setBounds(214, 83, 157, 35);
		getContentPane().add(lblMaPhieu);

		PDPCDao = new DAO_PhieuDatPhongCho();
		pdPhongChoDAO = new DAO_PhieuDatPhongCho();
		khachHangDAO = new DAO_KhachHang();
		nvDAO = new DAO_NhanVien();
		CTPDPhongChoDAO = new DAO_CTPhieuDatPhongCho();
		phongDAO = new DAO_Phong();

		this.dsCTPD = dsCTPD;
		this.nv = nvHienTai;
		btnKiemTraSDTKKhach.addActionListener(this);
		radioHomNay.addActionListener(this);
		radioNgayMai.addActionListener(this);
		btnDatPhong.addActionListener(this);
		btnHuy.addActionListener(this);
		cbInPhieuDat.addActionListener(this);
		String phieuDat = taoMaPhieuDat();
		lblMaPhieu.setText(phieuDat);
		int size = dsCTPD.size();
		JLabel lblSoPhong = new JLabel(String.valueOf(size) + " Phòng");
		lblSoPhong.setBounds(194, 131, 157, 30);
		getContentPane().add(lblSoPhong);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnKiemTraSDTKKhach)) {
			KhachHang x = kiemTraKhachHang();
		} else if (o.equals(btnHuy)) {
			setVisible(false);
			dispose();
		}
		if (o.equals(radioHomNay) || o.equals(radioNgayMai)) {
			thietLapGio();
		} else if (o.equals(btnDatPhong)) {
			
			boolean xacnhan = taoPhieuDatPhongCho();
			if (xacnhan) {
				JOptionPane.showMessageDialog(this, "Đặt phòng thành công");
				setVisible(false);
				dispose();
				String maPhieu = lblMaPhieu.getText();
				xuatFile(lblMaPhieu.getText());
				if(cbInPhieuDat.isSelected()) {
					int xacNhan1= JOptionPane.showConfirmDialog(this, "Bạn có muốn xem phiếu đặt phòng", "Phiếu đặt", JOptionPane.YES_NO_OPTION);
					if(xacNhan1==JOptionPane.YES_OPTION) {
						openFile(maPhieu);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Đặt phòng KHÔNG thành công");
				setVisible(false);
				dispose();
			}
		}
	}

	/**
	 * thiết lập giờ để cho phép khách hàng đặt phòng trước 2 tiếng so với thời gian hiện tại hoặc đặt vào ngày mai
	 */
	public void thietLapGio() {
		int gio = 1;
		int phut = 0;
		if (radioHomNay.isSelected()) {
			Date date = new Date();
			gio = date.getHours();
			phut = date.getMinutes() % 5 == 0 ? date.getMinutes() : ((date.getMinutes() / 5) * 5) + 5;// làm tròn giá
																										// trị phút
			if (phut == 60) {
				gio += 1;
				phut = 5;
			}
			gio+=2;
		}
		if (gio < 8) {
			gio = 8;
		}
		gioModel.removeAllElements();
		phutModel.removeAllElements();
		for (int i = gio; i < 23; i++) {
			gioModel.addElement(i + "");
		}
		for (int i = 0; i < 60; i += 5) {
			phutModel.addElement(i + "");
		}
	}
	/**
	 * hàm tạo phiếu đặt để lưu vào csdl và kiểm tra xem có lưu được không
	 * @return
	 */
	public boolean taoPhieuDatPhongCho() {
		KhachHang x = khachHangDAO.timKhachHangTheoSDT(tfSDTKhach.getText());
		int gio = Integer.parseInt(cbGio.getSelectedItem().toString());
		int phut = Integer.parseInt(cbPhut.getSelectedItem().toString());
		Date date = new Date();
		if (radioHomNay.isSelected()) {
			// ktra thời gian nhập có trước hiện tại hay không
			if (gio < date.getDay() || (gio == date.getHours() && phut < date.getMinutes())) {
				JOptionPane.showMessageDialog(this, "Thời gian phải trước thời gian hiện tại");
				return false;
			}
		} else {
			// add one day
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			date = c.getTime();
		}
		date.setMinutes(phut);
		date.setHours(gio);
		LocalDateTime ngayNhan = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		ngayNhan = ngayNhan.withHour(gio).withMinute(phut);
		if (x == null)
			return false;
		int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn đặt phòng chờ không ", "Thông báo",
				JOptionPane.YES_NO_OPTION);
		if (xacNhan == JOptionPane.YES_OPTION) {
			String maPhieu = taoMaPhieuDat();
			KhachHang Kh = khachHangDAO.timKhachHangTheoSDT(tfSDTKhach.getText());
			LocalDateTime ngayTao = LocalDateTime.now();
			PhieuDatPhongCho pd = new PhieuDatPhongCho(maPhieu, Kh.getMaKH(), nv.getMaNV(), ngayTao, ngayNhan, false);
			if (!pdPhongChoDAO.themPhieuDatPhongCho(pd)) {
				return false;
			}
			for (ChiTietPhieuDatPhongCho chiTietPhieuDatPhongCho : dsCTPD) {
				if (!CTPDPhongChoDAO.themCTPhieuDatPhongCho(chiTietPhieuDatPhongCho)) {
					thongBaoLoi("Lỗi tạo Chi tiết phiếu đặt phong", "lỗi");
					return false;
				}
				String maPhong = chiTietPhieuDatPhongCho.getMaPhong();
				String trangThaiPhong = "CHỜ";
				if (!phongDAO.capNhapTrangThaiPhong(trangThaiPhong, maPhong)) {
					thongBaoLoi("lỗi cập nhập trang thái phòng", "lỗi");
					return false;
				}
			}
		}
		else {
			return false;
		}

		return true;
	}
	/**
	 * hàm dùng để kiếm tra khách hàng có tồn tại hay không 
	 * 
	 * @return có thì khanchs hàng != null không có thì kh ==null
	 */
	public KhachHang kiemTraKhachHang() {
		String sdt = tfSDTKhach.getText();
		if (sdt.trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại Khách");
			tfSDTKhach.selectAll();
			tfSDTKhach.requestFocus();
			return null;
		}
		if (!sdt.matches("^0[1-9][0-9]{8}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không đúng địng dạng");
			tfSDTKhach.selectAll();
			tfSDTKhach.requestFocus();
			return null;
		}
		KhachHang x = khachHangDAO.timKhachHangTheoSDT(sdt);

		if (x == null) {
			int xacNhan = JOptionPane.showConfirmDialog(this,
					"Khách hàng không có trong hệ thống, Bạn có muốn thêm khách hàng không", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (xacNhan == JOptionPane.YES_OPTION) {
				String maKh = taoMaKH();
				dialogThemKhachHang dialogThemKhachHang = new dialogThemKhachHang(maKh);
				dialogThemKhachHang.setVisible(true);
				if (!dialogThemKhachHang.isShowing()) {
					x = khachHangDAO.timKhachHangTheoSDT(sdt);
					lblTenKhach.setText(x.getTenKH());
				}
			}
		} else {
			lblTenKhach.setText(x.getTenKH());
		}
		return x;
	}
	/**
	 * tạo mã khách hàng nếu khách hàng không có trông hệ thống
	 * @return
	 */
	public String taoMaKH() {
		int i = 0;
		ArrayList<KhachHang> ds = khachHangDAO.getALLKhachHang();
		if (ds == null) {
			return String.format("KH%03d", i + 1);
		}
		while (i < ds.size()) {
			if (Integer.parseInt(ds.get(i).getMaKH().substring(2)) == (i + 1)) {
				i++;
			} else {
				break;
			}
		}
		return String.format("KH%03d", i + 1);
	}
	/**
	 * tạo mã phiếu đặt phòng chờ
	 * @return
	 */
	public String taoMaPhieuDat() {
		int i = 0;
		ArrayList<PhieuDatPhongCho> ds = PDPCDao.getAllphieuDatPhongCho();
		if (ds == null) {
			return String.format("DP%04d", i + 1);
		}
		while (i < ds.size()) {
			if (Integer.parseInt(ds.get(i).getMaPhieuDatPhongCho().substring(2)) == (i + 1)) {
				i++;

			} else {
				break;
			}
		}
		return String.format("DP%04d", i + 1);
	}
	/**
	 * lưu thông tin phiếu đặt dưới file pdf để sau có thể thực hiện chức năng in
	 * @param maPhieuDat
	 */
	public void xuatFile(String maPhieuDat) {
		PhieuDatPhongCho pd = pdPhongChoDAO.getPhieuDat(maPhieuDat);

		try {
			// tao tai lieu in
			String urlFont = System.getProperty("user.dir") + "\\libs\\Arial Unicode MS.ttf";
			BaseFont unicodeFont = BaseFont.createFont(urlFont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			com.itextpdf.text.Font unicodeFontObject = new com.itextpdf.text.Font(unicodeFont, 12);
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
			String ngayTao = "Ngày Lập Phiếu:		" + pd.getNgayDangKi().format(formatter);
			String ngayNhan = "Ngày Nhận Phong:		" + pd.getNgayNhanPhong().format(formatter);
			String phieuDat = "Mã phiếu đặt:		" + maPhieuDat;
			Paragraph maPhieu = new Paragraph(phieuDat, unicodeFontObject);
			maPhieu.setAlignment(Element.ALIGN_LEFT);
			document.add(maPhieu);
			Paragraph ngayTao1 = new Paragraph(ngayTao, unicodeFontObject);
			ngayTao1.setAlignment(Element.ALIGN_LEFT);
			document.add(ngayTao1);
			Paragraph ngayNhan1 = new Paragraph(ngayNhan, unicodeFontObject);
			ngayNhan1.setAlignment(Element.ALIGN_LEFT);
			document.add(ngayNhan1);
			NhanVien nv = nvDAO.timNhanVienTheoMaNV(pd.getMaNhanVien());
			KhachHang kh = khachHangDAO.timKhachHangTheoMa(pd.getMaKhachHang());
			String nhanVien = "Nhân Viên:		" + nv.getTenNV();
			String kh1 = "Khách Hàng:		" + kh.getTenKH();
			Paragraph nv1 = new Paragraph(nhanVien, unicodeFontObject);
			nv1.setAlignment(Element.ALIGN_LEFT);
			Paragraph kh2 = new Paragraph(kh1, unicodeFontObject);
			kh2.setAlignment(Element.ALIGN_LEFT);
			String sdt = "số diện thoai:\t" + kh.getSdt();
			Paragraph sdtKH = new Paragraph(sdt, unicodeFontObject);
			sdtKH.setAlignment(Element.ALIGN_LEFT);
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
	/**
	 * mở file phiếu dtawjd
	 * @param maPhieuDat
	 */
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

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}
}
