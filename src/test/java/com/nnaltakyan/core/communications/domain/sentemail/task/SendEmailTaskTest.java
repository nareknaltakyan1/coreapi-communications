package com.nnaltakyan.core.communications.domain.sentemail.task;

import com.nnaltakyan.core.communications.domain.email.EmailService;
import com.nnaltakyan.core.communications.domain.sentemail.model.SentEmail;
import com.nnaltakyan.core.communications.domain.sentemail.service.SentEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SendEmailTaskTest {

    private SendEmailTask sendEmailTask;
    @Mock
    private SentEmailService sentEmailService;
    @Mock
    private EmailService emailService;

    @Test
    void givenSentEmails_whenFirstFails_thenSecondSucceed(){
        // given
        sendEmailTask = new SendEmailTask(sentEmailService, emailService, 10);
        final SentEmail sentEmail1 = new SentEmail();
        final SentEmail sentEmail2 = new SentEmail();
        sentEmail1.setId(1L);
        sentEmail2.setId(2L);
        List<Long> ids = List.of(sentEmail1.getId(), sentEmail2.getId());
        //when
        when(sentEmailService.getPendingEmails(any(Pageable.class))).thenReturn(new PageImpl<>(ids));
        when(sentEmailService.getById(sentEmail1.getId())).thenReturn(sentEmail1);
        when(sentEmailService.getById(sentEmail2.getId())).thenReturn(sentEmail2);
        doThrow(RuntimeException.class).when(emailService).send(sentEmail1);
        doNothing().when(emailService).send(sentEmail2);
        //act
        sendEmailTask.send();
        //verify
        verify(sentEmailService).getPendingEmails(any(Pageable.class));
        verify(sentEmailService).getById(sentEmail1.getId());
        verify(sentEmailService).getById(sentEmail2.getId());
        verify(emailService).send(sentEmail1);
        verify(emailService).send(sentEmail2);
    }


}