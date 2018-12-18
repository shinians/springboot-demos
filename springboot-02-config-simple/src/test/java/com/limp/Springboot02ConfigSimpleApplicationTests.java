package com.limp;

import com.limp.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02ConfigSimpleApplicationTests {

	@Autowired
	Student student;


	@Test
	public void testHelloService(){
		System.out.println("1234");
		System.out.println(student);
	}



	@Test
	public void contextLoads() {
	}

}

