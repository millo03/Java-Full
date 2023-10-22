package jsoft.ads.user;

import java.util.ArrayList;

import jsoft.library.Utilities;
import jsoft.objects.UserObject;

public class UserLibrary {

	public static ArrayList<String> viewUser(ArrayList<UserObject> items, UserObject user) {
		StringBuilder row = new StringBuilder();
		row.append("<table class=\"table table-striped table-sm\">");
		row.append("<thead>");
		row.append("<tr class=\"align-middle\">");
		row.append("<th scope=\"col\" class=\"align-middle\">#</th>");
		row.append("<th scope=\"col\" class=\"align-middle\">Tên đăng nhập</th>");
		row.append("<th scope=\"col\" class=\"align-middle\">Tên đầy đủ</th>");
		if(user.isUser_delete()) {
			row.append("<th scope=\"col\" class=\"align-middle\">Ngày xóa</th>");
			row.append("<th scope=\"col\" class=\"text-center align-middle\" colspan=\"2\">Thực hiện</th>");
			
		}else {
			row.append("<th scope=\"col\" class=\"align-middle\">Hộp thư</th>");
			row.append("<th scope=\"col\" class=\"align-middle\">Điện thoại</th>");
			row.append("<th scope=\"col\" class=\"align-middle\">Lần đăng nhập</th>");
			row.append("<th scope=\"col\" class=\"text-center align-middle\" colspan=\"3\">Thực hiện</th>");
		}
		
		row.append("</tr>");
		row.append("</thead>");

		row.append("<tbody>");
		items.forEach(item -> {
			row.append("<tr>");
			row.append("<th class=\"align-middle\" scope=\"row\">").append(item.getUser_id()).append("</th>");
			row.append("<td class=\"align-middle\">").append(item.getUser_name()).append("</td>");
			row.append("<td class=\"align-middle\">").append(item.getUser_fullname()).append("</td>");
			//row.append("<td class=\"align-middle\">").append(item.getUser_fullname()).append("</td>");
			//System.out.println("user_id = "+ item.getUser_id()+ " user_delete ="+ item.isUser_delete());
			if(user.isUser_delete()) {
				row.append("<td class=\"align-middle\">").append(item.getUser_last_modified()).append("</td>");
				row.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-reply\"></i></a></td>");
				// Delete
				row.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\"  data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
				row.append(UserLibrary.viewDelUser(item));
			}else {
				row.append("<td class=\"align-middle\">").append(item.getUser_email()).append("</td>");
				row.append("<td class=\"align-middle\">").append(item.getUser_homephone()).append("</td>");
				row.append("<td class=\"align-middle\">").append(item.getUser_logined()).append("</td>");
				
				// Detail
				row.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles?id="+ item.getUser_id() + "&t=over\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-eye\"></i></a></td>");
	
				if (item.getUser_id() == user.getUser_id()) {
					// Update
					row.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles?id=" + item.getUser_id() + "&t=edit\" class=\"btn btn-secondary btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
					// Delete
					row.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm disabled\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser\"><i class=\"bi bi-trash3\"></i></button></td>");
				} else {
					if (user.getUser_permission() >= 4) {
						// Update
						row.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles?id=" + item.getUser_id() + "&t=edit\" class=\"btn btn-secondary btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
						// Delete
						row.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\"  data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
						row.append(UserLibrary.viewDelUser(item));
					} else {
						if (item.getUser_parent_id() == user.getUser_id()) {
							// Update
							row.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles?id=" + item.getUser_id() + "&t=edit\" class=\"btn btn-secondary btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
						} else {
							// Update
							row.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles\" class=\"btn btn-secondary btn-sm disabled\"><i class=\"bi bi-pencil-square\"></i></a></td>");
						}
						// Delete
						row.append("<td class=\"align-middle\"><a href=\"#\" class=\"btn btn-danger btn-sm disabled\"><i class=\"bi bi-trash3\" disabled></i></a></td>");
					}
				}
			}//list

			row.append("</tr>");
		});
		row.append("</tbody>");
		row.append("</table>");

		ArrayList<String> view = new ArrayList<>();
		view.add(row.toString());
		// Cấu trúc biểu đồ
		String chart = UserLibrary.createChart(items).toString();
		view.add(chart);

		return view;
	}
	
	private static StringBuilder viewDelUser(UserObject item) {
		StringBuilder tmp = new StringBuilder();
	
		String title;
		if(item.isUser_delete()) {
			title= "Xóa vĩnh viễn";
		}else {
			title= "Xóa tài khoản";
		}
		tmp.append("<div class=\"modal fade\" id=\"delUser"+item.getUser_id()+"\" tabindex=\"-1\" aria-labelledby=\"UserLabel"+item.getUser_id()+"\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"UserLabel"+item.getUser_id()+"\">"+title+"</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		if (item.isUser_delete()) {
			tmp.append("Bạn sẽ xóa vĩnh viễn tài khoản <b>").append(Utilities.decode(item.getUser_fullname())).append("("+ Utilities.decode(item.getUser_name())+")</b>?<br>");
			tmp.append("Tài khoản không thể phục hồi được nữa.");
		}else {
			tmp.append("Bạn có chắc chắn muốn xóa tài khoản <b>").append(item.getUser_fullname()).append("("+ item.getUser_name()+")</b>?<br>");
			tmp.append("Hệ thống tạm thời lưu vào thùng rác, tài khoản có thể phục hồi trong vòng 30 ngày.");
		}
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		//System.out.println("delete = "+ item.isUser_delete());
		if(item.isUser_delete()) {
			tmp.append("<a href=\"/adv/user/dr?id="+item.getUser_id()+"&pid="+item.getUser_parent_id()+"\" class=\"btn btn-danger\">Xóa</a>");//dr: delete or restore
		}else {
															// &t là của TRASH ko cần truyền và giá trị
			tmp.append("<a href=\"/adv/user/dr?id="+item.getUser_id()+"&t&pid="+item.getUser_parent_id()+"\" class=\"btn btn-danger\">Xóa</a>");//dr: delete or restore
		}
		tmp.append("<button type=\"button\" class=\"btn btn-primary \" data-bs-dismiss=\"modal\">Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");

		return tmp;
	}
	
	public static StringBuilder createChart(ArrayList<UserObject> items) {
		StringBuilder data = new StringBuilder();
		StringBuilder accounts = new StringBuilder();
		items.forEach(item ->{// foreach phải dùng tham chiếu
			data.append(item.getUser_logined());
			accounts.append("'"+Utilities.decode(item.getUser_fullname())).append(" ("+ Utilities.decode(item.getUser_name())+" )'");
			// sẽ loại bỏ dấu phẩy cuối cùng, nếu có dấu phải cuối chart có thêm cột trống
			if(items.indexOf(item)< items.size()) {
				data.append(",");
				accounts.append(",");
			}
			
		});
		
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<h5 class=\"card-title\">Bar Chart</h5>");
		tmp.append("");
		tmp.append("<!-- Bar Chart -->");
		tmp.append("<div id=\"barChart\"></div>");
		 
		tmp.append("<script>");
		tmp.append("document.addEventListener(\"DOMContentLoaded\", () => {");
		tmp.append("new ApexCharts(document.querySelector(\"#barChart\"), {");
		tmp.append("series: [{");
		tmp.append("data: ["+data.toString()+"]");
		tmp.append("}],");
		tmp.append("chart: {");
		tmp.append("type: 'bar',");
		tmp.append("height: 350");
		tmp.append("},");
		tmp.append("plotOptions: {");
		tmp.append("bar: {");
		tmp.append("borderRadius: 4,");
		tmp.append("horizontal: true,");
		tmp.append("}");
		tmp.append("},");
		tmp.append("dataLabels: {");
		tmp.append("enabled: false");
		tmp.append("},");
		tmp.append("xaxis: {");
		tmp.append("categories: ["+accounts.toString()+"],");

		tmp.append("}");
		tmp.append("}).render();");
		tmp.append("});");
		tmp.append("</script>");
		tmp.append("<!-- End Bar Chart -->");
		 
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}

}
