package com.nnaltakyan.core.communications.domain.sentemail.service;


import com.nnaltakyan.core.communications.domain.sentemail.model.SentEmail;
import com.nnaltakyan.core.communications.domain.sentemail.repository.SentEmailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class SentEmailService {

    private final SentEmailRepository sentEmailRepository;

    @Transactional(readOnly = true)
    public Page<Long> getPendingEmails(final Pageable pageable){
        return sentEmailRepository.findIdsByStatusInPendingAndSentIsNull(pageable);
    }

    @Transactional(readOnly = true)
    public SentEmail getById(final Long id) {
        final Optional<SentEmail> email = sentEmailRepository.findById(id);
        if (email.isEmpty())
        {
            throw new EntityNotFoundException(String.format("Could not find sent email with id '%d'", id));
        }
        return email.get();
    }
}