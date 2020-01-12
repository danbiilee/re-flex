package item.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ItemQnaAns implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int itemQnaAnsNo;
	private int itemQnaNo;
	private String itemQnaAnsWriter;
	private String itemQnaAnsContent;
	private Date itemQnaAnsDate;
	
	public ItemQnaAns() {
		super();
	}

	public ItemQnaAns(int itemQnaAnsNo, int itemQnaNo, String itemQnaAnsWriter, String itemQnaAnsContent,
			Date itemQnaAnsDate) {
		super();
		this.itemQnaAnsNo = itemQnaAnsNo;
		this.itemQnaNo = itemQnaNo;
		this.itemQnaAnsWriter = itemQnaAnsWriter;
		this.itemQnaAnsContent = itemQnaAnsContent;
		this.itemQnaAnsDate = itemQnaAnsDate;
	}

	public int getItemQnaAnsNo() {
		return itemQnaAnsNo;
	}

	public void setItemQnaAnsNo(int itemQnaAnsNo) {
		this.itemQnaAnsNo = itemQnaAnsNo;
	}

	public int getItemQnaNo() {
		return itemQnaNo;
	}

	public void setItemQnaNo(int itemQnaNo) {
		this.itemQnaNo = itemQnaNo;
	}

	public String getItemQnaAnsWriter() {
		return itemQnaAnsWriter;
	}

	public void setItemQnaAnsWriter(String itemQnaAnsWriter) {
		this.itemQnaAnsWriter = itemQnaAnsWriter;
	}

	public String getItemQnaAnsContent() {
		return itemQnaAnsContent;
	}

	public void setItemQnaAnsContent(String itemQnaAnsContent) {
		this.itemQnaAnsContent = itemQnaAnsContent;
	}

	public Date getItemQnaAnsDate() {
		return itemQnaAnsDate;
	}

	public void setItemQnaAnsDate(Date itemQnaAnsDate) {
		this.itemQnaAnsDate = itemQnaAnsDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ItemQnaAns [itemQnaAnsNo=" + itemQnaAnsNo + ", itemQnaNo=" + itemQnaNo + ", itemQnaAnsWriter="
				+ itemQnaAnsWriter + ", itemQnaAnsContent=" + itemQnaAnsContent + ", itemQnaAnsDate=" + itemQnaAnsDate
				+ "]";
	}

}
