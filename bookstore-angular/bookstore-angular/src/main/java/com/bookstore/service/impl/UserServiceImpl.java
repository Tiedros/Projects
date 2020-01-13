package com.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.User;
import com.bookstore.domain.UserBilling;
import com.bookstore.domain.UserPayment;
import com.bookstore.domain.UserShipping;
import com.bookstore.domain.security.UserRole;
import com.bookstore.repository.RoleRepository;
import com.bookstore.repository.UserBillingRepository;
import com.bookstore.repository.UserPaymentRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.repository.UserShippingRepository;
import com.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserBillingRepository userBillingRepository;
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	@Transactional
	@Override
	public User  createUser(User user, Set<UserRole> userRoles) {
		// TODO Auto-generated method stub
		User localUser=userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info("User with username {} already exists. Nothing will be done. ",user.getUsername());
		}else {
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			user.setUserPaymentList(new ArrayList<UserPayment>());
			localUser=userRepository.save(user);
		}
		return localUser;
	}


	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}


	@Override
	public User findByEmail(String userEmail) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(userEmail);
	}


	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}


	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}


	@Override
	public void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user) {
		// TODO Auto-generated method stub
		save(user);
		userBillingRepository.save(userBilling);
		userPaymentRepository.save(userPayment);
		
	}
	
	@Override
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		// TODO Auto-generated method stub
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDeafultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);
		
		
	}


	@Override
	public void setUserDefaultPayment(long userPaymentId, User user) {
		// TODO Auto-generated method stub
		List<UserPayment> userPaymentList=(List<UserPayment>) userPaymentRepository.findAll();
		for(UserPayment userPayment: userPaymentList) {
			if(userPayment.getId() == userPaymentId) {
				userPayment.setDeafultPayment(true);
				userPaymentRepository.save(userPayment);
			}else {
				userPayment.setDeafultPayment(false);
				userPaymentRepository.save(userPayment);
			}
		}
	}


	@Override
	public void updateUserShipping(UserShipping userShipping, User user) {
		// TODO Auto-generated method stub
		
		userShipping.setUser(user);
		userShipping.setUserShipingDefault(true);
		user.getUserShippingList().add(userShipping);
		save(user);
		
	}


	@Override
	public void setUserDefaultShipping(long userShippingId, User user) {
		// TODO Auto-generated method stub
		List<UserShipping> userShippingList=(List<UserShipping>) userShippingRepository.findAll();
		for(UserShipping userShipping: userShippingList) {
			if(userShipping.getId() == userShippingId) {
				userShipping.setUserShipingDefault(true);
				userShippingRepository.save(userShipping);
			}else {
				userShipping.setUserShipingDefault(false);
				userShippingRepository.save(userShipping);
			}
		}
	}
	

}
