<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">

    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../js/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/md5.js"></script>

    <title>登录</title>
    <script type="text/javascript">
        $(function () {
            $("#adminLogin").validate({
				rules:{
					username:{
						required:true,
						minlength:4
					},
					password:{
						required:true,
						minlength: 4
					}
				},
				messages:{
					username:{
						required:"必填",
						minlength:"最少四位"
					},
					password:{
						required:"必填",
						minlength:"最少四位"
					}
				},
				submitHandler:function (){
					var formDate = $("#adminLogin").serializeJSON();
					formDate.password = md5(formDate.password);

					$.post({
						url:"/10_12_MyShop_war_exploded/other?action=login",
						data:formDate,
						success:function(data){
							if(data.success){
								location.href="admin.jsp";
							}else{
							    confirm(data.msg);
                            }
						}
					})
				}
			})
        })
    </script>
    <style type="text/css">
        #main {
            position: absolute;
            width: 400px;
            height: 400px;
            left: 50%;
            top: 40%;
            margin-left: -200px;
            margin-top: -100px;
        }
    </style>
</head>
<body>
<div id="main" class="panel panel-primary">
    <div class="panel-heading">
        <div class="panel-title">
            后台登录
        </div>
    </div>
    <div class="panel-body">
        <div style="text-align: center;">
            <img src="../image/mistore_logo.png" alt="logo" width="30%" height="30%"/>
        </div>
        <form action="${pageContext.request.contextPath }/adminLogin" method="post" id="adminLogin">
            <div class="form-group">
                <label>用户名:</label>
                <input type="text" name="username" id="" class="form-control" placeholder="请输入用户名"/>
            </div>
            <div class="form-group">
                <label>密&nbsp;&nbsp;&nbsp;码:</label>
                <div class="input-group">
                    <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码"/>
                    <span class="input-group-addon">
						<span class="glyphicon glyphicon-eye-open" id="eye"></span>
					</span>
                </div>
            </div>
            <div class="form-group" style="text-align: center;">
                <input type="submit" value="登录" class="btn btn-primary">
                <input type="reset" value="重置" class="btn btn-default">
            </div>
        </form>
    </div>
</div>
</body>
</html>