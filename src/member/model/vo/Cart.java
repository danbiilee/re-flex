package member.model.vo;

import java.io.Serializable;

import item.model.vo.Item;

public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int cartNo;
	private String memberId;
	private Item item;
	private String rentOptNo;
	private int itemQuantity;
	private int priceByRentOptNo;
	
	public Cart() {
		super();
	}

	public Cart(int cartNo, String memberId, Item item, String rentOptNo, int itemQuantity, int priceByRentOptNo) {
		super();
		this.cartNo = cartNo;
		this.memberId = memberId;
		this.item = item;
		this.rentOptNo = rentOptNo;
		this.itemQuantity = itemQuantity;
		this.priceByRentOptNo = priceByRentOptNo;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Cart2 [cartNo=" + cartNo + ", memberId=" + memberId + ", item=" + item + ", rentOptNo=" + rentOptNo
				+ ", itemQuantity=" + itemQuantity + ", priceByRentOptNo=" + priceByRentOptNo + "]";
	}

}