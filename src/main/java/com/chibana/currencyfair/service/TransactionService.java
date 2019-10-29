package com.chibana.currencyfair.service;

import com.chibana.currencyfair.model.Transaction;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/
public interface TransactionService {

    Transaction createTransaction(Transaction transaction);

    Transaction getTransactionById(Long transactionId);

    List<Transaction> getAllTransactionsByUserId(Long userId);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByDateRange(Calendar initialDate, Calendar endDate);

}
