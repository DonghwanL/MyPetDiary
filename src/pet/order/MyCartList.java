package pet.order;

import java.util.HashMap;
import java.util.Map;

// 나의 카트를 관리해주는 클래스 (카트 관리자)

public class MyCartList {
	// orderlists : 카트에 담은 정보를 저장하고 있는 맵 컬렉션
	// key는 상품 번호(기본 키), value는 구매 수량
	
	private Map<Integer, Integer> orderlists = null;
	
	public MyCartList() {
		this.orderlists = new HashMap<Integer, Integer>();
	}
	
	// 장바구니 내역을 모두 삭제
	// 주로 결재가 이루어질 때 사용
	public void RemoveAllProductInfo() {
		this.orderlists.clear();
	}
	
	// 장바구니 내역 정보를 반환
	public Map<Integer, Integer> GetAllOrderLists(){
		return this.orderlists;
	}
	
	// 장바구니 내역 정보를 수정
	public void EditOrder(int p_id, int stock) {
		// p_id는 수정될 상품 번호, stock은 수정할 수량
		this.AddOrder(p_id, stock);
	}
	
	// 장바구니에 들어 있는 해당 상품을 삭제
	public void DeleteOrder(int p_id) {
		// p_id는 삭제될 상품 번호
		this.orderlists.remove(p_id);
	}
	
	// 장바구니에 상품을 추가
	public void AddOrder(int p_id, int stock) {
		if (this.orderlists.containsKey(p_id)) { // 동일 상품이 이미 있는 경우
			int newstock = this.orderlists.get(p_id) + stock; 
			this.orderlists.put(p_id, newstock);
			
		} else {
			this.orderlists.put(p_id, stock);
		}
	}
}