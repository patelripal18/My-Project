package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.dao.UserDao;


@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		
		if(action.equalsIgnoreCase("sing up")) {
			
			boolean flag=UserDao.checkemail(request.getParameter("email"));
			if(flag==true) {
				
				request.setAttribute("msg","Email Already Registeread");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
				
			}
			else if(request.getParameter("password").equals(request.getParameter("cpassword"))) {
				
			
		User u=new User();
		u.setFname(request.getParameter("fname"));
		u.setLname(request.getParameter("lname"));
		u.setEmail(request.getParameter("email"));
		u.setMobile(Long.parseLong(request.getParameter("mobile")));
		u.setAddress(request.getParameter("address"));
		u.setPassword(request.getParameter("password"));
		UserDao.signupUser(u);
		request.setAttribute("msg", "User sign Up Successfull");
		request.getRequestDispatcher("signup.jsp").forward(request, response);
		
		}
			else {
				request.setAttribute("msg", "password & confim password dose not matched");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
				
			}
		}
	}

}
