package by.kanarski.booking.managers;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public enum AuthenticationManager {

    USER_NAME("booking.userName"),
    PASSWORD("booking.password");

    private String resourceKey;

    AuthenticationManager(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String get() {
        Locale locale = UserPreferences.getLocale();
        ResourceBundle bundle = ResourceManager.AUTHENTIFICATION.get(locale);
        String property = bundle.getString(resourceKey);
        return property;
    }

}
