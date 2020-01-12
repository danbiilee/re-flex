package mypage.model.vo;

import java.sql.Date;

public class Qna {

	public int qNo;
	public String memberId;
	public String qTypeNo;
	public String qTilte;
	public String qContent;
	public Date qDate;
	public String qAns;
	public String qImage;
	
	
	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Qna(int qNo, String memberId, String qTypeNo, String qTilte, String qContent, Date qDate, String qAns,
			String qImage) {
		super();
		this.qNo = qNo;
		this.memberId = memberId;
		this.qTypeNo = qTypeNo;
		this.qTilte = qTilte;
		this.qContent = qContent;
		this.qDate = qDate;
		this.qAns = qAns;
		this.qImage = qImage;
	}

	public int getqNo() {
		return qNo;
	}

	public void setqNo(int qNo) {
		this.qNo = qNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getqTypeNo() {
		return qTypeNo;
	}

	public void setqTypeNo(String qTypeNo) {
		this.qTypeNo = qTypeNo;
	}

	public String getqTilte() {
		return qTilte;
	}

	public void setqTilte(String qTilte) {
		this.qTilte = qTilte;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public Date getqDate() {
		return qDate;
	}

	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}

	public String getqAns() {
		return qAns;
	}

	public void setqAns(String qAns) {
		this.qAns = qAns;
	}

	public String getqImage() {
		return qImage;
	}

	public void setqImage(String qImage) {
		this.qImage = qImage;
	}

	@Override
	public String toString() {
		return "Qna [qNo=" + qNo + ", memberId=" + memberId + ", qTypeNo=" + qTypeNo + ", qTilte=" + qTilte
				+ ", qContent=" + qContent + ", qDate=" + qDate + ", qAns=" + qAns + ", qImage=" + qImage + "]";
	}
	
	
	
}
