package pet.bean;

public class OrderDetails {
	private int od_id; // 주문 상세 ID
	private int o_id; // 주문 ID
	private int p_num; // 상품 번호
	private int qty; // 주문 수량
	
	public int getOd_id() {
		return od_id;
	}
	public void setOd_id(int od_id) {
		this.od_id = od_id;
	}
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
