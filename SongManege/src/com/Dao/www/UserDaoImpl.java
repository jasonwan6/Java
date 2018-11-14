package com.Dao.www;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Unity.www.*;

import Util.*;

public class UserDaoImpl implements UserDao{
	
	//���ڻ�ȡ���ݿ������
	private Connection connection=null;
	//���ڲ������ݿ�
	private PreparedStatement pst=null;
	//���ڴ�����ݲ����Ľ��
	private ResultSet rs=null;

	@Override
	public List<User> findAll() {
		
		//����ִ�е�MYSQL���
		String sql="SELECT * FROM user WHERE role=1 ORDER BY orderby";
		//���ڴ洢�ṹ���б�
		List<User> users=new ArrayList<>();
		
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			rs=pst.executeQuery();  //ִ��sql���
			//�Ѳ�ѯ������Ϣ��װ��ʵ����User�У��ٷŵ�List������
			while(rs.next()){
				User user=new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
				users.add(user);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally{
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return users;
	}

	@Override
	public int foundCountByName(String name) {
		int count=0;
		//sql���,?�൱��ռλ��
		String sql="SELECT COUNT(*) as count FROM user WHERE name=?";
		
		//��Ž��
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, name);
			rs=pst.executeQuery();
			if(rs.next()){
				count=rs.getInt("count");
				System.out.println("count"+count);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
		
		return count;
	}

	@Override
	public List<User> findByName(String name) {
		//sqlִ�����
		String sql="SELECT * FROM user WHERE role=1 AND name LIKE concat('%',?,'%') ORDER BY orderby";
		//���ڴ�Ž��
		List<User> users=new ArrayList<>();
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, name);
			rs=pst.executeQuery();
			if(rs.next()){
				User user=new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
				users.add(user);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public User findById(String id) {
		//sqlִ�����
		String sql="SELECT * FROM user WHERE id=?";
		//���ڴ�Ž���Ķ���
		User user=null;
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, id);
			rs=pst.executeQuery();
			if(rs.next()){
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public User findByNameAndPass(String name, String password) {
		//ִ�е�sql���
		String sql="SELECT * FROM user WHERE name=? And password=?";
		//���ڴ�Ž����ʵ��
		User user=new User();
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			rs=pst.executeQuery();
			if(rs.next()){
				//��ʼ������user
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			//�ر�����
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public boolean addUser(User user) {
		//ִ�е�sql���
		String sql="INSERT INTO user(id,name,password,role) VALUES (?,?,?,?)";
		//�����ж��Ƿ���ӳɹ��ı�־
		boolean flag=false;
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, user.getId());
			pst.setString(2, user.getName());
			pst.setString(3, user.getPassword());
			pst.setInt(4, user.getRole());
			//��ʱ����rs=pst.executeQuery();
			
			
			if(pst.executeUpdate()==1){
				flag=true;
			}
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			try {
				//�ر���ص�����
				pst.close();
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean editUser(User user) {
		//����ִ�е�sql���
		String sql="UPDATE user SET name=?,password=?,role=? WHERE id=?";
		//�Ƿ�ɹ��ı�־
		boolean flag=false;
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getRole());
			pst.setString(4, user.getId());
			if(pst.executeUpdate()==1){
				flag=true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean deletUser(String id) {
		//����ִ�е�SQL���
		String sql="DELETE FROM user WHERE id=?";
		//�����жϳɹ�
		boolean flag=false;
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, id);
			if(pst.executeUpdate()==1){
				flag=true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
}
