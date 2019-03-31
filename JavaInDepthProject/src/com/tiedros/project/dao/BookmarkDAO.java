package com.tiedros.project.dao;

import com.tiedros.project.DataStore;
import com.tiedros.project.entity.Bookmark;
import com.tiedros.project.entity.UserBookmark;

public class BookmarkDAO {
 public Bookmark [][] getBookmarks() {
	 return DataStore.getBookmarks();
 }

public void saveUserBookmark(UserBookmark userBookmark) {
	DataStore.add(userBookmark);
	
}
}
