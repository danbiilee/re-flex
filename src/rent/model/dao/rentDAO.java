package rent.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.dao.MemberDAO;
import rent.model.vo.rent;


public class rentDAO {
	
	private Properties prop = new Properties();
	
	public rentDAO() {
		String fileName = MemberDAO.class.getResource("/sql/mypage/mypage-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//종료된 렌탈
		public List<rent> rentfinviewList(Connection conn, String itemrentuser) {
			
			List<rent> list = new ArrayList<>();
			
			rent r = null;
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "SELECT * FROM ITEM_RENT_EACH IRE JOIN ITEM I ON IRE.ITEM_NO = I.ITEM_NO WHERE ITEM_RENT_USER = ? AND ITEM_RENT_YN = 'N' ";
			
			System.out.println("DAO@@="+query);
			System.out.println("DAO@@user="+itemrentuser);
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1 , itemrentuser );
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					r = new rent();
					r.setItemNo(rset.getInt("item_no"));
					r.setItemEachNo(rset.getInt("Item_Each_No"));
					r.setItemRentEnd(rset.getDate("item_rent_end"));
					r.setItemRentStart(rset.getDate("item_rent_start"));
					r.setItemRentUser(rset.getString("item_rent_user"));
					r.setItemRentYN((rset.getString("item_rent_yn")).charAt(0));
					r.setRentOptNo(rset.getString("rent_opt_no"));
					r.setItemBrand(rset.getString("item_brand"));
					r.setItemPrice(rset.getInt("item_price"));
					r.setItemEnrollDate(rset.getDate("item_enroll_date"));
					r.setItemName(rset.getString("item_name"));
					r.setCategoryNo(rset.getString("category_no"));
					

					
					list.add(r);
				}
					System.out.println("@@@DAO"+list);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
		
		//진행중 렌탈
				public List<rent> rentingviewList(Connection conn, String itemrentuser) {
					
					List<rent> list = new ArrayList<>();
					
					rent r = null;
					
					PreparedStatement pstmt = null;
					ResultSet rset = null;
					
					String query = "SELECT * FROM ITEM_RENT_EACH IRE JOIN  ITEM I ON IRE.ITEM_NO = I.ITEM_NO WHERE ITEM_RENT_USER = ? AND ITEM_RENT_YN = 'Y' ";
					
					System.out.println("DAO@@="+query);
					System.out.println("DAO@@user="+itemrentuser);
					try {
						pstmt = conn.prepareStatement(query);
						
						pstmt.setString(1 , itemrentuser );
						
						rset = pstmt.executeQuery();
						
						while(rset.next()) {
							r = new rent();
							r.setItemNo(rset.getInt("item_no"));
							r.setItemEachNo(rset.getInt("Item_Each_No"));
							r.setItemRentEnd(rset.getDate("item_rent_end"));
							r.setItemRentStart(rset.getDate("item_rent_start"));
							r.setItemRentUser(rset.getString("item_rent_user"));
							r.setItemRentYN((rset.getString("item_rent_yn")).charAt(0));
							r.setRentOptNo(rset.getString("rent_opt_no"));
							r.setItemBrand(rset.getString("item_brand"));
							r.setItemPrice(rset.getInt("item_price"));
							r.setItemEnrollDate(rset.getDate("item_enroll_date"));
							r.setItemName(rset.getString("item_name"));
							r.setCategoryNo(rset.getString("category_no"));
							
							list.add(r);
						}
							System.out.println("@@@DAO"+list);
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rset);
						close(pstmt);
					}
					
					return list;
				}
//렌탈중 카운팅
				public int rentingcnt(Connection conn, String itemrentuser) {
					
					int cnt = 0;

					PreparedStatement pstmt = null;
					ResultSet rset = null;
					
					String query = "SELECT COUNT(*) FROM ITEM_RENT_EACH IRE JOIN  ITEM I ON IRE.ITEM_NO = I.ITEM_NO WHERE ITEM_RENT_USER = ? AND ITEM_RENT_YN = 'Y' ";
					
					System.out.println("DAO@@="+query);
					System.out.println("DAO@@user="+itemrentuser);
					try {
						pstmt = conn.prepareStatement(query);
						
						pstmt.setString(1 , itemrentuser );
						
						rset = pstmt.executeQuery();
						
						if(rset.next()) {
							cnt = rset.getInt(1);
						}
							System.out.println("@@@DAO"+cnt);
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rset);
						close(pstmt);
					}
					
					return cnt;
				}
