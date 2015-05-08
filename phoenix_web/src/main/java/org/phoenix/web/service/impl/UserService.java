package org.phoenix.web.service.impl;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.phoenix.basic.paging.Pager;
import org.phoenix.web.dao.IUserDao;
import org.phoenix.web.exception.PhoenixException;
import org.phoenix.web.model.User;
import org.phoenix.web.service.IUserService;
import org.phoenix.web.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {
	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	

	@Override
	public void add(User user) {
		User tu = userDao.loadByUserName(user.getUsername());
		if(tu!=null)throw new PhoenixException("添加的用户对象已经存在，不能添加");
		userDao.add(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}
   

	@Override
	public Pager<User> findUser() {
		return userDao.findUser();
	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}
   
	@Override
	public User login(String username, String password) {
		User user = userDao.loadLoginUser(username,password);
		//if(user==null) throw new PhoenixException("用户名或者密码不正确");
/*		try {
			if(!SecurityUtil.md5(username,password).equals(user.getPassword())) {
				throw new PhoenixException("用户名或者密码不正确");
			}
		} catch (NoSuchAlgorithmException e) {
			throw new PhoenixException("密码加密失败:"+e.getMessage());
		}*/
		return user;
	}
	@Override
	public void update(User user) {
		userDao.update(user);
	}
	@Override
	public void updatePwd(int uid, String oldPwd, String newPwd) {
		try {
			User u = userDao.load(uid);
			if(!SecurityUtil.md5(u.getUsername(),oldPwd).equals(u.getPassword())) {
				throw new PhoenixException("原始密码输入不正确");
			}
			u.setPassword(SecurityUtil.md5(u.getUsername(), newPwd));
			userDao.update(u);
		} catch (NoSuchAlgorithmException e) {
			throw new PhoenixException("更新密码失败:"+e.getMessage());
		}
	}

}
