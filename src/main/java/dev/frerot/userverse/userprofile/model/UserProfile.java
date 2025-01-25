package dev.frerot.userverse.userprofile.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "userprofile")
@Data
@Builder
public class UserProfile {
    @Id
    private String id;
    private String userid;
    @NotBlank
    private String profile_image;
    @NotBlank
    private String bio;
    @NotBlank
    private String website;
    @CreatedDate
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime updated_at;
}
