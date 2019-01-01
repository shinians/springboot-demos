package com.limp;

import com.limp.service.ApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot12ResttempleApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	ApiService apiService;
	@Test
	public  void testGet(){
		apiService.getObjectByGet();
	}
	@Test
	public  void testGet2(){
		apiService.getForEntityByGet();
	}
	@Test
	public  void testPost(){
		apiService.getObjectByPost();
	}
	@Test
	public  void testPut(){
		apiService.getObjectByPut();
	}
	@Test
	public  void testDel(){
		apiService.getObjectByDel();
	}
}

