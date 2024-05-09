package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.SystemColor;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAO.DAO_CTPDPhong;
import DAO.DAO_CTPhieuDatPhongCho;
import DAO.DAO_ChiTietHoaDon;
import DAO.DAO_HoaDon;
import DAO.DAO_KhachHang;
import DAO.DAO_LoaiPhong;
import DAO.DAO_NhanVien;
import DAO.DAO_PhieuDat;
import DAO.DAO_PhieuDatPhongCho;
import DAO.DAO_Phong;
import connectDB.connectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatPhong;
import entity.ChiTietPhieuDatPhongCho;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDat;
import entity.PhieuDatPhongCho;
import entity.Phong;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.TextArea;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.Timer;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class QLDatPhong extends JPanel implements ActionListener, ItemListener {
	private DefaultTableModel model;
	private JTextField txtMaKhachHang;
	private JTextField txtSDT;
	private JTextField txtGioVaoPhong;
	private JTextField txtGioRa;
	private JTextField txtSoNguoi;
	private JTextField txtGio;
	private JTextField txtPhut;
	private JTable table;
	private JLabel lb_9;
	private DAO_Phong phongDAO;
	private ArrayList<Phong> dsPhong;
	private JScrollPane scrollPane_1;
	private JPanel pnlDanhSachPhong;
	private JTextArea txaThongTinPhong, textArea_1;
	private JButton btnChonPhong, btnThanhToan, btnDatDichVu, btnXoaChonPhong, btnDatPhongCho, btnNhanPhong;
	private JTextField txtMaPhong;
	private JButton btnDatPhong;
	private JButton btnKtraKH;
	private JDateChooser time_VaoPhong, time_RaDuKien;
	private DAO_NhanVien nhanVienDAO;
	private DAO_KhachHang khachHangDAO;
	private DAO_HoaDon hoaDonDAO;
	private DAO_ChiTietHoaDon ctHDDAO;
	private DAO_PhieuDat phieuDatDAO;
	private DAO_CTPDPhong CTPDPhongDao;
	private DAO_PhieuDatPhongCho PDPCDao;
	private DAO_CTPhieuDatPhongCho CTPDPhongChoDAO;
	private String maNVTaoHD;
	private NhanVien nvHienTai;
	private JRadioButton rdbtnThoiGianHienTai;
	private DefaultTableModel model1, model2;

	/**
	 * Create the panel.
	 */
	public QLDatPhong(NhanVien nv) {
		setBackground(Color.WHITE);
		try {
			connectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		setLayout(null);
		setBounds(0, 0, 1540, 760);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 1920, 732);
		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 1541, 223);
		panel.add(panel_1);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBackground(SystemColor.inactiveCaptionBorder);
		panel_3.setBounds(10, 10, 305, 203);
		panel_1.add(panel_3);

		JLabel lblNewLabel = new JLabel("Thông tin khách đặt");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 10, 160, 34);
		panel_3.add(lblNewLabel);

		JLabel lblNewLabel_1_1 = new JLabel("Mã khách hàng:");
		lblNewLabel_1_1.setBounds(10, 38, 126, 22);
		panel_3.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_3 = new JLabel("SĐT khách:");
		lblNewLabel_1_3.setBounds(10, 70, 72, 22);
		panel_3.add(lblNewLabel_1_3);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(99, 40, 168, 19);
		panel_3.add(txtMaKhachHang);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(99, 70, 168, 19);
		panel_3.add(txtSDT);

		btnKtraKH = new JButton("Kiểm tra KH");
		btnKtraKH.setBackground(Color.LIGHT_GRAY);
		btnKtraKH.setBounds(10, 129, 126, 21);
		panel_3.add(btnKtraKH);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u1EDDi gian \u0111\u1EB7t ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_8.setBackground(SystemColor.inactiveCaptionBorder);
		panel_8.setBounds(325, 10, 581, 203);
		panel_1.add(panel_8);

		JLabel lblNewLabel_1_1_1 = new JLabel("Giờ vào phòng:");
		lblNewLabel_1_1_1.setBounds(10, 60, 103, 22);
		panel_8.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Giờ ra dự kiến:");
		lblNewLabel_1_1_1_1.setBounds(10, 113, 103, 22);
		panel_8.add(lblNewLabel_1_1_1_1);

//		txtGioVaoPhong = new JTextField();
//		
//		txtGioVaoPhong.setColumns(10);
//		txtGioVaoPhong.setBounds(123, 62, 103, 19);
//		panel_8.add(txtGioVaoPhong);

		time_VaoPhong = new JDateChooser();
		time_VaoPhong.setBackground(SystemColor.inactiveCaption);
		time_VaoPhong.getCalendarButton()
				.setIcon(new ImageIcon(QLDatPhong.class.getResource("/img/icons8-calendar-50.png")));
		time_VaoPhong.getCalendarButton().setFont(new Font("UTM Aurora", Font.BOLD, 20));
		time_VaoPhong.setLocale(new Locale("vi", "VN"));
		time_VaoPhong.setFont(new Font("Tahoma", Font.PLAIN, 10));
		time_VaoPhong.setDateFormatString("HH:mm || dd-MM-yyyy");
		time_VaoPhong.setBounds(108, 62, 150, 19);
		panel_8.add(time_VaoPhong);

//		txtGioRa = new JTextField();
//		txtGioRa.setColumns(10);
//		txtGioRa.setBounds(123, 115, 103, 19);

		time_RaDuKien = new JDateChooser();

		time_RaDuKien.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if (time_VaoPhong.getDate() != null && time_RaDuKien.getDate() != null) {
					LocalDateTime timeVao = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					LocalDateTime timeRa = time_RaDuKien.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					long soPhutLech = ChronoUnit.MINUTES.between(timeVao, timeRa);
					if (soPhutLech > 0) {
						int soGio = (int) (soPhutLech / 60);
						int soPhut = (int) (soPhutLech % 60);
						txtGio.setText(soGio + "");
						txtPhut.setText(soPhut + "");
						return;
					}
					if (soPhutLech < 0) {
						txtGio.setText("0");
						txtPhut.setText("0");
						time_RaDuKien.setCalendar(null);
						return;
					} else {
						txtGio.setText("0");
						txtPhut.setText("0");
						return;
					}
				}
			}
		});
		time_RaDuKien.getCalendarButton()
				.setIcon(new ImageIcon(QLDatPhong.class.getResource("/img/icons8-calendar-50.png")));
		time_RaDuKien.getCalendarButton().setFont(new Font("UTM Aurora", Font.BOLD, 20));
		time_RaDuKien.setLocale(new Locale("vi", "VN"));
		time_RaDuKien.setFont(new Font("Tahoma", Font.PLAIN, 10));
		time_RaDuKien.setDateFormatString("HH:mm dd-MM-yyyy");
		time_RaDuKien.setBounds(108, 115, 150, 19);
		panel_8.add(time_RaDuKien);
