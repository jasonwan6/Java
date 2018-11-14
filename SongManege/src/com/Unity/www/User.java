package com.Unity.www;

public class User {
	private String id;
	private String name;
	private String password;
	private int role;
	
	//无参构造函数
	public User(){
		
	}
	
	//有参构造函数
	public User(String id,String name,String password,int role){
		this.id=id;
		this.name=name;
		this.password=password;
		this.role=role;
	}
	
	//获取id函数
	public String getId() {
		return id;
	}
	//设置id函数
	public void setId(String id) {
		this.id=id;
	}
	
	//获取用户名的函数
	public String getName() {
		return name;
	}
	//设置用户名的函数
	public void setName(String name) {
		this.name=name;
	}
	
	//获取用户密码函数
	public String getPassword() {
		return password;
	}
	//设置用户密码函数
	public void setPassword(String password) {
		this.password=password;
	}
	
	//获取用户角色函数
	public int getRole() {
		return role;
	}
	//设置用户角色函数
	public void setRole(int role) {
		this.role=role;
	}

}
