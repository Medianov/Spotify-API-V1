package spotifyApi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



import spotifyApi.entity.Song;


import java.util.List;


@Repository
public interface SongRepository extends PagingAndSortingRepository<Song, Integer> {
    @Query(value = "Select song.artist ,count(distinct song.id) ,count(playlist_song.song_id) ,avg(playlist_song.position) from song left join playlist_song on playlist_song.song_id = song.id group by song.artist;",
            nativeQuery = true)
    public List<List<Object>> statistischeAbfrage();

    Page<Song> findByArtist(String artist, Pageable paging);
    Page<Song> findByArtistAndTitle(String artist, String title, Pageable paging);
    Page<Song> findByTitle(String title, Pageable paging);
}
