package pet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pet.bean.Member;
import pet.bean.Orders;
import pet.bean.ShoppingInfo;

public class OrderDao extends SuperDao {
	
	public List<ShoppingInfo> showOrderDetail(int o_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select p.p_id pp_id, p.name ppname, od.qty, p.price, p.p_point, p.file_name, p.file_path "; 
		sql += " from ( orders o inner join order_details od  ";
		sql += " on o.o_id=od.o_id ) inner join products p ";
		sql += " on od.p_num = p.p_id and o.o_id = ? "; 
		sql += " order by od.od_id desc ";
		
		List<ShoppingInfo> lists = new ArrayList<ShoppingInfo>();
		
		try {
			conn = super.getConnection();	
			pstmt = super.conn.prepareStatement(sql);			
			pstmt.setInt(1, o_id); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ShoppingInfo bean = new ShoppingInfo();
				
				bean.setFile_name(rs.getString("file_name"));
				bean.setFile_path(rs.getString("file_path"));
				bean.setP_id(Integer.parseInt(rs.getString("pp_id")));
				bean.setName(rs.getString("ppname"));
				bean.setP_point(Integer.parseInt(rs.getString("p_point")));
				bean.setPrice(Integer.parseInt(rs.getString("price") ));
				bean.setQty(Integer.parseInt(rs.getString("qty") ));		
				 
				lists.add(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally{
			
			try {
				if (rs != null) {rs.close();}
				if (pstmt != null) {pstmt.close();}
				if (conn != null) {conn.close();}
				
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}		
		
		return lists;
	}	
	
	public List<Orders> selectOrderByPK(int o_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		
		String sql = "select * from orders ";  
		sql += " where o_id = ? ";
	
		List<Orders> lists = new ArrayList<Orders>();
		
		try {
			conn = super.getConnection();		
			pstmt = this.conn.prepareStatement(sql);			
			pstmt.setInt(1, o_id); 
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Orders bean = new Orders(); 
				
				bean.setM_id(rs.getString("m_id"));
				bean.setO_id(rs.getInt("o_id"));
				bean.setOrder_date(String.valueOf(rs.getDate("order_date"))); 	
				bean.setAddress1(rs.getString("address1"));
				bean.setAddress2(rs.getString("address2"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setPhone(rs.getString("phone"));
				bean.setName(rs.getString("name"));
				
				lists.add(bean);
			}
			
		} catch (SQLException e) {			
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
		
		return lists;
	}
	
	public List<Orders> memberOrderList(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		
		String sql = " select * from orders ";
		sql += " where m_id = ? order by order_date desc ";
		
		List<Orders> lists = new ArrayList<Orders>();
		
		try {
			conn = super.getConnection();	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString( 1, id ); 
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Orders bean = new Orders();
				
				bean.setM_id(rs.getString("m_id"));				
				bean.setO_id(rs.getInt("o_id"));		
				bean.setName(rs.getString("name"));
				bean.setPhone(rs.getString("phone"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setAddress1(rs.getString("address1"));
				bean.setAddress2(rs.getString("address2"));
				
				bean.setOrder_date(String.valueOf(rs.getDate("order_date")));
				
				lists.add(bean); 
			}
			
		} catch (SQLException e) {			
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
		
		return lists;
	}

	public void calculatePR(Member mem, Map<Integer, Integer> maplists, int totalPoint) {
		// mem 객체는 고객 정보이고, maplists 객체는 구매한 상품 리스트
		// totalPoint는 회원에게 적립할 마일리지 포인트 금액
		
		// 고객의 장바구니에 대한 결재를 진행
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		int cnt = -999999;
		int maxnum = -999999; // 방금 추가된 송장 번호
		
		try {
			conn = super.getConnection();
			conn.setAutoCommit(false);
			
			// 1. 주문(orders) 테이블에 추가
			sql = " insert into orders(o_id, m_id, name, phone, zipcode, address1, address2, order_date) ";
			sql += " values(order_seq.nextval, ?, ?, ?, ?, ?, ?, sysdate) "; 			
			pstmt = conn.prepareStatement(sql);		
			
			pstmt.setString(1, mem.getId()); // 구매자			
			pstmt.setString(2, mem.getName()); // 이름
			pstmt.setString(3, mem.getPhone());// 번호 
			pstmt.setString(4, mem.getZipcode());// 우편번호 
			pstmt.setString(5, mem.getAddress1());// 주소 
			pstmt.setString(6, mem.getAddress2());// 주소 
		
			cnt = pstmt.executeUpdate();
			if (pstmt !=null) {pstmt.close();}
			
			// 2. 방금 추가된 송장 번호 읽기
			sql = " select max(o_id) as maxnum from orders ";
			
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				maxnum = rs.getInt("maxnum"); 
			} 
			
			if (pstmt !=null) {pstmt.close();}
			
			System.out.println("신규 송장 번호 : " + maxnum); 		
			
			Set<Integer> keylist = maplists.keySet();
			System.out.println("상품 개수 : " + keylist.size()); 
			

			for(Integer p_num : keylist){
				// 3. 반복문을 사용하여 주문 상세(order_details) 테이블에 추가
				// orders의 o_id와 order_details의 o_id는 동일한 값
				// 송장 번호가 참조 무결성 제약 조건에 의하여 연결
				
				sql = " insert into order_details(od_id, o_id, p_num, qty, remark) ";
				sql += " values(orderd_seq.nextval, ?, ?, ?, ?) ";			
				
				pstmt = conn.prepareStatement(sql);	
				int qty = maplists.get(p_num);		
				
				pstmt.setInt(1, maxnum); // 신규로 생성된 송장 번호 
				pstmt.setInt(2, p_num); // 해당 상품 번호
				pstmt.setInt(3, qty); // 구매한 수량
				pstmt.setString(4, " "); // 비고
				
				cnt = pstmt.executeUpdate();
				
				if (pstmt !=null) {pstmt.close();}
				
				// 4. 해당 상품 번호(p_num)를 이용하여 재고 수량(stock)을 감소
				sql = " update products set stock = stock - ? ";				
				sql += " where p_id = ? ";	
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, qty);				
				pstmt.setInt(2, p_num);		
				
				cnt = pstmt.executeUpdate();
				
				if (pstmt !=null) {pstmt.close();}
			}

			// 5. 구매자에 대한 마일리지 적립 포인트를 누적			
			sql = " update member set mpoint = mpoint + ? ";
			sql += " where id = ? "; 			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, totalPoint);			
			pstmt.setString(2, mem.getId());	
			
			cnt = pstmt.executeUpdate();
			
			if (pstmt !=null) {pstmt.close();}
			
			conn.commit(); 
			
			System.out.println("상품 계산 완료");
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
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
		
	}

}
