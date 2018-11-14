package com.Service.www;

import java.util.List;

import com.Dao.www.SongDao;
import com.Dao.www.SongDaoImpl;
import com.Unity.www.Song;

public class SongServiceImpl implements SongService {
	private SongDao songDao;
	public SongServiceImpl() {
		songDao = new SongDaoImpl();
	}
	@Override
	public List<Song> findAll() {
		return songDao.findAll();
	}

	@Override
	public Song findById(String id) {
		return songDao.findById(id);
	}

	@Override
	public List<Song> findByName(String name) {
		return songDao.findByName(name);
	}

	@Override
	public List<Song> findBylanguage(String language) {
		return songDao.findByLaugage(language);
	}

	@Override
	public List<Song> findBySinger(String singer) {
		return songDao.findBySinger(singer);
	}

	@Override
	public List<Song> findByCategory(String category) {
		return songDao.findByCategory(category);
	}

	@Override
	public boolean deletSong(String id) {
		return songDao.deleteSong(id);
	}

	@Override
	public boolean addSong(Song song) {
		return songDao.addSong(song);
	}

	@Override
	public boolean updateSong(Song song) {
		return songDao.updateSong(song);
	}

}