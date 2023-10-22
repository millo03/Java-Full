package jsoft.ads.category;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ShareControl;
import jsoft.objects.CategoryObject;

public interface Category extends ShareControl {
	// Các phương thức cập nhật thông tin Người sử dụng
	public boolean addCategory(CategoryObject item);
	public boolean editCategory(CategoryObject item);
	public boolean delCategory(CategoryObject item);
	//

	// Các phương thức / chức năng lấy thông tin Người sử dụng
	public ResultSet getCategory(int id);
	public ArrayList<ResultSet> getCategories(Triplet<CategoryObject, Integer, Byte> infors);
}
