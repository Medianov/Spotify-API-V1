package spotifyApi.entity;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "playlist")
public class Playlist {
	
	public Playlist() {}

	public Playlist(String name,String created_date,Integer owner_id) {
		this.name = name;
		this.created_date = created_date;
		this.owner_id = owner_id;
	}
	
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	public Set<Playlist_song> getPlaylist_song() {
		return playlist_song;
	}

	public void setPlaylist_song(Set<Playlist_song> playlist_song) {
		this.playlist_song = playlist_song;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "playlist_follower",
            joinColumns = {
                    @JoinColumn(name = "playlist_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "follower_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<User> followers ;

	public Set<User> getUsers() {
		return followers;
	}
	public void setUsers(Set<User> followers) {
		this.followers = followers;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="owner_id", referencedColumnName = "id", insertable = false, updatable = false)    
	private User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany (fetch = FetchType.EAGER)
	@JoinColumn (name = "playlist_id", referencedColumnName = "id")
	private Set<Playlist_song> playlist_song;
	
	@Column
	private String name;
	@Column
	private String created_date;
	
	@Column
	private Integer owner_id;
	
	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public Integer getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Integer owner_id) {
		this.owner_id = owner_id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", created_date=" + created_date + "]";
	}
}

