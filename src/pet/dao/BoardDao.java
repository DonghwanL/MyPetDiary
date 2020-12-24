package pet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pet.bean.Board;

public class BoardDao extends SuperDao {

	public int selectTotalCount(String mode, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = "select count(*) as cnt from boards "; 
		
		if (mode.equalsIgnoreCase("all") == false) {
			sql += " where " + mode + " like '" + keyword + "' ";
		}
		
		int cnt = -999999;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); 
			
			if (rs.next()) { 
				cnt = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {			
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
				if( rs != null){ rs.close();} 
				if( pstmt != null){ pstmt.close();} 
				if(conn != null){conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		} 	
		return cnt; 
	}

	public List<Board> readBoardList(int beginRow, int endRow, String mode, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = -99999;
		
		String sql = " select no, board_type, writer, title, content, groupno, orderno, depth, reads_count, likes_count, comments_count, ";
		  sql += " created_at, updated_at, deleted_at ";
	      sql += " from ( ";
	      sql += " select no, board_type, writer, title, content, groupno, orderno, depth, reads_count, likes_count, comments_count, ";
	      sql += " created_at, updated_at, deleted_at, ";
	      sql += " rank() over(order by groupno desc, orderno asc) as ranking ";
	      sql += " from boards ";
	      
	      if (mode.equalsIgnoreCase("all") == false) {
	    	  sql += " where " + mode + " like '" + keyword + "' ";
	      }
	      
	      sql += " ) ";
	      sql += " where ranking between ? and ? ";
		
		List<Board> lists = new ArrayList<Board>();

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Board bean = new Board();
							 	
				bean.setBoard_type(rs.getString("board_type"));
				bean.setComments_count(rs.getInt("comments_count"));
				bean.setContent(rs.getString("content"));
				bean.setCreated_at(String.valueOf(rs.getDate("created_at")));
				bean.setDeleted_at(String.valueOf(rs.getDate("deleted_at")));
				bean.setDepth(rs.getInt("depth"));
				bean.setGroup_no(rs.getInt("groupno"));
				bean.setLikes_count(rs.getInt("likes_count"));
				bean.setNo(rs.getInt("no"));
				bean.setOrder_no(rs.getInt("orderno"));
				bean.setReads_count(rs.getInt("reads_count"));
				bean.setTitle(rs.getString("title"));
				bean.setUpdated_at(String.valueOf(rs.getString("updated_at")));
				bean.setWriter(rs.getString("writer"));
				
				lists.add(bean);
				
				System.out.println("BDAO : 게시물 리스트 추가 완료");
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
