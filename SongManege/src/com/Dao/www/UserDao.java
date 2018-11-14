//���û������Ľӿ�
package com.Dao.www;

import java.util.List;
import com.Unity.www.User;;

/**
 * User Dao �ӿ�
 * @author ������
 *
 */

public interface UserDao {
	
	/**
	 * ��ѯ������ͨ�û���Ϣ
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
