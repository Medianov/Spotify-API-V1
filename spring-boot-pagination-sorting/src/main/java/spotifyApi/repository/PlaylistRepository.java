package spotifyApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import spotifyApi.entity.Playlist;


@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

}
