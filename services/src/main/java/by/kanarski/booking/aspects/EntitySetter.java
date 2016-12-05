package by.kanarski.booking.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Component("entitySetter")
@Aspect
public class EntitySetter {

    @Pointcut("execution(* by.kanarski.booking.services.*.*())")
    public void anyServiceMethod() {

    }



}
