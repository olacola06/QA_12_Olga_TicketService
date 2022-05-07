package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString

public class User {
    String name;
    String surname;
    String email;
    String password;
    String confirmPassword;
    String phone;

}
