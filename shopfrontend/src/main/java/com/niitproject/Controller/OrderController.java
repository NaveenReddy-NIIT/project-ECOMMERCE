package com.niitproject.Controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niitproject.DAO.CartDAO;
import com.niitproject.DAO.OrderDetailDAO;
import com.niitproject.DAO.ProductDAO;
import com.niitproject.models.Cart;
import com.niitproject.models.OrderDetail;


@Controller
public class OrderController {
@Autowired
OrderDetailDAO orderDetailDAO;

@Autowired
CartDAO cartDAO;

@Autowired
ProductDAO productDAO;

@RequestMapping(value="/confirmorder")
public String ConfirmOrderDetail(HttpSession session,Model m)
{ 
	String username=(String)session.getAttribute("username");
	List<Cart> listCarts=cartDAO.getCarts(username);
	m.addAttribute("listCarts",listCarts);
	m.addAttribute("grandtotal",grandTotal(listCarts));
	return "Order";
}


@RequestMapping(value="/paymentconfirm")
public String PaymentConfirm(HttpSession session,@RequestParam("payment")String payment,@RequestParam("ShippingAddress")String ShippingAddress,Model m) 
{
	String username=(String)session.getAttribute("username");
	List<Cart> listCarts=cartDAO.getCarts(username);
	for(Cart cart1:listCarts)
	{
			Cart cart2=cartDAO.getCart(cart1.getCartId());
			cart2.setPaymentStatus("P");
			cartDAO.updateCart(cart2);
	}
	
	List<Cart> listPaidCarts=cartDAO.getPaidCarts(username);
	m.addAttribute("listPaidCarts",listPaidCarts);
	m.addAttribute("grandtotal",grandTotal(listPaidCarts));
	
	OrderDetail orderdetail=new OrderDetail();
	orderdetail.setUsername(username);
	orderdetail.setOrderDate(String.format("%tc",new Date()));
	orderdetail.setTotalAmount(grandTotal(listPaidCarts));
	orderdetail.setTransactionType(payment);
	orderdetail.setShippingAddr(ShippingAddress);
	orderDetailDAO.confirmOrder(orderdetail);
	
	List<Cart> listpaidCarts=cartDAO.getPaidCarts(username);
	for(Cart cart3:listpaidCarts)
	{
			Cart cart4=cartDAO.getCart(cart3.getCartId());
			cart4.setPaymentStatus("Paid");
			cartDAO.updateCart(cart4);
	
	}
	List<OrderDetail> listOrderDetail=orderDetailDAO.getOrderDetail(username);
	
	int id=0;
	for(OrderDetail orderdetail1:listOrderDetail)
	{
			if(orderdetail.getOrderId()>id) {
				id=orderdetail.getOrderId();
			}
	}
	
	
	
	OrderDetail orderdetail2=orderDetailDAO.getOrderId(id); 
	m.addAttribute("orderdetail",orderdetail2);
	
	return "OrderDisplay";
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

public double grandTotals(List<Cart> listPaidCarts)
{

	double grandTotal1=0,grandTotal=0;
	try {
	for(Cart cart: listPaidCarts)
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
