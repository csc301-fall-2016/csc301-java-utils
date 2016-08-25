package edu.toronto.csc301.util.test;

import org.junit.Test;

import static edu.toronto.csc301.util.Asserts.*;

public class AssertsTest {

  // An extremely incomplete set of tests to show how you might use this library ...
	
  @Test
  public void test1() throws ClassNotFoundException {
    assertClassHasConstructor("java.lang.String",String.class); 
  }
  
  @Test (expected = AssertionError.class)
  public void test2() throws ClassNotFoundException {
    assertClassHasConstructor("java.lang.String",String.class, String.class); 
  }
  
  @Test
  public void test4() throws ClassNotFoundException {
    //pick on BigDecimal merely because it's one of few Constructors with two parms
    assertClassHasConstructor("java.math.BigDecimal", java.math.BigInteger.class, Integer.TYPE);
  }
  
  @Test (expected = AssertionError.class)
  public void test5() throws ClassNotFoundException {
    //this would be if the BigDecimal constructor second parm took Integer rather than int
    assertClassHasConstructor("java.math.BigDecimal", java.math.BigInteger.class, Integer.class);
  }  
  
  

}
