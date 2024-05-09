package GUI;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_NhanVien;
import entity.NhanVien;

import javax.swing.ImageIcon;

public class TimKiemNhanVien extends JPanel implements ActionListener, MouseListener, KeyListener {
	private JTextField txtSDT;
	private JTextField txtTenNhanVien;
	private JTextField txtMaNhanVien;
	private JTextField txtCMND;
	private JTextField txtDiaChi;
	private JTable table;
	private JComboBox cmbGioiTinh;
	private JComboBox cmbChucVu;
	private JButton btnXoaTrang, btnLamMoi;
	private DefaultTableModel model;
	private ArrayList<NhanVien> dsNhanVien;
	private DAO_NhanVien nhanVienDAO;
	private DAO_NhanVien daoNV = new DAO_NhanVien();
	private ArrayList<NhanVien> dsTimDuoc = daoNV.getALLNhanVien();

	/**
	 * Create the panel.
	 */
	public TimKiemNhanVien() {
		setLayout(null);
		setBounds(0, 0, 1540, 787);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1520, 753);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 10, 343, 721);
		panel.add(panel_1);

		JLabel lblThngTinNhn = new JLabel("Thông Tin Nhân Vien");
		lblThngTinNhn.setHorizontalAlignment(SwingConstants.LEFT);
		lblThngTinNhn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThngTinNhn.setBounds(0, 10, 181, 17);
		panel_1.add(lblThngTinNhn);

		JLabel lblMaNhanVien = new JLabel("Mã Nhân Viên");
		lblMaNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaNhanVien.setBounds(20, 37, 124, 17);
		panel_1.add(lblMaNhanVien);

		JLabel lblTenNhanVien = new JLabel("Tên Nhân Viên");
		lblTenNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenNhanVien.setBounds(20, 101, 124, 17);
		panel_1.add(lblTenNhanVien);

		JLabel lblSDT = new JLabel("Số Điện Thoại");
		lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDT.setBounds(20, 173, 124, 17);
		panel_1.add(lblSDT);

		JLabel lblCMND = new JLabel("CMND");
		lblCMND.setHorizontalAlignment(SwingConstants.LEFT);
		lblCMND.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCMND.setBounds(20, 249, 89, 17);
		panel_1.add(lblCMND);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(20, 200, 287, 27);
		panel_1.add(txtSDT);

		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(20, 129, 287, 27);
		panel_1.add(txtTenNhanVien);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setColumns(10);
		txtMaNhanVien.setBounds(20, 64, 287, 27);
		panel_1.add(txtMaNhanVien);

		txtCMND = new JTextField();
		txtCMND.setColumns(10);
		txtCMND.setBounds(20, 276, 287, 27);
		panel_1.add(txtCMND);

		JLabel lblGioiTinh = new JLabel("Giới Tính");

		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGioiTinh.setBounds(20, 326, 89, 17);
		panel_1.add(lblGioiTinh);

		txtDiaChi = new JTextField("");
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(20, 438, 287, 27);
		panel_1.add(txtDiaChi);

		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiaChi.setBounds(20, 411, 89, 17);
		panel_1.add(lblDiaChi);

		JLabel lblChucVu = new JLabel("Chức Vụ");
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChucVu.setBounds(20, 494, 89, 17);
		panel_1.add(lblChucVu);
		String gioTinh[] = { "Giới Tính","nam", "nữ" };
		cmbGioiTinh = new JComboBox(gioTinh);
		cmbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbGioiTinh.setBounds(20, 353, 287, 27);
		panel_1.add(cmbGioiTinh);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(
				new ImageIcon("D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-search-30.png"));
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLamMoi.setBackground(new Color(52, 147, 188));
		btnLamMoi.setBounds(102, 569, 124, 34);
		panel_1.add(btnLamMoi);

		String chucVu[] = {"", "Nhân viên", "Quản Lý" };
		cmbChucVu = new JComboBox(chucVu);
		cmbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbChucVu.setBounds(20, 532, 287, 27);
		panel_1.add(cmbChucVu);

		JLabel lblTimKiemNhanVien = new JLabel("Tìm Kiếm Nhân Viên");
		lblTimKiemNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTimKiemNhanVien.setBounds(932, 0, 203, 34);
		panel.add(lblTimKiemNhanVien);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(363, 48, 1147, 683);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã Nhân Viên", "Tên Nhân Viên",
				"Số điện thoại", "CMND ", "Giới Tính", "Địa Chỉ", "Chức Vụ" }));
		model = (DefaultTableModel) table.getModel();
		nhanVienDAO = new DAO_NhanVien();
		docDuLieuVaoBang();
		table.addMouseListener(this);
		cmbChucVu.addActionListener(this);
		cmbGioiTinh.addActionListener(this);
		btnLamMoi.addActionListener(this);
		txtMaNhanVien.addKeyListener(this);
		txtCMND.addKeyListener(this);
		txtDiaChi.addKeyListener(this);
		txtSDT.addKeyListener(this);
		txtTenNhanVien.addKeyListener(this);

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

			}
		}
	}

	private void xoaRongNhapLieu() {
		txtMaNhanVien.setText("");
		txtTenNhanVien.setText("");
//		txtTenNV.requestFocus();
		txtSDT.setText("");
		// txtNgaySinh.setDateFormatString(toString());
		txtDiaChi.setText("");
		cmbChucVu.setSelectedIndex(0);
		cmbGioiTinh.setSelectedIndex(0);
		txtCMND.setText("");
		// txtTimKiem.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnLamMoi)) {
			xoaRongNhapLieu();
			loadData(dsNhanVien);
		}
		if (obj.equals(cmbChucVu)) {
			String gioiTinh;
			if (cmbGioiTinh.getSelectedIndex() <= 0) {
				gioiTinh = "";
			} else {
				gioiTinh = cmbGioiTinh.getSelectedItem().toString();
			}
			if (cmbChucVu.getSelectedItem() != null) {
				System.out.println(cmbChucVu.getSelectedItem().toString());
				String chucVu = "";
				if(cmbChucVu.getSelectedIndex()<=0) {
					 chucVu = "";
				}else {
					chucVu = "QL";
					if (!cmbChucVu.getSelectedItem().toString().equalsIgnoreCase("Quản Lý")) {
						chucVu = "NV";
					}
				}
				
				dsTimDuoc = daoNV.getTimKiemDSNV(txtMaNhanVien.getText(), txtTenNhanVien.getText(), txtSDT.getText(),
						txtCMND.getText(), gioiTinh, txtDiaChi.getText(), chucVu);
				loadData(dsTimDuoc);
			}
		}
		if (obj.equals(cmbGioiTinh)) {
			String gioiTinh;
			if (cmbGioiTinh.getSelectedIndex() <= 0) {
				gioiTinh = "";
			} else {
				gioiTinh = cmbGioiTinh.getSelectedItem().toString();
			}
			String chucVu = "";
			if(cmbChucVu.getSelectedIndex()<=0) {
				 chucVu = "";
			}else {
				chucVu = "QL";
				if (!cmbChucVu.getSelectedItem().toString().equalsIgnoreCase("Quản Lý")) {
					chucVu = "NV";
				}
			}
			if (cmbGioiTinh.getSelectedItem() != null) {
				dsTimDuoc = daoNV.getTimKiemDSNV(txtMaNhanVien.getText(), txtTenNhanVien.getText(), txtSDT.getText(),
						txtCMND.getText(), gioiTinh, txtDiaChi.getText(),
						chucVu);
				System.out.println(gioiTinh);
				loadData(dsTimDuoc);
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		String gioiTinh;
		if (cmbGioiTinh.getSelectedIndex() <= 0) {
			gioiTinh = "";
		} else {
			gioiTinh = cmbGioiTinh.getSelectedItem().toString();
		}
		dsTimDuoc = daoNV.getTimKiemDSNV(txtMaNhanVien.getText(), txtTenNhanVien.getText(), txtSDT.getText(),
				txtCMND.getText(), gioiTinh, txtDiaChi.getText(), cmbChucVu.getSelectedItem().toString());
		loadData(dsTimDuoc);
		// Nếu danh sách tìm được rỗng

		// JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng nào theo tiêu
		// chí nhập vào");

		if (txtMaNhanVien.getText().isEmpty() && txtTenNhanVien.getText().isEmpty() && txtSDT.getText().isEmpty()
				&& txtCMND.getText().isEmpty() && txtDiaChi.getText().isEmpty() && gioiTinh.equals("")) {
			xoaALLDuLieuTable(); // Xóa hết các dòng trong bảng
		}
	}

	private void loadData(ArrayList<NhanVien> dsNV) {
		model.setRowCount(0);
		for (int i = 0; i < dsNV.size(); i++) {
			String maNV = dsNV.get(i).getMaNV();
			String tenNV = dsNV.get(i).getTenNV();
			String sDT = dsNV.get(i).getSdt();
			String cmnd = dsNV.get(i).getCmnd();
			String gioiTinh = dsNV.get(i).isGioiTinh() ? "Nam" : "Nữ";
			String diaChi = dsNV.get(i).getDiaChi();

			String chucVu = dsNV.get(i).getChucVu();
			String row[] = { maNV, tenNV, sDT, cmnd, gioiTinh, diaChi, chucVu };
			model.addRow(row);
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
