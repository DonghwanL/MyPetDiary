package pet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pet.bean.Board;
import pet.bean.Comment;

public class BoardDao extends SuperDao {
	
	public int selectPostCount(String writer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = -999999;
		
		String sql = " select  count(*) as cnt from boards ";
		sql += " where writer = ? ";
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, writer);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				cnt = rs.getInt("cnt");
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
			
		} finally {
			
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	public int imageModify(Board bean) {

		String sql = " update boards set ";
		sql += " title = ?, content = ?, board_type = ?, file_path = ?, file_name = ? "; 
		sql += " where no = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = - 999999;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getContent());
			pstmt.setString(3, bean.getBoard_type());
			pstmt.setString(4, bean.getFile_path());
			pstmt.setString(5, bean.getFile_name());
			pstmt.setInt(6, bean.getNo());
			
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
	
	public int imagePost(Board bean) {
	
		String sql = " insert into boards ";
			sql += " ( ";
			sql += " no, board_type, writer, title, content, file_path, file_name";
			sql += " ) ";
			sql += " values ";
			sql += " ( ";
			sql += " petboard.nextval, ?, ?, ?, ?, ?, ?";
			sql += " ) ";


		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  bean.getBoard_type());
			pstmt.setString(2,  bean.getWriter());
			pstmt.setString(3,  bean.getTitle());
			pstmt.setString(4, bean.getContent());
			pstmt.setString(5,  bean.getFile_path());
			pstmt.setString(6,  bean.getFile_name());

			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int addReply(Board bean, int groupno, int orderno) {
		// 1. 답글을 추가
		// 2. 동일 그룹의 orderno 컬럼 갱신
				
		String sql = " insert into boards  ";
		sql += " ( "; 
		sql += " no, board_type, writer, title, content, groupno, orderno, depth, reads_count, created_at ";  
		sql += " ) ";  
		sql += " values ";  
		sql += " ( ";  
		sql += " petboard.nextval, ?, ?, ?, ?, ?, ?, ?, default, default ";  
		sql += " ) ";  
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = - 999999;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bean.getBoard_type());
			pstmt.setString(2, bean.getWriter());
			pstmt.setString(3, bean.getTitle());
			pstmt.setString(4, bean.getContent());
			pstmt.setInt(5, bean.getGroup_no());
			pstmt.setInt(6, bean.getOrder_no());
			pstmt.setInt(7, bean.getDepth());

			cnt = pstmt.executeUpdate(); 

			// 동일 그룹의 orderno 컬럼을 갱신

			sql = " update boards set orderno = orderno + 1 ";
			sql += " where groupno = ? and orderno > ? ";

			pstmt = null;
			pstmt = conn.prepareStatement(sql);

			cnt = -99999;

			pstmt.setInt(1, groupno);
			pstmt.setInt(2, orderno);

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
	
	public int modifyPost(Board bean) {
		
		String sql = " update boards set ";
		sql += " title = ?, content = ?, board_type = ? "; 
		sql += " where no = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = - 999999;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getContent());
			pstmt.setString(3, bean.getBoard_type());
			pstmt.setInt(4, bean.getNo());
			
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
	
	public int writePost(Board bean) {
		String sql = " insert into boards(no, board_type, writer, title, content, groupno, orderno, ";
		sql += " depth, reads_count, created_at) ";  
		sql += " values(petboard.nextval, ?, ?, ?, ?, petboard.currval, default, default, default, default) ";  
		
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int cnt = -999999;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getBoard_type());
			pstmt.setString(2, bean.getWriter());
			pstmt.setString(3, bean.getTitle());
			pstmt.setString(4, bean.getContent());
			
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
	
	public Board selectLike(int no) {
		String sql = "select likes_count from boards where no = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board bean = null;
		int cnt = -99999;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);
			
			rs= pstmt.executeQuery();
			
			if (rs.next()) {
				bean = new Board();
				
				bean.setLikes_count(rs.getInt("likes_count"));
			}
			
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
		
		return bean;
	}
	
	public int updateLikes(int no) {
		String sql = "update boards set likes_count = likes_count + 1 where no = ?";
		
		Connection conn = null;
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
	
	public int deletePost(int no) {
		String sql = " delete from boards where no = ? ";
		
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
	
	public int updateReadsCount(int no) {
		String sql = " update boards set reads_count = reads_count + 1 ";
		sql += " where no = ? ";    
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = - 999999;

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
	
	public Board selectDataByPK(int no) {
		Board bean = null;
		
		String sql = " select * from boards ";
		sql += " where no = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new Board();
				
				bean.setBoard_type(rs.getString("board_type"));
				bean.setContent(rs.getString("content"));
				bean.setCreated_at(String.valueOf(rs.getDate("created_at")));
				bean.setDepth(rs.getInt("depth"));
				bean.setGroup_no(rs.getInt("groupno"));
				bean.setLikes_count(rs.getInt("likes_count"));
				bean.setNo(rs.getInt("no"));
				bean.setOrder_no(rs.getInt("orderno"));
				bean.setReads_count(rs.getInt("reads_count"));
				bean.setTitle(rs.getString("title"));
				bean.setWriter(rs.getString("writer"));
				bean.setFile_path(rs.getString("file_path"));
				bean.setFile_name(rs.getString("file_name"));
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
	
	public int selectTotalCount_I(String mode, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = "select count(*) as cnt from boards where board_type = '사진' "; 
		
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
	
	public int selectTotalCount_F(String mode, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = "select count(*) as cnt from boards where board_type = '자유' "; 
		
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
	
	public int selectTotalCount_Q(String mode, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = "select count(*) as cnt from boards where board_type = '문의' "; 
		
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

	public List<Board> readBoardList_Q(int beginRow, int endRow, String mode, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = -99999;
		
		String sql = " select no, board_type, writer, title, content, groupno, orderno, depth, reads_count, likes_count, ";
		  sql += " created_at ";
	      sql += " from ( ";
	      sql += " select no, board_type, writer, title, content, groupno, orderno, depth, reads_count, likes_count, created_at, ";
	      sql += " rank() over(order by groupno desc, orderno asc) as ranking ";
	      sql += " from boards where board_type = '문의' ";
	      
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
				bean.setContent(rs.getString("content"));
				bean.setCreated_at(String.valueOf(rs.getDate("created_at")));
				bean.setDepth(rs.getInt("depth"));
				bean.setGroup_no(rs.getInt("groupno"));
				bean.setLikes_count(rs.getInt("likes_count"));
				bean.setNo(rs.getInt("no"));
				bean.setOrder_no(rs.getInt("orderno"));
				bean.setReads_count(rs.getInt("reads_count"));
				bean.setTitle(rs.getString("title"));
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
	
	public List<Board> readBoardList_I(int beginRow, int endRow, String mode, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = -99999;
		
		String sql = " select no, board_type, writer, title, content, file_path, file_name, groupno, orderno, depth, reads_count, likes_count, ";
		  sql += " created_at ";
	      sql += " from ( ";
	      sql += " select no, board_type, writer, title, content, file_path, file_name, groupno, orderno, depth, reads_count, likes_count, created_at, ";
	      sql += " rank() over(order by no desc) as ranking ";
	      sql += " from boards where board_type = '사진' ";
	      
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
				bean.setContent(rs.getString("content"));
				bean.setCreated_at(String.valueOf(rs.getDate("created_at")));
				bean.setLikes_count(rs.getInt("likes_count"));
				bean.setNo(rs.getInt("no"));
				bean.setReads_count(rs.getInt("reads_count"));
				bean.setTitle(rs.getString("title"));
				bean.setWriter(rs.getString("writer"));
				bean.setFile_path(rs.getString("file_path"));
				bean.setFile_name(rs.getString("file_name"));
				
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

	public List<Board> readBoardList_F(int beginRow, int endRow, String mode, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = -99999;
		
		String sql = " select no, board_type, writer, title, content, file_path, file_name, groupno, orderno, depth, reads_count, likes_count, ";
		  sql += " created_at ";
	      sql += " from ( ";
	      sql += " select no, board_type, writer, title, content, file_path, file_name, groupno, orderno, depth, reads_count, likes_count, created_at, ";
	      sql += " rank() over(order by groupno desc, orderno asc) as ranking ";
	      sql += " from boards where board_type = '자유' ";
	      
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
				bean.setContent(rs.getString("content"));
				bean.setCreated_at(String.valueOf(rs.getDate("created_at")));
				bean.setLikes_count(rs.getInt("likes_count"));
				bean.setNo(rs.getInt("no"));
				bean.setReads_count(rs.getInt("reads_count"));
				bean.setTitle(rs.getString("title"));
				bean.setWriter(rs.getString("writer"));
				bean.setFile_path(rs.getString("file_path"));
				bean.setFile_name(rs.getString("file_name"));
				bean.setDepth(rs.getInt("depth"));
				
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
