<%@page import="org.apache.catalina.ssi.SSICommand"%>
<%@page import="dao.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dto.ProductDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>    
<!DOCTYPE html>
<html>
<head><link rel="stylesheet" href="./resource/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>
	<jsp:include page="Nav.jsp"/>
	<div class="jumbotron">
		<h1>상품 목록</h1>
	</div>
	<%
	 	ProductDAO instance = ProductDAO.getInstance();
		List<ProductDTO> productlist = instance.getAllProduct();
	%>
	<div class="container">	
        <div class="row" align="center">
        	<%

        		if(productlist != null && !productlist.isEmpty()){
        			for(ProductDTO item : productlist){
     
        	%>
		        		<div class="col-md-4">
		        			<h3><%=item.getProductName() %></h3>
		        			<p><%=item.getProductInfo() %>    	
		        			<p><%=item.getProductPrice() %>원</p>
		        			
		        			<p><a href="ProductInfo.jsp?productId=<%=item.getProductId() %>" class="btn btn-secondary">상세 정보&raquo;</a></p>
		        		</div>
        	<%		
        			}
        		} else {
        	%>
        		<p>상품이 존재하지 않습니다.<p>
        		<a href="ProductADD.jsp" class="btn btn-secondary">상품 추가&raquo;</a>	
        	<%
        		}
        		session.setAttribute("productlist", productlist);
        	%>	
        </div>
	</div>

</body>
</html>
