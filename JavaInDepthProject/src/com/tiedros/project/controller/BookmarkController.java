package com.tiedros.project.controller;

import com.tiedros.project.entity.Bookmark;
import com.tiedros.project.entity.User;
import com.tiedros.project.service.BookmarkService;

public class BookmarkController {
	
	private static BookmarkController bookmarkController= new BookmarkController();
	
	private BookmarkController() {
		
	}

	public static BookmarkController getInstance() {
		return bookmarkController;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkService.getInstance().saveUserBookmark(user,bookmark);
		
	}

	
}
