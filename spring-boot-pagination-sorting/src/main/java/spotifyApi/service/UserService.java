package spotifyApi.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import spotifyApi.repository.UserRepository;

import spotifyApi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;


@Service
public class UserService implements GraphQLQueryResolver, GraphQLMutationResolver {

}
