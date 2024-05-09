package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Choice;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JYearChooserBeanInfo;

import DAO.DAO_ChiTietDichVu;
import DAO.DAO_ChiTietHoaDon;
import DAO.DAO_HoaDon;
import DAO.DAO_KhachHang;
import DAO.DAO_NhanVien;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.JComboBox;
import javax.swing.UIManager;

public class ThongKeDoanhThu extends JPanel implements ActionListener {
	private JTextField txtChonNgay;
	private JTable table;
	private JComboBox cmbLuaChon;
	private JDateChooser txtLuaChonNgay;
	private JLabel lblChonNgay;
	private JButton btnXacNhan, btnDoanhThuHoaDon, btnTKTheoGioTrongNgay;
	private DefaultTableModel model;
	private DAO_HoaDon hoaDonDAO;
	private DAO_KhachHang khachHangDAO;
	private DAO_NhanVien nhanVienDAO;
	private DAO_ChiTietDichVu CTDVDAO;
	private DAO_ChiTietHoaDon CTHDDAO;
	private ArrayList<HoaDon> dsHoaDon;
//	private ChartPanel pnlChart;
	private JPanel panel_2, panel_1;

	/**
	 * Create the panel.
	 */
	public ThongKeDoanhThu() {
		setLayout(null);
		setBounds(0, 0, 1540, 787);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 1530, 845);
		add(panel);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(10, 10, 267, 388);
		panel.add(panel_1);

		JLabel lblThoiGianThongKe = new JLabel("Thời gian thống kê");
		lblThoiGianThongKe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThoiGianThongKe.setBackground(SystemColor.inactiveCaption);
		lblThoiGianThongKe.setBounds(29, 10, 199, 36);
		panel_1.add(lblThoiGianThongKe);

		JLabel lblLuaChonThongKe = new JLabel("Lựa chọn theo:");
		lblLuaChonThongKe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLuaChonThongKe.setBounds(20, 109, 114, 26);
		panel_1.add(lblLuaChonThongKe);

		lblChonNgay = new JLabel("Chọn ngày:");
		lblChonNgay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChonNgay.setBounds(20, 223, 114, 26);
		panel_1.add(lblChonNgay);

//		txtChonNgay = new JTextField();
//		txtChonNgay.setColumns(10);
//		txtChonNgay.setBounds(20, 266, 164, 26);
		txtLuaChonNgay = new JDateChooser();
		txtLuaChonNgay.setBounds(20, 259, 164, 26);
		panel_1.add(txtLuaChonNgay);

		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXacNhan.setBackground(Color.WHITE);
		btnXacNhan.setBounds(70, 321, 114, 36);
		panel_1.add(btnXacNhan);

		cmbLuaChon = new JComboBox();
		cmbLuaChon.setBounds(20, 145, 164, 26);
		panel_1.add(cmbLuaChon);
		cmbLuaChon.addItem("Theo ngày");
		cmbLuaChon.addItem("Theo tháng");
		cmbLuaChon.addItem("Theo năm");

//		textField = new JTextField();
//		textField.setBounds(20, 183, 164, 30);
//		panel_1.add(textField);
//		textField.setColumns(10);
//		JMonthChooser chonThang = new JMonthChooser();
//		chonThang.setBorder(UIManager.getBorder("DesktopIcon.border"));
//		chonThang.setBackground(Color.RED);
//		chonThang.setBounds(20, 183, 164, 30);
//		panel_1.add(chonThang);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1_1.setBounds(10, 408, 267, 354);
		panel.add(panel_1_1);

		JLabel lblMucThongKe = new JLabel("Mục thống kê");
		lblMucThongKe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMucThongKe.setBackground(SystemColor.inactiveCaption);
		lblMucThongKe.setBounds(60, 22, 143, 36);
		panel_1_1.add(lblMucThongKe);

		btnDoanhThuHoaDon = new JButton("Doanh thu hóa đơn");
		btnDoanhThuHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDoanhThuHoaDon.setBackground(Color.WHITE);
		btnDoanhThuHoaDon.setBounds(23, 81, 214, 44);
		panel_1_1.add(btnDoanhThuHoaDon);

		btnTKTheoGioTrongNgay = new JButton("Thống kê theo giờ trong ngày");
		btnTKTheoGioTrongNgay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTKTheoGioTrongNgay.setBackground(Color.WHITE);
		btnTKTheoGioTrongNgay.setBounds(23, 161, 214, 44);
		panel_1_1.add(btnTKTheoGioTrongNgay);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(287, 10, 1233, 439);
		panel.add(panel_2);

