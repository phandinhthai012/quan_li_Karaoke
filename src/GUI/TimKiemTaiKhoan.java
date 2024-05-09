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
import java.awt.Choice;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_TaiKhoan;
import entity.NhanVien;

public class TimKiemTaiKhoan extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMatKhau;
	private JTextField txtTenTaiKhoan;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<NhanVien> dsNhanVien;
	private DAO_TaiKhoan taiKhoanDAO;
	private JComboBox cmbMaNhanVien;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu;

	/**
	 * Create the panel.
	 * 
	 * sửa lại
	 */
	public TimKiemTaiKhoan() {
		setLayout(null);
		setBounds(0, 0, 1540, 787);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 1530, 845);
		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(10, 10, 267, 728);
		panel.add(panel_1);

		JLabel lblTenTaiKhoan = new JLabel("Tên tài khoản:");
		lblTenTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenTaiKhoan.setBounds(20, 109, 114, 26);
		panel_1.add(lblTenTaiKhoan);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatKhau.setBounds(20, 223, 114, 26);
		panel_1.add(lblMatKhau);

		txtMatKhau = new JTextField();
		txtMatKhau.setEditable(false);
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(20, 266, 221, 26);
		panel_1.add(txtMatKhau);

		txtTenTaiKhoan = new JTextField();
		txtTenTaiKhoan.setEditable(false);
		txtTenTaiKhoan.setColumns(10);
		txtTenTaiKhoan.setBounds(20, 145, 221, 26);
		panel_1.add(txtTenTaiKhoan);

