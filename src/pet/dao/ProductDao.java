package pet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pet.bean.Products;

public class ProductDao extends SuperDao {

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
