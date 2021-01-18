package pet.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Products;
import pet.bean.ShoppingInfo;
import pet.common.SuperClass;
import pet.dao.ProductDao;
import pet.member.MemberLoginController;
import pet.product.PRListController;

 
public class CartInstantBuyController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String gotopage = "";
		
		if (super.session.getAttribute("loginfo") == null) {
			// 로그인 하지 않았다면 로그인 페이지로 이동
			gotopage = "member/mLoginForm.jsp";
			super.goToPage(gotopage);
			
		} else { // 로그인 상태
			int p_id = Integer.parseInt(request.getParameter("p_id"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			int qty = Integer.parseInt(request.getParameter("qty"));
			
			System.out.println(p_id + " / " + stock + "/ " + qty);
			
			if (stock < qty) { // 재고 수량 초과
				String message = "재고 수량이 부족합니다.";
				request.setAttribute("message", message);
				new PRListController().doGet(request, response);
			
			} else { // 재고 수량 충족
				MyCartList mycart = (MyCartList)super.session.getAttribute("mycart");
				
				if (mycart == null) { // 카트가 없으면
					mycart = new MyCartList(); // 매장 입구에서 카트 준비
				}
				
				mycart.AddOrder(p_id, qty); // 카트에 담기
				super.session.setAttribute("mycart", mycart);
				
				// 카트에 담은 상품목록을 결제
				
				Map<Integer, Integer> maplists = mycart.GetAllOrderLists();
				
				// keylist : 구매하고자 하는 상품 번호를 저장하고 있는 Set 자료 구조
				Set<Integer> keylist = maplists.keySet();
				
				// ShoppingInfo : 상품 1건에 대한 정보를 저장하고 있는 Bean 객체
				// shoplists : ShoppingInfo 객체들을 저장하고 있는 컬렉션 객체
				List<ShoppingInfo> shoplists = new ArrayList<ShoppingInfo>();
				
				int totalAmount = 0; // 총 판매 금액
				int totalPoint = 0; // 총 누적 포인트
				
				for (Integer pid : keylist ){ // p_id : 상품 번호
					Integer p_qty = maplists.get(pid); // 구매 수량
					
					ProductDao pdao = new ProductDao();
					
					// 상품 번호 p_id에 대한 Bean 정보
					Products bean = pdao.selectProductByPK(pid);
					
					int p_point = bean.getP_point();
					int price = bean.getPrice();
					
					totalAmount += qty * price;
					totalPoint += qty * p_point;
													
					ShoppingInfo shopinfo = new ShoppingInfo();
					
					shopinfo.setFile_path(bean.getFile_path());
					shopinfo.setFile_name(bean.getFile_name());
					shopinfo.setContent(bean.getContent());
					shopinfo.setName(bean.getName()); 
					shopinfo.setP_id(pid);
					shopinfo.setP_point(p_point); 
					shopinfo.setPrice(price); 
					shopinfo.setQty(p_qty);  
					
					shoplists.add(shopinfo);
			}
				
				super.session.setAttribute("shoplists", shoplists);
				super.session.setAttribute("totalAmount", totalAmount);
				super.session.setAttribute("totalPoint", totalPoint);		
		
			new PRCalculateController().doGet(request, response);
		}
	
		}
	}
}