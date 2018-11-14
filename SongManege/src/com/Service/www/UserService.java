package com.Service.www;

import java.util.List;

import com.Unity.www.User;

public interface UserService {
	
	/**
	 * ��ѯ������ͨ�û�
	 * @return
	 */
	List<User> findAll();
	/**
	 * �����û�����ѯ
	 * @param name
	 * @return
	 */
	List<User> findByName(String name);
	/**
	 * �û���¼
	 * @param name����¼��
	 * @param password
	 * @return
	 */
	User login(String name, String password);
	/**
	 * �޸�����
	 * @param user
	 * @return
	 */
	boolean updatePassword(User user);
	/**
	 * �û�ע��
	 * @param user
	 * @return
	 */
	boolean register(User user);
	/**
	 * �鿴�û��Ƿ����
	 * @param name
	 * @return
	 */
	boolean checkUser(String name);
	/**
	 * �����û�
	 * @param user
	 * @return
	 */
	boolean addUser(User user);
	/**
	 * ɾ���û�
	 * @param id
	 * @return
	 */
	boolean deleteUser(String id);
	/**
	 * �޸��û���Ϣ
	 * @param user
	 * @return
	 */
	boolean updateUserInfo(User user);
}
