package rent.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;


import rent.model.dao.rentDAO;
import rent.model.vo.rent;



public class rentService {
	
	
	//종료된 렌탈
	public List<rent> rentfinviewList(String itemrentuser) {
		Connection conn = getConnection();
		
		List<rent> list = new rentDAO().rentfinviewList(conn, itemrentuser);
		
		close(conn);
		
		return list;
		
	}
	//진행중 렌탈
	public List<rent> rentingviewList(String itemrentuser) {
		Connection conn = getConnection();
		
		List<rent> list = new rentDAO().rentingviewList(conn, itemrentuser);
		
		close(conn);
		
		return list;
		
	}
	//렌탈중 카운팅
	public int rentingcnt(String itemrentuser) {
		
		Connection conn = getConnection();
		
		int cnt = new rentDAO().rentingcnt(conn, itemrentuser);
		
		close(conn);
		
		return cnt;	
	}
	
	//종료된 카운팅
		public int rentfincnt(String itemrentuser) {
			
			Connection conn = getConnection();
			
			int cntfin = new rentDAO().rentfincnt(conn, itemrentuser);
			
			close(conn);
			
			return cntfin;	
		}
		//1개월
		public List<rent> MypageRentalFinOne(String itemrentuser) {
			
			Connection conn = getConnection();
			
			List<rent> list = new rentDAO().MypageRentalFinOne(conn, itemrentuser);
			
			close(conn);
			
			return list;
		}
		//3개월
		public List<rent> MypageRentalFinThree(String itemrentuser) {
			
			Connection conn = getConnection();
			
			List<rent> list = new rentDAO().MypageRentalFinThree(conn, itemrentuser);
			
			close(conn);
			
			return list;
		}
		public List<rent> MypageRentalFinSix(String itemrentuser) {
			
			Connection conn = getConnection();
			
			List<rent> list = new rentDAO().MypageRentalFinSix(conn, itemrentuser);
			
			close(conn);
			
			return list;
		}
		public List<rent> MypageRentalFinAll(String itemrentuser) {
			
			Connection conn = getConnection();
			
			List<rent> list = new rentDAO().MypageRentalFinAll(conn, itemrentuser);
			
			close(conn);
			
			return list;
		}
		//디데이
		public List<Integer> rentingdday(String itemrentuser) {
			Connection conn = getConnection();
			
			List<Integer> list= new rentDAO().rentingdday(conn, itemrentuser);
			
			close(conn);
			
			return list;	
		}
	


}
