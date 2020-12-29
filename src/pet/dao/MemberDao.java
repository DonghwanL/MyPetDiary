package pet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pet.bean.Member;

public class MemberDao extends SuperDao {
	
	// ---------------- Sign-Up Section ----------------
	
	public int memberSignUp(Member bean) {
		String sql = " insert into member(id, name, nickname, password, email, phone, zipcode, address1, address2, animal_type, mpoint, mlevel, created_at, status) "; 
		sql += " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, default, default, default, default) ";
		
		PreparedStatement pstmt = null;
		int cnt = -99999;
		try {
			conn = super.getConnection();
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getNickname());
			pstmt.setString(4, bean.getPassword());
			pstmt.setString(5, bean.getEmail());
			pstmt.setString(6, bean.getPhone());
			pstmt.setString(7, bean.getZipcode());
			pstmt.setString(8, bean.getAddress1());
			pstmt.setString(9, bean.getAddress2());
			pstmt.setString(10, bean.getAnimal_type());
			
			cnt = pstmt.executeUpdate(); 
			conn.commit(); 
			
		} catch (Exception e) {
			SQLException err = (SQLException)e;
			cnt = - err.getErrorCode();			
			e.printStackTrace();
			
			try {
				conn.rollback(); 
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		} finally {
			try {
				if( pstmt != null ) {pstmt.close();}
				if(conn != null) {conn.close();} 
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	
	// ---------------- Login / Out Section ----------------
	
	public Member memberLogin(String id, String password) {
		Member bean = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " select * from member where id = ? and ";
		sql += " password = ? " ;
				
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean = new Member();
				
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setNickname(rs.getString("nickname"));
				bean.setPassword(rs.getString("password"));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setAddress1(rs.getString("address1"));
				bean.setAddress2(rs.getString("address2"));
				bean.setAnimal_type(rs.getString("animal_type"));
				
				String mdate = String.valueOf(rs.getDate("created_at"));
				bean.setCreated_at(mdate);
		
				bean.setMpoint(rs.getInt("mpoint"));
				bean.setMlevel(rs.getInt("mlevel"));
				bean.setStatus(rs.getInt("status"));
				
			}
			
			System.out.println("MDAO : 로그인 정보 입력 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return bean;
	}
	
	public String FindID(String name, String email) {
		String id = null;
		
		String sql = " select * from member where name = ? and ";
		sql += " email = ? " ;
		
		Connection conn = null;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				id = rs.getString("id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return id;
	}
	
	public String FindPassword(String name, String id, String email) {
		String password = null;
		Random rand = new Random();
		
		String sql = " select * from member where name = ? and id = ? and email = ? ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, email);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				password = rs.getString("password");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return password;
	}
	
	
	
	// ---------------- Modify / Delete Section ----------------
	
	public int deleteMember(String id) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				int cnt = -999999;
				Member bean = null;

				try {
					conn = super.getConnection();
					conn.setAutoCommit(false); 
					
					bean = this.selectDataByID(id);
					
					if (conn == null) {conn = super.getConnection();}
					
					// 회원 상태 변경
					String sql = " update member set status = 1 ";
					sql += " where id = ? ";
					pstmt = conn.prepareStatement(sql);
					

					// 회원 탈퇴			
					sql = " delete from member ";
					sql += " where id = ?  "; 
					
					if(pstmt != null) {pstmt.close();}
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, id);		
					
					cnt = pstmt.executeUpdate();
					
					conn.commit(); 

				} catch (Exception e) {
					SQLException err = (SQLException)e;			
					cnt = - err.getErrorCode() ;			
					e.printStackTrace();
					
					try {
						conn.rollback(); 
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				} finally {
					try {
						if(pstmt != null){pstmt.close();}
						if(conn != null){conn.close();}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
				return cnt;
	}
	
	public int modifyMember(Member bean) {
		String sql = " update member set ";
		sql += " nickname = ?, "; 
		sql += " password = ?, "; 
		sql += " email = ?, "; 
		sql += " phone = ?, "; 
		sql += " zipcode = ?, "; 
		sql += " address1 = ?, "; 
		sql += " address2 = ?, "; 
		sql += " animal_type = ? ";
		sql += " where id = ? "; 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = -999999;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			// placeholder
			pstmt.setString(1, bean.getNickname());
			pstmt.setString(2, bean.getPassword());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getPhone());
			pstmt.setString(5, bean.getZipcode());
			pstmt.setString(6, bean.getAddress1());
			pstmt.setString(7, bean.getAddress2());
			pstmt.setString(8, bean.getAnimal_type());
			pstmt.setString(9, bean.getId());
			
			cnt = pstmt.executeUpdate(); 
			conn.commit(); 
			
			System.out.println("MDAO : 회원정보 추가 완료");

		} catch (Exception e) {
			SQLException err = (SQLException)e;			
			cnt = - err.getErrorCode();			
			e.printStackTrace();
			
			try {
				conn.rollback(); 
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		} finally {
			try {
				if(pstmt != null){pstmt.close();}
				if(conn != null){conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt;
	}

	
	// ---------------- List / One Member ID, Nickname Load Section ----------------
	
	
	public Member selectDataByID(String id) {
		Member bean = null;
		
		String sql = "select * from member where id = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 해당 사용자 확인
				bean = new Member();
				
				bean.setAddress1(rs.getString("address1"));
				bean.setAddress2(rs.getString("address2"));
				String created_date = String.valueOf(rs.getDate("created_at"));
				bean.setCreated_at(created_date);
				bean.setEmail(rs.getString("email"));
				bean.setId(rs.getString("id"));
				bean.setMlevel(rs.getInt("mlevel"));
				bean.setMpoint(rs.getInt("mpoint"));
				bean.setName(rs.getString("name"));
				bean.setNickname(rs.getString("nickname"));
				bean.setPassword(rs.getString("password"));
				bean.setPhone(rs.getString("phone"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setAnimal_type(rs.getString("animal_type"));
			}
			
			System.out.println("MDAO : ID_ 사용자 정보 추가 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (pstmt != null) {pstmt.close();}
				if (conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return bean;
	}
	
	public Member selectDataByNick(String nickname) {
		Member bean = null;
		
		String sql = "select * from member where nickname = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nickname);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bean = new Member();
				
				bean.setAddress1(rs.getString("address1"));
				bean.setAddress2(rs.getString("address2"));
				String created_date = String.valueOf(rs.getDate("created_at"));
				bean.setCreated_at(created_date);
				bean.setEmail(rs.getString("email"));
				bean.setId(rs.getString("id"));
				bean.setMlevel(rs.getInt("mlevel"));
				bean.setMpoint(rs.getInt("mpoint"));
				bean.setName(rs.getString("name"));
				bean.setNickname(rs.getString("nickname"));
				bean.setPassword(rs.getString("password"));
				bean.setPhone(rs.getString("phone"));
				bean.setZipcode(rs.getString("zipcode"));
			}
			
			System.out.println("MDAO : 닉네임_ 사용자 정보 추가 완료");
	
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (pstmt != null) {pstmt.close();}
				if (conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return bean;
	}
	
	public List<Member> allMemberList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select id, name, nickname, email, phone, zipcode, address1, address2, animal_type, mpoint, mlevel, created_at, status "; 
		sql += " from member ";
		
		List<Member> lists = new ArrayList<Member>();

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while( rs.next() ){
				Member bean = new Member();

				bean.setAddress1(rs.getString("address1"));
				bean.setAddress2(rs.getString("address2"));
				bean.setAnimal_type(rs.getString("animal_type"));
				String created_date = String.valueOf(rs.getDate("created_at"));
				bean.setCreated_at(created_date);
				bean.setEmail(rs.getString("email"));
				bean.setId(rs.getString("id"));
				bean.setMlevel(rs.getInt("mlevel"));
				bean.setMpoint(rs.getInt("mpoint"));
				bean.setName(rs.getString("name"));
				bean.setNickname(rs.getString("nickname"));
				bean.setPhone(rs.getString("phone"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setStatus(rs.getInt("status"));
				
				lists.add(bean);
			}

		} catch (Exception e) {
			SQLException err = (SQLException)e;				
			e.printStackTrace();

			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} finally {
			try {
				if(rs != null){ rs.close();}
				if(pstmt != null){ pstmt.close();}
				if(conn != null){conn.close();}

			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		
		return lists;
	}




}
