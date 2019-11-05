package com.chibana.currencyfair.repository;

import com.chibana.currencyfair.dto.TransactionResponseDTO;
import com.chibana.currencyfair.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUserId(Long userId);

    Page<Transaction> findAllByTimePlacedBetween(Date initDate, Date endDate, Pageable pageable);
}
