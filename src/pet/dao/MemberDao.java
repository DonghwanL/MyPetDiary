package pet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pet.bean.Member;

public class MemberDao extends SuperDao {
	
	public int modifyData(Member bean) {
		String sql = " update member set ";
		sql += " nickname = ?, "; 
		sql += " password = ?, "; 
		sql += " email = ?, "; 
		sql += " phone = ?, "; 
		sql += " zipcode = ?, "; 
		sql += " address1 = ?, "; 
		sql += " address2 = ?, "; 
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
				if(pstmt != null){pstmt.close();}
				if(conn != null){conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt;
	}

	public Member selectDataByID(String id) {
		Member bean = null;
		
		String sql = "select * from members where id = ? ";
		
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
			}
			
			System.out.println("MDAO : 사용자 정보 추가 완료");
			
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
	
}
