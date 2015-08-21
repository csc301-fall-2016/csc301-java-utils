package edu.toronto.csc301.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.Timeout;

public abstract class CSC301TestBase {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(2);

	
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
	
	
	protected void assertClassExists(String className) {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			fail("Please create the class " + className);
		}
	}
	
	
	protected void assertClassHasDefaultConstructor(String className) throws ClassNotFoundException{
		Class<?> clazz = Class.forName(className);
		try {
			clazz.newInstance();
		} catch (Exception e) {
			fail("Cannot create " + className + " instance - " + e.getMessage());
		}
	}
	
	
	protected void assertClassHasConstructor(String className, Class<?> ... constructorParamTypes) throws ClassNotFoundException{
		Class<?> clazz = Class.forName(className);
		try {
			clazz.getConstructor(constructorParamTypes);
		} catch (Exception e) {
			fail("Cannot create " + className + " instance - " + e.getMessage());
		}
	}
	
	
	protected void assertClassImplementsInterface(String className, Class<?> anInterface) throws ClassNotFoundException{
		Class<?> clazz = Class.forName(className);
		Assert.assertTrue(className + " should implement " + anInterface.getSimpleName(), anInterface.isAssignableFrom(clazz));
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	protected <T> T newInstance(String className, Class<?>[] constructorParamTypes, Object ... constructorParams) throws Exception {
		Class<?> clazz = Class.forName(className);
		try {
			return (T) clazz.getConstructor(constructorParamTypes).newInstance(constructorParams);
		} catch (Exception e) {
			throw (Exception) e.getCause();
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T newInstance(String className) throws Exception{
		Class<?> clazz = Class.forName(className);
		try {
			return (T) clazz.newInstance();
		} catch (Exception e) {
			throw (Exception) e.getCause();
		}
	}
	
	
	

}
