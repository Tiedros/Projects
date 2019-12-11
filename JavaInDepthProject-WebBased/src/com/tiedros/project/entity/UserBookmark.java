package com.tiedros.project.entity;

public class UserBookmark {

	private Bookmark bookmark;
	private User user;
	
	public UserBookmark() {
		
	}

	public UserBookmark(Bookmark bookmark, User user) {
		super();
		this.bookmark = bookmark;
		this.user = user;
	}

	public Bookmark getBookmark() {
		return bookmark;
	}

	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserBookmark [bookmark=" + bookmark + ", user=" + user + "]";
	}
	
	
}
