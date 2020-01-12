package board.model.vo;

import java.io.Serializable;
import java.sql.Date;
/**
 * VO
 * Entity
 * DTO
 * bean 
 */
public class Board implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int review_no;
	private  int order_details_no;
	private String review_writer;
	private Date review_date;
	private int review_star;
	private String review_content;
	private String review_image;
	private String  review_image_rename;
	private int review_readCount;
	private int item_no;
	
	






	public Board() {}


	


	public Board(int review_no, int order_details_no, String review_writer, Date review_date, int review_star,
			String review_content, String review_image, String review_image_rename, int review_readCount, int item_no) {
		super();
		this.review_no = review_no;
		this.order_details_no = order_details_no;
		this.review_writer = review_writer;
		this.review_date = review_date;
		this.review_star = review_star;
		this.review_content = review_content;
		this.review_image = review_image;
		this.review_image_rename = review_image_rename;
		this.review_readCount = review_readCount;
		this.item_no = item_no;
	}





	public String getReview_image_rename() {
		return review_image_rename;
	}





	public void setReview_image_rename(String review_image_rename) {
		this.review_image_rename = review_image_rename;
	}





	public int getReview_no() {
		return review_no;
	}


	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}


	public int getOrder_details_no() {
		return order_details_no;
	}


	public void setOrder_details_no(int order_details_no) {
		this.order_details_no = order_details_no;
	}


	public String getReview_writer() {
		return review_writer;
	}


	public void setReview_writer(String review_writer) {
		this.review_writer = review_writer;
	}


	public Date getReview_date() {
		return review_date;
	}


	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}


	public int getReview_star() {
		return review_star;
	}


	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}


	public String getReview_content() {
		return review_content;
	}


	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}


	public String getReview_image() {
		return review_image;
	}


	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}


	public int getReview_readCount() {
		return review_readCount;
	}


	public void setReview_readCount(int review_readCount) {
		this.review_readCount = review_readCount;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getItem_no() {
		return item_no;
	}

	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}





	@Override
	public String toString() {
		return "Board [review_no=" + review_no + ", order_details_no=" + order_details_no + ", review_writer="
				+ review_writer + ", review_date=" + review_date + ", review_star=" + review_star + ", review_content="
				+ review_content + ", review_image=" + review_image + ", review_image_rename=" + review_image_rename
				+ ", review_readCount=" + review_readCount + ", item_no=" + item_no + "]";
	}

	


	
	
	

}
