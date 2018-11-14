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
	
	//用于获取数据库的连接
	private Connection connection=null;
	//用于操作数据库
	private PreparedStatement pst=null;
	//用于存放数据操作的结果
	private ResultSet rs=null;

	@Override
	public List<User> findAll() {
		
		//用来执行的MYSQL语句
		String sql="SELECT * FROM user WHERE role=1 ORDER BY orderby";
		//用于存储结构的列表
		List<User> users=new ArrayList<>();
		
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			rs=pst.executeQuery();  //执行sql语句
			//把查询到的信息封装到实体类User中，再放到List集合中
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
		//sql语句,?相当于占位符
		String sql="SELECT COUNT(*) as count FROM user WHERE name=?";
		
		//存放结果
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
		//sql执行语句
		String sql="SELECT * FROM user WHERE role=1 AND name LIKE concat('%',?,'%') ORDER BY orderby";
		//用于存放结果
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
		//sql执行语句
		String sql="SELECT * FROM user WHERE id=?";
		//用于存放结果的对象
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
		//执行的sql语句
		String sql="SELECT * FROM user WHERE name=? And password=?";
		//用于存放结果的实体
		User user=new User();
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			rs=pst.executeQuery();
			if(rs.next()){
				//初始化对象user
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			//关闭连接
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
		//执行的sql语句
		String sql="INSERT INTO user(id,name,password,role) VALUES (?,?,?,?)";
		//用于判断是否添加成功的标志
		boolean flag=false;
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, user.getId());
			pst.setString(2, user.getName());
			pst.setString(3, user.getPassword());
			pst.setInt(4, user.getRole());
			//此时不用rs=pst.executeQuery();
			
			
			if(pst.executeUpdate()==1){
				flag=true;
			}
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			try {
				//关闭相关的连接
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
		//用于执行的sql语句
		String sql="UPDATE user SET name=?,password=?,role=? WHERE id=?";
		//是否成功的标志
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
		//用于执行的SQL语句
		String sql="DELETE FROM user WHERE id=?";
		//用于判断成功
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
