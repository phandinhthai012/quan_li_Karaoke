package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_CTPDDichVu;
import DAO.DAO_ChiTietDichVu;
import DAO.DAO_DichVu;
import DAO.DAO_HoaDon;
import DAO.DAO_KhachHang;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatDichVu;
import entity.DichVu;
import entity.HoaDon;
import entity.Phong;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class GD_DatDV extends JFrame implements ActionListener, MouseListener {
	/**
	 * đổi sang dialog
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenDV;
	private JTable table;
	private JTextField txtMaPhieu;
	private JTextField txtMaKH;
	private JTable table_1;
	private JTextField txtTongTien;
	private JButton btnThem, btnThoat, btnCapNhap, btnXoa, btnSua, btnTim, btnLamMoi;

	private ArrayList<DichVu> dsDV;
	private DAO_DichVu dichVuDAO;
	private DAO_KhachHang khachHangDAO;
	private DAO_HoaDon hoaDonDAO;
	private DAO_ChiTietDichVu chitietDVDAO;
	private JComboBox comboBox, cmbMaDv;
	private DAO_CTPDDichVu CTPDDVDao;

	private DefaultTableModel model1, model2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GD_DatDV frame = new GD_DatDV();
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
	public GD_DatDV(String maPhieu, String maKH) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(1200, 700);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(0, 0, 1186, 84);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ĐẶT DỊCH VỤ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(506, 10, 162, 45);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin d\u1ECBch v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(0, 88, 561, 138);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMaDichVu = new JLabel("Mã Dịch Vụ: ");
		lblMaDichVu.setBounds(10, 34, 90, 23);
		panel_1.add(lblMaDichVu);

		JLabel lblNewLabel_1_1 = new JLabel("Tên dịch vụ:");
		lblNewLabel_1_1.setBounds(10, 90, 90, 23);
		panel_1.add(lblNewLabel_1_1);

		txtTenDV = new JTextField();
		txtTenDV.setBounds(104, 92, 198, 19);
		panel_1.add(txtTenDV);
		txtTenDV.setColumns(10);

		btnTim = new JButton("Tìm");
		btnTim.setBackground(SystemColor.window);
		btnTim.setBounds(312, 91, 72, 21);
		panel_1.add(btnTim);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(SystemColor.window);
		btnLamMoi.setBounds(394, 91, 90, 21);
		panel_1.add(btnLamMoi);

		cmbMaDv = new JComboBox();
		cmbMaDv.setBounds(104, 35, 198, 21);
		panel_1.add(cmbMaDv);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1_1.setBounds(571, 88, 615, 138);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("Mã phiếu:");
		lblNewLabel_1_2.setBounds(10, 34, 90, 23);
		panel_1_1.add(lblNewLabel_1_2);

		txtMaPhieu = new JTextField();
		txtMaPhieu.setEditable(false);
		txtMaPhieu.setColumns(10);
		txtMaPhieu.setBounds(134, 36, 138, 19);
		panel_1_1.add(txtMaPhieu);

		JLabel lblNewLabel_1_2_1 = new JLabel("Mã Khách Hàng:");
		lblNewLabel_1_2_1.setBounds(10, 90, 114, 23);
		panel_1_1.add(lblNewLabel_1_2_1);

		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(134, 92, 138, 19);
		panel_1_1.add(txtMaKH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 230, 560, 305);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "M\u00E3 DV", "T\u00EAn DV", "Gi\u00E1", "\u0110\u01A1n v\u1ECB", "T\u1ED3n kho" }));
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(0, 548, 561, 105);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(GD_DatDV.class.getResource("/img/icons8-add-new-30.png")));
		btnThem.setBackground(SystemColor.window);
		btnThem.setBounds(197, 24, 133, 33);
		panel_2.add(btnThem);

		btnThoat = new JButton("Thoát");
		btnThoat.setIcon(new ImageIcon(GD_DatDV.class.getResource("/img/exit.png")));
		btnThoat.setBackground(SystemColor.window);
		btnThoat.setBounds(10, 62, 120, 33);
		panel_2.add(btnThoat);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(571, 230, 605, 305);
		contentPane.add(scrollPane_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane_2);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "STT", "Mã DV", "T\u00EAn DV", "Gi\u00E1", "\u0110\u00E3 th\u00EAm" }));
		scrollPane_2.setViewportView(table_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(SystemColor.inactiveCaptionBorder);
		panel_3.setBounds(571, 548, 615, 105);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(GD_DatDV.class.getResource("/img/icons8-fix-30.png")));
		btnSua.setBackground(SystemColor.window);
		btnSua.setBounds(10, 26, 121, 33);
		panel_3.add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(GD_DatDV.class.getResource("/img/icons8-delete-30.png")));
		btnXoa.setBackground(SystemColor.window);
		btnXoa.setBounds(153, 26, 121, 33);
		panel_3.add(btnXoa);

		JLabel lblNewLabel_2 = new JLabel("Tổng tiền:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(424, 10, 84, 13);
		panel_3.add(lblNewLabel_2);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(434, 33, 138, 19);
		panel_3.add(txtTongTien);

		btnCapNhap = new JButton("Cập nhập");
		btnCapNhap.setIcon(new ImageIcon(GD_DatDV.class.getResource("/img/icons8-update-30.png")));
		btnCapNhap.setBackground(Color.WHITE);
		btnCapNhap.setBounds(284, 26, 128, 33);
		panel_3.add(btnCapNhap);

		txtMaPhieu.setText(maPhieu);
		txtMaKH.setText(maKH);

		dichVuDAO = new DAO_DichVu();
		khachHangDAO = new DAO_KhachHang();
		hoaDonDAO = new DAO_HoaDon();
		chitietDVDAO = new DAO_ChiTietDichVu();
		model1 = (DefaultTableModel) table.getModel();
		model2 = (DefaultTableModel) table_1.getModel();
		dsDV = new ArrayList<DichVu>();
		CTPDDVDao = new DAO_CTPDDichVu();
		loadDsDichVu();

		btnLamMoi.addActionListener(this);
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnCapNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		xoaALLDuLieuTable1(model2);
		loadCmbMaDV(dsDV);

	}

	public void xoaALLDuLieuTable1(DefaultTableModel model) {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	public void loadDsDichVu() {
		dsDV = dichVuDAO.getALLDichVu();
		if (dsDV == null) {
			JOptionPane.showMessageDialog(this, "Không có dữ liệu!");
			return;
		}
		xoaALLDuLieuTable1(model1);
		for (DichVu dichVu : dsDV) {
			Object[] data = { dichVu.getMaDV(), dichVu.getTenDichVu(), dichVu.getDonGia(), dichVu.getDonVi(),
					dichVu.getSoLuongTonKho() };
			model1.addRow(data);
		}
	}

	public void loadCmbMaDV(ArrayList<DichVu> ds) {
		for (DichVu dichVu : ds) {
			cmbMaDv.addItem(dichVu.getMaDV());
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

	int stt = 1;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnLamMoi)) {
			txtTenDV.setText("");
			xoaALLDuLieuTable1(model1);
			loadDsDichVu();
//			JOptionPane.showInputDialog("Nhập Số Lượng:");
		} else if (o.equals(btnTim)) {
			String tenDv = txtTenDV.getText();

			ArrayList<DichVu> dsTimKiem = timKiemDichVu1(tenDv);
			loadDsTimKiem(dsTimKiem);
		} else if (o.equals(btnThem)) {
			int chon = table.getSelectedRow();
			if (chon < 0) {
				JOptionPane.showMessageDialog(null, "Ban chưa chọn dịch vụ");
			} else {
				String slTon = model1.getValueAt(chon, 4).toString();
				int soLuongTon = Integer.parseInt(slTon);

				if (soLuongTon <= 0) {
					JOptionPane.showMessageDialog(null, "dịch vụ này tạm thời hết, chọn dịch vụ khác!");
				} else {
					String sluong = JOptionPane.showInputDialog("Nhập số lượng");
					int soLuongSD = Integer.parseInt(sluong);
					if (soLuongSD > soLuongTon) {
						// kiểm tra số lượng trong kho có đủ cho khách hàng hay không
						JOptionPane.showMessageDialog(null, "Số lượng không đủ!");
					} else {
						soLuongTon = soLuongTon - soLuongSD;
						String tendv = model1.getValueAt(chon, 1).toString();
						String gia = model1.getValueAt(chon, 2).toString();
						String madv = model1.getValueAt(chon, 0).toString();
						Object[] data = { stt, madv, tendv, gia, soLuongSD, };
						stt++;
						model2.addRow(data);
						capNhapTien();
					}

				}
			}

		} else if (o.equals(btnSua)) {
			int chon = table_1.getSelectedRow();
			if (chon < 0) {
				JOptionPane.showMessageDialog(null, "Ban chưa chọn dịch vụ để sửa");
			} else {
				String slTon = model1.getValueAt(chon, 4).toString();
				int soLuongTon = Integer.parseInt(slTon);
				String slMoi = JOptionPane.showInputDialog("Nhập lại số Lương ");
				int soLuongMoi = Integer.parseInt(slMoi);
				if (soLuongMoi > soLuongTon) {
					JOptionPane.showMessageDialog(null, "Số lượng không đủ!");
				} else {
					model2.setValueAt(slMoi, chon, 4);
				}
			}

		} else if (o.equals(btnXoa)) {
			int row = table_1.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "Ban chưa chọn dịch vụ để xóa");
			} else {
				int thongBao = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa", "Xóa phòng",
						JOptionPane.YES_NO_OPTION);
				if (thongBao == JOptionPane.YES_OPTION) {
					model2.removeRow(row);
				}
			}
		} else if (o.equals(btnCapNhap)) {
			String maPhieu = txtMaPhieu.getText();
			String tenKh = txtMaKH.getText();
			ArrayList<ChiTietPhieuDatDichVu> dsct = CTPDDVDao.getDSPhieuDatDVtheoMaPhieuDat(maPhieu);
			if(dsct ==null) {
				if (taoPhieuDatDv(maPhieu)) {
					JOptionPane.showMessageDialog(null, "Tạo Thành công dịch vụ");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Lỗi", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				for(int i = 0; i<model2.getRowCount(); i++) {
					/**
					 * tìm ctpddv nếu có thì cập nhập lại số lương 
					 * nếu không có thì tạo mới viết dao cập nhập số lương, viết dao lấy ctpdvdv theo maphieu+madv
					 */
					String madv = model2.getValueAt(i, 1).toString();
					ChiTietPhieuDatDichVu x = CTPDDVDao.getChiTietPhieuDatDv(maPhieu, madv);
					if(x!=null) {
						// đã có phiếu đặt dịch vu
						int sluonThem = Integer.parseInt(model2.getValueAt(	i, 4).toString());
						int soluongMoi = sluonThem+ x.getSoLuong();
						DichVu dv = dichVuDAO.timDVTheoMaDV(madv);
						int sluongTonKhoMoi = dv.getSoLuongTonKho() - sluonThem;
						if (!dichVuDAO.capNhapSoLuongTonDV(madv, sluongTonKhoMoi)) {
							JOptionPane.showMessageDialog(null, "lỗi cập nhập lại dịch vụ!");
							return;
						}
						if(!CTPDDVDao.capNhapSoLuongDichVu(maPhieu, madv, soluongMoi)) {
							thongBaoLoi("Lỗi cập nhập số lương dịch vu", "lỗi");
							return;
						}
					}
					else {
						// truownhf hopwk chưa có ctpddv
						String madv1 = model2.getValueAt(i, 1).toString();
						int soluong = Integer.parseInt(model2.getValueAt(i, 4).toString());
						ChiTietPhieuDatDichVu pdDV = new ChiTietPhieuDatDichVu(maPhieu, madv, soluong);
						DichVu dv = dichVuDAO.timDVTheoMaDV(madv);
						int soluongTonMoi = dv.getSoLuongTonKho() - soluong;
						if (!CTPDDVDao.themChiTietDatDV(pdDV)) {
							JOptionPane.showMessageDialog(null, "lỗi thêm chi tiết dv!");
							return;
						}
						if (!dichVuDAO.capNhapSoLuongTonDV(madv, soluongTonMoi)) {
							JOptionPane.showMessageDialog(null, "lỗi cập nhập lại dịch vụ!");
							return;
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Tạo Thành công dịch vụ");
				dispose();
			}
			
		} else if (o.equals(btnThoat)) {
			dispose();
		}
		else if(o.equals(cmbMaDv)) {
			
		}
	}


	public boolean taoPhieuDatDv(String maPhieu) {
		for (int i = 0; i < model2.getRowCount(); i++) {
			String madv = model2.getValueAt(i, 1).toString();
			int soluong = Integer.parseInt(model2.getValueAt(i, 4).toString());
			ChiTietPhieuDatDichVu pdDV = new ChiTietPhieuDatDichVu(maPhieu, madv, soluong);
			DichVu dv = dichVuDAO.timDVTheoMaDV(madv);
			int soluongTonMoi = dv.getSoLuongTonKho() - soluong;
			if (!CTPDDVDao.themChiTietDatDV(pdDV)) {
				JOptionPane.showMessageDialog(null, "lỗi thêm chi tiết dv!");
				return false;
			}
			if (!dichVuDAO.capNhapSoLuongTonDV(madv, soluongTonMoi)) {
				JOptionPane.showMessageDialog(null, "lỗi cập nhập lại dịch vụ!");
				return false;
			}
		}
		return true;
	}
	/**
	 * hiển thị thông tin tổng tiền dihcj vụ mà khách hàng chọn
	 */
	private void capNhapTien() {
		DefaultTableModel modeltinhTien = (DefaultTableModel) table_1.getModel();
		int soLuongDong = modeltinhTien.getRowCount();
		double tongTien = 0.0;
		for (int i = 0; i < soLuongDong; i++) {
			String dg = modeltinhTien.getValueAt(i, 3).toString();
			String sl = modeltinhTien.getValueAt(i, 4).toString();
			double donGia = Double.parseDouble(dg);
			int soLuong = Integer.parseInt(sl);
			double tienDV = donGia * soLuong;
			tongTien += tienDV;
		}
		txtTongTien.setText(String.valueOf(tongTien));
	}

	public ArrayList<DichVu> timKiemDichVu1(String tenDV) {
		ArrayList<DichVu> dsTimKiem = dichVuDAO.search(tenDV);
		return dsTimKiem;
	}
	/**
	 * load ds dịch vụ mà khách hàng dang tìm kiếm ()nhập thông tin liên qquan đển dv muốn tìm
	 * @param dsTimKiemDV
	 */
	public void loadDsTimKiem(ArrayList<DichVu> dsTimKiemDV) {
		if (dsDV == null) {
			JOptionPane.showMessageDialog(this, "Không có dữ liệu");
			return;
		}
		xoaALLDuLieuTable1(model1);
		for (DichVu dichVu : dsTimKiemDV) {
			Object[] data = { dichVu.getMaDV(), dichVu.getTenDichVu(), dichVu.getDonGia(), dichVu.getDonVi(),
					dichVu.getSoLuongTonKho() };
			model1.addRow(data);
		}
	}
	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}
}
