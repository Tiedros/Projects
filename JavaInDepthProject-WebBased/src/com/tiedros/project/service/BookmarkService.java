package com.tiedros.project.service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import com.tiedros.project.dao.BookmarkDAO;
import com.tiedros.project.entity.Book;
import com.tiedros.project.entity.Bookmark;
import com.tiedros.project.entity.Movie;
import com.tiedros.project.entity.User;
import com.tiedros.project.entity.UserBookmark;
import com.tiedros.project.entity.WebLink;
import com.tiedros.project.enums.BookGenre;
import com.tiedros.project.enums.KidFriendlyStatus;
import com.tiedros.project.enums.MovieGenre;
import com.tiedros.project.util.HttpConnect;
import com.tiedros.project.util.IOUtil;

public class BookmarkService {

	private static BookmarkService bookmarkService=new BookmarkService();
	private static BookmarkDAO dao= new BookmarkDAO();
	
	private BookmarkService() {
		
	}
	
	public static BookmarkService getInstance() {
		return bookmarkService;
	}
	
	
	public WebLink createWebLink(long id,String title,String url, String host) {
		WebLink webLink=new WebLink();
		
		webLink.setId(id);
		webLink.setTitle(title);
		webLink.setUrl(url);
		webLink.setHost(host);
		
		return webLink;
	}
	
	
public 	Book createBook(long id,String title,String imageUrl,String profileUrl,int publicationYear,
		String publisher, String[] authors, BookGenre genre, double amazonRating) {
	
	Book book=new Book();
	
	book.setId(id);
	book.setTitle(title);
	book.setImageUrl(imageUrl);
	book.setProfileUrl(profileUrl);
	book.setPublicationYear(publicationYear);
	book.setPublisher(publisher);
	book.setAuthors(authors);
	book.setGenre(genre);
	book.setAmazonRating(amazonRating);
	
	return book;
}
	public Movie createMovie(long id,String title,String profileUrl,int releaseYear, 
			String[] cast, String[] directors, MovieGenre genre, double imdbRating) {
		
	Movie movie=new Movie();
	
	movie.setId(id);
	movie.setTitle(title);
	movie.setProfileUrl(profileUrl);
	movie.setReleaseYear(releaseYear);
	movie.setCast(cast);
	movie.setDirectors(directors);
	movie.setGenre(genre);
	movie.setImdbRating(imdbRating);
	
	
	return movie;
	}
	
	public List<List<Bookmark>> getBookmarks(){
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark=new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		
		/*
		if (bookmark instanceof WebLink) {
			try {				
				String url = ((WebLink)bookmark).getUrl();
				if (!url.endsWith(".pdf")) {
					String webpage = HttpConnect.download(((WebLink)bookmark).getUrl());
					if (webpage != null) {
						IOUtil.write(webpage, bookmark.getId());
					}
				}				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		dao.saveUserBookmark(userBookmark);
		
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		
		dao.updateKidFriendlyStatus(bookmark);
		System.out.println("kid-friendly status "+ kidFriendlyStatus +", Marked by "+ user.getEmail() +" ,"+ bookmark);
	
		
	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);
		System.out.println("Data to be shared: ");
		
		if(bookmark instanceof Book) {
			 System.out.println(((Book)bookmark).getItemData());
		}else if(bookmark instanceof WebLink) {
			 System.out.println(((WebLink)bookmark).getItemData());
		}
		
		dao.sharedByInfo(bookmark);
	}

	public Collection<Bookmark> getBooks(boolean isBookmarked, long id) {
		return dao.getBooks(isBookmarked,id);
		
	}

	public Bookmark getBook(long bid) {
		return dao.getBook(bid);
		
	}
}
