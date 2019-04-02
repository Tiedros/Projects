package com.tiedros.project;

import java.util.ArrayList;
import java.util.List;

import com.tiedros.project.entity.Bookmark;
import com.tiedros.project.entity.User;
import com.tiedros.project.entity.UserBookmark;
import com.tiedros.project.enums.BookGenre;
import com.tiedros.project.enums.Gender;
import com.tiedros.project.enums.MovieGenre;
import com.tiedros.project.enums.UserType;
import com.tiedros.project.service.BookmarkService;
import com.tiedros.project.service.UserService;
import com.tiedros.project.util.IOUtil;

public class DataStore {

	public static final int USER_BOOKMARK_LIMIT = 5;
	public static final int BOOKMARK_COUNT_PER_TYPE = 5;
	public static final int BOOKMARK_TYPES_COUNT = 3;
	public static final int TOTAL_USER_COUNT = 5;

	
	private static List<User> users=new ArrayList<>();
	private static List<List<Bookmark>> bookmarks = new ArrayList<>();
	private static List<UserBookmark> userBookmark = new ArrayList<>();
	private static int bookmarkIndex;
	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	

	
	
	private static void loadUsers() {

		List<String> data=new ArrayList<>();
    	IOUtil.read(data, "User.txt");
    	int rowNum = 0;
    	for (String row : data) {
    		String[] values = row.split("\t");
    		
    		Gender gender = Gender.MALE;
    		if (values[5].equals("f")) {
    			gender = Gender.FEMALE;
    		} else if (values[5].equals("t")) {
    			gender = Gender.TRANSGENDER;
    		}
    			
    		users.add(UserService.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, values[6]));
    	}
    	
	
		
		
	}
	private static void loadWebLinks() {
		List<String> data = new ArrayList<>();
    	IOUtil.read(data, "WebLink.txt");
    	List<Bookmark> bookmarkList=new ArrayList<>();
    	for (String row : data) {
    		String[] values = row.split("\t");
    		bookmarkList.add(BookmarkService.getInstance().createWebLink(Long.parseLong(values[0]), values[1], values[2], values[3]/*, values[4]*/));;
    	}
		bookmarks.add(bookmarkList);	
		}
	private static void loadMovies() {
		List<String> data=new ArrayList<>();
    	IOUtil.read(data, "Movie.txt");
    	List<Bookmark> bookmarkList=new ArrayList<>();
    	for (String row : data) {
    		String[] values = row.split("\t");
    		String[] cast = values[3].split(",");
    		String[] directors = values[4].split(",");
    		bookmarkList.add(BookmarkService.getInstance().createMovie(Long.parseLong(values[0]), values[1], "", Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5]), Double.parseDouble(values[6])/*, values[7]*/));
    	}
    	bookmarks.add(bookmarkList);	
	}
	
	private static void loadBooks() {
		
		
		List<String> data=new ArrayList<>();
    	IOUtil.read(data, "Book.txt");
    	List<Bookmark> bookmarkList=new ArrayList<>();
    	for (String row : data) {
    		String[] values = row.split("\t");
    		String[] authors = values[4].split(",");
    		bookmarkList.add(BookmarkService.getInstance().createBook(Long.parseLong(values[0]), values[1],"", Integer.parseInt(values[2]), values[3], authors, BookGenre.valueOf(values[5]), Double.parseDouble(values[6])/*, values[7]*/));
    	}
    	bookmarks.add(bookmarkList);	
	}





	public static List<User> getUsers() {
		return users;
	}





	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}





	public static List<UserBookmark> getUserBookmark() {
		return userBookmark;
	}





	public static void add(UserBookmark userBookmark2) {
	userBookmark.add(userBookmark2);	
		
	}


}
