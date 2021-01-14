package pet.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Orders;
import pet.bean.ShoppingInfo;
import pet.common.SuperClass;
import pet.dao.OrderDao;

public class ORDetailViewController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int o_id = Integer.parseInt(request.getParameter("o_id"));
		
		super.doGet(request, response);
		String gotopage = "";		
		
		if (super.session.getAttribute("loginfo") == null) {		
			gotopage = "member/mLoginForm.jsp";
			super.goToPage(gotopage);
			
		} else {			
			OrderDao odao = new OrderDao(); 
			
			//Order 주문 정보 가져 오기
			List<Orders> orderLists = odao.selectOrderByPK(o_id);
			
			//lists : 해당 송장 번호에 대한 주문 상세 내역
			List<ShoppingInfo> lists = odao.showOrderDetail(o_id);

			request.setAttribute("orders", orderLists); // 주문 정보			
			request.setAttribute("shoplists", lists); // 쇼핑 정보
			
			gotopage = "order/orderDetail.jsp";
			super.goToPage(gotopage);
		}
	}
}
