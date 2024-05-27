package br.com.fiap.environment.alert.dto;

import br.com.fiap.environment.alert.domain.Person;
import br.com.fiap.environment.alert.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserInPutDto {
    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    private String name;
    @Email(message = "email is not valid")
    private String email;
    @NotBlank(message = "Login cannot be blank")
    @NotNull(message = "Login cannot be null")
    private String login;
    @NotBlank(message = "Login cannot be blank")
    @NotNull(message = "Login cannot be null")
    @Min(5)
    private String password;


    public User toUser() {
        return User.builder()
                .login(login)
                .enabled(true)
                .password(password)
                .person(Person.builder().email(email).name(name).build())
                .sendAlertNotification(false)
                .build();
    }
}
