package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import connectDB.connectDB;
import entity.NhanVien;
import java.awt.Toolkit;

public class GD_TrangChu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JMenuBar menuBar;
	JMenu mnDanhMuc;
	JMenuItem mntmKhachHang;
	JMenuItem mntmNhanVien;
	JMenuItem mntmDichVu;
	JMenuItem mntmPhongHat;
	JMenu mnXuLy;
	JMenuItem mntmDatPhongHat;
	JMenu mnTimKiem;
	JMenuItem mntmTimKiemPDP;
	JMenuItem mntmTimKiemDV;
	JMenuItem mntmTimKiemNV;
	JMenuItem mntmTimKiemKH;
	JMenuItem mntmTimKiemPhong;
	JMenuItem mntmTimKiemHoaDon;
	JMenuItem mntmTimKiemTK;
	JMenu mnThongKe;
	JMenuItem mntmTKDoanhThu;
	JMenuItem mntmTKKhachHang;
	JMenu mnHeThong;
	JMenuItem mntmTrangChu;
	JMenuItem mntmTaiKhoan;
	JMenuItem mntmTroGiup;
	JMenuItem mntmDangXuat;
	JMenuItem mntmThoat;
	JPanel pnlChinh;
	JPanel pnlTrangChu;
	JPanel pnlThongTin;
	JLabel lblNhanVienHienTai;
	private NhanVien nvHienTai;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GD_TrangChu frame = new GD_TrangChu( new NhanVien("NV001", null, null, null, null, true,"QL"));
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GD_TrangChu( NhanVien nv ) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GD_TrangChu.class.getResource("/img/logo-Nice-karaoke.jpg")));
		setTitle("Quản lí karaoke");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(0, 0, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(1920, 1080);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.inactiveCaptionBorder);
		menuBar.setBounds(0, 0, 1557, 51);
		contentPane.add(menuBar);

		mnDanhMuc = new JMenu("Danh mục");
		mnDanhMuc.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-category-30 (1).png")));
		mnDanhMuc.setBackground(Color.WHITE);
		mnDanhMuc.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(mnDanhMuc);

		mntmKhachHang = new JMenuItem("Khách hàng");
		mntmKhachHang.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-customer-30.png")));
		mntmKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnDanhMuc.add(mntmKhachHang);

		mntmNhanVien = new JMenuItem("Nhân viên");
		mntmNhanVien.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-employee-30.png")));
		mntmNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnDanhMuc.add(mntmNhanVien);

		mntmDichVu = new JMenuItem("Dịch vụ");
		mntmDichVu.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-service-30.png")));
		mntmDichVu.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnDanhMuc.add(mntmDichVu);

		mntmPhongHat = new JMenuItem("Phòng hát");
		mntmPhongHat.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-sofa-with-buttons-30.png")));
		mntmPhongHat.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnDanhMuc.add(mntmPhongHat);

		mnXuLy = new JMenu("Xử lý");
		mnXuLy.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-handle-30.png")));
		mnXuLy.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(mnXuLy);

		mntmDatPhongHat = new JMenuItem("Đặt phòng hát");
		mntmDatPhongHat.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-living-room-30.png")));
		mntmDatPhongHat.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnXuLy.add(mntmDatPhongHat);

		mnTimKiem = new JMenu("Tìm kiếm");
		mnTimKiem.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-view-30.png")));
		mnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(mnTimKiem);

		mntmTimKiemPDP = new JMenuItem("Tìm kiếm phiếu đặt phòng");
		mntmTimKiemPDP.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-package-search-30.png")));
		mntmTimKiemPDP.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnTimKiem.add(mntmTimKiemPDP);

		mntmTimKiemDV = new JMenuItem("Tìm kiếm dịch vụ");
		mntmTimKiemDV.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-package-search-30.png")));
		mntmTimKiemDV.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnTimKiem.add(mntmTimKiemDV);

		mntmTimKiemNV = new JMenuItem("Tìm kiếm nhân viên");
		mntmTimKiemNV.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-package-search-30.png")));
		mntmTimKiemNV.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnTimKiem.add(mntmTimKiemNV);

		mntmTimKiemKH = new JMenuItem("Tìm kiếm khách hàng");
		mntmTimKiemKH.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-package-search-30.png")));
		mntmTimKiemKH.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnTimKiem.add(mntmTimKiemKH);

		mntmTimKiemPhong = new JMenuItem("Tìm kiếm phòng");
		mntmTimKiemPhong.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-package-search-30.png")));
		mntmTimKiemPhong.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnTimKiem.add(mntmTimKiemPhong);

		mntmTimKiemHoaDon = new JMenuItem("Tìm kiếm hóa đơn");
		mntmTimKiemHoaDon.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-package-search-30.png")));
		mntmTimKiemHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnTimKiem.add(mntmTimKiemHoaDon);

		mntmTimKiemTK = new JMenuItem("Tìm kiếm tài khoản");
		mntmTimKiemTK.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-package-search-30.png")));
		mntmTimKiemTK.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnTimKiem.add(mntmTimKiemTK);

		mnThongKe = new JMenu("Thống kê");
		mnThongKe.setIcon(
				new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-analytics-30.png")));
		mnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(mnThongKe);

		mntmTKDoanhThu = new JMenuItem("Thống kê doanh thu");
		mntmTKDoanhThu.setIcon(
				new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-profit-30.png")));
		mntmTKDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnThongKe.add(mntmTKDoanhThu);

		mntmTKKhachHang = new JMenuItem("Thống kê khách hàng");
		mntmTKKhachHang.setIcon(
				new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-budget-30.png")));
		mntmTKKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnThongKe.add(mntmTKKhachHang);

		mnHeThong = new JMenu("Hệ thống");
		mnHeThong.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-system-information-30.png")));
		mnHeThong.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(mnHeThong);

		mntmTrangChu = new JMenuItem("Trang chủ");
		mntmTrangChu.setIcon(
				new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-home-30.png")));
		mntmTrangChu.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnHeThong.add(mntmTrangChu);

		mntmTaiKhoan = new JMenuItem("Tài khoản");
		mntmTaiKhoan.setIcon(
				new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-account-30.png")));
		mntmTaiKhoan.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnHeThong.add(mntmTaiKhoan);

		mntmTroGiup = new JMenuItem("Trợ giúp");
		mntmTroGiup.setIcon(
				new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-help-30.png")));
		mntmTroGiup.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnHeThong.add(mntmTroGiup);

		mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-logout-rounded-30.png")));
		mntmDangXuat.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnHeThong.add(mntmDangXuat);

		mntmThoat = new JMenuItem("Thoát");
		mntmThoat.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/exit.png")));
		mntmThoat.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnHeThong.add(mntmThoat);

		pnlChinh = new JPanel();
		pnlChinh.setBounds(0, 48, 1540, 797);
		contentPane.add(pnlChinh);
		pnlChinh.setLayout(null);

		pnlTrangChu = new JPanel();
		pnlTrangChu.setBounds(0, 0, 1540, 787);
		pnlChinh.add(pnlTrangChu);
		pnlTrangChu.setLayout(null);

		pnlThongTin = new JPanel();
		pnlThongTin.setBorder(null);
		pnlThongTin.setLayout(null);
		pnlThongTin.setBackground(Color.WHITE);
		pnlThongTin.setBounds(184, 186, 759, 439);
		pnlTrangChu.add(pnlThongTin);

		JLabel lblNewLabel = new JLabel("Karaoke Nice");
		lblNewLabel.setForeground(new Color(64, 81, 130));
		lblNewLabel.setFont(new Font("Stencil Std", Font.ITALIC, 26));
		lblNewLabel.setBounds(293, 10, 289, 77);
		pnlThongTin.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/Screenshot 2023-10-11 154602.png")));
		lblNewLabel_1.setBounds(39, 88, 244, 245);
		pnlThongTin.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("12 Nguyễn Văn Bảo, phường 4, Gò Vấp, TP.HCM");
		lblNewLabel_2.setForeground(new Color(64, 81, 130));
		lblNewLabel_2.setFont(new Font("Sitka Small", Font.PLAIN, 17));
		lblNewLabel_2.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-position-30.png")));
		lblNewLabel_2.setBounds(263, 131, 455, 37);
		pnlThongTin.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("0866952340");
		lblNewLabel_3.setForeground(new Color(64, 81, 130));
		lblNewLabel_3.setFont(new Font("Sitka Small", Font.PLAIN, 17));
		lblNewLabel_3.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/icons8-phone-30.png")));
		lblNewLabel_3.setBounds(263, 234, 306, 37);
		pnlThongTin.add(lblNewLabel_3);

		JLabel lblnvv = new JLabel("Nhân Viên :");
		lblnvv.setFont(new Font("Stencil", Font.BOLD, 10));
		lblnvv.setBounds(28, 331, 75, 37);
		pnlThongTin.add(lblnvv);
		
		JLabel lblTenNhanVien = new JLabel("");
		lblTenNhanVien.setBounds(98, 343, 122, 13);
		pnlThongTin.add(lblTenNhanVien);

		lblNhanVienHienTai = new JLabel("");
		lblNhanVienHienTai.setBounds(108, 337, 137, 22);
		pnlThongTin.add(lblNhanVienHienTai);
		lblNhanVienHienTai.setText(nv.getTenNV());

		JLabel lblImg = new JLabel("New label");
		lblImg.setBackground(new Color(240, 240, 240));
		lblImg.setIcon(new ImageIcon(GD_TrangChu.class.getResource("/img/HomeMenu.png")));
		lblImg.setBounds(0, 0, 1540, 787);
		pnlTrangChu.add(lblImg);

		mntmKhachHang.addActionListener(this);
		mntmTrangChu.addActionListener(this);
		mntmNhanVien.addActionListener(this);
		mntmDichVu.addActionListener(this);
		mntmPhongHat.addActionListener(this);
		mntmTimKiemPDP.addActionListener(this);
		mntmTimKiemDV.addActionListener(this);
		mntmTimKiemNV.addActionListener(this);
		mntmTimKiemKH.addActionListener(this);
		mntmTimKiemPhong.addActionListener(this);
		mntmTimKiemHoaDon.addActionListener(this);
		mntmTimKiemTK.addActionListener(this);
		mntmTaiKhoan.addActionListener(this);
		mntmDatPhongHat.addActionListener(this);
		mntmTKDoanhThu.addActionListener(this);
		mntmTKKhachHang.addActionListener(this);

		mntmDangXuat.addActionListener(this);
		mntmThoat.addActionListener(this);
		mntmTroGiup.addActionListener(this);
		
		nvHienTai = nv;
		phanQuyenChucNang(nv);
		
	}

	public void clearPanel() {
		mnDanhMuc.setSelected(false);
		mnHeThong.setSelected(false);
		mnThongKe.setSelected(false);
		mnTimKiem.setSelected(false);
		mnXuLy.setSelected(false);

	}

	public void thayDoiPanelChinh(JComponent component, JMenuItem menu) {
		clearPanel();
		menu.setSelected(true);
		this.pnlChinh.removeAll();
		this.pnlChinh.repaint();
		this.pnlChinh.revalidate();

		this.pnlChinh.add(component);
		this.pnlChinh.repaint();
		this.pnlChinh.revalidate();

	}

	public void ChonKhachHang() {
		this.thayDoiPanelChinh(new QLKhachHang(), mntmKhachHang);

	}

	public void chonTrangChu() {
		this.thayDoiPanelChinh(pnlTrangChu, mntmTrangChu);
	}

	public void chonQLNhanVien() {
		this.thayDoiPanelChinh(new QLNhanVien(), mntmNhanVien);
	}

	public void chonQLDichVu() {
		this.thayDoiPanelChinh(new QLDichVu(), mntmDichVu);
	}

	public void chonQLPhong() {
		this.thayDoiPanelChinh(new QLPhong(), mntmPhongHat);
	}

	public void chonTimKiemPhieuDatPhong() {
		this.thayDoiPanelChinh(new TimKiemPhieuDatPhong(nvHienTai), mntmTimKiemPDP);
	}

	public void chonTimKiemDichVu() {
		this.thayDoiPanelChinh(new timKiemDichVu(nvHienTai), mntmTimKiemDV);
	}

	public void chonTimKiemNhanVien() {
		this.thayDoiPanelChinh(new TimKiemNhanVien(), mntmTimKiemNV);
	}

	public void chonTimKiemKH() {
		this.thayDoiPanelChinh(new timKiemKhachHang(), mntmTimKiemKH);
	}

	public void chonTimKiemPhong() {
		this.thayDoiPanelChinh(new TimKiemPhong(), mntmTimKiemPhong);
	}

	public void chonTimKiemHoaDon() {
		this.thayDoiPanelChinh(new TimKiemHoaDon(), mntmTimKiemHoaDon);
	}

	public void chonTimKiemTaiKhoan() {
		this.thayDoiPanelChinh(new TimKiemTaiKhoan(), mntmTimKiemTK);
	}

	public void chonTaiKhoan() {
		this.thayDoiPanelChinh(new GD_ThongTinTaiKhoan(nvHienTai), mntmTaiKhoan);
	}

	public void windowClosing(WindowEvent e) {
		dispose();
	}

	public void ChonDatPhong() {
		this.thayDoiPanelChinh(new QLDatPhong(nvHienTai), mntmDatPhongHat);
	}

	public void chonThongKeDoanhThu() {
		this.thayDoiPanelChinh(new ThongKeDoanhThu(), mntmTKDoanhThu);
	}

	public void chonThongKeKH() {
		this.thayDoiPanelChinh(new ThongKeKhachHang(), mntmTKKhachHang);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(mntmKhachHang)) {
			ChonKhachHang();
		} else if (o.equals(mntmTrangChu)) {
			chonTrangChu();
		} else if (o.equals(mntmNhanVien)) {
			if(nvHienTai.getChucVu().equals("QL")) {
				chonQLNhanVien();
			}
			else {
				JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập chức năng này");
			}
			
		} else if (o.equals(mntmDichVu)) {
			chonQLDichVu();
		} else if (o.equals(mntmPhongHat)) {
			chonQLPhong();
		} else if (o.equals(mntmTimKiemPDP)) {
			chonTimKiemPhieuDatPhong();
		} else if (o.equals(mntmTimKiemDV)) {
			chonTimKiemDichVu();
		} else if (o.equals(mntmTimKiemNV)) {
			chonTimKiemNhanVien();
		} else if (o.equals(mntmTimKiemKH)) {
			chonTimKiemKH();
		} else if (o.equals(mntmTimKiemPhong)) {
			chonTimKiemPhong();
		} else if (o.equals(mntmTimKiemHoaDon)) {
			chonTimKiemHoaDon();
		} else if (o.equals(mntmTimKiemTK)) {
			chonTimKiemTaiKhoan();
		} else if (o.equals(mntmTaiKhoan)) {
			chonTaiKhoan();
		} else if (o.equals(mntmDatPhongHat)) {
			ChonDatPhong();
		} else if (o.equals(mntmTKDoanhThu)) {
			chonThongKeDoanhThu();
		} else if (o.equals(mntmTKKhachHang)) {
			chonThongKeKH();
		}

		else if (o.equals(mntmDangXuat)) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất ?", null,
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//				connectDB.getInstance().disConnect();
				GD_Login frame = null;
				try {
					frame = new GD_Login();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				;
				dispose();
			}
		} else if (o.equals(mntmThoat)) {
			System.exit(0);
		} else if (o.equals(mntmTroGiup)) {
			String url = "";
			url = System.getProperty("user.dir") + "\\help\\HDSD.pdf";
			File file = new File(url);
			Desktop dt = Desktop.getDesktop();
			try {
				dt.open(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private void phanQuyenChucNang(NhanVien nv) {
		if(!nv.getChucVu().equals("QL")) {
			/*
			 * nếu không phải quản lí sẽ remove mấy chức năng này
			 */
			mnDanhMuc.remove(mntmNhanVien);
			mnDanhMuc.remove(mntmDichVu);
			mnDanhMuc.remove(mntmPhongHat);
			mnTimKiem.remove(mntmTimKiemNV);
			mnTimKiem.remove(mntmTimKiemTK);
		}
	}
}
