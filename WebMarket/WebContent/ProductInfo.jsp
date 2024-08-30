<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dto.ProductDTO"%>
<%@page import="dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><link rel="stylesheet" href="./resource/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>상품 상세정보</title>
</head>
<body>
	<jsp:include page="Nav.jsp"/>
	<div class="jumbotron">
		<h1>상세 정보</h1>
	</div>
	<%
		String id = request.getParameter("productId");
		ProductDAO instance = ProductDAO.getInstance();
		ProductDTO product = instance.readProduct(id);
		session.setAttribute("product", product);
	%>
	<form action="Product" method="post">
		<div class="container">	
        	<div class="row">     	
		   		<%
		   			if(product != null){
		   		%>
			   	  <div class="col-md-6">
				      <div align="right">
				   		   <a href="ProductUpdate.jsp?productId=<%=product.getProductId() %>" class="btn btn-primary">상품 수정&raquo;</a>	
				   	  </div>
			   		
			    	   <h1><%=product.getProductName() %></h1><br>
	                   <p><%= product.getProductInfo() %></p>
			    	   <p><strong>상품 코드 : </strong><%=product.getProductId() %></p>
	                   <p><strong>제조사:</strong> <%= product.getProductCompany() %></p>
	 				   <p><strong>분류:</strong> <%= product.getProductTag() %></p>
	 				   <p><strong>주문 수량: </strong><input type="number" name="Quantity" value="1" min="1" max="<%=product.getProductStock() %>"></p>
	 				   <p><strong>재고:</strong> <%= product.getProductStock() %></p>
			           <h2><%= product.getProductPrice() %> 원</h2>
			        	
					   <div><br>
					   		<input type="hidden" name="action" value="order">
					   		<input type="hidden" name="productId" value="<%=product.getProductId() %>">
					   		<input type="submit" class="btn btn-success" value="상품 주문&raquo;">&nbsp;&nbsp;
					   		<a href="ProductList.jsp" class="btn btn-secondary">상품 목록&raquo;</a>
					   </div> 
			      </div>
        		<%
        			session.setAttribute("product", product);
		   			} else {
        		%>
			        	<p>상품이 존재하지 않습니다.<p>
			        	<a href="ProductADD.jsp" class="btn btn-secondary">상품 추가&raquo;</a>	
        		<%
		   			}
        		%>
        	</div>
		</div>
	</form>
</body>
</html>
