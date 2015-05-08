package org.phoenix.web.service;

import org.phoenix.basic.paging.Pager;
import org.phoenix.model.ScenarioBean;

/**
 * 场景服务接口
 * @author mengfeiyang
 *
 */
public interface IScenarioService {
	/**
	 * 增加一个
	 * @param scenarioBean
	 */
   void add(ScenarioBean scenarioBean);
   
   /**
    * 删除一个
    * @param id
    */
   void delete(int id);
   /**
    * 更新数据
    * @param scenarioBean
    */
   void update(ScenarioBean scenarioBean);
   
   /**
    * 加载一个
    * @param id
    * @return
    */
   ScenarioBean getScenarioBean(int id);
   
   /**
    * 加载多个，并且获取分页信息，根据用户的uid
    * @param uid
    * @return
    */
   Pager<ScenarioBean> getScenarioBeanPager(int uid);
}
