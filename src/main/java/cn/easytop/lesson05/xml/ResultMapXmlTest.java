package cn.easytop.lesson05.xml;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class ResultMapXmlTest {
	
	public static SqlSessionFactory getFactory() throws IOException{
		//指定核心配置文件mybatis.xml的路径
		String resource = "cn/easytop/lesson05/xml/mybatis.xml";
		//通过Resources 加载文件流
		InputStream is = Resources.getResourceAsStream(resource);
		//构建一个工厂类
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		return factory;
	}
	
	public static SqlSession getSession() throws IOException{
		//指定核心配置文件mybatis.xml的路径
		String resource = "cn/easytop/lesson05/xml/mybatis.xml";
		//通过Resources 加载文件流
		InputStream is = Resources.getResourceAsStream(resource);
		//构建一个工厂类
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		return session;
	}
	
	/**
	 * 一级缓存 
	 * @throws IOException
	 */
	@Test
	public void testXmlInterface() throws IOException{
		SqlSession session = getSession();
		
		StudentMapper fm = session.getMapper(StudentMapper.class);
		Student stu = fm.queryStudentById("1");
		//（一级缓存）从缓存中查询的
		Student stu1 = fm.queryStudentById("1");
		System.out.println(stu==stu1);
	}
	
	/**
	 * 二级缓存 ：同一个SessionFactory 下的不同session 可以共享数据
	 * @throws IOException
	 */
	@Test
	public void testXml() throws IOException{
		SqlSessionFactory factory = getFactory();
		//在同一个factory中
		SqlSession session1 = factory.openSession();
		SqlSession session2 = factory.openSession();
		
		StudentMapper sm1 = session1.selectOne("cn.easytop.lesson05.xml.StudentMapper.queryStudentById",1);
		
		session1.close();
		StudentMapper sm2 = session2.selectOne("cn.easytop.lesson05.xml.StudentMapper.queryStudentById",1);
		
		
		
	}
	
	
}
