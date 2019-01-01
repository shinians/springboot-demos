package com.limp.springboot10emailasync;

import com.limp.async.SleepService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot10EmailAsyncApplicationTests {
	@Autowired
	SleepService sleepService;

	@Test
	public void contextLoads() {
	}
	@Test
	public void sendSimpleMail() throws Exception {
		sleepService.sendSimpleMail();
	}

	/**
	 * 发送包含HTML文本的邮件
	 * @throws Exception
	 */
	@Test
	public void sendHtmlMail() throws Exception {
		sleepService.sendHtmlMail();
	}



	/**
	 * 发送包含附件的邮件
	 * @throws Exception
	 */
	@Test
	public void sendAttendedFileMail() throws Exception {
		sleepService.sendAttendedFileMail();
	}

}

