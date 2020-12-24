package pet.common;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuperClass implements SuperController, Validator {
	public static final String PREFIX = "err";
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	protected HttpSession session = null;
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get, Post에 상관없이 무조건 호출
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	
	@Override
	public boolean validate(HttpServletRequest request) {
		// 유효성 검사를 위한 메소드
		return true; // 기본 값은 true로 가정
	}
	
	public void goToPage(String url) {
		// dispatcher 객체를 사용하여 특정 페이지로 이동
		RequestDispatcher dispatcher = this.request.getRequestDispatcher(url);
		
		try {
			dispatcher.forward(this.request, this.response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setErrorMessage(String message) {
		// 오류 메시지는 common.jsp 파일의 하단에서 보여 줍니다.
		this.request.setAttribute("errmsg", message);
		
	}
}
