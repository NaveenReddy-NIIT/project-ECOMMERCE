package com.niitproject.DAOTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niitproject.DAO.CartDAO;
import com.niitproject.models.Cart;

public class CartDAOTest {


	static CartDAO cartDAO;
	@SuppressWarnings("resource")
	@BeforeClass
	public static void executefirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niitproject");
		context.refresh();
		cartDAO=(CartDAO)context.getBean("cartDAO");
}
	
	@Test
	public void addcarttest() {
		Cart cart=new Cart();
		cart.setProductId(62);
		cart.setTotal(8000);
		cart.setQuantity(1);
		cart.setProductName("OPPO");
		cart.setUsername("Praveen");
		cart.setPaymentStatus("NP");
		assertTrue("problem in adding",cartDAO.addCart(cart));
	}
	@Ignore
	@Test
	public void getCartTest()
	{
		assertNotNull("Problem in get Category",cartDAO.getCart(2));
	}
	@Ignore
	@Test
	public void deleteCartTest()
	{
		Cart cart=cartDAO.getCart(110);
		assertTrue("Problem in Deletion:",cartDAO.deleteCart(cart));
	}
	@Ignore
	@Test
	public void updateCartTest()
	{
		Cart cart=cartDAO.getCart(111);
		cart.setQuantity(6);
		assertTrue("Problem in Updation",cartDAO.updateCart(cart));
	}
	@Ignore
	@Test
	public void listCartTest()
	{
		List<Cart> listCarts=cartDAO.getCarts("joe");
		assertNotNull("No Cart",listCarts);
		
		for(Cart cart:listCarts)
		{
			System.out.print(cart.getCartId()+" ");
			System.out.print(cart.getProductId()+" ");
			System.out.println(cart.getProductName());
		}
	}
}
