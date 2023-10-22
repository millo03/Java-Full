function displayValidStatus(isValid, message, target) {
    target.style.marginTop = "5px"
    if (isValid) {
        target.innerHTML = '<i class="fa-solid fa-circle-check"></i>&nbsp;' + message;
        target.style.color = "green";
    } else {
        target.innerHTML = '<i class="fa-solid fa-circle-exclamation"></i>&nbsp;' + message;
        target.style.color = "red";
    }
}

function checkValidUsername() {
    const username = document.getElementById('username').value;
    const VALID_USERNAME_STATUS = document.getElementById('valid-username-status');
    const MIN_LENGTH = 5;
    const SPACE_CHAR = ' ';
    const EMAIL_REGEX = /^.*(?=.{5}).*@\w+[.]\w+.*$/;
    let message = "Tên đăng nhập hợp lệ";
    let isValid = true;

    if (username.length == 0) {
        message = "Tên đăng nhập không được để trống";
        isValid = false;
    } else if (username.includes(SPACE_CHAR)) {
        message = "Tên đăng nhập không được chứa dấu cách";
        isValid = false;
    } else if (username.length < MIN_LENGTH) {
        message = "Tên đăng nhập phải tối thiểu " + MIN_LENGTH + " ký tự";
        isValid = false;
    } //else if (!username.match(EMAIL_REGEX)) {
      // message = "Tên đăng nhập không hợp quy cách";
      //  isValid = false;
    //}

    displayValidStatus(isValid, message, VALID_USERNAME_STATUS);

    return isValid;
}

function checkValidUserpass() {
    const userpass = document.getElementById('userpass').value;
    const VALID_USERPASS_STATUS = document.getElementById('valid-userpass-status');
    const MIN_LENGTH = 8;
    const SPACE_CHAR = ' ';
    const UPPER_CASE_REGEX = /[A-Z]/;
    const LOWER_CASE_REGEX = /[a-z]/;
    const NUMBER_REGEX = /[0-9]/;
    const SPEC_CHAR_REGEX = /[!@#$%^&*.]/;
    let message = "Mật khẩu hợp lệ";
    let isValid = true;

    if (userpass.length == 0) {
        message = "Mật khẩu không được để trống";
        isValid = false;
    } else if (userpass.includes(SPACE_CHAR)) {
        message = "Mật khẩu không được chứa dấu cách";
        isValid = false;
    } else if (userpass.length < MIN_LENGTH) {
        message = "Mật khẩu phải tối thiểu " + MIN_LENGTH + " ký tự";
        isValid = false;
    } else if (!userpass.match(LOWER_CASE_REGEX)) {
        message = "Mật khẩu phải chứa ít nhất 1 ký tự in thường";
        isValid = false;
    } else if (!userpass.match(UPPER_CASE_REGEX)) {
        message = "Mật khẩu phải chứa ít nhất 1 ký tự in hoa";
        isValid = false;
    } else if (!userpass.match(NUMBER_REGEX)) {
        message = "Mật khẩu phải chứa ít nhất 1 chữ số";
        isValid = false;
    } else if (!userpass.match(SPEC_CHAR_REGEX)) {
        message = "Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt";
        isValid = false;
    }

    displayValidStatus(isValid, message, VALID_USERPASS_STATUS);

    return isValid;
}
