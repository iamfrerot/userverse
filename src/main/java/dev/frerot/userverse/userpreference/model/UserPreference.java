package dev.frerot.userverse.userpreference.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userpreference")
@Data
@Builder
public class UserPreference {
    @Id
    private String id;
    private String userid;
    @NotNull
    private boolean darkmode;
    @NotNull
    private boolean emailnotification;
    @NotNull
    private boolean smsnotification;
    @NotNull
    private String preferedlanguage;
}
