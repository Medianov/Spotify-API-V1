package spotifyApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import spotifyApi.entity.Song;
import spotifyApi.exception.RecordNotFoundException;
import spotifyApi.repository.SongRepository;


@Service

public class SongService{
     
    @Autowired
    SongRepository songRepository;
    @Autowired
	private EntityManager entityManager;
     
    public List<Song> getAllSongs(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Song> pagedResult = songRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Song>();
        }
    }

    public List<Song> getAllSongsByArtist(Integer pageNo, Integer pageSize, String sortBy, String artist) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Song> pagedResult = songRepository.findByArtist(artist, paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Song>();
        }
    }

    public List<Song> getAllSongsByArtistAndTitle(Integer pageNo, Integer pageSize, String sortBy, String artist, String title) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Song> pagedResult = songRepository.findByArtistAndTitle(artist, title, paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Song>();
        }
    }

    public List<Song> getAllSongsByTitle(Integer pageNo, Integer pageSize, String sortBy, String title) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Song> pagedResult = songRepository.findByTitle(title, paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Song>();
        }
    }


    public Song getSongById(Integer id) throws RecordNotFoundException
    {
        Optional<Song> song = songRepository.findById(id);
         
        if(song.isPresent()) {
            return song.get();
        } else {
            throw new RecordNotFoundException("No song record exist for given id");
        }
    }
    public void save(Song song) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(song);
	}
	
    public Song updateSong(Song entity,Integer id) throws RecordNotFoundException
    {
        Optional<Song> song = songRepository.findById(id);
         
        if(song.isPresent())
        {
            Song newEntity = song.get();
            newEntity.setTitle(entity.getTitle());
            newEntity.setArtist(entity.getArtist());
 
            newEntity = songRepository.save(newEntity);
             
            return newEntity;
        } else {
            entity = songRepository.save(entity);
             
            return entity;
        }
    }
   
     
    public void deleteSongById(Integer id) throws RecordNotFoundException
    {
        Optional<Song> song = songRepository.findById(id);
         
        if(song.isPresent())
        {
            songRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No song record exist for given id");
        }
    }
}