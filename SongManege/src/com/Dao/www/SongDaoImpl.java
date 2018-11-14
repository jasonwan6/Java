package com.Dao.www;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Patch;

import com.Unity.www.Song;
import Util.JdbcUtil;

public class SongDaoImpl implements SongDao{
	//���ڻ�ȡ���ݿ������
	private Connection connection=null;
	//����ִ�ж�̬��SQL���
	private PreparedStatement pst=null;
	//������Ž��
	private ResultSet rs=null;
	@Override
	
	public List<Song> findAll() {
		//sql���
		String sql="SELECT * FROM songim ORDER BY orderby";
		//��Ų�ѯ���
		List<Song> songs=new ArrayList<>();
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				Song song=new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setLaugage(rs.getString("laugage"));
				song.setCategory(rs.getString("category"));
				song.setSinger(rs.getString("singer"));
				System.out.println();
				songs.add(song);
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
		return songs;
	}
	@Override
	public Song findById(String id) {
		//sql���
		String sql="SELECT * FROM songim WHERE id=? ORDER BY orderby";
		//��Ž��
		Song song=null;
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, id);
			rs=pst.executeQuery();
			while(rs.next()){
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setLaugage(rs.getString("laugage"));
				song.setCategory(rs.getString("category"));
				song.setSinger(rs.getString("singer"));
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
		return song;
	}
	@Override
	public List<Song> findByName(String name) {
		//sql�����
		String sql="SELECT * FROM songim WHERE name LIKE concat('%',?,'%') ORDER BY orderby";
		List<Song> songs=new ArrayList<>();
		
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, name);
			rs=pst.executeQuery();
			while(rs.next()){
				Song song=new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setLaugage(rs.getString("laugage"));
				song.setCategory(rs.getString("category"));
				song.setSinger(rs.getString("singer"));
				songs.add(song);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return songs;
	}
	@Override
	public List<Song> findByLaugage(String laugage) {
		//sql�����
		String sql="SELECT * FROM songim WHERE laugage LIKE concat('%',?,'%') ORDER BY orderby";
		//��Ž����
		List<Song> songs=new ArrayList<>();
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, laugage);
			rs=pst.executeQuery();
			while(rs.next()){
				Song song=new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setLaugage(rs.getString("laugage"));
				song.setCategory(rs.getString("category"));
				song.setSinger(rs.getString("singer"));
				songs.add(song);
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
		
		return songs;
	}
	@Override
	public List<Song> findByCategory(String category) {
		//sqlִ�����
		String sql="SELECT * FROM songim WHERE category LIKE concat('%',?,'%') ORDER BY orderby";
		//��Ž����
		List<Song> songs=new ArrayList<>();
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, category);
			rs=pst.executeQuery();
			while(rs.next()){
				Song song=new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setCategory(rs.getString("category"));
				song.setLaugage(rs.getString("laugage"));
				song.setSinger(rs.getString("singer"));
				songs.add(song);
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
		
		return songs;
	}
	@Override
	public List<Song> findBySinger(String singer) {
		//sqlִ�����
		String sql="SELECT * FROM songim WHERE singer LIKE concat('%',?,'%') ORDER BY orderby";
		//��Ž��
		List<Song> songs=new ArrayList<>();
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, singer);
			rs=pst.executeQuery();
			while(rs.next()){
				Song song=new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setCategory(rs.getString("category"));
				song.setLaugage(rs.getString("laugage"));
				song.setSinger(rs.getString("singer"));
				songs.add(song);
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
		
		
		
		return songs;
	}
	@Override
	public boolean addSong(Song song) {
		//sqlִ�����
		String sql="INSERT INTO songim(id,name,laugage,category,singer) VALUES(?,?,?,?,?)";
		Boolean flag=false;
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, song.getId());
			pst.setString(2, song.getName());
			pst.setString(3, song.getLaugage());
			pst.setString(4, song.getCategory());
			pst.setString(5, song.getSinger());
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
	public boolean updateSong(Song song) {
		//sqlִ�����
		String sql="UPDATE songim SET name=?,laugage=?,category=?,singer=? WHERE id=?";
		Boolean flag=false;
		try {
			connection=JdbcUtil.getCon();
			pst=connection.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getLaugage());
			pst.setString(3, song.getCategory());
			pst.setString(4, song.getSinger());
			pst.setString(5, song.getId());
			if(pst.executeUpdate()==1){
				flag=true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean deleteSong(String id) {
		//sqlִ�����
		String sql="DELETE FROM songim WHERE id=?";
		Boolean flag=false;
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
