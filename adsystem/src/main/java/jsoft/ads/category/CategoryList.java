package jsoft.ads.category;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class CategoryList
 */
@WebServlet("/category/list")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if (user != null) {
			view(request, response, user);
		} else {
			response.sendRedirect("/adv/user/login");
		}
    }
	protected void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

		CategoryControl cc = new CategoryControl(cp);

		List<String> viewList = cc.viewCategory(null, (short) 1, (byte) 10);

		RequestDispatcher header = getServletContext().getRequestDispatcher("/header");

		if (header != null) {
			header.include(request, response);
		}

		out.append("    <main id=\"main\" class=\"main\">");

		out.append("        <div class=\"pagetitle d-flex\">");
		out.append("            <h1>Danh sách Section</h1>");
		out.append("            <nav class=\"ms-auto\">");
		out.append("                <ol class=\"breadcrumb\">");
		out.append("                    <li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("                    <li class=\"breadcrumb-item\">Section</li>");
		out.append("                    <li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("                </ol>");
		out.append("            </nav>");
		out.append("        </div><!-- End Page Title -->");

		out.append("        <section class=\"section\">");
		out.append("            <div class=\"row\">");

		out.append("                <div class=\"col-lg-12\">");
		out.append("                    <div class=\"card\">");
		out.append("                        <div class=\"card-body\">");

		out.append(viewList.get(0));

		out.append("                        </div>"); // card-body
		out.append("                    </div>"); // card
		out.append("                </div>"); // col-lg-12

		out.append("            </div>"); // row
		out.append("        </section>");

		out.append("    </main><!-- End #main -->");

		RequestDispatcher footer = getServletContext().getRequestDispatcher("/footer");
		if (footer != null) {
			footer.include(request, response);
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
