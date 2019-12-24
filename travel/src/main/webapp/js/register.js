/**注册js*/
function checkFrom() {
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
    $("#email").blur(checkEmail);
    $("#name").blur(checkRealname);
    $("#telephone").blur(checkPhone);
    $("#birthday").blur(checkBirthday);
    $("#check").blur(checkCodeFront);
    //表单提交校验
    $('#registerForm').submit(function () {
        if (checkUsername() && checkPassword() && checkEmail() && checkRealname() && checkPhone() && checkBirthday() /*&& checkCodeFront()*/) {
            /*serialize()序列化表单数据为key=value形式*/
            $.post("/registerUserServlet", $(this).serialize(), function (data) {
                if (data.flag){
                    location.href = "register_ok.html"
                }else {
                    $("#errMsg").html(data.errorMsg)
                }
            })
        } else {
            alert("红框选项填写有误")
        }
        return false
    });

}

function checkUsername() {
    var username = $("#username").val();
    var reg_username = /^\w{6,12}$/;
    var flag = reg_username.test(username);
    if (flag) {
        $("#username").css("border", "");
    } else {
        $("#username").css("border", "2px solid red");
    }
    return flag
}

function checkPassword() {
    var password = $("#password").val();
    var reg_password = /^\w{8,20}$/;
    var flag = reg_password.test(password);
    if (flag) {
        $("#password").css("border", "");
    } else {
        $("#password").css("border", "2px solid red");
    }
    return flag
}

function checkEmail() {
    var email = $("#email").val();
    var reg_email = /^\w+@\w+\.\w+$/;
    var flag = reg_email.test(email);
    if (flag) {
        $("#email").css("border", "");
    } else {
        $("#email").css("border", "2px solid red");
    }
    return flag
}

function checkRealname() {
    var flag = false;
    var name = $("#name").val();

    if (name == '' || name == null) {
        $("#name").css("border", "2px solid red");

    } else {
        $("#name").css("border", "");
        flag = true
    }
    return flag
}

function checkPhone() {
    var telephone = $("#telephone").val();
    var reg_telephone = /^1(3|4|5|6|7|8)\d{9}$/;
    var flag = reg_telephone.test(telephone);
    if (flag) {
        $("#telephone").css("border", "");
    } else {
        $("#telephone").css("border", "2px solid red");
    }
    return flag
}

function checkBirthday() {
    var birthday = $("#birthday").val();
    var flag = false;
    if (birthday == '' || birthday == null) {
        $("#birthday").css("border", "2px solid red");

    } else {
        $("#birthday").css("border", "");
        flag = true
    }
    return flag
}

function checkCodeFront() {
    var check = $("#check").val();
    var flag = false;
    if (check == '' || check == null) {
        $("#check").css("border", "2px solid red");
    } else {
        $("#check").css("border", "");
        flag = true
    }
    return flag
}