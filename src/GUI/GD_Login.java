package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.border.DropShadowBorder;

import DAO.DAO_NhanVien;
import GUI.ForgetPassword.GD_QuenMatKhau;
import connectDB.connectDB;
import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class GD_Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	JButton btnThoat, btnDangNhap;
	private JPasswordField txtMatKhau;
	private NhanVien nv;
	DAO_NhanVien DAO_NV;
	ArrayList<NhanVien> dsNV;
	private JLabel lblQuenMK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_Login frame = new GD_Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public GD_Login() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GD_Login.class.getResource("/img/logo-Nice-karaoke.jpg")));
		setTitle("Đăng Nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 880, 521);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 244, 249));
		panel.setBounds(195, 159, 649, 380);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblDangNhap = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
		lblDangNhap.setForeground(new Color(4, 78, 146));
		lblDangNhap.setFont(new Font("Minion Pro Cond", Font.BOLD, 30));
		lblDangNhap.setBounds(180, 10, 313, 58);
		panel.add(lblDangNhap);

		JLabel lblTaiKhoan = new JLabel("Tài khoản");
		lblTaiKhoan.setForeground(new Color(141, 141, 141));
		lblTaiKhoan.setFont(new Font("Segoe UI Semilight", Font.BOLD, 16));
		lblTaiKhoan.setBounds(85, 105, 85, 36);
		panel.add(lblTaiKhoan);

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setBounds(180, 105, 290, 36);
		panel.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);

		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setForeground(new Color(141, 141, 141));
		lblMatKhau.setFont(new Font("Segoe UI Semilight", Font.BOLD, 16));
		lblMatKhau.setBounds(85, 189, 85, 36);
		panel.add(lblMatKhau);

		btnThoat = new JButton("Thoát");
		btnThoat.setForeground(Color.GRAY);
		btnThoat.setBackground(Color.WHITE);
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThoat.setBounds(364, 266, 106, 45);
		panel.add(btnThoat);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setForeground(new Color(255, 255, 255));
		btnDangNhap.setBackground(new Color(255, 90, 0));
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDangNhap.setBounds(187, 266, 106, 45);
		btnDangNhap.setFocusPainted(false);
		btnDangNhap.setBorder(new EmptyBorder(5, 5, 5, 5)); // Optional: Add some padding
		btnThoat.setBorder(new EmptyBorder(5, 5, 5, 5));

		panel.add(btnDangNhap);

		///

		DropShadowBorder shadowBorder = new DropShadowBorder();
		shadowBorder.setCornerSize(20);
		shadowBorder.setShadowColor(new Color(220, 86, 13));
		btnDangNhap.setBorder(shadowBorder);
		btnThoat.setBorder(shadowBorder);

		///
		JLabel lblIMG = new JLabel("");
		lblIMG.setIcon(new ImageIcon(GD_Login.class.getResource("/img/icons8-user-90.png")));
		lblIMG.setBounds(511, 115, 98, 108);
		panel.add(lblIMG);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(180, 192, 290, 36);
		panel.add(txtMatKhau);

		lblQuenMK = new JLabel("Bạn Quên Mật Khẩu?");
		lblQuenMK.setForeground(Color.GRAY);
		lblQuenMK.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuenMK.setBounds(483, 332, 156, 38);
		lblQuenMK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Xử lý sự kiện khi click vào lbl quenmk sẽ xuất hiện form để lấy lại mk
				lblQuenMK.setForeground(new Color(251, 67, 67));
				GD_QuenMatKhau gd = new GD_QuenMatKhau();
				gd.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// Hiệu ứng khi di chuột vào lbl quenMk sẽ chuyển sang màu khác
				lblQuenMK.setForeground(new Color(20, 146, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Hiệu ứng khi di chuột ra khỏi sẽ chuyển về mau bàn đầu
				lblQuenMK.setForeground(Color.GRAY);
			}
		});
		panel.add(lblQuenMK);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblNewLabel.setBounds(0, 0, 2221, 835);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(GD_Login.class.getResource("/img/HomeMenu.png")));
		setSize(1920, 1080);
		DAO_NV = new DAO_NhanVien();
//		dsNV = DAO_NV.getALLNhanVien();

		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	public boolean kiemTraRangBuoc() {
		String userName = txtTaiKhoan.getText().trim();
		String passWord = txtMatKhau.getText();
		if (userName.equals("") || passWord.equals("")) {
			if (userName.equals("")) {
				thongBaoLoi("Bạn chưa nhập tên tài khoản", "Lỗi đăng nhập");
				return false;
			} else {
				thongBaoLoi("Bạn chưa nhập mật khẩu", "Lỗi đăng nhập");
				return false;
			}
		}
		// tên đăng nhập chỉ chứa số và chữ va không có ký tự đặc biệt
		// độ dài tên đăng nhập từ 5-30
		boolean rangbuocTenTaiKhoan;
		rangbuocTenTaiKhoan = userName.matches("[a-zA-z0-9 ]{5,30}");
		if (rangbuocTenTaiKhoan) {
			return true;
		}
		return false;
	}
	/**
	 * kiểm tra mật khẩu tài khoản trong hệ thống không
	 * @return
	 */
	public boolean kiemTraTaiKhoan() {
		String taiKhoan = txtTaiKhoan.getText();
		String matkhau = txtMatKhau.getText();
		System.out.println(taiKhoan + matkhau);

		nv = DAO_NV.timKiemNhanVienTheoTaiKhoan(taiKhoan, matkhau);
		if (nv != null) {
			JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
			return true;
		}
		thongBaoLoi("Tài Khoản mật khẩu không đúng", "Lỗi tài Khoản");
		return false;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			System.exit(0);
		} else if (o.equals(btnDangNhap)) {
			if (!kiemTraRangBuoc()) {
				return;
			}
			if (kiemTraTaiKhoan()) {
				String taiKhoan = txtTaiKhoan.getText();
				String matkhau = txtMatKhau.getText();
				NhanVien x = DAO_NV.timKiemNhanVienTheoTaiKhoan(taiKhoan, matkhau);
				GD_TrangChu trangChu = new GD_TrangChu(x);
				trangChu.setVisible(true);
//				trangChu.setResizable(false);
				trangChu.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		}

	}
}
