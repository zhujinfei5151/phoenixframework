package org.phoenix.cases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.phoenix.action.LocatorType;
import org.phoenix.dao.LocatorDao;
import org.phoenix.model.CaseBean;
import org.phoenix.model.DataBean;
import org.phoenix.model.LocatorBean;
import org.phoenix.model.ScenarioBean;
import org.phoenix.model.SuperDataBean;
import org.phoenix.model.TestCode;
import org.phoenix.powertools.HibernateUtil;
import org.phoenix.powertools.Obj2Stream;

public class TestCase {
	@Test
	public void testAddScenario(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			//session.save(new ScenarioBean("测试场景1","场景添加测试",new Date(),1));
			//session.save(new ScenarioBean("测试场景2","场景添加测试",new Date(),1));
			ScenarioBean scen = new ScenarioBean();
			scen.setId(3);
/*			List<CaseBean> list = session.createQuery("from CaseBean where scenarioBean.id=3").list();
			for(CaseBean c : list){
				System.out.println(c.getCaseName());
			}*/
			CaseBean caseBean = new CaseBean();
			caseBean.setId(8);
			session.delete(scen);
			session.getTransaction().commit();	
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void testAddCase(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			ScenarioBean scenarioBean = new ScenarioBean();
			scenarioBean.setId(6);
			//session.save(new CaseBean("测试用例1","testCode","首页用例",scenarioBean));
			//session.save(new CaseBean("测试用例2","testCode","首页用例",scenarioBean));
			CaseBean caseBean = new CaseBean("测试用例3",Obj2Stream.Obj2String(new TestPhoenixCase()),"首页用例3",1,new Date(),scenarioBean,1);
			caseBean.setId(20);
			session.update(caseBean);
			//session.save(new CaseBean("测试用例4","testCode","首页用例",1,new Date(),scenarioBean,1));
			//session.save(caseBean);
			session.getTransaction().commit();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void testSelect(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			
			//ScenarioBean scenarioBean = (ScenarioBean) session.get(ScenarioBean.class, 1);
			//System.out.println(scenarioBean.getScenarioName());
			
			CaseBean caseBean = (CaseBean) session.get(CaseBean.class, 3);
			System.out.println("scenarioId:"+caseBean.getScenarioBean().getId());
			System.out.println("caseName:"+caseBean.getCodeContent());
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			CaseBean caseBean = new CaseBean();
			caseBean.setId(8);

			//session.save(new LocatorBean(caseBean,Obj2Stream.Obj2String(new TestPhoenixCase()),"#su",LocatorType.CSS));
			session.save(new LocatorBean("set","#kw",LocatorType.CSS,caseBean));
			session.save(new LocatorBean("click","#su",LocatorType.CSS,caseBean));

			session.getTransaction().commit();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}

	@Test
	public void test02() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			Query query = session.createQuery("from CaseBean Where id = 20");
			
			CaseBean db = (CaseBean) query.uniqueResult();
			String obj = db.getCodeContent();
			//System.out.println(obj);
			TestPhoenixCase u = (TestPhoenixCase) Obj2Stream.String2Obj(obj);
			//System.out.println(u.getUsername());
			u.run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void test07(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			LocatorDao ld = new LocatorDao();
			List<LocatorBean> list = ld.getModelList(3);
			for(LocatorBean lb : list){
				System.out.println(lb.getLocatorDataName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void test03() throws IOException{
		SuperDataBean u = new DataBean("1","张三","");
		SuperDataBean u2 = new DataBean("2","李四","");
		List<SuperDataBean> list = new ArrayList<SuperDataBean>();
		list.add(u);
		list.add(u2);
		Obj2Stream.Obj2File(list, "obj.txt");
	}
	
	@Test
	public void test04() throws ClassNotFoundException, IOException{
		List<Object> list = Obj2Stream.File2Obj("obj.txt");
		for(int i=0;i<list.size();i++){
			SuperDataBean u = (SuperDataBean)list.get(i);
			u.say();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test05(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			Query query = session.createQuery("from DataBean where locatorType=?")
					.setParameter(0, "css");
			List<DataBean> list = query.list();
			for(DataBean lb : list){
				System.out.println(lb.getLocatorName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	@Test
	public void test06() throws Exception{
		String str = "package org.phoenix.model;"+
					  "import java.io.Serializable;"+
						"public class TestCode implements Serializable{"+
							"private static final long serialVersionUID = 1L;"+
							"public void run(){"+
								"System.out.println(\"1111111\");"+
							"}"+
						"}";
		System.out.println(Obj2Stream.Obj2String(new TestPhoenixCase()));
		//TestPhoenixCase tc = (TestPhoenixCase) Obj2Stream.String2Obj("%C2%AC%C3%AD%00%05sr%00%21org.phoenix.cases.TestPhoenixCase%00%00%00%00%00%00%00%01%02%00%00xp");
		//tc.run();
	}
	@Test
	public void test08() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			Query query = session.createQuery("from CaseBean Where id = 20");
			
			CaseBean db = (CaseBean) query.uniqueResult();
			String obj = db.getCodeContent();
			//System.out.println(obj);
			TestPhoenixCase u = (TestPhoenixCase) Obj2Stream.String2Obj(obj);
			//System.out.println(u.getUsername());
			u.run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
}
