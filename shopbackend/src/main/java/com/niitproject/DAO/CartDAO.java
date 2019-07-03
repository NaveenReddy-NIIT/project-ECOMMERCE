package com.niitproject.DAO;

import java.util.List;

import com.niitproject.models.Cart;

public interface CartDAO {
	public boolean addCart(Cart cart);
	public boolean updateCart(Cart cart);
	public boolean deleteCart(Cart cart);
	public List<Cart> getCarts(String username);
	public List<Cart> getPaidCarts(String username);
	Cart getCart(int cartId);
}
