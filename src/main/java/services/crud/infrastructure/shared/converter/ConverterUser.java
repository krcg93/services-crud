package services.crud.infrastructure.shared.converter;

import com.google.gson.Gson;
import services.crud.infrastructure.shared.dto.UserDto.UserDto;
import services.crud.infrastructure.shared.dto.UserDto.UsersDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ConverterUser {
    public static List<UserDto> mapperUser(Object users)
    {
        Gson gson = new Gson();
        List<UserDto> userDtos = new ArrayList<>();
        LinkedHashMap linkedHashMap = (LinkedHashMap) users;
        linkedHashMap.forEach((key, value) -> {
            String json = gson.toJson(value,LinkedHashMap.class);
            UserDto data = new Gson().fromJson(json, UserDto.class);
            userDtos.add(data);
        });
        return userDtos;
    }
}
