package com.chibana.currencyfair.service;

import com.chibana.currencyfair.exception.InvalidDateRange;
import com.chibana.currencyfair.exception.NullTransactionException;
import com.chibana.currencyfair.model.Transaction;
import com.chibana.currencyfair.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/

@Service
@Log4j2
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public Transaction createTransaction(final Transaction transaction) throws NullTransactionException {
        log.info("transaction={}", transaction);

        if(transaction == null) {
            throw new NullTransactionException();
        }

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction> getTransactionById(final Long transactionId) {
        log.info("transactionId={}", transactionId);
        return transactionRepository.findById(transactionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactionsByUserId(final Long userId) {
        log.info("userId={}", userId);
        return transactionRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Transaction> getTransactionsByDateRange(final Date initDate, final Date endDate, Pageable pageable) throws InvalidDateRange {
        log.info("initDate={}, endDate={}", initDate, endDate);

        if(initDate.after(endDate)) {
            log.warn("End date must be bigger than init date. initDate={}, endDate={}", initDate, endDate);
            throw new InvalidDateRange();
        }

        return this.transactionRepository.findAllByTimePlacedBetween(initDate, endDate, pageable);

    }
}
