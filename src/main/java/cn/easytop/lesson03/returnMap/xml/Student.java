package cn.easytop.lesson03.returnMap.xml;
/**
 * 多对一
 * @author Administrator
 */
public class Student {
	private String sid;
	private String sname;
	private Grade grade;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
}
