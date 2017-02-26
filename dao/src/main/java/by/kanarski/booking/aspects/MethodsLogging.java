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
public class MethodsLogging {

    private static final SystemLogger LOGGER = SystemLogger.getInstance().setSender(MethodsLogging.class);

    @Pointcut("execution(* by.kanarski.booking.dao.impl.*.* (..))")
    public void daoMethod() {

    }

    @Around("daoMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String thisName = joinPoint.getTarget().getClass().getSimpleName();
        LOGGER.logInfo(thisName + " dao method started");
        Object result = joinPoint.proceed();
        LOGGER.logInfo(thisName + " dao method succeeded");
        return result;
    }

}
