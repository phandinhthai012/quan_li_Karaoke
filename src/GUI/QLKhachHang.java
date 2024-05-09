package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.table.DefaultTableModel;

import DAO.DAO_KhachHang;
import connectDB.connectDB;
import entity.KhachHang;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;

public class QLKhachHang extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTable table;
	private JButton btnThem, btnXoaTrang, btnSua;
	private DefaultTableModel model;
	DAO_KhachHang khachHangDAO;
	private ArrayList<KhachHang> danhSachKH = null;
	private JComboBox cmbTimKH;

	/**
	 * Create the panel.
	 */
	public QLKhachHang() {

		try {
			connectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		setLayout(null);
		setBounds(0, 0, 1540, 787);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1530, 875);
		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(0, 0, 291, 757);
		panel.add(panel_1);

		JLabel lblMaKH = new JLabel("Mã khách hàng:");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKH.setBounds(10, 10, 120, 31);
		panel_1.add(lblMaKH);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(10, 37, 229, 31);
		panel_1.add(txtMaKhachHang);

		JLabel lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTnKhchHng.setBounds(10, 91, 151, 31);
		panel_1.add(lblTnKhchHng);

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(10, 125, 229, 31);
		panel_1.add(txtTenKhachHang);

		JLabel lblSdt = new JLabel("Số điện thoại:");
		lblSdt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSdt.setBounds(10, 179, 151, 31);
		panel_1.add(lblSdt);

		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setColumns(10);
		txtSDT.setBounds(10, 215, 229, 31);
		panel_1.add(txtSDT);

		JLabel lblaDiaChi = new JLabel("Địa chỉ:");
		lblaDiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblaDiaChi.setBounds(10, 268, 151, 31);
		panel_1.add(lblaDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(10, 309, 229, 31);
		panel_1.add(txtDiaChi);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(QLKhachHang.class.getResource("/img/icons8-new-copy-30.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThem.setBackground(Color.WHITE);
		btnThem.setBounds(10, 553, 132, 46);
		panel_1.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(QLKhachHang.class.getResource("/img/icons8-fix-30.png")));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSua.setBackground(Color.WHITE);
		btnSua.setBounds(164, 553, 117, 46);
		panel_1.add(btnSua);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon(QLKhachHang.class.getResource("/img/icons8-delete-30.png")));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoaTrang.setBackground(Color.WHITE);
		btnXoaTrang.setBounds(72, 632, 151, 58);
		panel_1.add(btnXoaTrang);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(301, 10, 1202, 157);
		panel.add(panel_2);

		JLabel lblDanhSachKhachHang = new JLabel("Danh sách khách hàng");
		lblDanhSachKhachHang.setIcon(new ImageIcon(QLKhachHang.class.getResource("/img/icons8-customer-90.png")));
		lblDanhSachKhachHang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblDanhSachKhachHang.setBounds(32, 44, 358, 90);
		panel_2.add(lblDanhSachKhachHang);

		JLabel lblTimKiemKH = new JLabel("Tìm kiếm khách hàng:");
		lblTimKiemKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTimKiemKH.setBounds(779, 22, 168, 26);
		panel_2.add(lblTimKiemKH);

		cmbTimKH = new JComboBox();
		cmbTimKH.setBounds(779, 58, 178, 26);
		panel_2.add(cmbTimKH);

		JPanel pnlTable = new JPanel();
		pnlTable.setBounds(301, 190, 1202, 567);
		panel.add(pnlTable);
		pnlTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 1182, 483);
		pnlTable.add(scrollPane);

		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 Kh\u00E1ch H\u00E0ng",
				"T\u00EAn Kh\u00E1ch H\u00E0ng", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "\u0110\u1ECBa Ch\u1EC9" }));
		model = (DefaultTableModel) table.getModel();

		khachHangDAO = new DAO_KhachHang();

		btnXoaTrang.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		table.addMouseListener(this);
		cmbTimKH.addActionListener(this);
		docDulieuVaoBang();
		docMaKhachHangVaocmb();
	}

	public void xoaTrang() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");
	}

	public void xoaALLDuLieuTable() {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}

	public void docDulieuVaoBang() {
		danhSachKH = khachHangDAO.getALLKhachHang();
		if (danhSachKH == null) {
			JOptionPane.showMessageDialog(this, "Không có dữ liệu Khách Hàng");
			return;
		}
		xoaALLDuLieuTable();
		for (KhachHang khachHang : danhSachKH) {
			model.addRow(new Object[] { khachHang.getMaKH(), khachHang.getTenKH(), khachHang.getSdt(),
					khachHang.getDiaChi() });
		}
	}

	public void docMaKhachHangVaocmb() {
		cmbTimKH.addItem("");
		for (KhachHang khachHang : danhSachKH) {
			cmbTimKH.addItem(khachHang.getMaKH());
		}
	}

	public boolean themKhachHang() {
		String maKh = txtMaKhachHang.getText();
		String tenKH = txtTenKhachHang.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		KhachHang kh = new KhachHang(maKh, tenKH, sdt, diaChi);
		if (khachHangDAO.themKhachHang(kh)) {
			danhSachKH.add(kh);
			cmbTimKH.addItem(maKh);
			Object[] data = { maKh, tenKH, sdt, diaChi };
			model.addRow(data);
			return true;
		} else
			return false;
	}

	public int timKhachHang(String makh) {
		for (int i = 0; i < danhSachKH.size(); i++) {
			if (danhSachKH.get(i).getMaKH().equalsIgnoreCase(makh)) {
				return i;
			}
		}
		return -1;
	}

	public boolean xoaKhachHang() {
		int row = table.getSelectedRow();
		String makh = model.getValueAt(row, 0).toString();
		if (khachHangDAO.xoaKhachHang(makh)) {
			return true;
		}
		return false;
	}

	public KhachHang timKhachHangTheoMaKH(String maKH) {
		KhachHang x = null;
		ArrayList<KhachHang> ds = khachHangDAO.getALLKhachHang();
		for (int i = 0; i < ds.size(); i++) {
			if (ds.get(i).getMaKH().equals(maKH)) {
				x = ds.get(i);
			}
		}
		return x;
	}

	public boolean capNhapThongTinKhachHang(KhachHang kh) {
		if (khachHangDAO.capNhapKhachHang(kh)) {
			JOptionPane.showMessageDialog(null, "Cập nhập thành công");
			kh.toString();
			return true;
		} else {
			thongBaoLoi("lỗi cập nhập", "lỗi");
			return false;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				xoaTrang();
				btnThem.setText("Lưu");
				String maKH = taoMaKH();
				txtMaKhachHang.setText(maKH);
				txtTenKhachHang.setEditable(true);
				txtDiaChi.setEditable(true);
				txtSDT.setEditable(true);
			} else if (btnThem.getText().equals("Lưu")) {
				boolean ktra = validData();
				if (ktra) {
					if (!themKhachHang()) {
						JOptionPane.showMessageDialog(this, "Trùng Mã Khách Hàng");
					} else {
						xoaTrang();
						btnThem.setText("Thêm");
						txtTenKhachHang.setEditable(false);
						txtDiaChi.setEditable(false);
						txtSDT.setEditable(false);
						JOptionPane.showMessageDialog(this, "Thêm Thành Công 1 Khách Hàng Mới");
					}
				}
			}

		} else if (o.equals(cmbTimKH)) {
			String makh = cmbTimKH.getItemAt(cmbTimKH.getSelectedIndex()).toString();
			int i = timKhachHang(makh);
			if (i >= 0) {
				table.setRowSelectionInterval(i, i);
				int row = table.getSelectedRow();
				txtMaKhachHang.setText(model.getValueAt(row, 0).toString());
				txtTenKhachHang.setText(model.getValueAt(row, 1).toString());
				txtSDT.setText(model.getValueAt(row, 2).toString());
				txtDiaChi.setText(model.getValueAt(row, 3).toString());
			}

		} else if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				btnSua.setText("Lưu");
				txtTenKhachHang.setEditable(true);
				txtDiaChi.setEditable(true);
				txtSDT.setEditable(true);
			} else if (btnSua.getText().equals("Lưu")) {
				if (txtMaKhachHang.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng để sửa !");
					txtTenKhachHang.setEditable(false);
					txtDiaChi.setEditable(false);
					txtSDT.setEditable(false);
					btnSua.setText("Sửa");
				} else {
					if (validData()) {
						String makh = txtMaKhachHang.getText();
						KhachHang kh = timKhachHangTheoMaKH(makh);
						String tenKh = txtTenKhachHang.getText();
						String sdt = txtSDT.getText();
						String diaChi = txtDiaChi.getText();
						kh.setTenKH(tenKh);
						kh.setSdt(sdt);
						kh.setDiaChi(diaChi);
						if (kh != null) {
							if (capNhapThongTinKhachHang(kh)) {
								xoaTrang();
								xoaALLDuLieuTable();
								docDulieuVaoBang();
								txtTenKhachHang.setEditable(false);
								txtDiaChi.setEditable(false);
								txtSDT.setEditable(false);
								btnSua.setText("Sửa");
							}
						} else {
							JOptionPane.showMessageDialog(this, "Lỗi");
						}
					}

				}

			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaKhachHang.setText(model.getValueAt(row, 0).toString());
		txtTenKhachHang.setText(model.getValueAt(row, 1).toString());
		txtSDT.setText(model.getValueAt(row, 2).toString());
		txtDiaChi.setText(model.getValueAt(row, 3).toString());
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

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}

	public boolean validData() {
		String tenKH = txtTenKhachHang.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		if (tenKH.trim().length() == 0) {
			showMessage(txtTenKhachHang, "Nhập tên khách hàng!");
			return false;
		}
		if (!tenKH.matches("" + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
				+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
				+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
			showMessage(txtTenKhachHang, "Tên Khách Hàng không chứa ký tự số và ký tự đặc biệt");
			txtTenKhachHang.selectAll();
			txtTenKhachHang.requestFocus();
			return false;
		}
		if (sdt.trim().length() == 0) {
			showMessage(txtSDT, "Nhập số điện thoại!");
			return false;
		}
		if (!sdt.matches("^0[1-9][0-9]{8}")) {
			showMessage(txtSDT, "Số điện thoại 10 số, số đầu tiền là số 0");
			return false;
		}
		if (diaChi.trim().equals("")) {
			showMessage(txtTenKhachHang, "Nhập Địa Chỉ!");
			return false;
		}
		if (!diaChi.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtDiaChi, "Địa chỉ không bao gồm chữ cái đặt biệt");
			return false;
		}
		return true;
	}

	/*
	 * 
	 * tạo mã khách hàng từ động
	 */
	public String taoMaKH() {
		int i = 0;
		String maKh = null;

		while (i < model.getRowCount()) {
			if (Integer.parseInt(model.getValueAt(i, 0).toString().substring(2)) == (i + 1)) {
				i++;
			} else {
				break;
			}
		}
		i = i + 1;
		if (i < 10) {
			maKh = "KH00";
			maKh = maKh + String.valueOf(i);
		} else if (i < 100) {
			maKh = "KH0";
			maKh = maKh + String.valueOf(i);
		} else if (i < 1000) {
			maKh = "KH";
			maKh = maKh + String.valueOf(i);
		}
		return maKh;
	}
}
