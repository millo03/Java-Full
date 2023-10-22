package jsoft.ads.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.UserObject;

public class UserImpl extends BasicImpl implements User {

	public UserImpl(ConnectionPool cp) {
		super(cp, "User");
	}

	@Override
	public boolean addUser(UserObject item) {
		// TODO Auto-generated method stub

		if (this.isExisting(item)) {
			return false;
		}

		String sql = "INSERT INTO tbluser(";
		sql += "user_name, user_pass, user_fullname, user_birthday,";
		sql += "user_mobilephone, user_homephone, user_officephone, user_email, user_address,";
		sql += "user_jobarea, user_job, user_position, user_applyyear, user_permission,";
		sql += "user_notes, user_roles, user_logined, user_created_date, user_last_modified,";
		sql += "user_last_logined, user_parent_id, user_actions";
		sql += ") ";
		sql += " VALUES(?, md5(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

		// Biên dịch (phải có kết nối)
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_pass());
			pre.setString(3, item.getUser_fullname());
			pre.setString(4, item.getUser_birthday());
			pre.setString(5, item.getUser_mobilephone());
			pre.setString(6, item.getUser_homephone());
			pre.setString(7, item.getUser_officephone());
			pre.setString(8, item.getUser_email());
			pre.setString(9, item.getUser_address());
			pre.setString(10, item.getUser_jobarea());
			pre.setString(11, item.getUser_job());
			pre.setString(12, item.getUser_position());
			pre.setShort(13, item.getUser_applyyear());
			pre.setByte(14, item.getUser_permission());
			pre.setString(15, item.getUser_notes());
			pre.setString(16, item.getUser_roles());
			pre.setShort(17, item.getUser_logined());
			pre.setString(18, item.getUser_created_date());
			pre.setString(19, item.getUser_last_modified());
			pre.setString(20, item.getUser_last_logined());
			pre.setInt(21, item.getUser_parent_id());
			pre.setByte(22, item.getUser_actions());
			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		

