package GUI;

import javax.swing.JPanel;
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
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_NhanVien;
import DAO.DAO_TaiKhoan;
import entity.NhanVien;

public class TimKiemTaiKhoan1 extends JPanel implements MouseListener, ActionListener {
	private JTextField txtTenTaiKhoan;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<NhanVien> dsNhanVien;
	private DAO_TaiKhoan taiKhoanDAO;
	private JComboBox cmbMaNhanVien;
	private JButton btnLamMoi, btnXoaTrang;

	/**
	 * Create the panel.
	 */
	public TimKiemTaiKhoan1() {
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
		panel_1.setBounds(10, 10, 1500, 160);
		panel.add(panel_1);

		JLabel lblDanhSachTaiKhoan = new JLabel("Danh sách tài khoản");
		lblDanhSachTaiKhoan.setIcon(new ImageIcon(TimKiemTaiKhoan1.class.getResource("/img/icons8-male-user-60.png")));
		lblDanhSachTaiKhoan.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblDanhSachTaiKhoan.setBounds(634, 26, 302, 47);
		panel_1.add(lblDanhSachTaiKhoan);

		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(98, 58, 115, 13);
		panel_1.add(lblNewLabel_1);

		txtTenTaiKhoan = new JTextField();
		txtTenTaiKhoan.setColumns(10);
		txtTenTaiKhoan.setBounds(206, 51, 266, 32);
		panel_1.add(txtTenTaiKhoan);

		JLabel lblNewLabel_2 = new JLabel("Mã tài khoản:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(98, 110, 104, 13);
		panel_1.add(lblNewLabel_2);

		cmbMaNhanVien = new JComboBox();
		cmbMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbMaNhanVien.setBounds(206, 105, 266, 20);
		panel_1.add(cmbMaNhanVien);

//		Choice choice = new Choice();
//		choice.setBounds(206, 105, 266, 20);
//		panel_1.add(choice);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(10, 610, 1540, 125);
		panel.add(panel_1_1);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBackground(Color.WHITE);
		btnLamMoi.setBounds(498, 25, 198, 52);
		panel_1_1.add(btnLamMoi);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoaTrang.setBackground(Color.WHITE);
		btnXoaTrang.setBounds(842, 25, 186, 52);
		panel_1_1.add(btnXoaTrang);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 180, 1500, 417);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Tên Tài Khoản", "Mã Tài Khoản" }));
		model = (DefaultTableModel) table.getModel();
		taiKhoanDAO = new DAO_TaiKhoan();
		docDuLieuVaoBang();
		docMaNhanVienVaoCMB();
		table.addMouseListener(this);
		cmbMaNhanVien.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		dsNhanVien = taiKhoanDAO.getALLNhanVien();
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
		cmbMaNhanVien.setSelectedIndex(0);
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
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnLamMoi)) {
			docDuLieuVaoBang();
			docMaNhanVienVaoCMB();
		} else if (o.equals(cmbMaNhanVien)) {
			String maNV = cmbMaNhanVien.getItemAt(cmbMaNhanVien.getSelectedIndex()).toString();
			int x = timViTri(maNV);
			if (x >= 0) {
				NhanVien nv = taiKhoanDAO.timNhanVienTheoMaNV(maNV);
				if(!(nv.getMatKhau() == null || nv.getTaiKhoan() == null)) {
					txtTenTaiKhoan.setText(nv.getTaiKhoan());
				}
				else {
					txtTenTaiKhoan.setText("");
				}
				table.setRowSelectionInterval(x, x);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();

//		txtTenTaiKhoan.setText(model.getValueAt(row, 0).toString());
		String maNV = model.getValueAt(row, 1).toString();
		NhanVien nv = taiKhoanDAO.timNhanVienTheoMaNV(maNV);
		if (!(nv.getMatKhau() == null || nv.getTaiKhoan() == null)) {
			txtTenTaiKhoan.setText(nv.getTaiKhoan());
			
		} else {
			txtTenTaiKhoan.setText("");
			
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

}
