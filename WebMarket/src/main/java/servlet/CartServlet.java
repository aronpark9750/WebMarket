package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import dto.CartDTO;

@WebServlet("/Cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDAO instance = ProductDAO.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");
		
		switch (action) {
			//장바구니 결제하기 
			case "payment" :
				paymentProduct(request, response);
				break;
			//장바구니 취소버튼
			case "delete" :
				deleteProduct(request, response);
				break;
		}
	}
	/**
	 * 장바구니 취소버튼 관련 코드
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		ArrayList<CartDTO> cart = (ArrayList<CartDTO>)session.getAttribute("cart");
		String productId = request.getParameter("productId");
		try {
			if(cart == null) {
				cart = new ArrayList<CartDTO>();
			}
			if(cart != null) {
				cart.removeIf(item -> item.getProductId().equals(productId));
				session.setAttribute("cart", cart);
				session.setAttribute("message", "상품을 취소하였습니다.");
			}
			response.sendRedirect("ProductCart.jsp");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 장바구니 결제하기 관련 코드
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void paymentProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String productId = request.getParameter("ProductId");
		ArrayList<CartDTO> cart = (ArrayList<CartDTO>)session.getAttribute("cart");
		try {
			
			if(cart == null) {
				cart = new ArrayList<CartDTO>();
			}
			if(cart != null) {
				for(CartDTO item : cart) {

					instance.updateStock(item.getProductId(), item.getCartQuantity());
				}
				session.removeAttribute("cart");
				session.setAttribute("message", "결제가 성공적으로 처리되었습니다.");
				response.sendRedirect("ProductList.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
