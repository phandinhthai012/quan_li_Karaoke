package GUI;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_KhachHang;
import DAO.DAO_NhanVien;
import entity.KhachHang;

public class timKiemKhachHang extends JPanel implements ActionListener,MouseListener,KeyListener {
	private JTextField txtSDT;
	private JTextField txtTenKhachHang;
	private JTextField txtMaKhachHang;
	private JTextField txtDiaChi;
	private JTable table;
	private DAO_KhachHang daoKH = new DAO_KhachHang();
	private DAO_KhachHang khachHangDAO;
	private DefaultTableModel model;
	private ArrayList<KhachHang> danhSachKH;
	private ArrayList<KhachHang> dsTimDuoc = daoKH.getALLKhachHang();
	private JButton btnLamMoi;
	/**
	 * Create the panel.
	 */
	public timKiemKhachHang() {
		setLayout(null);
		setBounds(0, 0, 1540, 787);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1520, 767);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 10, 343, 721);
		panel.add(panel_1);
		
		JLabel lblThongTin = new JLabel("Thông Tin Khách Hàng");
		lblThongTin.setHorizontalAlignment(SwingConstants.LEFT);
		lblThongTin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThongTin.setBounds(0, 10, 181, 17);
		panel_1.add(lblThongTin);
		
		JLabel lblMaKhachHang = new JLabel("Mã Khách Hàng");
		lblMaKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaKhachHang.setBounds(20, 63, 124, 17);
		panel_1.add(lblMaKhachHang);
		
		JLabel lblTenKhachHang = new JLabel("Tên Khách Hàng");
		lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenKhachHang.setBounds(20, 170, 124, 17);
		panel_1.add(lblTenKhachHang);
		
		JLabel lblSDT = new JLabel("Số Điện Thoại");
		lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDT.setBounds(20, 295, 124, 17);
		panel_1.add(lblSDT);
		
		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiaChi.setBounds(20, 403, 89, 17);
		panel_1.add(lblDiaChi);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(20, 322, 287, 27);
		panel_1.add(txtSDT);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(20, 197, 287, 27);
		panel_1.add(txtTenKhachHang);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(20, 90, 287, 27);
		panel_1.add(txtMaKhachHang);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(20, 430, 287, 27);
		panel_1.add(txtDiaChi);
		
		
	
		
	    btnLamMoi = new JButton("Làm mới");
	    btnLamMoi.setIcon(new ImageIcon("D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-search-30.png"));
	    btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    btnLamMoi.setBackground(new Color(52, 147, 188));
	    btnLamMoi.setBounds(91, 491, 124, 34);
		panel_1.add(btnLamMoi);
		
		JLabel txtTimKiemThongTinKH = new JLabel("Tìm Kiếm Khách Hàng");
		txtTimKiemThongTinKH.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimKiemThongTinKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiemThongTinKH.setBounds(812, 10, 264, 27);
		panel.add(txtTimKiemThongTinKH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(371, 45, 1139, 686);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã Khách Hàng", "Tên Khách Hàng","Số Điện Thoai", "Địa Chỉ",
			}
		));
		model = (DefaultTableModel) table.getModel();
		khachHangDAO = new DAO_KhachHang();
		docDulieuVaoBang();
		table.addMouseListener(this);
		btnLamMoi.addActionListener(this);
		txtMaKhachHang.addKeyListener(this);
		txtTenKhachHang.addKeyListener(this);
		txtSDT.addKeyListener(this);
		txtDiaChi.addKeyListener(this);
		xoaALLDuLieuTable();
		
		
	}
	public void xoaALLDuLieuTable() {   //xoa tat ca row trong table
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}
	public void docDulieuVaoBang() {   //load du lieu vao table
		danhSachKH = khachHangDAO.getALLKhachHang();
		if (danhSachKH == null) {
			JOptionPane.showMessageDialog(this, "Không có dữ liệu Khách Hàng");
			return;
		}
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
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
	private void xoaRongNhapLieu() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnLamMoi)) {
			xoaRongNhapLieu();
			xoaALLDuLieuTable();
			
		}
	}
		
	public void keyReleased(KeyEvent e) {  // Hien du lieu khi go thong tin vao
		
		dsTimDuoc = daoKH.getTimKiemDSKH(txtMaKhachHang.getText(), txtTenKhachHang.getText(),
				txtSDT.getText(), txtDiaChi.getText());
		if(dsTimDuoc != null && !dsTimDuoc.isEmpty()) { // Nếu danh sách tìm được không rỗng
			loadData(dsTimDuoc);           // Hiển thị dữ liệu lên bảng
		} else {                 // Nếu danh sách tìm được rỗng
			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng nào theo tiêu chí nhập vào");
		
	}
		if(txtMaKhachHang.getText().isEmpty() && txtTenKhachHang.getText().isEmpty() &&
				txtSDT.getText().isEmpty() && txtDiaChi.getText().isEmpty()) {
			xoaALLDuLieuTable(); // Xóa hết các dòng trong bảng
		}
	}
	                                                   //load du lieu 
	private void loadData(ArrayList<KhachHang> dsKH) {
		model.setRowCount(0);  
		for (int i = 0; i < dsKH.size(); i++) {  //duyet qua cac phan tu cua dsKH
			String maKH = dsKH.get(i).getMaKH();
			String tenKH = dsKH.get(i).getTenKH();
			String sDT = dsKH.get(i).getSdt();
		
		
			String diaChi = dsKH.get(i).getDiaChi();
			String row[] = { maKH, tenKH, sDT,diaChi };
			model.addRow(row); //them vao table cac gia tri phan tu mang row[]
			
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
