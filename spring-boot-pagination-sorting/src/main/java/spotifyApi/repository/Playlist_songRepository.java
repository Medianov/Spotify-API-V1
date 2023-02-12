package spotifyApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import spotifyApi.entity.Playlist_song;


@Repository
public interface Playlist_songRepository extends JpaRepository<Playlist_song, Integer> {

}

