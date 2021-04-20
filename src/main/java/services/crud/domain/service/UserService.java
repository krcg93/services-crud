package services.crud.domain.service;

import services.crud.domain.exception.Validate;
import services.crud.domain.model.Users.User;
import services.crud.domain.model.Users.UserAdd;
import services.crud.domain.model.Users.UserUpdate;
import services.crud.domain.model.Users.Users;
import services.crud.domain.service.dependency.UserI;
import reactor.core.publisher.Mono;

import java.util.List;


public class UserService {

    private UserI userI;

    public UserService(UserI userI) {
        this.userI = userI;
    }

    public Mono<User> addUser(UserAdd userAdd){
        return  Validate.nullEntityValidate(userAdd,"añadir usuario")
                .then(Mono.defer(() ->{
                    return userI.addUser(userAdd);
                }))
                .onErrorResume(Mono::error);
    }

    public Mono<Users> updateUser(UserUpdate userUpdate){
        return  Validate.nullEntityValidate(userUpdate,"actualizar usuarios")
                .then(Mono.defer(() ->{
                    return userI.updateUsers(userUpdate);
                }))
                .onErrorResume(Mono::error);
    }

    public Mono<List<User>> getUsers(){
        return userI.getUsers()
                .onErrorResume(Mono::error);
    }
}
