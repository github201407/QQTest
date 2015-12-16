package com.example.qqtest.test;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7836668400563999008L;
	String name;
	int id;
	int age;
	String department;
	public Student(){
		
	}
	public Student(String name, int id, int age, String department) {
		this.age = age;
		this.department = department;
		this.id = id;
		this.name = name;
	}
	@MethodInfo(author="mngqn",date="2015/08/06",version=1)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
