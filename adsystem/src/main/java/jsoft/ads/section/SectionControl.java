package jsoft.ads.section;

import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.objects.SectionObject;

public class SectionControl {
	private SectionModel sm;

	public SectionControl(ConnectionPool cp) {
		sm = new SectionModel(cp);
	}

	public ConnectionPool getCP() {
		return this.sm.getCP();
	}

	public void releaseConnection() {
		this.sm.releaseConnection();
	}


	public boolean addSection(SectionObject item) {
		return this.sm.addSection(item);
	}

	public boolean editSection(SectionObject item) {
		return this.sm.editSection(item);
	}

	public boolean delSection(SectionObject item) {
		return this.sm.delSection(item);
	}



	public ArrayList<String> viewSection(SectionObject similar, short page, byte total) {
		return SectionLibrary.viewSection(sm.getSections(similar, page, total));
	}

	public static void main(String[] args) {
		SectionControl sc = new SectionControl(null);

		ArrayList<String> view = sc.viewSection(null, (short) 1, (byte) 10);

		sc.releaseConnection();

		view.forEach(item -> {
			System.out.println(item);
		});
	}
}
