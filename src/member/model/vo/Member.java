package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Member implements Serializable, HttpSessionBindingListener {

	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String memberName;
	private String memberPassword;
	private String memberPhone;
	private String memberEmail;
	private int memberPostcode;
	private String memberAddress;
	private String memberDetailAddress;
	private int memberPoint;
	private String memberHobby1;
	private String memberHobby2;
	private char memberQuitYn;
	private Date memberEnrollDate;
	
	public Member() {
		super();
	}
	
	public Member(String memberId, String memberName, String memberPassword, String memberPhone, String memberEmail,
			int memberPostcode, String memberAddress, String memberDetailAddress, int memberPoint, String memberHobby1,
			String memberHobby2, char memberQuitYn, Date memberEnrollDate) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPassword = memberPassword;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberPostcode = memberPostcode;
		this.memberAddress = memberAddress;
		this.memberDetailAddress = memberDetailAddress;
		this.memberPoint = memberPoint;
		this.memberHobby1 = memberHobby1;
		this.memberHobby2 = memberHobby2;
		this.memberQuitYn = memberQuitYn;
		this.memberEnrollDate = memberEnrollDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public int getMemberPostcode() {
		return memberPostcode;
	}

	public void setMemberPostcode(int memberPostcode) {
		this.memberPostcode = memberPostcode;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberDetailAddress() {
		return memberDetailAddress;
	}

	public void setMemberDetailAddress(String memberDetailAddress) {
		this.memberDetailAddress = memberDetailAddress;
	}

	public int getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}

	public String getMemberHobby1() {
		return memberHobby1;
	}

	public void setMemberHobby1(String memberHobby1) {
		this.memberHobby1 = memberHobby1;
	}

	public String getMemberHobby2() {
		return memberHobby2;
	}

	public void setMemberHobby2(String memberHobby2) {
		this.memberHobby2 = memberHobby2;
	}

	public char getMemberQuitYn() {
		return memberQuitYn;
	}

	public void setMemberQuitYn(char memberQuitYn) {
		this.memberQuitYn = memberQuitYn;
	}

	public Date getMemberEnrollDate() {
		return memberEnrollDate;
	}

	public void setMemberEnrollDate(Date memberEnrollDate) {
		this.memberEnrollDate = memberEnrollDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", memberPassword=" + memberPassword
				+ ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", memberPostcode=" + memberPostcode
				+ ", memberAddress=" + memberAddress + ", memberDetailAddress=" + memberDetailAddress + ", memberPoint="
				+ memberPoint + ", memberHobby1=" + memberHobby1 + ", memberHobby2=" + memberHobby2 + ", memberQuitYn="
				+ memberQuitYn + ", memberEnrollDate=" + memberEnrollDate + "]";
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("["+memberId+"]님이 ["+new java.util.Date()+"]에 로그인하셨습니다.");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("["+memberId+"]님이 ["+new java.util.Date()+"]에 로그아웃하셨습니다.");
	}

}
