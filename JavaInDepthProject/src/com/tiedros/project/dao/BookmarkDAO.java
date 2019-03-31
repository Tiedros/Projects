package com.tiedros.project.dao;

import com.tiedros.project.DataStore;
import com.tiedros.project.entity.Bookmark;

public class BookmarkDAO {
 public Bookmark [][] getBookmarks() {
	 return DataStore.getBookmarks();
 }
}
