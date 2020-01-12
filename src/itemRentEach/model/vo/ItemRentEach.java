package itemRentEach.model.vo;

import java.io.Serializable;
import java.sql.Date;

import item.model.vo.Item;

public class ItemRentEach  extends Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int itemEachNo;
	private int itemNo;
	private char itemRentYN;
	private Date itemRentStart;
	private Date itemRentEnd;
	private String rentOptNo;
	private String itemRentUser;
	
	public ItemRentEach() {}

	public ItemRentEach(int itemEachNo, int itemNo, char itemRentYN, Date itemRentStart, Date itemRentEnd,
			String rentOptNo, String itemRentUser) {
		super();
		this.itemEachNo = itemEachNo;
		this.itemNo = itemNo;
		this.itemRentYN = itemRentYN;
		this.itemRentStart = itemRentStart;
		this.itemRentEnd = itemRentEnd;
		this.rentOptNo = rentOptNo;
		this.itemRentUser = itemRentUser;
	}

	public int getItemEachNo() {
		return itemEachNo;
	}

	public void setItemEachNo(int itemEachNo) {
		this.itemEachNo = itemEachNo;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
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
	public String toString() {
		return "ItemRentEach [itemEachNo=" + itemEachNo + ", itemNo=" + itemNo + ", itemRentYN=" + itemRentYN
				+ ", itemRentStart=" + itemRentStart + ", itemRentEnd=" + itemRentEnd + ", rentOptNo=" + rentOptNo
				+ ", itemRentUser=" + itemRentUser + "]";
	}
	
	
}
