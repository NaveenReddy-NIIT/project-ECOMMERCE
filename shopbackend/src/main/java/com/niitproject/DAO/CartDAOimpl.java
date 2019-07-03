package com.niitproject.DAO;

import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niitproject.models.Cart;

@Repository("cartDAO")
@Transactional
public class CartDAOimpl implements CartDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addCart(Cart cart) {
		try
		{
			sessionFactory.getCurrentSession().save(cart);
			return true;
		}
		catch(Exception e)
		{
			System.out.print("Exception arised "+e);
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try
		{
			sessionFactory.getCurrentSession().update(cart);
			return true;
		}
		catch(Exception e)
		{
			System.out.print("Exception arised "+e);
			return false;
		}
	}

	@Override
	public boolean deleteCart(Cart cart) {
		try
		{
			sessionFactory.getCurrentSession().delete(cart);
			return true;
		}
		catch(Exception e)
		{
			System.out.print("Exception arised "+e);
			return false;
		}
	}

	@Override
	public List<Cart> getCarts(String username) {
		Session session=sessionFactory.openSession();
		Query query = session.createQuery("from Cart where Username=:username and Paymentstatus='NP'");
		query.setParameter("username",username);
		List<Cart> listCart=(List<Cart>)query.list();
		return listCart;
	}
	
	@Override
	public List<Cart> getPaidCarts(String username) {
		Session session=sessionFactory.openSession();
		Query query = session.createQuery("from Cart where Username=:username and PaymentStatus='P'");
		query.setParameter("username",username);
		List<Cart> listCart=(List<Cart>)query.list();
		return listCart;
	}

	@Override
	public Cart getCart(int cartId) {
		Session session=sessionFactory.openSession();
		Cart cart=(Cart)session.get(Cart.class,cartId);
		session.close();
		return cart;
	}

}
