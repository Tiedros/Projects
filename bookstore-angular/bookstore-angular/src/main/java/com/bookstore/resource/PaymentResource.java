package com.bookstore.resource;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.User;
import com.bookstore.domain.UserBilling;
import com.bookstore.domain.UserPayment;
import com.bookstore.service.UserPaymentService;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/payment")
public class PaymentResource {
	private static final Logger LOG=LoggerFactory.getLogger(PaymentResource.class.getName());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPaymentService userPaymentService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity addNewCreditCardPost(
				@RequestBody UserPayment userPayment,
				Principal principal
			) {
		LOG.info("******** IN Side Method {} ********","addNewCreditCardPost" );
		User user=userService.findByUsername(principal.getName());
		
		UserBilling userBilling = userPayment.getUserBilling();
		userService.updateUserBilling(userBilling,userPayment,user);
		//return new ResponseEntity("Payment Added(Updated) Successfully!",HttpStatus.OK );
		return new ResponseEntity(HttpStatus.OK );
		
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public ResponseEntity removePaymentPost(
				@RequestBody String id,
				Principal principal
			) {
		LOG.info("******** IN Side Method {} ********","removePaymentPost" );
		userPaymentService.removeById(Long.parseLong(id));
		//return new ResponseEntity("Payment Removed Successfully!",HttpStatus.OK );
		return new ResponseEntity(HttpStatus.OK );
	}

	@RequestMapping(value="/setDefault",method=RequestMethod.POST)
	public ResponseEntity setDefaultPaymentPost(
				@RequestBody String id,
				Principal principal
			) {
		LOG.info("******** IN Side Method {} ********","setDefaultPaymentPost" );
		User user=userService.findByUsername(principal.getName());
		
		userService.setUserDefaultPayment(Long.parseLong(id),user);
		//return new ResponseEntity("Payment Removed Successfully!",HttpStatus.OK );
		return new ResponseEntity(HttpStatus.OK );
	}
	

	@RequestMapping(value="/getUserPaymentList",method=RequestMethod.GET)
	public List<UserPayment> getUserPaymentList(
				Principal principal
			) {
		LOG.info("******** IN Side Method {} ********","getUserPaymentList" );
		User user=userService.findByUsername(principal.getName());
		
		return user.getUserPaymentList();
	
	}
}
