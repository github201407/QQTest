package com.example.qqtest.test;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 标注原理
 * @author Administrator
 *
 */
public class StudentTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 运行时 Annotation 解析
	 * 
	 * 如 Field，Class 方法类似 
	 * getAnnotation(AnnotationName.class) 表示得到该 Target 某个	Annotation 的信息，因为一个 Target 可以被多个 Annotation 修饰 
	 * getAnnotations() 则表示得到该Target 所有 Annotation 
	 * isAnnotationPresent(AnnotationName.class) 表示该 Target是否被某个 Annotation 修饰
	 */
	@Test
	public void test() {
		try {
			Class cls = Class.forName("com.example.qqtest.test.Student");
			for (Method method : cls.getMethods()) {
				MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
				if (methodInfo != null) {
					System.out.println("method name:" + method.getName());
					System.out.println("method author:" + methodInfo.author());
					System.out.println("method version:" + methodInfo.version());
					System.out.println("method date:" + methodInfo.date());
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
