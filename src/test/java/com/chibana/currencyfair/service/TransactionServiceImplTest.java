package com.chibana.currencyfair.service;

import com.chibana.currencyfair.exception.InvalidDateRange;
import com.chibana.currencyfair.exception.NullTransactionException;
import com.chibana.currencyfair.model.Transaction;
import com.chibana.currencyfair.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Calendar.NOVEMBER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Calendar calendar;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.calendar = Calendar.getInstance();

    }

    @Test
    void creatingNullTransactionShouldReturnException() throws NullTransactionException {
        Assertions.assertThrows(NullTransactionException.class, () ->{
            this.transactionService.createTransaction(null);
        });
    }

    @Test
    void getTransactionByIdNotInDatabase() {

        when(this.transactionRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        final Optional<Transaction> transactionById = this.transactionService.getTransactionById(1L);

        Assertions.assertTrue(transactionById.isEmpty());

    }

    @Test
    void getAllTransactionsByUserIdNotInDatabase() {
        when(this.transactionRepository.findAllByUserId(0L)).thenReturn(new ArrayList<Transaction>());

        final List<Transaction> allTransactionsByUserId = this.transactionService.getAllTransactionsByUserId(0L);

        Assertions.assertTrue(allTransactionsByUserId.isEmpty());

    }

    @Test
    void getTransactionsByDateRangeInitDateBiggerThanEndDate() {

        calendar.set(2019, NOVEMBER, 5);
        final Date initDate = calendar.getTime();

        calendar.set(2018, NOVEMBER, 5);
        final Date endDate = calendar.getTime();

        Assertions.assertThrows(InvalidDateRange.class, () -> {
           this.transactionService.getTransactionsByDateRange(initDate, endDate);
        });

    }

    private Transaction getTransaction() {
        final Date date = calendar.getTime();

        return new Transaction(134256L,"EUR", "GBP", new BigDecimal("1000"), new BigDecimal("747.10"), 0.7471, date , "FR");
    }
}