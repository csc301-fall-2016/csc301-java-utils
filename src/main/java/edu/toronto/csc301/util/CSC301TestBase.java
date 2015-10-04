package edu.toronto.csc301.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.Timeout;

/**
 * A base class for JUnit test cases, containing a few convenience functions
 * and helpers.
 * 
 * @author Joey Freund
 */
public abstract class CSC301TestBase {

	/**
	 * By default, don't let a single test method run for more than 2 seconds.
	 */
	@Rule
    public Timeout globalTimeout = Timeout.seconds(2);


	/**
	 * Convenience method.
	 * 
	 * Assert that the the given <code>set</code> contains all of
	 * the given <code>items</code> and nothing else.
	 * 
	 * @param set A set of string.
	 * @param items All the Strings we expect to be in <code>set</code>.
	 * 
	 * @throws AssertionError If the items of <code>set</code> are not the given <code>items</code>
	 */
	public void assertSetItemsAre(Set<String> set, String ... items){
		assertEquals(new HashSet<String>(Arrays.asList(items)), set);
	}
	
	
	//=========================================================================
	
	/*
	 * Internal utilities that allow us to easily test the structure of 
	 * the code.
	 * 
	 * 
	 * NOTE/DISCLAIMER TO STUDENTS:
	 * 
	 * We are using an advanced technique (called reflections) to check
	 * the internals of your code.     
	 * Reflections is an advanced technique, and you should **NOT** use it
	 * unless you understand it properly.
	 * 
	 * In fact, some of the things we are doing here (for example, creating 
	 * instances using reflections) are very bad, and they only make sense
	 * in the context of this assignment.  
	 * 
	 * In other words - Please do not use what you see here in a real 
	 * software project.
	 */
	
	
	/**
	 * Assert that a class called <code>className</code> is defined. 
	 * 
	 * @param className The full name of the class (including the package)
	 */
	protected void assertClassExists(String className) {
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
	protected void assertClassHasDefaultConstructor(String className) throws ClassNotFoundException{
		Class<?> clazz = Class.forName(className);
		try {
			clazz.newInstance();
		} catch (Exception e) {
			fail( className + " should define default constructor - " + e.getMessage());
		}
	}
	
	
	/**
	 * Assert that the class called <code>className</code> has a
	 * constructor that takes arguments with the specified <code>constructorParamTypes</code>. 
	 * 
	 * @param className The full name of the class (including the package)
	 */
	protected void assertClassHasConstructor(String className, Class<?> ... constructorParamTypes) throws ClassNotFoundException{
		Class<?> clazz = Class.forName(className);
		try {
			clazz.getConstructor(constructorParamTypes);
		} catch (Exception e) {
		  StringJoiner sigJoiner = new StringJoiner(",", "(", ")");
		  for(Class<?> c: constructorParamTypes) {
		    sigJoiner.add(c.getName());
		  }
		fail(className + " should define constructor with parameters - " + sigJoiner 
		    + " no such method: " + e.getMessage());
		}
	}
	
	
	/**
	 * Assert that the class called <code>className</code> implements <code>anInterface</code>. 
	 * 
	 * @param className The full name of the class (including the package)
	 * @param anInterface An interface
	 */
	protected void assertClassImplementsInterface(String className, Class<?> anInterface) throws ClassNotFoundException{
		Class<?> clazz = Class.forName(className);
		Assert.assertTrue(className + " should implement " + anInterface.getSimpleName(), anInterface.isAssignableFrom(clazz));
	}
	
	
	/**
	 * Create (and return) a new instance of a class called <code>className</code> (a String).
	 * The instance will be created using the default constructor (i.e. constructor that
	 * takes 0 arguments).
	 * 
	 * In general, it is a bad idea to instantiate objects this way, but 
	 * it works well for our special use case - We use Unit Tests to specify 
	 * to students which classes we expect them to create. 
	 * 
	 * @param className The full name of the class (including the package)
	 * 
	 * @return A new instance
	 */
	@SuppressWarnings("unchecked")
	protected <T> T newInstance(String className) throws Exception{
		Class<?> clazz = Class.forName(className);
		try {
			return (T) clazz.newInstance();
		} catch (Exception e) {
			throw (Exception) e.getCause();
		}
	}
	
	
	/**
	 * Create (and return) a new instance of a class called <code>className</code> (a String).
	 * 
	 * In general, it is a bad idea to instantiate objects this way, but 
	 * it works well for our special use case - We use Unit Tests to specify 
	 * to students which classes we expect them to create. 
	 * 
	 * @param className The full name of the class (including the package)
	 * @param constructorParamTypes Determine which constructor to use, in order to create the new instance
	 * @param constructorArgs The arguments that will be passed to the constructor
	 * 
	 * @return A new instance
	 */
	@SuppressWarnings("unchecked")
	protected <T> T newInstance(String className, Class<?>[] constructorParamTypes, Object ... constructorArgs) throws Exception {
		Class<?> clazz = Class.forName(className);
		try {
			return (T) clazz.getConstructor(constructorParamTypes).newInstance(constructorArgs);
		} catch (Exception e) {
			throw (Exception) e.getCause();
		}
	}
	
	
	
	
	
	

}
