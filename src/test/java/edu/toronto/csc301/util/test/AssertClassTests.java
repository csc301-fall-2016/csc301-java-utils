package edu.toronto.csc301.util.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.toronto.csc301.util.CSC301TestBase;

public class AssertClassTests extends CSC301TestBase {

  @Before
  public void setUp() throws Exception {}

  @After
  public void tearDown() throws Exception {}

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
