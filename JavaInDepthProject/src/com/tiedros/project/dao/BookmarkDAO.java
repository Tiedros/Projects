package com.tiedros.project.dao;

import java.util.List;

import com.tiedros.project.DataStore;
import com.tiedros.project.entity.Bookmark;
import com.tiedros.project.entity.UserBookmark;

public class BookmarkDAO {
 public List<List<Bookmark>> getBookmarks() {
	 return DataStore.getBookmarks();
 }

public void saveUserBookmark(UserBookmark userBookmark) {
	DataStore.add(userBookmark);
	
}
}
