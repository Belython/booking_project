package by.kanarski.booking.aspects;

import by.kanarski.booking.utils.SystemLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Component
@Aspect
public class DaoLogger {

    private static final SystemLogger LOGGER = SystemLogger.getInstance();

    @Pointcut("execution(* by.kanarski.booking.dao.impl.*.* (..))")
    public void daoMethod() {

    }

    @Around("daoMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> daoClass = joinPoint.getTarget().getClass();
        LOGGER.setSender(daoClass);
        String daoName = daoClass.getSimpleName();
        LOGGER.logInfo(daoName + " dao method started");
        Object result = joinPoint.proceed();
        LOGGER.logInfo(daoName + " dao method succeeded");
        return result;
    }

}
