package item.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int itemNo;
	private String categoryNo;
	private int itemStock;
	private String itemBrand;
	private String itemName;
	private int itemPrice;
	private String itemDesc;
	private Date itemEnrollDate;
	
	public Item() {
		super();
	}

	public Item(int itemNo, String categoryNo, int itemStock, String itemBrand, String itemName, int itemPrice,
			String itemDesc, Date itemEnrollDate) {
		super();
		this.itemNo = itemNo;
		this.categoryNo = categoryNo;
		this.itemStock = itemStock;
		this.itemBrand = itemBrand;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemDesc = itemDesc;
		this.itemEnrollDate = itemEnrollDate;
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

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Date getItemEnrollDate() {
		return itemEnrollDate;
	}

	public void setItemEnrollDate(Date itemEnrollDate) {
		this.itemEnrollDate = itemEnrollDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", categoryNo=" + categoryNo + ", itemStock=" + itemStock + ", itemBrand="
				+ itemBrand + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemDesc=" + itemDesc
				+ ", itemEnrollDate=" + itemEnrollDate + "]";
	}
	
}
