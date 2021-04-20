package services.crud.application;

import io.micronaut.http.annotation.*;
import org.modelmapper.TypeToken;
import services.crud.domain.model.Users.UserAdd;
import services.crud.domain.model.Users.UserUpdate;
import services.crud.domain.service.UserService;
import io.micronaut.http.MediaType;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import services.crud.infrastructure.shared.dto.UserDto.UserDto;
import services.crud.infrastructure.shared.dto.UserDto.UsersDto;

import javax.inject.Inject;
import java.util.List;

@Controller("/user")
public class UserCtr {

    @Inject
    private ModelMapper mapper;

    @Inject
    private UserService userService;

    @Post(value = "add-user", produces = MediaType.APPLICATION_JSON)
    public Mono<UserDto> addUser(@Body UserDto userDto) {
        return Mono.just(mapper.map(userDto, UserAdd.class))
                .flatMap(userService::addUser)
                .map(user -> mapper.map(user, UserDto.class));
    }

    @Put(value = "update-user", produces = MediaType.APPLICATION_JSON)
    public Mono<UsersDto> updateUser(@Body UsersDto usersDto) {
        return UserUpdate.create(mapper.map(usersDto.getUsers(), new TypeToken<List<UserAdd>>() {
        }.getType()))
                .flatMap(userServ ->{
                    return userService.updateUser(userServ);
                })
                .map(user -> mapper.map(user, UsersDto.class));
    }

    @Get(value = "users", produces = MediaType.APPLICATION_JSON)
    public Mono<List<UserDto>> getUser() {
        return userService.getUsers()
                .map(users -> mapper.map(users, new TypeToken<List<UserDto>>() {
                }.getType()));
    }
}
