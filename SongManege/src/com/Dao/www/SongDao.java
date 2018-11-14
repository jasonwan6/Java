package com.Dao.www;
import java.util.List;

import com.Unity.www.Song;
/**
 * �Ը�����Ϣ���й���Ľӿ�
 * @author ������
 */

public interface SongDao {
	
	/**
	 * ��ʾ���и�����Ϣ
	 * @return
	 */
	List<Song> findAll();
	/**
	 * ͨ��id���Ҹ���
	 * @param id
	 * @return
	 */
	Song findById(String id);
	/**
	 * ͨ�� ����������
	 * @param name
	 * @return
	 */
	List<Song> findByName(String name);
	/**
	 * ͨ�����Բ��Ҹ���
	 * @param laugage
	 * @return
	 */
	List<Song> findByLaugage(String laugage);
	/**
	 * ͨ�������������
	 * @param category
	 * @return
	 */
	List<Song> findByCategory(String category);
	/**
	 * ͨ�����ֽ��в���
	 * @param singer
	 * @return
	 */
	List<Song> findBySinger(String singer);
	/**
	 * ��Ӹ���
	 * @param song
	 * @return
	 */
	boolean addSong(Song song);
	/**
	 * ���¿�����
	 * @param song
	 * @return
	 */
	boolean updateSong(Song song);
	/**
	 * ɾ��������
	 * @param id
	 * @return
	 */
	boolean deleteSong(String id);
}
