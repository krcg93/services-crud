package services.crud.domain.service.dependency;

import reactor.core.publisher.Mono;
import services.crud.domain.model.Users.User;
import services.crud.domain.model.Users.UserAdd;
import services.crud.domain.model.Users.UserUpdate;
import services.crud.domain.model.Users.Users;



public interface UserI {

    Mono<User> addUser(UserAdd userAdd);

    Mono<Users> updateUsers(UserUpdate userUpdate);

    Mono<Users> getUsers();

}
