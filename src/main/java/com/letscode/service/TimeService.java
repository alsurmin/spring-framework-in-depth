package com.letscode.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {

    @Value("#{new Boolean(environment['spring.profiles.active'] != 'dev')}")
    private boolean is24;

    public TimeService() {
        super();
    }

    public boolean isIs24() {
        return is24;
    }

    public void setIs24(boolean is24) {
        this.is24 = is24;
    }

    public String getCurrentTime() {
        if (is24) {
            return DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
        } else {
            return DateTimeFormatter.ofPattern("HH:mm a").format(LocalDateTime.now());
        }
    }

}
