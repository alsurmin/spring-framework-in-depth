package com.letscode.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class CallingCountAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallingCountAspect.class);
    private static final Map<String, Long> countByMethodSignature = new HashMap<>();

    @Pointcut("@annotation(CallingCount)")
    public void executeCounting() {
    }

    @Before(value = "executeCounting()")
    public void countMethodCall(JoinPoint joinPoint) {
        Long counter = countByMethodSignature.getOrDefault(joinPoint.getSignature().toString(), 0L);
        String message = "Method: " + joinPoint.getSignature().getName() +
                " totalCountCall: " + ++counter;
        countByMethodSignature.put(joinPoint.getSignature().toString(), counter);
        LOGGER.info(message);
    }

}
