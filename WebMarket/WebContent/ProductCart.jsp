<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.CartDTO"%>
<%@page import="dto.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./resource/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
	<jsp:include page="Nav.jsp"/>
	<div class="jumbotron">
		<h1>장바구니</h1>
	</div>
	<%
		String message = (String)session.getAttribute("message");
		if(message != null){
	%>
		<script type="text/javascript">
			alert("<%=message %>");
		</script>
	<%
		session.removeAttribute("message");
		}
	%>
	<% 
		List<CartDTO> cart = (List<CartDTO>)session.getAttribute("cart");
		if(cart == null){
			cart = new ArrayList<CartDTO>();
		}
	%>
	<form action="Cart" method="post">	
		<table class="table table-striped table-bordered">
	        <thead class="thead-dark">
	            <tr>
	               <th>상품 이름</th>
	               <th>가격</th>
	               <th>수량</th>     
	               <th></th>           
	            </tr>
	        </thead>
	        
	        <tbody>
	            <% 
	            	int totalprice = 0;
	         		if(cart != null){
	          			for(CartDTO item : cart) {
	          				totalprice += item.getProductPrice() * item.getCartQuantity();
	           	%>
	              		
			              	<tr>
			              		<td><%=item.getProductName() %></td>
			               		<td><%=item.getProductPrice() %></td>
			               		<td><%=item.getCartQuantity() %></td>
			               		<td>
				               		<input type="hidden" name="action" value="delete">
				               		<input type="hidden" name="productId" value="<%=item.getProductId() %>">
				               		<input type="submit" name="action" class="btn btn-secondary" value="취소&raquo;">
			               		</td>
			               	</tr>
			         
		        <%
	          			}
	         		}
		        %>
	        </tbody>
	     </table>
	     </form>
	     <p>총 가격:<%=totalprice %> </p>
	     <form action="Cart" method="post">
		     <div class="form-group">
		     	<%if(cart.size() > 0){ %>
		     		<input type="hidden" name="action" value="payment"> 
			     	<input type="submit" name="action" value="결제 하기&raquo;" class="btn btn-success">
			     	<a href="ProductList.jsp" class="btn btn-primary">상품 추가&raquo;</a>
		     	<%} else{ %>
				<p>상품이 존재하지 않습니다.</p>
				<a href="ProductList.jsp" class="btn btn-primary">상품 추가&raquo;</a>
				 <% } %>    	
	    	 </div>
	     </form>
	 
</body>
</html>