function checkValidUsername() {
    username = "";
    username = document.getElementById('username').value;
    const VALID_USERNAME_STATUS = document.getElementById('valid-username-status');
    isValidUsername = false;
    message = "";

    if (username.length == 0) {
        isValidUsername = false;
        message = "Tên đăng nhập không được để trống"
    } else {
        isValidUsername = true;
        const MIN_LENGTH = 8;
        if (username.length < MIN_LENGTH) {
            isValidUsername = false;
            message = "Tên đăng nhập ít hơn " + MIN_LENGTH + " ký tự";
        } else {
            isValidUsername = true;
            const SPACE_CHAR = " ";
            if (username.indexOf(SPACE_CHAR) != -1) {
                isValidUsername = false;
                message = "Tên đăng nhập không được chứa dấu cách";
            } else {
                isValidUsername = true;
                pattern = /^.*(?=.{5}).*@\w+[.]\w+.*$/
                if (!username.match(pattern)) {
                    isValidUsername = false;
                    message = "Tên đăng nhập không hợp quy cách";
                } else {
                    isValidUsername = true;
                }
            }
        }
    }

    VALID_USERNAME_STATUS.style.marginTop = "5px";
    if (isValidUsername) {
        VALID_USERNAME_STATUS.innerHTML = '<i class="fa-solid fa-circle-check"></i>&nbsp;' + "Tên đăng nhập hợp lệ";
        VALID_USERNAME_STATUS.style.color = "green";
    } else {
        VALID_USERNAME_STATUS.innerHTML = "<i class=\"fa-solid fa-circle-exclamation\"></i>&nbsp;" + message;
        VALID_USERNAME_STATUS.style.color = "red";
    }

    return isValidUsername;
}

