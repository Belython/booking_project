package by.kanarski.booking.managers;

public enum ResourceManager {

    OPERATION_MESSAGES(ResourcePath.OPERATION_MESSAGES_SOURCE),
    JSP_TEXT(ResourcePath.TEXT_SOURCE),
    EXCEPTION_MESSAGES(ResourcePath.EXCEPTION_MESSAGES_SOURCE),
    DATABASE(ResourcePath.DATABASE_SOURCE),
    SUPPORTED_LANGUAGES(ResourcePath.SUPPORTED_LANGUAGES),

    //Mail resources

    AUTHENTIFICATION(ResourcePath.AUTHENTIFICATION_SOURCE),
    MAIL_MESSAGES(ResourcePath.MAIL_MESSAGES_SOURCE);
    
    private String resoucePath;
    private ResourceBundle bundle;

    ResourceManager(String resoucePath) {
        this.resoucePath = resoucePath;
    }

    private static final ResourceCache RESOURCE_CACHE = new ResourceCache();

    public ResourceBundle get() {
        String resourceName = this.name() + "Default";
        bundle = RESOURCE_CACHE.get(resourceName);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(resoucePath);
            RESOURCE_CACHE.put(resourceName, bundle);
        }
        return bundle;
    }

    public ResourceBundle get(Locale locale) {
        String resourceName = this.name() + " " + locale.toLanguageTag();
        bundle = RESOURCE_CACHE.get(resourceName);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(resoucePath, locale);
            RESOURCE_CACHE.put(resourceName, bundle);
        }
        return bundle;
    }
    
}
