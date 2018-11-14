//对用户操作的接口
package com.Dao.www;

import java.util.List;
import com.Unity.www.User;;

/**
 * User Dao 接口
 * @author 万启航
 *
 */

public interface UserDao {
	
	/**
	 * 查询所有普通用户信息
	 */
	List<User> findAll();
	
	int foundCountByName(String name);
	
	List<User> findByName(String name);
	
	User findById(String id);
	
	User findByNameAndPass(String name,String password);
	
	boolean addUser(User user);
	
	boolean editUser(User user);
	
	boolean deletUser(String id);
	
	
}
