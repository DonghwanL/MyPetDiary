package pet.bean;

public class ShoppingInfo {
	
	// mid 컬럼은 ShoppingInfos 테이블에 저장할 때만 사용
	
	private String id; // 구매자 아이디
	private int p_id; // 상품 번호
	private String name; // 상품 이름
	private int qty; // 구매 수량
	private int price; // 단가
 	private int p_point; // 적립할 포인트
 	private String file_path; // 파일 경로  
	private String file_name; // 파일 이름
 	private String content; //상품 내용 
 	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getP_id() {
		return p_id;
	}
	
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQty() {
		return qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getP_point() {
		return p_point;
	}
	
	public void setP_point(int p_point) {
		this.p_point = p_point;
	}
	
	public String getFile_path() {
		return file_path;
	}
	
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	public String getFile_name() {
		return file_name;
	}
	
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "ShoppingInfo [id=" + id + ", p_id=" + p_id + ", name=" + name + ", qty=" + qty + ", price=" + price
				+ ", p_point=" + p_point + ", file_path=" + file_path + ", file_name=" + file_name + "]";
	}
 }