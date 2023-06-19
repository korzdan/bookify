package by.bookify.auth.model;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;
    private String surname;
    private String email;
    private String password;
}
