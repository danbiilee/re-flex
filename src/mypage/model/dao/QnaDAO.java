package mypage.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mypage.model.vo.MyPage;
import mypage.model.vo.Qna;

public class QnaDAO {
	
	private Properties prop = new Properties();
	
	public QnaDAO() {
		String fileName = QnaDAO.class.getResource("/sql/mypage/mypage-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Qna> selectQnaList(Connection conn, String memberId, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectQnaListByPaging");
		System.out.println("cPage = " + cPage +", numPerPage="+numPerPage+", memberId="+memberId);
		List<Qna> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, cPage*numPerPage -(numPerPage-1));
			pstmt.setInt(3, cPage*numPerPage);
			
			
			rset = pstmt.executeQuery();
			
            while(rset.next()){
            	Qna qna = new Qna();
                //컬럼명은 대소문자 구분이 없다.
				qna.setqNo(rset.getInt("p_qna_no"));
				qna.setMemberId(rset.getString("member_id"));
				qna.setqTypeNo(rset.getString("p_qna_type_no"));
				qna.setqTilte(rset.getString("p_qna_title"));
				qna.setqContent(rset.getString("p_qna_content"));
				qna.setqDate(rset.getDate("p_qna_date"));
				qna.setqAns(rset.getString("p_ans_yn"));
				qna.setqImage(rset.getString("p_qna_image"));
                
                list.add(qna);
            }
            
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
		
