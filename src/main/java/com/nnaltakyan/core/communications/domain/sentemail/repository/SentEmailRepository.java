package com.nnaltakyan.core.communications.domain.sentemail.repository;

import com.nnaltakyan.core.communications.domain.sentemail.model.SentEmail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SentEmailRepository extends PagingAndSortingRepository<SentEmail, Long>, CrudRepository<SentEmail, Long> {

    @Query("SELECT s.id FROM SentEmail s WHERE s.status='PENDING' AND s.sent IS NULL")
    Page<Long> findIdsByStatusInPendingAndSentIsNull(Pageable pageable);
}
