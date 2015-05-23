package org.phoenix.action;

import org.junit.Assert;

public class CheckPoint implements ICheckPoint{
	
	public String checkIsEqual(Object obj1,Object obj2){
		String r = null;
		try{
			Assert.assertEquals(obj1, obj2);
		} catch (AssertionError e){
			r = "info:"+e.getMessage();
		}
		return r;
	}
	
	public String checkArrayIsEqual(Object[] obj1,Object[] obj2){
		String r = null;
		try{
			Assert.assertArrayEquals(obj1, obj2);
		} catch (AssertionError e){
			r = "info:"+e.getMessage();
		}
		return r;
	}
	
	public String checkIsFalse(boolean condition){
		String r = null;
		try{
			Assert.assertFalse(condition);
		} catch (AssertionError e){
			r = "info:"+e.getMessage();
		}
		return r;
	}
	
	public String checkNotNull(Object obj){
		String r = null;
		try{
			Assert.assertNotNull(obj);
		} catch (AssertionError e){
			r = "info:"+e.getMessage();
		}
		return r;
	}
	
	public String checkIsNull(Object obj){
		String r = null;
		try{
			Assert.assertNull(obj);
		} catch (AssertionError e){
			r = "info:"+e.getMessage();
		}
		return r;
	}
	
	public String checkIsTrue(boolean condition){
		String r = null;
		try{
			Assert.assertTrue(condition);
		} catch (AssertionError e){
			r = "info:"+e.getMessage();
		}
		return r;
	}
	
	/*
	 * 校验str2是否包含str1,若包含返回true，否则返回false
	 */
	public String checkIsMatcher(String str1,String str2){
		if(str2.contains(str1))return null;
		else return "false";
		}
	}