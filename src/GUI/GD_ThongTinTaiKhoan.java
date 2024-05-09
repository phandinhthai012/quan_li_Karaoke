package GUI;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import DAO.DAO_NhanVien;
import entity.NhanVien;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class GD_ThongTinTaiKhoan extends JPanel implements ActionListener {
	private JTextField txtHoTen;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtCCCD;
	private JTextField txtMaNV;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JPasswordField txtMKC;
	private JPasswordField txtMKM;
	private JPasswordField txtXNMK;
	private JComboBox cmbGioiTinh;
	private JComboBox cmbChucVu;
	private JLabel lblHinhAnh;
	private JButton btnCapNhat;
	private JButton btnLuu;
	private JButton btnLuuMK, btnDoiMk;
	private JRadioButton rdbtnHienMK;
	private NhanVien nvHienTai;
	private DAO_NhanVien nhanVienDAO;

	/**
	 * Create the panel.
	 */
	public GD_ThongTinTaiKhoan(NhanVien nv) {
		setLayout(null);
		setBounds(0, 0, 1540, 779);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1520, 731);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(225, 237, 245));
		panel_1.setBorder(
				new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Th\u00F4ng Tin T\u00E0i Kho\u1EA3n",
						TitledBorder.CENTER, TitledBorder.TOP, null, new Color(130, 127, 143)));
		panel_1.setBounds(10, 10, 1500, 336);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Họ Tên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(22, 35, 134, 25);
		panel_1.add(lblNewLabel);

		JLabel lblaCh = new JLabel("Địa Chỉ");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblaCh.setBounds(22, 104, 134, 25);
		panel_1.add(lblaCh);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSinThoi.setBounds(22, 171, 134, 25);
		panel_1.add(lblSinThoi);

		JLabel lblCnCcCng = new JLabel("Chứng Minh Nhân Dân");
		lblCnCcCng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCnCcCng.setBounds(22, 240, 185, 25);
		panel_1.add(lblCnCcCng);

		JLabel lblGiiTnh = new JLabel("Giới Tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGiiTnh.setBounds(741, 104, 134, 25);
		panel_1.add(lblGiiTnh);

		JLabel lblChcV = new JLabel("Chức Vụ");
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChcV.setBounds(741, 179, 134, 25);
		panel_1.add(lblChcV);

		txtHoTen = new JTextField();
		txtHoTen.setEditable(false);
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtHoTen.setBounds(183, 40, 455, 19);
		panel_1.add(txtHoTen);
		txtHoTen.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(183, 109, 455, 19);
		panel_1.add(txtDiaChi);

		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSDT.setColumns(10);
		txtSDT.setBounds(183, 176, 455, 19);
		panel_1.add(txtSDT);

		txtCCCD = new JTextField();
		txtCCCD.setEditable(false);
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(183, 245, 455, 19);
		panel_1.add(txtCCCD);

		cmbGioiTinh = new JComboBox();
		cmbGioiTinh.setEditable(true);
		cmbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cmbGioiTinh.setBounds(873, 108, 574, 21);
		cmbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Giới Tính", "Nam", "Nữ" }));
		panel_1.add(cmbGioiTinh);

		cmbChucVu = new JComboBox();
		cmbChucVu.setEditable(true);
		cmbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cmbChucVu.setBounds(873, 175, 574, 21);
		cmbChucVu.setModel(new DefaultComboBoxModel(new String[] { "Chức Vụ", "Quản lí", "Nhân viên" }));
		panel_1.add(cmbChucVu);

		JLabel lblMNhnVin = new JLabel("Mã Nhân Viên");
		lblMNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMNhnVin.setBounds(741, 35, 134, 25);
		panel_1.add(lblMNhnVin);

		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(873, 40, 574, 19);
		panel_1.add(txtMaNV);

		btnCapNhat = new JButton("Cập Nhập");
		btnCapNhat.setBackground(new Color(4, 163, 249));
		btnCapNhat.setBounds(585, 293, 134, 33);
		btnCapNhat.setBorder(new EmptyBorder(1, 1, 1, 1));
		panel_1.add(btnCapNhat);

		btnLuu = new JButton("Lưu ");
		btnLuu.setBackground(Color.WHITE);
		btnLuu.setBorder(new LineBorder(new Color(4, 163, 249), 2));
		btnLuu.setEnabled(false);
		btnLuu.setBounds(758, 293, 134, 33);
		panel_1.add(btnLuu);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u0110\u1ED5i M\u1EADt Kh\u1EA9u", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 374, 1500, 347);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblMtKhuC = new JLabel("Mật Khẩu Cũ");
		lblMtKhuC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMtKhuC.setBounds(24, 53, 134, 25);
		panel_2.add(lblMtKhuC);

		JLabel lblMtKhuMi = new JLabel("Mật Khẩu Mới");
		lblMtKhuMi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMtKhuMi.setBounds(24, 117, 134, 25);
		panel_2.add(lblMtKhuMi);

		JLabel lblXcNhnMt = new JLabel("Xác Nhận Mật Khẩu Mới");
		lblXcNhnMt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblXcNhnMt.setBounds(24, 210, 170, 25);
		panel_2.add(lblXcNhnMt);

		txtMKC = new JPasswordField();
		txtMKC.setEditable(false);
		txtMKC.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtMKC.setColumns(10);
		txtMKC.setBounds(205, 58, 1218, 19);
		panel_2.add(txtMKC);

		txtMKM = new JPasswordField();
		txtMKM.setEditable(false);
		txtMKM.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtMKM.setColumns(10);
		txtMKM.setBounds(205, 122, 1218, 19);
		panel_2.add(txtMKM);

		txtXNMK = new JPasswordField();
		txtXNMK.setEditable(false);
		txtXNMK.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtXNMK.setColumns(10);
		txtXNMK.setBounds(204, 215, 1219, 19);
		panel_2.add(txtXNMK);

		btnDoiMk = new JButton("Đổi Mật Khẩu");
		btnDoiMk.setBorder(new EmptyBorder(1, 1, 1, 1));
		btnDoiMk.setBackground(new Color(4, 163, 249));
		btnDoiMk.setBounds(556, 293, 134, 33);
		panel_2.add(btnDoiMk);

		btnLuuMK = new JButton("Lưu Mật Khẩu");
		btnLuuMK.setBackground(Color.WHITE);
		btnLuuMK.setEnabled(false);
		btnLuuMK.setBorder(new LineBorder(new Color(4, 163, 249), 2));
		btnLuuMK.setBounds(729, 293, 134, 33);
		panel_2.add(btnLuuMK);

		rdbtnHienMK = new JRadioButton("Hiển Thị Mật Khẩu");
		rdbtnHienMK.setBounds(1305, 299, 149, 21);
		panel_2.add(rdbtnHienMK);
		rdbtnHienMK.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnHienMK.isSelected()) {
					txtMKC.setEchoChar((char) 0);
					txtMKM.setEchoChar((char) 0);
					txtXNMK.setEchoChar((char) 0);
				} else if (rdbtnHienMK.isSelected() == false) {
					txtMKC.setEchoChar('*');
					txtMKM.setEchoChar('*');
					txtXNMK.setEchoChar('*');
				}
			}
		});
		nvHienTai = nv;
		loadThongTinNhanVien(nvHienTai);
		nhanVienDAO = new DAO_NhanVien();
		btnCapNhat.addActionListener(this);
		btnLuu.addActionListener(this);
		btnDoiMk.addActionListener(this);
		btnLuuMK.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCapNhat)) {
			if (btnCapNhat.getText().equals("Cập Nhập")) {
				txtHoTen.setEditable(true);
				txtCCCD.setEditable(true);
				txtDiaChi.setEditable(true);
				txtMaNV.setEditable(true);
				txtSDT.setEditable(true);
				cmbChucVu.setEditable(true);
//				cmbChucVu.setSelectedIndex(0);
				cmbGioiTinh.setEditable(true);
//				cmbGioiTinh.setSelectedIndex(0);
				btnLuu.setEnabled(true);
				btnCapNhat.setText("Hủy");
			} else {
				txtHoTen.setEditable(false);
				txtCCCD.setEditable(false);
				txtDiaChi.setEditable(false);
				txtMaNV.setEditable(false);
				txtSDT.setEditable(false);
				cmbChucVu.setEditable(false);
//				cmbChucVu.setSelectedIndex(0);
				cmbGioiTinh.setEditable(false);
//				cmbGioiTinh.setSelectedIndex(0);
				btnLuu.setEnabled(false);
				btnCapNhat.setText("Cập nhật");
			}

		} else if (o.equals(btnLuu)) {
			if (btnCapNhat.getText().equals("Hủy")) {
				if (valiData()) {
					String maNv = txtMaNV.getText();
					String tenNv = txtHoTen.getText();
					String sdt = txtSDT.getText();
					String cmnd = txtCCCD.getText();
					String diaChi = txtDiaChi.getText();
					NhanVien nv = nhanVienDAO.timNhanVienTheoMaNV(maNv);
					boolean gt = true;
					if (cmbGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nữ")) {
						gt = false;
					}
					String cv = cmbChucVu.getSelectedItem().toString();
					nv.setTenNV(tenNv);
					nv.setChucVu(cv);
					nv.setCmnd(cmnd);
					nv.setDiaChi(diaChi);
					nv.setGioiTinh(gt);
					nv.setSdt(sdt);
					if (nhanVienDAO.capNhapNhanVien(nv)) {
						JOptionPane.showMessageDialog(this, "Cập nhập Thông Tin Thành Công");
						txtHoTen.setEditable(false);
						txtCCCD.setEditable(false);
						txtDiaChi.setEditable(false);
						txtMaNV.setEditable(false);
						txtSDT.setEditable(false);
						cmbChucVu.setEditable(false);
//						cmbChucVu.setSelectedIndex(0);
						cmbGioiTinh.setEditable(false);
//						cmbGioiTinh.setSelectedIndex(0);
						btnLuu.setEnabled(false);
						btnCapNhat.setText("Cập nhật");
					} else {
						JOptionPane.showMessageDialog(this, "Lỗi cập nhập thông Tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		} else if (o.equals(btnDoiMk)) {
			if (btnDoiMk.getText().equals("Đổi Mật Khẩu")) {
				txtMKC.setEditable(true);
				txtMKM.setEditable(true);
				txtXNMK.setEditable(true);
				btnDoiMk.setText("Hủy");
				btnLuuMK.setEnabled(true);
			} else {
				txtMKC.setEditable(false);
				txtMKM.setEditable(false);
				txtXNMK.setEditable(false);
				btnDoiMk.setText("Đổi Mật Khẩu");
				btnLuuMK.setEnabled(false);
			}
		} else if (o.equals(btnLuuMK)) {
			if(valiDataMK()) {
				String ten =nvHienTai.getTaiKhoan();
				String mk = txtMKM.getText().trim();
				if(nhanVienDAO.capNhanTaiKhoanMatKhauChoNhanVien(nvHienTai.getMaNV(), ten, mk)) {
					JOptionPane.showMessageDialog(this, "Đổi Mật khẩu thành công");
					txtMKC.setText("");
					txtMKM.setText("");
					txtXNMK.setText("");
					txtMKC.setEditable(false);
					txtMKM.setEditable(false);
					txtXNMK.setEditable(false);
					
					btnDoiMk.setText("Đổi mật khẩu");
					btnLuuMK.setEnabled(false);
				}else {
					JOptionPane.showMessageDialog(this, "Đổi mật khẩu không thành công!");
				}
			}
		}

	}

	public void loadThongTinNhanVien(NhanVien x) {
		txtHoTen.setText(x.getTenNV());
		txtCCCD.setText(x.getCmnd());
		txtDiaChi.setText(x.getDiaChi());
		txtMaNV.setText(x.getMaNV());
		txtSDT.setText(x.getSdt());
		if (x.isGioiTinh()) {
			cmbGioiTinh.setSelectedIndex(1);
		} else {
			cmbGioiTinh.setSelectedIndex(2);
		}
		if (x.getChucVu().equals("QL")) {
			cmbChucVu.setSelectedIndex(1);
		} else {
			cmbChucVu.setSelectedIndex(2);
		}
	}

	public boolean valiData() {
		String tenNV = txtHoTen.getText();
		String cmnd = txtCCCD.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		if (tenNV.length() == 0) {
			showMessage(txtHoTen, "Nhập Tên Nhân Viên!");
			return false;
		}
		if (!tenNV.matches("" + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
				+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
				+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
			showMessage(txtHoTen, "Tên Nhân viên bao gồm chứ cái, chữ số tiếng việt, không bao gồm kí tự đặc biệt!");
			return false;
		}
		if (cmnd.length() == 0) {
			showMessage(txtCCCD, "Nhập số Chứng Minh Nhân Dân!");
			return false;
		}
		if (!cmnd.matches("^[0-9]{9,12}")) {
			showMessage(txtCCCD, "CMND là số nguyên (9-10 số)");
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
		if (cmbChucVu.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "vui lòng chọn chức vụ!");
			return false;
		}
		if (cmbGioiTinh.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn tỉnh giới tính!");
			return false;
		}

		return true;
	}

	public boolean valiDataMK() {
		String manv = txtMaNV.getText();
		NhanVien nvDoiMK = nhanVienDAO.timNhanVienTheoMaNV(manv);
		String mkc = nvDoiMK.getMatKhau();
		String matKhauCu = txtMKC.getText().trim();
		String matKhauMoi = txtMKM.getText().trim();
		String xacNhanMK = txtXNMK.getText().trim();
		if (matKhauCu.trim().equals("")) {
			showMessage(txtMKC, "Chưa nhập mật khẩu");
			return false;
		}
		if (!matKhauCu.equals(mkc)) {
			showMessage(txtMKC, "Mật khẩu cũ không đúng!");
			return false;
		}
		if (matKhauMoi.length() == 0) {
			showMessage(txtMKM, "Nhập mật khẩu mới!");
			return false;
		}
		if(!matKhauMoi.matches("^.{8,}")){
			showMessage(txtMKM,"Gồm ít nhất 8 kí tự");
			return false;
		}
		if (xacNhanMK.length() == 0) {
			showMessage(txtXNMK, "Nhập xác nhân mật khẩu mới!");
			return false;
		}
		if (!matKhauMoi.equals(xacNhanMK)) {
			showMessage(txtMKC, "Xác nhận mật khẩu mới không đúng!");
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
