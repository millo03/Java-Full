package jsoft.ads.section;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.objects.SectionObject;

public class SectionModel {
	private Section s;

	public SectionModel(ConnectionPool cp) {
		this.s = new SectionImplV2(cp);
	}

	protected void finallize() throws Throwable{
		this.s = null;
	}

	public ConnectionPool getCP() {
		return this.s.getCP();
	}

	public void releaseConnection() {
		this.s.releaseConnection();
	}

	//*************************************************************************************
	public boolean addSection(SectionObject item) {
		return this.s.addSection(item);
	}

	public boolean editSection(SectionObject item) {
		return this.s.editSection(item);
	}

	public boolean delSection(SectionObject item) {
		return this.s.delSection(item);
	}

	//*************************************************************************************

	public SectionObject getSection(int id) {
		SectionObject item = null;
		ResultSet rs = this.s.getSection(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new SectionObject();
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_created_date(rs.getString("section_created_date"));
					item.setSection_manager_id(rs.getInt("section_manager_id"));
					item.setSection_enable(rs.getBoolean("section_enable"));
					item.setSection_delete(rs.getBoolean("section_delete"));
					item.setSection_last_modified(rs.getString("section_last_modified"));
					item.setSection_created_author_id(rs.getInt("section_created_author_id"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_language(rs.getByte("section_language"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}

	/**
	 *
	 * @param similar
	 * @param page: tham số chỉ trang số bao nhiêu (trang số 1, trang số 2, ...)
	 * @param total: tham số chỉ số lượng bản ghi muốn xem trong 1 trang
	 * @return
	 */
	public Pair<ArrayList<SectionObject>, Integer> getSections(SectionObject similar, short page, byte total) {
		ArrayList<SectionObject> items = new ArrayList<>();
		SectionObject item = null;
		// tính chỉ số bản ghi muốn bắt đầu xem
		int at = (page - 1) * total;
		ArrayList<ResultSet> res = this.s.getSections(similar, at, total);
		ResultSet rs = res.get(0);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new SectionObject();

					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_created_date(rs.getString("section_created_date"));
					item.setSection_manager_id(rs.getInt("section_manager_id"));
					item.setSection_enable(rs.getBoolean("section_enable"));
					item.setSection_delete(rs.getBoolean("section_delete"));
					item.setSection_last_modified(rs.getString("section_last_modified"));
					item.setSection_created_author_id(rs.getInt("section_created_author_id"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_language(rs.getByte("section_language"));

					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int all = 0;
		rs = res.get(1);
		if (rs != null) {
			try {
				if (rs.next()) {
					all = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new Pair<>(items, all);
	}

	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();

		SectionModel sm = new SectionModel(cp);

		Pair<ArrayList<SectionObject>, Integer> datas = sm.getSections(null, (short)1, (byte)10);
		ArrayList<SectionObject> list = datas.getValue0();
		int total = datas.getValue1();

		sm.releaseConnection();

		list.forEach(item -> {
			System.out.print(list.indexOf(item) + "\t");
			System.out.println(item.getSection_name());
		});
		System.out.println("Total = " + total);
	}

}
