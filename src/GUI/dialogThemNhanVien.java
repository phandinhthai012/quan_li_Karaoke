package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DAO_NhanVien;
import entity.NhanVien;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class dialogThemNhanVien extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtSDT;
	private JTextField txtcmnd;
	private JTextField txtTK;
	private JTextField txtMK;
	private JTextField txtDiaChi;
	private JComboBox cmbGioiTinh,cmbchucVu;
	private JButton btnHuy,btnThem,btnlamMoi;
	private DAO_NhanVien nhanVienDao;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			dialogThemNhanVien dialog = new dialogThemNhanVien();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public dialogThemNhanVien(String manv) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Thêm nhân viên");
		setBounds(100, 100, 776, 507);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setModal(true);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 762, 48);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thêm Nhân Viên");
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(295, 10, 143, 28);
		panel.add(lblNewLabel);
		
		JLabel lblManv = new JLabel("Mã nhân viên: ");
		lblManv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblManv.setBounds(10, 72, 116, 22);
		contentPanel.add(lblManv);
		
		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenNV.setBounds(10, 119, 116, 22);
		contentPanel.add(lblTenNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setBounds(109, 72, 175, 23);
		contentPanel.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		txtTenNV = new JTextField();
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(109, 119, 556, 23);
		contentPanel.add(txtTenNV);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT.setBounds(10, 178, 116, 22);
		contentPanel.add(lblSDT);
		
		JLabel lblCMND = new JLabel("CMND:");
		lblCMND.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCMND.setBounds(386, 178, 116, 22);
		contentPanel.add(lblCMND);
		
		JLabel lblTK = new JLabel("Tài khoản:");
		lblTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTK.setBounds(10, 231, 116, 22);
		contentPanel.add(lblTK);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGioiTinh.setBounds(386, 238, 116, 22);
		contentPanel.add(lblGioiTinh);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(109, 178, 175, 23);
		contentPanel.add(txtSDT);
		
		txtcmnd = new JTextField();
		txtcmnd.setColumns(10);
		txtcmnd.setBounds(490, 178, 175, 23);
		contentPanel.add(txtcmnd);
		
		txtTK = new JTextField();
		txtTK.setColumns(10);
		txtTK.setBounds(109, 235, 175, 23);
		contentPanel.add(txtTK);
		
		JLabel lblMK = new JLabel("Mật khẩu: ");
		lblMK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMK.setBounds(10, 294, 116, 22);
		contentPanel.add(lblMK);
		
		txtMK = new JTextField();
		txtMK.setColumns(10);
		txtMK.setBounds(109, 294, 175, 23);
		contentPanel.add(txtMK);
		
		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChucVu.setBounds(386, 292, 116, 22);
		contentPanel.add(lblChucVu);
		
		cmbGioiTinh = new JComboBox();
		cmbGioiTinh.setBounds(490, 234, 175, 21);
		cmbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Giới tính", "Nam", "Nữ" }));
		contentPanel.add(cmbGioiTinh);
		
		cmbchucVu = new JComboBox();
		cmbchucVu.setBounds(490, 295, 175, 21);
		cmbchucVu.setModel(new DefaultComboBoxModel(new String[] {"Chức Vụ","Quản lí","Nhân viên"}));
		contentPanel.add(cmbchucVu);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaChi.setBounds(10, 357, 116, 22);
		contentPanel.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(109, 356, 556, 23);
		contentPanel.add(txtDiaChi);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHuy.setIcon(new ImageIcon(dialogThemNhanVien.class.getResource("/img/icons8-cancel-30.png")));
		btnHuy.setBackground(new Color(255, 99, 71));
		btnHuy.setBounds(10, 426, 105, 34);
		contentPanel.add(btnHuy);
		
		btnlamMoi = new JButton("Làm mới");
		btnlamMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnlamMoi.setBackground(new Color(50, 205, 50));
		btnlamMoi.setBounds(550, 426, 97, 34);
		btnlamMoi.setMargin(new Insets(2, 0, 2, 0));
		btnlamMoi.setIconTextGap(2);
		btnlamMoi.setForeground(Color.WHITE);
		contentPanel.add(btnlamMoi);
		
		btnThem = new JButton("Thêm");
		btnThem.setForeground(Color.BLACK);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBackground(new Color(224, 255, 255));
		btnThem.setBounds(657, 426, 80, 34);
		
		contentPanel.add(btnThem);
		txtMaNV.setText(manv);
		
		nhanVienDao = new  DAO_NhanVien();
		btnHuy.addActionListener(this);
		btnlamMoi.addActionListener(this);
		btnThem.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnHuy)) {
			dispose();
		}else if(o.equals(btnThem)) {
			boolean vali = valiData();
			if(vali) {
				boolean k = themNhanVien();
				if(k) {
					JOptionPane.showMessageDialog(this, "Thêm  khách hàng thành công!");
					dispose();
				}
			}
			
		}
		else if(o.equals(btnlamMoi)) {
			txtTenNV.setText("");
			txtcmnd.setText("");
			txtDiaChi.setText("");
			txtMK.setText("");
			txtTK.setText("");
			txtSDT.setText("");
			cmbchucVu.setSelectedIndex(0);
			cmbGioiTinh.setSelectedIndex(0);
		}
	}
	
	public boolean themNhanVien() {
		
			String manv = txtMaNV.getText();
			String hoTen = txtTenNV.getText();
			String sdt = txtSDT.getText();
			String cmnd = txtcmnd.getText();
			String diaChi = txtDiaChi.getText();
			String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
			String chucVu = cmbchucVu.getSelectedItem().toString();
			String tk = txtTK.getText();
			String mk = txtMK.getText();
			boolean gt = true;
			if(gioiTinh.equalsIgnoreCase("nữ")) {
				gt=false;
			}

			NhanVien nv = new NhanVien(manv, hoTen, sdt, cmnd, gt, diaChi, tk, mk, chucVu);
			if(nhanVienDao.themNhanVien(nv)) {
				return true;
			}
			return false;
	}
	public boolean valiData() {
		String tenNV = txtTenNV.getText();
		String cmnd = txtcmnd.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		String tk = txtTK.getText();
		String mk = txtMK.getText();
		if (tenNV.length() == 0) {
			showMessage(txtTenNV, "Nhập Tên Nhân Viên!");
			return false;
		}
		if (!tenNV.matches(
				"" + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
						+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
						+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
			showMessage(txtTenNV,
					"Tên Nhân viên bao gồm chứ cái, chữ số tiếng việt, không bao gồm kí tự đặc biệt!");
			return false;
		}
		if (cmnd.length() == 0) {
			showMessage(txtcmnd, "Nhập số Chứng Minh Nhân Dân!");
			return false;
		}
		if (!cmnd.matches("^[0-9]{9,12}")) {
			showMessage(txtcmnd, "CMND là số nguyên (9-10 số)");
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
		if(cmbchucVu.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(this, "vui lòng chọn chức vụ!");
			return false;
		}
		if(cmbGioiTinh.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn tỉnh giới tính!");
			return false;
		}
		if(mk.trim().equals("")) {
			showMessage(txtMK, "Chưa nhập mật khẩu");
			return false;
		}
		if(!mk.matches("^.{8,}")){
			showMessage(txtMK,"Gồm ít nhất 8 kí tự");
			return false;
		}
		if(tk.trim().equals("")) {
			showMessage(txtTK, "Chưa nhập nhập tài khoản");
			return false;
		}
		if(!tk.matches("[a-zA-Z0-9]{5,25}$")) {
			showMessage(txtTK, "Tài khoản từ 5 đến 25 ký tự, bao gồm chữ cái và số.");
			return false;
		}
		return true;
	}
	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}
}
