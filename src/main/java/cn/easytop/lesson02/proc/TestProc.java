package cn.easytop.lesson02.proc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import cn.easytop.lesson02.Food;

public class TestProc {

	public static SqlSession getSession() throws IOException{
		String resource = "cn/easytop/lesson02/proc/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工廠類
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	
	@Test
	public void testProcInterface() throws IOException{
		SqlSession session =getSession();
		Map map=new HashMap();
		map.put("p1", 1111);
		map.put("p2", 1212);
		map.put("result", 0);
		session.selectOne("proc.call_prg_add",map);
		System.out.println(map.get("result"));
	}
	
	
	@Test
	public void testFunInterface() throws IOException{
		SqlSession session =getSession();
		Map map=new HashMap<String, Integer>();
		map.put("p1", 1111);
		map.put("p2", 1212);
		map.put("result", 0);
		session.selectOne("proc.call_fun_add",map);
		System.out.println(map.get("result"));
	}
	

}
