package com.tiedros.project.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tiedros.project.service.BookmarkService;

class WebLinkTest {

	@Test
	void testIsKidFriendlyEligible() {
		
		// Test 1: porn in url -- fale
		WebLink webLink=BookmarkService.getInstance().createWebLink(2000,"Taming Tiger, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html","http://www.javaworld.com");
		boolean isKidFriendlyEligible=webLink.isKidFriendlyEligible();
		assertFalse("for porn in url -isKidFriendlyEligible() must return false",isKidFriendlyEligible);
		// Test 2: porn in title -- false
		webLink=BookmarkService.getInstance().createWebLink(2000,"Taming porn, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld.com");
		isKidFriendlyEligible=webLink.isKidFriendlyEligible();
		assertFalse("for porn in title -isKidFriendlyEligible() must return false",isKidFriendlyEligible);
		
		// Test 3: adult in host -- false
		webLink=BookmarkService.getInstance().createWebLink(2000,"Taming Tiger, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld-adult.com");
		isKidFriendlyEligible=webLink.isKidFriendlyEligible();
		assertFalse("for adult in host -isKidFriendlyEligible() must return false",isKidFriendlyEligible);
		
		// Test 4: adult in url but not in host -- true
		webLink=BookmarkService.getInstance().createWebLink(2000,"Taming Tiger, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-adult-part-2.html","http://www.javaworld.com");
		isKidFriendlyEligible=webLink.isKidFriendlyEligible();
		assertTrue("for adult in url but not in host -isKidFriendlyEligible() must return true",isKidFriendlyEligible);
		
		// Test 5: adult in title only -- true
		webLink=BookmarkService.getInstance().createWebLink(2000,"Taming adult, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld.com");
		isKidFriendlyEligible=webLink.isKidFriendlyEligible();
		assertTrue("for adult in title only  -isKidFriendlyEligible() must return true",isKidFriendlyEligible);
		
	}

}
