package com.ljq.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ljq.po.User;
import com.ljq.service.UserService;
import com.ljq.util.MailUtil;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService us;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        us = new UserService();
        // TODO Auto-generated constructor stub
    }
    
    protected void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	User user = new User(0, username, password, "","",0);
    	if(us.login(user)) {
    		HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("user", user);
			response.sendRedirect("main.jsp");
    	}else {
    		response.sendRedirect("index.jsp");
    	}
    }
    
    protected void regist(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String email = request.getParameter("email");
    	String code = UUID.randomUUID().toString().replaceAll("-", "");
    	User user = new User(0, username, password, email, code, 0);
    	us.newUser(user);
    	new Thread(new MailUtil(email, code)).start();;
    	request.getRequestDispatcher("/sendOK.jsp").forward(request, response);
    }
    
    protected void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	HttpSession session = request.getSession();
    	session.removeAttribute("user");
    	response.sendRedirect("index.jsp");
    }
    
    protected void toActivation(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String code = request.getParameter("code");
    	User user = us.getUserByCode(code);
    	request.setAttribute("user", user);
    	request.getRequestDispatcher("/activation.jsp").forward(request, response);
    }
    
    protected void activation(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	String username = request.getParameter("username");
    	User user = us.getUserByUserName(username);
    	user.setStatus(1);
    	us.updateUser(user);
    	response.sendRedirect("index.jsp");
    }
    
    protected void username(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        User user = us.getUserByUserName(username);
        if(user!=null){//用户名已存在
            response.getWriter().print("yes");
        }else{//用户名不存在
            response.getWriter().print("no");
        }

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		switch (action) {
		case "login":
			login(request, response);
			break;
		case "toRegist":
			response.sendRedirect("regist.jsp");
			break;
		case "regist":
			regist(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "toActivation":
			toActivation(request, response);
			break;
		case "activation":
			activation(request, response);
			break;
		case "username":
			username(request, response);
			break;
		default:
			break;
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
