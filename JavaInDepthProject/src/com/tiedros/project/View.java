package com.tiedros.project;

import com.tiedros.project.controller.BookmarkController;
import com.tiedros.project.entity.Bookmark;
import com.tiedros.project.entity.User;

public class View {

	public static void bookmark(User user,Bookmark [][] bookmarks) {
		System.out.println("\n "+ user.getEmail() +" is bookmarking");
		
		for(int i=0;i<DataStore.USER_BOOKMARK_LIMIT;i++) {
			int typeOffSet = (int)(Math.random()*DataStore.BOOKMARK_TYPES_COUNT);
			int bookmarkOffSet = (int)(Math.random()*DataStore.BOOKMARK_COUNT_PER_TYPE);
		
			//System.out.println("typeOffSet "+typeOffSet);
			//System.out.println("bookmarkOffSet "+bookmarkOffSet);
			Bookmark bookmark=bookmarks[typeOffSet][bookmarkOffSet];
			BookmarkController.getInstance().saveUserBookmark(user,bookmark);
		
			System.out.println(bookmark);
		}
	}
}
