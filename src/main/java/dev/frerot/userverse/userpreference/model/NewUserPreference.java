package dev.frerot.userverse.userpreference.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewUserPreference {
    @NotNull
    private boolean darkmode;
    @NotNull
    private boolean emailnotification;
    @NotNull
    private boolean smsnotification;
    @NotNull
    private String preferedlanguage;
}
