package order.model.vo;

import java.io.Serializable;

import item.model.vo.Item;

public class OrderDetail extends OrderSheet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int orderDetailNo;
	private Item item;
	private String rentOptNo;
	private int itemQuantity;
	private int priceByRentOptNo;
	private String orderStatusNo;
	private String reviewYn;
	private String orderCancelNo;
	
	
	public OrderDetail() {
		super();
	}

	public OrderDetail(int orderDetailNo, Item item, String rentOptNo, int itemQuantity, int priceByRentOptNo,
			String orderStatusNo, String reviewYn, String orderCancelNo) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.item = item;
		this.rentOptNo = rentOptNo;
		this.itemQuantity = itemQuantity;
		this.priceByRentOptNo = priceByRentOptNo;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getRentOptNo() {
		return rentOptNo;
	}

	public void setRentOptNo(String rentOptNo) {
		this.rentOptNo = rentOptNo;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public int getPriceByRentOptNo() {
		return priceByRentOptNo;
	}

	public void setPriceByRentOptNo(int itemPrice, int rentPeriod, double disRate) {
		this.priceByRentOptNo = (int)Math.ceil((itemPrice*disRate)/240*rentPeriod)/100*100;
	}

	public String getOrderStatusNo() {
		return orderStatusNo;
	}

	public void setOrderStatusNo(String orderStatusNo) {
		this.orderStatusNo = orderStatusNo;
	}

	public String getReviewYn() {
		return reviewYn;
	}

	public void setReviewYn(String reviewYn) {
		this.reviewYn = reviewYn;
	}

	public String getOrderCancelNo() {
		return orderCancelNo;
	}

	public void setOrderCancelNo(String orderCancelNo) {
		this.orderCancelNo = orderCancelNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailNo=" + orderDetailNo + ", item=" + item + ", rentOptNo=" + rentOptNo
				+ ", itemQuantity=" + itemQuantity + ", priceByRentOptNo=" + priceByRentOptNo + ", orderStatusNo="
				+ orderStatusNo + ", reviewYn=" + reviewYn + ", orderCancelNo=" + orderCancelNo + "]";
	}

	
}
