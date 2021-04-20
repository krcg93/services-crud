package services.crud.domain.model.Users;

import services.crud.domain.exception.Validate;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public class UserAdd implements Serializable {
    private String name;
    private String lastName;
    private Boolean indicted;

    public UserAdd(){}

    private UserAdd(String name, String lastName, Boolean indicted) {
        this.name = name;
        this.lastName = lastName;
        this.indicted = indicted;
    }

    public static Mono<UserAdd> create(String name, String lastName, Boolean indicted){
        UserAdd userAdd =new UserAdd(name, lastName, indicted);
        return userAdd.validate().then(Mono.just(userAdd));
    }

    public Mono<Void> validate(){
        return Validate
                .nullOrEmptyValidate(name,"name")
                .switchIfEmpty(Validate.nullOrEmptyValidate(lastName,"lastName"))
                .switchIfEmpty(Validate.nullEntityValidate(indicted,"indicted"));
    }
    
}