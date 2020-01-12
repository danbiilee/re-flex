package mypage.model.vo;

public class Wishlist {
	private int wishNo;
	private String memberId;
	private int itemNo;
	private String rentOptNo;
	
	public Wishlist() {
		super();
	}

	public Wishlist(int wishNo, String memberId, int itemNo, String rentOptNo) {
		super();
		this.wishNo = wishNo;
		this.memberId = memberId;
		this.itemNo = itemNo;
		this.rentOptNo = rentOptNo;
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

	public int getWishNo() {
		return wishNo;
	}

	public void setWishNo(int wishNo) {
		this.wishNo = wishNo;
	}
	
	public String getRentOptNo() {
		return rentOptNo;
	}

	public void setRentOptNo(String rentOptNo) {
		this.rentOptNo = rentOptNo;
	}

	@Override
	public String toString() {
		return "Wishlist [wishNo=" + wishNo + ", memberId=" + memberId + ", itemNo=" + itemNo + ", rentOptNo="
				+ rentOptNo + "]";
	}

}
