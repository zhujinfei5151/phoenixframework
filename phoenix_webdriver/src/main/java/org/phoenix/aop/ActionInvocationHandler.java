package org.phoenix.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;
import org.phoenix.utils.ScreenShot;

/**
 * @author mengfeiyang
 * 使用动态代理需要实现InvocationHandler接口
 * 
 */
public class ActionInvocationHandler implements InvocationHandler {
	private Object target = null;
	private LinkedList<UnitLogBean> unitLog;
	private CaseLogBean caseLogBean;

	public ActionInvocationHandler(Object target,LinkedList<UnitLogBean> unitLog,CaseLogBean caseLogBean) {
		this.target = target;
		this.unitLog = unitLog;
		this.caseLogBean = caseLogBean;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		Object result = null;
		try{
			result = method.invoke(this.target, args);
			if(!method.getName().equals("checkPoint") && !method.getName().equals("webElement") && !method.getName().equals("toString")){
				unitLog.add(new UnitLogBean("步骤 [ "+method.getName()+" ]执行成功，参数值："+Arrays.toString(args)+",执行结果返回值："+result,method.getName(),"STEP","SUCCESS","",caseLogBean));
				PhoenixLogger.info("步骤 [ "+method.getName()+" ]执行成功，参数值："+Arrays.toString(args)+",执行结果返回值："+result);
			}
		}catch(Exception e){
			long picName = new Date().getTime();
			String picPath = ScreenShot.TakeScreenshot(caseLogBean.getAttachPath()+"/screenshot/"+picName+".jpg");
			String picWebPath = "<a href='http://"+caseLogBean.getClientIP()+"/phoenix_node/screenshot/"+picName+".jpg' target='_blank'>点击查看</a>";
			unitLog.add(new UnitLogBean("步骤 [ "+method.getName()+" ]执行失败，参数值："+Arrays.toString(args)+",异常信息："+e.getClass().getName()+","+e.getMessage(),method.getName(),"STEP","FAIL",picWebPath,caseLogBean));
			PhoenixLogger.info("步骤 [ "+method.getName()+" ]执行失败，参数值："+Arrays.toString(args)+",异常信息："+e.getClass().getSimpleName()+","+e.getMessage()+",截图路径："+picPath);
		}
		return result;
	}

	public Object getProxy() {
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(), this);
	}
}