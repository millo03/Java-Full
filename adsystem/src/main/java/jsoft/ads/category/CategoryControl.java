package jsoft.ads.category;

import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.objects.CategoryObject;

public class CategoryControl {
	private CategoryModel cm;

	public CategoryControl(ConnectionPool cp) {
		cm = new CategoryModel(cp);
	}

	public ConnectionPool getCP() {
		return this.cm.getCP();
	}

	public void releaseConnection() {
		this.cm.releaseConnection();
	}

	public boolean addCategory(CategoryObject item) {
		return this.addCategory(item);
	}

	public boolean editCategory(CategoryObject item) {
		return this.editCategory(item);
	}

	public boolean delCategory(CategoryObject item) {
		return this.delCategory(item);
	}

	public ArrayList<String> viewCategory(CategoryObject similar, short page, byte total) {
		return CategoryLibrary.viewCategory(cm.getCategories(similar, page, total));
	}

	public static void main(String[] args) {
		CategoryControl cc = new CategoryControl(null);

		ArrayList<String> view = cc.viewCategory(null, (short) 1, (byte) 10);

		cc.releaseConnection();

		view.forEach(item -> {
			System.out.println(item);
		});
	}
}
