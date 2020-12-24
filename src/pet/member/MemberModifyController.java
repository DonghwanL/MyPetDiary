package pet.member;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.common.MainController;
import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberModifyController extends SuperClass {
	private Member bean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MemberDao mdao = new MemberDao();
		Member bean = mdao.selectDataByID(id);
		
		super.doGet(request, response);
		request.setAttribute("bean", bean);
		
		String gotopage = "/member/mModifyForm.jsp";
		super.goToPage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		bean = new Member();
				
		bean.setAddress1(request.getParameter("address1"));
		bean.setAddress2(request.getParameter("address2"));
		bean.setId(request.getParameter("id"));
		bean.setName(request.getParameter("name"));
		bean.setPassword(request.getParameter("password"));
		bean.setZipcode(request.getParameter("zipcode"));
		bean.setNickname(request.getParameter("nickname"));
		bean.setEmail(request.getParameter("email"));
		bean.setPhone(request.getParameter("phone"));
		bean.setAnimal_type(request.getParameter("animal_type"));
		
		System.out.println("here1");
		
		String gotopage = "";
			
		System.out.println("here2");
		if (this.validate(request) == true) {
			MemberDao mdao = new MemberDao();
			
			int cnt = -99999;
			cnt = mdao.modifyMember(bean);
					
			System.out.println("MDAO : 회원 정보 수정 완료");
			
			new MemberModifyController().doGet(request, response);
					
		} else {
			super.doPost(request, response);
			request.setAttribute("bean", bean);
			
			gotopage = "member/mModifyForm.jsp";
			super.goToPage(gotopage);			
		}
	}
	
	
	@Override
	public boolean validate(HttpServletRequest request) {
		boolean isCheck = true;
		
		if (bean.getNickname().length() < 2 || bean.getNickname().length() > 10) {
			request.setAttribute(super.PREFIX + "nickname", "닉네임은 2글자 이상 10글자 이하로 입력 해주세요");
			isCheck = false; 
		}
		
		if (bean.getPassword().length() < 6 || bean.getPassword().length() > 15) {
			request.setAttribute(super.PREFIX + "password", "비밀번호는 6자리 이상 15자리 이하로 입력 해주세요");
			isCheck = false;
		}
		

		if (bean.getZipcode() == null || bean.getZipcode() == "") {
			request.setAttribute(super.PREFIX + "zipcode", "우편번호는 필수 사항 입니다");
			isCheck = false;
		}
		
		if (bean.getAddress1() == null || bean.getAddress1() == "" ) {
			request.setAttribute(super.PREFIX + "address2", "주소는 필수 사항 입니다");
			isCheck = false;
		}
		
		if (bean.getAnimal_type() == null || bean.getAnimal_type() == "") {
			request.setAttribute( super.PREFIX + "animal",	"반려동물 종류를 선택 해주세요");
			isCheck = false;
		}
		
		boolean result = false;
		
		String hphone_regex = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
		
		result = Pattern.matches(hphone_regex, bean.getPhone());
		
		if (result == false) {
			request.setAttribute(super.PREFIX + "phone", "휴대폰 번호를 올바른 형식으로 입력 해주세요");
			isCheck = false;
		}
		
		String email_regex = "^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z-]+)*$";

		result = Pattern.matches(email_regex, bean.getEmail());
		
		if (result == false) {
			request.setAttribute(super.PREFIX + "email", "이메일을 형식에 맞추어 입력 해주세요");
			isCheck = false;
		}
		
		return isCheck; 
	}
}
