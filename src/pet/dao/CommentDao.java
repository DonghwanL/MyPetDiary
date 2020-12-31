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
	
//	public List<Comment> readCommentList() {
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		int cnt = -99999;
//		String sql = " select no, subject, writer, password, content, readhit, regdate, groupno, orderno, depth, remark  ";
//	      sql += " from ( ";
//	      sql += " select no, subject, writer, password, content, readhit, regdate, groupno, orderno, depth, remark, ";
//	      sql += " rank() over(order by groupno desc, orderno asc) as ranking ";
//	      sql += " from boards ";
//	      
//	      if(mode.equalsIgnoreCase("all") == false) {
//	    	  sql += " where " + mode + " like '" + keyword + "' ";
//	      }
//	      
//	      sql += " ) ";
//	      sql += " where ranking between ? and ? ";
//		
//		List<Board> lists = new ArrayList<Board>();
//
//		try {
//			conn = super.getConnection();
//			pstmt = conn.prepareStatement(sql);
//
//			// placeholder
//			pstmt.setInt(1, beginRow);
//			pstmt.setInt(2, endRow);
//
//			rs = pstmt.executeQuery();	
//			
//			while(rs.next()){
//				Board bean = new Board();
//							 	
//				bean.setContent(rs.getString("content"));
//				bean.setDepth(rs.getInt("depth"));
//				bean.setGroupno(rs.getInt("groupno"));
//				bean.setNo(rs.getInt("no"));
//				bean.setOrderno(rs.getInt("orderno"));
//				bean.setPassword(rs.getString("password"));
//				bean.setReadhit(rs.getInt("readhit"));
//				bean.setRegdate(String.valueOf(rs.getDate("regdate")));
//				bean.setSubject(rs.getString("subject"));
//				bean.setWriter(rs.getString("writer"));
//				bean.setRemark(rs.getString("remark"));
//				
//				lists.add(bean);
//			}
//			
//		} catch (Exception e) {
//			SQLException err = (SQLException)e;			
//			cnt = - err.getErrorCode();			
//			e.printStackTrace();
//			
//			try {
//				conn.rollback(); 
//				
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//			
//		} finally{
//			try {
//				if(rs != null){ rs.close();}
//				if(pstmt != null){ pstmt.close();}
//				if(conn != null){conn.close();}
//				
//			} catch (Exception e2) {
//				e2.printStackTrace(); 
//			}
//		}
//		
//		return lists;
//	}
	
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