		JLabel lblBieuDo = new JLabel("BIỂU ĐỒ DOANH THU ");
		lblBieuDo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBieuDo.setBounds(447, 10, 366, 43);
		panel_2.add(lblBieuDo);

//		try {
//			pnlChart = new ChartPanel(taoBieuDo(createDataset()));
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};

//		pnlChart.setBounds(42, 66, 1164, 350);
//		panel_2.add(pnlChart);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(287, 459, 1233, 300);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã Hóa Đơn", "Ngày Lập",
				"Khách Hàng", "SDT Khách Hàng", "Nhân Viên", "Tiền Thanh Toán" }));

		model = (DefaultTableModel) table.getModel();
		hoaDonDAO = new DAO_HoaDon();
		nhanVienDAO = new DAO_NhanVien();
		khachHangDAO = new DAO_KhachHang();
		CTDVDAO = new DAO_ChiTietDichVu();
		CTHDDAO = new DAO_ChiTietHoaDon();
		cmbLuaChon.addActionListener(this);
		btnXacNhan.addActionListener(this);
		btnDoanhThuHoaDon.addActionListener(this);
		btnTKTheoGioTrongNgay.addActionListener(this);
		dsHoaDon = hoaDonDAO.getALLHoaDon();
		loadData(dsHoaDon);
		/*
		 * mở giao điện thống kê sẽ hiện thị biểu đồ hóa đơn trong ngày
		 */
		thongkeHoaDonNgayHienTai(LocalDate.now());
	}

	public void xoaALLDuLieuTable() {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	int stt = 1;
	private JTextField textField;

	public void loadData(ArrayList<HoaDon> dsHoaDon) {
		xoaALLDuLieuTable();
		int i = 1;
		for (HoaDon hoaDon : dsHoaDon) {
			KhachHang kh = khachHangDAO.timKhachHangTheoMa(hoaDon.getMaKhachHang());
			NhanVien nv = nhanVienDAO.timNhanVienTheoMaNV(hoaDon.getMaNhanVien());

			DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/YYYY || HH:mm");
			ArrayList<ChiTietDichVu> dsChiTietDichVu = CTDVDAO.getALLChiTietDVTheoMaHD(hoaDon.getMaHoaDon());
			ArrayList<ChiTietHoaDon> dsChiTietHoaDon = CTHDDAO.layDSTheoMaHD(hoaDon.getMaHoaDon());
			double tongTien = 0;
			for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
				double soGio = (double)chiTietHoaDon.getThoiLuong() / 60;
				double thanhTien = soGio * chiTietHoaDon.getPhong().getDonGia();
				tongTien += thanhTien;
			}
			for (ChiTietDichVu chiTietDichVu : dsChiTietDichVu) {
				double donGia = chiTietDichVu.getDichVu().getDonGia();
				double thanhTien = chiTietDichVu.getSoLuong() * donGia;
				tongTien += thanhTien;

			}
			DecimalFormat df = new DecimalFormat("###,###");
			Object[] data = { i, hoaDon.getMaHoaDon(), dt.format(hoaDon.getNgayTao()), kh.getTenKH(), kh.getSdt(),
					nv.getTenNV(),df.format( tongTien) };
			model.addRow(data);

			i++;
		}
	}

	public void showDataTheoYeucau() {
		if (cmbLuaChon.getSelectedItem().equals("Theo ngày")) {
			LocalDate ngayTao = txtLuaChonNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int ngay = ngayTao.getDayOfMonth();
			int thang = ngayTao.getMonthValue();
			int nam = ngayTao.getYear();
			ArrayList<HoaDon> dsHoadonTheoNgay = hoaDonDAO.getDSHoaDonTheoNgay(String.valueOf(ngay),
					String.valueOf(thang), String.valueOf(nam));
			loadData(dsHoadonTheoNgay);
		} else if (cmbLuaChon.getSelectedItem().equals("Theo tháng")) {
			LocalDate ngayTao = txtLuaChonNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int thang = ngayTao.getMonthValue();
			int nam = ngayTao.getYear();
			ArrayList<HoaDon> dsHoadonTheoThang = hoaDonDAO.getDSHoaDonTheoThang(String.valueOf(thang),
					String.valueOf(nam));
			
			loadData(dsHoadonTheoThang);
		} else {
			LocalDate ngayTao = txtLuaChonNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int nam = ngayTao.getYear();
			
			ArrayList<HoaDon> dsHoadonTheoThang = hoaDonDAO.getDSHoaDonTheoNam(String.valueOf(nam));
			loadData(dsHoadonTheoThang);
		}
	}

	public void thongkeHoaDonNgayHienTai(LocalDate now) {
		int ngay = now.getDayOfMonth();
		int thang = now.getMonthValue();
		int nam = now.getYear();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<HoaDon> dsTrongNgay = hoaDonDAO.getDSHoaDonTheoNgay(String.valueOf(ngay), String.valueOf(thang),
				String.valueOf(nam));
		for (HoaDon hoaDon : dsTrongNgay) {
			double tongDoanhthu = 0;
			ArrayList<ChiTietDichVu> dsChiTietDichVu = CTDVDAO.getALLChiTietDVTheoMaHD(hoaDon.getMaHoaDon());
			ArrayList<ChiTietHoaDon> dsChiTietHoaDon = CTHDDAO.layDSTheoMaHD(hoaDon.getMaHoaDon());
			for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
				double soGio = (double)chiTietHoaDon.getThoiLuong() / 60;
				double thanhTien = soGio * chiTietHoaDon.getPhong().getDonGia();
				tongDoanhthu += thanhTien;
			}
			for (ChiTietDichVu chiTietDichVu : dsChiTietDichVu) {
				double donGia = chiTietDichVu.getDichVu().getDonGia();
				double thanhTien = chiTietDichVu.getSoLuong() * donGia;
				tongDoanhthu += thanhTien;
			}
			dataset.addValue(tongDoanhthu, "Hoa Dơn", hoaDon.getMaHoaDon());
		}
		JFreeChart chart = ChartFactory.createBarChart("Biểu Đồ Doanh Thu Theo Ngày " + String.valueOf(now), "Hóa Đơn",
				"Doanh Thu", dataset, PlotOrientation.VERTICAL, false, false, false);
		panel_2.removeAll();
		ChartPanel pnlChart = new ChartPanel(chart);
		pnlChart.setBounds(28, 32, 1163, 377);
		panel_2.add(pnlChart);
	}

	// tao bieu do de biet trong ngay gio dat nhieu hoa don nhat la:
	public void thongKeSlHoaDonTheoGio(LocalDate now) {
		int ngay = now.getDayOfMonth();
		int thang = now.getMonthValue();
		int nam = now.getYear();
		HoaDon hd = null;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 1; i <= 24; i++) {
			int soLuongHoaDon = hoaDonDAO.getSoluongHoaDonTheoGioTrongNgay(String.valueOf(ngay), String.valueOf(thang),
					String.valueOf(nam), String.valueOf(i));
			dataset.addValue(soLuongHoaDon, "giờ", "" + i);
		}
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		JFreeChart chart = ChartFactory.createBarChart(
				"Biểu Đồ Thống Kê Thời Gian Đặt Phòng Của Khách Hàng:" + dft.format(now), "Giờ", "số lượng hóa đơn",
				dataset, PlotOrientation.VERTICAL, false, false, false);
		panel_2.removeAll();
		panel_2.repaint();
		panel_2.revalidate();
		ChartPanel pnlChart = new ChartPanel(chart);
		pnlChart.setBounds(28, 32, 1163, 377);
		panel_2.add(pnlChart);
	}

	// Tạo biểu đồ thống kê các ngày trong 1 tháng
	public JFreeChart taoBieuDoTheoThang(CategoryDataset dataset, String thang) {
		JFreeChart chart = ChartFactory.createBarChart("Biểu Đồ Doanh Thu Theo Tháng " + thang, "Ngày", "Doanh Thu",
				dataset, PlotOrientation.VERTICAL, false, false, false);
		return chart;
	}
	/**
	 * tạo biểu đồ hiển thi
	 */
	private CategoryDataset createDataSetTheoThang(ArrayList<HoaDon> dsHoaDonTheoThang) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 1; i < 32; i++) {
			double tongDoanhthu = 0;
			for (HoaDon hoaDon : dsHoaDonTheoThang) {
				if (hoaDon.getThoiGianKetThuc().getDayOfMonth() == i) {
					ArrayList<ChiTietDichVu> dsChiTietDichVu = CTDVDAO.getALLChiTietDVTheoMaHD(hoaDon.getMaHoaDon());
					ArrayList<ChiTietHoaDon> dsChiTietHoaDon = CTHDDAO.layDSTheoMaHD(hoaDon.getMaHoaDon());
					for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
						double soGio =(double) chiTietHoaDon.getThoiLuong() / 60;
						double thanhTien = soGio * chiTietHoaDon.getPhong().getDonGia();
						tongDoanhthu += thanhTien;
					}
					for (ChiTietDichVu chiTietDichVu : dsChiTietDichVu) {
						double donGia = chiTietDichVu.getDichVu().getDonGia();
						double thanhTien = chiTietDichVu.getSoLuong() * donGia;
						tongDoanhthu += thanhTien;
					}
				}
				dataset.addValue(tongDoanhthu, "ngày", "" + i);
			}
		}

		return dataset;
	}

	// Tạo biểu đồ thống kê 12 tháng của 1 năm
	public JFreeChart taoBieuDoTheoNam(CategoryDataset dataset, String nam) {
		JFreeChart chart = ChartFactory.createBarChart("Biểu Đồ Doanh Thu Theo năm " + nam, "Tháng", "Doanh Thu",
				dataset, PlotOrientation.VERTICAL, false, false, false);
		return chart;
	}
	/**
	 * tạo biểu đồ hiển thi
	 */
	private CategoryDataset createDataSetTheoNam(ArrayList<HoaDon> dsHoaDonTheonam) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 1; i < 13; i++) {
			double tongDoanhthu = 0;
			for (HoaDon hoaDon : dsHoaDonTheonam) {
				if (hoaDon.getThoiGianKetThuc().getMonthValue() == i) {
					ArrayList<ChiTietDichVu> dsChiTietDichVu = CTDVDAO.getALLChiTietDVTheoMaHD(hoaDon.getMaHoaDon());
					ArrayList<ChiTietHoaDon> dsChiTietHoaDon = CTHDDAO.layDSTheoMaHD(hoaDon.getMaHoaDon());
					for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
						double soGio = (double)chiTietHoaDon.getThoiLuong() / 60;
						double thanhTien = soGio * chiTietHoaDon.getPhong().getDonGia();
						tongDoanhthu += thanhTien;
					}
					for (ChiTietDichVu chiTietDichVu : dsChiTietDichVu) {
						double donGia = chiTietDichVu.getDichVu().getDonGia();
						double thanhTien = chiTietDichVu.getSoLuong() * donGia;
						tongDoanhthu += thanhTien;
					}
				}
				dataset.addValue(tongDoanhthu, "Tháng", "" + i);
			}
		}

		return dataset;
	}

	// Tạo biểu đồ thống kê các hóa đơn trong ngày(có bao nhiêu hóa đơn trong ngày)
	public JFreeChart taoBieuDoTheoNgay(CategoryDataset dataset, String Ngay) {
		JFreeChart chart = ChartFactory.createBarChart("Biểu Đồ Doanh Thu Theo Ngày " + Ngay, "Hóa Đơn", "Doanh Thu",
				dataset, PlotOrientation.VERTICAL, false, false, false);
		return chart;
	}

	private CategoryDataset createDataSetTheoNgay(ArrayList<HoaDon> dsHoaDonTheoNgay) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (HoaDon hoaDon : dsHoaDonTheoNgay) {
			double tongDoanhthu = 0;
			ArrayList<ChiTietDichVu> dsChiTietDichVu = CTDVDAO.getALLChiTietDVTheoMaHD(hoaDon.getMaHoaDon());
			ArrayList<ChiTietHoaDon> dsChiTietHoaDon = CTHDDAO.layDSTheoMaHD(hoaDon.getMaHoaDon());
			for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
				double soGio = (double)chiTietHoaDon.getThoiLuong() / 60;
				double thanhTien = soGio * chiTietHoaDon.getPhong().getDonGia();
				tongDoanhthu += thanhTien;
			}
			for (ChiTietDichVu chiTietDichVu : dsChiTietDichVu) {
				double donGia = chiTietDichVu.getDichVu().getDonGia();
				double thanhTien = chiTietDichVu.getSoLuong() * donGia;
				tongDoanhthu += thanhTien;
			}
			dataset.addValue(tongDoanhthu, "Hoa Dơn", hoaDon.getMaHoaDon());
		}
		return dataset;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(cmbLuaChon)) {
			if (cmbLuaChon.getSelectedItem().equals("Theo ngày")) {
				lblChonNgay.setText("Chọn Ngày");
				txtLuaChonNgay.setDate(null);
				txtLuaChonNgay.setDateFormatString("dd-MM-yyyy");
			}
			if (cmbLuaChon.getSelectedItem().equals("Theo tháng")) {
				lblChonNgay.setText("Chọn Tháng");
				txtLuaChonNgay.setDate(null);
				txtLuaChonNgay.setDateFormatString("MM-yyyy");
			}
			if (cmbLuaChon.getSelectedItem().equals("Theo năm")) {
				lblChonNgay.setText("Chọn Năm");
				txtLuaChonNgay.setDate(null);
				txtLuaChonNgay.setDateFormatString("yyyy");
			}

		} else if (o.equals(btnXacNhan)) {
			if (txtLuaChonNgay.getDate() == null) {
				if (cmbLuaChon.getSelectedItem().equals("Theo ngày")) {
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày thống kê!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (cmbLuaChon.getSelectedItem().equals("Theo tháng")) {
						JOptionPane.showMessageDialog(this, "Bạn chưa chọn tháng thống kê!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this, "Bạn chưa chọn năm thống kê!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				showDataTheoYeucau();
			}
		} else if (o.equals(btnDoanhThuHoaDon)) {
			if (cmbLuaChon.getSelectedItem().equals("Theo tháng")) {
				LocalDate ngayChon = txtLuaChonNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int thang = ngayChon.getMonthValue();
				int nam = ngayChon.getYear();
				ArrayList<HoaDon> dsHoadonTheoThang = hoaDonDAO.getDSHoaDonTheoThang(String.valueOf(thang),
						String.valueOf(nam));
				panel_2.removeAll();
				panel_2.repaint();
				panel_2.revalidate();
				/**
				 * tạo biểu đồ hiển thi
				 */
				ChartPanel pnlChart = new ChartPanel(
						taoBieuDoTheoThang(createDataSetTheoThang(dsHoadonTheoThang), String.valueOf(thang)));
				pnlChart.setBounds(28, 32, 1163, 377);
				panel_2.add(pnlChart);
			} else if (cmbLuaChon.getSelectedItem().equals("Theo năm")) {
				LocalDate ngayChon = txtLuaChonNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int nam = ngayChon.getYear();
				ArrayList<HoaDon> dsHoaDonTheoNam = hoaDonDAO.getDSHoaDonTheoNam(String.valueOf(nam));
				/**
				 * tạo biểu đồ hiển thi
				 */
				ChartPanel pnlChart = new ChartPanel(
						taoBieuDoTheoNam(createDataSetTheoNam(dsHoaDonTheoNam), String.valueOf(nam)));
				panel_2.removeAll();
				panel_2.repaint();
				panel_2.revalidate();
				pnlChart.setBounds(28, 32, 1163, 377);
				panel_2.add(pnlChart);
			} else {
				LocalDate ngayChon = txtLuaChonNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int ngay = ngayChon.getDayOfMonth();
				int thang = ngayChon.getMonthValue();
				int nam = ngayChon.getYear();
				ArrayList<HoaDon> dsHoadonTheoNgay = hoaDonDAO.getDSHoaDonTheoNgay(String.valueOf(ngay),
						String.valueOf(thang), String.valueOf(nam));
				/**
				 * tạo biểu đồ hiển thi
				 */
				ChartPanel pnlChart = new ChartPanel(taoBieuDoTheoNgay(createDataSetTheoNgay(dsHoadonTheoNgay),
						String.valueOf(ngay) + "/" + String.valueOf(thang) + "/" + String.valueOf(nam)));
				panel_2.removeAll();
				panel_2.repaint();
				panel_2.revalidate();
				pnlChart.setBounds(28, 32, 1163, 377);
				panel_2.add(pnlChart);
			}
		}
		else if(o.equals(btnTKTheoGioTrongNgay)) {
			if(cmbLuaChon.getSelectedItem().equals("Theo ngày")) {
				LocalDate ngayChon = txtLuaChonNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				thongKeSlHoaDonTheoGio(ngayChon);
			}
			else {
				JOptionPane.showMessageDialog(null, "Chức năng chỉ sử dụng theo ngày");
			}
		}
	}
}
