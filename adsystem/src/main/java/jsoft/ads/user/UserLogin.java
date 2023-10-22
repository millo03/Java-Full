package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsoft.ConnectionPool;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/user/login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Thường được dùng để cung cấp một giao diện (GUI) (Cấu trúc HTML) <br>
	 * Được gọi trong 2 trường hợp: <br>
	 * - Thông qua URL / URI <br>
	 * - Thông qua sự kiện của Form (method = "get") (lộ hết thông số của form)
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *
	 * @param request  - lưu trữ các yêu cầu xử lý các dữ liệu được gửi lên bởi
	 *                 trình khách (Client)
	 * @param response - lưu trữ các phản hồi (đáp ứng) dữ liệu sẽ được trả về cho
	 *                 Client
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng thực hiện xuất nội dung
		PrintWriter out = response.getWriter();

		out.append("<!doctype html>");
		out.append("<html lang=\"en\">");
		out.append("<head>");
		out.append("	<meta charset=\"utf-8\">");
		out.append("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.append("	<title>Login</title>");
		out.append("	<link rel=\"stylesheet\" href=\"/adv/adcss/all.min.css\">");
		out.append("	<link rel=\"stylesheet\" href=\"/adv/adcss/bootstrap.min.css\">");
		out.append("	<link rel=\"stylesheet\" href=\"/adv/adcss/loginv4.css\">");
		out.append("	<script src=\"/adv/adjs/loginv5.js?1500\"></script>");
		out.append("</head>");
		out.append("<body>");
		out.append("	<div class=\"container-lg\">");

		// Tìm tham số báo lỗi nếu có
		String error = request.getParameter("err");
		
		if (error != null) {
			out.append("<div class=\"row\">");
			out.append("	<div class=\"col-lg-6 offset-lg-3 mt-5\">");
			out.append("		<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">");
			out.append("			<i class=\"fa-solid fa-triangle-exclamation\"></i>&nbsp;");

			switch (error) {
			case "param":
				out.append("Tham số lấy giá trị không chính xác!)");
				break;
			case "value":
				out.append("Không tồn tại giá trị cho tài khoản");
				break;
			case "notexists":
				out.append("Đăng nhập không thành công");
				break;
			default:
				out.append("Có lỗi trong quá trình đăng nhập!");
				break;
			}

			out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("</div>");
			out.append("</div>");
		}

		out.append("<div class=\"row my-5\">");
		out.append("<div class=\"col-lg-6 offset-lg-3 text-bg-light\">");
		out.append("<form method=\"post\" class=\"loginview mb-3\" action=\"\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-12 text-bg-primary py-3 text-center fw-bold text-uppercase mb-5\">");
		out.append("<i class=\"fa-solid fa-user\"></i> &nbsp;");
		out.append("Login");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"name\" class=\"col-sm-4 col-form-label text-end fw-bolder\">Username / Email</label>");
		out.append("<div class=\"col-sm-6\">");
		out.append("<input type=\"text\" class=\"form-control\" id=\"username\" name=\"txtName\" onKeyup=\"checkValidUsername()\">");
		out.append("<div id=\"valid-username-status\"></div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"pass\" class=\"col-sm-4 col-form-label text-end fw-bolder\">Password</label>");
		out.append("<div class=\"col-sm-6\">");
		out.append("<input type=\"password\" class=\"form-control\" id=\"userpass\" name=\"txtPass\" onKeyup=\"checkValidUserpass()\">");
		out.append("<div id=\"valid-userpass-status\"></div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-sm-8 offset-sm-4\">");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"chkSave\">");
		out.append("<label class=\"form-check-label\" for=\"chkSave\">");
		out.append("Save the account information?");
		out.append("</label>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-12 text-center\">");
		out.append("<a class=\"text-decoration-none\" href=\"#\"><i class=\"fa-solid fa-unlock-keyhole\"></i>&nbsp;Forget password?</a> &nbsp;&nbsp;|&nbsp;&nbsp;");
		out.append("<a class=\"text-decoration-none\" href=\"#\">");
		out.append("<i class=\"fa-solid fa-circle-question\"></i>&nbsp;Help!</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
		out.append("<a class=\"text-decoration-none\" href=\"#\"><i class=\"fa-solid fa-user-plus\"></i>&nbsp;Sign up!</a>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-12 text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\" id=\"sign-in-btn\">");
		out.append("								<i class=\"fa-solid fa-right-to-bracket\"></i>&nbsp;Sign in</button>");
		out.append("							<button type=\"submit\" class=\"btn btn-danger \">");
		out.append("								<i class=\"fa-solid fa-xmark\"></i>&nbsp;Cancel</button>");
		out.append("						</div>");
		out.append("					</div>");
		out.append("					<div class=\"row mb-3\">");
		out.append("						<div class=\"col-12 text-end\">");
		out.append("							<a class=\"text-decoration-none\" href=\"\">");
		out.append("								<i class=\"fa-solid fa-language\"></i>&nbsp;Vietnamese</a>");
		out.append("						</div>");
		out.append("					</div>");
		out.append("				</form>");
		out.append("			</div>");
		out.append("		</div>");
		out.append("	</div>");
		out.append("	<script src=\"/adv/adjs/bootstrap.bundle.min.js?1500\"></script>");
		out.append("</body>");
		out.append("</html>");
	}

	/**
	 * Thường được dùng để xử lý dữ liệu do doGet gửi cho<br>
	 * Được gọi trong sự kiện của Form (method = "post")<br>
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		response.setContentType(CONTENT_TYPE);

		// Lấy thông tin tài khoản
		String username = request.getParameter("txtName");
		String userpass = request.getParameter("txtPass");

		if (username != null && userpass != null) {
			username = username.trim();
			userpass = userpass.trim();

			if (!username.equalsIgnoreCase("") && !userpass.equalsIgnoreCase("")) {
				// Tham chiếu ngữ cảnh ứng dụng
				ServletContext application = getServletConfig().getServletContext();

				// Tìm bộ quản lý kết nối ở trong không gian ngữ cảnh
				ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");

				// Tạo đối tượng thực thi chức năng
				UserControl uc = new UserControl(cp);
				if (cp == null) {
					application.setAttribute("CPool", uc.getCP());
				}
				// Thực hiện đăng nhập
				UserObject user = uc.getUserObject(username, userpass);

				// Trả về kết nối
				uc.releaseConnection();
				if (user != null) {
					// Tham chiếu phiên làm việc (session)
					HttpSession session = request.getSession(true);

					// Đưa thông tin đăng nhập vào phiên
					session.setAttribute("userLogined", user);

					// Trở về giao diện chính
					response.sendRedirect("/adv/view");
				} else {
					response.sendRedirect("/adv/user/login?err=notexists");
				}
			} else {
				response.sendRedirect("/adv/user/login?err=value");
			}
		} else {
			response.sendRedirect("/adv/user/login?err=param");
		}
	}
}
