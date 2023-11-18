package co.com.example.model.user.gateways;

import co.com.example.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);
}
