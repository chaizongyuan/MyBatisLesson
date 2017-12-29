package cn.easytop.lesson02.xml;

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

public class TestHelloWorld {

	public static SqlSession getSession() throws IOException{
		String resource = "cn/easytop/lesson02/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工廠類
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	
	@Test
	public void testXmlInterface() throws IOException{
		SqlSession session =getSession();
		FoodMapper fm=session.getMapper(FoodMapper.class);
		List list=fm.queryFoodByFoodName("椒");
		session.commit();
	}
	
	
	@Test
	public void deleteXmlInterface() throws IOException{
		SqlSession session =getSession();
		FoodMapper fm=session.getMapper(FoodMapper.class);
		fm.deleteFood("4");
		session.commit();
	}
	
	
	@Test
	public void saveXmlInterface() throws IOException{
		SqlSession session =getSession();
		FoodMapper fm=session.getMapper(FoodMapper.class);
		Food food=new Food();
		food.setFoodName("啊啊不错");
		food.setPrice("10");
		fm.saveFood(food);
		session.commit();
		System.out.println(food.getFoodId());
	}

}
