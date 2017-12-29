package cn.easytop.lesson05.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import cn.easytop.lesson05.JavaRedis;

public class RedisCache implements Cache {
	
	/**
	 * 操作Redis对象
	 */
	Jedis jedis = new Jedis("localhost",6379);
	
	private String cacheId;
	public RedisCache(String cacheId){
		this.cacheId=cacheId;
	}
	

	public void clear() {
		//jedis.flushDB();
	}

	public String getId() {
		return cacheId;
	}

	public Object getObject(Object key) {
		try {
			byte[] bt = jedis.get(JavaRedis.ObjectToByte(key));
			if(bt==null){
				return null;
			}
				//将byte数组反序列化成对象
				return JavaRedis.ByteToObject(bt);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ReadWriteLock getReadWriteLock() {
		return new ReentrantReadWriteLock();
	}
	
	public int getSize() {
		return 0;
	}

	/**
	 * 读取数据时 将数据库中读取的数据通过putObject设置到缓存中
	 */
	public void putObject(Object key, Object value) {
		//将查询到的数据写入Redis(需要先序列化)
		try {
			jedis.set(JavaRedis.ObjectToByte(key), JavaRedis.ObjectToByte(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object removeObject(Object key) {
		Object obj = getObject(key);
		
		try {
			jedis.del(JavaRedis.ObjectToByte(key));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

}
