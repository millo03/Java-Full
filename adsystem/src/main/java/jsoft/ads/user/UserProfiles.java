package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.library.UtilitiesDate;
import jsoft.library.UtilitiesText;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class View
 */
@WebServlet("/user/profiles")
public class UserProfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		 Tìm thông tin đăng nhập
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		
		if (user != null) {
			view(request, response, user);
		} else {
			response.sendRedirect("/adv/user/login");
		}
	}

	protected void view(HttpServletRequest request, HttpServletResponse response, UserObject user)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		// Tim id cua nguoi su dung de cap nhat
		int id = Utilities.getIntParam(request, "id");
		
		//xd vị trí tab làm việc
		String tab = request.getParameter("t");
		HashMap<String, String> tab_active = new HashMap<>();
		HashMap<String, String> show_active = new HashMap<>();
		
		if(tab!= null&&(tab.equalsIgnoreCase("over")|| tab.equalsIgnoreCase("edit"))) {
			tab_active.put(tab, "active");
			show_active.put(tab,"show active");
			
		}else {
			tab_active.put("over", "active");
			show_active.put("over","show active");
		}

		UserObject selectedItem = null;

		if (id > 0) {
			// Tim bo quan ly ket noi
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

			// Tao doi tuong thuc thi chuc nang
			UserControl uc = new UserControl(cp);

			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}
			selectedItem = uc.getUserObject(id);

			// Tra ve ket noi ngay sau khi su dung xong
			uc.releaseConnection();
		}

		// Tham chieu tim Header
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=urlist");
		if (header != null) {
			header.include(request, response);
		}

		out.append("    <main id=\"main\" class=\"main\">");

		RequestDispatcher error = getServletContext().getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách Người sử dụng</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Người sử dụng</li>");
		out.append("<li class=\"breadcrumb-item active\">Cập nhật chi tiết</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section profile\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-xl-4\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body profile-card pt-4 d-flex flex-column align-items-center\">");

		if (selectedItem != null) {
			out.append("<img src=\"assets/img/profile-img.jpg\" alt=\"Profile\" class=\"rounded-circle\">");
			out.append("<h2>" + selectedItem.getUser_fullname() + "</h2>");
			out.append("<h3>" + selectedItem.getUser_name() + "</h3>");
			out.append("<div class=\"social-links mt-2\">");
			out.append("<a href=\"#\" class=\"twitter\"><i class=\"bi bi-twitter\"></i></a>");
			out.append("<a href=\"#\" class=\"facebook\"><i class=\"bi bi-facebook\"></i></a>");
			out.append("<a href=\"#\" class=\"instagram\"><i class=\"bi bi-instagram\"></i></a>");
			out.append("<a href=\"#\" class=\"linkedin\"><i class=\"bi bi-linkedin\"></i></a>");
			out.append("</div>");
		}

		out.append("</div>"); // card
		out.append("</div>"); // col-xl-4
		out.append("</div>"); // row

		out.append("<div class=\"col-xl-8\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body pt-3\">");
		out.append("<!-- Bordered Tabs -->");
		out.append("<ul class=\"nav nav-tabs nav-tabs-bordered\">");
		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link "+tab_active.getOrDefault("over", "")+"\" data-bs-toggle=\"tab\" data-bs-target=\"#overview\"><i class=\"bi bi-info-circle\"></i> Tổng quát</button>");
		out.append("</li>");
		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link "+tab_active.getOrDefault("edit", "")+"\" data-bs-toggle=\"tab\" data-bs-target=\"#edit\"><i class=\"bi bi-pencil-square\"></i> Chỉnh sửa</button>");
		out.append("</li>");
		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link "+tab_active.getOrDefault("sett", "")+"\" data-bs-toggle=\"tab\" data-bs-target=\"#settings\"><i class=\"bi bi-gear\"></i> Cài đặt</button>");
		out.append("</li>");
		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link "+tab_active.getOrDefault("pass", "")+"\" data-bs-toggle=\"tab\" data-bs-target=\"#change-password\"><i class=\"bi bi-pass\"></i> Thay đổi mật khẩu</button>");
		out.append("</li>");
		out.append("</ul>");
		out.append("<div class=\"tab-content pt-2\">");

		String summary = "", name = "", fullName = "";
		String address = "", email = "", hphone = "",mphone = "",ophone = "", job = "", jobArea = "";
		short logined = 0;
		String dob = "";
		boolean isEdit = false;

		if (selectedItem != null) {
			summary = selectedItem.getUser_notes();
			name = selectedItem.getUser_name();
			fullName = selectedItem.getUser_fullname();
			address = selectedItem.getUser_address();
			email = selectedItem.getUser_email();
			hphone = selectedItem.getUser_homephone();
			ophone = selectedItem.getUser_officephone();
			mphone = selectedItem.getUser_mobilephone();
			job = selectedItem.getUser_job();
			jobArea = selectedItem.getUser_jobarea();
			logined = selectedItem.getUser_logined();
			dob = selectedItem.getUser_birthday();
			isEdit = true;
		}
			
		out.append("<div class=\"tab-pane fade "+show_active.getOrDefault("over", "")+" profile-overview\" id=\"overview\">");
		out.append("<h5 class=\"card-title\">Tóm tắt</h5>");
		out.append("<p class=\"small fst-italic\">" + summary + "</p>");
		out.append("<h5 class=\"card-title\">Thông tin chi tiết</h5>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label \">Họ và tên</div>");
		out.append("<div class=\"col-lg-7 col-md-6\">" + fullName + "</div>");
		out.append("<div class=\"col-lg-2 col-md-2\">(" + name + ")</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Ngày sinh</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + dob + "</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Nghề nghiệp</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + job + "</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Lĩnh vực</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + jobArea + "</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Địa chỉ</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + address + "</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Số điện thoại</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + hphone + "</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Địa chỉ hộp thư</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + email + "</div>");
		out.append("</div>");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Số lần đăng nhập</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + logined + "</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"tab-pane fade "+show_active.getOrDefault("edit","")+" profile-edit pt-3\" id=\"edit\">");
		out.append("<!-- Profile Edit Form -->");// profiles edit form
		out.append("<form method=\"post\" action=\"/adv/user/profiles\">");
		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"profileImage\" class=\"col-md-3 col-lg-2 col-form-label\">Ảnh đại diện</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<img src=\"assets/img/profile-img.jpg\" alt=\"Profile\">");
		out.append("<div class=\"pt-2\">");
		out.append("<a href=\"#\" class=\"btn btn-primary btn-sm\" title=\"Upload new profile image\"><i class=\"bi bi-upload\"></i></a>");
		out.append("<a href=\"#\" class=\"btn btn-danger btn-sm\" title=\"Remove my profile image\"><i class=\"bi bi-trash\"></i></a>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"fullName\" class=\"col-md-3 col-lg-2 text-end \">Họ và tên</label>");
		out.append("<div class=\"col-md-7 col-lg-8 \">");
		out.append("<div class=\"input-group \">");
		out.append("<input name=\"fullNam\" type=\"text\" class=\"form-control\" id= \"fullName\" value=\""+fullName+"\">");
		out.append("<input name=\"txtAlias\" type=\"text\" class=\"form-control\" id= \"txtAlias\" readonly value=\"\">");
		out.append("</div>");//đóng div input group
		out.append("</div>");
		out.append("<div class=\"col-md-2 col-lg-2\">");
		out.append("<input name=\"name\" type=\"text\" class=\"form-control\" disabled id=\"name\" value=\""+ name + "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"notes\" class=\"col-md-3 col-lg-2 col-form-label\">Tóm tắt</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<textarea name=\"txtNotes\" class=\"form-control\" id=\"notes\" style=\"height: 100px\">"+ summary + "</textarea>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"date\" class=\"col-md-3 col-lg-2 col-form-label\">Ngày sinh</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"txtBirthday\" type=\"date\" class=\"form-control\" id=\"date\" value=\""+ dob + "\">");
		out.append("</div>");
		out.append("<label for=\"slcGender\" class=\"col-md-3 col-lg-2 col-form-label\">Giới tính</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<select class =\"form-control\" id=\"slcGender\" name = \"slcGender\">");	
		out.append("<option value=\"\">-------</option>     ");
		out.append(" <option value=\"\">Nam</option>     ");
		out.append(" <option value=\"\">Nữ</option>     ");
		out.append("</select >");	
		
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"job\" class=\"col-md-3 col-lg-2 col-form-label\">Nghề nghiệp</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"txtJob\" type=\"text\" class=\"form-control\" id=\"job\" value=\""+ job + "\">");
		out.append("</div>");
		
		out.append("<label for=\"jobarea\" class=\"col-md-3 col-lg-2 col-form-label\">Lĩnh vực</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"txtJobArea\" type=\"text\" class=\"form-control\" id=\"jobarea\" value=\""+ jobArea + "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Address\" class=\"col-md-3 col-lg-2 col-form-label\">Địa chỉ</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<input name=\"txtAddress\" type=\"text\" class=\"form-control\" id=\"Address\" value=\""+ address + "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Phone\" class=\"col-md-3 col-lg-2 col-form-label\">Số điện thoại</label>");		
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<div class=\"input-group\">");
		out.append("<input name=\"txtPhone\" type=\"text\" class=\"form-control\" id=\"txtHPhone\" value=\""+ hphone + "\"placehoder=\"Home Phone\" >");
		out.append("<input name=\"txtPhone\" type=\"text\" class=\"form-control\" id=\"txtOPhone\" value=\""+ ophone + "\"placehoder=\"Office Phone\">");
		out.append("<input name=\"txtPhone\" type=\"text\" class=\"form-control\" id=\"txtMPhone\" value=\""+ mphone + "\"placehoder=\"Mobile Phone\">");
		out.append("</div>");
		out.append("</div>");// div đóng inp
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Email\" class=\"col-md-3 col-lg-2 col-form-label\">Địa chỉ hộp thư</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<input name=\"txtEmail\" type=\"email\" class=\"form-control\" id=\"Email\" value=\""	+ email + "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Facebook\" class=\"col-md-3 col-lg-2 col-form-label\">Facebook cá nhân</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<input name=\"txtFacebookURL\" type=\"text\" class=\"form-control\" id=\"Facebook\" value=\"https://facebook.com/#\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-save\"></i> Lưu thay đổi</button>");
		out.append("</div>");
		// Truyền id theo cơ chế biến form ẩn để thực hiện edit
		if (isEdit) {
			out.append("<input type=\"hidden\" name=\"idForPost\" value=\"" + id + "\" >");
			out.append("<input type=\"hidden\" name=\"action\" value=\"edit\" >");
		}
		out.append("</form><!-- End Profile Edit Form -->");
		out.append("</div>");

		out.append("<div class=\"tab-pane "+show_active.getOrDefault("sett", "")+"fade pt-3\" id=\"settings\">");
		out.append("<!-- Settings Form -->");

		out.append("<form>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Email Notifications</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"changesMade\" checked>");
		out.append("<label class=\"form-check-label\" for=\"changesMade\">");
		out.append("Changes made to your account");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"newProducts\" checked>");
		out.append("<label class=\"form-check-label\" for=\"newProducts\">");
		out.append("Information on new products and services");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"proOffers\">");
		out.append("<label class=\"form-check-label\" for=\"proOffers\">");
		out.append("Marketing and promo offers");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"securityNotify\" checked disabled>");
		out.append("<label class=\"form-check-label\" for=\"securityNotify\">");
		out.append("Security alerts");
		out.append("</label>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">Save Changes</button>");
		out.append("</div>");
		out.append("</form><!-- End settings Form -->");

		out.append("</div>");

		out.append("<div class=\"tab-pane fade"+show_active.getOrDefault("pass", "")+" pt-3\" id=\"change-password\">");
		out.append("<!-- Change Password Form -->");
		out.append("<form>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"currentPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Mật khẩu cũ</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"password\" type=\"password\" class=\"form-control\" id=\"currentPassword\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"newPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Mật khẩu mới</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"newpassword\" type=\"password\" class=\"form-control\" id=\"newPassword\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"renewPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Nhập lại mật khẩu mới</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"renewpassword\" type=\"password\" class=\"form-control\" id=\"renewPassword\">");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">Change Password</button>");
		out.append("</div>");
		out.append("</form><!-- End Change Password Form -->");
		out.append("</div>");
		out.append("</div><!-- End Bordered Tabs -->");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</section>");

		out.append("    </main><!-- End #main -->");

		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if (footer != null) {
			footer.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		// Lấy id của người sử dụng để chỉnh sửa // để xóa
		int id = Utilities.getIntParam(request, "idForPost");
		String action = request.getParameter("action");

		if (id > 0) {

			if (action != null && action.equals("edit")) {

				UserObject parentUser = (UserObject) request.getSession().getAttribute("userLogined");

				String fullName = request.getParameter("txtFullName");
				String address = request.getParameter("txtAddress");
				String job = request.getParameter("txtJob");
				String jobArea = request.getParameter("txtJobArea");
				String email = request.getParameter("txtEmail");
				String hphone = request.getParameter("txtHPhone");
				String mphone = request.getParameter("txtMPhone");
				String ophone = request.getParameter("txtOPhone");
				String notes = request.getParameter("txtNotes");
				String birthday = request.getParameter("txtBirthday");

				if (fullName.length() > 0 
						&& UtilitiesText.isValidValue(email) 
						&& UtilitiesText.isValidValue(hphone)) {
					UserObject selectedUser = new UserObject();
					
					selectedUser.setUser_id(id);
					selectedUser.setUser_fullname(Utilities.encode(fullName));
					selectedUser.setUser_address(Utilities.encode(address));
					selectedUser.setUser_parent_id(parentUser.getUser_id());
					selectedUser.setUser_email(email);
					selectedUser.setUser_homephone(hphone);
					selectedUser.setUser_last_modified(UtilitiesDate.getDate());
					selectedUser.setUser_job(Utilities.encode(job));
					selectedUser.setUser_jobarea(Utilities.encode(jobArea));
					selectedUser.setUser_notes(Utilities.encode(notes));
					selectedUser.setUser_birthday(birthday);
					
					ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
					UserControl uc = new UserControl(cp);

					if (cp == null) {
						getServletContext().setAttribute("CPool", cp);
					}
					
					// Thực hiện cập nhật
					boolean result = uc.editUser(selectedUser, USER_EDIT_TYPE.GENERAL);
					uc.releaseConnection();

					if (result) {
						response.sendRedirect("/adv/user/list");
					} else {
						response.sendRedirect("/adv/user/list?err=edit");
					}

				} else {
					response.sendRedirect("/adv/user/list?err=upd");
				}
			}//đóng if id>0
			else {
				// != "edit"
			}
		} else {
			// id <= 0: khong ton tai id
			response.sendRedirect("/adv/user/list?err=profiles");

		}
	}

}