//종료된 카운팅
				public int rentfincnt(Connection conn, String itemrentuser) {
					
					int cntfin = 0;

					PreparedStatement pstmt = null;
					ResultSet rset = null;
					
					String query = "SELECT COUNT(*) FROM ITEM_RENT_EACH IRE JOIN  ITEM I ON IRE.ITEM_NO = I.ITEM_NO WHERE ITEM_RENT_USER = ? AND ITEM_RENT_YN = 'N' ";
					
					System.out.println("DAO@@="+query);
					System.out.println("DAO@@user="+itemrentuser);
					try {
						pstmt = conn.prepareStatement(query);
						
						pstmt.setString(1 , itemrentuser );
						
						rset = pstmt.executeQuery();
						
						if(rset.next()) {
							cntfin = rset.getInt(1);
						}
							System.out.println("@@@DAOfininsh"+cntfin);
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rset);
						close(pstmt);
					}
					
					return cntfin;
				}
				//1개월
				public List<rent> MypageRentalFinOne(Connection conn, String itemrentuser) {
					List<rent> list = new ArrayList<>();
					
					rent r = null;
					
					PreparedStatement pstmt = null;
					ResultSet rset = null;
					
					String query = " SELECT * FROM ITEM_RENT_EACH IRE JOIN  ITEM I ON IRE.ITEM_NO = I.ITEM_NO  WHERE ITEM_RENT_USER = ? AND ITEM_RENT_YN = 'N' AND (item_rent_start BETWEEN (SYSDATE-30) AND (SYSDATE)) ";
					
					System.out.println("DAO@@="+query);
					System.out.println("DAO@@user="+itemrentuser);
					try {
						pstmt = conn.prepareStatement(query);
						
						pstmt.setString(1 , itemrentuser );
						
						rset = pstmt.executeQuery();
						
						while(rset.next()) {
							r = new rent();
							r.setItemNo(rset.getInt("item_no"));
							r.setItemEachNo(rset.getInt("Item_Each_No"));
							r.setItemRentEnd(rset.getDate("item_rent_end"));
							r.setItemRentStart(rset.getDate("item_rent_start"));
							r.setItemRentUser(rset.getString("item_rent_user"));
							r.setItemRentYN((rset.getString("item_rent_yn")).charAt(0));
							r.setRentOptNo(rset.getString("rent_opt_no"));
							r.setItemBrand(rset.getString("item_brand"));
							r.setItemPrice(rset.getInt("item_price"));
							r.setItemEnrollDate(rset.getDate("item_enroll_date"));
							r.setItemName(rset.getString("item_name"));
							

							
							list.add(r);
						}
							System.out.println("@@@DAO"+list);
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rset);
						close(pstmt);
					}
					
					return list;
				}
				//3개월
				public List<rent> MypageRentalFinThree(Connection conn, String itemrentuser) {
					
					List<rent> list = new ArrayList<>();
					
					rent r = null;
					
					PreparedStatement pstmt = null;
					ResultSet rset = null;
					
					String query = " SELECT * FROM ITEM_RENT_EACH IRE JOIN  ITEM I ON IRE.ITEM_NO = I.ITEM_NO  WHERE ITEM_RENT_USER = ? AND ITEM_RENT_YN = 'N' AND (item_rent_start BETWEEN (SYSDATE-60) AND (SYSDATE)) ";
					
					System.out.println("DAO@@="+query);
					System.out.println("DAO@@user="+itemrentuser);
					try {
						pstmt = conn.prepareStatement(query);
						
						pstmt.setString(1 , itemrentuser );
						
						rset = pstmt.executeQuery();
						
						while(rset.next()) {
							r = new rent();
							r.setItemNo(rset.getInt("item_no"));
							r.setItemEachNo(rset.getInt("Item_Each_No"));
							r.setItemRentEnd(rset.getDate("item_rent_end"));
							r.setItemRentStart(rset.getDate("item_rent_start"));
							r.setItemRentUser(rset.getString("item_rent_user"));
							r.setItemRentYN((rset.getString("item_rent_yn")).charAt(0));
							r.setRentOptNo(rset.getString("rent_opt_no"));
							r.setItemBrand(rset.getString("item_brand"));
							r.setItemPrice(rset.getInt("item_price"));
							r.setItemEnrollDate(rset.getDate("item_enroll_date"));
							r.setItemName(rset.getString("item_name"));
							

							
							list.add(r);
						}
							System.out.println("@@@DAO"+list);
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rset);
						close(pstmt);
					}
					
					return list;
				}
				//6개월
				public List<rent> MypageRentalFinSix(Connection conn, String itemrentuser) {
					List<rent> list = new ArrayList<>();
					
					rent r = null;
					
					PreparedStatement pstmt = null;
					ResultSet rset = null;
					
					String query = " SELECT * FROM ITEM_RENT_EACH IRE JOIN  ITEM I ON IRE.ITEM_NO = I.ITEM_NO  WHERE ITEM_RENT_USER = ? AND ITEM_RENT_YN = 'N' AND (item_rent_start BETWEEN (SYSDATE-180) AND (SYSDATE)) ";
					
					System.out.println("DAO@@="+query);
					System.out.println("DAO@@user="+itemrentuser);
					try {
						pstmt = conn.prepareStatement(query);
						
						pstmt.setString(1 , itemrentuser );
						
						rset = pstmt.executeQuery();
						
						while(rset.next()) {
							r = new rent();
							r.setItemNo(rset.getInt("item_no"));
							r.setItemEachNo(rset.getInt("Item_Each_No"));
							r.setItemRentEnd(rset.getDate("item_rent_end"));
							r.setItemRentStart(rset.getDate("item_rent_start"));
							r.setItemRentUser(rset.getString("item_rent_user"));
							r.setItemRentYN((rset.getString("item_rent_yn")).charAt(0));
							r.setRentOptNo(rset.getString("rent_opt_no"));
							r.setItemBrand(rset.getString("item_brand"));
							r.setItemPrice(rset.getInt("item_price"));
							r.setItemEnrollDate(rset.getDate("item_enroll_date"));
							r.setItemName(rset.getString("item_name"));
							

							
							list.add(r);
						}
							System.out.println("@@@DAO"+list);
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rset);
						close(pstmt);
					}
					
					return list;
				}
				//전체
				public List<rent> MypageRentalFinAll(Connection conn, String itemrentuser) {
					
					List<rent> list = new ArrayList<>();
					
					rent r = null;
					
					PreparedStatement pstmt = null;
					ResultSet rset = null;
					
					String query = " SELECT * FROM ITEM_RENT_EACH IRE JOIN  ITEM I ON IRE.ITEM_NO = I.ITEM_NO WHERE ITEM_RENT_USER = ? AND ITEM_RENT_YN = 'N' ";
					
					System.out.println("DAO@@="+query);
					System.out.println("DAO@@user="+itemrentuser);
					try {
						pstmt = conn.prepareStatement(query);
						
						pstmt.setString(1 , itemrentuser );
						
						rset = pstmt.executeQuery();
						
						while(rset.next()) {
							r = new rent();
							r.setItemNo(rset.getInt("item_no"));
							r.setItemEachNo(rset.getInt("Item_Each_No"));
							r.setItemRentEnd(rset.getDate("item_rent_end"));
							r.setItemRentStart(rset.getDate("item_rent_start"));
							r.setItemRentUser(rset.getString("item_rent_user"));
							r.setItemRentYN((rset.getString("item_rent_yn")).charAt(0));
							r.setRentOptNo(rset.getString("rent_opt_no"));
							r.setItemBrand(rset.getString("item_brand"));
							r.setItemPrice(rset.getInt("item_price"));
							r.setItemEnrollDate(rset.getDate("item_enroll_date"));
							r.setItemName(rset.getString("item_name"));
							

							
							list.add(r);
						}
							System.out.println("@@@DAO"+list);
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rset);
						close(pstmt);
					}
					
					return list;
				}
				//디데이
				public List<Integer> rentingdday(Connection conn, String itemrentuser) {
					
					List<Integer> list = new ArrayList<>();
					
					int r = 0;
					
					PreparedStatement pstmt = null;
					ResultSet rset = null;
					
					String query = " SELECT TRUNC(ITEM_RENT_END - SYSDATE) AS DDAY FROM ITEM_RENT_EACH WHERE ITEM_RENT_USER = ? AND ITEM_RENT_YN = 'Y' ";
					
					System.out.println("DAO@@="+query);
					System.out.println("DAO@@RENT="+itemrentuser);
					try {
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1, itemrentuser);
				
						
						rset = pstmt.executeQuery();
						
						while(rset.next()) {
							r = rset.getInt("DDAY");
							
							

							
							list.add(r);
						}
							System.out.println("@@@DAO44444444444"+list);
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rset);
						close(pstmt);
					}
					
					return list;
				}
				
				
				
		

	}
