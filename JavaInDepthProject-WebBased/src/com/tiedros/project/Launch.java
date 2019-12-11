package com.tiedros.project;

import java.util.List;

import com.tiedros.project.bgjobs.WebPageDownloaderTask;
import com.tiedros.project.entity.Bookmark;
import com.tiedros.project.entity.User;
import com.tiedros.project.service.BookmarkService;
import com.tiedros.project.service.UserService;

public class Launch {

	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;
	
	private static void loadData() {
		
		System.out.println("1. Loading data ...");
		DataStore.loadData();
		
		users=UserService.getInstance().getUsers();
		bookmarks=BookmarkService.getInstance().getBookmarks();
		
		//System.out.println(" Printing Data ...");
		//printUserData();
		//printBookmarkData();
		
	}

	private static void printBookmarkData() {
		for(List<Bookmark> bookmarkList : bookmarks) {
			for(Bookmark bookmark:bookmarkList) {
				System.out.println(bookmark);
			}
		}
		
	}

	private static void printUserData() {
		
		
		for(User user:users) {
			System.out.println(user);
		}
	}
	
	private static void start() {
		
		//System.out.println("2. Bookmarking ...");
		for(User user:users) {
			//View.bookmark(user, bookmarks);
			View.browse(user, bookmarks);
		}
		
	}

	public static void main(String[] args) {
	
		loadData();
		start();
		
		// background jobs
		runDonloadJob();

	}

	private static void runDonloadJob() {
		
		WebPageDownloaderTask webPageDownloaderTask=new WebPageDownloaderTask(true);
		(new Thread(webPageDownloaderTask)).start();
		
	}

	

	
}
