package web.dispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/DispatcherServlet", "/dispatcher" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		path = path.substring(1, path.lastIndexOf("."));
		String next = "main.jsp";
		if(path.equals("main")){
			String view = request.getParameter("view");
			if(view != null){
				build(request, view);
			}
			next = "main.jsp";
		}else {
			next = path;
		}
		RequestDispatcher rd = 
		request.getRequestDispatcher(next);
		rd.forward(request, response);
	}
	
	
	// make ui
	private void build(HttpServletRequest request,
			String view){
		if(view.equals("login")) {
			request.setAttribute("center","login");
			request.setAttribute("navi", Navi.login);
		}
		if(view.equals("register")) {
			request.setAttribute("center", "register");
			request.setAttribute("navi", Navi.register);
		}
		if(view.equals("cust_register")) {
			request.setAttribute("center", "cust/register");
			request.setAttribute("navi", Navi.cust_register);
		}
		if(view.equals("prd_register")) {
			request.setAttribute("center", "product/register");
			request.setAttribute("navi", Navi.prd_register);
		}
	}

	
	

}




