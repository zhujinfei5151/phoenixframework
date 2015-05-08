package org.phoenix.node.action;

import java.lang.reflect.Method;

import org.phoenix.node.compiler.DynamicEngine;
import org.phoenix.node.util.MethodPattern;

public class ExecuteMethod {
	
	public String runByJavaCode(String codeContent){
		//"public\\sclass(.*)(?=\\{)").split("\\{")[0]
		String packageName = MethodPattern.result(codeContent, "package(.*);").trim();
		String className = MethodPattern.result(codeContent, "public\\sclass\\s(.*)extends\\sWebElementAction").trim();
        DynamicEngine de = DynamicEngine.getInstance();
        try {
			Class<?> clazz =  de.javaCodeToClass(packageName+"."+className,codeContent);
			Method method = clazz.getDeclaredMethod("run");
			method.invoke(clazz.newInstance());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "execute fail!"+e.getMessage();
		}
	}
}
