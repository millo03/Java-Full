package jsoft.ads.category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.section.SectionImplV2;
import jsoft.objects.CategoryObject;

public class CategoryImplV2 extends SectionImplV2 implements Category {

	public CategoryImplV2(ConnectionPool cp) {
		super(cp, "Category");
	}

	public CategoryImplV2(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addCategory(CategoryObject item) {
		// TODO Auto-generated method stub

		if(this.isExisting(item)) return false;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into tblcategory(category_name, \r\n"
				+ "						category_section_id, \r\n"
				+ "                        category_notes, \r\n"
				+ "                        category_created_date, \r\n"
				+ "                        category_created_author_id, \r\n"
				+ "                        category_last_modified, \r\n"
				+ "                        category_manager_id, \r\n"
				+ "                        category_enable, \r\n"
				+ "                        category_delete, \r\n"
				+ "                        category_image, \r\n"
				+ "                        category_name_en, \r\n"
				+ "                        category_language)\r\n"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getCategory_name());
			pre.setShort(2, item.getCategory_section_id());
			pre.setString(3, item.getCategory_notes());
			pre.setString(4, item.getCategory_created_date());
			pre.setInt(5, item.getCategory_created_author_id());
			pre.setString(6, item.getCategory_last_modified());
			pre.setInt(7, item.getCategory_manager_id());
			pre.setBoolean(8, item.isCategory_enable());
			pre.setBoolean(9, item.isCategory_delete());
			pre.setString(10, item.getCategory_image());
			pre.setString(11, item.getCategory_name_en());
			pre.setShort(12, item.getCategory_language());
			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return false;
	}

	private boolean isExisting(CategoryObject item) {
		String sql1 = "select \r\n"
				+ "	*\r\n"
				+ "from\r\n"
				+ "	tblcategory\r\n"
				+ "where\r\n"
				+ "	category_name = 'hello thanh quynh';\r\n"
				+ "";
		ResultSet rs = this.gets(sql1);
		if(sql1 != null) {
			try {
				while(rs.next()) {
					rs.close();
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean editCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getCategory(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from tblcategory "
				+ "left join tblsection on category_section_id = section_id "
				+ "where category_id = ?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getCategories(Triplet<CategoryObject, Integer, Byte> infors) {
		// TODO Auto-generated method stub
//		String sql = "SELECT * FROM tblcategory ";
//		sql += "LEFT JOIN tblsection ON category_section_id = section_id ";
//		sql += "order by category_name asc ";
//		sql += "limit " + at + ", " + total;

		CategoryObject similar = infors.getValue0();
		int at = infors.getValue1();
		byte total = infors.getValue2();

		StringBuilder multiSelect = new StringBuilder();
		multiSelect.append("SELECT * FROM tblcategory ");
		multiSelect.append("LEFT JOIN tblsection ON category_section_id = section_id ");
		multiSelect.append("ORDER BY category_name ASC ");
		multiSelect.append("LIMIT ").append(at).append(", ").append(total).append("; ");
		multiSelect.append("SELECT COUNT(category_id) as total FROM tblcategory;");

		return this.getReList(multiSelect.toString());
	}

//	public static void main(String[] args) {
//		ConnectionPool cp = new ConnectionPoolImpl();
//		Category c = new CategoryImplV2(cp);
////		ArrayList<ResultSet> res = c.getCategories(new Triplet<CategoryObject, Integer, Byte>(null, 0, 20));
//
//		ResultSet rs = res.get(0);
//		CategoryObject item = new CategoryObject();
//		item.setCategory_name("Hello Thanh Quynh");
//		item.setCategory_section_id((short) 2);
//
////		boolean result = c.addCategory(item);
////
////		if(!result) {
////			System.out.println("===KHONG THANH CONG===");
////		}
//
//
//
//		StringBuilder builder = new StringBuilder();
//		if(rs != null) {
//			try {
//				while(rs.next()) {
//					builder.append("ID: " + rs.getInt("category_id"));
//					builder.append("\tName: " + rs.getString("category_name"));
//					System.out.println(builder.toString());
//					builder.setLength(0);
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
}
