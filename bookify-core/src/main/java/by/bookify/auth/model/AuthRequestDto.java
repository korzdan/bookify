package by.bookify.auth.model;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String email;
    private String password;
}
