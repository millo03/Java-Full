package jsoft;


/**
 * Interface này được tạo ra để ẩn bớt các điều khiển của basic mà các model không dùng đến
 *
 * @author vieth
 *
 */
public interface ShareControl {
	// Chia sẻ bộ quản lý kết nối giữa các basic với nhau
	public ConnectionPool getCP();

	// Ra lệnh các đối tượng phải trả lại kết nối
	public void releaseConnection();
}
