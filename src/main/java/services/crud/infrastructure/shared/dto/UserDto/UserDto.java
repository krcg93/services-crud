package services.crud.infrastructure.shared.dto.UserDto;

public class UserDto {
    private String name;
    private String lastName;
    private boolean indicted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isIndicted() {
        return indicted;
    }

    public void setIndicted(boolean indicted) {
        this.indicted = indicted;
    }
}
