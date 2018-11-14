package com.Dao.www;
import java.util.List;

import com.Unity.www.Song;
/**
 * 对歌曲信息进行管理的接口
 * @author 万启航
 */

public interface SongDao {
	
	/**
	 * 显示所有歌曲信息
	 * @return
	 */
	List<Song> findAll();
	/**
	 * 通过id查找歌曲
	 * @param id
	 * @return
	 */
	Song findById(String id);
	/**
	 * 通过 歌曲名查找
	 * @param name
	 * @return
	 */
	List<Song> findByName(String name);
	/**
	 * 通过语言查找歌曲
	 * @param laugage
	 * @return
	 */
	List<Song> findByLaugage(String laugage);
	/**
	 * 通过语言种类查找
	 * @param category
	 * @return
	 */
	List<Song> findByCategory(String category);
	/**
	 * 通过歌手进行查找
	 * @param singer
	 * @return
	 */
	List<Song> findBySinger(String singer);
	/**
	 * 添加歌曲
	 * @param song
	 * @return
	 */
	boolean addSong(Song song);
	/**
	 * 更新库存歌曲
	 * @param song
	 * @return
	 */
	boolean updateSong(Song song);
	/**
	 * 删除库存歌曲
	 * @param id
	 * @return
	 */
	boolean deleteSong(String id);
}
