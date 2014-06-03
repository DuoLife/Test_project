package memcached;

import junit.framework.Assert;

import org.junit.Test;

public class MemcachedUtilTest {

	//@Test
//	public void testMemcached() {
//		Memcachedutil.put("hello", "world", 60);
//		String hello = (String) Memcachedutil.get("hello");
//		Assert.assertEquals("world", hello);
//	}
	public static void main(String[] args) {
		TestBean bean = new TestBean("testBean", "123456");
		System.out.println(Memcachedutil.put("hello", bean, 60));
		TestBean hello = (TestBean) Memcachedutil.get("hello");
		System.out.println(hello.toString());
		//Assert.assertEquals("world", hello);
	}
}
