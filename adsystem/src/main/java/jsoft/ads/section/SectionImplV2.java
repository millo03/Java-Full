package jsoft.ads.section;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.SectionObject;

/**
 *
 * Hw
 *
 * @author vieth
 *
 */

public class SectionImplV2 extends BasicImpl implements Section {

	public SectionImplV2(ConnectionPool cp) {
		super(cp, "Section");
		// TODO Auto-generated constructor stub
	}

	public SectionImplV2(ConnectionPool cp, String objectName) {
		super(cp, objectName);
	}

	@Override
	public boolean addSection(SectionObject item) {
		// TODO Auto-generated method stub

		if (this.isExisting(item))
			return false;

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblsection(");
		sql.append("section_name, section_notes, section_created_date, ");
		sql.append("section_manager_id, section_enable, section_delete, ");
		sql.append("section_last_modified, section_created_author_id, section_name_en, ");
		sql.append("section_language)");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());
			pre.setString(3, item.getSection_created_date());
			pre.setInt(4, item.getSection_manager_id());
			pre.setBoolean(5, item.isSection_enable());
			pre.setBoolean(6, item.isSection_delete());
			pre.setString(7, item.getSection_last_modified());
			pre.setInt(8, item.getSection_created_author_id());
			pre.setString(9, item.getSection_name_en());
			pre.setByte(10, item.getSection_language());

			return this.add(pre);
		} catch (SQLException e) {
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean isExisting(SectionObject item) {
		String sql = "SELECT section_id FROM tblsection WHERE section_name = '" + item.getSection_name() + "' ";
		ResultSet rs = this.gets(sql);
		if (rs != null) {
			// kể cả không tìm được trong câu lệnh select thì rs vẫn != null, nên mới cần
			// vòng while check rs.next()
			try {
				while (rs.next()) {
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
	public boolean editSection(SectionObject item) {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblsection SET ");
		sql.append("section_name = ?, section_notes = ?, section_manager_id = ?, ");
		sql.append("section_enable = ?, section_delete = ?, section_last_modified = ?, ");
		sql.append("section_name_en = ?, section_language = ? ");
		sql.append("WHERE section_id = " + item.getSection_id());

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setString(1, item.getSection_name());
			// 1 đại diện cho dấu hỏi chấm thứ nhất, 2 đại diện cho dấu hỏi chấm thứ hai, ...
			// có thể truyền trực tiếp get... vào trong đó luôn cũng được, có 2 cách
			pre.setString(2, item.getSection_notes());
			pre.setInt(3, item.getSection_manager_id());
			pre.setBoolean(4, item.isSection_enable());
			pre.setBoolean(5, item.isSection_delete());
			pre.setString(6, item.getSection_last_modified());
			pre.setString(7, item.getSection_name_en());
			pre.setByte(8, item.getSection_language());
			return this.edit(pre);
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

	@Override
	public boolean delSection(SectionObject item) {
		// TODO Auto-generated method stub

		if(!this.isEmpty(item)) return false;

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblsection ");
		sql.append("WHERE section_id = " + item.getSection_id());

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean isEmpty(SectionObject item) {
		String sql1 = "SELECT article_id FROM tblarticle WHERE article_section_id = ?";
		String sql2 = "SELECT category_id FROM tblcategory WHERE category_section_id = ?";

		ResultSet rs1 = this.get(sql1, item.getSection_created_author_id());
		ResultSet rs2 = this.get(sql2, item.getSection_created_author_id());

		if(rs1 != null || rs2 != null) {
			try {
				if(rs1.next() || rs2.next()) {
					rs1.close();
					rs2.close();
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ResultSet getSection(int id) {
		String sql = "SELECT * FROM tblsection WHERE section_id = ?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getSections(SectionObject similar, int at, byte total) {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM tblsection ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append("; ");

		sql.append("SELECT COUNT(section_id) as total FROM tblsection;");

		return this.getReList(sql.toString());
	}

	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();

		Section s = new SectionImplV2(cp);

		SectionObject newSection = new SectionObject();
		newSection.setSection_id((short) 19);
		newSection.setSection_name("Hello thanh quynh");

		boolean result = s.addSection(newSection);

		if (!result) {
			System.out.println("KHÔNG THÀNH CÔNG");
		}

		ArrayList<ResultSet> res = s.getSections(null, 0, (byte) 10);
		ResultSet rs = res.get(0);
//		ResultSet rs = s.getSection(1);

		StringBuilder builder = new StringBuilder();
		if (rs != null) {
			try {
				while (rs.next()) {
					builder.append("ID: " + rs.getShort("section_id"));
					builder.append("\tName: " + rs.getString("section_name"));
					builder.append("\tCreated date: " + rs.getString("section_created_date"));
					System.out.println(builder.toString());
					builder.setLength(0);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
