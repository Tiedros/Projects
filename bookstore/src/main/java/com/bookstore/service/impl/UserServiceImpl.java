package com.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.User;
import com.bookstore.domain.UserBilling;
import com.bookstore.domain.UserPayment;
import com.bookstore.domain.UserShipping;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.domain.security.UserRole;
import com.bookstore.repository.PasswordResetTokenRepository;
import com.bookstore.repository.RoleRepository;
import com.bookstore.repository.UserPaymentRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.repository.UserShippingRepository;
import com.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG=LoggerFactory.getLogger(UserService.class);
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	@Override
	public PasswordResetToken getPasswordRestToken(String token) {
		// TODO Auto-generated method stub
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken= new PasswordResetToken(token,user);
		passwordResetTokenRepository.save(myToken);

	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User localUser=userRepository.findByUsername(user.getUsername());
		if(localUser != null) {
			//throw new Exception("user already exists. Nothing will be done");
			LOG.info("user {} already exists. Nothing will be done",user.getUsername());
		}else {
			for(UserRole userRole:userRoles) {
				roleRepository.save(userRole.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			ShoppingCart shoppingCart=new ShoppingCart();
			shoppingCart.setUser(user);
			user.setShoppingCart(shoppingCart);
			
			user.setUserShippingList(new ArrayList<UserShipping>());
			user.setUserPaymentList(new ArrayList<UserPayment>() );
			
			localUser=userRepository.save(user);
		}
		
		return localUser;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
		
	}

	@Override
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		// TODO Auto-generated method stub
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);
		
	}

	@Override
	public void setUserDefaulPayment(Long userPaymentId, User user) {
		// TODO Auto-generated method stub
		
		List<UserPayment> userPaymentList=(List<UserPayment>) userPaymentRepository.findAll();
		
		for(UserPayment userPayment:userPaymentList) {
			if(userPayment.getId() == userPaymentId) {
				userPayment.setDefaultPayment(true);
				userPaymentRepository.save(userPayment);
			}else {
				userPayment.setDefaultPayment(false);
				userPaymentRepository.save(userPayment);
			}
		}
		
	}

	

	@Override
	public void setUserDefaulShipping(Long useShippingId, User user) {
		// TODO Auto-generated method stub
		List<UserShipping> userShippingList=(List<UserShipping>) userShippingRepository.findAll();
		
		for(UserShipping userShipping:userShippingList) {
			if(userShipping.getId() == useShippingId) {
				userShipping.setUserShippingDefault(true);
				userShippingRepository.save(userShipping);
			}else {
				userShipping.setUserShippingDefault(false);
				userShippingRepository.save(userShipping);
			}
		}
		
	}
	
	@Override
	public void updateUserShipping(UserShipping userShipping, User user) {
		// TODO Auto-generated method stub
		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippingList().add(userShipping);
		save(user);
		
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

}
