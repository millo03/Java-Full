package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/user/list")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserList() {
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

		// Tim bo quan ly ket noi
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

		// Tao doi tuong thuc thi chuc nang
		UserControl uc = new UserControl(cp);

		if (cp == null) {
			getServletContext().setAttribute("CPool", uc.getCP());
		}
		//đối tượng lưu trữ bộ lọc
		UserObject similar = new UserObject();
		similar.setUser_id(user.getUser_id());
		similar.setUser_permission(user.getUser_permission());
		similar.setUser_delete(false);
		// tìm tham số xđ loại ds
		String trash = request.getParameter("trash");
		String title,post ;
		if(trash == null) {
			similar.setUser_delete(false);
			post = "urlist";
			title = "Danh sách người sử dụng";
		}else {
			similar.setUser_delete(true);
			post = "urtrash";
			title = "Danh sách người bị xóa";
		}

		// Lay cau truc
		Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors = new Quartet<>(similar, (short) 1, (byte) 10, USER_SORT_TYPE.NAME);

		List<String> viewList = uc.viewUser(infors);

		// Tra ve ket noi ngay sau khi su dung xong
		uc.releaseConnection();
		

		// Tham chieu tim Header
		RequestDispatcher header = request.getRequestDispatcher("/header?pos="+post);
		
		
		if (header != null) {
			header.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher error = getServletContext().getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>"+title+"</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Người sử dụng</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");

		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
//		out.append("<h5 class=\"card-title\">Example Card</h5>");
//		out.append("<p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>");

		out.append("<!-- Button trigger modal -->");
		if(trash == null) {
		
		out.append("<button type=\"button\" class=\"btn btn-primary btn-sm my-3\" data-bs-toggle=\"modal\" data-bs-target=\"#addUser\">");
		out.append("<i class=\"bi bi-person-plus\"></i> Thêm mới </button>");

		out.append("<!-- Modal -->");// thêm mới user
		out.append("<div class=\"modal fade\" id=\"addUser\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"addUserLabel\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-lg\">");
		out.append("<form method=\"post\" action=\"/adv/user/list\" class=\"needs-validation\" novalidate>");//method=\"post\": gọi đến doPost: chờ đợi lấy dữ liệu
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append("<h1 class=\"modal-title fs-5\" id=\"addUserLabel\">Thêm mới người sử dụng</h1>");
		out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"user-name\" class=\"form-label\">Tên tài khoản</label>");
		out.append("<input type=\"text\" class=\"form-control\" id=\"user-name\" name=\"txtUserName\" required>");
		out.append("<div class=\"invalid-feedback\">");
		out.append("Hãy nhập vào tên của tài khoản");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"full-name\" class=\"form-label\">Họ tên</label>");
		out.append("<input type=\"text\" class=\"form-control\" id=\"full-name\" name=\"txtFullName\">");
		out.append("<div class=\"invalid-feedback\">");
		out.append("Hãy nhập vào họ tên của tài khoản");
		out.append("</div>");
		out.append("</div>");//col-lg-6
		out.append("</div>");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"password\" class=\"form-label\">Mật khẩu</label>");
		out.append("<input type=\"password\" class=\"form-control\" id=\"password\" required name=\"txtPassword\">");
		out.append("<div class=\"invalid-feedback\">");
		out.append("Hãy nhập vào mật khẩu cho tài khoản");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"confirm-password\" class=\"form-label\">Nhập lại mật khẩu</label>");
		out.append("<input type=\"password\" class=\"form-control\" id=\"confirm-password\" required name=\"txtConfirmPassword\">");
		out.append("<div class=\"invalid-feedback\">");
		out.append("Hãy nhập lại mật khẩu cho tài khoản");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"email-address\" class=\"form-label\">Hộp thư</label>");
		out.append("<input type=\"email\" class=\"form-control\" id=\"email-address\" required name=\"txtEmailAddress\">");
		out.append("<div class=\"invalid-feedback\">");
		out.append("Hãy nhập vào tên địa chỉ hộp thư");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label>");
		out.append("<input type=\"text\" class=\"form-control\" id=\"phone-number\" required name=\"txtPhoneNumber\">");
		out.append("<div class=\"invalid-feedback\">");
		out.append("Hãy nhập số điện thoại cho tài khoản");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"user-permission\" class=\"form-label\">Quyền thực thi</label>");
		out.append("<select class=\"form-select\" required id=\"user-permission\" name=\"selUserPermis\">");
		out.append("<option value=\"\" selected>--- Chọn ---</option>");
		out.append("<option value=\"1\">Thành viên</option>");
		out.append("<option value=\"2\">Tác giả</option>");
		out.append("<option value=\"3\">Quản lý</option>");
		out.append("<option value=\"4\">Quản tị</option>");
		out.append("<option value=\"5\">Quản trị cấp cao</option>");
		out.append("</select>");
		out.append("<div class=\"invalid-feedback\">");
		out.append("Hãy lựa chọn quyền thực thi");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-lg-6\">");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"modal-footer\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-person-plus\"></i> Thêm mới</button>");
		out.append("<button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-circle\"></i> Thoát</button>");
		out.append("</div>");
		out.append("</div>");
		out.append("</form>");
		out.append("</div>");
		out.append("</div>");// modal
		}


		out.append(viewList.get(0));


		out.append("</div>"); // card-body
		out.append("</div>"); // card
		out.append("</div>"); // col-lg-12

		out.append("</div>"); // row
		
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append(viewList.get(1));
		out.append("</div>");// col-lg-12
		out.append("</div>");//row
		out.append("</section>");

		out.append("</main><!-- End #main -->");

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

		UserObject parentUser = (UserObject) request.getSession().getAttribute("userLogined");

		// tìm thông tin trên giao điện
		String userName = request.getParameter("txtUserName");
		String password = request.getParameter("txtPassword");
		String confirmPassword = request.getParameter("txtConfirmPassword");
		String email = request.getParameter("txtEmailAddress");
		String phone = request.getParameter("txtPhoneNumber");
		byte permis = Utilities.getByteParam(request, "selUserPermis");
		
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");

		if (UtilitiesText.isValidValue(userName) &&
				UtilitiesText.isValidPass(password, confirmPassword) &&
				UtilitiesText.isValidValue(email) &&
				UtilitiesText.isValidValue(phone) &&
				permis > 0) {
			String fullName = request.getParameter("txtFullName");

			UserObject newUser = new UserObject();
			newUser.setUser_name(userName);
			newUser.setUser_pass(password);
			newUser.setUser_fullname(Utilities.encode(fullName));
			//System.out.println(Utilities.encode(fullName));
			newUser.setUser_email(email);
			newUser.setUser_parent_id(parentUser.getUser_id());
			newUser.setUser_homephone(phone);
			newUser.setUser_created_date(UtilitiesDate.getDate());
			newUser.setUser_permission(permis);
			
			// tìm bộ quản lý kết nối 
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			UserControl uc = new UserControl(cp);

			if (cp == null) {
				getServletContext().setAttribute("CPool", cp);
			}

			// thực hiện thêm mới

			boolean result = uc.addUser(newUser);
			//trả về kết nối
			uc.releaseConnection();
			// báo kêt qỉa
			if (result) {
				response.sendRedirect("/adv/user/list");
			} else {
				response.sendRedirect("/adv/user/list?err=addvalue");
			}

		} else {
			response.sendRedirect("/adv/user/list?err=add");
		}

	}

}
