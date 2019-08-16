package com.example;

import com.example.entity.User;
import com.example.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
		User user = new User();
		//user.setId(5);
		user.setUserName("张三");
		user.setPassWord("123");
		user.setRealName("张三");

		userService.insertData(user);
	}

	@Test
	public void testRedisForSaveString(){

		//保存字符串
		stringRedisTemplate.opsForValue().set("aaa","111");
		System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
	}

	@Test
	public void testRedisForSaveCharacter(){
		User user = new User();
		user.setId(1);
		user.setUserName("张三");
		user.setPassWord("123");
		user.setRealName("张三丰");
		//保存对象
		redisTemplate.opsForValue().set("user_1",user);

		System.out.println(redisTemplate.opsForValue().get("user_1"));
	}

}