		return list;
	}

	public int selectQnaCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectQnaCount");
		
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			System.out.println("totalContent@DAO="+totalContent);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int qEnroll(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("qEnroll");
		
		System.out.println(conn);
		System.out.println(query);
		try {
			pstmt = conn.prepareStatement(query);
			
			
//			pstmt.setInt(1, q.getqNo());
			pstmt.setString(1, q.getMemberId());
			pstmt.setString(2, q.getqTypeNo());
			pstmt.setString(3, q.getqTilte());
			pstmt.setString(4, q.getqContent());
//			pstmt.setDate(6, q.getqDate());
//			pstmt.setString(7, q.getqAns());
			pstmt.setString(5, q.getqImage());
			
			result = pstmt.executeUpdate();

			System.out.println("result="+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Qna selectQna(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna qna = new Qna();
		String query = prop.getProperty("selectQna");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qna.setqNo(rset.getInt("p_qna_no"));
				qna.setMemberId(rset.getString("member_id"));
				qna.setqTypeNo(rset.getString("p_qna_type_no"));
				qna.setqTilte(rset.getString("p_qna_title"));
				qna.setqContent(rset.getString("p_qna_content"));
				qna.setqDate(rset.getDate("p_qna_date"));
				qna.setqAns(rset.getString("p_ans_yn"));
				qna.setqImage(rset.getString("p_qna_image"));
			}
			System.out.println("Qna@DAO="+qna);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return qna;
	}

	public int increaseReadCount(Connection conn, int qNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("increaseReadCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteQna(Connection conn, int qNo) {
		
		PreparedStatement pstmt = null;
		int result=0;
		String query = prop.getProperty("deleteQna");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateQna(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateQna");
		
		try {
			pstmt = conn.prepareStatement(query);
			
//			pstmt.setString(2, q.getMemberId());
			pstmt.setString(1, q.getqTypeNo());
			pstmt.setString(2, q.getqTilte());
			pstmt.setString(3, q.getqContent());
//			pstmt.setDate(6, q.getqDate());
//			pstmt.setString(7, q.getqAns());
			pstmt.setString(4, q.getqImage());
			pstmt.setInt(5, q.getqNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Object selectLastSeq(Connection conn) {
		int qNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectlastSeq");
		try {
			pstmt=conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				qNo = rset.getInt("currval");
			}
			System.out.println("새로발급받은 게시글 번호@dao="+qNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return qNo;
	}

	public Qna selectOne(Connection conn, int qNo) {
		System.out.println("@@@@@@@@@@dao시작");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna qna = null;
		String query = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qna = new Qna();
				qna.setqNo(rset.getInt("p_qna_no"));
				qna.setMemberId(rset.getString("member_id"));
				qna.setqTypeNo(rset.getString("p_qna_type_no"));
				qna.setqTilte(rset.getString("p_qna_title"));
				qna.setqContent(rset.getString("p_qna_content"));
				qna.setqDate(rset.getDate("p_qna_date"));
				qna.setqAns(rset.getString("p_ans_yn"));
				qna.setqImage(rset.getString("p_qna_image"));
			}
			System.out.println("Qna@DAO="+qna);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return qna;
	}

	public int selectTotalContent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPointOneAllPaging");
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

	public List<Qna> selectOneToOneAll(Connection conn, String memberId, int cPage, int numPerPage) {
		List<Qna> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectOneToOneSixByPaging");
        try{
            //미완성쿼리문을 가지고 객체생성.
            pstmt = conn.prepareStatement(query);
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            while(rset.next()){
            	Qna qna = new Qna();
                //컬럼명은 대소문자 구분이 없다.
				qna.setqNo(rset.getInt("p_qna_no"));
				qna.setMemberId(rset.getString("member_id"));
				qna.setqTypeNo(rset.getString("p_qna_type_no"));
				qna.setqTilte(rset.getString("p_qna_title"));
				qna.setqContent(rset.getString("p_qna_content"));
				qna.setqDate(rset.getDate("p_qna_date"));
				qna.setqAns(rset.getString("p_ans_yn"));
				qna.setqImage(rset.getString("p_qna_image"));
                list.add(qna);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        return list;
	}
	
	public List<Qna> selectOneToOneOne(Connection conn, String memberId, int cPage, int numPerPage) {
		List<Qna> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectOneToOneOneByPaging");
        try{
            //미완성쿼리문을 가지고 객체생성.
            pstmt = conn.prepareStatement(query);
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            while(rset.next()){
            	Qna qna = new Qna();
                //컬럼명은 대소문자 구분이 없다.
				qna.setqNo(rset.getInt("p_qna_no"));
				qna.setMemberId(rset.getString("member_id"));
				qna.setqTypeNo(rset.getString("p_qna_type_no"));
				qna.setqTilte(rset.getString("p_qna_title"));
				qna.setqContent(rset.getString("p_qna_content"));
				qna.setqDate(rset.getDate("p_qna_date"));
				qna.setqAns(rset.getString("p_ans_yn"));
				qna.setqImage(rset.getString("p_qna_image"));
                list.add(qna);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        return list;
	}
	
	public List<Qna> selectOneToOneThree(Connection conn, String memberId, int cPage, int numPerPage) {
		List<Qna> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectOneToOneThreeByPaging");
        try{
            //미완성쿼리문을 가지고 객체생성.
            pstmt = conn.prepareStatement(query);
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            while(rset.next()){
            	Qna qna = new Qna();
                //컬럼명은 대소문자 구분이 없다.
				qna.setqNo(rset.getInt("p_qna_no"));
				qna.setMemberId(rset.getString("member_id"));
				qna.setqTypeNo(rset.getString("p_qna_type_no"));
				qna.setqTilte(rset.getString("p_qna_title"));
				qna.setqContent(rset.getString("p_qna_content"));
				qna.setqDate(rset.getDate("p_qna_date"));
				qna.setqAns(rset.getString("p_ans_yn"));
				qna.setqImage(rset.getString("p_qna_image"));
                list.add(qna);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        return list;
	}
	
	public List<Qna> selectOneToOneSix(Connection conn, String memberId, int cPage, int numPerPage) {
		List<Qna> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectOneToOneSixByPaging");
        try{
            //미완성쿼리문을 가지고 객체생성.
            pstmt = conn.prepareStatement(query);
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            while(rset.next()){
            	Qna qna = new Qna();
                //컬럼명은 대소문자 구분이 없다.
				qna.setqNo(rset.getInt("p_qna_no"));
				qna.setMemberId(rset.getString("member_id"));
				qna.setqTypeNo(rset.getString("p_qna_type_no"));
				qna.setqTilte(rset.getString("p_qna_title"));
				qna.setqContent(rset.getString("p_qna_content"));
				qna.setqDate(rset.getDate("p_qna_date"));
				qna.setqAns(rset.getString("p_ans_yn"));
				qna.setqImage(rset.getString("p_qna_image"));
                list.add(qna);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        return list;
	}

}
	
	
	
	



