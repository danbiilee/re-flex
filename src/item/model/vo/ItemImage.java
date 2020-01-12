package item.model.vo;

import java.io.Serializable;

public class ItemImage implements Serializable{

	private static final long serialVersionUID = 1L;
	int itemImageNo;
	int itemNo;
	String itemImageTypeNo;
	String itemImageDefault;
	String itemImageRenamed;
	
	public ItemImage() {
		super();
	}

	public ItemImage(int itemImageNo, int itemNo, String itemImageTypeNo, String itemImageDefault, String itemImageRenamed) {
		super();
		this.itemImageNo = itemImageNo;
		this.itemNo = itemNo;
		this.itemImageTypeNo = itemImageTypeNo;
		this.itemImageDefault = itemImageDefault;
		this.itemImageRenamed = itemImageRenamed;
	}

	public int getItemImageNo() {
		return itemImageNo;
	}

	public void setItemImageNo(int itemImageNo) {
		this.itemImageNo = itemImageNo;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemImageTypeNo() {
		return itemImageTypeNo;
	}

	public void setItemImageTypeNo(String itemImageTypeNo) {
		this.itemImageTypeNo = itemImageTypeNo;
	}

	public String getItemImageDefault() {
		return itemImageDefault;
	}

	public void setItemImageDefault(String itemImageDefault) {
		this.itemImageDefault = itemImageDefault;
	}

	public String getItemImageRenamed() {
		return itemImageRenamed;
	}

	public void setItemImageRenamed(String itemImageRenamed) {
		this.itemImageRenamed = itemImageRenamed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ItemImage [itemImageNo=" + itemImageNo + ", itemNo=" + itemNo + ", itemImageTypeNo=" + itemImageTypeNo
				+ ", itemImageDefault=" + itemImageDefault + ", itemImageRenamed=" + itemImageRenamed + "]";
	}

	
	
}
