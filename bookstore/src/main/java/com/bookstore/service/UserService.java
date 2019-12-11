package com.bookstore.service;

import java.util.Set;

import com.bookstore.domain.User;
import com.bookstore.domain.UserBilling;
import com.bookstore.domain.UserPayment;
import com.bookstore.domain.UserShipping;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.domain.security.UserRole;

public interface UserService {
	
	PasswordResetToken getPasswordRestToken(final String token);
	void createPasswordResetTokenForUser(final User user,final String token);
	User findByUsername(String username);
	User findByEmail(String email);
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	void save(User user);
	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
	void setUserDefaulPayment(Long userPaymentId, User user);
	void updateUserShipping(UserShipping userShipping, User user);
	void setUserDefaulShipping(Long defaultShippingId, User user);
	User findById(Long id);

}
