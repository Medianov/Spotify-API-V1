package spotifyApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Datenbankkonfiguration anpassen (in Application.properties)
@SpringBootApplication
public class SpotifyAPI {
	public static void main(String[] args) {
		SpringApplication.run(SpotifyAPI.class, args);
	}

}
