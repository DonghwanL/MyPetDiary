package pet.bean;

public class Products {
	private int p_id; // 상품 ID
	private String p_type; // 상품 타입 (고양이, 강아지)
	private String category; // 상품 카테고리 (사료, 간식, 기타)
	private String name; // 상품명
	private int price; // 가격
	private int stock; // 재고량
	private String file_path; // 파일 경로  
	private String file_name; // 파일 이름
	private String content; // 상세 설명
	private int p_point; // 상품 포인트
	private int sell_counts; // 판매 수량
	private int discount_rate; // 할인율
	private String created_at; // 등록 일자
	
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
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
	public int getP_point() {
		return p_point;
	}
	public void setP_point(int p_point) {
		this.p_point = p_point;
	}
	public int getSell_counts() {
		return sell_counts;
	}
	public void setSell_counts(int sell_counts) {
		this.sell_counts = sell_counts;
	}
	public int getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
