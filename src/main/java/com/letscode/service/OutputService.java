package com.letscode.service;

import com.letscode.aspect.CallingCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OutputService {

    @Value("${app.name}")
    private String name;

    private final GreetingService greetingService;
    private final TimeService timeService;

    @Autowired
    public OutputService(GreetingService greetingService, TimeService timeService) {
        this.greetingService = greetingService;
        this.timeService = timeService;
    }

    @CallingCount
    public void generateOutput() {
        String time = timeService.getCurrentTime();
        String greeting = greetingService.getGreeting();
        System.out.printf("%s %s %s%n", time, greeting, this.name);
    }

}
