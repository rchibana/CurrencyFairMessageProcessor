package com.chibana.currencyfair.service;

import com.chibana.currencyfair.model.Transaction;
import com.chibana.currencyfair.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.transaction = getTransaction();

    }

    @Test
    void createTransactionSuccessfully() {
        transactionServiceImpl.createTransaction(this.transaction);
    }

    @Test
    void createTransactionUserIdNegative() {
        this.transaction.setUserId(-1L);
        transactionServiceImpl.createTransaction(this.transaction);
    }

    @Test
    void createTransactionUserIdNull() {
        transactionServiceImpl.createTransaction(this.transaction);
    }

    @Test
    void getTransactionById() {
    }

    @Test
    void getAllTransactionsByUserId() {
    }

    @Test
    void getAllTransactions() {
    }

    @Test
    void getTransactionsByDateRange() {
    }

    private Transaction getTransaction() {
        final Calendar instance = Calendar.getInstance();
        return new Transaction(134256L,"EUR", "GBP", new BigDecimal("1000"), new BigDecimal("747.10"), 0.7471, instance , "FR");
    }
}