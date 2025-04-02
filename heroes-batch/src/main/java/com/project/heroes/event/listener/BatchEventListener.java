package com.project.heroes.event.listener;

import com.project.heroes.event.BatchErrorEvent;
import com.project.heroes.infra.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchEventListener {

    private final EmailService emailService;

    @EventListener
    public void handleBatchErrorEvent(BatchErrorEvent event) {
        log.debug("Batch error event: " + event);
//        emailService.sendSimpleEmail("Batch Error", event.getMessage());
    }
}
