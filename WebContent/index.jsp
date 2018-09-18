<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="${pageContext.request.contextPath }/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		//这里一定是json字符串
		var jsonString='{"id": 1,"name": "测试商品","price": 99.9,"detail": "测试商品描述","pic": "123456.jpg"}';
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/items/jsonToList.action",
			   data: jsonString,
				 contentType:"application/json",
				dataType:"json",
			   success: function(msg){
			     alert( msg.name );
			   }
			});
	});
</script>
</head>
<body>
</body>
</html>