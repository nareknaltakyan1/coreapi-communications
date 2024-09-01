package com.nnaltakyan.core.communications.domain.sentemail.task;

import com.nnaltakyan.core.communications.domain.email.EmailService;
import com.nnaltakyan.core.communications.domain.sentemail.service.SentEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.data.domain.Page;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "internal.tasks.send-email", name = "enabled")
public class SendEmailTask {

    private final SentEmailService sentEmailService;
    private final EmailService emailService;
    private final int pageSize;

    public SendEmailTask(final SentEmailService sentEmailService, final EmailService emailService,
                         @Value("${internal.tasks.send-email.page-size}") final int pageSize)
    {
        this.sentEmailService = sentEmailService;
        this.emailService = emailService;
        this.pageSize = pageSize;
    }

    @Scheduled(initialDelayString = "${internal.tasks.send-email.initial-delay}", fixedRateString = "${internal.tasks.send-email.rate}")
    @SchedulerLock(name = "emailSend")
    public void send(){
        log.debug("Started sending pending emails");
        Page<Long> sendEmailIds = sentEmailService.getPendingEmails(PageRequest.of(0, pageSize));
        log.debug("There are {} emails pending to be sent", sendEmailIds.getNumberOfElements());
        sendEmailIds.forEach(
                sendEmailId -> {
                    final var email = sentEmailService.getById(sendEmailId);
                    try
                    {
                        emailService.send(email);
                        log.debug("Successfully sent email {}", email.getId());
                    }
                    catch (final RuntimeException ex){
                        log.error("Unexpected exception while sending email {}", email.getId(), ex);
                    }
                }
        );
        log.debug("Finished sending emails");
    }
}
