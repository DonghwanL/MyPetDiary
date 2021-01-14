package pet.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.bean.Orders;
import pet.common.SuperClass;
import pet.dao.OrderDao;
import pet.member.MemberLoginController;
import pet.product.PRListController;

public class CartHistoryController extends SuperClass {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		Member loginfo = (Member) super.session.getAttribute("loginfo");

		int p_id = Integer.parseInt(request.getParameter("p_id"));
		int stock = Integer.parseInt(request.getParameter("stock")); // 재고
		System.out.println("a무엇이 오류인가 ");

		int qty = Integer.parseInt(request.getParameter("qty").trim());

		System.out.println("p_id : " + p_id + "/ " + "stock : " + "/ " + stock + "qty: " + qty);
		if (loginfo == null) { // 미로그인 시
			// session.setAttribute("destination", "redirect:/Order.mall");
			new MemberLoginController().doGet(request, response);
		} else {

			// orderlists : 로그인 된 사람의 이전 쇼핑 내역을 저장하고 있는 컬렉션
			List<Orders> orderlists = new ArrayList<Orders>();

			OrderDao mdao = new OrderDao();

			// lists : 현재 로그인 한 사람의 쇼핑 주문 내역들을 담고 있는 컬렉션(최근 주문 내역이 먼저 나옴)
			List<Orders> lists = mdao.memberOrderList(loginfo.getId());

			request.setAttribute("lists", lists);

			if (lists.size() == 0) {
				request.setAttribute("errmsg", "이전 쇼핑 내역이 존재하지 않습니다.");
			}

			String gotopage = "order/orderList.jsp";
			super.goToPage(gotopage);

			// 5. 세션 영역의 모든 정보를 지우도록 한다.

			super.session.removeAttribute("shoplists"); // 쇼핑 정보 삭제
			super.session.removeAttribute("totalAmount");// 금액 정보 삭제
			super.session.removeAttribute("totalPoint");// 금액 정보 삭제
			super.session.removeAttribute("mycart"); // 카트 반납하기

		}
	}
}