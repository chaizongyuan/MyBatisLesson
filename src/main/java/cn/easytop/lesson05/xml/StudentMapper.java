package cn.easytop.lesson05.xml;


public interface StudentMapper {
	/**
	 * 根据学生编号查询
	 * @param stuid
	 * @return
	 */
	public Student queryStudentById(String stuid);
	
}
