package com.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.Book;
import com.bookstore.domain.BookToCartItem;
import com.bookstore.domain.CartItem;
import com.bookstore.domain.Order;
import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.User;
import com.bookstore.repository.BookToCartItemRepository;
import com.bookstore.repository.CartItemRepository;
import com.bookstore.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private BookToCartItemRepository bookToCartItemRepository;
	
	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}

	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		BigDecimal bigDecimal=new BigDecimal(cartItem.getBook().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
		
		bigDecimal=bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
		cartItem.setSubTotal(bigDecimal);
		cartItemRepository.save(cartItem);
		return cartItem;
	}

	@Override
	public CartItem addBookToCartItem(Book book, User user, int qty) {
		// TODO Auto-generated method stub
		List<CartItem> cartItemList= findByShoppingCart(user.getShoppingCart());
		
		for(CartItem cartItem: cartItemList) {
			if(book.getId() == cartItem.getBook().getId()) {
				cartItem.setQty(cartItem.getQty() + qty);
				cartItem.setSubTotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
		
		CartItem cartItem=new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setBook(book);
		
		cartItem.setQty(qty);
		cartItem.setSubTotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
		cartItem= cartItemRepository.save(cartItem);
		
		BookToCartItem bookToCartItem=new BookToCartItem();
		bookToCartItem.setBook(book);
		bookToCartItem.setCartItem(cartItem);
		bookToCartItemRepository.save(bookToCartItem);
		
		
		return cartItem;
	}

	@Override
	public CartItem findById(Long cartItemId) {
		// TODO Auto-generated method stub
		return cartItemRepository.findById(cartItemId).get();
	}

	@Override
	public void removeCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		bookToCartItemRepository.deleteBycartItem(cartItem);
		cartItemRepository.delete(cartItem);
		
	}

	@Override
	public CartItem save(CartItem cartItem) {
		// TODO Auto-generated method stub
		return cartItemRepository.save(cartItem);
		
	}

	@Override
	public List<CartItem> findByOrder(Order order) {
		// TODO Auto-generated method stub
		return cartItemRepository.findByOrder(order);
	}

}
