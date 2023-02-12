package spotifyApi.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import spotifyApi.entity.Playlist;
import spotifyApi.entity.Playlist_song;
import spotifyApi.entity.Song;
import spotifyApi.repository.PlaylistRepository;
import spotifyApi.repository.Playlist_songRepository;
import spotifyApi.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


@Service
public class PlaylistService implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private Playlist_songRepository playlist_songRepository;
    @Autowired
    private SongRepository songRepository;

    public Optional<Playlist> find(Integer id) {
        return playlistRepository.findById(id);
    }
    

    @Transactional
    @Modifying
    public Playlist addplaylist(String name, String created_date,Integer owner_id,String artist, String title,Integer Position) {
    	Playlist playlist = new Playlist();
    	Playlist_song playlist_song= new Playlist_song();
    	Song song = new Song();
    	playlist.setName(name);
    	playlist.setCreated_date(created_date);
    	playlist.setOwner_id(owner_id);
    	song.setArtist(artist);
        song.setTitle(title);
      
        
    	playlistRepository.save(playlist);
       
        songRepository.save(song);
        playlist_song.setSong_id(song.getId());
    	playlist_song.setPlaylist_id(playlist.getId());
    	playlist_song.setPosition(Position);
        playlist_songRepository.save(playlist_song);
        return playlist;
      }
}