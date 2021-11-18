package com.letscode.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.nonNull;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(Loggable)")
    public void executeLogging() {
    }

    @Around(value = "executeLogging()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long totalTime = System.currentTimeMillis()-startTime;

        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());
        message.append(" totalTime: ").append(totalTime).append("ms");

        Object[] args = joinPoint.getArgs();
        if (nonNull(args) && args.length > 0) {
            message.append(" args=[ | ");
            Arrays.asList(args).forEach( arg -> {
                message.append(arg).append(" | ");
            });
            message.append("]");
        }

        message.append(", returning ");
        if (returnValue instanceof Collection) {
            message.append(((Collection<?>) returnValue).size()).append("instance(s)");
        } else {
            message.append(returnValue.toString());
        }

        LOGGER.info(message.toString());

        return returnValue;
    }

}
