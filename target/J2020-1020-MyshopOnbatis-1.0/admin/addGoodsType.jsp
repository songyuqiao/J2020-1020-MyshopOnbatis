<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>添加商品种类</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>

	<script src="../js/jquery.min.js"></script>
	<script src="../js/jquery.serializejson.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>


    <script>
        $(function () {
			$.post({
				url:"../category?action=queryByLevel",
				data:{level : 1},
				dataType:"json",
				success:function (data) {
					for(var category of data){
						$("#firstCategroy").append($("<option value='" + category.id +"'>"+category.name+"</option>"));
					}
				}
			});
			$("#firstCategroy").change(function () {
				var val = $("#firstCategroy").val();
				if(val == 0){
					$("#secondCategory").hide();
					$("#thirdCategory").hide();
				}else{
					$("thirdCategory").hide();

					$.post({
						url : "../category?action=queryByParent",
						data:{parent : val},
						dataType: "json",
						success : function (data) {
							$("#secondCategory").show();
							$("#secondCategory").empty();

							$("#secondCategory").append("<option value='0'>--请选择--</option>");

							for(var category of data){
								$("#secondCategory").append($("<option value='"+category.id + "'>"+category.name+"</option>"));
							}
						},
                        error : function () {
						    console.log();
                        }
					})
				}
			});
			$("#addCategory").submit(function () {
				var parentId;
				var level ;
				if($("#firstCategroy").val() == 0){
                    parentId = 0;
					level = 1;
				}else if($("#secondCategory").val() == 0){
                    parentId = $("#firstCategroy").val();
					level = 2;
				}else if($("#thirdCategory").val() == 0) {
                    parentId = $("#secondCategory").val();
					level = 3;
				}else{
				    parentId = $("#thirdCategory").val();
				    level = 4;
                }

				$.post({
					url : "../category?action=add",
					data: {
                        parent: parentId,
                        name:$("#categoryName").val(),
                        level: level
					},
					success : function (){
						location.reload();
					},
                    error: function() {
					    console.log();
                    }
				});
				return false;
			});
        });
    </script>
</head>
<body>
<div style="width:98%;margin-left: 1%;">
    <div class="panel panel-default">
        <div class="panel-heading">
            添加商品种类
        </div>
        <div class="panel-body">
            <form id="addCategory" action="${pageContext.request.contextPath }/addGoodsType" method="post">
                <div class="row">
                    <div class="form-group form-inline">
                        <span>所属种类</span>
                        <select name="firstCategory" id="firstCategroy" categoryLevel="1" class="categorySelect">
                            <option value="0">--请选择--</option>
                        </select>
                        <select name="secondCategory" id="secondCategory" style="display: none;">
                            <option value="0">--请选择--</option>
                        </select>
                        <select name="thirdCategory" id="thirdCategory" style="display: none;">
                            <option value="0">--请选择--</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group form-inline">
                        <span>种类名称</span>
                        <input type="text" id="categoryName" name="typename" class="form-control">
                    </div>
                </div>
                <div class="row">
                    <div class="btn-group">
                        <button type="reset" class="btn btn-default">清空</button>
                        <button type="submit" class="btn btn-default">添加</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>