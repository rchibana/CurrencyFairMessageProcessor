package com.chibana.currencyfair.service;

import com.chibana.currencyfair.model.Transaction;
import com.chibana.currencyfair.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByDateRange(Calendar initialDate, Calendar endDate) {
        return null;
    }
}
