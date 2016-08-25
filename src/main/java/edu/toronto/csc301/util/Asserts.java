package edu.toronto.csc301.util;

import static org.junit.Assert.fail;

import java.util.StringJoiner;

import org.junit.Assert;


public class Asserts {

	
	/* 
	 * NOTE/DISCLAIMER TO STUDENTS:
	 * 
	 * We are using an advanced technique (called reflections) to check
	 * the internals of your code.     
	 * Reflections is an advanced technique, and you should **NOT** use it
	 * unless you understand it properly.
	 * 
	 * In fact, some of the things we are doing here (e.g. creating 
	 * instances using reflections) would be a terrible idea in a real application, 
	 * and they only make sense in the context of this assignment.  
	 * 
	 * In other words - Please do not use what you see here in a real 
	 * software project.
	 */
	

	/**
	 * Assert that a class called <code>className</code> is defined. 
	 * 
	 * @param className The full name of the class (including the package)
	 */
	public static void assertClassExists(String className) {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			fail("Please create the class " + className);
		}
	}
	
	
	/**
	 * Assert that the class called <code>className</code> has a default
	 * constructor (i.e. A constructor that takes 0 arguments). 
	 * 
	 * @param className The full name of the class (including the package)
	 */
	public static void assertClassHasDefaultConstructor(String className) throws ClassNotFoundException{
		Class<?> clazz = Class.forName(className);
		try {
			clazz.newInstance();
		} catch (Exception e) {
			fail(className + " should define default constructor.\n" + e.getMessage());
		}
	}
	
	
	/**
	 * Assert that the class called <code>className</code> has a
	 * constructor that takes arguments with the specified <code>constructorParamTypes</code>. 
	 * 
	 * @param className The full name of the class (including the package)
	 */
	public static void assertClassHasConstructor(String className, 
			Class<?> ... paramTypes) throws ClassNotFoundException{
		
		Class<?> clazz = Class.forName(className);
		try {
			clazz.getConstructor(paramTypes);
		} catch (Exception e) {
		  StringJoiner joiner = new StringJoiner(", ");
		  for(Class<?> c: paramTypes) {
		    joiner.add(c.getName());
		  }
		  
		  fail(String.format("%s should define constructor with %d parameters: %s.", 
				  className, paramTypes.length, joiner.toString()));
		}
	}
	
	
	/**
	 * Assert that the class called <code>className</code> implements <code>anInterface</code>. 
	 * 
	 * @param className The full name of the class (including the package)
	 * @param anInterface An interface
	 */
	public static void assertClassImplementsInterface(String className, 
								Class<?> anInterface) throws ClassNotFoundException{
		Class<?> clazz = Class.forName(className);
		Assert.assertTrue(className + " should implement " + anInterface.getSimpleName(), 
				anInterface.isAssignableFrom(clazz));
	}
	
	

}
