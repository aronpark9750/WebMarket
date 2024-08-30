<%@page import="dto.ProductDTO"%>
<%@page import="dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head><link rel="stylesheet" href="./resource/css/bootstrap.min.css">
<title>상품 수정</title>
</head>
<body>
	<jsp:include page="Nav.jsp"/>
	<div class="jumbotron">
		<h1>상품 수정</h1>
	</div>
	<%
		
		ProductDTO product = (ProductDTO)session.getAttribute("product");
	%>
	<div class="container">	
       <form action="Product" method="post">
	        <div align ="right"class="form-group">
	        	<input type="hidden" name ="action" value="remove" >
	        	<input type="hidden" name ="productId" value="<%=product.getProductId() %>">
	        	<input type="submit" value = "삭제" class="btn btn-danger">
	        </div>
       </form>
	  <form action="Product" method="post">
        <div class="form-group">
            <label for="productId">상품 코드 :</label>
            <input type="text" id="productId" name="productId" class="form-control" value="<%=product.getProductId() %>" readonly>
        </div>
        
        <div class="form-group">
            <label for="productName">상품 이름 :</label>
            <input type="text" id="productName" name="productName" class="form-control" value="<%=product.getProductName() != null ? product.getProductName() : "" %>" placeholder="상품 이름을 입력해주세요." required="required">
        </div>
        
        <div class="form-group">
            <label for="productPrice">상품 가격 :</label>
            <input type="number" id="productPrice" name="productPrice" class="form-control" value="<%=product.getProductPrice() %>"  placeholder="상품의 가격을 입력해주세요." required="required">
        </div>
        
        <div class="form-group">
            <label for="productInfo">상품 정보 :</label>
            <textarea rows="6" id="productInfo" name="productInfo" class="form-control"><%=product.getProductInfo() != null ? product.getProductInfo() : "" %></textarea>
        </div>
        
        <div class="form-group">
            <label for="productCompany">제조사 :</label>
            <input type="text" id="productCompany" name="productCompany" class="form-control" value="<%=product.getProductCompany() != null ? product.getProductCompany() : "" %>" placeholder="제조사를 입력해주세요." required="required">
        </div>
        
        <div class="form-group">
            <label for="productTag">분류 :</label>
               <select id="productTag" name="productTag" class="form-control">
                   <option value="전기 제품" <%= "전기 제품".equals(product.getProductTag()) ? "selected" : "" %>>전기 제품</option>
                   <option value="샤워 제품" <%= "샤워 제품".equals(product.getProductTag()) ? "selected" : "" %>>샤워 제품</option>
                   <option value="피부 제품" <%= "피부 제품".equals(product.getProductTag()) ? "selected" : "" %>>피부 제품</option>
                   <option value="주방 제품" <%= "주방 제품".equals(product.getProductTag()) ? "selected" : "" %>>주방 제품</option>
                   <option value="데코 제품" <%= "데코 제품".equals(product.getProductTag()) ? "selected" : "" %>>데코 제품</option>
                   <option value="기타 제품" <%= "기타 제품".equals(product.getProductTag()) ? "selected" : "" %>>기타 제품</option>
               </select>
         </div>
        
        <div class="form-group">
            <label for="productStock">재고 수 :</label>
            <input type="number" id="productStock" name="productStock" class="form-control" value="<%=product.getProductStock() %>" placeholder="제품의 재고 수를 입력해주세요." required="required">
        </div>
        
        <input type="hidden" name="action" value="update">
        <input type="submit" class="btn btn-primary" value="수정">
        <a href="ProductList.jsp" class="btn btn-secondary">취소</a>
   	 </form>
	</div>

</body>
</html>
