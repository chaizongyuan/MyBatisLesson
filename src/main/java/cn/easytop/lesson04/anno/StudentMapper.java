package cn.easytop.lesson04.anno;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

public interface StudentMapper {

	static class StudentProvider{
		/**
		 * 提供sql语句的方法
		 * 
		 * 对象的属性取值：别名.属性名
		 * 提供SQL方法的参数是Map类型
		 * @param map
		 * @return
		 */
		public String queryStudentSql(Map map){
			Student student = (Student)map.get("stu");
			String sql = "select * from student where 1=1";
			if(student.getSname()!=null && !"".equals(student.getSname())){		
				//sql+=" and sname like '%${stu.sname}%'";
				student.setSname("%"+student.getSname()+"%");
				sql+=" and sname like #{stu.sname}";
			}
			
			if(student.getAddress()!=null && !"".equals(student.getAddress())){
				//sql+=" and address like '%${stu.address}%'";
				student.setAddress("%"+student.getAddress()+"%");
				sql+=" and address like #{stu.address}";
			}
			
			return sql;
		}
		
		
		/**
		 * 根据任意条件查询学生信息的SQL
		 * @param map
		 * @return
		 */
		public String queryStudentSql1(Map map){
			Student student = (Student)map.get("stu");
			SQL sql = new SQL();
			sql.SELECT("*").FROM("student");
			//String sql = "select * from student where 1=1";
			if(student.getSname()!=null && !"".equals(student.getSname())){						
				student.setSname("%"+student.getSname()+"%");
				sql.WHERE(" sname like #{stu.sname}");
			}
			
			if(student.getAddress()!=null && !"".equals(student.getAddress())){				
				student.setAddress("%"+student.getAddress()+"%");
				sql.AND();
				sql.WHERE(" address like #{stu.address}");
			}
			
			return sql.toString();
		}	
		
		
		/**
		 * 修改学生信息的SQL
		 * @param map
		 * @return
		 */
		public String updateStudentSql(Map map){
			Student student = (Student)map.get("stu");
			SQL sql = new SQL();
			sql.UPDATE("student");
			if(student.getSname()!=null && !"".equals(student.getSname())){
				sql.SET("sname=#{stu.sname}");
			}
			
			if(student.getAddress()!=null && !"".equals(student.getAddress())){
				sql.SET("address=#{stu.sname}");
			}
			sql.WHERE(" stuid=#{stu.stuid}");
			
			return sql.toString();
		}
		
		/**
		 * 根据任意班级查询学生的SQL
		 * @param map
		 * @return
		 */
		public String queryStudentByAnyGrade(Map map){
			List<String> list = (List<String>) map.get("gradeList");
			SQL sql = new SQL();
			sql.SELECT("*").FROM("student");
			StringBuilder temp = new StringBuilder();
			for (String str : list) {
				temp.append(str+",");
			}
			
			sql.WHERE("gid in("+temp.substring(0, temp.length()-1)+")");
			
			return sql.toString();
			
		}
		
		
	}
	
	
	
		
	
	
	@SelectProvider(type=StudentProvider.class,method="queryStudentSql")
	public List<Student> queryStudent(@Param("stu") Student student);
	
	/**
	 * 根据性别查所有学生
	 * 参数中传入sex  就根据条件查 没有传值 查所有女生
	 * @param sex
	 * @return
	 */
	public List<Student> queryBySex(@Param("sex") Integer sex);
	
	
	/**
	 * 更新学生信息
	 * @param student
	 */
	@UpdateProvider(type=StudentProvider.class,method="updateStudentSql")
	public void updateStudent(@Param("stu") Student student);
	
	/**
	 * 根据传入的班级查询学生
	 * @param gradeList
	 */
	
	@SelectProvider(type=StudentProvider.class,method="queryStudentByAnyGrade")
	public List<Student> queryStudentByAnyGrade(@Param("gradeList") List<String> gradeList);
}
