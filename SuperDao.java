package pet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SuperDao {
	protected Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "Mypet";
	private String password = "oracle";
	
	public SuperDao() {
		try {
			Class.forName(this.driver);
			this.conn = this.getConnection();
			
			if (conn != null) {
				System.out.println("DAO 접속 성공");
			} else {
				System.out.println("DAO 접속 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() {
		// 커넥션 객체 생성	
		try {			 
			return DriverManager.getConnection(url, id, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	public void closeConnection() {
		// 커넥션 객체 닫기
		try {
			if(conn != null) {conn.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
