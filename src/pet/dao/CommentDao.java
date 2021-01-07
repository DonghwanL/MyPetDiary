package pet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pet.bean.Board;
import pet.bean.Comment;

public class CommentDao extends SuperDao {
	
	public int modifyComment(Comment bean) {
		String sql = " update comments set ";
		sql += " content = ?, created_at = default "; 
		sql += " where c_no = ? ";    
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = - 999999;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bean.getContent());
			pstmt.setInt(2, bean.getC_no());

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
	
	public Comment getComment(int cno) {
		Comment bean = null;
		
		String sql = " select * from comments ";
		sql += " where c_no = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, cno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean  = new Comment();
				
				bean.setB_no(rs.getInt("b_no"));
				bean.setC_no(rs.getInt("c_no"));
				bean.setContent(rs.getString("content"));
				bean.setCreated_at(String.valueOf(rs.getDate("created_at")));
				bean.setWriter(rs.getString("writer"));
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
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return bean;
	}
	
	public int deleteComments(int no) {
		String sql = " delete from comments where c_no = ? ";
		
		PreparedStatement pstmt = null;
		int cnt = -99999;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
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
	
	public List<Comment> readCommentList(int no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = -99999;
		
		String sql = " select c_no, b_no, writer, content, created_at from comments ";
		sql += " where b_no = ? ";
		sql += " order by c_no asc";
	      
		List<Comment> lists = new ArrayList<Comment>();

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();	
			
			while(rs.next()){
				Comment bean = new Comment();
				
				bean.setB_no(rs.getInt("b_no"));
				bean.setC_no(rs.getInt("c_no"));
				bean.setContent(rs.getString("content"));
				bean.setCreated_at(String.valueOf(rs.getDate("created_at")));
				bean.setWriter(rs.getString("writer"));
				
				lists.add(bean);
			}
			
		} catch (Exception e) {
			SQLException err = (SQLException)e;			
			cnt = - err.getErrorCode();			
			e.printStackTrace();
			
			try {
				conn.rollback(); 
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		} finally{
			try {
				if (rs != null) { rs.close();}
				if (pstmt != null) { pstmt.close();}
				if (conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		
		return lists;
	}
	
	public int addComment(Comment bean) {
		String sql = " insert into comments(c_no, b_no, writer, content, created_at) ";
		sql += " values(comments_seq.nextval, ?, ?, ?, default) ";  
		
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int cnt = -999999;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bean.getB_no());
			pstmt.setString(2, bean.getWriter());
			pstmt.setString(3, bean.getContent());
			
			cnt = pstmt.executeUpdate(); 
			conn.commit(); 
			
			System.out.println("COMMENT 추가 완료");

		} catch (Exception e) {
			SQLException err = (SQLException)e;			
			cnt = - err.getErrorCode();			
			e.printStackTrace();
			
			try {
				conn.rollback(); 
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		} finally{
			try {
				if(pstmt != null){pstmt.close();}
				if(conn != null){conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cnt;
	}

}
