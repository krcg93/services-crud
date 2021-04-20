package services.crud.infrastructure.shared.dto.UserDto;

import java.util.List;

public class UsersDto {

    private List<UserDto> users;

    public UsersDto(){}

    public UsersDto(List<UserDto> users){
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
