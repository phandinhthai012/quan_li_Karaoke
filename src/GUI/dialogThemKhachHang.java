package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DAO_KhachHang;
import entity.KhachHang;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;

public class dialogThemKhachHang extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JButton btnThem, btnLamMoi, btnHuy;
	private DAO_KhachHang khachHangDao;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			dialogThemKhachHang dialog = new dialogThemKhachHang();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public dialogThemKhachHang(String makh) {
		setBounds(100, 100, 761, 353);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(SystemColor.activeCaption);
			panel.setBounds(0, 0, 770, 48);
			contentPanel.add(panel);
			{
				JLabel lblNewLabel = new JLabel("Thêm Khách Hàng");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel.setBounds(327, 10, 143, 28);
				panel.add(lblNewLabel);
			}
		}

		JLabel lblMKhchHng = new JLabel("Mã Khách Hàng:");
		lblMKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMKhchHng.setBounds(10, 58, 116, 22);
		contentPanel.add(lblMKhchHng);

		txtMaKH = new JTextField();
		txtMaKH.setText("<dynamic>");
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(124, 58, 175, 23);
		contentPanel.add(txtMaKH);

		JLabel lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnKhchHng.setBounds(10, 105, 116, 22);
		contentPanel.add(lblTnKhchHng);

		txtTenKH = new JTextField();
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(124, 105, 556, 23);
		contentPanel.add(txtTenKH);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT.setBounds(10, 164, 116, 22);
		contentPanel.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(124, 164, 556, 23);
		contentPanel.add(txtSDT);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(124, 218, 556, 23);
		contentPanel.add(txtDiaChi);

		JLabel lblaCh = new JLabel("Địa Chỉ:");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblaCh.setBounds(10, 216, 116, 22);
		contentPanel.add(lblaCh);

		btnHuy = new JButton("Hủy ");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHuy.setBackground(new Color(255, 87, 87));
		btnHuy.setBounds(10, 273, 80, 33);
		contentPanel.add(btnHuy);

		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoi.setBackground(new Color(228, 218, 167));
		btnLamMoi.setBounds(428, 273, 116, 33);
		contentPanel.add(btnLamMoi);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBackground(new Color(90, 220, 148));
		btnThem.setBounds(584, 273, 109, 33);
		contentPanel.add(btnThem);
		txtMaKH.setText(makh);
		khachHangDao = new DAO_KhachHang();
		
		btnHuy.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			dispose();
		} else if (o.equals(btnLamMoi)) {
			txtTenKH.setText("");
			txtDiaChi.setText("");
			txtSDT.setText("");
		} else if (o.equals(btnThem)) {
			if(validateData()) {
				String makh = txtMaKH.getText();
				String tenkh = txtTenKH.getText();
				String sdt = txtSDT.getText();
				String diachi = txtDiaChi.getText();
				KhachHang kh = new KhachHang(makh, tenkh, sdt, diachi);
				if(khachHangDao.themKhachHang(kh)) {
					JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
					dispose();
				}
				else {
					thongBaoLoi("Lỗi thêm khách hàng", "lỗi");
				}
			}
		}

	}
	
	/**
	 * kiểm tra ràng buộc dữ liệu thông tin khách hàng
	 * @return
	 */
	public boolean validateData() {
		String tenKH = txtTenKH.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		if (tenKH.trim().equals("")) {
			showMessage(txtTenKH, "Tên không được trống");
			return false;
		}
		if(!tenKH.matches("" + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
				+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
				+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
			showMessage(txtTenKH, "Tên không có kí tự đặc biệt!");
			return false;
		}
		if (sdt.trim().length() == 0) {
			showMessage(txtSDT, "Nhập số điện thoại!");
			return false;
		}
		if (!sdt.matches("^0[1-9][0-9]{8}")) {
			showMessage(txtSDT, "Số điện thoại 10 số, số đầu tiền là số 0");
			return false;
		}if (diaChi.trim().equals("")) {
			showMessage(txtDiaChi, "Nhập Địa Chỉ!");
			return false;
		}
		if (!diaChi.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtDiaChi, "Địa chỉ không bao gồm chữ cái đặt biệt");
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
	public KhachHang traKhachHangMoinhap() {
		KhachHang kh=null;
		return  kh;
	}
}