//		panel_8.add(txtGioRa);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Số người:");
		lblNewLabel_1_1_1_1_1.setBounds(10, 150, 103, 22);
		panel_8.add(lblNewLabel_1_1_1_1_1);

		txtSoNguoi = new JTextField();
		txtSoNguoi.setColumns(10);
		txtSoNguoi.setBounds(108, 145, 41, 33);
		panel_8.add(txtSoNguoi);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("người");
		lblNewLabel_1_1_1_1_1_1.setBounds(159, 150, 55, 22);
		panel_8.add(lblNewLabel_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Thời gian sử dụng:");
		lblNewLabel_1_1_1_1_1_2.setBounds(268, 60, 118, 22);
		panel_8.add(lblNewLabel_1_1_1_1_1_2);

		txtGio = new JTextField();
		txtGio.setColumns(10);
		txtGio.setBounds(396, 53, 41, 33);
		panel_8.add(txtGio);
		txtGio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE)
					e.consume();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtGio.getText().equals("")) {
					int soGioNhap = Integer.parseInt(txtGio.getText());
					LocalDateTime thoiGianHienTai = LocalDateTime.now();
//					LocalDateTime ngayMai = LocalDateTime.of(thoiGianHienTai.getYear(),thoiGianHienTai.getMonthValue(),thoiGianHienTai.getDayOfMonth()+1,0,0,0);
					String sophutTxt = txtPhut.getText();
					int soPhutNhap = 0;
					if (!sophutTxt.isEmpty()) {
						soPhutNhap = Integer.parseInt(txtPhut.getText().trim());
					}

					int soPhut = soGioNhap * 60 + soPhutNhap;
					LocalDateTime thoiGianVao = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					LocalDateTime thoiGianRa = thoiGianVao.plusMinutes(soPhut);
					Date thoiGianRaDate = Date.from(thoiGianRa.atZone(ZoneId.systemDefault()).toInstant());
					time_RaDuKien.setDate(thoiGianRaDate);
				}
			}

		});

		txtPhut = new JTextField();
		txtPhut.setColumns(10);
		txtPhut.setBounds(481, 53, 41, 33);
		panel_8.add(txtPhut);
		txtPhut.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE)
					e.consume();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (!txtPhut.getText().equals("0")) {
					int soPhutNhap = Integer.parseInt(txtGio.getText().trim());
					if (soPhutNhap > 59 || soPhutNhap < 0) {
						thongBaoLoi("Phút nhập phải từ 0-59", "Lỗi nhập thời gian");
						txtPhut.setText("0");
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtPhut.getText().equals("0") && !txtPhut.getText().equals("")) {
					int soGioNhap = Integer.parseInt(txtGio.getText().trim());
					String sophutTxt = txtPhut.getText();
					int soPhutNhap = 0;
					if (!sophutTxt.isEmpty()) {
						soPhutNhap = Integer.parseInt(txtPhut.getText().trim());
					}
//					int soPhutNhap = Integer.parseInt(txtPhut.getText().trim());
					int soPhut = soGioNhap * 60 + soPhutNhap;
					LocalDateTime timeVao = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					LocalDateTime timeRa = timeVao.plusMinutes(soPhut);
					Date timeRaDate = Date.from(timeRa.atZone(ZoneId.systemDefault()).toInstant());
					time_RaDuKien.setDate(timeRaDate);

					LocalDateTime thoiGianHienTai = LocalDateTime.now();
//					LocalDateTime ngayMai = LocalDateTime.of(thoiGianHienTai.getYear(), thoiGianHienTai.getMonthValue(),
//							thoiGianHienTai.getDayOfMonth() + 1, 0, 0, 0);
					time_RaDuKien.setDate(timeRaDate);
				}
			}
		});

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("giờ");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(447, 59, 55, 22);
		panel_8.add(lblNewLabel_1_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1_2 = new JLabel("phút");
		lblNewLabel_1_1_1_1_1_1_2.setBounds(532, 59, 55, 22);
		panel_8.add(lblNewLabel_1_1_1_1_1_1_2);

		lb_9 = new JLabel();
		lb_9.setBounds(308, 85, 173, 76);
		panel_8.add(lb_9);

		rdbtnThoiGianHienTai = new JRadioButton("Thời gian hiện tại");
		rdbtnThoiGianHienTai.setBackground(new Color(255, 255, 255));
		rdbtnThoiGianHienTai.setBounds(108, 86, 150, 21);
		panel_8.add(rdbtnThoiGianHienTai);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.inactiveCaptionBorder);
		panel_4.setBorder(new TitledBorder(null, "Danh s\u00E1ch kh\u00E1ch h\u00E0ng ", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_4.setBounds(916, 10, 598, 203);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 22, 578, 171);
		panel_4.add(scrollPane_2);

		tblDSKhachHangDat = new JTable();
		tblDSKhachHangDat.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, },
				new String[] { "M\u00E3 sử dụng", "M\u00E3 Kh\u00E1ch h\u00E0ng", "T\u00EAn kh\u00E1ch h\u00E0ng",
						"S\u1ED1 \u0111i\u1EC7n thoai", "Ng\u00E0y \u0111\u1EB7t", "Ph\u00F2ng" }));
		scrollPane_2.setViewportView(tblDSKhachHangDat);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBackground(SystemColor.inactiveCaptionBorder);
		panel_6.setBounds(706, 233, 824, 488);
		panel.add(panel_6);

		btnChonPhong = new JButton("Chọn phòng");
		btnChonPhong.setIcon(new ImageIcon(QLDatPhong.class.getResource("/img/8423740ac0dd17834ecc.jpg")));
		btnChonPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChonPhong.setBackground(Color.WHITE);
		btnChonPhong.setBounds(284, 192, 178, 57);
		panel_6.add(btnChonPhong);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBackground(Color.WHITE);
		panel_7.setBounds(402, 10, 412, 172);
		panel_6.add(panel_7);

		JLabel lblNewLabel_6 = new JLabel("Danh sách phòng đã chọn:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_6.setBounds(10, 10, 184, 13);
		panel_7.add(lblNewLabel_6);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 392, 126);
		panel_7.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "STT", "T\u00EAn Ph\u00F2ng",
				"M\u00E3 Ph\u00F2ng", "Tr\u1EA1ng Th\u00E1i", "Gi\u00E1 Ti\u1EC1n M\u1ED7i Gi\u1EDD" }));

		btnDatPhong = new JButton("Đặt phòng");
		btnDatPhong.setIcon(new ImageIcon(QLDatPhong.class.getResource("/img/icons8-reservation-30.png")));
		btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDatPhong.setBackground(new Color(104, 215, 148));
		btnDatPhong.setBounds(42, 434, 216, 44);
		panel_6.add(btnDatPhong);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 10, 382, 172);
		panel_6.add(panel_2);

		txaThongTinPhong = new JTextArea();
		txaThongTinPhong.setEditable(false);
		txaThongTinPhong.setLineWrap(true);
		txaThongTinPhong.setBounds(10, 28, 362, 115);
		panel_2.add(txaThongTinPhong);

		JLabel lblNewLabel_5 = new JLabel("Thông tin phòng");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_5.setBounds(10, 0, 126, 31);
		panel_2.add(lblNewLabel_5);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setIcon(new ImageIcon(QLDatPhong.class.getResource("/img/icons8-receipt-30.png")));
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThanhToan.setBackground(new Color(195, 158, 8));
		btnThanhToan.setBounds(619, 405, 178, 38);
		panel_6.add(btnThanhToan);

		txtMaPhong = new JTextField();
		txtMaPhong.setEditable(false);
		txtMaPhong.setEnabled(false);
		txtMaPhong.setBounds(10, 180, 118, 19);
		panel_6.add(txtMaPhong);
		txtMaPhong.setColumns(10);

		btnDatDichVu = new JButton("Đặt dịch vụ");
		btnDatDichVu.setIcon(new ImageIcon(QLDatPhong.class.getResource("/img/icons8-beer-30.png")));
		btnDatDichVu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDatDichVu.setBackground(new Color(104, 215, 148));
		btnDatDichVu.setBounds(349, 367, 216, 44);
		panel_6.add(btnDatDichVu);

		btnDatPhongCho = new JButton("Đặt phòng chờ");
		btnDatPhongCho.setIcon(new ImageIcon(QLDatPhong.class.getResource("/img/icons8-delivery-time-30.png")));
		btnDatPhongCho.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDatPhongCho.setBackground(new Color(104, 215, 148));
		btnDatPhongCho.setBounds(42, 367, 216, 44);
		panel_6.add(btnDatPhongCho);

		btnXoaChonPhong = new JButton("Xóa chọn phòng");
		btnXoaChonPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoaChonPhong.setBackground(Color.WHITE);
		btnXoaChonPhong.setBounds(490, 192, 178, 57);
		panel_6.add(btnXoaChonPhong);

		btnNhanPhong = new JButton("Nhận phòng");
		btnNhanPhong.setIcon(new ImageIcon(QLDatPhong.class.getResource("/img/icons8-spell-check-30.png")));
		btnNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNhanPhong.setBackground(new Color(104, 215, 148));
		btnNhanPhong.setBounds(349, 434, 216, 44);
		panel_6.add(btnNhanPhong);

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_11.setBounds(10, 233, 686, 133);
		panel.add(panel_11);
		panel_11.setLayout(null);

		JLabel lblNhngPhngC = new JLabel("Những phòng có thể đặt");
		lblNhngPhngC.setBounds(233, 0, 214, 34);
		panel_11.add(lblNhngPhngC);
		lblNhngPhngC.setFont(new Font("Tahoma", Font.BOLD, 15));

		JPanel panel_9 = new JPanel();
		panel_9.setBounds(36, 26, 609, 97);
		panel_11.add(panel_9);
		panel_9.setLayout(null);
		panel_9.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ch\u00FA th\u00EDch", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.setBackground(SystemColor.inactiveCaptionBorder);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.RED);
		panel_10.setBounds(10, 22, 79, 10);
		panel_9.add(panel_10);

		JPanel panel_10_1 = new JPanel();
		panel_10_1.setBackground(Color.ORANGE);
		panel_10_1.setBounds(10, 69, 79, 10);
		panel_9.add(panel_10_1);

		JPanel panel_10_2 = new JPanel();
		panel_10_2.setBackground(new Color(50, 205, 50));
		panel_10_2.setBounds(224, 69, 79, 10);
		panel_9.add(panel_10_2);

		JLabel lblNewLabel_1_3_2 = new JLabel("Phòng đang sử dụng");
		lblNewLabel_1_3_2.setBounds(99, 10, 154, 22);
		panel_9.add(lblNewLabel_1_3_2);

		JLabel lblNewLabel_1_3_2_1 = new JLabel("Phòng chờ");
		lblNewLabel_1_3_2_1.setBounds(99, 57, 108, 22);
		panel_9.add(lblNewLabel_1_3_2_1);

		JLabel lblNewLabel_1_3_2_1_1 = new JLabel("Phòng trống");
		lblNewLabel_1_3_2_1_1.setBounds(313, 57, 108, 22);
		panel_9.add(lblNewLabel_1_3_2_1_1);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBorder(new EmptyBorder(1, 1, 1, 1));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBackground(SystemColor.inactiveCaption);
		btnLamMoi.setBounds(431, 27, 102, 39);
		panel_9.add(btnLamMoi);

		JPanel panel_10_3 = new JPanel();
		panel_10_3.setBackground(new Color(205, 0, 154));
		panel_10_3.setBounds(224, 17, 79, 10);
		panel_9.add(panel_10_3);

		JLabel lblNewLabel_1_3_2_2 = new JLabel("Phòng tạm");
		lblNewLabel_1_3_2_2.setBounds(313, 10, 154, 22);
		panel_9.add(lblNewLabel_1_3_2_2);
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlDanhSachPhong.removeAll();
				pnlDanhSachPhong.revalidate();
				pnlDanhSachPhong.repaint();
				loadDanhSachPhong();
				hienThiDSKhachHangDatPhong();

			}
		});

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 376, 688, 345);
		panel.add(scrollPane_1);

		GridLayout gridLayout = new GridLayout(0, 5);
		gridLayout.setHgap(7); // Khoảng cách ngang giữa các ô
		gridLayout.setVgap(10); // Khoảng cách dọc giữa các ô

		pnlDanhSachPhong = new JPanel();
		scrollPane_1.setViewportView(pnlDanhSachPhong);

		pnlDanhSachPhong.setLayout(gridLayout);
		pnlDanhSachPhong.setBorder(new EmptyBorder(5, 5, 5, 5)); // Khoảng cách xung quanh danh sách phòng

		JLabel lablePhong = new JLabel("");
		lablePhong.setSize(90, 90);
		lablePhong.setIcon(new ImageIcon(QLDatPhong.class.getResource("/img/icons8-living-room-30.png")));

		getDate();
		binDate();
		nvHienTai = nv;

		phongDAO = new DAO_Phong();
		nhanVienDAO = new DAO_NhanVien();
		khachHangDAO = new DAO_KhachHang();
		hoaDonDAO = new DAO_HoaDon();
		ctHDDAO = new DAO_ChiTietHoaDon();
		phieuDatDAO = new DAO_PhieuDat();
		CTPDPhongDao = new DAO_CTPDPhong();
		PDPCDao = new DAO_PhieuDatPhongCho();
		CTPDPhongChoDAO = new DAO_CTPhieuDatPhongCho();

		model = (DefaultTableModel) table.getModel();
		model1 = (DefaultTableModel) tblDSKhachHangDat.getModel();
		dsPhong = phongDAO.getALLPhong();
		xoaALLDuLieuTable(model);
		xoaALLDuLieuTable(model);
		loadDanhSachPhong();
		hienThiDSKhachHangDatPhong();
		rdbtnThoiGianHienTai.addItemListener(this);

		btnChonPhong.addActionListener(this);
		btnKtraKH.addActionListener(this);
		btnDatPhong.addActionListener(this);
		btnDatDichVu.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnXoaChonPhong.addActionListener(this);
		btnDatPhongCho.addActionListener(this);
		btnNhanPhong.addActionListener(this);

	}

	/**
	 * giao điện danh sách phòng hiển thị
	 */
	public void loadDanhSachPhong() {
		kiemTraThoiHanPhieuDatPhong();
		DAO_LoaiPhong loaiPhongDAO = new DAO_LoaiPhong();
		ArrayList<LoaiPhong> dsLoaiPhong = loaiPhongDAO.getAllLoaiPhong();
		String loaiVIP = null, loaiThuong = null;
		for (LoaiPhong loaiPhong : dsLoaiPhong) {
			if (loaiPhong.getTenLoaiPhong().equalsIgnoreCase("Phòng VIP")) {
				loaiVIP = loaiPhong.getMaLoaiPhong();
			} else {
				loaiThuong = loaiPhong.getMaLoaiPhong();
			}
		}
		dsPhong = phongDAO.getALLPhong();

		for (Phong phong : dsPhong) {
			if (phong.getLoaiPhong().getTenLoaiPhong().equalsIgnoreCase("Phòng VIP")) {
				// xử lí phong vip dang su dung
				if (phong.getTrangThai().equalsIgnoreCase("Đang sử dụng")) {
					JPanel item = new JPanel();
					item.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Khoảng cách xung quanh từng
																						// phòng
					item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
					ImageIcon icon = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-living-room-90.png"));
					ImageIcon iconVIP = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-vip-30.png"));
					JLabel imageLabel = new JLabel(icon);
					JLabel imageVip = new JLabel(iconVIP);
					JLabel nameLabel = new JLabel(phong.getTenPhong());
					nameLabel.setHorizontalAlignment(JLabel.CENTER);
					item.setBackground(new Color(235, 87, 83));
					item.add(imageVip);
					item.add(imageLabel);
					item.add(nameLabel);
					pnlDanhSachPhong.add(item);
					item.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// Xử lý sự kiện khi người dùng nhấp vào roomItem
							item.setBackground(Color.RED);
//							item.setBorder(BorderFactory.createLineBorder(Color.RED));
							String text = "Tên Phong: " + phong.getTenPhong() + "\n";
							txaThongTinPhong.setText(text);
							String text2 = "Loại Phòng :" + phong.getLoaiPhong().getTenLoaiPhong() + "\n";
							text2 += "Sức Chứa: " + phong.getSucChua() + "\n" + "Giá phòng: " + phong.getDonGia()
									+ "VND \n" + "Trạng thái: " + phong.getTrangThai();
							txaThongTinPhong.append(text2);
							txtMaPhong.setText(phong.getMaPhong());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// Khôi phục trạng thái ban đầu khi chuột rời khỏi panel
							item.setBackground(new Color(235, 87, 83));
//					        item.setSize(originalSize);
						}
					});
					// xu li phong vip trong
				} else if (phong.getTrangThai().equalsIgnoreCase("Trống")) {
					JPanel item = new JPanel();
					item.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Khoảng cách xung quanh từng
																						// phòng
					item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
					ImageIcon icon = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-living-room-90.png"));
					ImageIcon iconVIP = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-vip-30.png"));
					JLabel imageLabel = new JLabel(icon);
					JLabel imageVip = new JLabel(iconVIP);
					JLabel nameLabel = new JLabel(phong.getTenPhong());
					nameLabel.setHorizontalAlignment(JLabel.CENTER);
					item.setBackground(new Color(193, 229, 215));
					item.add(imageVip);
					item.add(imageLabel);
					item.add(nameLabel);
					pnlDanhSachPhong.add(item);
					item.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// Xử lý sự kiện khi người dùng nhấp vào roomItem
							item.setBackground(Color.RED);
//							item.setBorder(BorderFactory.createLineBorder(Color.RED));
							String text = "Tên Phong: " + phong.getTenPhong() + "\n";
							txaThongTinPhong.setText(text);
							String text2 = "Loại Phòng :" + phong.getLoaiPhong().getTenLoaiPhong() + "\n";
							text2 += "Sức Chứa: " + phong.getSucChua() + "\n" + "Giá phòng: " + phong.getDonGia()
									+ "VND \n" + "Trạng thái: " + phong.getTrangThai();
							txaThongTinPhong.append(text2);
							txtMaPhong.setText(phong.getMaPhong());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// Khôi phục trạng thái ban đầu khi chuột rời khỏi panel
							item.setBackground(new Color(193, 229, 215));
//					        item.setSize(originalSize);
						}

