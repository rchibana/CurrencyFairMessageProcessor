package com.chibana.currencyfair.service;

import com.chibana.currencyfair.exception.InvalidDateRange;
import com.chibana.currencyfair.exception.NullTransactionException;
import com.chibana.currencyfair.model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/
public interface TransactionService {

    Transaction createTransaction(Transaction transaction) throws NullTransactionException;

    Optional<Transaction> getTransactionById(Long transactionId);

    List<Transaction> getAllTransactionsByUserId(Long userId);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByDateRange(Date initDate, Date endDate) throws InvalidDateRange;

}
