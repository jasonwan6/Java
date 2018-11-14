package com.Unity.www;

public class Song {
	private String id;         //歌曲编号
	private String name;       //歌曲名
	private String laugage;    //歌曲语言
	private String category;   //歌曲的种类
	private String singer;     //歌曲的歌唱者
	
	//无参构造函数
	public  Song() {
		
	}
	
	//有参构造函数
	public Song(String id,String name,String laugage,String category,String singer){
		this.id=id;
		this.name=name;
		this.laugage=laugage;
		this.category=category;
		this.singer=singer;
	}
	
	//获取歌曲id函数
	public String getId() {
		return id;
	}
	//设置歌曲id函数
	public void setId(String id) {
		this.id=id;
	}
	
	//获取歌曲名函数
	public String getName() {
		return name;
	}
	//设置歌曲名函数
	public void setName(String name) {
		this.name=name;
	}
	
	//获取歌曲语言函数
	public String getLaugage() {
		return laugage;
	}
	//设置歌曲语言函数
	public void setLaugage(String laugage) {
		this.laugage=laugage;
	}
	
	//获取歌曲类型函数
	public String getCategory() {
		return category;
	}
	//设置歌曲类型函数
	public void setCategory(String category) {
		this.category=category;
	}
	
	//获取歌手函数
	public String getSinger() {
		return singer;
	}
	//设置歌手函数
	public void setSinger(String singer) {
		this.singer=singer;
	}
	
}
