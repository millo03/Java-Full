package jsoft.ads.user;

import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.objects.UserObject;

public class UserControl {

	private UserModel um;

	public UserControl(ConnectionPool cp) {
		this.um = new UserModel(cp);
	}

	public ConnectionPool getCP() {
		return this.um.getCP();
	}

	public void releaseConnection() {
		this.um.releaseConnection();
	}

	//******************************************************************************************************
	public boolean addUser(UserObject item) {
		return this.um.addUser(item);
	}

	public boolean editUser(UserObject item, USER_EDIT_TYPE edt) {
		return this.um.editUser(item, edt);
	}

	public boolean delUser(UserObject item) {
		return this.um.delUser(item);
	}
	//******************************************************************************************************
	public UserObject getUserObject(String username, String userpass) {
		return this.um.getUserObject(username, userpass);
	}
	
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}

	//******************************************************************************************************
	public ArrayList<String> viewUser(Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors) {
		// Lấy dữ liệu
		UserObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		USER_SORT_TYPE ust = infors.getValue3();

		ArrayList<UserObject> datas = this.um.getUserObjects(similar, page, total, ust);

		return UserLibrary.viewUser(datas, similar);
	}

	public static void main(String[] args) {
		// ko dua vao thi basic se tu tao
		UserControl uc = new UserControl(null);

		Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors = new Quartet<>(null, (short) 1, (byte) 10, null);

		ArrayList<String> view = uc.viewUser(infors);

		uc.releaseConnection(); // trả về kết nối ngay sau khi lấy được dữ liệu

		view.forEach(item -> {
			System.out.print(item);
		});
	}
}
