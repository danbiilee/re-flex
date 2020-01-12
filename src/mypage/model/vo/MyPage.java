package mypage.model.vo;

import java.io.Serializable;
import java.sql.Date;



public class MyPage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public MyPage() {
		super();
	}
	
	public MyPage(int pointNo, String memberId, char pointStatus, int pointAmount, String pointChangeReason,
			Date pointChangeDate) {
		super();
		this.pointNo = pointNo;
		this.memberId = memberId;
		this.pointStatus = pointStatus;
		this.pointAmount = pointAmount;
		this.pointChangeReason = pointChangeReason;
		this.pointChangeDate = pointChangeDate;
	}
	
	private int pointNo;
	private String memberId;
	private char pointStatus;
	private int pointAmount;
	private String pointChangeReason;
	private Date pointChangeDate;
	
	
	
	@Override
	public String toString() {
		return "MyPage [pointNo=" + pointNo + ", memberId=" + memberId + ", pointStatus=" + pointStatus
				+ ", pointAmount=" + pointAmount + ", pointChangeReason=" + pointChangeReason + ", pointChangeDate="
				+ pointChangeDate + "]";
	}
	
	public int getPointNo() {
		return pointNo;
	}
	public void setPointNo(int pointNo) {
		this.pointNo = pointNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public char getPointStatus() {
		return pointStatus;
	}
	public void setPointStatus(char pointStatus) {
		this.pointStatus = pointStatus;
	}
	public int getPointAmount() {
		return pointAmount;
	}
	public void setPointAmount(int pointAmount) {
		this.pointAmount = pointAmount;
	}
	public String getPointChangeReason() {
		return pointChangeReason;
	}
	public void setPointChangeReason(String pointChangeReason) {
		this.pointChangeReason = pointChangeReason;
	}
	public Date getPointChangeDate() {
		return pointChangeDate;
	}
	public void setPointChangeDate(Date pointChangeDate) {
		this.pointChangeDate = pointChangeDate;
	}
	

	
	
}
