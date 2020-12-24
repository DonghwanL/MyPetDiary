package pet.member;

import java.io.IOException;
import java.util.regex.Pattern;

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
		bean.setAnimal_type(request.getParameter("animal_type"));
		
		System.out.println("MDAO : 회원가입 정보 추가 완료");
		
		String goToPage = "";
		
		if (this.validate(request) == true) {
			goToPage = "member/mLoginForm.jsp";		
			
			MemberDao mdao = new MemberDao();		
			int cnt = -99999; 			

			cnt = mdao.memberSignUp(bean);

			System.out.println("MDAO : 회원가입 유효성 검사 완료");
			
			super.session.setAttribute("message", "회원 가입이 완료 되었습니다");
			
			new MemberLoginController().doPost(request, response);
			
		} else {
			goToPage = "member/mSignUpForm.jsp";  
			request.setAttribute("bean", bean);
			super.doPost(request, response);
			super.goToPage(goToPage);
		}		
	}

	@Override
	public boolean validate(HttpServletRequest request) {
		boolean isCheck = true;
		
		if (bean.getId().length() < 5 || bean.getId().length() > 10 ) {
			request.setAttribute(super.PREFIX + "id", "아이디는 5글자 이상 10글자 이하로 입력 해주세요.");
			isCheck = false;
		}
		
		if (bean.getName().length() < 2 || bean.getName().length() > 10 ) {
			request.setAttribute(super.PREFIX + "name", "이름은 2글자 이상 10글자 이하로 입력 해주세요.");
			isCheck = false;
		}
		
		if (bean.getNickname().length() < 2 || bean.getNickname().length() > 10 ) {
			request.setAttribute( super.PREFIX + "nickname", "닉네임은 2글자 이상 10글자 이하로 입력 해주세요.");
			isCheck = false;
		}
		
		if (bean.getPassword().length() < 6 || bean.getPassword().length() > 15 ) {
			request.setAttribute( super.PREFIX + "password", "비밀번호는 6자리 이상 15자리 이하로 입력 해주세요");
			isCheck = false;
		}
		
		if (bean.getZipcode() == null || bean.getZipcode() == "") {
			request.setAttribute(super.PREFIX + "zipcode", "우편 번호는 필수 입력 사항입니다");
			isCheck = false;
		}
		
		if (bean.getAddress1() == null || bean.getAddress1() == "") {
			request.setAttribute( super.PREFIX + "address1", "주소는 필수 입력 사항입니다");
			isCheck = false ;
		}
		
		if (bean.getAnimal_type() == null || bean.getAnimal_type() == "") {
			request.setAttribute( super.PREFIX + "animal",	"반려동물 종류를 선택 해주세요");
			isCheck = false;
		}
		
		boolean result = false;
		
		String email_regex = "^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z-]+)*$";

		result = Pattern.matches(email_regex, bean.getEmail());
		
		if (result == false) {
			request.setAttribute(super.PREFIX + "email", "이메일을 형식에 맞추어 입력 해주세요");
			isCheck = false;
		}
		
		String hphone_regex = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
		
		result = Pattern.matches(hphone_regex, bean.getPhone());
		
		if (result == false) {
			request.setAttribute(super.PREFIX + "phone", "휴대폰 번호를 올바른 형식으로 입력 해주세요");
			isCheck = false;
		}
		
		return isCheck;
	}
}
