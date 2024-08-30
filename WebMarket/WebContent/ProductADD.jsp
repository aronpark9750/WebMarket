<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./resource/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>상품 등록</title>
</head>
<body>
	<jsp:include page="Nav.jsp"/>
	<div class="jumbotron">
		<h1>상품 등록</h1>
	</div>
	
	<div class="container">	
	  <form action="Product" method="post">
        <input type="hidden" name="action" value="create">
        
        <div class="form-group">
            <label for="productId">상품 코드 :</label>
            <input type="text" id="productId" name="productId" class="form-control" placeholder="상품의 코드를 입력해주세요." required="required">
        </div>
        
        <div class="form-group">
            <label for="productName">상품 이름 :</label>
            <input type="text" id="productName" name="productName" class="form-control" placeholder="상품 이름을 입력해주세요." required="required">
        </div>
        
        <div class="form-group">
            <label for="productPrice">상품 가격 :</label>
            <input type="number" id="productPrice" name="productPrice" class="form-control" placeholder="상품의 가격을 입력해주세요." required="required">
        </div>
        
        <div class="form-group">
            <label for="productInfo">상품 정보 :</label>
            <textarea rows="6" id="productInfo" name="productInfo" class="form-control" placeholder="상품의 정보를 입력해주세요."></textarea>
        </div>
        
        <div class="form-group">
            <label for="productCompany">제조사 :</label>
            <input type="text" id="productCompany" name="productCompany" class="form-control" placeholder="제조사를 입력해주세요." required="required">
        </div>
        
        <div class="form-group">
            <label for="productTag">분류 :</label>
            <select id="productTag" name="productTag" class="form-control">
                <option value="전기 제품">전기 제품</option>
                <option value="샤워 제품">샤워 제품</option>
                <option value="피부 제품">피부 제품</option>
                <option value="주방 제품">주방 제품</option>
                <option value="데코 제품">데코 제품</option>
                <option value="기타 제품">기타 제품</option>
            </select>
        </div>
        
        <div class="form-group">
            <label for="productStock">재고 수 :</label>
            <input type="number" id="productStock" name="productStock" class="form-control" placeholder="제품의 재고 수를 입력해주세요." required="required">
        </div>
        <div>        
	        <input type="submit" class="btn btn-primary" value="등록">
	        <a href="MainPage.jsp" class="btn btn-secondary">취소</a>
        </div>
   	 </form>
	</div>
</body>
</html>
