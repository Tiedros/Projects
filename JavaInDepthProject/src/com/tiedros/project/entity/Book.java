package com.tiedros.project.entity;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.tiedros.project.enums.BookGenre;
import com.tiedros.project.partner.Shareable;

public class Book  extends  Bookmark implements Shareable{
	
	private int publicationYear;
	private String publisher;
	private String [] authors;
	private BookGenre genre;
	private double amazonRating;
	
	
	public Book() {
		
	}


	public Book(int publicationYear, String publisher, String[] authors, BookGenre genre, double amazonRating) {
		super();
		this.publicationYear = publicationYear;
		this.publisher = publisher;
		this.authors = authors;
		this.genre = genre;
		this.amazonRating = amazonRating;
	}


	public int getPublicationYear() {
		return publicationYear;
	}


	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String[] getAuthors() {
		return authors;
	}


	public void setAuthors(String[] authors) {
		this.authors = authors;
	}


	public BookGenre getGenre() {
		return genre;
	}


	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}


	public double getAmazonRating() {
		return amazonRating;
	}


	public void setAmazonRating(double amazonRating) {
		this.amazonRating = amazonRating;
	}


	@Override
	public String toString() {
		return "Book [publicationYear=" + publicationYear + ", publisher=" + publisher + ", authors="
				+ Arrays.toString(authors) + ", genre=" + genre + ", amazonRating=" + amazonRating + "]";
	}


	@Override
	public boolean isKidFriendlyEligible() {
		if(getGenre().equals(BookGenre.PHILOSOPHY) || getGenre().equals(BookGenre.SELF_HELP)) {
			return false;
		}
		return true;
	}


	@Override
	public String getItemData() {
		StringBuilder builder=new StringBuilder();
		builder.append("<item>");
			builder.append("<type>Book</type>");
			builder.append("<title>").append(getTitle()).append("</title>");
			builder.append("<authors>").append(StringUtils.join(authors,",")).append("</authors>");
			builder.append("<publisher>").append(getPublisher()).append("</publisher>");
			builder.append("<publicationYear>").append(getPublicationYear()).append("</publicationYear>");
			builder.append("<genre>").append(getGenre()).append("</genre>");
			builder.append("<amazonRating>").append(getAmazonRating()).append("</amazonRating>");
		
		builder.append("</item>");
		
		
		return builder.toString();
	}

	
}
