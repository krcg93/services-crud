package services.crud.infrastructure.shared;

import services.crud.domain.service.UserService;
import io.micronaut.context.annotation.Factory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class AppContext {

    @Inject
    services.crud.infrastructure.service.UserService userServiceRepository;

    @Singleton
    UserService userService(){
        return new UserService(this.userServiceRepository);
    }
}