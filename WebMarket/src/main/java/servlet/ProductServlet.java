package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import dto.CartDTO;
import dto.ProductDTO;

@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDAO instance = ProductDAO.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		try {			
			switch(action) {
				//데이터베이스에 추가
				case "create" :
					createProduct(request, response);
					break;
				//데이터베이스 수정
				case "update" :
					updateProduct(request, response);
					break;
				//데이터베이스에서 삭제
				case "remove" :
					removeProduct(request, response);
					break;
				//장바구니
				case "order" :
					orderProduct(request, response);
					break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 상품 등록하는 코드
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException 
	 */
	private void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		String productInfo = request.getParameter("productInfo");
		String productCompany = request.getParameter("productCompany");
		String productTag = request.getParameter("productTag");
		int productStock = Integer.parseInt(request.getParameter("productStock"));
		
		ProductDTO product = new ProductDTO(productId, productName, productPrice, productInfo, productCompany, productTag, productStock);
		if(product != null) {
			instance.createProduct(product);
		}
		response.sendRedirect("ProductList.jsp");
	}
	/**
	 * 데이터베이스 상품 수정하는 코드
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		String productInfo = request.getParameter("productInfo");
		String productCompany = request.getParameter("productCompany");
		String productTag = request.getParameter("productTag");
		int productStock = Integer.parseInt(request.getParameter("productStock"));
		
		ProductDTO product = new ProductDTO(productId, productName, productPrice, productInfo, productCompany, productTag, productStock);
		if(product != null) {
			instance.updateProduct(product);
		}
		response.sendRedirect("ProductList.jsp");
	}
	/**
	 * 데이터베이스에서 상품 삭제하는 코드
	 * @param request
	 * @param response
	 */
	private void removeProduct(HttpServletRequest request, HttpServletResponse response) {
			String productId = request.getParameter("productId");
			
			try {
				instance.removeProduct(productId);
				
				response.sendRedirect("ProductList.jsp");
				
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 상품을 장바구니에 추가하는 코드
	 * @param request
	 * @param response
	 */
	private void orderProduct(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ArrayList<CartDTO> cart = (ArrayList<CartDTO>)session.getAttribute("cart");
		String productId = request.getParameter("productId");
		int quantity = Integer.parseInt(request.getParameter("Quantity"));
	
		try {
			ProductDTO product = instance.readProduct(productId);
			if(cart == null) {
				cart = new ArrayList<CartDTO>();
			}
	
			if(product != null) {	
				boolean productExists = false;
				for(CartDTO item : cart) {
					if(item.getProductId().equals(productId)) {
						item.setCartQuantity(item.getCartQuantity() + quantity);
						productExists = true;
						break;
					}
				}
				
				if(!productExists) {					
					CartDTO cartItem = new CartDTO(productId, product.getProductName(), product.getProductPrice(), quantity);
					cart.add(cartItem);
				}
				session.setAttribute("cart", cart);
				response.sendRedirect("ProductCart.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
