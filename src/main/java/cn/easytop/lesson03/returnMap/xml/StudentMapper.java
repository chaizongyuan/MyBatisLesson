package cn.easytop.lesson03.returnMap.xml;


public interface StudentMapper {

	/**
	 * 通过编号查询学生
	 * @param sid
	 * @return
	 */
	public Student queryStudent(String sid);
	
}

