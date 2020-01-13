package com.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.UserPayment;
import com.bookstore.repository.UserPaymentRespository;
import com.bookstore.service.UserPaymentService;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
	
	@Autowired
	private UserPaymentRespository userPaymentRepository;

	@Override
	public UserPayment findById(Long id) {
		// TODO Auto-generated method stub
		return userPaymentRepository.findById(id).get();
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		userPaymentRepository.deleteById(id);
		
	}

}
