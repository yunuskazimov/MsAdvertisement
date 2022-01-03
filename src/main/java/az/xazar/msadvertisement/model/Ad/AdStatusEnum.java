package az.xazar.msadvertisement.model.Ad;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum AdStatusEnum {
    ACTIVE, DEACTIVE;

    @JsonValue
    public String toLower() {
        return this.toString().toLowerCase(Locale.ENGLISH);
    }
}
