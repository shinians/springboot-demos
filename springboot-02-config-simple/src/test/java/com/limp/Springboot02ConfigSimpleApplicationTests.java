package com.limp;

import com.limp.bean.Student;
import com.limp.bean.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02ConfigSimpleApplicationTests {

	/**
	 * 注入student
	 */
	@Autowired
	Student student;

	@Autowired
	Teacher teacher;


	@Test
	public void testStudent(){
		System.out.println("/------------testStudent-----------------/");
		System.out.println(student);
	}

	/**
	 * 测试studentConfig
	 */
	@Test
	public void testTeacher(){
		System.out.println("/------------testTeacher-----------------/");
		System.out.println(teacher);
	}



	@Test
	public void contextLoads() {
	}

}

