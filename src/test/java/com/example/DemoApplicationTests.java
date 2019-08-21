package com.example;

import com.example.entity.User;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
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
		log.info("info");
		log.warn("warm");
		log.error("error");

		log.debug("debug");
		log.trace("trace");
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

	/**  Redis操作List   BEGIN  **/
	// list数据类型适合于消息队列的场景:比如12306并发量太高，而同一时间段内只能处理指定数量的数据！必须满足先进先出的原则，其余数据处于等待
	@Test
	public void testRedisForListPush(){
		//rightPush依次从右边添加
		stringRedisTemplate.opsForList().rightPush("myList","1");
		stringRedisTemplate.opsForList().rightPush("myList","2");
		stringRedisTemplate.opsForList().rightPush("myList","A");
		stringRedisTemplate.opsForList().rightPush("myList","B");
		//leftPush依次从左边添加
		stringRedisTemplate.opsForList().leftPush("myList","Ha");
		stringRedisTemplate.opsForList().leftPush("myList","A");

		this.testRedisForListGet();
	}

	@Test
	public void testRedisForListGet(){
		//查询类别所有元素
		List<String> listAll = stringRedisTemplate.opsForList().range("myList",0,-1);
		log.info("list all{}"+listAll);
		//查询前三个元素
		List<String> list = stringRedisTemplate.opsForList().range("myList",0,3);
		log.info("list limit{}"+list);
	}

	@Test
	public void testRedisForListRemoveOne(){
		//删除先进入的"B"元素
		stringRedisTemplate.opsForList().remove("myList",1,"B");

		this.testRedisForListGet();
	}

	@Test
	public void testRedisForListRemoveAll(){
		//删除所有的"A"元素
		stringRedisTemplate.opsForList().remove("myList",0,"A");
		stringRedisTemplate.opsForList().remove("myList",2,"2");

		this.testRedisForListGet();
	}

	/**  Redis操作List   END **/


	/**  Redis操作Hash   BEGIN  **/
	@Test
	public void testRedisForHashPut(){
		// map的key值相同，后添加的覆盖原有的
		stringRedisTemplate.opsForHash().put("banks:12306","a","123");
		stringRedisTemplate.opsForHash().put("banks:12306","b","233");
		stringRedisTemplate.opsForHash().put("banks:12306","c","344");
		stringRedisTemplate.opsForHash().put("banks:12306","d","455");

		this.testRedisForHashGetEntries();
	}

	@Test
	public void testRedisForHashGetEntries(){
		// 获取map对象
		Map<Object,Object> map = stringRedisTemplate.opsForHash().entries("banks:12306");
		log.info("objects {}"+map);
	}

	@Test
	public void testRedisForHashGetDelete(){
		// 根据map的key删除这个元素
		stringRedisTemplate.opsForHash().delete("banks:12306","c");

		this.testRedisForHashGetEntries();
	}

	@Test
	public void testRedisForHashGetKeySet(){
		//获取map的key集合
		Set<Object> set = stringRedisTemplate.opsForHash().keys("banks:12306");
		log.info("objects:{}", set);
	}

	@Test
	public void testRedisForHashGetValueList() {
		// 获得map的value列表
		List<Object> objects = stringRedisTemplate.opsForHash().values("banks:12306");
		log.info("objects:{}", objects);
	}

	@Test
	public void testRedisForGetHashSize() {
		// 获取map对象大小
		long size = stringRedisTemplate.opsForHash().size("banks:12306");
		log.info("size:{}", size);
	}

	/**  Redis操作Hash   END **/
}
