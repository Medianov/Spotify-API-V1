package spotifyApi.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spotifyApi.entity.Song;
import spotifyApi.exception.RecordNotFoundException;
import spotifyApi.repository.SongRepository;
import spotifyApi.service.SongService;

/* Zum Testen fuer Postman
* http://localhost:8080/songs?pageSize=5&pageNo=1&sortBy=artist
* http://localhost:8080/graphql
* http://localhost:8080/songs/filter?artist=Professional Naz
* */

@RestController
@RequestMapping("/songs")
public class SongController {
    @Autowired
    SongService service;
    @Autowired
    SongRepository repository;

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "title") String sortBy)
    {
        List<Song> list = service.getAllSongs(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Song>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // hab hier noch etwas ergänzt, es ist möglich beides zu suchen (nur ob das sinnvoll ist)
    @GetMapping("/filter")
    public ResponseEntity<List<Song>> getAllSongsByArtist(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "artist") String sortBy,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String title)
    {
        if(artist != null && title == null) {
            List<Song> list = service.getAllSongsByArtist(pageNo, pageSize, sortBy, artist);
            return new ResponseEntity<List<Song>>(list, new HttpHeaders(), HttpStatus.OK);
        } else if(artist != null && title != null) {
            List<Song> list = service.getAllSongsByArtistAndTitle(pageNo, pageSize, sortBy, artist, title);
            return new ResponseEntity<List<Song>>(list, new HttpHeaders(), HttpStatus.OK);
        } else {
            List<Song> list = service.getAllSongsByTitle(pageNo, pageSize, sortBy, title);
            return new ResponseEntity<List<Song>>(list, new HttpHeaders(), HttpStatus.OK);
        }
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        Song entity = service.getSongById(id);
 
        return new ResponseEntity<Song>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable("id") Integer id,@RequestBody Song song)
                                                    throws RecordNotFoundException {
        Song updated = service.updateSong(song,id);
        return new ResponseEntity<Song>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity statistischeAbfrage() {
        return new ResponseEntity(repository.statistischeAbfrage(), new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
	public Song save(@RequestBody Song song) {
    	service.save(song);
		return song;
	}
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteSongById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        service.deleteSongById(id);
        return HttpStatus.FORBIDDEN;
    }
}
