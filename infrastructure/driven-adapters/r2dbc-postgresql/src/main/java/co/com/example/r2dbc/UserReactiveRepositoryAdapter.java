package co.com.example.r2dbc;

import co.com.example.model.user.User;
import co.com.example.model.user.gateways.UserRepository;
import co.com.example.r2dbc.entity.UserData;
import co.com.example.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<User, UserData, String, UserReactiveRepository>
        implements UserRepository {
    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public Mono<User> save(User user) {
        return Mono.just(user)
                .map(userRequest -> mapper.map(userRequest, UserData.class))
                .map(UserReactiveRepositoryAdapter::getBuildUserData)
                .flatMap(userData -> repository.save(userData))
                .map(userData -> mapper.map(userData, User.class));
    }

    private static UserData getBuildUserData(UserData userData) {
        return userData.toBuilder()
                .id(UUID.randomUUID().toString())
                .documents(userData.getDocuments().stream()
                        .map(document -> document.toBuilder()
                                .user(userData)
                                .build())
                        .toList())
                .build();
    }
}
