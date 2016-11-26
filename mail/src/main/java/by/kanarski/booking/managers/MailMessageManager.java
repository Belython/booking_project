package by.kanarski.booking.managers;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public enum MailMessageManager {

    //Subjects
    SUBJECT_RECOVERY_PASSWORD("mailMessage.recoveryPassword"),

    //Messages
    MESSAGE_YOUR_PASSWORD("mailMessage.yourPassword");

    private String resourceKey;

    MailMessageManager(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String get() {
        Locale locale = UserPreferences.getLocale();
        ResourceBundle bundle = ResourceManager.MAIL_MESSAGES.get(locale);
        String property = bundle.getString(resourceKey);
        return property;
    }

}
