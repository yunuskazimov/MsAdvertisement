package az.xazar.msadvertisement.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum StatusEnum {
    SHARE, PENDING;

    @JsonValue
    public String toLower() {
        return this.toString().toLowerCase(Locale.ENGLISH);
    }
}
