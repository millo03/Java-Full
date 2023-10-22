package jsoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
	// Trình điều khiển làm việc với CSDL
	private String driver;

	// Đường dẫn thực thi
	private String url;

	// Tài khoản sử dụng CSDL
	private String username;
	private String userpass;

	private Stack<Connection> pool;

	public ConnectionPoolImpl() {
		// Xác định trình điều khiển của MySQL
		this.driver = "com.mysql.jdbc.Driver";

		// Xác định đường dẫn thực thi MySQL
//		this.url = "jdbc:mysql://localhost:3306";
		this.url = "jdbc:mysql://127.0.0.1:3306/jp255_data?allowMultiQueries=true";

		this.username = "root";
		// root chỉ ở máy bản thân
		this.userpass = "1234";


		// nạp trình điều khiển
		this.loadDriver();


		// Đối tượng lưu trữ kết nối
		this.pool = new Stack<>();
	}

	/**
	 * Phương thức nạp trình điều khiển
	 */
	private void loadDriver() {
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub

		if (this.pool.isEmpty()) {
			// Khởi tạo kết nối mới
			//System.out.println(objectName + " have created a new Connection.");
			return DriverManager.getConnection(this.url, this.username, this.userpass);
		} else {
			//System.out.println(objectName + " have popped the Connection.");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub

		// Yêu cầu các đối tượng trả về kết nối
		//System.out.println(objectName + " have pushed the Connection.");
		this.pool.push(con);
	}

	@Override
	protected void finalize() throws Throwable {
		this.pool.clear(); // Loại bỏ các đối tượng, cho nó không còn tham chiếu đến bất kì đối tượng nào
							// nữa
		this.pool = null;

		System.gc();

		//System.out.println("ConnectionPool is closed.");
	}

}
