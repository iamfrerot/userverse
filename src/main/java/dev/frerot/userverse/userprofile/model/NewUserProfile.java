package dev.frerot.userverse.userprofile.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewUserProfile {
    @NotBlank
    private String profile_image;
    @NotBlank
    private String bio;
    @NotBlank
    private String website;
}
