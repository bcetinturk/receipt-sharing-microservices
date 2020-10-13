package com.example.userservice.listeners;

import com.example.userservice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BeforeConvertListener extends AbstractMongoEventListener<User> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        log.info("onBeforeConvert: " + event.getSource());
        if (event.getSource().getId() == null) {
            event.getSource().setId(UUID.randomUUID());
        }
    }
}


