package rent.model.vo;

import java.io.Serializable;
import java.sql.Date;

import item.model.vo.Item;

public class rent extends Item implements Serializable {

	private int itemEachNo;
	private int itemNo;
	private char itemRentYN;
	private Date itemRentStart;
	private Date itemRentEnd;
	private String rentOptNo;
	private String itemRentUser;
	private String categoryNo;
	
	public rent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public rent(int itemEachNo, int itemNo, char itemRentYN, Date itemRentStart, Date itemRentEnd, String rentOptNo,
			String itemRentUser, String categoryNo) {
		super();
		this.itemEachNo = itemEachNo;
		this.itemNo = itemNo;
		this.itemRentYN = itemRentYN;
		this.itemRentStart = itemRentStart;
		this.itemRentEnd = itemRentEnd;
		this.rentOptNo = rentOptNo;
		this.itemRentUser = itemRentUser;
		this.categoryNo = categoryNo;
	}

	public int getItemEachNo() {
		return itemEachNo;
	}
	public void setItemEachNo(int itemEachNo) {
		this.itemEachNo = itemEachNo;
	}
	public char getItemRentYN() {
		return itemRentYN;
	}
	public void setItemRentYN(char itemRentYN) {
		this.itemRentYN = itemRentYN;
	}
	public Date getItemRentStart() {
		return itemRentStart;
	}
	public void setItemRentStart(Date itemRentStart) {
		this.itemRentStart = itemRentStart;
	}
	public Date getItemRentEnd() {
		return itemRentEnd;
	}
	public void setItemRentEnd(Date itemRentEnd) {
		this.itemRentEnd = itemRentEnd;
	}
	public String getRentOptNo() {
		return rentOptNo;
	}
	public void setRentOptNo(String rentOptNo) {
		this.rentOptNo = rentOptNo;
	}
	public String getItemRentUser() {
		return itemRentUser;
	}
	public void setItemRentUser(String itemRentUser) {
		this.itemRentUser = itemRentUser;
	}
	
	
	@Override
	public int getItemNo() {
		// TODO Auto-generated method stub
		return super.getItemNo();
	}
	@Override
	public void setItemNo(int itemNo) {
		// TODO Auto-generated method stub
		super.setItemNo(itemNo);
	}
	@Override
	public String getCategoryNo() {
		// TODO Auto-generated method stub
		return super.getCategoryNo();
	}
	@Override
	public void setCategoryNo(String categoryNo) {
		// TODO Auto-generated method stub
		super.setCategoryNo(categoryNo);
	}
	@Override
	public int getItemStock() {
		// TODO Auto-generated method stub
		return super.getItemStock();
	}
	@Override
	public void setItemStock(int itemStock) {
		// TODO Auto-generated method stub
		super.setItemStock(itemStock);
	}
	@Override
	public String getItemBrand() {
		// TODO Auto-generated method stub
		return super.getItemBrand();
	}
	@Override
	public void setItemBrand(String itemBrand) {
		// TODO Auto-generated method stub
		super.setItemBrand(itemBrand);
	}
	@Override
	public String getItemName() {
		// TODO Auto-generated method stub
		return super.getItemName();
	}
	@Override
	public void setItemName(String itemName) {
		// TODO Auto-generated method stub
		super.setItemName(itemName);
	}
	@Override
	public int getItemPrice() {
		// TODO Auto-generated method stub
		return super.getItemPrice();
	}
	@Override
	public void setItemPrice(int itemPrice) {
		// TODO Auto-generated method stub
		super.setItemPrice(itemPrice);
	}
	@Override
	public String getItemDesc() {
		// TODO Auto-generated method stub
		return super.getItemDesc();
	}
	@Override
	public void setItemDesc(String itemDesc) {
		// TODO Auto-generated method stub
		super.setItemDesc(itemDesc);
	}
	@Override
	public Date getItemEnrollDate() {
		// TODO Auto-generated method stub
		return super.getItemEnrollDate();
	}
	@Override
	public void setItemEnrollDate(Date itemEnrollDate) {
		// TODO Auto-generated method stub
		super.setItemEnrollDate(itemEnrollDate);
	}
	
	
	public rent(int itemEachNo, int itemNo, char itemRentYN, Date itemRentStart, Date itemRentEnd, String rentOptNo,
			String itemRentUser) {
		super();
		this.itemEachNo = itemEachNo;
		this.itemNo = itemNo;
		this.itemRentYN = itemRentYN;
		this.itemRentStart = itemRentStart;
		this.itemRentEnd = itemRentEnd;
		this.rentOptNo = rentOptNo;
		this.itemRentUser = itemRentUser;
	}

	@Override
	public String toString() {
		return "rent [itemEachNo=" + itemEachNo + ", itemNo=" + itemNo + ", itemRentYN=" + itemRentYN
				+ ", itemRentStart=" + itemRentStart + ", itemRentEnd=" + itemRentEnd + ", rentOptNo=" + rentOptNo
				+ ", itemRentUser=" + itemRentUser + "]";
	}
}
