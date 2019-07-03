package com.niitproject.DAOTest;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niitproject.DAO.OrderDetailDAO;
import com.niitproject.models.OrderDetail;

public class OrderDetailDAOTest {

	static OrderDetailDAO orderDetailDAO;
	
	@BeforeClass
	public static void executefirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niitproject");
		context.refresh();
		orderDetailDAO=(OrderDetailDAO)context.getBean("orderDetailDAO");
	}
	@Test
	public void confirmOrderDetail() {
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setOrderId(1);
			orderDetail.setUsername("Praveen");
			orderDetail.setTotalAmount(10000);
			orderDetail.setShippingAddr("chennai");
			orderDetail.setTransactionType("COD");
			orderDetail.setOrderDate(String.format("%tc",new Date()));
			assertTrue("problem in adding",orderDetailDAO.confirmOrder(orderDetail));
	}
	}

