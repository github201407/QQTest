package com.example.qqtest.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.example.qqtest.utils.MD5Util;

import junit.framework.TestCase;

public class MD5UtilTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testgetFileMD5String() {
		String expect = "a384b6463fc216a5f8ecb6670f86456a";
		String actual = MD5Util.getStringMD5("qwert");
		assertEquals(expect, actual);
	}

	/* 数据缓存策略 ObjectOutputStream 存储对象到txt文件*/
	public void testObejectOutputStream() throws IOException {
		Student s1 = new Student("张三", 1, 15, "化学");
		Student s2 = new Student("李四", 2, 19, "生物");

		FileOutputStream fout = new FileOutputStream("student.txt");
		ObjectOutputStream out = new ObjectOutputStream(fout);
		out.writeObject(s1);
		out.writeObject(s2);
		out.close();
		FileInputStream fin = new FileInputStream("student.txt");
		ObjectInputStream in = new ObjectInputStream(fin);
		System.out.println(in.available());
		File file = new File("student.txt");
		System.out.println(file.length());
		try {
			s1 = (Student) in.readObject();
			s2 = (Student) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
		System.out.print("name:" + s1.name);
		System.out.print(" id:" + s1.id);
		System.out.print(" age:" + s1.age);
		System.out.println(" department:" + s1.department);
		System.out.print("name:" + s2.name);
		System.out.print(" id:" + s2.id);
		System.out.print(" age:" + s2.age);
		System.out.println(" department:" + s2.department);
	}
}