		return false;
	}

	private boolean isExisting(UserObject item) {
		boolean flag = false;

		String sql = "SELECT user_id FROM tbluser WHERE user_name = '" + item.getUser_name() + "' ";
		ResultSet rs = this.gets(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}

	@Override
	public boolean editUser(UserObject item, USER_EDIT_TYPE udt) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbluser SET ";
		switch (udt) {
		case GENERAL:
			sql += "user_fullname = ?, user_birthday = ?, ";
			sql += "user_mobilephone = ?, user_homephone = ?, user_officephone = ?, user_email = ?, user_address = ?, ";
			sql += "user_jobarea = ?, user_job = ?, user_position = ?, user_applyyear = ?, ";
			sql += "user_notes = ?, user_last_modified = ?, ";
			sql += "user_actions = ?  ";
			break;
		case SETTINGS:
			sql += "user_permission = ?, user_roles = ?, ";
			break;
		case PASS:
			sql += "user_pass = md5(?) ";
			break;
		case TRASH:
			sql+= " user_delete = 1, user_last_modified = ? ";
		}

		sql += "WHERE user_id = ?; ";
		

		// Biên dịch (phải có kết nối)
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			switch (udt) {
			case GENERAL:
				pre.setString(1, item.getUser_fullname());
				pre.setString(2, item.getUser_birthday());
				pre.setString(3, item.getUser_mobilephone());
				pre.setString(4, item.getUser_homephone());
				pre.setString(5, item.getUser_officephone());
				pre.setString(6, item.getUser_email());
				pre.setString(7, item.getUser_address());
				pre.setString(8, item.getUser_jobarea());
				pre.setString(9, item.getUser_job());
				pre.setString(10, item.getUser_position());
				pre.setShort(11, item.getUser_applyyear());
				pre.setString(12, item.getUser_notes());
				pre.setString(13, item.getUser_last_modified());
				pre.setByte(14, item.getUser_actions());

				pre.setInt(15, item.getUser_id());
				break;
			case SETTINGS:
				pre.setByte(1, item.getUser_permission());
				pre.setString(2, item.getUser_roles());
				
				pre.setInt(3, item.getUser_id());
				break;
			case PASS:
				pre.setString(1, item.getUser_pass());
				pre.setInt(2, item.getUser_id());
				break;
			case TRASH:
				pre.setString(1, item.getUser_last_modified());
				pre.setInt(2, item.getUser_id());	
			}
			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//System.out.println(sql.toString());
		return false;
	}

	@Override
	public boolean delUser(UserObject item) {
		// TODO Auto-generated method stub

		if (!this.isEmpty(item)) {
			return false;
		}

		String sql = "DELETE FROM tbluser WHERE user_id = ? ";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getUser_id());

			return this.del(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Kiem tra xem cai id nguoi dung nay co lien quan den nhung bang nao khong
	 *
	 * @param item
	 * @return
	 */
	private boolean isEmpty(UserObject item) {
		boolean flag = true;
		/*
		 * String sql1 =
		 * "SELECT article_id FROM tblarticle WHERE article_author_name = '" +
		 * item.getUser_name() + "' "; // Kiem tra xem user co la tac gia cua tac pham
		 * nao khong String sql2 =
		 * "SELECT product_id FROM tblproduct WHERE product_manager_id = ?"; String sql3
		 * = "SELECT user_id FROM tbluser WHERE user_parent_id = ?";
		 *
		 * ResultSet rs1 = this.gets(sql1); ResultSet rs2 = this.get(sql2,
		 * item.getUser_id()); ResultSet rs3 = this.get(sql3, item.getUser_id()); //
		 * FIXME: tim cach gop 3 cau lenh ???
		 *
		 * if (rs1 != null || rs2 != null || rs3 != null) { try { if (rs1.next() ||
		 * rs2.next() || rs3.next()) { // chi can next duoc (co ban ghi) thi ngat luon
		 * flag = false; } } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT article_id FROM tblarticle WHERE article_author_name = '" + item.getUser_name() + "'; ");
		sql.append("SELECT product_id FROM tblproduct WHERE product_manager_id = " + item.getUser_id() + "; ");
		sql.append("SELECT user_id FROM tbluser WHERE user_parent_id = " + item.getUser_id() + "; ");

		ArrayList<ResultSet> res = this.getReList(sql.toString());

		for (ResultSet rs : res) {
			try {
				if (rs != null && rs.next()) {
					flag = false;
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}

	@Override
	public ResultSet getUser(int id) {
		// TODO Auto-generated method stub

		String sql  = "SELECT * FROM tbluser WHERE (user_id = ?) AND (user_delete = 0 )";
		

		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String username, String userpass) {
		// TODO Auto-generated method stub

		String sqlSelect = "SELECT * FROM tbluser WHERE (user_name = ?) AND (user_pass = md5(?)) AND (user_delete = 0 ) ";
		String sqlUpdate = "UPDATE tbluser SET user_logined =user_logined +1 WHERE (user_name = ?) AND (user_pass = md5(?)) ";
		ArrayList<String >sql = new ArrayList<>();
		sql.add(sqlSelect);
		sql.add(sqlUpdate);
		
		return this.get(sql, username, userpass);
	}

	@Override
	public ArrayList<ResultSet> getUsers(UserObject similar, int at, byte total, USER_SORT_TYPE type) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tbluser ";
		sql += this.createConditions(similar);
		sql += "";

		switch (type) {
		case NAME:
			sql += "ORDER BY user_name ASC ";
			break;
		case FULLNAME:
			sql += "ORDER BY user_fullname ASC ";
			break;
		case ADDRESS:
			sql += "ORDER BY user_address ASC ";
			break;
		case CREATED:
			sql += "ORDER BY user_created_date DESC ";
			break;
		case MODIFIED:
		case UPDATE:
			sql += "ORDER BY user_last_modified DESC ";
			break;
		default:
			sql += "ORDER BY user_id ASC ";
			break;
		}

		sql += "LIMIT " + at + ", " + total + "; ";

		StringBuilder multiSelect = new StringBuilder();
		multiSelect.append(sql);
		multiSelect.append("SELECT COUNT(user_id) as total from tbluser;");
		
		//System.out.println(multiSelect.toString());
		return this.getReList(multiSelect.toString());
//		return this.get(sql, 0);
	}

	private String createConditions(UserObject similar) {
		// where (user_permission < permis) and (user_parent_id = id or user_id = id) 
		StringBuilder condition = new StringBuilder();
		if (similar != null) {
			//System.out.println(similar.isUser_delete());
			// permis do tai khoan dang nhap truyen cho
			byte permis = similar.getUser_permission();

			condition.append("((user_permission < ").append(permis).append(") ");
			condition.append("OR (user_id = ").append(similar.getUser_id()).append(")) ");
			
			if (permis < 4) {
				int id = similar.getUser_id();
				if (id > 0) {
					condition.append("AND ((user_parent_id = ").append(id).
					append(") OR (user_id = ").append(id).append(")) ");
				}
			}
			
			if(similar.isUser_delete() ) {
				condition.append("AND (user_delete = 1) ");
			}else {
				condition.append("AND (user_delete = 0) ");
			}
		}

		if (condition.length() > 0) {
			condition.insert(0, " WHERE ");
		}
		//System.out.println("condition "+condition.toString() );



		return condition.toString();
	}

	public static void main(String[] args) {
		// Khởi tạo bộ quản lý kết nối
		ConnectionPool cp = new ConnectionPoolImpl();

		// Tạo đối tượng thực thi chức năng mức User
		User u = new UserImpl(cp);

		// Đối tượng lưu trữ thông tin
		UserObject nUser = new UserObject();
		nUser.setUser_id(56);
		nUser.setUser_name("ad789");
		nUser.setUser_fullname("Nguyễn Việt Hoàng");
		nUser.setUser_address("Bắc Giang");
		nUser.setUser_pass("07012003");
		nUser.setUser_email("admin@gmail.com");
		nUser.setUser_created_date("07/05/2023");
		nUser.setUser_parent_id(20);
		// parent id = ai la nguoi tao ra tai khoan nay

//		boolean result = u.addUser(nUser);
//		boolean result = u.delUser(nUser);
//		boolean result = u.editUser(nUser);

//		if (!result) {
//			System.out.print("\n\n------------KHÔNG THÀNH CÔNG-------------\n");
//		}

		// Lấy tập bản ghi Người sử dụng
		ArrayList<ResultSet> res = u.getUsers(null, 0, (byte) 10, USER_SORT_TYPE.NAME);
		ResultSet rs = res.get(0);
		// Duyệt và hiển thị danh sách này
		String row;
		if (rs != null) {
			try {
				while (rs.next()) {
					row = "ID: " + rs.getInt("user_id");
					row += "\tNAME: " + rs.getString("user_name");
					row += "\tFULLNAME: " + rs.getString("user_fullname");
					row += "\tPASS: " + rs.getString("user_pass");
					row += "\tLOGINED: " + rs.getShort("user_logined");

					//System.out.println(row);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		rs = res.get(1);

		if (rs != null) {
			try {
				if (rs.next()) {
					System.out.println("Tổng số người sử dụng: total = " + rs.getInt("total"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