//						xử lí khi click 2 lần vào phòng để sau phát triển thêm
//						@Override
//						public void mousePressed(MouseEvent mouseEvent) {
////							JTable table =(JTable) mouseEvent.getSource();
//							Point point = mouseEvent.getPoint();
//							// lấy row được click double
////							int row = table.rowAtPoint(point);
//							if (mouseEvent.getClickCount() == 2) {
//								// làm gì khi click double ở đây
//								dialogThemKhachHang fmt = new dialogThemKhachHang("KH100");
//								fmt.setVisible(true);
//							}
//						}
					});
					// xử lí phong cho vip
				} else if (phong.getTrangThai().equalsIgnoreCase("CHỜ(sử dụng)")) {
					JPanel item = new JPanel();
					item.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Khoảng cách xung quanh từng
																						// phòng
					item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
					ImageIcon icon = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-living-room-90.png"));
					ImageIcon iconVIP = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-vip-30.png"));
					JLabel imageLabel = new JLabel(icon);
					JLabel imageVip = new JLabel(iconVIP);
					JLabel nameLabel = new JLabel(phong.getTenPhong());
					nameLabel.setHorizontalAlignment(JLabel.CENTER);
					item.setBackground(new Color(205, 0, 154));
					item.add(imageVip);
					item.add(imageLabel);
					item.add(nameLabel);
					pnlDanhSachPhong.add(item);
					item.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// Xử lý sự kiện khi người dùng nhấp vào roomItem
							item.setBackground(Color.RED);
//							item.setBorder(BorderFactory.createLineBorder(Color.RED));
							String text = "Tên Phong: " + phong.getTenPhong() + "\n";
							txaThongTinPhong.setText(text);
							String text2 = "Loại Phòng :" + phong.getLoaiPhong().getTenLoaiPhong() + "\n";
							text2 += "Sức Chứa: " + phong.getSucChua() + "\n" + "Giá phòng: " + phong.getDonGia()
									+ "VND \n" + "Trạng thái: " + phong.getTrangThai();
							txaThongTinPhong.append(text2);
							txtMaPhong.setText(phong.getMaPhong());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// Khôi phục trạng thái ban đầu khi chuột rời khỏi panel
							item.setBackground(new Color(205, 0, 154));
//					        item.setSize(originalSize);
						}
					});
				} else {
					JPanel item = new JPanel();
					item.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Khoảng cách xung quanh từng
																						// phòng
					item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
					ImageIcon icon = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-living-room-90.png"));
					ImageIcon iconVIP = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-vip-30.png"));
					JLabel imageLabel = new JLabel(icon);
					JLabel imageVip = new JLabel(iconVIP);
					JLabel nameLabel = new JLabel(phong.getTenPhong());
					nameLabel.setHorizontalAlignment(JLabel.CENTER);
					item.setBackground(new Color(253, 187, 105));
					item.add(imageVip);
					item.add(imageLabel);
					item.add(nameLabel);
					pnlDanhSachPhong.add(item);
					item.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// Xử lý sự kiện khi người dùng nhấp vào roomItem
							item.setBackground(Color.RED);
//							item.setBorder(BorderFactory.createLineBorder(Color.RED));
							String text = "Tên Phong: " + phong.getTenPhong() + "\n";
							txaThongTinPhong.setText(text);
							String text2 = "Loại Phòng :" + phong.getLoaiPhong().getTenLoaiPhong() + "\n";
							text2 += "Sức Chứa: " + phong.getSucChua() + "\n" + "Giá phòng: " + phong.getDonGia()
									+ "VND \n" + "Trạng thái: " + phong.getTrangThai();
							txaThongTinPhong.append(text2);
							txtMaPhong.setText(phong.getMaPhong());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// Khôi phục trạng thái ban đầu khi chuột rời khỏi panel
							item.setBackground(new Color(253, 187, 105));
//					        item.setSize(originalSize);
						}
					});
				}
			} else {
				if (phong.getTrangThai().equalsIgnoreCase("Đang sử dụng")) {
					JPanel item = new JPanel();
					item.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Khoảng cách xung quanh từng
																						// phòng
					item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
					ImageIcon icon = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-living-room-90.png"));
					JLabel imageLabel = new JLabel(icon);
					JLabel nameLabel = new JLabel(phong.getTenPhong());
					ImageIcon iconN = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-dry-30.png"));
					JLabel lblicon = new JLabel(iconN);
					nameLabel.setHorizontalAlignment(JLabel.CENTER);
					item.setBackground(new Color(235, 87, 83));
					item.add(lblicon);
					item.add(imageLabel);
					item.add(nameLabel);

					item.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// Xử lý sự kiện khi người dùng nhấp vào roomItem
							item.setBackground(Color.RED);
//							item.setBorder(BorderFactory.createLineBorder(Color.RED));
							String text = "Tên Phong: " + phong.getTenPhong() + "\n";
							txaThongTinPhong.setText(text);
							String text2 = "Loại Phòng :" + phong.getLoaiPhong().getTenLoaiPhong() + "\n";
							text2 += "Sức Chứa: " + phong.getSucChua() + "\n" + "Giá phòng: " + phong.getDonGia()
									+ "VND \n" + "Trạng thái: " + phong.getTrangThai();
							txaThongTinPhong.append(text2);
							txtMaPhong.setText(phong.getMaPhong());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// Khôi phục trạng thái ban đầu khi chuột rời khỏi panel
							item.setBackground(new Color(235, 87, 83));
//					        item.setSize(originalSize);
						}
					});
					pnlDanhSachPhong.add(item);
				} else if (phong.getTrangThai().equalsIgnoreCase("Trống")) {
					JPanel item = new JPanel();
					item.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Khoảng cách xung quanh từng
																						// phòng
					item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
					ImageIcon icon = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-living-room-90.png"));
					JLabel imageLabel = new JLabel(icon);
					JLabel nameLabel = new JLabel(phong.getTenPhong());
					nameLabel.setHorizontalAlignment(JLabel.CENTER);
					item.setBackground(new Color(193, 229, 215));
					ImageIcon iconN = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-dry-30.png"));
					JLabel lblicon = new JLabel(iconN);
					item.add(lblicon);
					item.add(imageLabel);
					item.add(nameLabel);
					pnlDanhSachPhong.add(item);
					item.setOpaque(true);

					item.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// Xử lý sự kiện khi người dùng nhấp vào roomItem
							item.setBackground(Color.RED);
//							item.setBorder(BorderFactory.createLineBorder(Color.RED));
							String text = "Tên Phong: " + phong.getTenPhong() + "\n";
							txaThongTinPhong.setText(text);
							String text2 = "Loại Phòng :" + phong.getLoaiPhong().getTenLoaiPhong() + "\n";
							text2 += "Sức Chứa: " + phong.getSucChua() + "\n" + "Giá phòng: " + phong.getDonGia()
									+ "VND \n" + "Trạng thái: " + phong.getTrangThai();
							txaThongTinPhong.append(text2);
							txtMaPhong.setText(phong.getMaPhong());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// Khôi phục trạng thái ban đầu khi chuột rời khỏi panel
							item.setBackground(new Color(193, 229, 215));
//					        item.setSize(originalSize);
						}
					});
				} else if (phong.getTrangThai().equalsIgnoreCase("CHỜ(sử dụng)")) {
					JPanel item = new JPanel();
					item.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Khoảng cách xung quanh từng
																						// phòng
					item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
					ImageIcon icon = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-living-room-90.png"));
					ImageIcon iconVIP = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-vip-30.png"));
					JLabel imageLabel = new JLabel(icon);
					JLabel imageVip = new JLabel(iconVIP);
					JLabel nameLabel = new JLabel(phong.getTenPhong());
					nameLabel.setHorizontalAlignment(JLabel.CENTER);
					item.setBackground(new Color(205, 0, 154));
					item.add(imageVip);
					item.add(imageLabel);
					item.add(nameLabel);
					pnlDanhSachPhong.add(item);
					item.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// Xử lý sự kiện khi người dùng nhấp vào roomItem
							item.setBackground(Color.RED);
//							item.setBorder(BorderFactory.createLineBorder(Color.RED));
							String text = "Tên Phong: " + phong.getTenPhong() + "\n";
							txaThongTinPhong.setText(text);
							String text2 = "Loại Phòng :" + phong.getLoaiPhong().getTenLoaiPhong() + "\n";
							text2 += "Sức Chứa: " + phong.getSucChua() + "\n" + "Giá phòng: " + phong.getDonGia()
									+ "VND \n" + "Trạng thái: " + phong.getTrangThai();
							txaThongTinPhong.append(text2);
							txtMaPhong.setText(phong.getMaPhong());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// Khôi phục trạng thái ban đầu khi chuột rời khỏi panel
							item.setBackground(new Color(205, 0, 154));
//					        item.setSize(originalSize);
						}
					});
				} else {
					JPanel item = new JPanel();
					item.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Khoảng cách xung quanh từng
																						// phòng
					item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
					ImageIcon icon = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-living-room-90.png"));
					JLabel imageLabel = new JLabel(icon);
					JLabel nameLabel = new JLabel(phong.getTenPhong());
					nameLabel.setHorizontalAlignment(JLabel.CENTER);
					item.setBackground(new Color(253, 187, 105));
					ImageIcon iconN = new ImageIcon(QLDatPhong.class.getResource("/img/icons8-dry-30.png"));
					JLabel lblicon = new JLabel(iconN);
					item.add(lblicon);
					item.add(imageLabel);
					item.add(nameLabel);
					pnlDanhSachPhong.add(item);
					item.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// Xử lý sự kiện khi người dùng nhấp vào roomItem
							item.setBackground(Color.RED);
							String text = "Tên Phong: " + phong.getTenPhong() + "\n";
							txaThongTinPhong.setText(text);
							String text2 = "Loại Phòng :" + phong.getLoaiPhong().getTenLoaiPhong() + "\n";
							text2 += "Sức Chứa: " + phong.getSucChua() + "\n" + "Giá phòng: " + phong.getDonGia()
									+ "VND \n" + "Trạng thái: " + phong.getTrangThai();
							txaThongTinPhong.append(text2);
							txtMaPhong.setText(phong.getMaPhong());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// Khôi phục trạng thái ban đầu khi chuột rời khỏi panel
							item.setBackground(new Color(253, 187, 105));
//						        item.setSize(originalSize);
						}

					});
				}
			}
		}
	}

	/*
	 * hiển thi thông tin giờ hiện tạo
	 */
	private void getDate() {
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				binDate();
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
	}

	private void binDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy|HH:mm:ss");

		lb_9.setText(sdf.format(new Date()));

		lb_9.setFont(new Font("Tahoma", Font.BOLD, 13));
	}

	int i = 1;
	private JTable tblDSKhachHangDat;

	/**
	 * hàm kiểm tra hiệu lực của phiếu đặt phòng chờ trước khi hiển thị lên ds phòng
	 */
	private void kiemTraThoiHanPhieuDatPhong() {
		ArrayList<PhieuDatPhongCho> ds = PDPCDao.getAllphieuDatPhongChoConHieuLuc();
		for (PhieuDatPhongCho phieuDatPhongCho : ds) {
//			if(!phieuDatPhongCho.isTinhtrang()) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime ngayNhanPhong = phieuDatPhongCho.getNgayNhanPhong();
			Duration ktra = Duration.between(ngayNhanPhong, now);

			long ktraPhut = ktra.toMinutes();
			if (ktraPhut > 30) {
				if (!PDPCDao.huyPhieuDat(phieuDatPhongCho.getMaPhieuDatPhongCho())) {
					JOptionPane.showMessageDialog(this, "lỗi phiếu đặt phòng chơ");
					return;
				}

				ArrayList<ChiTietPhieuDatPhongCho> dsCT = CTPDPhongChoDAO
						.getDSphieuDatTheoMaPhieu(phieuDatPhongCho.getMaPhieuDatPhongCho());
				for (ChiTietPhieuDatPhongCho chiTietPhieuDatPhongCho : dsCT) {
					Phong phongCho = phongDAO.timPhongTheoMaPhong(chiTietPhieuDatPhongCho.getMaPhong());
					String trangThai = "TRỐNG";
					if (!phongDAO.capNhapTrangThaiPhong(trangThai, phongCho.getMaPhong())) {
						JOptionPane.showMessageDialog(this, "Lỗi Phòng");
						return;
					}
				}
			}
		}

	}

