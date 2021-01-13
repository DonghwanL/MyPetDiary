package pet.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.common.SuperClass;
import pet.product.PRListController;

public class CartAddController extends SuperClass {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		super.doPost(request, response);
		
		String gotopage = "";
		
		if (super.session.getAttribute("loginfo") == null) {
			// 로그인 하지 않았다면 로그인 페이지로 이동
			gotopage = "member/mLoginForm.jsp";
			super.goToPage(gotopage);
			
		} else { // 로그인 상태
			int p_id = Integer.parseInt(request.getParameter("p_id"));
			int stock = Integer.parseInt(request.getParameter("stock")); // 재고
			int qty = Integer.parseInt(request.getParameter("qty")); // 구매 수량
			
			System.out.println(p_id + "/" + stock  + "/" + qty);
			
			if (stock < qty) { // 재고 수량 초과
				String message = "재고 수량이 부족합니다.";
				request.setAttribute("message", message);
				new PRListController().doGet(request, response);
				
			} else { // 재고 있음
				MyCartList mycart = (MyCartList)super.session.getAttribute("mycart");
				
				if (mycart == null) { // 카트가 없을 경우
					mycart = new MyCartList(); // 카트 준비
				}
				
				mycart.AddOrder(p_id, qty); // 카트에 담기
				super.session.setAttribute("mycart", mycart);
				
				// 장바구니 목록 페이지로 이동합니다.
				new CartListController().doGet(request, response);
			}
		}		
	}
}
