package com.Unity.www;

public class User {
	private String id;
	private String name;
	private String password;
	private int role;
	
	//�޲ι��캯��
	public User(){
		
	}
	
	//�вι��캯��
	public User(String id,String name,String password,int role){
		this.id=id;
		this.name=name;
		this.password=password;
		this.role=role;
	}
	
	//��ȡid����
	public String getId() {
		return id;
	}
	//����id����
	public void setId(String id) {
		this.id=id;
	}
	
	//��ȡ�û����ĺ���
	public String getName() {
		return name;
	}
	//�����û����ĺ���
	public void setName(String name) {
		this.name=name;
	}
	
	//��ȡ�û����뺯��
	public String getPassword() {
		return password;
	}
	//�����û����뺯��
	public void setPassword(String password) {
		this.password=password;
	}
	
	//��ȡ�û���ɫ����
	public int getRole() {
		return role;
	}
	//�����û���ɫ����
	public void setRole(int role) {
		this.role=role;
	}

}
