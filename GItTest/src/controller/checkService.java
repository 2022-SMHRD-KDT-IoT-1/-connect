package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

@WebServlet("/checkService")
public class checkService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. 인코딩
		request.setCharacterEncoding("EUC-KR");
		// 1. 파라미터 수집
		String mb_id = request.getParameter("mb_id");
		// 2. DAO 사용
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.idCheck(mb_id);
		
		// 3. 응답
		response.setCharacterEncoding("EUC-KR");
		PrintWriter out = response.getWriter();

		out.print(vo == null);

	}

}
