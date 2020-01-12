package member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;

import static common.JDBCTemplate.*;

public class MemberDAO {
	
	private Properties prop = new Properties();
	
	public MemberDAO() {
		String fileName = MemberDAO.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(Connection conn, String memberId) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		
		
		try {
			//1. prepareStatment객체 생성
			pstmt = conn.prepareStatement(query);
			
			//2.미완성 쿼리 값 대입
			pstmt.setString(1, memberId);
			
			//3.쿼리 실행
			rset = pstmt.executeQuery();
			
			//4. resultSet => member
			if(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPassword(rset.getString("member_password"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberPostcode(rset.getInt("member_postcode"));
				m.setMemberAddress(rset.getString("member_address"));
				m.setMemberDetailAddress(rset.getString("member_detail_address"));
				m.setMemberPoint(rset.getInt("member_point"));
				m.setMemberHobby1(rset.getString("member_hobby1"));
				m.setMemberHobby2(rset.getString("member_hobby2"));
				m.setMemberQuitYn(rset.getString("member_quit_yn").charAt(0));
				m.setMemberEnrollDate(rset.getDate("member_enroll_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}

	public int memberEnroll(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("memberEnroll");
		
		try {
			//1. prepareStatment객체 생성
			pstmt = conn.prepareStatement(query);
			
			//2.쿼리 실행
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMemberPassword());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setInt(6, member.getMemberPostcode());
			pstmt.setString(7, member.getMemberAddress());
			pstmt.setString(8, member.getMemberDetailAddress());
			pstmt.setString(9, member.getMemberHobby1());
			pstmt.setString(10, member.getMemberHobby2());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int memberUpdate(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("memberUpdate");
		
		try {
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, member.getMemberPassword());
			pstmt.setString(2, member.getMemberPhone());
			pstmt.setString(3, member.getMemberEmail());
			pstmt.setInt(4, member.getMemberPostcode());
			pstmt.setString(5, member.getMemberAddress());
			pstmt.setString(6, member.getMemberDetailAddress());
			pstmt.setString(7, member.getMemberHobby1());
			pstmt.setString(8, member.getMemberHobby2());
			pstmt.setString(9, member.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int memberDelete(Connection conn, String memberId, String memberPwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			
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

}
