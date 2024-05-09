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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



import DAO.DAO_ChiTietDichVu;
import DAO.DAO_ChiTietHoaDon;
import DAO.DAO_HoaDon;
import DAO.DAO_KhachHang;
import DAO.DAO_NhanVien;
import DAO.DAO_Phong;
import connectDB.connectDB;
//import org.w3c.dom.Document;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phong;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.lang.model.element.NestingKind;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

public class GD_HoaDonXemChiTiet extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtMaHoaDon;
	private JTextField txtNV;
	private JTextField txtKH;
	private JTextField txtNgayTao;
	private JTextField txtSDT;
	private JTable table;
	private JTextField txtTongTien;
	private JButton btnQuayLai, btnXuatFile;
	private DefaultTableModel model;

	private DAO_ChiTietDichVu CTDVDAO;
	private DAO_KhachHang khachHangDAO;
	private DAO_HoaDon hoaDonDAO;
	private DAO_ChiTietHoaDon CTHDDAO;
	private DAO_NhanVien nhanVienDAO;
	private DAO_Phong phongDAO;
	private ArrayList<ChiTietDichVu> dsChiTietDV;
	private ArrayList<ChiTietHoaDon> dsChiTietHD;
	private double tongTien = 0;
	private NhanVien nhanVienHienTai;
	private HoaDon hoaDonHienTai;

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
	public GD_HoaDonXemChiTiet(HoaDon hoaDon) {

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
		lbHoaDonTinhTien.setBounds(386, 0, 121, 33);
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
		scrollPane.setBounds(10, 164, 763, 243);
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
		TableColumn column1 = table.getColumnModel().getColumn(0);
		column1.setPreferredWidth(50); // Độ rộng
		TableColumn column2 = table.getColumnModel().getColumn(1);
		column2.setPreferredWidth(120); // Độ rộng
		TableColumn column3 = table.getColumnModel().getColumn(2);
		column3.setPreferredWidth(170); // Độ rộng
		TableColumn column4 = table.getColumnModel().getColumn(3);
		column4.setPreferredWidth(130);

		JLabel lbTongTien = new JLabel("Tổng Tiền :");
		lbTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTongTien.setBounds(10, 671, 101, 24);
		contentPane.add(lbTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(98, 671, 176, 24);
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
		table.getTableHeader().setBackground(new Color(255, 252, 237));
		model = (DefaultTableModel) table.getModel();
		CTHDDAO = new DAO_ChiTietHoaDon();
		CTDVDAO = new DAO_ChiTietDichVu();
		hoaDonDAO = new DAO_HoaDon();
		khachHangDAO = new DAO_KhachHang();
		nhanVienDAO = new DAO_NhanVien();
		phongDAO = new DAO_Phong();
//		private DAO_ChiTietDichVu CHTDVDAO;
//		private DAO_KhachHang khachHangDAO;
//		private DAO_HoaDon hoaDonDAO;
//		private DAO_ChiTietHoaDon CTHĐAO;
//		private DAO_NhanVien nhanVienDAO;
//		private ArrayList<ChiTietDichVu> dsChiTietDV;
//		private ArrayList<ChiTietHoaDon> dsChiTietHD;
//		
		btnQuayLai.addActionListener(this);
		btnXuatFile.addActionListener(this);
		hoaDonHienTai = hoaDon;
		loadTinHoaDon(hoaDonHienTai);
		String maHD = txtMaHoaDon.getText();
		dsChiTietDV = CTDVDAO.getALLChiTietDVTheoMaHD(maHD);
		dsChiTietHD = CTHDDAO.layDSTheoMaHD(maHD);
		loadDanhSachDichVuSuDung(dsChiTietDV, dsChiTietHD);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnQuayLai)) {
			setVisible(false);
		} else if (o.equals(btnXuatFile)) {
//			inHoaDon();
			String mahd = txtMaHoaDon.getText();
			int y = JOptionPane.showConfirmDialog(null, "bạn có muốn xem file", "mở file", JOptionPane.YES_NO_OPTION);
			if (y == JOptionPane.YES_OPTION) {
				openFile(mahd);
			}
		}

	}

	public void xoaALLDuLieuTable() {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	int stt = 1;

	public void loadDanhSachDichVuSuDung(ArrayList<ChiTietDichVu> dsCTDV, ArrayList<ChiTietHoaDon> dsCTHD) {
		xoaALLDuLieuTable();
		double tongTien = 0;
		for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
			double soGio = (double)chiTietHoaDon.getThoiLuong() / 60;
			double soGio2 =(double)Math.round(soGio * 100.0) / 100.0;
			double thanhTien = soGio2 * chiTietHoaDon.getPhong().getDonGia();
			
			tongTien += thanhTien;
			Object[] data = { stt, chiTietHoaDon.getPhong().getTenPhong(),soGio2,
					chiTietHoaDon.getPhong().getDonGia(), "phòng", (long)thanhTien };
			stt++;
			model.addRow(data);
		}
		for (ChiTietDichVu chiTietDichVu : dsCTDV) {
			double donGia = chiTietDichVu.getDichVu().getDonGia();
			double thanhTien = chiTietDichVu.getSoLuong() * donGia;
			tongTien += thanhTien;
			Object[] data = { stt, chiTietDichVu.getDichVu().getTenDichVu(), chiTietDichVu.getSoLuong(),
					chiTietDichVu.getDichVu().getDonGia(), chiTietDichVu.getDichVu().getDonVi(), thanhTien };
			stt++;
			model.addRow(data);
		}
		DecimalFormat df = new DecimalFormat("###,###,###");
		txtTongTien.setText(df.format(tongTien));
	}

	public void loadTinHoaDon(HoaDon hd) {
		KhachHang kh = khachHangDAO.timKhachHangTheoMa(hd.getMaKhachHang());
		NhanVien nv = nhanVienDAO.timNhanVienTheoMaNV(hd.getMaNhanVien());
		txtMaHoaDon.setText(hd.getMaHoaDon());
		txtNV.setText(nv.getTenNV());
		txtKH.setText(kh.getTenKH());
		txtSDT.setText(kh.getSdt());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ||HH:mm");
		
		txtNgayTao.setText(hd.getNgayTao().format(formatter));
	}



