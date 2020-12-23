package pet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberSignUpController extends SuperClass {
	private Member bean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		String goToPage = "member/mSignUpForm.jsp";
		super.goToPage(goToPage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bean = new Member();
		bean.setAddress1(request.getParameter("address1"));
		bean.setAddress2(request.getParameter("address2"));
		bean.setName(request.getParameter("name"));
		bean.setEmail(request.getParameter("email"));
		bean.setId(request.getParameter("id"));
		bean.setCreated_at(request.getParameter("created_at"));
		bean.setNickname(request.getParameter("nickname"));
		bean.setPassword(request.getParameter("password"));
		bean.setZipcode(request.getParameter("zipcode"));
		bean.setPhone(request.getParameter("phone"));
		
		
		//bean.setmStatus(Integer.parseInt(request.getParameter("status")));
		if( request.getParameter("status") != null || request.getParameter("status").equals("") == false ){
			bean.setStatus( Integer.parseInt( request.getParameter("status") ));	
			
		}
		//bean.setmlevel(Integer.parseInt(request.getParameter("mlevel")));
		if( request.getParameter("mlevel") != null || request.getParameter("mlevel").equals("") == false ){
			bean.setMlevel( Integer.parseInt( request.getParameter("mlevel") ));	
		}
		//bean.setmpoint(Integer.parseInt(request.getParameter("mpoint")));
		if( request.getParameter("mpoint") != null || request.getParameter("mpoint").equals("") == false ){
			bean.setMpoint( Integer.parseInt( request.getParameter("mpoint") ));	
		}
		
		System.out.println(bean);
		
		String goToPage = "";
		if ( this.validate( request ) == true ) {
			goToPage = "member/mLoginForm.jsp";		
			//DAO 객체를 생성한다.
			MemberDao dao = new MemberDao();		
			int cnt = -99999 ; 			
			//Bean 객체를 이용하여 해당 게시물을 추가한다.
			cnt = dao.InsertData(bean) ;
			System.out.println("ok");
			
			super.session.setAttribute( "message" , "축하합니다. 회원 가입이 되었습니다. 로그인 후 게시물 목록 페이지로 이동하겠습니다." );
			
			// 회원 가입을 성공하면, 바로 로그인되도록 처리해줍니다.
			// doGet() 메소드를 사용하면 안되욧....
			// doPost() 메소드를 호출하여 바로 로그인을 수행합니다.
			new MemberLoginController().doPost(request, response);
		}else{
			goToPage = "member/mSignUpForm.jsp";  
			request.setAttribute("bean", bean);
			super.doPost(request, response);
			super.goToPage( goToPage );
		}		
	
	}

	@Override
	public boolean validate(HttpServletRequest request) {
		boolean isCheck = true ;
		
		if( bean.getId().length() < 4 || bean.getId().length() > 10 ){
			request.setAttribute( super.PREFIX + "id", "아이디는 4자리 이상 10자리 이하이어야 합니다.");
			isCheck = false  ;
		}
		if( bean.getName().length() < 2 || bean.getName().length() > 10 ){
			request.setAttribute( super.PREFIX + "name", "이름은 2자리 이상 10자리 이하이어야 합니다.");
			isCheck = false  ;
		}
		
		if( bean.getNickname().length() < 2 || bean.getNickname().length() > 8 ){
			request.setAttribute( super.PREFIX + "nickname", "닉네임은 2자리 이상 8자리 이하이어야 합니다.");
			isCheck = false  ;
		}
		if( bean.getPassword().length() < 6 || bean.getPassword().length() > 10 ){
			request.setAttribute( super.PREFIX + "password", "비밀 번호는 6자리 이상 10자리 이하이어야 합니다.");
			isCheck = false  ;
		}
		
//		if( bean.getZipcode() == null || bean.getZipcode() == ""){
//			request.setAttribute( super.PREFIX + "zipcode", "우편 번호는 필수 입력 사항입니다.");
//			isCheck = false  ;
//		}
//		if( bean.getaddress1() == null || bean.getaddress1() == "" ){
//			request.setAttribute( super.PREFIX + "address1", "주소는 필수 입력 사항입니다.");
//			isCheck = false  ;
//		}
	
		if( bean.getEmail() == null || bean.getEmail() == "" ){
			request.setAttribute( super.PREFIX + "email", "영문자만 입력 가능합니다.");
			isCheck = false  ;
		}
		
		return isCheck ;
		
	}
	
}
