package services.crud.domain.model.Users;

import reactor.core.publisher.Mono;
import services.crud.domain.exception.Validate;

import java.util.List;

public class UserUpdate {
    private List<UserAdd> users;

    public UserUpdate(){}

    private UserUpdate(List<UserAdd> users) {
        this.users = users;
    }

    public static Mono<UserUpdate> create(List<UserAdd> users){
        UserUpdate userUpdate =new UserUpdate(users);
        return userUpdate.validate().then(Mono.just(userUpdate));
    }

    public Mono<Void> validate(){
        return Validate
                .nullOrEmptyValidate(users,"users");
    }
}
