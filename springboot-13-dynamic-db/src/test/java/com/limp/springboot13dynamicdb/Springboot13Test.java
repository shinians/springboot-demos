package com.limp.springboot13dynamicdb;

import com.limp.pojo.DynamicDataSourceEntity;
import com.limp.pojo.MyRowMapper;
import com.limp.pojo.TestPo;
import com.limp.utils.JdbcTemplateDynamic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot13Test {
	static String dbKey = "limp_db";

	//定义临时缓存|存放数据
	private  static Map<String,DynamicDataSourceEntity> mapEntity=new HashMap<>();
	@Test
	public void contextLoads() {
	}

	/**
	 * 定义多数据源配置
	 */
	@Before
	public void initDB(){
		DynamicDataSourceEntity dynamicSourceEntity = new DynamicDataSourceEntity();
		//以下配置信息应该在数据中获取
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.26.112:3306/test?userUnicode=true&characterEncoding=UTF-8&userSSL=false";
		String dbUser = "root";
		String dbPassword = "123@gla.net.cn";

		dynamicSourceEntity.setDbKey(dbKey);
		dynamicSourceEntity.setDriverClass(driverClassName);
		dynamicSourceEntity.setUrl(url);
		dynamicSourceEntity.setDbUser(dbUser);
		dynamicSourceEntity.setDbPassword(dbPassword);
		//将初始化好的  数据连接信息放在map当中|方便获取
		mapEntity.put(dbKey, dynamicSourceEntity);
	}



	/**
	 * 测试新增
	 * @throws Exception
	 */
	@Test
	public void testInsert() throws Exception {


		/*******************************************/
		String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();


		String sql = "insert test (id,name)values(?,'test-DynamicDBTest-insert') ";

		DynamicDataSourceEntity dynamicSourceEntity=mapEntity.get(dbKey);
		JdbcTemplateDynamic jdbcTemplateDynamic=new JdbcTemplateDynamic(dynamicSourceEntity);
		JdbcTemplate jdbcTemplate =jdbcTemplateDynamic.getJdbcTemplate();

		Integer	effectCount = jdbcTemplate.update(sql, id);
		System.out.println("-----------testInsert 成功---------");
	}

	/**
	 * 查询方法
	 */
	@Test
	public void  testGetList(){

		String sql = "select * from test";
		DynamicDataSourceEntity dynamicSourceEntity=mapEntity.get(dbKey);
		JdbcTemplateDynamic jdbcTemplateDynamic=new JdbcTemplateDynamic(dynamicSourceEntity);
		JdbcTemplate jdbcTemplate =jdbcTemplateDynamic.getJdbcTemplate();

		//调用方法获得list集合
		List<TestPo> testList = jdbcTemplate.query(sql, new MyRowMapper());
		System.out.println(testList);


		//
		String sqlOne = "select * from test where id = ?";
	       //根据id获取单个对象
	      TestPo testPo = jdbcTemplate.queryForObject(sqlOne, new MyRowMapper(), "C5828944E72D48B5B4BABCA2F7844EFA");

	      //！！！！这种用到比较多
		   List<Map<String, Object>> testListMap = jdbcTemplate. queryForList(sqlOne,"C5828944E72D48B5B4BABCA2F7844EFA");
		   System.out.println("获取单个对象");
		   System.out.println(testPo);


		//调用方法获得记录数
		String sqlGetNum = "select count(*) from test";
		int count = jdbcTemplate.queryForObject(sqlGetNum, Integer.class);
		System.out.println("数据总数：" + count);



		//[TestPo{id='2E747EB9BAC043CEA99F620578B3D6FF', name='DynamicDBTest-insert'}, TestPo{id='C8ACD8BCF6DD4177B6CAC9699509C816', name='DynamicDBTest-insert'}, TestPo{id='034AC4FDE2924D0EA304EE782312C3B6', name='DynamicDBTest-insert'}, TestPo{id='C5828944E72D48B5B4BABCA2F7844EFA', name='test-DynamicDBTest-insert'}]
	}



}
