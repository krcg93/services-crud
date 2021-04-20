package services.crud.infrastructure.service;

import reactor.core.publisher.Flux;
import services.crud.domain.model.Users.User;
import services.crud.domain.model.Users.UserAdd;
import services.crud.domain.model.Users.UserUpdate;
import services.crud.domain.model.Users.Users;
import services.crud.domain.service.dependency.UserI;
import services.crud.infrastructure.client.database.Firebase;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import services.crud.infrastructure.shared.converter.ConverterUser;
import services.crud.infrastructure.shared.dto.UserDto.UserDto;
import services.crud.infrastructure.shared.dto.UserDto.UsersDto;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService implements UserI {

    @Inject
    Firebase firebase;

    @Inject
    private ModelMapper mapper;

    @Override
    public Mono<User> addUser(UserAdd userAdd) {
        return Mono.just(mapper.map(userAdd, UserDto.class))
                .flatMap(userDto ->  firebase.putUser(userDto))
                .map(userDto -> {
                    return mapper.map(userDto, User.class);
                });
    }

    @Override
    public Mono<Users> updateUsers(UserUpdate userUpdate) {
        return Mono.just(mapper.map(userUpdate, UsersDto.class))
                .zipWhen(userDto -> {
                   return Flux.fromIterable(userDto.getUsers())
                            .flatMap(dto ->
                                    updateUser(dto)
                                            .map(userDto1 -> userDto1))
                            .collectList();
                })
                .map(tuple -> {
                    UsersDto usersDto = new UsersDto(tuple.getT2());
                    return mapper.map(usersDto, Users.class);
                });

    }

    private Mono<UserDto> updateUser(UserDto userDto) {
        return Mono.just(mapper.map(userDto, UserDto.class))
                .flatMap(dto ->  firebase.patchUser(dto));
    }

    @Override
    public Mono<Users> getUsers() {
        return firebase.getUsers()
                .map(users -> mapper.map(ConverterUser.mapperUser(users), Users.class));
    }
}
