package com.tiedros.project.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tiedros.project.constants.BookGenre;
import com.tiedros.project.service.BookmarkService;

class BookTest {

	@Test
	void testIsKidFriendlyEligible() {
		// Test 1: for PHILOSOPHY Genre -- false
		Book book=BookmarkService.getInstance().createBook(4000,"Walden","",1854,	"Wilder Publications",	new String[] {"Henry David Thoreau"},BookGenre.PHILOSOPHY,	4.3);
		boolean isKidFriendlyEligible=book.isKidFriendlyEligible();
		assertFalse("for PHILOSOPHY genre -isKidFriendlyEligible( ) must retrun false",isKidFriendlyEligible);
		// Test 2: for Self_Help genre -- false
		book=BookmarkService.getInstance().createBook(4000,"Walden","",1854,	"Wilder Publications",	new String[] {"Henry David Thoreau"},BookGenre.SELF_HELP,	4.3);
		isKidFriendlyEligible=book.isKidFriendlyEligible();
		assertFalse("for Self_Help genre -isKidFriendlyEligible() must return false",isKidFriendlyEligible);
	}

}
