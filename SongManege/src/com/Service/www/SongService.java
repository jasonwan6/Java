package com.Service.www;

import java.util.List;

import com.Unity.www.Song;

public interface SongService {
	/**
	 * ��ʾ���и���
	 * @return
	 */
	List<Song> findAll();
	/**
	 * ͨ��id������Ϣ
	 * @return
	 */
	Song findById(String id);
	/**
	 * ��������������ѯ����
	 * @param condition
	 * @return
	 */
	List<Song> findByName(String name);
	/**
	 * �����Բ�ѯ����
	 * @param language
	 * @return
	 */
	List<Song> findBylanguage(String language);
	/**
	 * ���ݸ�������ѯ����
	 * @param singer
	 * @return
	 */
	List<Song> findBySinger(String singer);
	/**
	 * ��ָ����������ѯ����
	 * @param category
	 * @return
	 */
	List<Song> findByCategory(String category);
	
	/**
	 * ɾ������
	 * @param id
	 * @return
	 */
	boolean deletSong(String id);
	/**
	 * ��Ӹ���
	 * @param song
	 * @return
	 */
	boolean addSong(Song song);
	/**
	 * �޸ĸ���
	 * @param song
	 * @return
	 */
	boolean updateSong(Song song);
}
