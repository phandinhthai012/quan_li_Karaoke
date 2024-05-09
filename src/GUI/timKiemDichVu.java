package GUI;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_DichVu;
import connectDB.connectDB;

import entity.DichVu;
import entity.NhanVien;

import javax.swing.ImageIcon;

public class timKiemDichVu extends JPanel implements ActionListener,MouseListener,KeyListener {
	private JTextField txtTimKiem;
	private JTable table;
	
	private DAO_DichVu dichVuDAO;
	private ArrayList<DichVu> dsDV;
	private DefaultTableModel model;
	private JButton btnCapNhat;
	private static String maPhong = "";
	private static String maNhanVien = "";
	private DAO_DichVu dao_DichVu = new DAO_DichVu();
	private ArrayList<DichVu> dsTimDuoc = dao_DichVu.getALLDichVu();
	private NhanVien nhanVienHienTai;
	private JPanel panel ;
	/**
	 * Create the panel.
	 */
	public timKiemDichVu(NhanVien nv) {
		try {
			connectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		setLayout(null);
		setBounds(0, 0, 1540, 732);
		
		panel = new JPanel();
		panel.setBounds(10, 10, 1520, 712);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(10, 10, 1500, 148);
		panel.add(panel_1);
		
		JLabel lblTimKiemDichVu = new JLabel("Tìm kiếm dịch vụ");
		lblTimKiemDichVu.setIcon(new ImageIcon("D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-package-search-60.png"));
		lblTimKiemDichVu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblTimKiemDichVu.setBounds(623, 10, 389, 63);
		panel_1.add(lblTimKiemDichVu);
		
		JLabel lblNhapTT = new JLabel("Nhập thông tin tìm kiếm:");
		lblNhapTT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhapTT.setBounds(349, 81, 210, 37);
		panel_1.add(lblNhapTT);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(550, 83, 423, 37);
		panel_1.add(txtTimKiem);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBackground(SystemColor.menu);
		panel_1_1.setBounds(10, 609, 1500, 84);
		panel.add(panel_1_1);
		
		btnCapNhat = new JButton("Cập nhật dịch vụ");
		btnCapNhat.setIcon(new ImageIcon("D:\\nam3_Ki1\\PTUD\\workSpace\\nhom17_PTUD_Karaoke\\src\\img\\icons8-update-30.png"));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCapNhat.setBackground(SystemColor.inactiveCaption);
		btnCapNhat.setBounds(524, 10, 448, 51);
		panel_1_1.add(btnCapNhat);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 1500, 431);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			
			},
			new String[] {
				 "Mã Dịch Vụ", "Tên Dịch Vụ", "Đơn Giá", "Đơn Vị", "Số Lượng Tồn Kho"
			}
		));
		
		
		model = (DefaultTableModel) table.getModel();
		nhanVienHienTai= nv;
		dichVuDAO = new DAO_DichVu();
		
		docDuLieuVaoBang();
		loadDonVi();
		btnCapNhat.addActionListener(this);
		txtTimKiem.addKeyListener(this);
		xoaALLDuLieuTable();
	}
	
	
	
	public void xoaALLDuLieuTable() {   //xoa tat ca row trong table
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}
	
	public void docDuLieuVaoBang() {
		dsDV = dichVuDAO.getALLDichVu();
		if(dsDV == null) {
			JOptionPane.showMessageDialog(this, "Không có dữ liệu Khách Hàng");
			return;
		}
//		for (DichVu dichVu : dsDV) {
//			String ghiChu= "";
//			if(dichVu.getSoLuongTonKho()<=0) {
//				ghiChu = "Tạm hết";
//			}
//			model.addRow(new Object[] {dichVu.getMaDV(),dichVu.getTenDichVu(),dichVu.getDonGia(),dichVu.getDonVi(),dichVu.getSoLuongTonKho(),ghiChu});
//		}
	}
	public void loadDonVi() {
		ArrayList<DichVu> ds = dichVuDAO.getALLDichVu();
		ArrayList<String> dsDonVi = new ArrayList<String>();
		for (DichVu dichVu : ds) {
			String donVi = dichVu.getDonVi();
			if(!dsDonVi.contains(donVi)) {
				dsDonVi.add(donVi);
			
			}
		}
	}
	
	private void loadData(ArrayList<DichVu> dsDV) {
		DecimalFormat df = new DecimalFormat("#,##0 đ");
		DecimalFormat ds = new DecimalFormat("###");
		model.setRowCount(0);
		for (int i = 0; i < dsDV.size(); i++) {
			String maDV = dsDV.get(i).getMaDV();
			String tenDV = dsDV.get(i).getTenDichVu();
			String gia = df.format(dsDV.get(i).getDonGia());
			String tenDonVi = dsDV.get(i).getDonVi();
			String soLuongTon = ds.format(dsDV.get(i).getSoLuongTonKho());
			String row[] = { maDV, tenDV, gia,tenDonVi, soLuongTon};
			model.addRow(row);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnCapNhat)) {
			if(nhanVienHienTai.getChucVu().equals("QL")) {
				
				this.panel.removeAll();
				this.panel.repaint();
				this.panel.revalidate();
				
				this.panel.add(new QLDichVu());
				this.panel.repaint();
				this.panel.revalidate();

			}
			else {
				JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập chức năng này");
			}
		}
		
			
			
		}
	public void keyReleased(KeyEvent e) {
		dsTimDuoc = dao_DichVu.search(txtTimKiem.getText().trim());
//		loadData(dsTimDuoc);
		if(dsTimDuoc != null && !dsTimDuoc.isEmpty()) {
			loadData(dsTimDuoc);
			}
			
			if(txtTimKiem.getText().isEmpty()) {
				xoaALLDuLieuTable();
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


