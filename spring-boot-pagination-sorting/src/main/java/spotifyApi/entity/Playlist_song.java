package spotifyApi.entity;

import javax.persistence.*;


@Entity
@Table(name = "playlist_song")
public class Playlist_song {

	public Playlist_song() {}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}


	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer position;
	@Column
	private Integer song_id;
	@Column
	private Integer playlist_id;
	
	
	public Integer getPlaylist_id() {
		return playlist_id;
	}

	public void setPlaylist_id(Integer playlist_id) {
		this.playlist_id = playlist_id;
	}

	public Integer getSong_id() {
		return song_id;
	}

	public void setSong_id(Integer song_id) {
		this.song_id = song_id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="song_id", referencedColumnName = "id", insertable = false, updatable = false)    
	private Song song;
}

