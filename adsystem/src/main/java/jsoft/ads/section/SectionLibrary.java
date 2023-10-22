package jsoft.ads.section;

import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.objects.SectionObject;

public class SectionLibrary {
	public static ArrayList<String> viewSection(Pair<ArrayList<SectionObject>, Integer> infors) {
		StringBuilder row = new StringBuilder();
		ArrayList<SectionObject> datas = infors.getValue0();
//		int numOfRecord = infors.getValue1();
		row.append("<table class=\"table table-striped\">\n");

		row.append("	<thead>\n");
		row.append("		<tr>");
		row.append("			<th scope=\"col\">#</th>");
		row.append("			<th scope=\"col\">Name</th>");
		row.append("			<th scope=\"col\">Notes</th>");
		row.append("			<th scope=\"col\">Manager ID</th>");
		row.append("			<th scope=\"col\">Last modified</th>");
		row.append("			<th scope=\"col\" colspan = \"3\">Action</th>");
		row.append("		</tr>\n");
		row.append("	</thead>\n");

		row.append("	<tbody>\n");

		datas.forEach(item -> {
			row.append("		<tr>");
			row.append("			<th scope=\"row\">" + item.getSection_id() + "</th>");
			row.append("			<td>" + item.getSection_name() + "</td>");
			row.append("			<td>" + item.getSection_notes() + "</td>");
			row.append("			<td>" + item.getSection_manager_id() + "</td>");
			row.append("			<td>" + item.getSection_last_modified() + "</td>");
			row.append("			<td><button class=\"btn btn-outline-primary btn-sm align-middle\">Detail</button></td>");
			row.append("			<td><button class=\"btn btn-outline-secondary btn-sm align-middle\">Update</button></td>");
			row.append("			<td><button class=\"btn btn-outline-danger btn-sm align-middle\">Delete</button></td>");
			row.append("		</tr>\n");
		});

		row.append("	</tbody>\n");
		row.append("</table>\n");
//		row.append("Total: ").append(numOfRecord);

		ArrayList<String> view = new ArrayList<>();
		view.add(row.toString()); // List

		row.setLength(0);

		row.append("<nav aria-label=\"...\">");
		row.append("	<ul class=\"pagination\">");
		row.append("		<li class=\"page-item disabled\">");
		row.append("			<a class=\"page-link\" href=\"#\" tabindex=\"-1\" aria-disabled=\"true\">Previous</a>");
		row.append("		</li>");
		row.append("		<li class=\"page-item\"><a class=\"page-link\" href=\"#\">1</a></li>");
		row.append("		<li class=\"page-item active\" aria-current=\"page\">");
		row.append("			<a class=\"page-link\" href=\"#\">2</a>");
		row.append("		</li>");
		row.append("		<li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>");
		row.append("		<li class=\"page-item\">");
		row.append("			<a class=\"page-link\" href=\"#\">Next</a>");
		row.append("		</li>");
		row.append("	</ul>");
		row.append("</nav>");

		view.add(row.toString()); // Paging

		return view;
	}
}
