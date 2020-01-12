package order.model.vo;

import java.io.Serializable;
import java.sql.Date;

import item.model.vo.Item;

public class OrderDetail3 extends Item implements Serializable{

	private int orderDetailNo;
	private int orderNo;
	private String memberId;
	private int itemNo;
	private String rentOptNo;
	private int orderQuantity;
	private String orderStatusNo;
	private char reviewYn;
	private String orderCancelNo;
	
	public OrderDetail3() {}

	public OrderDetail3(int itemNo, String categoryNo, int itemStock, String itemBrand, String itemName, int itemPrice,
			String itemDesc, Date itemEnrollDate, int orderDetailNo, int orderNo, String memberId, int itemNo2,
			String rentOptNo, int orderQuantity, String orderStatusNo, char reviewYn, String orderCancelNo) {
		super(itemNo, categoryNo, itemStock, itemBrand, itemName, itemPrice, itemDesc, itemEnrollDate);
		this.orderDetailNo = orderDetailNo;
		this.orderNo = orderNo;
		this.memberId = memberId;
		itemNo = itemNo2;
		this.rentOptNo = rentOptNo;
		this.orderQuantity = orderQuantity;
		this.orderStatusNo = orderStatusNo;
		this.reviewYn = reviewYn;
		this.orderCancelNo = orderCancelNo;
	}

	public int getOrderDetailNo() {
		return orderDetailNo;
	}

	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getRentOptNo() {
		return rentOptNo;
	}

	public void setRentOptNo(String rentOptNo) {
		this.rentOptNo = rentOptNo;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getOrderStatusNo() {
		return orderStatusNo;
	}

	public void setOrderStatusNo(String orderStatusNo) {
		this.orderStatusNo = orderStatusNo;
	}

	public char getReviewYn() {
		return reviewYn;
	}

	public void setReviewYn(char reviewYn) {
		this.reviewYn = reviewYn;
	}

	public String getOrderCancelNo() {
		return orderCancelNo;
	}

	public void setOrderCancelNo(String orderCancelNo) {
		this.orderCancelNo = orderCancelNo;
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

	@Override
	public String toString() {
		return "OrderDetail [orderDetailNo=" + orderDetailNo + ", orderNo=" + orderNo + ", memberId=" + memberId
				+ ", itemNo=" + itemNo + ", rentOptNo=" + rentOptNo + ", orderQuantity=" + orderQuantity
				+ ", orderStatusNo=" + orderStatusNo + ", reviewYn=" + reviewYn + ", orderCancelNo=" + orderCancelNo
				+ "]";
	}

	

	
}
