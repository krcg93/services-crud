package services.crud.infrastructure.client.database;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Mono;
import services.crud.infrastructure.shared.dto.UserDto.UserDto;

import javax.inject.Singleton;

@Singleton
public class Firebase {

    protected RxHttpClient client = null;

    public Firebase(@Client("${firebase.path}") RxHttpClient client) {
        this.client = client;
    }

    @Put
    public Mono<UserDto> putUser(UserDto userDto) {
        HttpRequest httpRequest = HttpRequest.PUT("/users/"+ userDto.getLastName()+ userDto.getName()+".json", userDto);
        Flowable<UserDto> response = client.retrieve(httpRequest, UserDto.class);
        Maybe<UserDto> userAddDtoMaybe = response.firstElement();
        return RxJava2Adapter.maybeToMono(userAddDtoMaybe);
    }

    @Patch
    public Mono<UserDto> patchUser(UserDto userDto) {
        HttpRequest httpRequest = HttpRequest.PATCH("/users/"+ userDto.getLastName()+ userDto.getName()+".json", userDto);
        Flowable<UserDto> response = client.retrieve(httpRequest, UserDto.class);
        Maybe<UserDto> userAddDtoMaybe = response.firstElement();
        return RxJava2Adapter.maybeToMono(userAddDtoMaybe);
    }

    @Get
    public Mono<Object> getUsers() {
        HttpRequest httpRequest = HttpRequest.GET("/users.json");
        Flowable<Object> response = client.retrieve(httpRequest, Object.class);
        Object dao = response.blockingFirst();
        Maybe<Object> objectMaybe = response.firstElement();
        return RxJava2Adapter.maybeToMono(objectMaybe);
    }

}