//	public boolean capNhapTrangThaiPhong(ArrayList<ChiTietHoaDon> dschiChiTietHD) {
//		ArrayList<Phong> dsPhongSD = new ArrayList<Phong>();
//		for (ChiTietHoaDon chiTietHoaDon : dschiChiTietHD) {
//			dsPhongSD.add(chiTietHoaDon.getPhong());
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
			// logo
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
//			String logoPath = "src/img/logo-Nice-karaoke.jpg";
//			Image img = Image.getInstance(logoPath);
//			Paragraph logo = new Paragraph();
//			logo.add(img);
//			img.setAlignment(Image.MIDDLE);
//			document.add(logo);
			
			String tenQuan = "KARAOKE NICE";
			Paragraph ten = new Paragraph(tenQuan, unicodeFontObject);
			ten.setAlignment(Element.ALIGN_CENTER);
			document.add(ten);
			String diaChi = "Nguyễn Văn Bảo Phường 4 Gò Vấp Thành phố Hồ Chí Minh\n";
			Paragraph dc = new Paragraph(diaChi, unicodeFontObject);
			dc.setAlignment(Element.ALIGN_CENTER);
			document.add(dc);
			Paragraph hoaDonThanhToan = new Paragraph("HÓA ĐƠN THANH TOÁN", new com.itextpdf.text.Font(unicodeFont,20,com.itextpdf.text.Font.BOLD));
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
			String nhanVien = "Nhân Viên: ..."+txtNV.getText();
			String kh = "Khách Hàng: ..."+txtKH.getText();
			Paragraph nv = new Paragraph(nhanVien, unicodeFontObject);
			nv.setAlignment(Element.ALIGN_LEFT);
			Paragraph kh1 = new Paragraph(kh, unicodeFontObject);
			kh1.setAlignment(Element.ALIGN_LEFT);
			document.add(nv);
			document.add(kh1);
			document.add(Chunk.NEWLINE);
			Paragraph dong2 = new Paragraph("Thông Tin Hóa Dơn*", new com.itextpdf.text.Font(unicodeFont,10,com.itextpdf.text.Font.BOLD));
			dong2.setAlignment(Element.ALIGN_CENTER);
			document.add(dong2);
			document.add(Chunk.NEWLINE);
			// tạo bảng
			PdfPTable table = new PdfPTable(5);
			table.setTotalWidth(new float[] { 70f, 100f, 60f, 50f, 70f });
			table.setWidthPercentage(100);
			// Thêm tiêu đề cho bảng
			table.addCell(new PdfPCell(new Phrase("Tên", unicodeFontObject)));
			table.addCell(new PdfPCell(new Phrase("Số lượng / thời lượng (phút)", unicodeFontObject)));
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
			String tongTien = txtTongTien.getText();
			String hoadon = "Tổng Tiên: " + tongTien ;
			Paragraph TongTienHd =  new Paragraph(hoadon,unicodeFontObject);
			TongTienHd.setAlignment(Element.ALIGN_RIGHT);
			document.add(TongTienHd);
			
			document.add(Chunk.NEWLINE);
			Paragraph dong3 = new Paragraph("\nXin Cảm Ơn", new com.itextpdf.text.Font(unicodeFont,10,com.itextpdf.text.Font.BOLD));
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