//		Choice choice = new Choice();
//		choice.setBounds(20, 434, 221, 18);
//		panel_1.add(choice);
		cmbMaNhanVien = new JComboBox();
		cmbMaNhanVien.setBounds(20, 434, 221, 18);
		panel_1.add(cmbMaNhanVien);

		JLabel lblMaChuTaiKhoan = new JLabel("Mã chủ tài khoản:");
		lblMaChuTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaChuTaiKhoan.setBounds(20, 385, 156, 26);
		panel_1.add(lblMaChuTaiKhoan);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(287, 10, 1229, 83);
		panel.add(panel_2);

		JLabel lblDanhSachTaiKhoan = new JLabel("Danh sách tài khoản");
		lblDanhSachTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDanhSachTaiKhoan.setBounds(54, 30, 314, 43);
		panel_2.add(lblDanhSachTaiKhoan);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(SystemColor.menu);
		panel_3.setBounds(287, 576, 1229, 165);
		panel.add(panel_3);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThem.setBackground(Color.WHITE);
		btnThem.setBounds(160, 45, 169, 44);
		panel_3.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoa.setBackground(Color.WHITE);
		btnXoa.setBounds(574, 44, 169, 46);
		panel_3.add(btnXoa);

		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLuu.setBackground(Color.WHITE);
		btnLuu.setBounds(360, 44, 169, 46);
		panel_3.add(btnLuu);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoaTrang.setBackground(Color.WHITE);
		btnXoaTrang.setBounds(777, 44, 169, 47);
		panel_3.add(btnXoaTrang);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(287, 103, 1229, 463);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Tên Tài Khoản", "Mã Chủ Tài Khoản" }));

		model = (DefaultTableModel) table.getModel();
		taiKhoanDAO = new DAO_TaiKhoan();
		docDuLieuVaoBang();
		docMaNhanVienVaoCMB();
		table.addMouseListener(this);
		cmbMaNhanVien.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(this);
	}

	public void docMaNhanVienVaoCMB() {
		cmbMaNhanVien.removeAllItems();
		cmbMaNhanVien.addItem("");
		for (NhanVien nhanVien : dsNhanVien) {
			cmbMaNhanVien.addItem(nhanVien.getMaNV());
		}
	}

	public void xoaALLDuLieuTable() {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	public void docDuLieuVaoBang() {
		dsNhanVien = taiKhoanDAO.getALLNhanVien();
		if (dsNhanVien == null) {
			JOptionPane.showMessageDialog(this, "Không có dữ liệu Nhân Viên!!");
			return;
		} else {
			xoaALLDuLieuTable();
			for (NhanVien nhanVien : dsNhanVien) {
				model.addRow(new Object[] { nhanVien.getTaiKhoan(), nhanVien.getMaNV() });
			}
		}
	}

	public void xoaTrang() {
		txtTenTaiKhoan.setText("");
		txtMatKhau.setText("");
		cmbMaNhanVien.setSelectedIndex(0);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		ArrayList<NhanVien> ds = taiKhoanDAO.getALLNhanVien();
		int row = table.getSelectedRow();

		String maNV = model.getValueAt(row, 1).toString();
		NhanVien nv = taiKhoanDAO.timNhanVienTheoMaNV(maNV);

//		txtTenTaiKhoan.setText(model.getValueAt(row, 0).toString());
//		txtMatKhau.setText(nv.getMatKhau());
		if (!(nv.getMatKhau() == null || nv.getTaiKhoan() == null)) {
			txtTenTaiKhoan.setText(nv.getTaiKhoan());
			txtMatKhau.setText(nv.getMatKhau());
		} else {
			txtTenTaiKhoan.setText("");
			txtMatKhau.setText("");
		}

		for (int i = 0; i < cmbMaNhanVien.getItemCount(); i++) {
			if (maNV.equalsIgnoreCase(cmbMaNhanVien.getItemAt(i).toString())) {
				cmbMaNhanVien.setSelectedIndex(i);
				return;
			}
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

	public int timViTri(String maNV) {
		for (int i = 0; i < dsNhanVien.size(); i++) {
			if (dsNhanVien.get(i).getMaNV().equalsIgnoreCase(maNV)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(cmbMaNhanVien)) {
			String maNV = cmbMaNhanVien.getItemAt(cmbMaNhanVien.getSelectedIndex()).toString();
			int viTri = timViTri(maNV);
			NhanVien nv = taiKhoanDAO.timNhanVienTheoMaNV(maNV);
			if (viTri >= 0) {
				table.setRowSelectionInterval(viTri, viTri);
				if (!(nv.getMatKhau() == null || nv.getTaiKhoan() == null)) {
					txtTenTaiKhoan.setText(nv.getTaiKhoan());
					txtMatKhau.setText(nv.getMatKhau());
				} else {
					txtTenTaiKhoan.setText("");
					txtMatKhau.setText("");
				}
				int row = table.getSelectedRow();

			}

		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnThem)) {
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên");
			} else {
				
				if (model.getValueAt(row, 0)!=null) {
					JOptionPane.showMessageDialog(this, "Nhân viên đã có tài khoản");
				} else {
					if (btnThem.getText().equals("Thêm")) {
						btnLuu.setEnabled(true);
						txtTenTaiKhoan.setEditable(true);
						txtMatKhau.setEditable(true);
						btnThem.setText("Hủy");
					} else if (btnThem.getText().equals("Hủy")) {
						btnLuu.setEnabled(false);
						txtTenTaiKhoan.setEditable(false);
						txtMatKhau.setEditable(false);
						btnThem.setText("Thêm");
					}
				}
			}

		} else if (o.equals(btnLuu)) {
			if (validateData()) {
				String taiKhoan = txtTenTaiKhoan.getText();
				String matKhau = txtMatKhau.getText();
				String maNV = cmbMaNhanVien.getItemAt(cmbMaNhanVien.getSelectedIndex()).toString();
				if (taiKhoanDAO.capNhanTaiKhoanMatKhauChoNhanVien(maNV, taiKhoan, matKhau)) {
					JOptionPane.showMessageDialog(null, "cập nhập thành công");
					btnLuu.setEnabled(false);
					txtTenTaiKhoan.setEditable(false);
					txtMatKhau.setEditable(false);
					xoaALLDuLieuTable();
					docDuLieuVaoBang();
					btnThem.setText("Thêm");
				} else {
					JOptionPane.showMessageDialog(null, "Không cập nhập được tài khoản và mật khẩu cho nhân viên!",
							"Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else if (o.equals(btnXoa)) {
			int yes = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa tài khoản của nhân viên không", "Xóa",
					JOptionPane.YES_NO_OPTION);
			if (yes == JOptionPane.YES_OPTION) {
				String maNV = cmbMaNhanVien.getItemAt(cmbMaNhanVien.getSelectedIndex()).toString();
				if (taiKhoanDAO.xoaTaiKhoanCuaNhanVien(maNV)) {
					JOptionPane.showMessageDialog(null, "xóa tài khoản thành công");
					xoaALLDuLieuTable();
					docDuLieuVaoBang();
				} else {
					JOptionPane.showMessageDialog(null, "Không xóa được!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		txt.requestFocus();
		JOptionPane.showMessageDialog(this, message, "Lỗi!", JOptionPane.ERROR_MESSAGE);
	}

	private boolean validateData() {
		String taiKhoan = txtTenTaiKhoan.getText();
		String matKhau = txtMatKhau.getText();
		if (taiKhoan.length() == 0) {
			showMessage(txtTenTaiKhoan, "Nhập tên tài khoản");
			return false;
		}
		if (!taiKhoan.matches("^[a-zA-Z0-9]+$")) {
			showMessage(txtTenTaiKhoan,
					"Tên tài khoản là các ký tự thuộc về bảng chữ cái tiếng Anh hoa hoặc thường (a-z, A-Z) hoặc chữ số (0-9)");
			return false;
		}
		if (matKhau.length() == 0) {
			showMessage(txtMatKhau, "nhập mật khẩu");
			return false;
		}
		if (!matKhau.matches("^.{8,}$")) {
			showMessage(txtMatKhau, "Mật khẩu phải tối thiểu tám ký tự");
			return false;
		}
		return true;
	}
}
