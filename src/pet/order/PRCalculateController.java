package pet.order;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.OrderDao;
import pet.product.PRListController;

public class PRCalculateController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		System.out.println("장바구니 내역을 이용하여 계산을 합니다.");
		
		MyCartList mycart = (MyCartList)super.session.getAttribute("mycart");
		
		OrderDao dao = new OrderDao();		
		
		if (mycart != null) {
			System.out.println("maplists : 내가 구매한 상품들의 번호와 수량에 대한 컬렉션");
			Map<Integer, Integer> maplists = mycart.GetAllOrderLists();

			int totalPoint = (Integer)super.session.getAttribute("totalPoint"); 
			
			// mem : 계산을 수행하는 당사자
			Member mem = (Member)super.session.getAttribute("loginfo");
			
			// Calculate() 메소드는 계산 로직을 수행해주는 메소드
			dao.calculatePR(mem, maplists, totalPoint);
		}
		
		new ORListController().doGet(request, response);
		
		new CartHistoryController().doGet(request, response);
	}	
}
