package com.niitproject.DAOTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niitproject.DAO.UserDAO;
import com.niitproject.models.User;

public class UserDAOTest {

	static UserDAO userDAO;
    @BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niitproject");
		context.refresh();
		
		userDAO=(UserDAO)context.getBean("userDAO");
	}
	@Test
	public void addUserTest()
	{
		User user=new User();
		user.setMobileNo("7095766766");
		user.setPassword("Naveen@1");
		user.setUsername("Naveen123");
		user.setRole("ROLE_ADMIN");
		user.setEmail("naveen@gmail.com");
		user.setAddress("chennai");
		assertTrue("Problem in User Insertion",userDAO.addUser(user));
	}
    @Ignore
	@Test
	public void updateUserTest()
	{
		User user=userDAO.getUser(115);
	    user.setUsername("Kumar123");
	    user.setRole("ROLE_ADMIN");
		assertTrue("Problem in Updation",userDAO.updateUser(user));
	}
    @Ignore
    @Test
	public void listUserTest()
	{
		List<User> listUser=userDAO.getUser();
		assertNotNull("No User",listUser);
		
		for(User user:listUser)
		{
			System.out.print(user.getUsername()+" ");
			System.out.print(user.getEmail()+" ");
			
		}
     }
}
