package order.model.vo;

public class OrderDetail2 extends OrderSheet {
	private static final long serialVersionUID = 1L;

	private int orderDetailsNo;
	private String orderNo;
	private String memberId;
	private int itemNo;
	private String rentOptNo;
	private int orderQuantity;
	private String orderStatusNo;
	private char reviewYn;
	private String orderCancelNo;
	public OrderDetail2() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderDetail2(int orderDetailsNo, String orderNo, String memberId, int itemNo, String rentOptNo,
			int orderQuantity, String orderStatusNo, char reviewYn, String orderCancelNo) {
		super();
		this.orderDetailsNo = orderDetailsNo;
		this.orderNo = orderNo;
		this.memberId = memberId;
		this.itemNo = itemNo;
		this.rentOptNo = rentOptNo;
		this.orderQuantity = orderQuantity;
		this.orderStatusNo = orderStatusNo;
		this.reviewYn = reviewYn;
		this.orderCancelNo = orderCancelNo;
	}
	public int getOrderDetailsNo() {
		return orderDetailsNo;
	}
	public void setOrderDetailsNo(int orderDetailsNo) {
		this.orderDetailsNo = orderDetailsNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
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
	public String toString() {
		return "OrderDeetail [orderDetailsNo=" + orderDetailsNo + ", orderNo=" + orderNo + ", memberId=" + memberId
				+ ", itemNo=" + itemNo + ", rentOptNo=" + rentOptNo + ", orderQuantity=" + orderQuantity
				+ ", orderStatusNo=" + orderStatusNo + ", reviewYn=" + reviewYn + ", orderCancelNo=" + orderCancelNo
				+ "]";
	}
	
	
	
	
}
