package mypage.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class WishlistItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int itemNo;
	private String categoryNo;
	private int itemStock;
	private String itemBrand;
	private String itemName;
	private int itemPrice;
	private String rentOptNo;
	
	public WishlistItem() {
		super();
	}

	public WishlistItem(int itemNo, String categoryNo, int itemStock, String itemBrand, String itemName, int itemPrice,
			String rentOptNo) {
		super();
		this.itemNo = itemNo;
		this.categoryNo = categoryNo;
		this.itemStock = itemStock;
		this.itemBrand = itemBrand;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.rentOptNo = rentOptNo;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public int getItemStock() {
		return itemStock;
	}

	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getRentOptNo() {
		return rentOptNo;
	}

	public void setRentOptNo(String rentOptNo) {
		this.rentOptNo = rentOptNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "WishlistItem [itemNo=" + itemNo + ", categoryNo=" + categoryNo + ", itemStock=" + itemStock
				+ ", itemBrand=" + itemBrand + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", rentOptNo="
				+ rentOptNo + "]";
	}

}
