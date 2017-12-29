package cn.easytop.lesson04.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StudentMapper {

	
	public List<Student> queryStudent(Student student);
	
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
	public void updateStudent(Student student);
	
	/**
	 * 根据传入的班级查询学生
	 * @param gradeList
	 */
	public List<Student> queryStudentByAnyGrade(@Param("gradeList") List<String> gradeList);
}
