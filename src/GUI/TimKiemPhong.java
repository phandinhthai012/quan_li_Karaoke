package GUI;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_Phong;
import entity.LoaiPhong;
import entity.Phong;

import javax.swing.JScrollPane;

public class TimKiemPhong extends JPanel implements ActionListener,KeyListener{
	private JTable table;
	private JTextField txtTimKiem;
	private DAO_Phong dao_Phong = new DAO_Phong();
	private DefaultTableModel model;
	private ArrayList<Phong> dsTimDuoc = dao_Phong.getALLPhong();

	/**
	 * Create the panel.
	 */
	public TimKiemPhong() {
		setLayout(null);
		setBounds(0, 0, 1540, 787);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setBounds(10, 10, 1520, 767);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 220, 1510, 547);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Mã Phòng", "Tên Phòng", "Loại Phòng", "Giá Phòng", "Sức Chứa", "Trạng Thái"
			}
		));
		model = (DefaultTableModel) table.getModel();
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(10, 10, 1500, 200);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblTimKiemPhong = new JLabel("Tìm Kiếm Phòng");
		lblTimKiemPhong.setBounds(750, 22, 146, 25);
		panel_2.add(lblTimKiemPhong);
		lblTimKiemPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(554, 88, 89, 36);
		panel_2.add(lblNewLabel);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(653, 88, 347, 41);
		panel_2.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		xoaALLDuLieuTable();
//		loadData(dao_Phong.getALLPhong());
		txtTimKiem.addKeyListener(this);
		
		
		
		
		
	}
	public void xoaALLDuLieuTable() {   //xoa tat ca row trong table
		for (int i = model.getRowCount(); i > 0; i--) {  // cho i = voi so dong, lon hon 0 tiep tuc lap, = 0 thi thoat lap
			model.removeRow(0);
		}
	}
	private void loadData(ArrayList<Phong> dsPhong) {
		DecimalFormat df = new DecimalFormat("#,##0đ");
		DecimalFormat ds = new DecimalFormat("#,##");
		model.setRowCount(0);
		for (int i = 0; i < dsPhong.size(); i++) {
			String maPhong = dsPhong.get(i).getMaPhong();
			String tenPhong = dsPhong.get(i).getTenPhong();
			String tenLoaiPhong = dsPhong.get(i).getLoaiPhong().getTenLoaiPhong();
			String giaPhong = df.format(dsPhong.get(i).getDonGia());
			String sucChua = ds.format(dsPhong.get(i).getSucChua());
			String trangThai = dsPhong.get(i).getTrangThai();
			
	
			String row[] = { maPhong, tenPhong, tenLoaiPhong,giaPhong, sucChua, trangThai};
			model.addRow(row);
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






	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		dsTimDuoc = dao_Phong.search(txtTimKiem.getText().trim());
		if(dsTimDuoc!= null && !dsTimDuoc.isEmpty()) //neu dstimdc ko empty thi load du lieu ra
			loadData(dsTimDuoc);
			else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng nào theo tiêu chí nhập vào");

			} 
			if(txtTimKiem.getText().isEmpty()){ //neu txtfield tim kiem trong thi xoa het row
				xoaALLDuLieuTable();
			}
	}
	





	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
