package cn.easytop.lesson03.returnMap.xml;

import java.util.ArrayList;
import java.util.List;

public class Grade {
	private int gid;
	private String gname1;
	private List<Student> studentList=new ArrayList<Student>();
	public int getGid() {
		return gid;
	}
	public String getGname1() {
		return gname1;
	}
	public void setGname1(String gname1) {
		this.gname1 = gname1;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	
}
