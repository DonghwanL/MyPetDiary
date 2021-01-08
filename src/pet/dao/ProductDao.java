package pet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pet.bean.Products;

public class ProductDao extends SuperDao {
	
	public int modifyProduct(Products bean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update products set ";
		sql += " p_type = ?, category = ?, file_name = ?, file_path = ?, content = ?, name = ?, ";
		sql += " stock = ?, price = ?, p_point = ? ";
		sql += " where p_id  = ? ";
		
		int cnt = -999999;
		
		try {
			conn = super.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getP_type());
			pstmt.setString(2, bean.getCategory());
			pstmt.setString(3, bean.getFile_name());
			pstmt.setString(4, bean.getFile_path());
			pstmt.setString(5, bean.getContent());
			pstmt.setString(6, bean.getName());
			pstmt.setInt(7, bean.getStock());
			pstmt.setInt(8, bean.getPrice());
			pstmt.setInt(9, bean.getP_point());			
			pstmt.setInt(10, bean.getP_id());
			
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
	
	public int addProduct(Products bean) {
		String sql = "insert into products(p_id, p_type, category, name, price, stock, file_path, file_name, p_point, sell_counts, discount_rate, ";
		sql += " created_at, content ) ";
		sql += " values(product_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, default, default, default, ?) ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = -999999;
		
		try {
			conn = super.getConnection();
			conn.setAutoCommit( false );
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getP_type());
			pstmt.setString(2, bean.getCategory());
			pstmt.setString(3, bean.getName());
			pstmt.setInt(4, bean.getPrice());
			pstmt.setInt(5, bean.getStock());
			pstmt.setString(6, bean.getFile_path());
			pstmt.setString(7, bean.getFile_name());
			pstmt.setInt(8, bean.getP_point());
			pstmt.setString(9, bean.getContent());
			
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
				if( pstmt != null ) { pstmt.close();}
				if(conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cnt;
	}

	public int delectProduct(int p_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = - 999999;

		try {
			conn = super.getConnection();
			
			// 해당 상품 번호에 대한 orderdetails.remark 컬럼 수정
			String sql = " update order_details set remark = ? "; 
			sql += " where p_num = ? ";
			
			Products bean = this.selectProductByPK(p_id);

			pstmt = conn.prepareStatement(sql);
			
			String temp = "삭제된 상품 : " + bean.getName();
					
			pstmt.setString(1, temp); // 저장하고자 하는 문자열
			pstmt.setInt(2, p_id); // 상품 번호
			
			cnt = pstmt.executeUpdate(); 
			pstmt.close();
			
			// 해당 상품을 삭제
			sql = " delete from products ";
			sql += " where p_id = ? "; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, p_id); // 상품 번호
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

	public Products selectProductByPK(int p_id) {
		Products bean = null;
		
		String sql = "select * ";
		sql += " from products "; 
		sql += " where p_id = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, p_id); 
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new Products();
				
				bean.setCategory(rs.getString("category"));
				bean.setCreated_at(String.valueOf(rs.getDate("created_at")));
				bean.setFile_name(rs.getString("file_name"));
				bean.setFile_path(rs.getString("file_path"));
				bean.setName(rs.getString("name"));
				bean.setP_id(rs.getInt("p_id"));
				bean.setP_point(rs.getInt("p_point"));
				bean.setP_type(rs.getString("p_type"));
				bean.setPrice(rs.getInt("price"));
				bean.setSell_counts(rs.getInt("sell_counts"));
				bean.setStock(rs.getInt("stock"));
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

	public int selectTotalCount(String mode, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = " select count(*) as cnt from products ";
		
		if (mode.equalsIgnoreCase("all") == false) {
			sql += " where " + mode + " like '" + keyword + "'";
		}
		
		int cnt = - 999999;

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

		} finally {
			try {
				if( rs != null){ rs.close(); } 
				if( pstmt != null){ pstmt.close();} 
				if(conn != null){conn.close();}

			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		} 	
	
		return cnt; 
	}

	public List<Products> adminProductList(int beginRow, int endRow, String mode, String keyword) {
		// 상품 목록을 조회
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select p_id, p_type, category, name, price, stock, file_path, file_name, p_point, sell_counts, discount_rate, ";
		sql += " created_at from ";
		sql += " ( ";
		sql += " select p_id, p_type, category, name, price, stock, file_path, file_name, p_point, sell_counts, discount_rate, created_at, ";
		sql += " rank() over(order by p_id desc) as ranking ";
		sql += " from products ";

		if(mode.equalsIgnoreCase("all") == false) {
			sql += " where " + mode + " like '" + keyword + "' ";
		}

		sql += " ) " ;
		sql += " where ranking between ? and ? " ; 

		List<Products> lists = new ArrayList<Products>();

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);

			// placeholder
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				Products bean = new Products();
				
				bean.setP_type(rs.getString("p_type"));
				bean.setCategory(rs.getString("category"));
				bean.setName(rs.getString("name"));
				bean.setFile_path(rs.getString("file_path"));
				bean.setFile_name(rs.getString("file_name"));

				// 날짜 형식
				bean.setCreated_at(String.valueOf(rs.getDate("created_at")));

				// 숫자 형식
				bean.setP_id(rs.getInt("p_id"));
				bean.setP_point(rs.getInt("p_point"));
				bean.setPrice(rs.getInt("price"));
				bean.setStock(rs.getInt("stock"));

				lists.add(bean);
			}

		} catch (Exception e) {
			SQLException err = (SQLException)e;			
			int cnt = - err.getErrorCode();			
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
