package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_NhanVien;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class QLNhanVien extends JPanel implements ActionListener, MouseListener {
	private JTextField txtTenNhanVien;
	private JTextField txtMaNhanVien;
	private JTextField txtCMND;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTable table;
	private JRadioButton radNam, radNu;
	private ButtonGroup bgGroup;
	private JButton btnThem, btnSua, btnXoaTrang, btnLamMoi;
	private DefaultTableModel model;
	private ArrayList<NhanVien> dsNhanVien;
	private DAO_NhanVien nhanVienDAO;
	private JComboBox cmbMaNhanVien;
	private JComboBox cmbChucVu;

	/**
	 * Create the panel.
	 */
	public QLNhanVien() {
		setLayout(null);
		setBounds(0, 0, 1540, 787);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1554, 878);
		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(0, 0, 323, 783);
		panel.add(panel_1);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaNV.setBounds(10, 10, 151, 31);
		panel_1.add(lblMaNV);

		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setEditable(false);
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(20, 115, 274, 31);
		panel_1.add(txtTenNhanVien);

		JLabel lblTenNhanVien = new JLabel("Tên nhân viên:");
		lblTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenNhanVien.setBounds(10, 86, 281, 31);
		panel_1.add(lblTenNhanVien);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setColumns(10);
		txtMaNhanVien.setBounds(20, 45, 274, 31);
		panel_1.add(txtMaNhanVien);

		JLabel lblCMND = new JLabel("CMND:");
		lblCMND.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCMND.setBounds(10, 156, 281, 31);
		panel_1.add(lblCMND);

		txtCMND = new JTextField();
		txtCMND.setEditable(false);
		txtCMND.setColumns(10);
		txtCMND.setBounds(20, 190, 274, 31);
		panel_1.add(txtCMND);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSDT.setBounds(10, 231, 281, 31);
		panel_1.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setColumns(10);
		txtSDT.setBounds(20, 272, 274, 31);
		panel_1.add(txtSDT);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDiaChi.setBounds(10, 327, 281, 31);
		panel_1.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(20, 368, 274, 31);
		panel_1.add(txtDiaChi);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGioiTinh.setBounds(10, 405, 71, 31);
		panel_1.add(lblGioiTinh);

		radNam = new JRadioButton("Nam");
		radNam.setFont(new Font("Tahoma", Font.BOLD, 15));
		radNam.setBounds(87, 434, 71, 21);
		panel_1.add(radNam);

		radNu = new JRadioButton("Nữ");
		radNu.setFont(new Font("Tahoma", Font.BOLD, 15));
		radNu.setBounds(211, 434, 103, 21);
		panel_1.add(radNu);

		bgGroup = new ButtonGroup();
		bgGroup.add(radNam);
		bgGroup.add(radNu);
		radNam.setSelected(true);

		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChucVu.setBounds(10, 461, 281, 31);
		panel_1.add(lblChucVu);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(QLNhanVien.class.getResource("/img/icons8-new-copy-30.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThem.setBackground(Color.WHITE);
		btnThem.setBounds(23, 561, 117, 42);
		panel_1.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(QLNhanVien.class.getResource("/img/icons8-fix-30.png")));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSua.setBackground(Color.WHITE);
		btnSua.setBounds(162, 561, 110, 42);
		panel_1.add(btnSua);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon(QLNhanVien.class.getResource("/img/icons8-delete-30.png")));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoaTrang.setBackground(Color.WHITE);
		btnXoaTrang.setBounds(23, 638, 249, 42);
		panel_1.add(btnXoaTrang);

		String chucVu[] = { "Nhân viên", "Quản Lí" };
		cmbChucVu = new JComboBox(chucVu);
		cmbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbChucVu.setBounds(10, 496, 284, 31);

		panel_1.add(cmbChucVu);

		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBackground(Color.WHITE);
		btnLamMoi.setBounds(23, 691, 249, 42);
		panel_1.add(btnLamMoi);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(338, 10, 1189, 163);
		panel.add(panel_2);

		JLabel lblDanhSachNhanVien = new JLabel("Danh sách nhân viên");
		lblDanhSachNhanVien.setIcon(new ImageIcon(QLNhanVien.class.getResource("/img/icons8-employee-90.png")));
		lblDanhSachNhanVien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblDanhSachNhanVien.setBounds(50, 45, 330, 68);
		panel_2.add(lblDanhSachNhanVien);

		JLabel lblTimKiemNV = new JLabel("Tìm kiếm nhân viên:");
		lblTimKiemNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTimKiemNV.setBounds(672, 45, 171, 32);
		panel_2.add(lblTimKiemNV);

		cmbMaNhanVien = new JComboBox();
		cmbMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbMaNhanVien.setBounds(828, 45, 214, 32);
		panel_2.add(cmbMaNhanVien);

		JPanel pnlTable = new JPanel();
		pnlTable.setBounds(338, 198, 1189, 579);
		panel.add(pnlTable);
		pnlTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 1154, 527);
		pnlTable.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn", "CMND",
						"S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "\u0110\u1ECBa Ch\u1EC9", "Gi\u1EDBi T\u00EDnh",
						"Ch\u1EE9c V\u1EE5" }));

		model = (DefaultTableModel) table.getModel();
		nhanVienDAO = new DAO_NhanVien();
		docDuLieuVaoBang();
		docMaNhanVienVaoCMB();
		table.addMouseListener(this);
		btnXoaTrang.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		cmbMaNhanVien.addActionListener(this);
	}

	public void xoaTrang() {
		txtMaNhanVien.setText("");
		txtTenNhanVien.setText("");
		txtCMND.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		radNam.setSelected(true);
		cmbChucVu.setSelectedIndex(0);
		txtTenNhanVien.requestFocus();
	}

	public void xoaALLDuLieuTable() {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	public void docDuLieuVaoBang() {
		dsNhanVien = nhanVienDAO.getALLNhanVien();
		if (dsNhanVien == null) {
			JOptionPane.showMessageDialog(this, "Không có dữ liệu Nhân Viên!!");
			return;
		} else {
			xoaALLDuLieuTable();

			for (NhanVien nhanVien : dsNhanVien) {
				String chucVu = "Quản Lí";
				if (nhanVien.getChucVu().equalsIgnoreCase("nv")) {
					chucVu = "Nhân Viên";
				}
				String gioiTinh = "Nam";
				if (!nhanVien.isGioiTinh()) {
					gioiTinh = "Nữ";
				}
				model.addRow(new Object[] { nhanVien.getMaNV(), nhanVien.getTenNV(), nhanVien.getCmnd(),
						nhanVien.getSdt(), nhanVien.getDiaChi(), gioiTinh, chucVu });
			}
		}
	}

	public void docMaNhanVienVaoCMB() {
		cmbMaNhanVien.removeAllItems();
		cmbMaNhanVien.addItem("");
		for (NhanVien nhanVien : dsNhanVien) {
			cmbMaNhanVien.addItem(nhanVien.getMaNV());
		}
	}

	public int timViTriNhanVien(String manv) {
		for (int i = 0; i < dsNhanVien.size(); i++) {
			if (dsNhanVien.get(i).getMaNV().equalsIgnoreCase(manv)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNhanVien.setText(model.getValueAt(row, 0).toString());
		txtTenNhanVien.setText(model.getValueAt(row, 1).toString());
		txtCMND.setText(model.getValueAt(row, 2).toString());
		txtSDT.setText(model.getValueAt(row, 3).toString());
		txtDiaChi.setText(model.getValueAt(row, 4).toString());
		String chucVu = model.getValueAt(row, 6).toString();
		String gioiTinh = model.getValueAt(row, 5).toString();
		if (gioiTinh.equalsIgnoreCase("Nữ")) {
			radNu.setSelected(true);
		} else {
			radNam.setSelected(true);
		}
		for (int i = 0; i < cmbChucVu.getItemCount(); i++) {
			if (chucVu.equalsIgnoreCase(cmbChucVu.getItemAt(i).toString())) {
				cmbChucVu.setSelectedIndex(i);
				return;
			}
		}

	}

	public boolean themNhanVien() {
		String manv = txtMaNhanVien.getText();
		String tenNhanVien = txtTenNhanVien.getText();
		String cmnd = txtCMND.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		boolean gt = true;
		String gt1 = "Nam";

		String chucVu = "NV";
		if (radNu.isSelected()) {
			gt = false;
			gt1 = "Nữ";
		}
		String cv = (String) cmbChucVu.getItemAt(cmbChucVu.getSelectedIndex());
		if (cv.equals("Quản Lí")) {
			chucVu = "QL";
		}
		NhanVien nv = new NhanVien(manv, tenNhanVien, cmnd, sdt, diaChi, gt, chucVu);
		if (nhanVienDAO.themNhanVien(nv)) {
			dsNhanVien.add(nv);
			cmbMaNhanVien.addItem(manv);
			Object[] data = { manv, tenNhanVien, cmnd, sdt, diaChi, gt1, cv };
			model.addRow(data);
			return true;
		}
		return false;
	}

	public NhanVien timNhanVienTheoMa(String manv) {
		NhanVien x = null;
		ArrayList<NhanVien> ds = nhanVienDAO.getALLNhanVien();
		for (int i = 0; i < ds.size(); i++) {
			if (ds.get(i).getMaNV().equals(manv)) {
				x = ds.get(i);
			}
		}
		return x;
	}

	public boolean capNhapThongTinNhanVien(NhanVien x) {
		if (nhanVienDAO.capNhapNhanVien(x)) {
			JOptionPane.showMessageDialog(this, "cập nhập thông tin nhân viên thành công");
			return true;
		} else {
			thongBaoLoi("lỗi cập nhập", "lỗi");
			return false;
		}
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnLamMoi)) {
			xoaALLDuLieuTable();
			docDuLieuVaoBang();
		} else {
			if (o.equals(btnThem)) {
//			if (btnThem.getText().equals("Thêm")) {
//				xoaTrang();
//				btnThem.setText("Lưu");
//				String manv = taoMaNhanVien();
//				txtMaNhanVien.setText(manv);
//				txtTenNhanVien.setEditable(true);
//				txtCMND.setEditable(true);
//				txtSDT.setEditable(true);
//				txtDiaChi.setEditable(true);
//			} else if (btnThem.getText().equals("Lưu")) {
//				if (valiData()) {
//					if (themNhanVien()) {
//						xoaTrang();
//						btnThem.setText("Thêm");
//						txtTenNhanVien.setEditable(false);
//						txtDiaChi.setEditable(false);
//						txtSDT.setEditable(false);
//						txtCMND.setEditable(false);
//						cmbChucVu.setSelectedIndex(0);
//						JOptionPane.showMessageDialog(this, "Thêm Thành Công 1 Nhân viên");
//					} else {
//						JOptionPane.showMessageDialog(this, "Trùng Mã Nhân Viên");
//					}
//				}
//
//			}
				String maNVMoi = taoMaNhanVien();
				dialogThemNhanVien dialogThem = new dialogThemNhanVien(maNVMoi);
				dialogThem.setVisible(true);
			} else if (o.equals(cmbMaNhanVien)) {
				String maKH = cmbMaNhanVien.getItemAt(cmbMaNhanVien.getSelectedIndex()).toString();
				int i = timViTriNhanVien(maKH);
				if (i >= 0) {
					table.setRowSelectionInterval(i, i);
					int row = table.getSelectedRow();
					txtMaNhanVien.setText(model.getValueAt(row, 0).toString());
					txtTenNhanVien.setText(model.getValueAt(row, 1).toString());
					txtCMND.setText(model.getValueAt(row, 2).toString());
					txtSDT.setText(model.getValueAt(row, 3).toString());
					txtDiaChi.setText(model.getValueAt(row, 4).toString());
					String chucVu = model.getValueAt(row, 6).toString();
					String gioiTinh = model.getValueAt(row, 5).toString();
					if (gioiTinh.equalsIgnoreCase("Nữ")) {
						radNu.setSelected(true);
					} else {
						radNam.setSelected(true);
					}
					for (int j = 0; j < cmbChucVu.getItemCount(); j++) {
						if (chucVu.equals(cmbChucVu.getItemAt(j).toString())) {
							cmbChucVu.setSelectedIndex(j);
							break;
						}
					}
				}
			} else if (o.equals(btnSua)) {
				if (btnSua.getText().equals("Sửa")) {
					btnSua.setText("Lưu");
					txtTenNhanVien.setEditable(true);
					txtDiaChi.setEditable(true);
					txtSDT.setEditable(true);
					txtCMND.setEditable(true);
				} else if (btnSua.getText().equals("Lưu")) {
					if (txtMaNhanVien.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên để sửa!");
						txtTenNhanVien.setEditable(false);
						txtDiaChi.setEditable(false);
						txtSDT.setEditable(false);
						txtCMND.setEditable(false);
						cmbChucVu.setSelectedIndex(0);
						radNam.setSelected(true);
						btnSua.setText("Sửa");
					} else {
						if (valiData()) {
							String manv = txtMaNhanVien.getText();
							NhanVien x = timNhanVienTheoMa(manv);
							String tenNhanVien = txtTenNhanVien.getText();
							String cmnd = txtCMND.getText();
							String sdt = txtSDT.getText();
							String diaChi = txtDiaChi.getText();
							boolean gt = true;
							String gt1 = "Nam";
							String chucVu = "NV";
							if (radNu.isSelected()) {
								gt = false;
								gt1 = "Nữ";
							}
							String cv = (String) cmbChucVu.getItemAt(cmbChucVu.getSelectedIndex());
							if (cv.equalsIgnoreCase("Quản Lí")) {
								chucVu = "QL";
							}
							x.setTenNV(tenNhanVien);
							x.setChucVu(chucVu);
							x.setGioiTinh(gt);
							x.setCmnd(cmnd);
							x.setDiaChi(diaChi);
							x.setSdt(sdt);
							if (x != null) {
								if (capNhapThongTinNhanVien(x)) {
									xoaTrang();
									xoaALLDuLieuTable();
									docDuLieuVaoBang();
									txtTenNhanVien.setEditable(false);
									txtDiaChi.setEditable(false);
									txtSDT.setEditable(false);
									txtCMND.setEditable(false);
									cmbChucVu.setSelectedIndex(0);
									radNam.setSelected(true);
									btnSua.setText("Sửa");
								}
							}
							
						}

					}

				}
			}
		}
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * Tạo số cuối của mã mới không trùng với các mã đã có trong table. Lưu ý: Cần
	 * sắp xếp mã trên table theo thứ tự tăng dần.
	 * 
	 * @return Số cuối của mả mới.
	 */
	private String taoMaNhanVien() {
		ArrayList<NhanVien> ds = nhanVienDAO.getALLNhanVien();
		int i = 0; // thứ tự hàng
		while (i < ds.size()) {
			// Lấy mã ở hàng thứ i trong table bỏ "NV" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(ds.get(i).getMaNV().substring(2)) == (i + 1))
				// nếu mã khớp thì tăng i lên để xét hàng tiếp theo.
				i++;
			else
				// nếu mã không khớp thì thoát vòng lập
				break;
		}
		// i ra khỏi vòng lập là hàng có mã không khớp hoặc bằng với số hàng, ta
		// chỉ cần + 1 để có số cuối của mã mới.
		i++;
		String maNV = "NV";
		if (i < 10) {
			maNV += "00" + String.valueOf(i);
		} else if (i < 100) {
			maNV += "0" + String.valueOf(i);
		} else if (i < 1000) {
			maNV += String.valueOf(i);
		}
		return maNV;
	}

	public boolean valiData() {
		String tenNV = txtTenNhanVien.getText();
		String cmnd = txtCMND.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		if (tenNV.length() == 0) {
			showMessage(txtTenNhanVien, "Nhập Tên Nhân Viên!");
			return false;
		}
		if (!tenNV.matches("" + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
				+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
				+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
			showMessage(txtTenNhanVien,
					"Tên Nhân viên bao gồm chứ cái, chữ số tiếng việt, không bao gồm kí tự đặc biệt!");
			return false;
		}
		if (cmnd.length() == 0) {
			showMessage(txtCMND, "Nhập số Chứng Minh Nhân Dân!");
			return false;
		}
		if (!cmnd.matches("^\\\\d{9,10}$") && Integer.parseInt(cmnd) < 0) {
			showMessage(txtCMND, "CMND là số nguyên (9-10 số)");
			return false;
		}
		if (sdt.length() == 0) {
			showMessage(txtSDT, "Nhập số điện thoại!");
			return false;
		}
		if (!sdt.matches("^0\\d{9}$")) {
			showMessage(txtSDT, "Số điện thoại là số nguyên (10 số, số đầu tiên là số 0)");
			return false;
		}
		if (diaChi.length() == 0) {
			showMessage(txtDiaChi, "Nhập địa chỉ!");
			return false;
		}
		return true;
	}
}
