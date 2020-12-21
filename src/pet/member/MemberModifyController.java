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
//				bean.setMpoint(Integer.parseInt(request.getParameter("mpoint")));
//				bean.setMlevel(Integer.parseInt(request.getParameter("mlevel")));
				
				String gotopage = "";
				
				if (this.validate(request) == true) { // 유효성 검사 성공
					MemberDao dao = new MemberDao();
					int cnt = -99999;
					cnt = dao.modifyData(bean);
					
					new MainController().doGet(request, response);
					
				} else { // 유효성 검사 실패
					request.setAttribute("bean", bean);
					super.doPost(request, response);
					
					gotopage = "/member/mModifyForm.jsp";
					super.goToPage(gotopage);
				}
	}
	
	
	@Override
	public boolean validate(HttpServletRequest request) {
		boolean isCheck = true;
		
		if (bean.getNickname().length() < 4 || bean.getNickname().length() > 10) {
			request.setAttribute(super.PREFIX + "nickname", "닉네임은 4글자 이상 10글자 이하로 입력 해주세요.");
			isCheck = false; 
		}
		
		if (bean.getPassword().length() < 4 || bean.getPassword().length() > 10) {
			request.setAttribute(super.PREFIX + "password", "비밀번호는 6자리 이상 10자리 이하로 입력 해주세요.");
			isCheck = false;
		}

		if (bean.getZipcode() == null || bean.getZipcode() == "") {
			request.setAttribute(super.PREFIX + "zipcode", "우편번호는 필수 사항 입니다.");
			isCheck = false;
		}
		
		if (bean.getAddress1() == null || bean.getAddress1() == "" ) {
			request.setAttribute(super.PREFIX + "address1", "주소는 필수 사항 입니다.");
			isCheck = false;
		}
		
		String regex = "/^￦d{2,3}-￦d{3,4}-￦d{4}$/";
		
		if (bean.getPhone() == null) {
			bean.setPhone("");
		}
		
		boolean result = Pattern.matches(regex, bean.getPhone());
		
		if (result == false ) {
			request.setAttribute(super.PREFIX + "phone", "핸드폰 번호를 000-0000-0000 형식으로 입력 해주세요.");
			isCheck = false;
		}
		
		regex = "/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;\n";
		
		if (bean.getEmail() == null) {
			bean.setEmail("");
			}
		
		result = Pattern.matches(regex, bean.getEmail());
		
		if (result == false ) {
			request.setAttribute(super.PREFIX + "email", "이메일을 형식에 맞춰 입력 해주세요.");
			isCheck = false;
		}
		
		return isCheck; 
	}
	
}
