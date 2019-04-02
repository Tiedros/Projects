package com.tiedros.project.entity;

import java.util.Arrays;

import com.tiedros.project.enums.MovieGenre;

public class Movie extends Bookmark {
	
	private int releaseYear;
	private String [] cast;
	private String [] directors;
	private MovieGenre genre;
	private double imdbRating;
	
	public Movie() {
		
	}

	public Movie(int releaseYear, String[] cast, String[] directors, MovieGenre genre, double imdbRating) {
		super();
		this.releaseYear = releaseYear;
		this.cast = cast;
		this.directors = directors;
		this.genre = genre;
		this.imdbRating = imdbRating;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String[] getCast() {
		return cast;
	}

	public void setCast(String[] cast) {
		this.cast = cast;
	}

	public String[] getDirectors() {
		return directors;
	}

	public void setDirectors(String[] directors) {
		this.directors = directors;
	}

	public MovieGenre getGenre() {
		return genre;
	}

	public void setGenre(MovieGenre genre) {
		this.genre = genre;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	@Override
	public String toString() {
		return "Movie [releaseYear=" + releaseYear + ", cast=" + Arrays.toString(cast) + ", directors="
				+ Arrays.toString(directors) + ", genre=" + genre + ", imdbRating=" + imdbRating + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		if(getGenre().equals(MovieGenre.HORROR) || getGenre().equals(MovieGenre.THRILLERS)) {
			return false;
		}
		return true;
	}
	
	
	

}
