package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
	private static Connection con = null;
	private static connectDB instantce = new connectDB();
	public static connectDB getInstance() {
		return instantce;
	}
	public void connect() throws SQLException {
		String userName = "sa";
		String password ="123456";
		String databaseName = "quanLiKaraokeNice";
		String url ="jdbc:sqlserver://localhost:1433;databasename="+databaseName;
		try {
			con = DriverManager.getConnection(url, userName, password);
			System.out.println("Kết nối Thành công");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi kết nối SQL server");
		}
		
	}
	
	public void disConnect() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		return con;
	}
}
