package jsoft.ads.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import jsoft.ConnectionPool;
import jsoft.library.*;
import jsoft.objects.*;

/**
 * Servlet implementation class UserDR
 */
@WebServlet("/user/dr")
public class UserDR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// tìm thông tin đăng nhập
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		int id = Utilities.getIntParam(request, "id");
		int pid = Utilities.getIntParam(request, "pid");// parent_id
		
		if(user != null && id>0) {
			if(user.getUser_id()!= id ) {
				if(user.getUser_permission()>= 4 || user.getUser_id() == pid) {
					
					ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
					UserObject dUser= new UserObject();
					UserControl uc = new UserControl(cp);
					dUser.setUser_id(id);
					dUser.setUser_parent_id(pid);
					dUser.setUser_last_modified(UtilitiesDate.getDate());
	
					// tìm tham số định xóa
					String trash = request.getParameter("t");
					
					String url = "/adv/user/list";
					boolean result;
					if(trash == null) {
						 result = uc.delUser(dUser);// xóa tuyết đối ko còn csdl
						 url+= "?trash";
					}else {
						// user_delete = 1 thì xóa tương đối 
						result = uc.editUser(dUser, USER_EDIT_TYPE.TRASH);
					}
					
					if(result) {
						response.sendRedirect(url);
					}else {
						response.sendRedirect(url+"?err=notok");
					}
				}else {
					response.sendRedirect("/adv/user/list?err=nopermis");
				}
			}else {
				response.sendRedirect("/adv/user/list?err=acclogin");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
