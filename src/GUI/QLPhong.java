package GUI;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_LoaiPhong;
import DAO.DAO_Phong;
import entity.LoaiPhong;
import entity.Phong;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class QLPhong extends JPanel implements ActionListener, MouseListener {
	private JTextField txtTenPhong;
	private JTextField txtMaPhong;
	private JTextField txtGiaPhong;
	private JTextField txtSucChua;
	private JTable table;
	private JButton btnThem, btnSua, btnXoaTrang;
	private DefaultTableModel model;
	private JComboBox cmbLoaiPhong, cmbTrangThai, cmbTimKiemTheoMaPhong;
	private ArrayList<Phong> dsPhong;
	private ArrayList<LoaiPhong> dsLoaiPhong;
	private DAO_LoaiPhong loaiPhongDAO;
	private DAO_Phong phongDAO;

	/**
	 * Create the panel.
	 */
	public QLPhong() {
		setLayout(null);
		setBounds(0, 0, 1540, 787);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1520, 767);
		add(panel);
		panel.setLayout(null);

		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setForeground(new Color(255, 252, 237));
		pnMain.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnMain.setBackground(SystemColor.inactiveCaptionBorder);
		pnMain.setBounds(10, 10, 1500, 338);
		panel.add(pnMain);

		JLabel lblDanhSachPhong = new JLabel("Danh Sách Phòng Hát");
		lblDanhSachPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDanhSachPhong.setBounds(10, 0, 171, 33);
		pnMain.add(lblDanhSachPhong);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(20, 53, 1470, 179);
		pnMain.add(panel_1);

		JLabel lblThongTinPhong = new JLabel("Thông Tin Phòng");
		lblThongTinPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThongTinPhong.setBounds(10, 0, 162, 36);
		panel_1.add(lblThongTinPhong);

		JLabel lblTenPhong = new JLabel("Tên Phòng");
		lblTenPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTenPhong.setBounds(41, 54, 88, 36);
		panel_1.add(lblTenPhong);

		txtTenPhong = new JTextField();
		txtTenPhong.setToolTipText("Tên Phòng");
		txtTenPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenPhong.setColumns(10);
		txtTenPhong.setBounds(139, 59, 306, 25);
		panel_1.add(txtTenPhong);

		JLabel lblMaPhong = new JLabel("Mã Phòng");
		lblMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaPhong.setBounds(41, 112, 74, 36);
		panel_1.add(lblMaPhong);

		txtMaPhong = new JTextField();
		txtMaPhong.setEditable(false);
		txtMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaPhong.setColumns(10);
		txtMaPhong.setBounds(139, 118, 306, 25);
		panel_1.add(txtMaPhong);

		JLabel lblLoaiPhong = new JLabel("Loại Phòng");
		lblLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoaiPhong.setBounds(527, 54, 88, 36);
		panel_1.add(lblLoaiPhong);

		JLabel lblGiaPhong = new JLabel("Giá Phòng");
		lblGiaPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGiaPhong.setBounds(527, 112, 74, 36);
		panel_1.add(lblGiaPhong);

		txtGiaPhong = new JTextField();
		txtGiaPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGiaPhong.setColumns(10);
		txtGiaPhong.setBounds(633, 118, 306, 25);
		panel_1.add(txtGiaPhong);

		JLabel lbSucChua = new JLabel("Sức Chứa");
		lbSucChua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbSucChua.setBounds(1052, 54, 70, 36);
		panel_1.add(lbSucChua);

		JLabel lbTrangThai = new JLabel("Trạng Thái");
		lbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTrangThai.setBounds(1052, 112, 88, 36);
		panel_1.add(lbTrangThai);

		txtSucChua = new JTextField();
		txtSucChua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSucChua.setColumns(10);
		txtSucChua.setBounds(1150, 60, 307, 28);
		panel_1.add(txtSucChua);

		cmbLoaiPhong = new JComboBox();
		cmbLoaiPhong.setEditable(true);
		cmbLoaiPhong.setBounds(633, 62, 306, 25);
		panel_1.add(cmbLoaiPhong);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setEditable(true);
		cmbTrangThai.setBounds(1150, 122, 306, 25);
		panel_1.add(cmbTrangThai);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(QLPhong.class.getResource("/img/icons8-new-copy-30.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThem.setBackground(new Color(152, 251, 152));
		btnThem.setBounds(109, 270, 131, 33);
		pnMain.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(QLPhong.class.getResource("/img/icons8-fix-30.png")));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSua.setBackground(new Color(152, 251, 152));
		btnSua.setBounds(268, 270, 121, 33);
		pnMain.add(btnSua);

		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setIcon(new ImageIcon(QLPhong.class.getResource("/img/icons8-delete-30.png")));
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoaTrang.setBackground(new Color(152, 251, 152));
		btnXoaTrang.setBounds(412, 270, 153, 33);
		pnMain.add(btnXoaTrang);

		cmbTimKiemTheoMaPhong = new JComboBox();
		cmbTimKiemTheoMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbTimKiemTheoMaPhong.setEditable(true);
		cmbTimKiemTheoMaPhong.setBounds(1172, 273, 273, 31);
		pnMain.add(cmbTimKiemTheoMaPhong);

		JLabel lnDanhSachPhong = new JLabel("Danh Sách Phong Hát");
		lnDanhSachPhong.setFont(new Font("Tahoma", Font.BOLD, 19));
		lnDanhSachPhong.setBounds(10, 358, 265, 49);
		panel.add(lnDanhSachPhong);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 406, 1500, 351);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, },
				new String[] { "Mã Phòng", "Tên Phòng", "Loại Phòng", "Sức chứa", "Giá Phòng", "Trạng Thái" }));
		model = (DefaultTableModel) table.getModel();
		loaiPhongDAO = new DAO_LoaiPhong();
		phongDAO = new DAO_Phong();
		docDuLieuLoaiPhong();
		docDuLieuVaoBang();
		docDuLieuTrangThai();
		docDulieuVaoCMDtimKiem();
		table.addMouseListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		cmbTimKiemTheoMaPhong.addActionListener(this);
		btnThem.addActionListener(this);

	}

	public void xoaTrang() {
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		txtGiaPhong.setText("");
		txtSucChua.setText("");
		cmbTrangThai.setSelectedIndex(0);
		cmbLoaiPhong.setSelectedIndex(0);
//		txtTrangThai.setText("");
	}

	private void xoaALLDuLieuTable() {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	public void docDuLieuLoaiPhong() {
		dsLoaiPhong = loaiPhongDAO.getAllLoaiPhong();
		for (LoaiPhong loaiPhong : dsLoaiPhong) {
			cmbLoaiPhong.addItem(loaiPhong.getTenLoaiPhong());
		}
	}

	public void docDuLieuTrangThai() {
		ArrayList<String> dsTrangThai = phongDAO.getDSTrangThai();
		for (String string : dsTrangThai) {
			cmbTrangThai.addItem(string);
		}
	}

	public void docDulieuVaoCMDtimKiem() {
		cmbTimKiemTheoMaPhong.removeAllItems();
		cmbTimKiemTheoMaPhong.addItem("");
		ArrayList<Phong> ds = phongDAO.getALLPhong();
		for (Phong phong : ds) {
			cmbTimKiemTheoMaPhong.addItem(phong.getMaPhong());
		}
	}

	public void docDuLieuVaoBang() {
		dsPhong = phongDAO.getALLPhong();
		
		if (dsPhong == null) {
			JOptionPane.showMessageDialog(this, "Không có dữ liệu Phong!!");
		} else {
			xoaALLDuLieuTable();
			for (Phong phong : dsPhong) {
//				String tenLoaiPhong="";
//				for (LoaiPhong loaiPhong : dsLoaiPhong) {
//					if(phong.getLoaiPhong().equals(loaiPhong.getMaLoaiPhong())) {
//						tenLoaiPhong=loaiPhong.getTenLoaiPhong();
//					}
//				}
				model.addRow(
						new Object[] { phong.getMaPhong(), phong.getTenPhong(), phong.getLoaiPhong().getTenLoaiPhong(),
								phong.getSucChua(),(int) phong.getDonGia(), phong.getTrangThai() });
			}
		}
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		txt.requestFocus();
		JOptionPane.showMessageDialog(this, message, "Lỗi!", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaPhong.setText(model.getValueAt(row, 0).toString());
		txtTenPhong.setText(model.getValueAt(row, 1).toString());
		txtSucChua.setText(model.getValueAt(row, 3).toString());
		txtGiaPhong.setText(model.getValueAt(row, 4).toString());
		String lp = model.getValueAt(row, 2).toString();
		String tt = model.getValueAt(row, 5).toString();
		for (int i = 0; i < cmbLoaiPhong.getItemCount(); i++) {
			if (lp.equalsIgnoreCase(cmbLoaiPhong.getItemAt(i).toString())) {
				cmbLoaiPhong.setSelectedIndex(i);
			}
		}
		for (int i = 0; i < cmbTrangThai.getItemCount(); i++) {
			if (tt.equalsIgnoreCase(cmbTrangThai.getItemAt(i).toString())) {
				cmbTrangThai.setSelectedIndex(i);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				txtTenPhong.setEditable(true);
				txtGiaPhong.setEditable(true);
				txtSucChua.setEditable(true);
				btnSua.setText("Lưu");
			} else if (btnSua.getText().equals("Lưu")) {
				if (txtMaPhong.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng");
					btnSua.setText("Sửa");
					xoaTrang();
					txtTenPhong.setEditable(false);
					txtGiaPhong.setEditable(false);
					txtSucChua.setEditable(false);
				} else {
					if (validateData()) {
						String maPhong = txtMaPhong.getText();
						String tenPhong = txtTenPhong.getText();
						String donGia = txtGiaPhong.getText();
						double dg = Double.parseDouble(donGia);
						int sucChua = Integer.parseInt(txtSucChua.getText());
						String trangThai = cmbTrangThai.getSelectedItem().toString();
						dsLoaiPhong = loaiPhongDAO.getAllLoaiPhong();
						LoaiPhong lp = null;
						String lPhong = cmbLoaiPhong.getSelectedItem().toString();
						for (LoaiPhong loaiPhong : dsLoaiPhong) {
							if (lPhong.equalsIgnoreCase(loaiPhong.getTenLoaiPhong())) {
								lp = loaiPhong;
							}
						}
						Phong p = phongDAO.timPhongTheoMaPhong(maPhong);
						p.setDonGia(dg);
						p.setLoaiPhong(lp);
						p.setSucChua(sucChua);
						p.setTenPhong(tenPhong);
						p.setTrangThai(trangThai);
						if (phongDAO.capNhapPhong(p)) {
							JOptionPane.showMessageDialog(null, "Cập Nhập Thành công");
							docDuLieuVaoBang();
							cmbTimKiemTheoMaPhong.setSelectedIndex(0);
							xoaTrang();
						} else {
							thongBaoLoi("Lỗi cập nhập phòng", "Lỗi");
						}
					}

				}
			}
		} else if (o.equals(cmbTimKiemTheoMaPhong)) {
			String maPhong = cmbTimKiemTheoMaPhong.getItemAt(cmbTimKiemTheoMaPhong.getSelectedIndex()).toString();
			if (maPhong != null) {
				int viTri = timKiemViTriPhong(maPhong);
				if (viTri >= 0) {
					table.setRowSelectionInterval(viTri, viTri);
					int row = table.getSelectedRow();
					txtMaPhong.setText(model.getValueAt(row, 0).toString());
					txtTenPhong.setText(model.getValueAt(row, 1).toString());
					txtSucChua.setText(model.getValueAt(row, 3).toString());
					txtGiaPhong.setText(model.getValueAt(row, 4).toString());
					String lp = model.getValueAt(row, 2).toString();
					String tt = model.getValueAt(row, 5).toString();
					for (int i = 0; i < cmbLoaiPhong.getItemCount(); i++) {
						if (lp.equalsIgnoreCase(cmbLoaiPhong.getItemAt(i).toString())) {
							cmbLoaiPhong.setSelectedIndex(i);
						}
					}
					for (int i = 0; i < cmbTrangThai.getItemCount(); i++) {
						if (tt.equalsIgnoreCase(cmbTrangThai.getItemAt(i).toString())) {
							cmbTrangThai.setSelectedIndex(i);
						}
					}
				}

			}
		} else if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("thêm")) {
				String maPhong = taoMaPhong();
				txtMaPhong.setText(maPhong);
				btnThem.setText("Lưu");
			} else {
				if (validateData()) {
					String maPhong = txtMaPhong.getText();

					String tenPhong = txtTenPhong.getText();
					double donGia = Double.parseDouble(txtGiaPhong.getText());
					int sucChua = Integer.parseInt(txtSucChua.getText());
					String trangThai = cmbTrangThai.getSelectedItem().toString();
					dsLoaiPhong = loaiPhongDAO.getAllLoaiPhong();
					LoaiPhong lp = null;
					String lPhong = cmbLoaiPhong.getSelectedItem().toString();
					for (LoaiPhong loaiPhong : dsLoaiPhong) {
						if (lPhong.equalsIgnoreCase(loaiPhong.getTenLoaiPhong())) {
							lp = loaiPhong;
						}
					}
					Phong x = new Phong(maPhong, tenPhong, lp, donGia, sucChua, trangThai);
					if (phongDAO.themPhong(x)) {
						JOptionPane.showMessageDialog(null, "Thêm phòng thành cônng");
						xoaALLDuLieuTable();
						docDuLieuVaoBang();
						cmbTimKiemTheoMaPhong.addItem(x.getMaPhong());
					} else {
						thongBaoLoi("Lỗi thêm phòng", "lỗi");
					}
				}

			}

		}

	}

	public int timKiemViTriPhong(String maPhong) {
		ArrayList<Phong> ds = phongDAO.getALLPhong();
		for (int i = 0; i < ds.size(); i++) {
			if (maPhong.equalsIgnoreCase(ds.get(i).getMaPhong())) {
				return i;
			}
		}
		return -1;
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	public String taoMaPhong() {
		ArrayList<Phong> ds = phongDAO.getALLPhong();
		int i = 0;
		while (i < ds.size()) {
			if (Integer.parseInt(ds.get(i).getMaPhong().substring(1)) == (i + 1)) {
				i++;
			} else {
				break;
			}
		}
		return String.format("P%03d", i + 1);
	}

	public boolean validateData() {
		String tenPhong = txtTenPhong.getText();
		String donGia = txtGiaPhong.getText();
		String sucChua = txtSucChua.getText();
		if (tenPhong.length() == 0) {
			showMessage(txtTenPhong, "Nhập tên Phòng!");
			return false;
		}
		if (!tenPhong.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]+\\s?)+$")) {
			showMessage(txtTenPhong,
					"Tên phòng bao gồm chứ cái, chữ số,có thể có 1 khoảng trắng ngăn cách giữa 2 từ và không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (donGia.length() == 0) {
			showMessage(txtGiaPhong, "Nhập Giá Phòng!");
			return false;
		}
		if (!donGia.matches("^(?!0*$)\\d+$")) {
			showMessage(txtGiaPhong, "Giá Phòng lớn hơn 0 , không để trống!");
			return false;
		}
		if (sucChua.length() == 0) {
			showMessage(txtSucChua, "Nhập sức chứa của phòng!");
			return false;
		}
		if (!sucChua.matches("^(?!0*$)\\d+$")) {
			showMessage(txtSucChua, "sức chưa lớn hơn 0!");
			return false;
		}
		return true;
	}
}
