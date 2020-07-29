package voca_test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class VocaControl
 */
@WebServlet("/voca/*")
public class VocaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] result = new String[15];
	int cnt = 0;
	List<Integer> scores = new ArrayList<Integer>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VocaControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String pathInfo = request.getPathInfo();
		String action = request.getParameter("action");
		String viewName = null;
		MemberDO member = new MemberDO();
		VocaTestDO voca = new VocaTestDO();	
		TestDAO dao = (TestDAO)session.getAttribute("dao");

		if(dao == null) {
			ServletContext context = getServletContext();
			dao = new TestDbcpDao(
					context.getInitParameter("dbcp_resource_name")
			);
			session.setAttribute("dao", dao);
		}
		
		// step #2. data processing
		// get routing info.
		if(pathInfo != null && pathInfo.length() > 1) {
			if(pathInfo.equals("/login_form")) {
				viewName = "/view/login_form.jsp";
			}
			else if(pathInfo.equals("/join_form")) {
				viewName = "/view/join_form.jsp";
			}
			else if(pathInfo.equals("/test")) {				// voca test page
				int index = Integer.parseInt(request.getParameter("index"));
				request.setAttribute("index", index);
				String[] eng = new String[3];
				List<String> kor = new ArrayList<String>();
				
				try {
					if(index<16) {
						for(int i = index-1;i<index+2;i++) {
							voca = dao.getList(i+1);
							eng[i%3] = voca.getEng();	
						}
					}
					kor = dao.getKor();		// 뜻 가져오기
					if(index>3) {
						Enumeration<String> e = request.getParameterNames();
						e.nextElement();
						while ( e.hasMoreElements() ){
							String name = (String) e.nextElement();
							String value = request.getParameter(name);
							if(kor.get(index-4).equals(value)) {		// 정답
								result[index-4]="O";
								cnt++;
							}else {										// 오답
								result[index-4]="X";
							}
							index++;
						}
					}
						viewName = "/view/test.jsp";
						if(index>16) {			// 결과 페이지
							scores.add(cnt);
							session.setAttribute("scores", scores);
							viewName = "/view/result.jsp";
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				request.setAttribute("eng", eng);	 
				request.setAttribute("cnt", cnt);
				request.setAttribute("result", result);
			}
		}
		if(action != null){
			if(action.equals("join")) {
				member.setId(request.getParameter("id"));
				member.setPwd(request.getParameter("pwd"));
				member.setName(request.getParameter("name"));
				
				try {
					dao.insert(member);
					viewName = "redirect:/voca/login_form";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					viewName="redirect:/voca/join_form";		// 아이디 중복입력
				}
			}
			else if(action.equals("login")) {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String name = null;

				try {
					member = dao.Login(id);
					if(member != null) {	
						if(member.getPwd().equals(pwd)) {
							name = member.getName();
							session.setAttribute("name", name);
							viewName="/view/main.jsp";
						}
						else {		// 비밀번호 잘못 입력					
							viewName="redirect:/voca/login_form";
						}
					}
					else{					// 아이디 잘못 입력
						viewName="redirect:/voca/login_form";
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
			else if(action.equals("log")) {
				cnt = 0;
				viewName="/view/main.jsp";
			}
			else if(action.equals("logout")) {
				session.invalidate();
				scores.clear();
				viewName="redirect:/voca/login_form";
			}
			else if(action.equals("scores")) {		// 결과들
				if(scores.size()>=3) {
					viewName="/view/scores.jsp";
				}
				else {
					viewName="redirect:/voca?action=log";
				}
			}
		
		}
		if(viewName != null) {
			if(viewName.contains("redirect:")) {
				String location = viewName.split(":")[1];
				response.sendRedirect(request.getContextPath()+location);
			}
			else{
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
