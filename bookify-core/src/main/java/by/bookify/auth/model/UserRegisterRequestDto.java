package by.bookify.auth.model;

import lombok.Data;

import java.util.List;

@Data
public class UserRegisterRequestDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private List<Long> genreIds;
    private List<Long> authorIds;
}
