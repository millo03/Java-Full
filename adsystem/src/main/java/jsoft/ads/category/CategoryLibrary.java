package jsoft.ads.category;

import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.objects.CategoryObject;

public class CategoryLibrary {
	public static ArrayList<String> viewCategory(Pair<ArrayList<CategoryObject>, Integer> infors) {
		StringBuilder row = new StringBuilder();

		ArrayList<CategoryObject> datas = infors.getValue0();
//		int numOfRecords = infors.getValue1();

		row.append("<table class=\"table table-striped\">\n");

		row.append("	<thead>\n");
		row.append("		<tr>");
		row.append("			<th scope=\"col\">#</th>");
		row.append("			<th scope=\"col\">Name</th>");
		row.append("			<th scope=\"col\">Section ID</th>");
		row.append("			<th scope=\"col\">Notes</th>");
		row.append("			<th scope=\"col\">Author ID</th>");
		row.append("			<th scope=\"col\">Last modified</th>");
		row.append("			<th scope=\"col\">Manager ID</th>");
		row.append("			<th scope=\"col\" colspan = \"3\">Action</th>");
		row.append("		</tr>\n");
		row.append("	</thead>\n");

		row.append("	<tbody>\n");

		datas.forEach(item -> {
			row.append("		<tr>");
			row.append("			<th scope=\"row\">" + item.getCategory_id() + "</th>");
			row.append("			<td>" + item.getCategory_name() + "</td>");
			row.append("			<td>" + item.getCategory_section_id() + "</td>");
			row.append("			<td>" + item.getCategory_notes() + "</td>");
			row.append("			<td>" + item.getCategory_created_author_id() + "</td>");
			row.append("			<td>" + item.getCategory_last_modified() + "</td>");
			row.append("			<td>" + item.getCategory_manager_id() + "</td>");
			row.append("			<td><button class=\"btn btn-outline-primary btn-sm align-middle\">Detail</button></td>");
			row.append("			<td><button class=\"btn btn-outline-secondary btn-sm align-middle\">Update</button></td>");
			row.append("			<td><button class=\"btn btn-outline-danger btn-sm align-middle\">Delete</button></td>");
			row.append("		</tr>\n");
		});

		row.append("	</tbody>\n");
		row.append("</table>\n");

//		row.append("Total: ").append(numOfRecords);

		ArrayList<String> view = new ArrayList<>();
		view.add(row.toString());

		row.setLength(0);

		row.append("<nav aria-label=\"...\">\n");
		row.append("	<ul class=\"pagination\">\n");
		row.append("		<li class=\"page-item disabled\">");
		row.append("			<a class=\"page-link\" href=\"#\" tabindex=\"-1\" aria-disabled=\"true\">Previous</a>");
		row.append("		</li>\n");
		row.append("		<li class=\"page-item\"><a class=\"page-link\" href=\"#\">1</a></li>\n");
		row.append("		<li class=\"page-item active\" aria-current=\"page\">");
		row.append("			<a class=\"page-link\" href=\"#\">2</a>");
		row.append("		</li>\n");
		row.append("		<li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>\n");
		row.append("		<li class=\"page-item\">");
		row.append("			<a class=\"page-link\" href=\"#\">Next</a>");
		row.append("		</li>\n");
		row.append("	</ul>\n");
		row.append("</nav>\n");

		view.add(row.toString()); // Paging

		return view;
	}
}
