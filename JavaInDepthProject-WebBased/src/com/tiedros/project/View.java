package com.tiedros.project;

import java.util.List;

import com.tiedros.project.controller.BookmarkController;
import com.tiedros.project.entity.Bookmark;
import com.tiedros.project.entity.User;
import com.tiedros.project.enums.KidFriendlyStatus;
import com.tiedros.project.enums.UserType;
import com.tiedros.project.partner.Shareable;

public class View {
	
	
	public static void browse(User user,List<List<Bookmark>> bookmarks) {
		System.out.println("\n "+ user.getEmail() +" is browsing items ....");
		
		
		//int bookmarkCount=0;
		for(List<Bookmark> bookmarkList:bookmarks) {
			for(Bookmark bookmark:bookmarkList) {
				//Bookmarking!!
				//if(bookmarkCount <DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmarked=getBookmarkDecision(bookmark);
					if(isBookmarked) {
						//bookmarkCount++;
						//BookmarkController.getInstance().saveUserBookmark(user,bookmark);
						System.out.println("New Item bookarked "+ bookmark);
					}
				//}
				// Mark as Kid-friendly
				if(user.getUserType().equals(UserType.EDITOR)|| user.getUserType().equals(UserType.CHIEF_EDITOR)) {
					if(bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus kidFriendlyStatus=getKidFriendlyStatusDecision(bookmark);
					if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
						//BookmarkController.getInstance().setKidFriendlyStatus(user,kidFriendlyStatus,bookmark);
						}
					}
					// Sharing 
					if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APROVED) && bookmark instanceof Shareable) {
						
						boolean isShare=getShareDecision();
						if(isShare) {
							//BookmarkController.getInstance().share(user,bookmark);
						}
					}
					
				}
			}
		}
		
		
		
	}
	// TODO: Below method simulate user input. After IO, we take input via console.
	private static boolean getShareDecision() {
		return Math.random() < 0.5 ? true:false;
		
	}

	private static KidFriendlyStatus  getKidFriendlyStatusDecision(Bookmark bookmark) {
		double decision=Math.random();
		return   decision< 0.4 ? KidFriendlyStatus.APROVED :
			(decision >=0.4 && decision <0.8) ? KidFriendlyStatus.REJECTED:KidFriendlyStatus.UNKNOWN;
		
	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random()<0.5?true:false;
		
	}

	/*public static void bookmark(User user,Bookmark [][] bookmarks) {
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
	}*/
}
