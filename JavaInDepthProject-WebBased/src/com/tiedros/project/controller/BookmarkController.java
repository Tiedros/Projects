package com.tiedros.project.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tiedros.project.entity.Bookmark;
import com.tiedros.project.entity.User;
import com.tiedros.project.enums.KidFriendlyStatus;
import com.tiedros.project.service.BookmarkService;
import com.tiedros.project.service.UserService;

import sun.rmi.server.Dispatcher;

@WebServlet(urlPatterns= {"/bookmark","/bookmark/save","/bookmark/mybooks"})
public class BookmarkController extends HttpServlet {
	
	/*private static BookmarkController bookmarkController= new BookmarkController();
	
	private BookmarkController() {
		
	}

	public static BookmarkController getInstance() {
		return bookmarkController;
	}*/
	
	public BookmarkController() {
		
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher=null;
		System.out.println("Servlet Path: "+ request.getServletPath());
		//long userId=5;
		if(request.getSession().getAttribute("userId") != null) {
			long userId=(long) request.getSession().getAttribute("userId");
			
			if(request.getServletPath().contains("save")) {
				// save
				dispatcher=request.getRequestDispatcher("/mybooks.jsp");
				String bid=request.getParameter("bid");
				User user=UserService.getInstance().getUser(userId);
				Bookmark bookmark=BookmarkService.getInstance().getBook(Long.parseLong(bid));
				BookmarkService.getInstance().saveUserBookmark(user, bookmark);
				Collection<Bookmark> list=BookmarkService.getInstance().getBooks(false,userId);
				request.setAttribute("books", list);
				
			}else if(request.getServletPath().contains("mybooks")) {
				//mybooks
				dispatcher=request.getRequestDispatcher("/mybooks.jsp");
				Collection<Bookmark> list=BookmarkService.getInstance().getBooks(true,userId);
				request.setAttribute("books", list);
				
			}else {
				dispatcher=request.getRequestDispatcher("/browse.jsp");
				Collection<Bookmark> list=BookmarkService.getInstance().getBooks(false,userId);
				request.setAttribute("books", list);
				
					
			}
			
		}else {
			dispatcher=request.getRequestDispatcher("/login.jsp");
		}
			//System.out.println("dispatcher: "+ dispatcher);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	
	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkService.getInstance().saveUserBookmark(user,bookmark);
		
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
		BookmarkService.getInstance().setKidFriendlyStatus(user,kidFriendlyStatus,bookmark);
		
	}

	public void share(User user, Bookmark bookmark) {
		BookmarkService.getInstance().share(user,bookmark);
		
	}

	
}
