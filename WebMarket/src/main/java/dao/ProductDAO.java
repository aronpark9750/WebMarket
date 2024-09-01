package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import db.DB;
import dto.ProductDTO;

public class ProductDAO {
	private ProductDAO() {}
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	
	/**
	 * 데이터베이스에서 상품 목록 가져오기
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<ProductDTO> getAllProduct() throws SQLException{
		String listSql = "select * from productList";
		ArrayList<ProductDTO> productlist = new ArrayList<ProductDTO>();

		try(Connection connection = DB.getConnection();
			PreparedStatement statement = connection.prepareStatement(listSql);	
			ResultSet resultSet = statement.executeQuery()){
			
			while(resultSet.next()) {
				String productId = resultSet.getString("productid");
				String productName = resultSet.getString("productname");
				int productPrice = resultSet.getInt("productprice");
				String productInfo = resultSet.getString("productinfo");
				String productCompany = resultSet.getString("productcompany");
				String productTag = resultSet.getString("producttag");
				int productStock = resultSet.getInt("productstock");
				
				ProductDTO item = new ProductDTO(productId, productName, productPrice, productInfo, productCompany, productTag, productStock);
				productlist.add(item);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return productlist;
	}
	/**
	 * 데이터베이스에 상품 추가 하는 코드
	 * @param product
	 * @throws SQLException
	 */
	public void createProduct(ProductDTO product, HttpServletRequest request) throws SQLException {
		String createSql = "insert into productList (productid, productname, productprice, productinfo, productcompany, producttag, productstock) values (?, ?, ?, ?, ?, ?, ?)";
		HttpSession session = request.getSession();
		try(Connection connection = DB.getConnection();
			PreparedStatement statement = connection.prepareStatement(createSql)){
				statement.setString(1, product.getProductId());
				statement.setString(2, product.getProductName());
				statement.setInt(3, product.getProductPrice());
				statement.setString(4, product.getProductInfo());
				statement.setString(5, product.getProductCompany());
				statement.setString(6, product.getProductTag());
				statement.setInt(7, product.getProductStock());
				
				statement.executeUpdate();
				session.setAttribute("message", "상품이 등록되었습니다.");
		}catch (SQLException e) {
			e.printStackTrace();
			session.setAttribute("message", "상품을 등록하는데 실패하였습니다.");
		}
	}
	/**
	 * 상품 특정해서 가져오는 코드
	 * @param productId
	 * @return
	 * @throws SQLException
	 */
	public ProductDTO readProduct(String productId) throws SQLException {
		String readSql = "select * from productList where productid = ?";
		ProductDTO product = null;
		
		try(Connection connection = DB.getConnection();
			PreparedStatement statement = connection.prepareStatement(readSql)){
			
			statement.setString(1, productId);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) {
					String productName = resultSet.getString("productname");
					int productPrice = resultSet.getInt("productprice");
					String productInfo = resultSet.getString("productinfo");
					String productCompany = resultSet.getString("productcompany");
					String productTag = resultSet.getString("producttag");
					int productStock = resultSet.getInt("productstock");
					
					product = new ProductDTO(productId, productName, productPrice, productInfo, productCompany, productTag, productStock);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return product;
	}
	
	/**
	 * 상품 정보를 수정하는 코드
	 * @param product
	 * @throws SQLException
	 */
	public void updateProduct(ProductDTO product, HttpServletRequest request) throws SQLException {
		String updateSql = "update productList set productname = ?, productprice = ?, productinfo = ?, productcompany = ?, producttag = ?, productstock = ? where productid = ?";
		HttpSession session = request.getSession();
		try(Connection connection = DB.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateSql)) {
			
			statement.setString(1, product.getProductName());
			statement.setInt(2, product.getProductPrice());
			statement.setString(3, product.getProductInfo());
			statement.setString(4, product.getProductCompany());
			statement.setString(5, product.getProductTag());
			statement.setInt(6, product.getProductStock());
			statement.setString(7, product.getProductId());
			
			statement.executeUpdate();
			session.setAttribute("message", "상품이 수정되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", "상품을 수정하는데 오류가 발생하였습니다.");
		}
	}
	
	/**
	 * 특정 상품을 데이터베이스에서 삭제하는 코드
	 * @param productId
	 * @throws SQLException
	 */
	public void removeProduct(String productId, HttpServletRequest request) throws SQLException {
		String deleteSql = "delete from productList where productid = ?";
		HttpSession session = request.getSession();
		try(Connection connection = DB.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteSql)){
				
				statement.setString(1, productId);
				statement.executeUpdate();
				
				session.setAttribute("message", "상품을 삭제하였습니다.");
		}catch (SQLException e) {
			e.printStackTrace();
			session.setAttribute("message", "상품을 삭제하는데 오류가 발생하였습니다.");
		}
	}
	/**
	 * 결제한 후 재고수 변화 관련 코드 
	 * @param ProductId
	 * @param Quantity
	 * @throws SQLException
	 */
	public void updateStock(String ProductId, int Quantity) throws SQLException {
		String stockSql = "update productList set productstock = productstock - ? where productid = ?";
		
		try(Connection connection = DB.getConnection();
			PreparedStatement statement = connection.prepareStatement(stockSql)){
				
				statement.setInt(1, Quantity);
				statement.setString(2, ProductId);
				
				statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
