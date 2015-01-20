package com.ect.bigjump.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.User;
import com.ect.bigjump.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath*:config/bigjump-core.xml"}) 
public class TestUserService {

	@Autowired
	private UserService userService;

	@Autowired
	private MailSender mailSender;
	
	@Test
	public void add() throws Exception{
		User user = new User();
		user.setFirstName("Shawn");
		user.setLastName("Yu");
		user.setUserName("syu");
		user.setPassword("123456");

		userService.add(user);

	}
	
	@Test
	public void queryForPage(){
		Page<User> userPage = userService.queryForPage(1, null);
		userPage.getAllRow();
	}

	@Test
	public void TestMail(){
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("shawn_yu01@163.com");
		mail.setFrom("shawn_yu01@163.com");
		mail.setText("Test");
		mail.setSubject("Test");
		mailSender.send(mail);
	}
}
