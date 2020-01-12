package item.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ItemQna implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int itemQnaNo;
	private String memberId;
	private int itemNo;
	private String itemQnaContent;
	private Date itemQnaDate;
	private String itemQnaAnsYn;
	
	public ItemQna() {
		super();
	}

	public ItemQna(int itemQnaNo, String memberId, int itemNo, String itemQnaContent, Date itemQnaDate,
			String itemQnaAnsYn) {
		super();
		this.itemQnaNo = itemQnaNo;
		this.memberId = memberId;
		this.itemNo = itemNo;
		this.itemQnaContent = itemQnaContent;
		this.itemQnaDate = itemQnaDate;
		this.itemQnaAnsYn = itemQnaAnsYn;
	}

	public int getItemQnaNo() {
		return itemQnaNo;
	}

	public void setItemQnaNo(int itemQnaNo) {
		this.itemQnaNo = itemQnaNo;
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

	public String getItemQnaContent() {
		return itemQnaContent;
	}

	public void setItemQnaContent(String itemQnaContent) {
		this.itemQnaContent = itemQnaContent;
	}

	public Date getItemQnaDate() {
		return itemQnaDate;
	}

	public void setItemQnaDate(Date itemQnaDate) {
		this.itemQnaDate = itemQnaDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getItemQnaAnsYn() {
		return itemQnaAnsYn;
	}

	public void setItemQnaAnsYn(String itemQnaAnsYn) {
		this.itemQnaAnsYn = itemQnaAnsYn;
	}

	@Override
	public String toString() {
		return "ItemQna [itemQnaNo=" + itemQnaNo + ", memberId=" + memberId + ", itemNo=" + itemNo + ", itemQnaContent="
				+ itemQnaContent + ", itemQnaDate=" + itemQnaDate + ", itemQnaAnsYn=" + itemQnaAnsYn + "]";
	}

}
