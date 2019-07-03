package com.niitproject.configure;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niitproject.DAO.CartDAO;
import com.niitproject.DAO.CartDAOimpl;
import com.niitproject.DAO.CategoryDAO;
import com.niitproject.DAO.CategoryDAOimpl;
import com.niitproject.DAO.OrderDetailDAO;
import com.niitproject.DAO.OrderDetailDAOimpl;
import com.niitproject.DAO.ProductDAO;
import com.niitproject.DAO.ProductDAOimpl;
import com.niitproject.DAO.SupplierDAO;
import com.niitproject.DAO.SupplierDAOimpl;
import com.niitproject.DAO.UserDAO;
import com.niitproject.DAO.UserDAOimpl;
import com.niitproject.models.Cart;
import com.niitproject.models.Category;
import com.niitproject.models.OrderDetail;
import com.niitproject.models.Product;
import com.niitproject.models.Supplier;
import com.niitproject.models.User;

@Configuration
@ComponentScan("com.niitproject.*")
@EnableTransactionManagement  

public class DBConfigure {
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		System.out.println("Data source");
	DriverManagerDataSource dataSource = new DriverManagerDataSource();   
	dataSource.setDriverClassName("org.h2.Driver");
	dataSource.setUrl("jdbc:h2:tcp://localhost/F:/H2/table1/table");	
	dataSource.setUsername("naveen");
	dataSource.setPassword("naveen");
	 
	    return dataSource;
   }
	
	private Properties getHibernateProperties() {	
		System.out.println(" Hibernate");
		Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.hbm2ddl.auto", "update");	
		return properties;

	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		System.out.println("Session factory");
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource); 
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(Category.class);
		sessionBuilder.addAnnotatedClasses(Supplier.class);          
		sessionBuilder.addAnnotatedClasses(Product.class);
		sessionBuilder.addAnnotatedClasses(Cart.class);
		sessionBuilder.addAnnotatedClasses(User.class);
		sessionBuilder.addAnnotatedClasses(OrderDetail.class);
		
		
		System.out.println("...SessionFactory Object created..");
		
		return sessionBuilder.buildSessionFactory();
	}
	
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) 
	{
	System.out.println("Transaction manager");
	HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	return transactionManager;
	}
	
	
	@Bean(name="categoryDAO") 
	public CategoryDAO getCategoryDAO() 
	{
		return new CategoryDAOimpl();
	} 
    

    @Bean(name="supplierDAO") 
    public SupplierDAO getSupplierDAO() 
    {
	return new SupplierDAOimpl();
	}
	
    @Bean(name="productyDAO") 
    public ProductDAO getProductDAO() 
    {
    	return new ProductDAOimpl();
    } 
   

	@Bean(name="cartDAO") 
	public CartDAO getCartDAO() {
		System.out.println("Cart DAO Implementation");
		return new CartDAOimpl();
	}
   
	@Bean(name="userDAO") 
	public UserDAO getUserDAO() {
		System.out.println("User DAO Implementation");
		return new UserDAOimpl();
	}
	@Bean(name="orderDetailDAO") 
	public OrderDetailDAO getOrderDetailDAO() 
	{
		System.out.println("OrderDetail DAO Implementation");
		return new OrderDetailDAOimpl();
	}
	
}
	
