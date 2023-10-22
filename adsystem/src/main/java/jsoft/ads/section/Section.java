package jsoft.ads.section;

import java.sql.ResultSet;
import java.util.ArrayList;

import jsoft.ShareControl;
import jsoft.objects.SectionObject;

public interface Section extends ShareControl {
	// Các phương thức cập nhật thông tin Người sử dụng
	public boolean addSection(SectionObject item);
	public boolean editSection(SectionObject item);
	public boolean delSection(SectionObject item);

	// Các phương thức / chức năng lấy thông tin Người sử dụng
	public ResultSet getSection(int id);
	public ArrayList<ResultSet> getSections(SectionObject similar, int at, byte total);
}
