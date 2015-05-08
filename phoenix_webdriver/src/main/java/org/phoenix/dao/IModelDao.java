package org.phoenix.dao;

import java.util.List;

/**
 * 基础model操作接口
 * @author mengfeiyang
 *
 * @param <T>
 */
public interface IModelDao<T> {
	/**
	 * 根据uid加载model列表
	 * @param uid
	 * @return
	 */
	List<T> getModelList(int uid);
	
	/**
	 * 根据name加载model列表
	 * @param uid
	 * @return
	 */
	List<T> getModelList(String name);
	/**
	 * 根据id加载model
	 * @param Id
	 * @return
	 */
	T loadModel(int Id);
	
	/**
	 * 根据name加载model
	 * @param name
	 * @return
	 */
	T loadModel(String name);

}
