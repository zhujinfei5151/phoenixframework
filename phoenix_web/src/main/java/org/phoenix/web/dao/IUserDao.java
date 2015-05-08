package org.phoenix.web.dao;

import org.phoenix.basic.dao.IBaseDao;
import org.phoenix.basic.paging.Pager;
import org.phoenix.web.model.User;

public interface IUserDao extends IBaseDao<User>{
	
	/**
	 * 根据用户名获取用户对象
	 * @param username
	 * @return
	 */
	public User loadLoginUser(String username,String password);
	
	/**
	 * 根据用户名获取用户对象
	 * @param username
	 * @return
	 */
	public User loadByUserName(String username);
	
	/**
	 * 获取用户的信息及分页信息
	 * @return
	 */
	public Pager<User> findUser();
}
