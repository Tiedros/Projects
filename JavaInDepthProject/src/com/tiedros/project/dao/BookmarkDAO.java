package com.tiedros.project.dao;

import java.util.ArrayList;
import java.util.List;

import com.tiedros.project.DataStore;
import com.tiedros.project.entity.Bookmark;
import com.tiedros.project.entity.UserBookmark;
import com.tiedros.project.entity.WebLink;

public class BookmarkDAO {
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}

	// In real application, we would have sql or hibernate quries
	public List<WebLink> getAllWebLinks() {
		List<WebLink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks=DataStore.getBookmarks();
		List<Bookmark> allWebLinks=bookmarks.get(0);
		
		for(Bookmark bookmark:allWebLinks) {
			result.add((WebLink)bookmark);
		}

		return result;
	}

	public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus) {
		List<WebLink> result = new ArrayList<>();
		List<WebLink> allWebLinks=getAllWebLinks();
		
		for(WebLink webLink:allWebLinks) {
			if(webLink.getDownloadStatus().equals(downloadStatus)) {
				result.add(webLink);
			}
		}
		return result;
	}

}
