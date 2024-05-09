package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import DAO.DAO_DichVu;
import connectDB.connectDB;
import entity.DichVu;
import javax.swing.JComboBox;

public class GD_themMoiDV extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnHuy, btnLamMoi, btnTaoMoi;
	private JTextField txtMaDV;
	private JTextField txtTenDV;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private DAO_DichVu dichVuDAO;
	private ArrayList<DichVu> dsDichVu;
	private JComboBox cmbDonVi;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GD_themMoiDV frame = new GD_themMoiDV();
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
	public GD_themMoiDV(String maDV, String[] dsDonVi) {
		try {
			connectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Thêm Mới Dịch Vụ");
		setBounds(100, 100, 904, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 890, 40);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tạo Mới Dịch Vụ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(341, 0, 170, 40);
		panel.add(lblNewLabel);

		btnHuy = new JButton("Hủy ");
		btnHuy.setIcon(new ImageIcon(GD_themMoiDV.class.getResource("/img/icons8-cancel-24.png")));
		btnHuy.setBackground(new Color(255, 87, 87));
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHuy.setBounds(43, 402, 110, 40);
		contentPane.add(btnHuy);

		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setIcon(new ImageIcon(GD_themMoiDV.class.getResource("/img/icons8-refresh-30.png")));
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLamMoi.setBackground(new Color(228, 218, 167));
		btnLamMoi.setBounds(576, 402, 148, 40);
		contentPane.add(btnLamMoi);

		btnTaoMoi = new JButton("Tạo Mới");
		btnTaoMoi.setIcon(new ImageIcon(GD_themMoiDV.class.getResource("/img/icons8-new-copy-30.png")));
		btnTaoMoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTaoMoi.setBackground(new Color(90, 220, 148));
		btnTaoMoi.setBounds(734, 402, 146, 40);
		contentPane.add(btnTaoMoi);

		JPanel pnNoiDung = new JPanel();
		pnNoiDung.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnNoiDung.setBounds(22, 67, 841, 312);
		contentPane.add(pnNoiDung);
		pnNoiDung.setLayout(null);

		JLabel lbMaDV = new JLabel("Mã Dịch Vụ");
		lbMaDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbMaDV.setBounds(29, 29, 110, 40);
		pnNoiDung.add(lbMaDV);

		txtMaDV = new JTextField();
		txtMaDV.setEditable(false);
		txtMaDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtMaDV.setColumns(10);
		txtMaDV.setBounds(149, 36, 245, 27);
		pnNoiDung.add(txtMaDV);

		JLabel lbTenDV = new JLabel("Tên Dịch Vụ");
		lbTenDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTenDV.setBounds(29, 104, 110, 40);
		pnNoiDung.add(lbTenDV);

		txtTenDV = new JTextField();
		txtTenDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtTenDV.setColumns(10);
		txtTenDV.setBounds(149, 111, 462, 27);
		pnNoiDung.add(txtTenDV);

		JLabel lbDonGia = new JLabel("Đơn Giá");
		lbDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDonGia.setBounds(29, 184, 72, 27);
		pnNoiDung.add(lbDonGia);

		JLabel lbSoLuong = new JLabel("Số Lượng");
		lbSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbSoLuong.setBounds(29, 241, 82, 33);
		pnNoiDung.add(lbSoLuong);

		JLabel lbDonVi = new JLabel("Đơn Vị");
		lbDonVi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDonVi.setBounds(312, 178, 72, 33);
		pnNoiDung.add(lbDonVi);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(149, 184, 123, 27);
		pnNoiDung.add(txtDonGia);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(149, 241, 123, 27);
		pnNoiDung.add(txtSoLuong);

		cmbDonVi = new JComboBox();
		for (String string : dsDonVi) {
			cmbDonVi.addItem(string);
		}
		cmbDonVi.setBounds(365, 184, 131, 26);
		pnNoiDung.add(cmbDonVi);
		txtMaDV.setText(maDV);
		dichVuDAO = new DAO_DichVu();
		dsDichVu = dichVuDAO.getALLDichVu();

		btnLamMoi.addActionListener(this);
		btnHuy.addActionListener(this);
		btnTaoMoi.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLamMoi)) {
			XoaTrang();
		} else if (o.equals(btnHuy)) {
			dispose();
		} else if (o.equals(btnTaoMoi)) {
			if (valiData()) {
				String maDV = txtMaDV.getText();
				String tenDV = txtTenDV.getText();
				String donGia = txtDonGia.getText();
				String slTon = txtSoLuong.getText();
				String donVi = cmbDonVi.getItemAt(cmbDonVi.getSelectedIndex()).toString();
				double gia = Double.parseDouble(donGia);
				int sl = Integer.parseInt(slTon);
				DichVu x = new DichVu(maDV, tenDV, gia, donVi, sl);
				if(themMoiDV(x)) {
					dsDichVu.add(x);
					JOptionPane.showMessageDialog(null, "Thêm Mới Thành công");
					QLDichVu pnlQlDV = new QLDichVu();
//					pnlQlDV.xoaALLDuLieuTable();
//					pnlQlDV.docDuLieuVaoBang();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Lỗi không thêm được dịch vụ");
				}
			}
		}
	}

	public void XoaTrang() {
		txtTenDV.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
//		txtMaDV.requestFocus();
	}

	public boolean themMoiDV(DichVu x) {
		if (dichVuDAO.themDichVu(x)) {
			dsDichVu.add(x);
			
			return true;
		} else {
			
			return false;
		}
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		txt.requestFocus();
		JOptionPane.showMessageDialog(this, message, "Lỗi!", JOptionPane.ERROR_MESSAGE);
	}
	public ArrayList<DichVu> getDSDichVu() {
		return dsDichVu;
	}

	public boolean valiData() {
		String tendv = txtTenDV.getText();
		String donGia = txtDonGia.getText();
		String sl = txtSoLuong.getText();
		if (tendv.length() == 0) {
			showMessage(txtTenDV, "Nhập tên dịch vụ!");
			return false;
		}
		if (!tendv.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]+\\s?)+$")) {
			showMessage(txtTenDV,
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
		if (!sl.matches("^([1-9]\\d*)$")) {
			showMessage(txtSoLuong, "Số lượng là số nguyên > 0 và không được bắt đầu bằng 0!");
			return false;
		}
		return true;
	}
}
