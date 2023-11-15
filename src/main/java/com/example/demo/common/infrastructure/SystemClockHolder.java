package com.example.demo.common.infrastructure;

import com.example.demo.common.service.post.ClockHolder;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class SystemClockHolder implements ClockHolder {
    @Override
    public long mills() {
        return Clock.systemUTC().millis();
    }
}
