package admin.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class QnaAns implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public QnaAns() {
	
	}
	/*
	 * 	p_qna_ans_no	number		NOT NULL,
	p_qna_no	number		NOT NULL,
	p_qna_ans_writer	varchar2(20)		NOT NULL,
	p_qna_ans_date	date	DEFAULT sysdate	NOT NULL,
	p_qna_ans_content	varchar2(1000)		NOT NULL,
	 */
	
	private int aNo;
	private int qnaNo;
	private String aWriter;
	private Date aDate;
	private String aContent;

	
	
	public QnaAns(int aNo, int qnaNo, String aWriter, Date aDate, String aContent) {
		super();
		this.aNo = aNo;
		this.qnaNo = qnaNo;
		this.aWriter = aWriter;
		this.aDate = aDate;
		this.aContent = aContent;
	}
	
	public int getaNo() {
		return aNo;
	}
	public void setaNo(int aNo) {
		this.aNo = aNo;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getaWriter() {
		return aWriter;
	}
	public void setaWriter(String aWriter) {
		this.aWriter = aWriter;
	}
	public Date getaDate() {
		return aDate;
	}
	public void setaDate(Date aDate) {
		this.aDate = aDate;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}

	@Override
	public String toString() {
		return "QnaAns [aNo=" + aNo + ", qnaNo=" + qnaNo + ", aWriter=" + aWriter + ", aDate=" + aDate + ", aContent="
				+ aContent + "]";
	}
	
	
	
}