function checkValidUserpass() {
    userpass = "";
    userpass = document.getElementById('userpass').value;
    const VALID_USERPASS_STATUS = document.getElementById('valid-userpass-status');
    message = "";
    isValidUserpass = false;
    if (userpass.length == 0) {
        isValidUserpass = false;
        message = "Mật khẩu không được để trống";
    } else {
        isValidUserpass = true;
        const MIN_LENGTH = 8;
        if (userpass.length < MIN_LENGTH) {
            isValidUserpass = false;
            message = "Mật khẩu ít nhất " + MIN_LENGTH + " ký tự";
        } else {
            isValidUserpass = true;
            const SPACE_CHAR = " ";
            if (userpass.indexOf(SPACE_CHAR) != -1) {
                isValidUserpass = false;
                message = "Mật khẩu không được chứa dấu cách";
            } else {
                isValidUsername = true;
                const UPPER_CASE_PATTERN = /^.*[A-Z]+.*$/;
                if (!userpass.match(UPPER_CASE_PATTERN)) {
                    isValidUserpass = false;
                    message = "Mật khẩu phải chứa ít nhất một ký tự in hoa";
                } else {
                    isValidUserpass = true;
                    const LOWER_CASE_PATTERN = /^.*[a-z]+.*$/
                    if (!userpass.match(LOWER_CASE_PATTERN)) {
                        isValidUserpass = false;
                        message = "Mật khẩu phải chứa ít nhất một ký tự in thường";
                    } else {
                        isValidUserpass = true;
                        const NUMBER_PATTERN = /^.*[0-9].*$/;
                        if (!userpass.match(NUMBER_PATTERN)) {
                            isValidUserpass = false;
                            message = "Mật khẩu phải chứa ít nhất một chữ số";
                        } else {
                            isValidUserpass = true;
                            const SPEC_CHAR_PATTERN = /^.*[!@#$&*].*$/;
                            if (!userpass.match(SPEC_CHAR_PATTERN)) {
                                isValidUserpass = false;
                                message = "Mật khẩu phải chứa ít nhất một ký tự đặc biệt";
                            } else {
                                isValidUserpass = true;
                            }
                        }
                    }
                }
            }
        }
    }

    VALID_USERPASS_STATUS.style.marginTop = "5px";
    if (isValidUserpass) {
        VALID_USERPASS_STATUS.innerHTML = '<i class="fa-solid fa-circle-check"></i>&nbsp;' + "Mật khẩu hợp lệ";
        VALID_USERPASS_STATUS.style.color = "green";
    } else {
        VALID_USERPASS_STATUS.innerHTML = '<i class="fa-solid fa-circle-exclamation"></i>&nbsp;' + message;
        VALID_USERPASS_STATUS.style.color = "red";
    }
    return isValidUserpass;
}




// ^.*                         : Start
// ?=                          : Lockaround the string
// ^.*(?=.{8,}) ... .*$        : At least 8 character in total 
// .*(?=[A-Z])                 : At least one upper case after 0 or many characters exclude line breaks (/n /r)
// .*(?=[a-z])                 : At least one lower case after 0 or many characters exclude line breaks
// .*(?=[0-9])                 : At least one number after 0 or many characters exclude line breaks
// .*(?=[!@#$ &*])             : At least one special character after 0 or many characters exclude line breaks
// .*$                         : End


















function checkValidLogin() {
    let username = "";
    let userpass = "";
    username = document.getElementById('name').value;
    userpass = document.getElementById('pass').value;

    let viewErrName = document.getElementById('errName');
    let viewErrPass = document.getElementById('errPass');

    console.log(username);
    console.log(userpass);

    var isValidName = true;
    var isValidPass = true;

    var message = "";

    if (username.length == 0) {
        isValidName = false;
        message = "Tên đăng nhập không được để trống";
    } else {
        isValidName = true;
        if (username.length < 5 || username.length > 50) {
            isValidName = false;
            message = "Tên đăng nhập quá ngắn hoặc quá dài";
        } else {
            isValidName = true;
            if (username.indexOf(" ") != -1) {
                isValidName = false;
                message = "Tên đăng nhập không được chứa dấu cách";
            } else {
                isValidName = true;
                if (username.indexOf("@") != -1) {
                    isValidName = true;
                    var pattern = /\w+@\w+[.]\w+/
                    if (!username.match(pattern)) {
                        isValidName = false;
                        message = "Tên đăng nhập không đúng quy cách";
                    } else {
                        isValidName = true;
                    }
                } else {
                    isValidName = false;
                }
            }
        }
    }

    if (isValidName) {
        viewErrName.innerHTML = "OK";
    } else {
        viewErrName.innerHTML = message;
    }

    if (userpass.length == 0) {
        isValidPass = false;
        message = "Mật khẩu không được để trống";
    } else {
        isValidName = true;
        if (userpass.length < 6) {
            isValidPass = false;
            message = "Mật khẩu quá ngắn";
        } else {
            isValidPass = true;
            if (userpass.indexOf(" ") != -1) {
                isValidPass = false;
                message = "Mật khẩu không được chứa dấu cách"
            } else {
                isValidPass = true;
                var pattern = /^.*(?=.{8,}).*(?=[A-Z]).*(?=[a-z]).*(?=[0-9]).*(?=[!@#$&*]).*$/
                // ^.*                         : Start
                // ?=                          : Lockaround the string
                // ^.*(?=.{8,}) ... .*$        : At least 8 character in total 
                // .*(?=[A-Z])                 : At least one upper case after any character exclude line breaks (/n /r)
                // .*(?=[a-z])                 : At least one lower case after any character exclude line breaks
                // .*(?=[0-9])                 : At least one number after any character exclude line breaks
                // .*(?=[!@#$ &*])             : At least one special character after any character exclude line breaks
                // .*$                         : End

                if (!userpass.match(pattern)) {
                    isValidPass = false;
                    message = "Mật khẩu không đúng quy cách";
                } else {
                    isValidPass = true;
                }
            }
        }
    }

    if (isValidPass) {
        viewErrPass.innerHTML = "OK";
    } else {
        viewErrPass.innerHTML = message;
    }



}