package admin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import admin.model.vo.QnaAns;
import board.model.vo.Board;
import item.model.vo.Item;
import item.model.vo.ItemQnaAns;
import itemRentEach.model.vo.ItemRentEach;
import member.model.vo.Member;
import mypage.model.vo.Qna;
import order.model.vo.OrderDetail2;
import order.model.vo.OrderSheet;
//프로젝트 DAO
public class AdminDAO {

	private Properties prop = new Properties();
	
	 public AdminDAO(){
	        try {
	            //클래스객체 위치찾기 : 절대경로를 반환한다. 
	            String fileName = AdminDAO.class.getResource("/sql/admin/admin-query.properties").getPath();
	            prop.load(new FileReader(fileName));
	            
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public int selectTotalContent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContent");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public List<Member> selectMemberList(Connection conn, int cPage, int numPerPage) {
		 List<Member> list = new ArrayList<>();
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;
	        
	        String query = prop.getProperty("selectMemberListByPaging");
	        
	        try{
	            //미완성쿼리문을 가지고 객체생성. 
	            pstmt = conn.prepareStatement(query);
	            //cPage, numPerPage
	            //1, 10 => 1, 10 => 0+1
	            //2, 10 => 11, 20 => 10+1
	            //3, 10 => 21, 30 => 20+1
	            //(공식1)시작rownum, 끝rownum
	            pstmt.setInt(1, (cPage-1)*numPerPage+1);
	            pstmt.setInt(2, cPage*numPerPage);
	            
	            
	            //쿼리문실행
	            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
	            rset = pstmt.executeQuery();
	            
	            while(rset.next()){
	                Member m = new Member();
	                //컬럼명은 대소문자 구분이 없다.
					m.setMemberId(rset.getString("member_id"));
					m.setMemberName(rset.getString("member_name"));
					m.setMemberAddress(rset.getString("member_address"));
					m.setMemberPoint(rset.getInt("member_point"));
					m.setMemberEnrollDate(rset.getDate("member_enroll_date"));
	                
	                list.add(m);
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            close(rset);
	            close(pstmt);
	        }
	        
	        
	        return list;
	}

	public List<Member> selectMemberByMemberId(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = null;
		String query = prop.getProperty("selectMemberByMemberIdByPaging");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			//(공식1)
			pstmt.setInt(2,(cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while (rset.next()) {
				Member m = new Member();
				// 컬럼명은 대소문자 구분이 없다.
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberAddress(rset.getString("member_address"));
				m.setMemberPoint(rset.getInt("member_point"));
				m.setMemberEnrollDate(rset.getDate("member_enroll_date"));

				list.add(m);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;

	}

	public int selectTotalContentByMemberId(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberId");
		int totalContent = 0;
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public List<Member> selectMemberByMemberName(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberByMemberNameByPaging");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberAddress(rset.getString("member_address"));
				m.setMemberPoint(rset.getInt("member_point"));
				m.setMemberEnrollDate(rset.getDate("member_enroll_date"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalContentByMemberName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberName");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}


	public int memberDelete(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, memberId);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	//==============================아이템 추가 삭제 수정
	public int selectTotalItem(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalItem");
		int totalItem = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalItem = rset.getInt("cnt");
			}
			
			System.out.println("totalItem@dao="+totalItem);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalItem;
	}

	public List<Item> selectItemList(Connection conn, int cPage, int numPerPage) {
		List<Item> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectItemListByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
            //cPage, numPerPage
            //1, 10 => 1, 10 => 0+1
            //2, 10 => 11, 20 => 10+1
            //3, 10 => 21, 30 => 20+1
            //(공식1)시작rownum, 끝rownum
            pstmt.setInt(1, (cPage-1)*numPerPage+1);
            pstmt.setInt(2, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
                Item i = new Item();
                //컬럼명은 대소문자 구분이 없다.
				i.setItemNo(rset.getInt("item_no"));
				i.setCategoryNo(rset.getString("category_no"));
				i.setItemStock(rset.getInt("item_stock"));
				i.setItemBrand(rset.getString("item_brand"));
				i.setItemName(rset.getString("item_name"));
				i.setItemPrice(rset.getInt("item_price"));
				i.setItemDesc(rset.getString("item_desc"));
				i.setItemEnrollDate(rset.getDate("item_enroll_date"));
                
                list.add(i);
            }
            System.out.println("***********"+list+"**********");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<Item> selectItemByItemName(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Item> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectItemByItemNameByPaging");
		System.out.println(searchKeyword+"//"+(cPage-1)*numPerPage+1+"//"+cPage*numPerPage);
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Item i = new Item();
                //컬럼명은 대소문자 구분이 없다.
				i.setItemNo(rset.getInt("item_no"));
				i.setCategoryNo(rset.getString("category_no"));
				i.setItemStock(rset.getInt("item_stock"));
				i.setItemBrand(rset.getString("item_brand"));
				i.setItemName(rset.getString("item_name"));
				i.setItemPrice(rset.getInt("item_price"));
				i.setItemDesc(rset.getString("item_desc"));
				i.setItemEnrollDate(rset.getDate("item_enroll_date"));
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Item> selectItemByCategoryNo(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Item> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectItemByCategoryNoByPaging");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Item i = new Item();
                //컬럼명은 대소문자 구분이 없다.
				i.setItemNo(rset.getInt("item_no"));
				i.setCategoryNo(rset.getString("category_no"));
				i.setItemStock(rset.getInt("item_stock"));
				i.setItemBrand(rset.getString("item_brand"));
				i.setItemName(rset.getString("item_name"));
				i.setItemPrice(rset.getInt("item_price"));
				i.setItemDesc(rset.getString("item_desc"));
				i.setItemEnrollDate(rset.getDate("item_enroll_date"));
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalItemByItemName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalItemByItemName");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int selectTotalItemByCategoryNo(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalItemByCategoryNo");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int selectSellingItem(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectSellingItem");
		int sellingItem = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				sellingItem = rset.getInt("cnt");
			}
			
			System.out.println("sellingItem@dao="+sellingItem);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return sellingItem;
	}

	public int selectSoldoutItem(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectSoldoutItem");
		int soldoutItem = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				soldoutItem = rset.getInt("cnt");
			}
			
			System.out.println("soldoutItem@dao="+soldoutItem);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return soldoutItem;
	}

	public List<Qna> selectQnaList(Connection conn, int cPage, int numPerPage) {
		 List<Qna> list = new ArrayList<>();
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;
	        
	        String query = prop.getProperty("selectQnaListByPaging");
	        
	        try{
	            //미완성쿼리문을 가지고 객체생성. 
	            pstmt = conn.prepareStatement(query);
	           
	            pstmt.setInt(1, (cPage-1)*numPerPage+1);
	            pstmt.setInt(2, cPage*numPerPage);
	            
	            
	            //쿼리문실행
	            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
	            rset = pstmt.executeQuery();
	            
	            while(rset.next()){
	            	Qna q = new Qna();
	                //컬럼명은 대소문자 구분이 없다.
	    //번호	문의유형	제목	작성자	작성일자	답변여부
					//q.setMemberId(rset.getString("member_id"));
					q.setqNo(rset.getInt("p_qna_no"));
					q.setqTypeNo(rset.getString("p_qna_type_no"));
	                q.setqTilte(rset.getString("p_qna_title"));
	                q.setMemberId(rset.getString("member_id"));
	                q.setqDate(rset.getDate("p_qna_date"));
	                q.setqAns(rset.getString("p_ans_yn"));
	               
					
	                list.add(q);
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            close(rset);
	            close(pstmt);
	        }
	        
	        
	        return list;
	}

	public int selectTotalContent2(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContent2");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContent;
	}

	public Qna selectOne(Connection conn, int qNo) {
		Qna q = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("qnaSelectOne");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, qNo);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				
				q = new Qna();
				q.setqNo(rset.getInt("p_qna_no"));
				q.setMemberId(rset.getString("member_id"));
				q.setqTypeNo(rset.getString("p_qna_type"));
				q.setqTilte(rset.getString("p_qna_title"));;
				q.setqContent(rset.getString("p_qna_content"));
				q.setqDate(rset.getDate("p_qna_date"));
				q.setqAns(rset.getString("p_ans_yn"));
				q.setqImage(rset.getString("p_qna_image"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return q;
	}

	public int insertAns(Connection conn, QnaAns a) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertQnaAns");
		int result = 0;
		//INSERT INTO PERSONAL_QNA_ANS VALUES(seq_personal_qna_ans.nextval,?, ?, default, ?  );
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, a.getQnaNo());
			pstmt.setString(2, a.getaContent());
			
			
			result = pstmt.executeUpdate();
			
			
			System.out.println("result@dao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public String selectAns(Connection conn, int qNo) {
		String ans = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAns");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, qNo);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				/*
				 * 	p_qna_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	p_qna_type_no	varchar2(30)			NOT NULL,
	p_qna_title	varchar2(100)		NOT NULL,
	p_qna_content	varchar2(3000)		NOT NULL,
	p_qna_date	date	DEFAULT sysdate	NOT NULL,
	p_ans_yn	char(1)	DEFAULT 'N'	NOT NULL,
	p_qna_image	varchar2(300)		NULL,
				 */
				
				ans = rset.getString(1);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return ans;
	}

	public List<Qna> selectqnaType(Connection conn, String qnaSearchword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Qna> list = null;
		String query = prop.getProperty("selectqnaTypeByPaging");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+qnaSearchword+"%");
			
			//(공식1)
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while (rset.next()) {
				Qna q = new Qna();
			
				q.setqNo(rset.getInt("p_qna_no"));
				q.setqTypeNo(rset.getString("p_qna_type_no"));
				q.setqTilte(rset.getString("p_qna_title"));
				q.setMemberId(rset.getString("member_id"));
				q.setqDate(rset.getDate("p_qna_date"));
				q.setqAns(rset.getString("p_ans_yn"));
				list.add(q);
			}
			System.out.println("dao !!!!!!!!!!!!!!  "+list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalContentByqnaType(Connection conn, String qnaSearchword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByqnaType");
		int totalContent = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+qnaSearchword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public List<Qna> selectqnaYN(Connection conn, String qnaSearchword, int cPage, int numPerPage) {
		List<Qna> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectqnaYNByPaging");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, "%"+qnaSearchword+"%");
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Qna q = new Qna();
				
				q.setqNo(rset.getInt("p_qna_no"));
				q.setqTypeNo(rset.getString("p_qna_type_no"));
				q.setqTilte(rset.getString("p_qna_title"));
				q.setMemberId(rset.getString("member_id"));
				q.setqDate(rset.getDate("p_qna_date"));
				q.setqAns(rset.getString("p_ans_yn"));
				
				list.add(q);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectTotalContentByqnaYN(Connection conn, String qnaSearchword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByqnaYN");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+qnaSearchword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	
	//관리자 rental list
	public int selectTotalDetailItem(Connection conn, int itemNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalDetailItem");
		int totalItem = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalItem = rset.getInt("cnt");
			}
			
			System.out.println("totalItem@dao="+totalItem);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalItem;
		
	}

	public int rentItemYes(Connection conn, int itemNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("rentItemYes");
		int rentItemYes = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				rentItemYes = rset.getInt("cnt");
			}
			
			System.out.println("rentItemYes@dao="+rentItemYes);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return rentItemYes;
		
	}

	public int rentItemNo(Connection conn, int itemNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("rentItemNo");
		int rentItemNo = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				rentItemNo = rset.getInt("cnt");
			}
			
			System.out.println("rentItemNo@dao="+rentItemNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return rentItemNo;
	}

	public List<ItemRentEach> selectItemEachList(Connection conn, int itemNo, int cPage, int numPerPage) {
		List<ItemRentEach> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectItemEachList");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setInt(1, itemNo);
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				ItemRentEach i = new ItemRentEach();
                //컬럼명은 대소문자 구분이 없다.
				
				i.setItemEachNo(rset.getInt("item_each_no"));
				i.setItemNo(rset.getInt("item_no"));
				i.setItemRentYN(rset.getString("item_rent_yn").charAt(0));
				i.setRentOptNo(rset.getString("rent_opt_no"));
				i.setItemRentStart(rset.getDate("item_rent_start"));
				i.setItemRentEnd(rset.getDate("item_rent_end"));
				i.setItemRentUser(rset.getString("item_rent_user"));
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}

	public int selectTotalDetailItemAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalDetailItemAll");
		int totalItem = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalItem = rset.getInt("cnt");
			}
			
			System.out.println("totalItem@dao="+totalItem);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalItem;
	}

	public int rentItemYesAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("rentItemYesAll");
		int rentItemYes = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				rentItemYes = rset.getInt("cnt");
			}
			
			System.out.println("rentItemYes@dao="+rentItemYes);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return rentItemYes;
	}

	public int rentItemNoAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("rentItemNoAll");
		int rentItemNo = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				rentItemNo = rset.getInt("cnt");
			}
			
			System.out.println("rentItemNo@dao="+rentItemNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return rentItemNo;
	}

	public List<ItemRentEach> selectItemEachListAll(Connection conn, int cPage, int numPerPage) {
		List<ItemRentEach> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectItemEachListAll");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setInt(1,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(2, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				ItemRentEach i = new ItemRentEach();
                //컬럼명은 대소문자 구분이 없다.
				
				i.setItemEachNo(rset.getInt("item_each_no"));
				i.setItemNo(rset.getInt("item_no"));
				i.setItemRentYN(rset.getString("item_rent_yn").charAt(0));
				i.setRentOptNo(rset.getString("rent_opt_no"));
				i.setItemRentStart(rset.getDate("item_rent_start"));
				i.setItemRentEnd(rset.getDate("item_rent_end"));
				i.setItemRentUser(rset.getString("item_rent_user"));
				i.setItemName(rset.getString("item_name"));
				i.setCategoryNo(rset.getString("category_no"));
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<ItemRentEach> selectItemEachListByItemName(Connection conn, String searchKeyword, int cPage,
			int numPerPage) {
		List<ItemRentEach> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectItemEachListByItemName");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				ItemRentEach i = new ItemRentEach();
                //컬럼명은 대소문자 구분이 없다.
				i.setItemEachNo(rset.getInt("item_each_no"));
				i.setItemNo(rset.getInt("item_no"));
				i.setItemRentYN(rset.getString("item_rent_yn").charAt(0));
				i.setRentOptNo(rset.getString("rent_opt_no"));
				i.setItemRentStart(rset.getDate("item_rent_start"));
				i.setItemRentEnd(rset.getDate("item_rent_end"));
				i.setItemRentUser(rset.getString("item_rent_user"));
				i.setItemName(rset.getString("item_name"));
				i.setCategoryNo(rset.getString("category_no"));
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	
	}

	public int selectTotalItemEachByItemName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalItemEachByItemName");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	
	public int selectYesItemEachByItemName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectYesItemEachByItemName");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	
	public int selectNoItemEachByItemName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectNoItemEachByItemName");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public List<ItemRentEach> selectItemEachListByCategoryNo(Connection conn, String searchKeyword, int cPage,
			int numPerPage) {
		List<ItemRentEach> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectItemEachListByCategoryNo");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				ItemRentEach i = new ItemRentEach();
                //컬럼명은 대소문자 구분이 없다.
				i.setItemEachNo(rset.getInt("item_each_no"));
				i.setItemNo(rset.getInt("item_no"));
				i.setItemRentYN(rset.getString("item_rent_yn").charAt(0));
				i.setRentOptNo(rset.getString("rent_opt_no"));
				i.setItemRentStart(rset.getDate("item_rent_start"));
				i.setItemRentEnd(rset.getDate("item_rent_end"));
				i.setItemRentUser(rset.getString("item_rent_user"));
				i.setItemName(rset.getString("item_name"));
				i.setCategoryNo(rset.getString("category_no"));
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalItemEachByCategoryNo(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalItemEachByCategoryNo");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	
	public int selectYesItemEachByCategoryNo(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectYesItemEachByCategoryNo");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	
	public int selectNoItemEachByCategoryNo(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectNoItemEachByCategoryNo");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public List<ItemRentEach> selectItemEachListByRentYn(Connection conn, String searchKeyword, int cPage,
			int numPerPage) {
		List<ItemRentEach> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectItemEachListByRentYn");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				ItemRentEach i = new ItemRentEach();
                //컬럼명은 대소문자 구분이 없다.
				i.setItemEachNo(rset.getInt("item_each_no"));
				i.setItemNo(rset.getInt("item_no"));
				i.setItemRentYN(rset.getString("item_rent_yn").charAt(0));
				i.setRentOptNo(rset.getString("rent_opt_no"));
				i.setItemRentStart(rset.getDate("item_rent_start"));
				i.setItemRentEnd(rset.getDate("item_rent_end"));
				i.setItemRentUser(rset.getString("item_rent_user"));
				i.setItemName(rset.getString("item_name"));
				i.setCategoryNo(rset.getString("category_no"));
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalItemEachByRent_yn(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalItemEachByRent_yn");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int insertItemAns(Connection conn, ItemQnaAns iqn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertItemAns");
		
		try {
			//1.pstmt객체 생성 및 미완성쿼리 값대입
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, iqn.getItemQnaNo());
			pstmt.setString(2, iqn.getItemQnaAnsContent());
			
			//2.실행 결과 처리 DML
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납
			close(pstmt);
		}
		return result;
		
		
		
		
		
		
		
		
		
		
		
		
	}
	//==========================
	//상품 리뷰 조회페이지
	
	public int selectTotalDetailReview(Connection conn, int itemNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalDetailReview");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContent;
	}

	public List<Board> selectItemReviewList(Connection conn, int itemNo, int cPage, int numPerPage) {
		List<Board> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectItemReviewList");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
            //cPage, numPerPage
            //1, 10 => 1, 10 => 0+1
            //2, 10 => 11, 20 => 10+1
            //3, 10 => 21, 30 => 20+1
            //(공식1)시작rownum, 끝rownum
            pstmt.setInt(1, itemNo);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	Board b = new Board();
                //컬럼명은 대소문자 구분이 없다.
            	b.setReview_no(rset.getInt("review_no"));
            	b.setOrder_details_no(rset.getInt("order_detail_no"));
            	b.setReview_writer(rset.getString("review_writer"));
            	b.setReview_date(rset.getDate("review_date"));
            	b.setReview_star(rset.getInt("review_star"));
            	b.setReview_content(rset.getString("review_content"));
            	b.setReview_image(rset.getString("review_image"));
            	b.setReview_image_rename(rset.getString("review_image_rename"));
            	b.setReview_readCount(rset.getInt("review_readcount"));
            	b.setItem_no(rset.getInt("item_no"));
            	                
                list.add(b);
            }
            System.out.println("***********"+list+"**********");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	
	}
	//==========================
	//판매 현황 페이지
	public List<Integer> selectCategorySellCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCategorySellCount");
		List<Integer> categorySellCount = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int i = rset.getInt("cnt");
				
				categorySellCount.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return categorySellCount;
		
	}

	public List<Integer> selectMontlyIncome(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMontly");
		List<Integer> montlyIncome = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int i = rset.getInt("income");
				
				montlyIncome.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return montlyIncome;
	}

	public List<Integer> selectMontlySaleAmount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMontly");
		List<Integer> montlySaleAmount = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int i = rset.getInt("cnt");
				
				montlySaleAmount.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return montlySaleAmount;
	}

	public List<Integer> selectYearlyIncome(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectYearly");
		List<Integer> yearlyIncome = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int i = rset.getInt("income");
				
				yearlyIncome.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return yearlyIncome;
	}

	public List<Integer> selectYearlySaleAmount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectYearly");
		List<Integer> yearlySaleAmount = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int i = rset.getInt("cnt");
				
				yearlySaleAmount.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return yearlySaleAmount;
	}

	//==========================
	public int selectTotalOrderAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalOrderAll");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContent;
	}

	public int orderstatus(Connection conn, String os) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int osCnt = 0;
		String query = prop.getProperty("osCnt");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, os);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				osCnt= rset.getInt("cnt");
			}
			
			System.out.println("OSCount@dao="+osCnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return osCnt;
	}

	public List<OrderDetail2> selectOrderSheetList(Connection conn, int cPage, int numPerPage) {
		List<OrderDetail2> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectOrderSheetList");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
            //cPage, numPerPage
            //1, 10 => 1, 10 => 0+1
            //2, 10 => 11, 20 => 10+1
            //3, 10 => 21, 30 => 20+1
            //(공식1)시작rownum, 끝rownum
            pstmt.setInt(1, (cPage-1)*numPerPage+1);
            pstmt.setInt(2, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	OrderDetail2 od = new OrderDetail2();
                //컬럼명은 대소문자 구분이 없다.
            	od.setOrderNo(rset.getString("order_no"));
            	od.setMemberId(rset.getString("member_id"));
            	od.setOrderTotalItemEa(rset.getInt("order_total_item_ea"));
            	od.setOrderTotalPrice(rset.getInt("order_total_price"));
            	od.setOrderStatusNo(rset.getString("order_status_no"));
            	
                list.add(od);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<OrderDetail2> selectOrderListbyId(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<OrderDetail2> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOrderListbyId");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				OrderDetail2 od = new OrderDetail2();
                //컬럼명은 대소문자 구분이 없다.
            	od.setOrderNo(rset.getString("order_no"));
            	od.setMemberId(rset.getString("member_id"));
            	od.setOrderTotalItemEa(rset.getInt("order_total_item_ea"));
            	od.setOrderTotalPrice(rset.getInt("order_total_price"));
            	od.setOrderStatusNo(rset.getString("order_status_no"));
            	
                list.add(od);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<OrderDetail2> selectOrderListbyOs(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<OrderDetail2> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOrderListbyOs");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(sql);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				OrderDetail2 od = new OrderDetail2();
                //컬럼명은 대소문자 구분이 없다.
            	od.setOrderNo(rset.getString("order_no"));
            	od.setMemberId(rset.getString("member_id"));
            	od.setOrderTotalItemEa(rset.getInt("order_total_item_ea"));
            	od.setOrderTotalPrice(rset.getInt("order_total_price"));
            	od.setOrderStatusNo(rset.getString("order_status_no"));
            	
                list.add(od);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalListById(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalListById");
		int totalContent = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int selectTotalListByOs(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalListByOs");
		int totalContent = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int updateOrder(Connection conn, String orderNo, String orderStatus) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateOrder"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, orderStatus);
			pstmt.setString(2, orderNo);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	//==========================
	//메인 필터 처리
	
	public List<String> selectCategorySellAmount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCategorySellAmount");
		List<String> categorySellAmount = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				String str = rset.getString("category_no");
				
				categorySellAmount.add(str);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return categorySellAmount;
	}
	
}

	
