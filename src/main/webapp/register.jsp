<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="js/md5.js"></script>


    <script type="text/javascript">
        $(function () {
            $("#registerForm").validate({
                rules: {
                    username: {
                        required: true,
                        minlength: 3,
                        remote: "user?action=checkUsername"
                    },
                    password: {
                        required: true,
                        minlength: 3
                    },
                    confirm: {
                        required: true,
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true,
                        remote: "user?action=checkEmail"
                    },
                    gender: {
                        required: true
                    }
                },
                messages: {
                    username: {
                        required: "必填",
                        remote: "用户名已经存在"
                    },
                    password: {
                        required: "必填"
                    },
                    confirm: {
                        required: "必填"
                    },
                    email: {
                        required: "必填",
                        email: "格式错误",
                        remote: "邮箱已经存在"
                    },
                    gender: {
                        required: "必填"
                    }
                },
                submitHandler: function (element) {
                    var jQuery = $(element).serializeJSON();
                    jQuery.password = md5(jQuery.password);
                    delete jQuery.confirm;

                    $.post({
                        url: "user?action=register",
                        data: jQuery,
                        success: function () {
                            location.href = "registerSuccess.jsp"
                        }
                    });
                    return false;
                }
            });
        });
    </script>
    <title>注册</title>
</head>
<body>
<div class="regist">
    <div class="regist_center">
        <div class="regist_top">
            <div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;会员注册</div>
            <div class="right fr">
                <a href="index.jsp" target="_black">小米商城</a>
            </div>
            <div class="clear"></div>
            <div class="xian center"></div>
        </div>
        <div class="center-block" style="margin-top: 80px;">
            <form id="registerForm" class="form-horizontal" action="userRegister" method="post">

                <div class="form-group">
                    <label class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="text" id="username" name="username" class="form-control col-sm-10"
                               placeholder="Username"/>
                    </div>
                    <div class="col-sm-2">
                        <label for="username" class="error text-danger help-block" style="..."></label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input id="password" type="password" name="password" class="form-control col-sm-10"
                               placeholder="Password"/>
                    </div>
                    <div class="col-sm-2">
                        <label for="password" class="error text-danger help-block" style="...">请输入6位以上字符</label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input id="confirm" name="confirm" type="password" class="form-control col-sm-10"
                               placeholder="Password Again"/>
                    </div>
                    <div class="col-sm-2">
                        <label for="confirm" class="error text-danger help-block" style="...">两次密码要输入一致</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">邮箱</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input id="email" type="text" name="email" class="form-control col-sm-10" placeholder="Email"/>
                    </div>
                    <div class="col-sm-2">
                        <label for="email" class="error text-danger help-block" style="...">填写正确邮箱格式</label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别</label>
                    <div id="gender" class="col-sm-8" style="width: 40%">
                        <label class="radio-inline">
                            <input type="radio" name="gender" value="m"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="gender" value="f"> 女
                        </label>
                    </div>
                    <div class="col-sm-2">
                        <label for="gender" class="error text-danger help-block" style="display: none">你是帅哥 还是美女</label>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <div class="col-sm-7 col-sm-push-2">
                        <input id="registerBtn" type="submit" value="注册" class="btn btn-primary  btn-lg"
                               style="width: 200px;"/> &nbsp; &nbsp;
                        <input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"/>
                    </div>
                </div>
                <div>${session.registerMsg}</div>
            </form>

        </div>
    </div>
</div>

</body>
</html>