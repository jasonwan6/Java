package com.Unity.www;

public class Song {
	private String id;         //�������
	private String name;       //������
	private String laugage;    //��������
	private String category;   //����������
	private String singer;     //�����ĸ質��
	
	//�޲ι��캯��
	public  Song() {
		
	}
	
	//�вι��캯��
	public Song(String id,String name,String laugage,String category,String singer){
		this.id=id;
		this.name=name;
		this.laugage=laugage;
		this.category=category;
		this.singer=singer;
	}
	
	//��ȡ����id����
	public String getId() {
		return id;
	}
	//���ø���id����
	public void setId(String id) {
		this.id=id;
	}
	
	//��ȡ����������
	public String getName() {
		return name;
	}
	//���ø���������
	public void setName(String name) {
		this.name=name;
	}
	
	//��ȡ�������Ժ���
	public String getLaugage() {
		return laugage;
	}
	//���ø������Ժ���
	public void setLaugage(String laugage) {
		this.laugage=laugage;
	}
	
	//��ȡ�������ͺ���
	public String getCategory() {
		return category;
	}
	//���ø������ͺ���
	public void setCategory(String category) {
		this.category=category;
	}
	
	//��ȡ���ֺ���
	public String getSinger() {
		return singer;
	}
	//���ø��ֺ���
	public void setSinger(String singer) {
		this.singer=singer;
	}
	
}
