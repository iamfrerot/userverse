package dev.frerot.userverse.user.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(value = "users")
@Data
public class User {
    @Id
    private String userid;
    private String firstname;
    private String lastname;
    private String email;
    private String birthdate;
    private String phone;
}
