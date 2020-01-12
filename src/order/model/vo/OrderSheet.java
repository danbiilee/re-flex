package order.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class OrderSheet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String orderNo;
	private String memberId;
	private Date orderDate;
	private String orderPayMethod;
	private String orderPayStatus;
	private int orderTotalItemEa;
	private int orderTotalPrice;
	private int orderUsePoint;
	private String impUid; //아임포트에서 발급하는 거래건당 고유번호
	
	public OrderSheet() {
		super();
	}
	
	public OrderSheet(String orderNo, String memberId, Date orderDate, String orderPayMethod, String orderPayStatus,
			int orderTotalItemEa, int orderTotalPrice, int orderUsePoint, String impUid) {
		super();
		this.orderNo = orderNo;
		this.memberId = memberId;
		this.orderDate = orderDate;
		this.orderPayMethod = orderPayMethod;
		this.orderPayStatus = orderPayStatus;
		this.orderTotalItemEa = orderTotalItemEa;
		this.orderTotalPrice = orderTotalPrice;
		this.orderUsePoint = orderUsePoint;
		this.impUid = impUid;
	}


	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getImpUid() {
		return impUid;
	}

	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderPayMethod() {
		return orderPayMethod;
	}

	public void setOrderPayMethod(String orderPayMethod) {
		this.orderPayMethod = orderPayMethod;
	}

	public String getOrderPayStatus() {
		return orderPayStatus;
	}

	public void setOrderPayStatus(String orderPayStatus) {
		this.orderPayStatus = orderPayStatus;
	}

	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getOrderTotalItemEa() {
		return orderTotalItemEa;
	}

	public void setOrderTotalItemEa(int orderTotalItemEa) {
		this.orderTotalItemEa = orderTotalItemEa;
	}

	public int getOrderUsePoint() {
		return orderUsePoint;
	}

	public void setOrderUsePoint(int orderUsePoint) {
		this.orderUsePoint = orderUsePoint;
	}

	@Override
	public String toString() {
		return "OrderSheet [orderNo=" + orderNo + ", memberId=" + memberId + ", orderDate=" + orderDate
				+ ", orderPayMethod=" + orderPayMethod + ", orderPayStatus=" + orderPayStatus + ", orderTotalItemEa="
				+ orderTotalItemEa + ", orderTotalPrice=" + orderTotalPrice + ", orderUsePoint=" + orderUsePoint
				+ ", impUid=" + impUid + "]";
	}

}
