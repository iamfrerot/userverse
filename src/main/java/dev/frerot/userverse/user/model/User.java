package dev.frerot.userverse.user.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(value = "users")
@Data
public class User {
    @Id
    private String userid;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String birthdate;
    private String phone;
    private String country;
    private boolean active;
    @CreatedDate
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime updated_at;
}
