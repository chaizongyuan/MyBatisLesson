package cn.easytop.lesson04.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class ResultMapXmlTest {
	
	public static SqlSession getSession() throws IOException{
		//指定核心配置文件mybatis.xml的路径
		String resource = "cn/easytop/lesson04/xml/mybatis.xml";
		//通过Resources 加载文件流
		InputStream is = Resources.getResourceAsStream(resource);
		//构建一个工厂类
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		return session;
	}
	
	
	@Test
	public void testXmlInterface() throws IOException{
		SqlSession session = getSession();
		//获取映射接口的实现类
		StudentMapper fm = session.getMapper(StudentMapper.class);
		Student stu = new Student();
		stu.setSname("王");
		stu.setAddress("北京");
		List<Student> list = fm.queryStudent(stu);	
		for(Student s:list){
			System.out.println(s.getSname());
		}
		System.out.println(list.size());
	}

	
	@Test
	public void testChooseXml() throws IOException{
		SqlSession session = getSession();
		//获取映射接口的实现类
		StudentMapper fm = session.getMapper(StudentMapper.class);
		Integer sex = null;
		List<Student> list = fm.queryBySex(sex);	
		for(Student s:list){
			System.out.println(s.getSname());
		}
		System.out.println(list.size());
	}
	
	@Test
	public void testUpdateXml() throws IOException{
		SqlSession session = getSession();
		//获取映射接口实现类
		StudentMapper sm = session.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setStuid(1);
		student.setSname("马三");
		sm.updateStudent(student);
		session.commit();
	}
	
	@Test
	public void testQueryByAnyGrade() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);
		List list = new ArrayList();
		list.add("1");
		list.add("2");
		List<Student> li = sm.queryStudentByAnyGrade(list);
		for (Student stu : li) {
			System.out.println(stu.getSname());
		}
	}
}
