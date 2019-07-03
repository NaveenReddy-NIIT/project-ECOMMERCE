package com.niitproject.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niitproject.DAO.CartDAO;
import com.niitproject.DAO.ProductDAO;
import com.niitproject.models.Cart;
import com.niitproject.models.Product;

@Controller
public class CartController {


	@Autowired
	ProductDAO productDAO;
	@Autowired
	CartDAO cartDAO;
	
	@RequestMapping(value="/Cart")
	public String displaycart(HttpSession session,Model m) {
		String username=(String)session.getAttribute("username");
		List<Cart> listCarts=cartDAO.getCarts(username);
		m.addAttribute("listCarts", listCarts);
		m.addAttribute("grandtotal",grandTotal(listCarts));
		return "Cart";
	}

	@RequestMapping(value = "/addCart/{productId}",method = RequestMethod.POST)
	public String addCart(@PathVariable("productId") int proid,HttpSession session,Model m) {
		Product product=productDAO.getProduct(proid);
		m.addAttribute("product",product);
		
		boolean id=false;
		String username=(String)session.getAttribute("username");
		System.out.println("username:"+username);
        
		if(username==null) {
        	return "login";
        }
		List<Cart> listCarts=cartDAO.getCarts(username);
		for(Cart cart1: listCarts) {
			if(proid==cart1.getProductId()) {
				id=true;
				break;
			}
		}
		
		if(id==false) {
	
	    Cart cart=new Cart();
		cart.setProductId(proid);
		cart.setQuantity(1);
		cart.setUsername(username);
		cart.setProductName(product.getProductName());
		cart.setPaymentStatus("NP");
		cart.setTotal(product.getPrice());
		cartDAO.addCart(cart);
		m.addAttribute("listCarts", listCarts);
		m.addAttribute("grandtotal",grandTotal(listCarts));
		return "Cart";
		}
		else {
			m.addAttribute("alert","This product is already added to cart");
		}
		
		return "ProductDesc";
	}
	

	@RequestMapping(value="/deletecart/{cartId}")
	public String deletecart(@PathVariable("cartId") int cartid,HttpSession session,Model m) {
		String username=(String)session.getAttribute("username");
	Cart cart=cartDAO.getCart(cartid);
    cartDAO.deleteCart(cart);
    List<Cart> listCarts=cartDAO.getCarts(username);
	m.addAttribute("listCarts", listCarts);
	m.addAttribute("grandtotal",grandTotal(listCarts));
		return "Cart";
	}
	
	@RequestMapping(value="/updatecart/{cartId}")
	public String updatecart(@PathVariable("cartId") int cartid,@RequestParam("quantity") int quantity,HttpSession session,Model m) {
		String username=(String)session.getAttribute("username");
	Cart cart=cartDAO.getCart(cartid);
	Product product=productDAO.getProduct(cart.getProductId());
	if(quantity>5) {
		quantity=5;
		m.addAttribute("Cart",cart);
		m.addAttribute("alert","quantity can't be more than 5");
	}
	cart.setQuantity(quantity);
	int quant=(int)((quantity*product.getPrice())*100);
	double qua=(double)quant/100;
	cart.setTotal(qua);
    cartDAO.updateCart(cart);
    List<Cart> listCarts=cartDAO.getCarts(username);
	m.addAttribute("listCarts", listCarts);
	m.addAttribute("grandtotal",grandTotal(listCarts));
	
		return "Cart";
	}
	
	public double grandTotal(List<Cart> listCarts)
	{
	
		double grandTotal1=0,grandTotal=0;
		try {
		for(Cart cart: listCarts)
		{
			grandTotal1=grandTotal1+cart.getQuantity()*(productDAO.getProduct(cart.getProductId()).getPrice());
			
		}
		int grandTotal2=(int)(grandTotal1*100);
		grandTotal=(double)grandTotal2/100; 
		}
		catch(Exception e) {
			System.out.println("total error");
		}
		
		return grandTotal;
	}
}
