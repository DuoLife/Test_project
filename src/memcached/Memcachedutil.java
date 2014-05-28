package memcached;

import java.io.IOException;

import net.spy.memcached.MemcachedClient;

public class Memcachedutil {

	public static void main(String[] args) {
		
		try {
			MemcachedClient cachedClient = new MemcachedClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
