package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import DAO.DAO_DichVu;
import entity.DichVu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dialogThemDichVu extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMaDichVu;
	private JTextField txtTenDichVu;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private DAO_DichVu dichVuDao;
	private JButton btnThem, btnLamMoi,btnHuy;
	private JComboBox cmbDonVi;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
////			dialogThemDichVu dialog = new dialogThemDichVu();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public dialogThemDichVu(String madv) {
		setBounds(100, 100, 769, 499);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pnNoiDung = new JPanel();
		pnNoiDung.setLayout(null);
		pnNoiDung.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnNoiDung.setBounds(10, 67, 735, 312);
		contentPanel.add(pnNoiDung);
		
		JLabel lbMaDV = new JLabel("Mã Dịch Vụ");
		lbMaDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbMaDV.setBounds(29, 29, 110, 40);
		pnNoiDung.add(lbMaDV);
		
		txtMaDichVu = new JTextField();
		txtMaDichVu.setText("<dynamic>");
		txtMaDichVu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtMaDichVu.setEditable(false);
		txtMaDichVu.setColumns(10);
		txtMaDichVu.setBounds(149, 36, 245, 27);
		pnNoiDung.add(txtMaDichVu);
		
		JLabel lbTenDV = new JLabel("Tên Dịch Vụ");
		lbTenDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTenDV.setBounds(29, 79, 110, 40);
		pnNoiDung.add(lbTenDV);
		
		txtTenDichVu = new JTextField();
		txtTenDichVu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtTenDichVu.setColumns(10);
		txtTenDichVu.setBounds(149, 86, 430, 27);
		pnNoiDung.add(txtTenDichVu);
		
		JLabel lbDonGia = new JLabel("Đơn Giá");
		lbDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDonGia.setBounds(29, 129, 72, 27);
		pnNoiDung.add(lbDonGia);
		
		JLabel lbSoLuong = new JLabel("Số Lượng");
		lbSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbSoLuong.setBounds(29, 178, 82, 33);
		pnNoiDung.add(lbSoLuong);
		
		JLabel lbDonVi = new JLabel("Đơn Vị");
		lbDonVi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDonVi.setBounds(313, 129, 72, 33);
		pnNoiDung.add(lbDonVi);
		
		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(149, 129, 123, 27);
		pnNoiDung.add(txtDonGia);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(149, 178, 123, 27);
		pnNoiDung.add(txtSoLuong);
		
		cmbDonVi = new JComboBox();
		cmbDonVi.setToolTipText("");
		cmbDonVi.addItem("Chai");
		cmbDonVi.addItem("lon");
		cmbDonVi.addItem("Bịch");
		cmbDonVi.addItem("Thùng");
		cmbDonVi.addItem("Phần");
		cmbDonVi.addItem("Khác");
		cmbDonVi.setBounds(366, 135, 131, 26);
		pnNoiDung.add(cmbDonVi);
		
		btnHuy = new JButton("Hủy ");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHuy.setBackground(new Color(255, 87, 87));
		btnHuy.setBounds(31, 402, 92, 40);
		contentPanel.add(btnHuy);
		
		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoi.setBackground(new Color(228, 218, 167));
		btnLamMoi.setBounds(449, 402, 129, 40);
		contentPanel.add(btnLamMoi);
		
		btnThem = new JButton("Tạo Mới");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBackground(new Color(90, 220, 148));
		btnThem.setBounds(605, 402, 129, 40);
		contentPanel.add(btnThem);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 10, 735, 47);
		contentPanel.add(panel);
		txtMaDichVu.setText(madv);
		
		JLabel lblNewLabel = new JLabel("Tạo Mới Dịch Vụ");
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dichVuDao = new DAO_DichVu();
		
		btnHuy.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnHuy)) {
			dispose();
		}
		else if(o.equals(btnThem)) {
			if(validateData()) {
				boolean k = themDichVu();
				if(k) {
					JOptionPane.showMessageDialog(this, "Thêm dịch vụ thành công");
					dispose();
				}
			}
		}else if(o.equals(btnLamMoi)) {
			txtTenDichVu.setText("");
			txtDonGia.setText("");
			txtSoLuong.setText("");
			cmbDonVi.setSelectedIndex(0);
		}
	}
	public boolean themDichVu() {
		String maDV = txtMaDichVu.getText();
		String tenDV = txtTenDichVu.getText();
		int soLuong = Integer.parseInt(txtSoLuong.getText());
		double dongia = Double.parseDouble(txtDonGia.getText());
		String donVi = cmbDonVi.getSelectedItem().toString();
		DichVu dv = new DichVu(maDV, tenDV, dongia, donVi, soLuong);
		if(dichVuDao.themDichVu(dv)) {
			return true;
		}
		return false;
	}
	
	
	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}
	/**
	 * kiểm tra ràng buộc dữ liệu khi nhập thông tin vào
	 * @return
	 */
	public boolean validateData() {
		if(txtTenDichVu.getText().trim().equals("")) {
			showMessage(txtTenDichVu, "Tên không được trống");
			return false;
		}
		if(!txtTenDichVu.getText().matches("[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
				+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
				+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9 ]+")) {
			showMessage(txtTenDichVu, "Tên không có kí tự đặc biệt!");
			return false;
		}
		if(txtSoLuong.getText().trim().equals("")) {
			showMessage(txtSoLuong, "Số lượng không được trống!");
			return false;
		}
		try {
			int soluong = Integer.parseInt(txtSoLuong.getText());
			if(soluong<0) {
				showMessage(txtSoLuong, "số lượng phải lớn hơn 0");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			showMessage(txtSoLuong, "số lượng là chữ số");
			return false;
		}
		if(txtDonGia.getText().trim().equals("")) {
			showMessage(txtDonGia, "Đơn giá không được để trống");
			return false;
		}
		try {
			double donGia = Double.parseDouble(txtDonGia.getText());
			if(donGia<0) {
				showMessage(txtDonGia, "Đơn giá phải lớn hơn 0");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			showMessage(txtSoLuong, "số lượng là chữ số");
			return false;
		}
		return true;
	}
	
}