//	}
	/**
	 * tạo mã hóa đơn
	 * 
	 * @return
	 */
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
	 * tạo ma phiếu đặt cho khách hàng sử dụng ngay thời điểm hiện tại
	 * 
	 * @return
	 */
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

	/**
	 * kiểm tra dữ liệu vào
	 * 
	 * @return
	 */
	public boolean validateData() {
		String maKh = txtMaKhachHang.getText();
		String sdt = txtSDT.getText();

		String gio = txtGio.getText();
		String phut = txtPhut.getText();
		if (maKh.trim().length() == 0) {
			showMessage(txtMaKhachHang, "Chưa có thông tin của khách hàng vui long nhập số điện thoại để kiểm tra!");
			return false;
		}
		if (sdt.trim().length() == 0) {
			showMessage(txtSDT, "Nhập số điện thoại!");
			return false;
		}
		if (!sdt.matches("^0[1-9][0-9]{8}")) {
			showMessage(txtSDT, "Số điện thoại 10 số, số đầu tiền là số 0");
			return false;
		}
		if (time_VaoPhong.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Không được để thời gian vào phòng trống!");
			return false;
		}
		if (!gio.matches("[1-9]+")) {
			showMessage(txtGio, "số giờ phải nhập số và lớp hơn 0 !");
			return false;
		}
		if (!phut.matches("^[0-5]?[0-9]$")) {
			showMessage(txtPhut, "số phút phải nhỏ hơn hoặc bằng 60 !");
			return false;
		}
		if (model.getRowCount() <= 0) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng để đặt!");
			return false;
		}
		return true;
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(null, message);
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(null, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	/***
	 * hàm lấy ds mà khách hàng đã chọn để đặt
	 * 
	 * @return
	 */
	private ArrayList<String> layDSPhongDaChon() {
		ArrayList<String> dsPhongDaChon = new ArrayList<String>();
		for (int j = 0; j < model.getRowCount(); j++) {
			String maPhongDaChon = model.getValueAt(j, 2).toString();
			dsPhongDaChon.add(maPhongDaChon);
		}
		return dsPhongDaChon;
	}

	int stt = 1;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnChonPhong)) {
			String maPhong = txtMaPhong.getText();
			if (maPhong.equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn phòng!!");
			} else {
				Phong p = phongDAO.timPhongTheoMaPhong(maPhong);
				boolean ktraPhongTam = ktraPhongChoCoDuocDatKhong();
				if (p.getTrangThai().equalsIgnoreCase("Đang sử dụng")
						|| p.getTrangThai().equalsIgnoreCase("CHỜ(sử dụng)")) {
					thongBaoLoi("Bạn không được chọn phòng này!", "lỗi");
					txtMaPhong.setText("");
				} else {
					boolean ktraPhong = ktraPhongChoCoDuocDatKhong();
					ArrayList<String> dsPhongdaChon = layDSPhongDaChon();
					for (String string : dsPhongdaChon) {
						if (p.getMaPhong().equals(string)) {
							thongBaoLoi("Bạn đã chọn phòng này rồi!", "lỗi");
							txtMaPhong.setText("");
							txaThongTinPhong.setText("");
							return;
						}
					}
					if (ktraPhong) {
						PhieuDatPhongCho pd = PDPCDao.getPhieuDatPhongChoTheoMaPhong(maPhong);
						if (pd != null) {
							LocalDateTime ngayNhan = pd.getNgayNhanPhong();
							String pattern = "- HH:mm -- dd/MM/yyyy ";
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
							String ngayNhan1 = formatter.format(ngayNhan);
							int xacNhan = JOptionPane.showConfirmDialog(this,
									"nêu đặt phòng này vui long trả trước 1 tiếng so với:" + ngayNhan1, "Thống báo",
									JOptionPane.YES_NO_OPTION);
							if (xacNhan == JOptionPane.YES_OPTION) {
								Object[] data = { stt, p.getTenPhong(), p.getMaPhong(), p.getTrangThai(),
										p.getDonGia() };
								model.addRow(data);
								stt++;
								txtMaPhong.setText("");
								txaThongTinPhong.setText("");
							} else {
								txtMaPhong.setText("");
								txaThongTinPhong.setText("");
							}
						} else {
							Object[] data = { stt, p.getTenPhong(), p.getMaPhong(), p.getTrangThai(), p.getDonGia() };
							model.addRow(data);
							stt++;
							txtMaPhong.setText("");
							txaThongTinPhong.setText("");
						}
					} else {
						thongBaoLoi("Bạn không được chọn phòng này vì sắp có người đến nhận phồng", "lỗi");
						txtMaPhong.setText("");
						txaThongTinPhong.setText("");
					}
				}
			}

		} else if (o.equals(btnKtraKH)) {

			kiemTraKhachHang();
		} else if (o.equals(btnDatPhong)) {
			boolean ktraThongTin = validateData();
			if (ktraThongTin) {
				int y = JOptionPane.showConfirmDialog(null, "Bạn có muốn đặt phòng", "Đặt Phòng",
						JOptionPane.YES_NO_OPTION);
				if (y == JOptionPane.YES_OPTION) {
					String maPhieuDat = taoMaPhieuDat();
					String makh = txtMaKhachHang.getText();
					if (taoPhieudat(maPhieuDat)) {
						if (taoChiTietPhieuDatPhongTam(maPhieuDat)) {
							JOptionPane.showMessageDialog(this, "Đặt phòng thành công");
							xoaALLDuLieuTable(model);
							hienThiDSKhachHangDatPhong();
							txaThongTinPhong.setText("");
//							txtMaKhachHang.setText("");
							txtSDT.setText("");
							time_VaoPhong.setDate(null);
							pnlDanhSachPhong.removeAll();
							pnlDanhSachPhong.revalidate();
							pnlDanhSachPhong.repaint();
							stt = 1;
							loadDanhSachPhong();
							int ys = JOptionPane.showConfirmDialog(null, "Bạn có muốn đặt thêm dịch vụ", "Đặt dịch vụ",
									JOptionPane.YES_NO_OPTION);
							if (ys == JOptionPane.YES_OPTION) {
								GD_DatDV gd = new GD_DatDV(maPhieuDat, makh);
								gd.setVisible(true);
							}
						}
					}
				}
			} else {
				xoaALLDuLieuTable(model1);
				txtSDT.setText("");
				txtMaKhachHang.setText("");
				stt = 1;
			}

		} else if (o.equals(btnDatDichVu)) {
			int row = tblDSKhachHangDat.getSelectedRow();
			if (row < 0) {
				thongBaoLoi("bạn chưa chọn khách hàng", "lỗi");
			} else {
				String maPhieu = model1.getValueAt(row, 0).toString();
				String maKh = model1.getValueAt(row, 1).toString();

				GD_DatDV x = new GD_DatDV(maPhieu, maKh);
				x.setVisible(true);

			}

		} else if (o.equals(btnThanhToan)) {
			int row = tblDSKhachHangDat.getSelectedRow();
			if (row < 0) {
				thongBaoLoi("bạn chưa chọn khách hàng", "lỗi");
			} else {
				String maPhieu = model1.getValueAt(row, 0).toString();
				String maKh = model1.getValueAt(row, 1).toString();
				GD_HoaDon frm = new GD_HoaDon(maPhieu, maKh, nvHienTai);
				frm.setVisible(true);
			}
		} else if (o.equals(btnXoaChonPhong)) {
			int row = table.getSelectedRow();
			if (row >= 0) {
				int y = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa phòng ra khỏi danh sách ", "Xóa đặt phòng",
						JOptionPane.YES_NO_OPTION);
				if (y == JOptionPane.YES_OPTION) {
					model.removeRow(row);
					for (int i = 0; i < model.getRowCount(); i++) {
						model.setValueAt(i + 1, i, 0);
					}
					stt -= 1;
				}

			} else {
				thongBaoLoi("bạn chưa chọn phòng trong danh sách phòng!", "lỗi");
			}
		} else if (o.equals(btnDatPhongCho)) {
			if (model.getRowCount() <= 0) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn phòng");
			} else {
				String maPhieuDatCho = taoMaPhieuDatPhongCho();
				ArrayList<ChiTietPhieuDatPhongCho> ds = new ArrayList<ChiTietPhieuDatPhongCho>();
				for (int i = 0; i < model.getRowCount(); i++) {
					String maPhong = model.getValueAt(i, 2).toString();
					ChiTietPhieuDatPhongCho ctpd = new ChiTietPhieuDatPhongCho(maPhieuDatCho, maPhong);
					ds.add(ctpd);
				}
				DatPhongCho dialogDatPhongCho = new DatPhongCho(ds, nvHienTai);
				dialogDatPhongCho.setVisible(true);
				xoaALLDuLieuTable(model);
				stt = 1;
			}

		} else if (o.equals(btnNhanPhong)) {
			String maPhong = txtMaPhong.getText();
			if (maPhong.equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn phòng chờ để nhận phòng");
			} else {
				Phong phongCho = phongDAO.timPhongTheoMaPhong(maPhong);
				if (phongCho.getTrangThai().equalsIgnoreCase("CHỜ")) {
					int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn nhận phòng không?", "Nhận Phòng",
							JOptionPane.YES_NO_OPTION);
					if (xacnhan == JOptionPane.YES_OPTION) {
						PhieuDatPhongCho phieuDatPC = PDPCDao.getPhieuDatPhongChoTheoMaPhong(maPhong);
						LocalDateTime ngayDat = LocalDateTime.now();
						boolean trangthai = false;
						String maPhieuDatSuDungNgay = taoMaPhieuDat();
						PhieuDat pd = new PhieuDat(maPhieuDatSuDungNgay, phieuDatPC.getMaKhachHang(),
								nvHienTai.getMaNV(), ngayDat, trangthai);
						if (!phieuDatDAO.themPhieuDat(pd)) {
							thongBaoLoi("Lỗi tạo phiếu đặt", "lỗi");
							return;
						}
						ArrayList<ChiTietPhieuDatPhongCho> dsct = CTPDPhongChoDAO
								.getDSphieuDatTheoMaPhieu(phieuDatPC.getMaPhieuDatPhongCho());
						for (ChiTietPhieuDatPhongCho chiTietPhieuDatPhongCho : dsct) {
							Phong phongSD = phongDAO.timPhongTheoMaPhong(chiTietPhieuDatPhongCho.getMaPhong());
							if (!phongDAO.capNhapTrangThaiPhong("Đang Sử Dụng", phongSD.getMaPhong())) {
								thongBaoLoi("Lỗi cập nhập trạng thái phòng", "lỗi");
								return;
							}
							ChiTietPhieuDatPhong ctpd = new ChiTietPhieuDatPhong(maPhieuDatSuDungNgay, phongSD);
							if (!CTPDPhongDao.themChiTietPhieuDatP(ctpd)) { // CTPDPHong tạo chi tiết phieu dặt phong
																			// cho
																			// khách hàng dụng ngay
								thongBaoLoi("Lỗi tạo phiếu chi tiết đặt phòng", "lỗi");
								return;
							}
						}
						if (!PDPCDao.capNhapTrangThai(phieuDatPC.getMaPhieuDatPhongCho())) {
							thongBaoLoi("Lỗi cập nhập trang Thái phiếu đặt phòng chờ", "lỗi");
							return;
						}
						loadDanhSachPhong();
						hienThiDSKhachHangDatPhong();
						JOptionPane.showMessageDialog(this, "Nhận Phòng Thành Công");
					}

				} else {
					JOptionPane.showMessageDialog(this, "Phòng này không phải phòng chờ");
				}

			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getStateChange() == ItemEvent.SELECTED) {
			LocalDateTime now = LocalDateTime.now();
			Date thoiGianHienTai = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
			time_VaoPhong.setDate(thoiGianHienTai);
		} else {
			time_VaoPhong.setDate(null);
		}

	}

	public void xoaALLDuLieuTable(DefaultTableModel model) {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	/**
	 * kiểm tra khách hàng có tồn tại trong hệ thống hay không nếu chưa thì thêm
	 * khách hàng
	 */
	public void kiemTraKhachHang() {
		String sdt = txtSDT.getText();

		if (sdt.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại!");
			return;
		} else if (!sdt.matches("^0[1-9][0-9]{8}")) {
			showMessage(txtSDT, "Số điện thoại 10 số, số đầu tiền là số 0");
		} else {
			KhachHang kh = khachHangDAO.timKhachHangTheoSDT(sdt);
			if (kh == null) {
				int y = JOptionPane.showConfirmDialog(null, "Khách hàng chưa tồn tại. Bạn có muốn thêm khách hàng",
						"Thêm khách hàng", JOptionPane.YES_NO_OPTION);
				if (y == JOptionPane.YES_OPTION) {
					String makh = taoMaKH();
					txtMaKhachHang.setText(makh);
					dialogThemKhachHang dialog = new dialogThemKhachHang(makh);
					dialog.setVisible(true);
				}
			} else {
				txtMaKhachHang.setText(kh.getMaKH());
			}
		}
	}

	/**
	 * hàm kiểm tra xem có tạo được phiếu đặt cho khách hàng sử dụng ngay thời điểm
	 * hiện tạo hay không rồi lưu vào csdl để sau khi nhân thanh toán thì chuyển
	 * sang hóa đơn
	 * 
	 * @param maPhieuDat
	 * @return
	 */
	public boolean taoPhieudat(String maPhieuDat) {
		String maKh = txtMaKhachHang.getText();
		LocalDateTime ngayDat = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		String manv = nvHienTai.getMaNV();
		boolean trangthai = false;

		PhieuDat pd = new PhieuDat(maPhieuDat, maKh, manv, ngayDat, trangthai);
		if (!phieuDatDAO.themPhieuDat(pd)) {
			thongBaoLoi("Lỗi tạo phiếu đặt", "lỗi");
			return false;
		}
		return true;
	}

	/**
	 * hạm ktra tạo chi tiết phiếu đặt các phòng hay dịch vụ mà khách hàng chọn
	 * 
	 * @param maPhieu
	 * @return
	 */
	public boolean taoChiTietPhieuDatPhong(String maPhieu) {
		ArrayList<String> dsPhongChon = layDSPhongDaChon();
		for (String maPhong : dsPhongChon) {
			Phong x = phongDAO.timPhongTheoMaPhong(maPhong);
			if (!phongDAO.capNhapTrangThaiPhong("Đang Sử Dụng", x.getMaPhong())) {
				thongBaoLoi("Lỗi cập nhập trạng thái phòng", "lỗi");
				return false;
			}
			ChiTietPhieuDatPhong ctpd = new ChiTietPhieuDatPhong(maPhieu, x);
			if (!CTPDPhongDao.themChiTietPhieuDatP(ctpd)) {
				thongBaoLoi("Lỗi tạo phiếu chi tiết đặt phòng", "lỗi");
				return false;
			}
		}
		return true;
	}

	/**
	 * hạm ktra tạo chi tiết phiếu đặt các phòng hay dịch vụ mà khách hàng chọn
	 * (trường hợp này cho việc phòng đang là phòng chờ và có thể đặt để sử dụng)
	 * 
	 * @param maPhieu
	 * @return
	 */
	public boolean taoChiTietPhieuDatPhongTam(String maPhieu) {
		ArrayList<String> dsPhongChon = layDSPhongDaChon();
		for (String maPhong : dsPhongChon) {
			PhieuDatPhongCho phieuDatPhongCho = PDPCDao.getPhieuDatPhongChoTheoMaPhong(maPhong);
			Phong x = phongDAO.timPhongTheoMaPhong(maPhong);
			if (phieuDatPhongCho != null) {// phiếu đặt tồn tại nghĩa là phòng ở trạng thái
				String trangThai = "CHỜ(sử dụng)";
				if (!phongDAO.capNhapTrangThaiPhong(trangThai, x.getMaPhong())) {
					thongBaoLoi("Lỗi cập nhập trạng thái phòng", "lỗi");
					return false;
				}
			} else {
				if (!phongDAO.capNhapTrangThaiPhong("Đang Sử Dụng", x.getMaPhong())) {
					thongBaoLoi("Lỗi cập nhập trạng thái phòng", "lỗi");
					return false;
				}
			}
			ChiTietPhieuDatPhong ctpd = new ChiTietPhieuDatPhong(maPhieu, x);
			if (!CTPDPhongDao.themChiTietPhieuDatP(ctpd)) {
				thongBaoLoi("Lỗi tạo phiếu chi tiết đặt phòng", "lỗi");
				return false;
			}
		}
		/**
		 * phòng chuyển sang trang thái chờ sử dụng
		 */
		return true;
	}

	/**
	 * kiểm tra xem phòng có cho phép được đặt hay không khi ở trạng thái phòng chờ
	 * 
	 * @return
	 */
	public boolean ktraPhongChoCoDuocDatKhong() {
		String maPhong = txtMaPhong.getText();
		PhieuDatPhongCho phieuDat = PDPCDao.getPhieuDatPhongChoTheoMaPhong(maPhong);
		if (phieuDat == null) {
			return true;
		}
		if (phieuDat != null) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime ngayNhanPhong = phieuDat.getNgayNhanPhong();
//			long hours = ChronoUnit.HOURS.between(now,ngayNhanPhong);
			Duration ktra = Duration.between(now, ngayNhanPhong);
			long hour = ktra.toHours();
			if (hour < 3) { // kieam tra nếu thời điểm hiện tại và thời điểm nhận phòng của phòng đó cách
							// nhau 3 tiếng trở lên thì mới đc cho phép đặt phòng đó
				return false;
			}
		}
		return true;
	}

	public void hienThiDSKhachHangDatPhong() {
		xoaALLDuLieuTable(model1);
		ArrayList<PhieuDat> dsphieuDat = phieuDatDAO.getALLPhieuDat();
		for (PhieuDat phieuDat : dsphieuDat) {
			if (!phieuDat.isTrangThai()) {
				KhachHang kh = khachHangDAO.timKhachHangTheoMa(phieuDat.getMaKhachHang());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				ArrayList<ChiTietPhieuDatPhong> dsct = CTPDPhongDao.getDSphieuDatTheoMaPhieu(phieuDat.getMaPhieuDat());
				String dsPhong = "";
				for (int i = 0; i < dsct.size(); i++) {
					dsPhong += dsct.get(i).getPhong().getTenPhong() + ",";
				}
				Object[] data = { phieuDat.getMaPhieuDat(), kh.getMaKH(), kh.getTenKH(), kh.getSdt(),
						phieuDat.getNgayDat().toString(), dsPhong };
				model1.addRow(data);
			}
		}
	}

	/**
	 * tạo mã phiếu đặt phòng chờ
	 * 
	 * @return
	 */
	public String taoMaPhieuDatPhongCho() {
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
}

/*
 * ktra lúc chọn phòng nếu phòng chờ cách thời điểm hiện tại trc 2 tiếng thì cho
 * --> done chọn phòng --> chuyển sang trạng thái chờ sử dụng lúc thanh toán
 * ktra nếu --> done phòng dang sử dụng là chờ (sử dụng thì thanh toán và chuyển
 * sang trang thái-->done phòng là chờ)
 */
