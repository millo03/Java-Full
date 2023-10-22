//loginv3.js - Các xử lí kịch bản cho loginv3.html

console.log(1);

function checkValidLogin() {
	//Tham chiếu lấy giá trị
	let name = document.getElementById("name").value;
	let pass = document.getElementById("pass").value;

	//Tham chiếu đối tượng hiển thị lỗi
	let viewErrName = document.getElementById("errName");
	let viewErrPass = document.getElementById("errPass");

	//Khai báo biến xác nhận hợp lệ
	var validName = true;
	var validPass = true;

	//Khai báo biến ghi nhận thông báo
	var message = "";

	// console.log(name);
	// console.log(message);
	//Kiểm tra name
	name = name.trim();
	console.log(name);
	if (name == "") {
		validName = false;
		message = "Thiếu tên / hộp thư đăng nhập cho tài khoản";
		console.log(message);
		console.log(2);
	} else {
		if (name.length < 5 || name.length > 50) {
			valid = false;
			message = "Tên đăng nhập/ hộp thư quá ngắn hoặc quá dài";
		} else {
			valid = true;
			if (name.indexOf(" ") != -1) {
				validName = false;
				message = "Tên đăng nhập/ hộp thư không có dấu cách";
			} else {
				if (name.indexOf("@") != -1) {
					var pattern = /\w+@+\w+[.]+\w/;
					if (!name.match(pattern)) {
						validName = false;
						message = "Không đúng cấu trúc hộp thư";
					}
				}
			}
		}
	}

	//Thông báo lỗi name
	viewErrName.style.padding = "8px";
	viewErrName.style.marginTop = "5px";
	if (validName) {
		viewErrName.innerHTML = '<i class="fa-solid fa-check"></i>';
		// viewErrName.style.display = "flex";
		viewErrName.style.backgroundColor = "transparent";
		viewErrName.style.color = "blue";
	} else {
		viewErrName.innerHTML = message;
		viewErrName.style.backgroundColor = "red";
		viewErrName.style.color = "yellow";
	}

	//Kiểm tra pass
	pass = pass.trim();
	if (pass == "") {
		validPass = false;
		message = "Thiếu mật khẩu để đăng nhập";
	} else {
		if (pass.length < 6) {
			validPass = false;
			message = "Mật khẩu không hợp lệ";
		} else {
			valid = true;
			if (pass.indexOf(" ") != -1) {
				validPass = false;
				message = "Mật khẩu có chứa dấu cách";
			} else {
				var pattern =
					/^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$/;
				if (!name.match(pattern)) {
					validPass = false;
					message = "Không đúng cấu trúc mật khẩu";
				}
			}
		}
	}

	//Thông báo lỗi pass
	viewErrPass.style.padding = "8px";
	viewErrPass.style.marginTop = "5px";
	if (validPass) {
		viewErrPass.innerHTML = '<i class="fa-solid fa-check"></i>';
		viewErrPass.style.backgroundColor = "transparent";
		viewErrPass.style.color = "blue";
	} else {
		viewErrPass.innerHTML = message;
		viewErrPass.style.backgroundColor = "red";
		viewErrPass.style.color = "yellow";
	}

	return validName && validPass;
}
