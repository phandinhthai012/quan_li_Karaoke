package GUI;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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

public class TimKiemHoaDon extends JPanel implements ActionListener, MouseListener, KeyListener {
	private JTextField txtTimKiem;
	private JTable table;
	private JButton btnXemChiTiet;
	private DefaultTableModel model;
	private DAO_HoaDon hoaDonDAO;
	private DAO_KhachHang khachHangDAO;
	private DAO_NhanVien nhanVienDAO;
	private DAO_ChiTietDichVu CTDVDAO;
	private DAO_ChiTietHoaDon CTHDDAO;
	private ArrayList<HoaDon> dsHoaDon;

	/**
	 * Create the panel.
	 */
	public TimKiemHoaDon() {
		setLayout(null);
		setBounds(0, 0, 1540, 787);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1520, 767);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(10, 10, 1500, 149);
		panel.add(panel_1);

		JLabel lblNewLabel = new JLabel("Tìm kiếm hóa đơn");
		lblNewLabel.setIcon(new ImageIcon(
				"D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-package-search-60.png"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(607, 23, 272, 60);
		panel_1.add(lblNewLabel);

		JLabel lblNhapThongTin = new JLabel("Nhập thông tin tìm kiếm:");
		lblNhapThongTin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhapThongTin.setBounds(403, 100, 198, 25);
		panel_1.add(lblNhapThongTin);

		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(611, 100, 351, 25);
		panel_1.add(txtTimKiem);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBackground(SystemColor.menu);
		panel_1_1.setBounds(10, 600, 1540, 142);
		panel.add(panel_1_1);

		btnXemChiTiet = new JButton("   Xem chi tiết");
		btnXemChiTiet.setIcon(
				new ImageIcon("D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-seen-30.png"));
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXemChiTiet.setBackground(SystemColor.inactiveCaption);
		btnXemChiTiet.setBounds(575, 39, 365, 42);
		panel_1_1.add(btnXemChiTiet);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 169, 1500, 414);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã Hóa Đơn", "Ngày Tạo",
				"Tên Khách Hàng", "Tên Nhân viên", "Số Điện Thoại Khách Hàng", "Tiền thanh toán" }));
		TableColumn column1 = table.getColumnModel().getColumn(0);
		column1.setPreferredWidth(30); // Độ rộng 100 pixels

		model = (DefaultTableModel) table.getModel();
		hoaDonDAO = new DAO_HoaDon();
		nhanVienDAO = new DAO_NhanVien();
		khachHangDAO = new DAO_KhachHang();
		CTDVDAO = new DAO_ChiTietDichVu();
		CTHDDAO = new DAO_ChiTietHoaDon();
		docDulieuVaoBan();
		btnXemChiTiet.addActionListener(this);
		txtTimKiem.addKeyListener(this);
	}

	int stt = 1;

	public void xoaALLDuLieuTable() {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	public void docDulieuVaoBan() {
		xoaALLDuLieuTable();
		DecimalFormat df = new DecimalFormat("###,###,###");
		dsHoaDon = hoaDonDAO.getALLHoaDon();
		for (HoaDon hoaDon : dsHoaDon) {
			KhachHang kh = khachHangDAO.timKhachHangTheoMa(hoaDon.getMaKhachHang());
			NhanVien nv = nhanVienDAO.timNhanVienTheoMaNV(hoaDon.getMaNhanVien());

			ArrayList<ChiTietDichVu> dsChiTietDichVu = CTDVDAO.getALLChiTietDVTheoMaHD(hoaDon.getMaHoaDon());
			ArrayList<ChiTietHoaDon> dsChiTietHoaDon = CTHDDAO.layDSTheoMaHD(hoaDon.getMaHoaDon());
			long tongTien = 0;
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
			Object[] data = { stt, hoaDon.getMaHoaDon(), hoaDon.getNgayTao().toLocalDate(), kh.getTenKH(),
					nv.getTenNV(), kh.getSdt(),df.format(tongTien) };
			model.addRow(data);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXemChiTiet)) {
			int row = table.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
			}else {
				String maHoaDon = model.getValueAt(row, 1).toString();
				HoaDon hd = hoaDonDAO.timHoaDonTheoMaHD(maHoaDon);
//				ArrayList<ChiTietHoaDon> dsChiTietHoaDon = CTHDDAO.layDSTheoMaHD(hd.getMaHoaDon());
//				String maPhong = dsChiTietHoaDon.get(0).getPhong().getMaPhong();
//				GD_HoaDon frm = new GD_HoaDon(hd);
				GD_HoaDonXemChiTiet frm = new GD_HoaDonXemChiTiet(hd);
				frm.setVisible(true);
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		xoaALLDuLieuTable();
		ArrayList<HoaDon> dsTimKiem = hoaDonDAO.getDSTimKiemHoaDon(txtTimKiem.getText());
		for (HoaDon hoaDon : dsTimKiem) {
			KhachHang kh = khachHangDAO.timKhachHangTheoMa(hoaDon.getMaKhachHang());
			NhanVien nv = nhanVienDAO.timNhanVienTheoMaNV(hoaDon.getMaNhanVien());
			
				ArrayList<ChiTietDichVu> dsChiTietDichVu = CTDVDAO.getALLChiTietDVTheoMaHD(hoaDon.getMaHoaDon());
				ArrayList<ChiTietHoaDon> dsChiTietHoaDon = CTHDDAO.layDSTheoMaHD(hoaDon.getMaHoaDon());
				long tongTien = 0;
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
				Object[] data = { stt, hoaDon.getMaHoaDon(), hoaDon.getNgayTao().toLocalDate(), kh.getTenKH(),
						nv.getTenNV(), kh.getSdt(), (long)tongTien };
				model.addRow(data);
			
			stt++;
		}
	}
}
