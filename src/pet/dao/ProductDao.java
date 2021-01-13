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
				bean.setContent(rs.getString("content"));
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

	public int selectTotalCount_S(String mode, String keyword, String filterType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = " select count(*) as cnt from products where 1 = 1"; 
		
		// 동적쿼리에서 if문을 사용하여 조건을 유동적으로 바꾸고 싶을때 WHERE 1=1 으로 첫 조건을 선언 
		// WHERE 구문에 어떤 조건이 해당될지 불명확할 때 WHERE 조건줄을 1=1로 작성 후 조건에 맞는 if문에 해당되면 조건이 만들어 진다.
		// 전체, 고양이, 강아지를 선택할지 불분명하기 때문에 작성해 주는 구문 
		
		if (mode.equalsIgnoreCase("all") == false) {
			sql += " and " + mode + " like '" + keyword + "'";
		}
		
		if (filterType != null && !filterType.equals("all")) {
			sql += " and p_type = '" + filterType + "'";
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

	public List<Products> filterProductList(int beginRow, int endRow, String filterType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int cnt = -99999;
		
		String sql = " select p_id, p_type, category, name, price, stock, file_path, file_name, content, p_point, sell_counts, discount_rate, created_at ";
		sql += " from ( ";
		sql += " select p_id, p_type, category, name, price, stock, file_path, file_name, content, p_point, sell_counts, discount_rate, created_at,";
		sql += " rank() over(order by p_id desc) as ranking ";
		sql += " from products ";
		
		if (filterType != null && !filterType.equals("all")) {
			sql += " where p_type = ? " ; // 전체, 고양이, 강아지 분류에 해당되는 구문 
		}
		
		sql += " ) ";
		sql += " where ";
		sql += " ranking between ? and ? ";
		
		System.out.println("타입: "+ filterType + " : " +beginRow + " : " + endRow );

		List<Products> lists = new ArrayList<Products>();

		try {
			conn = super.getConnection() ;
			pstmt = conn.prepareStatement(sql) ;

			if(filterType != null && !filterType.equals("all")) {
				pstmt.setString(1, filterType);
				pstmt.setInt(2, beginRow);
				pstmt.setInt(3, endRow);
				
			} else {
				pstmt.setInt(1, beginRow);
				pstmt.setInt(2, endRow);
			}
			
			rs = pstmt.executeQuery();	
			
			while (rs.next()) {
				Products bean = new Products();
				
				bean.setP_id(rs.getInt("p_id"));
				bean.setP_type(rs.getString("p_type"));
				bean.setCategory(rs.getString("category"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getInt("price"));
				bean.setStock(rs.getInt("stock"));
				bean.setFile_path(rs.getString("file_path"));
				bean.setFile_name(rs.getString("file_name"));
				bean.setContent(rs.getString("content"));
				bean.setP_point(rs.getInt("p_point"));
				bean.setSell_counts(rs.getInt("sell_counts"));
				bean.setDiscount_rate(rs.getInt("discount_rate"));
				bean.setCreated_at(String.valueOf(rs.getString("created_at")));

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
			
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) { pstmt.close();}
				if(conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		
		return lists;
	}

	public List<Products> mainProductList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int cnt = -99999;
		
		String sql = " select p_id, p_type, category, name, price, stock, file_path, file_name, content, p_point, sell_counts, discount_rate, created_at ";
		sql += " from ( ";
		sql += " select p_id, p_type, category, name, price, stock, file_path, file_name, content, p_point, sell_counts, discount_rate, created_at,";
		sql += " rank() over(order by p_id desc) as ranking ";
		sql += " from products ";
		sql += " where category = '특가' ";
		sql += " ) ";

		List<Products> lists = new ArrayList<Products>();

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
	
			rs = pstmt.executeQuery();	
			
			while (rs.next()) {
				Products bean = new Products();
				
				bean.setP_id(rs.getInt("p_id"));
				bean.setP_type(rs.getString("p_type"));
				bean.setCategory(rs.getString("category"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getInt("price"));
				bean.setStock(rs.getInt("stock"));
				bean.setFile_path(rs.getString("file_path"));
				bean.setFile_name(rs.getString("file_name"));
				bean.setContent(rs.getString("content"));
				bean.setP_point(rs.getInt("p_point"));
				bean.setSell_counts(rs.getInt("sell_counts"));
				bean.setDiscount_rate(rs.getInt("discount_rate"));
				bean.setCreated_at(String.valueOf(rs.getString("created_at")));

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
			
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) { pstmt.close();}
				if(conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		
		return lists;
	}
}
