package dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {
	private String productId;
	private String productName;
	private Integer productPrice;
	private String productInfo;
	private String productCompany;
	private String productTag;
	private Integer productStock;
	
	public ProductDTO() {}

	public ProductDTO(String productId, String productName, int productPrice, String productInfo, 
            String productCompany, String productTag, int productStock) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productInfo = productInfo;
		this.productCompany = productCompany;
		this.productTag = productTag;
		this.productStock = productStock;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getProductCompany() {
		return productCompany;
	}

	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}

	public String getProductTag() {
		return productTag;
	}

	public void setProductTag(String productTag) {
		this.productTag = productTag;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	
	
}
