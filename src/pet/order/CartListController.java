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

public class CartListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.doGet(request, response);
		String message = "";
		
		if (super.session.getAttribute("loginfo") == null) {
			
			new MemberLoginController().doGet(request, response);	
			
		} else {
			MyCartList mycart = (MyCartList)super.session.getAttribute("mycart");
			
			if (mycart == null) {
				mycart = new MyCartList(); // 카트 준비
				super.session.setAttribute("mycart", mycart); 
			}
			
			Map<Integer, Integer> maplists = mycart.GetAllOrderLists();
			
			// keylist : 구매하고자 하는 상품 번호를 저장하고 있는 Set 자료 구조
			Set<Integer> keylist = maplists.keySet();
			
			// ShoppingInfo : 상품 1건에 대한 정보를 저장하고 있는 Bean 객체
			// shoplists : ShoppingInfo 객체들을 저장하고 있는 컬렉션 객체
			List<ShoppingInfo> shoplists = new ArrayList<ShoppingInfo>();
			
			int totalAmount = 0 ; // 총 판매 금액
			int totalPoint = 0 ; // 총 누적 포인트
			
			for (Integer p_id : keylist) { // p_id : 상품 번호
				Integer qty = maplists.get(p_id) ; // 구매 수량
				
				ProductDao pdao = new ProductDao();
				
				// 상품 번호 p_id에 대한 Bean 정보
				Products bean = pdao.selectProductByPK(p_id);
				
				int p_point = bean.getP_point();
				int price = bean.getPrice();
				
				totalAmount += qty * price;
				totalPoint += qty * p_point;
												
				ShoppingInfo shopinfo = new ShoppingInfo();
				
				shopinfo.setFile_path(bean.getFile_path());
				shopinfo.setFile_name(bean.getFile_name());
				shopinfo.setContent(bean.getContent());
				shopinfo.setName(bean.getName()); 
				shopinfo.setP_id(p_id);
				shopinfo.setP_point(p_point); 
				shopinfo.setPrice(price); 
				shopinfo.setQty(qty);  
				
				shoplists.add(shopinfo);
			}
			
			// 이번에 구매할 총 목록
			super.session.setAttribute("shoplists", shoplists);
			super.session.setAttribute("totalAmount", totalAmount);
			super.session.setAttribute("totalPoint", totalPoint);			
		}
		
		String gotopage = "order/cartList.jsp";
		super.goToPage(gotopage);
	}
}
