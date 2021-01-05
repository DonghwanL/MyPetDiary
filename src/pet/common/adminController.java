package pet.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class adminController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String gotopage = "/common/admin.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(gotopage);
		dispatcher.forward(request, response);
	}
}
