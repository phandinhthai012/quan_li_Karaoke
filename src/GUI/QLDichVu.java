package GUI;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_DichVu;
import connectDB.connectDB;
import entity.DichVu;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class QLDichVu extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaDichVu;
	private JTextField txtTenDichVu;
	private JTextField txtSoLuong;
	private JTextField txtDonGia;
	private JTable table;
	private JButton btnXoaDV, btnTaoMoi, btnCapNhap,btnCapNhapDs;
	private DefaultTableModel model;
	private JComboBox cmbDonVi, cmbMaDV;
	private DAO_DichVu dichVuDAO;
	private ArrayList<DichVu> dsDV;

	/**
	 * Create the panel.
	 */
	public QLDichVu() {

		try {
			connectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		setLayout(null);
		setBounds(0, 0, 1540, 787);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1520, 748);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 10, 1500, 52);
		panel.add(panel_1);

		JLabel lbQlDV = new JLabel("Quản Lí Dịch Vụ");
		lbQlDV.setHorizontalAlignment(SwingConstants.CENTER);
		lbQlDV.setFont(new Font("Tahoma", Font.BOLD, 23));
		lbQlDV.setBounds(587, 0, 376, 44);
		panel_1.add(lbQlDV);

		JPanel pnlThongTinDV = new JPanel();
		pnlThongTinDV.setLayout(null);
		pnlThongTinDV.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongTinDV.setBackground(SystemColor.inactiveCaption);
		pnlThongTinDV.setBounds(10, 88, 1500, 232);
		panel.add(pnlThongTinDV);

		JLabel lbTieuDe = new JLabel("Thông Tin Dịch Vụ");
		lbTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbTieuDe.setBounds(10, 10, 201, 37);
		pnlThongTinDV.add(lbTieuDe);

		JLabel lbMaDichVu = new JLabel("Mã Dịch Vụ");
		lbMaDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbMaDichVu.setBounds(32, 63, 109, 25);
		pnlThongTinDV.add(lbMaDichVu);

		JLabel lbTenDichVu = new JLabel("Tên Dịch Vụ");
		lbTenDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenDichVu.setBounds(32, 130, 109, 25);
		pnlThongTinDV.add(lbTenDichVu);

		txtMaDichVu = new JTextField();
		txtMaDichVu.setEditable(false);
		txtMaDichVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaDichVu.setColumns(10);
		txtMaDichVu.setBounds(162, 63, 190, 25);
		pnlThongTinDV.add(txtMaDichVu);

		txtTenDichVu = new JTextField();
		txtTenDichVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenDichVu.setColumns(10);
		txtTenDichVu.setBounds(162, 129, 545, 28);
		pnlThongTinDV.add(txtTenDichVu);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(492, 63, 218, 25);
		pnlThongTinDV.add(txtSoLuong);

		JLabel lbSoLuong = new JLabel("Số Lượng");
		lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSoLuong.setBounds(390, 63, 92, 25);
		pnlThongTinDV.add(lbSoLuong);

		JLabel lbDonVi = new JLabel("Đơn Vị");
		lbDonVi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDonVi.setBounds(754, 63, 70, 25);
		pnlThongTinDV.add(lbDonVi);

		JLabel lbDonGia = new JLabel("Đơn Gía");
		lbDonGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDonGia.setBounds(754, 130, 70, 25);
		pnlThongTinDV.add(lbDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(834, 130, 177, 25);
		pnlThongTinDV.add(txtDonGia);

		btnTaoMoi = new JButton("Tạo Mới");
		btnTaoMoi.setIcon(
				new ImageIcon("D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-new-copy-30.png"));
		btnTaoMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTaoMoi.setBackground(new Color(90, 220, 148));
		btnTaoMoi.setBounds(1065, 130, 128, 32);
		pnlThongTinDV.add(btnTaoMoi);

		btnCapNhap = new JButton("Cập Nhập");
		btnCapNhap.setIcon(
				new ImageIcon("D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-update-16.png"));
		btnCapNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCapNhap.setBackground(new Color(90, 220, 148));
		btnCapNhap.setBounds(1203, 130, 128, 32);
		pnlThongTinDV.add(btnCapNhap);

		btnXoaDV = new JButton("Xóa DV");
		btnXoaDV.setIcon(
				new ImageIcon("D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-delete-30.png"));
		btnXoaDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoaDV.setBackground(new Color(255, 87, 87));
		btnXoaDV.setBounds(1341, 130, 123, 32);
		pnlThongTinDV.add(btnXoaDV);

		cmbMaDV = new JComboBox();
		cmbMaDV.setBounds(1240, 63, 190, 25);
		pnlThongTinDV.add(cmbMaDV);

		JLabel lblNewLabel = new JLabel("Tìm Dịch Vụ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(1152, 65, 78, 21);
		pnlThongTinDV.add(lblNewLabel);

		cmbDonVi = new JComboBox();
		cmbDonVi.setEditable(true);
		cmbDonVi.setBounds(834, 63, 177, 25);
		pnlThongTinDV.add(cmbDonVi);

		JLabel lbDSDichVu = new JLabel("Danh Sách Dịch Vụ");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbDSDichVu.setBounds(20, 330, 1211, 44);
		panel.add(lbDSDichVu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 373, 1500, 352);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null }, },
				new String[] { "Mã Dịch Vụ", "Tên Dịch Vụ", "Đơn Giá", "Đơn Vị", "Số Lượng", "Ghi Chú" }));
		
		btnCapNhapDs = new JButton("Cập nhập danh sách");
		btnCapNhapDs.setIcon(new ImageIcon(QLDichVu.class.getResource("/img/icons8-refresh-30.png")));
		btnCapNhapDs.setBackground(SystemColor.activeCaption);
		btnCapNhapDs.setBounds(1332, 330, 160, 37);
		panel.add(btnCapNhapDs);
		model = (DefaultTableModel) table.getModel();
		dichVuDAO = new DAO_DichVu();
		btnXoaDV.addActionListener(this);
		btnTaoMoi.addActionListener(this);
		table.addMouseListener(this);
		cmbMaDV.addActionListener(this);
		btnCapNhap.addActionListener(this);
		btnCapNhapDs.addActionListener(this);

		docDuLieuVaoBang();
		loadDonVi();
		loadMaDichVu();

	}

	public void xoaALLDuLieuTable() {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	public void docDuLieuVaoBang() {
		dsDV = dichVuDAO.getALLDichVu();
		if (dsDV == null) {
			JOptionPane.showMessageDialog(this, "Không có dữ liệu Khách Hàng");
			return;
		}
		xoaALLDuLieuTable();
		for (DichVu dichVu : dsDV) {
			String ghiChu = "";
			if (dichVu.getSoLuongTonKho() <= 0) {
				ghiChu = "Tạm hết";
			}
			model.addRow(new Object[] { dichVu.getMaDV(), dichVu.getTenDichVu(),(int) dichVu.getDonGia(), dichVu.getDonVi(),
					dichVu.getSoLuongTonKho(), ghiChu });
		}
	}

	public int timViTriDichVu(String maDV) {
		for(int i =0; i<dsDV.size(); i++) {
			if(dsDV.get(i).getMaDV().equalsIgnoreCase(maDV)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXoaDV)) {
			xoa();
		} else if (o.equals(btnTaoMoi)) {
//			String[] dsDonVi = {"Đĩa", "Hộp", "Phần", "Lon", "Cái", "Chai" };
			String maDV = taoMaDV();
//			GD_themMoiDV themMoi = new GD_themMoiDV(maDV,dsDonVi);
//			themMoi.setVisible(true);
//			dsDV = themMoi.getDSDichVu();
			dialogThemDichVu dialogThem = new dialogThemDichVu(maDV);
			dialogThem.setVisible(true);
		} else if (o.equals(cmbMaDV)) {
			String maDV = cmbMaDV.getItemAt(cmbMaDV.getSelectedIndex()).toString();
			int vitri = timViTriDichVu(maDV);
			if(vitri>=0) {
				table.setRowSelectionInterval(vitri, vitri);
				int row = table.getSelectedRow();
				txtMaDichVu.setText(model.getValueAt(row, 0).toString());
				txtTenDichVu.setText(model.getValueAt(row, 1).toString());
				txtDonGia.setText(model.getValueAt(row, 2).toString());
				txtSoLuong.setText(model.getValueAt(row, 4).toString());
				String donVi = model.getValueAt(row, 3).toString();
				for (int i = 0; i < cmbDonVi.getItemCount(); i++) {
					if (donVi.equalsIgnoreCase(cmbDonVi.getItemAt(i).toString())) {
						cmbDonVi.setSelectedIndex(i);
						break;
					}
				}
			}
		}
		else if(o.equals(btnCapNhap)) {
			if(txtMaDichVu.getText().equals("")) {
				JOptionPane.showMessageDialog(table, "Bạn chưa chọn dịch vụ !");
			}
			else {
				if(valiData()) {
					if(capNhapDichVu()) {
						xoaTrang();
						xoaALLDuLieuTable();
						docDuLieuVaoBang();
					}
				}
				
			}
		}
		else if(o.equals(btnCapNhapDs)) {
			xoaALLDuLieuTable();
			docDuLieuVaoBang();
			loadDonVi();
		}

	}
	public boolean capNhapDichVu() {
		String madv=txtMaDichVu.getText();
		String tenDV = txtTenDichVu.getText();
		String donGia = txtDonGia.getText();
		String soLuong = txtSoLuong.getText();
		String DonVi = cmbDonVi.getItemAt(cmbDonVi.getSelectedIndex()).toString();
		double gia= Double.parseDouble(donGia);
		int sl = Integer.parseInt(soLuong);
		DichVu x = new DichVu(madv, tenDV, gia, DonVi, sl);
		if(dichVuDAO.updateDichVu(x)) {
			JOptionPane.showMessageDialog(this, "cập nhập thành công!");
			return true;
		}
		else {
			thongBaoLoi("Lỗi cập nhập", "Lỗi");
			return false;
		}
	}

	public void loadDonVi() {
		cmbDonVi.removeAllItems();
		ArrayList<DichVu> ds = dichVuDAO.getALLDichVu();
		ArrayList<String> dsDonVi = new ArrayList<String>();
		for (DichVu dichVu : ds) {
			String donVi = dichVu.getDonVi();
			if (!dsDonVi.contains(donVi)) {
				dsDonVi.add(donVi);
				cmbDonVi.addItem(donVi);
			}
		}
	}

	public void loadMaDichVu() {
		cmbMaDV.removeAllItems();
		cmbMaDV.addItem("");
		for (DichVu dichVu : dsDV) {
			cmbMaDV.addItem(dichVu.getMaDV());
		}
	}

	public void xoa() {
		int r = table.getSelectedRow();
		if (r != -1) {
			int thongBao = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa", "Xóa phòng",
					JOptionPane.YES_NO_OPTION);
			if (thongBao == JOptionPane.YES_OPTION) {
				String madv = txtMaDichVu.getText();
				if(dichVuDAO.xoaDichVu(madv)) {
					model.removeRow(r);
					xoaALLDuLieuTable();
					docDuLieuVaoBang();
					loadDonVi();
					cmbMaDV.removeItem(madv);
					JOptionPane.showMessageDialog(null, "Bạn đã xóa thành công");
				}
				else {
					thongBaoLoi("Không xóa được", "Lỗi");
				}
				
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
		}
	}
	public void xoaTrang() {
		txtMaDichVu.setText("");
		txtTenDichVu.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		cmbDonVi.setSelectedIndex(0);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		int row = table.getSelectedRow();
		txtMaDichVu.setText(model.getValueAt(row, 0).toString());
		txtTenDichVu.setText(model.getValueAt(row, 1).toString());
		txtDonGia.setText(model.getValueAt(row, 2).toString());
		txtSoLuong.setText(model.getValueAt(row, 4).toString());
		String donVi = model.getValueAt(row, 3).toString();
		for (int i = 0; i < cmbDonVi.getItemCount(); i++) {
			if (donVi.equalsIgnoreCase(cmbDonVi.getItemAt(i).toString())) {
				cmbDonVi.setSelectedIndex(i);
				break;
			}
		}
		for (int i = 0; i < cmbMaDV.getItemCount(); i++) {
			if (model.getValueAt(row, 0).toString().equalsIgnoreCase(cmbMaDV.getItemAt(i).toString())) {
				cmbMaDV.setSelectedIndex(i);
				break;
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

	private String taoMaDV() {
		ArrayList<DichVu> ds = dichVuDAO.getALLDichVu();
		int i = 0;
		while (i < ds.size()) {
			// Lấy mã ở hàng thứ i trong table bỏ "DV" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(ds.get(i).getMaDV().substring(2)) == (i + 1))
				// nếu mã khớp thì tăng i lên để xét hàng tiếp theo.
				i++;
			else
				// nếu mã không khớp thì thoát vòng lập
				break;
		}
		// i ra khỏi vòng lập là hàng có mã không khớp hoặc bằng với số hàng, ta
		// chỉ cần + 1 để có số cuối của mã mới.
		i = i + 1;
		String maDV = "DV";
		if (i < 10) {
			maDV += "00" + String.valueOf(i);
		} else if (i < 100) {
			maDV += "0" + String.valueOf(i);
		} else if (i < 1000) {
			maDV += String.valueOf(i);
		}
		return maDV;
	}
	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}
	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		txt.requestFocus();
		JOptionPane.showMessageDialog(this, message, "Lỗi!", JOptionPane.ERROR_MESSAGE);
	}
	public boolean valiData() {
		String tendv = txtTenDichVu.getText();
		String donGia = txtDonGia.getText();
		String sl = txtSoLuong.getText();
		if (tendv.length() == 0) {
			showMessage(txtTenDichVu, "Nhập tên dịch vụ!");
			return false;
		}
		if (!tendv.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]+\\s?)+$")) {
			showMessage(txtTenDichVu,
					"Tên dịch vụ bao gồm chữ cái (có thể viết tiếng việt), chữ số, có thể có 1 khoảng trắng ngăn cách giữa 2 từ và không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (donGia.length() == 0) {
			showMessage(txtDonGia, "Nhập giá dịch vụ!");
			return false;
		}
		if (!donGia.matches("^([1-9]\\d{3,})$")) {
			showMessage(txtDonGia, "Giá bán là số nguyên >= 1000 và không được bắt đầu bằng 0!");
			return false;
		}
		if (sl.length() == 0) {
			showMessage(txtDonGia, "Nhập số lượng trong kho cho dịch vụ!");
			return false;
		}
		if (!sl.matches("^([0-9]\\d*)$")) {
			showMessage(txtSoLuong, "Số lượng không được âm");
			return false;
		}
		return true;
	}
}
