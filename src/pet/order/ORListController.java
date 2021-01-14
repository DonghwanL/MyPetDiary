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

public class ORListController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		Member loginfo = (Member)super.session.getAttribute("loginfo");
		
		if (loginfo == null) {  // 미로그인 시
			new MemberLoginController().doGet(request, response);  
			
		} else { 
			
			//orderlists : 로그인 된 사람의 이전 쇼핑 내역을 저장하고 있는 컬렉션
			List<Orders> orderlists = new ArrayList<Orders>();

			OrderDao odao = new OrderDao();

			// lists : 현재 로그인 한 사람의 쇼핑 주문 내역들을 담고 있는 컬렉션 (최근 주문 내역이 먼저 나옴)
			List<Orders> lists = odao.memberOrderList(loginfo.getId());

			// Header에서 주문조회를 바로 가기 위해 session에 저장
			super.session.setAttribute("listsForMem", lists);

			if (lists.size() == 0) {
				request.setAttribute("errmsg", "이전 쇼핑 내역이 존재하지 않습니다.");	
			}

			String gotopage = "order/orderList.jsp";  
			super.goToPage(gotopage);
		}			
	}
}
