package com.tiedros.project.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tiedros.project.constants.MovieGenre;
import com.tiedros.project.service.BookmarkService;

class MovieTest {

	@Test
	void testIsKidFriendlyEligible() {
		//Test 1: for HORROR genre -- false
		Movie movie=BookmarkService.getInstance().createMovie(3000,"Citizen Kane","",1941,new String[] {"Orson Welles","Joseph Cotten"},new String[] {"Orson Welles"},MovieGenre.HORROR,8.5);
		boolean isKidFriendlyEligible=movie.isKidFriendlyEligible();
		assertFalse("For HORROR Genre - isKidFriendlyEligible() must return fale",isKidFriendlyEligible);
		// Test 2: for THRILLERS -- false
		movie=BookmarkService.getInstance().createMovie(3000,"Citizen Kane","",1941,new String[] {"Orson Welles","Joseph Cotten"},new String[] {"Orson Welles"},MovieGenre.THRILLERS,8.5);
		isKidFriendlyEligible=movie.isKidFriendlyEligible();
		assertFalse("for THRILLERS Genre - isKidFriendlyEligible() must return false",isKidFriendlyEligible); 
	}

}
