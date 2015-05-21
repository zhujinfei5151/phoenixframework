package org.phoenix.action;

public interface ICheckPoint {
	String checkIsEqual(Object obj1,Object obj2);
	String checkArrayIsEqual(Object[] obj1,Object[] obj2);
	String checkIsFalse(boolean condition);
	String checkNotNull(Object obj);
	String checkIsNull(Object obj);
	String checkIsTrue(boolean condition);
	String checkIsMatcher(String str1,String str2);

}
