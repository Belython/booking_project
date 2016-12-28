package by.kanarski.booking.utils.threadLocal;

import by.kanarski.booking.utils.wrappers.ThreadVariables;

/**
 * Main thread local util. Provides access to thread local variable
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see ThreadVariables
 */
public enum ThreadLocalUtil {
    LOCALE,
    REQUESTED_LOCALE,
    CURRENCY;

    private static final ThreadLocal<ThreadVariables> THREAD_VARIABLES = new ThreadLocal<ThreadVariables>() {

        @Override
        protected ThreadVariables initialValue() {
            return new ThreadVariables();
        }

    };

    /**
     * Recives thread local variable for currenct enum constant
     * @return the thread local variable, if it is added to <i>THREAD_VARIABLES</i>,
     * otherwise null
     */
    public Object get() {
        String name = this.name();
        Object variable = THREAD_VARIABLES.get().get(name);
        return variable;
    }

    /**
     * Recives thread local variable for currenct enum constant
     * @param initialValue the initial value, added to <i>THREAD_VARIABLES</i> if variable with this name
     *                     is not present
     * @return the thread local variable
     */
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

    /**
     * Adds variable to <i>THREAD_VARIABLES</i>, or rewrites current with the same name
     * @param value an variable to add
     */
    public void set(Object value) {
        String name = this.name();
        THREAD_VARIABLES.get().put(name, value);
    }

    /**
     * Removes all thread local variables from <i>THREAD_VARIABLES</i>, closes them before, if necessary
     */
    public static void destroy() {
        THREAD_VARIABLES.remove();
    }

    public void remove() {
        String name = this.name();
        THREAD_VARIABLES.get().remove(name);
    }

    /**
     * Checks if the thread variables map contains the variable
     * @param variableName the required variable name
     * @return true, if contains, else false
     */
    public static boolean hasVariable(String variableName) {
        ThreadVariables threadVariables = THREAD_VARIABLES.get();
        return threadVariables.containsKey(variableName.toUpperCase());
    }
}




