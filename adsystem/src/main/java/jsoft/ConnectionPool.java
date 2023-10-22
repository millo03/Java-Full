package jsoft;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {

	//Phương thức cung cấp kết nối khi 1 đối tượng cần:
	public Connection getConnection(String objectName) throws SQLException;

	//Phương thức thu hồi kết nối
	public void releaseConnection(Connection con, String objectName) throws SQLException;


}
