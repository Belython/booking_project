package by.kanarski.booking.utils.threadLocal;


import by.kanarski.booking.utils.ConnectionUtil;

public enum ThreadLocalUtil {
    CONNECTION,
    LOCALE,
    CURRENCY,
    RESOURCE_CACHE;

    private static final ThreadLocal<ThreadVariables> THREAD_VARIABLES = new ThreadLocal<ThreadVariables>() {

        @Override
        protected ThreadVariables initialValue() {
            return new ThreadVariables();
        }

    };

    public Object get() {
        String name = this.name();
        Object variable = THREAD_VARIABLES.get().get(name);
        return variable;
    }

    public Object get(Object initialValue) {
        String name = this.name();
        Object variable = THREAD_VARIABLES.get().get(name);
        if (variable == null) {
            THREAD_VARIABLES.get().put(name, initialValue);
            return get();
        } else {
            return variable;
        }
    }

    public void set(Object value) {
        String name = this.name();
        THREAD_VARIABLES.get().put(name, value);
    }

    public static void destroy() {
        ConnectionUtil.closeConnection();
        THREAD_VARIABLES.remove();
    }


}




