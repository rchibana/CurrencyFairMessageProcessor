package com.chibana.currencyfair.controller;

import com.chibana.currencyfair.dto.TransactionRequestDTO;
import com.chibana.currencyfair.dto.TransactionResponseDTO;
import com.chibana.currencyfair.exception.NullTransactionException;
import com.chibana.currencyfair.mapper.TransactionMapper;
import com.chibana.currencyfair.model.Transaction;
import com.chibana.currencyfair.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/
@Log4j2
@RestController
@RequestMapping("/transaction")
@Validated
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionController(final TransactionService transactionService, final TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDTO receiveNewTransaction(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) throws NullTransactionException {

        final Transaction transaction = transactionMapper.requestDTOToTransaction(transactionRequestDTO);

        final Transaction responseTransaction= this.transactionService.createTransaction(transaction);

        return this.transactionMapper.transactionToResponseDTO(responseTransaction);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponseDTO> getAllUserTransactions(
            @Positive(message = "{userId.positive}")
            @NotNull(message = "{userId.notNull}")
            @RequestParam("userId") Long userId){

        final List<Transaction> listTransactionsByUserId = this.transactionService.getAllTransactionsByUserId(userId);

        return this.transactionMapper.transactionsToResponseDTOs(listTransactionsByUserId);
    }

}
