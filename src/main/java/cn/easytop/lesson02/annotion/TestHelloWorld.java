package cn.easytop.lesson02.annotion;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.easytop.lesson02.Food;

public class TestHelloWorld {

	public static SqlSession getSession() throws IOException{
		String resource = "cn/easytop/lesson02/annotion/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工廠類
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	
	@Test
	public void testAnnoInterface() throws IOException{
		SqlSession session =getSession();
		FoodMapper fm=session.getMapper(FoodMapper.class);
		List<Food> list=fm.queryFoodByFoodName("椒");
		System.out.println(list);
	}
	
	
	@Test
	public void deleteAnnoInterface() throws IOException{
		SqlSession session =getSession();
		FoodMapper fm=session.getMapper(FoodMapper.class);
		fm.deleteFood("4");
		session.commit();
	}
	
	@Test
	public void saveAnnoInterface() throws IOException{
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
