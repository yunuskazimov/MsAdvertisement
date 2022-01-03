package az.xazar.msadvertisement.model.Ad.exception;

public class AdNotFoundException extends RuntimeException {
    public AdNotFoundException(String message) {
        super(message);
    }
}
