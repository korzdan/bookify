package by.bookify.auth.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class RegisterRequestDto {

    @NotNull(message = "name must not be null")
    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotNull(message = "surname must not be null")
    @NotEmpty(message = "surname must not be empty")
    private String surname;

    @Email(message = "email is not appropriate")
    @NotNull(message = "email must not be null")
    @NotEmpty(message = "email must not be empty")
    private String email;

    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    private String password;
}
