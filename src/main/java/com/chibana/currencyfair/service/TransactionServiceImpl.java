package com.chibana.currencyfair.service;

import com.chibana.currencyfair.model.Transaction;
import com.chibana.currencyfair.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

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
    public Transaction createTransaction(final Transaction transaction) {
        log.info("transaction={}", transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction getTransactionById(final Long transactionId) {
        log.info("transactionId={}", transactionId);
        return transactionRepository.getOne(transactionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactionsByUserId(final Long userId) {
        log.info("userId={}", userId);
        return transactionRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        log.info("getting all transactions");
        return transactionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByDateRange(final Calendar initDate, final Calendar endDate) {
        log.info("initDate={}, endDate={}");
        return null;
    }
}
