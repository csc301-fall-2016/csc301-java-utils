package edu.toronto.csc301.util;

public class Helpers {


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
	 * Create (and return) a new instance of a class called <code>className</code> (a String).
	 * The instance will be created using the default constructor (i.e. constructor that
	 * takes 0 arguments).
	 * 
	 * In general, it is a bad idea to instantiate objects this way, but 
	 * it works well for our special use case - We use Unit Tests to specify 
	 * to students which classes we expect them to create. 
	 * 
	 * @param className The full name of the class (including the package).
	 * 
	 * @return A new instance
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className) throws Exception{
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
	public static <T> T newInstance(String className, Class<?>[] constructorParamTypes, Object ... constructorArgs) throws Exception {
		Class<?> clazz = Class.forName(className);
		try {
			return (T) clazz.getConstructor(constructorParamTypes).newInstance(constructorArgs);
		} catch (Exception e) {
			throw (Exception) e.getCause();
		}
	}
	
}
