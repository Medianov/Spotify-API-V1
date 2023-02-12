package spotifyApi.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import spotifyApi.entity.Playlist_song;
import spotifyApi.repository.Playlist_songRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class Playlist_songService implements GraphQLQueryResolver, GraphQLMutationResolver {

}