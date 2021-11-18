package com.letscode.service;

import com.letscode.aspect.CallingCount;
import com.letscode.aspect.Loggable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Value("${app.greeting}")
    private String greeting;

    @CallingCount
    @Loggable
    public String getGreeting() {
        return greeting;
    }

    public GreetingService() {
        super();
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
