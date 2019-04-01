package com.tiedros.project.entity;

import com.tiedros.project.constants.KidFriendlyStatus;

public abstract class Bookmark {

	private long id;
	private String title;
	private String profileUrl;
	private String kidFriendlyStatus=KidFriendlyStatus.UNKNOWN;
	private User kidFriendlyMarkedBy;
	private User sharedBy;
	
	public Bookmark() {
		
	}

	public Bookmark(long id, String title, String profileUrl) {
		this.id = id;
		this.title = title;
		this.profileUrl = profileUrl;
	}

	
	
	public User getSharedBy() {
		return sharedBy;
	}

	public void setSharedBy(User sharedBy) {
		this.sharedBy = sharedBy;
	}

	public User getKidFriendlyMarkedBy() {
		return kidFriendlyMarkedBy;
	}

	public void setKidFriendlyMarkedBy(User kidFriendlyMarkedBy) {
		this.kidFriendlyMarkedBy = kidFriendlyMarkedBy;
	}

	public String getKidFriendlyStatus() {
		return kidFriendlyStatus;
	}

	public void setKidFriendlyStatus(String kidFriendlyStatus) {
		this.kidFriendlyStatus = kidFriendlyStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	
	public abstract boolean isKidFriendlyEligible();
	@Override
	public String toString() {
		return "Bookmark [id=" + id + ", title=" + title + ", profileUrl=" + profileUrl + "]";
	}
	
	
	
	
	
}
