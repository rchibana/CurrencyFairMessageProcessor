package com.chibana.currencyfair.controller;

import com.chibana.currencyfair.dto.TransactionResponseDTO;
import com.chibana.currencyfair.exception.InvalidDateRange;
import com.chibana.currencyfair.mapper.TransactionMapper;
import com.chibana.currencyfair.model.Transaction;
import com.chibana.currencyfair.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/
@Log4j2
@RestController
@RequestMapping("/transaction/reporter")
@Validated
public class TransactionReporterController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    private final SimpleDateFormat simpleDateFormat;

    @Autowired
    public TransactionReporterController(final TransactionService transactionService, final TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
        this.simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
    }

    @GetMapping("/date")
    @ResponseStatus(HttpStatus.OK)
    public Page<TransactionResponseDTO> getTransactionsBetweenDates(
            @NotNull(message = "{date.notNull}") @DateTimeFormat(pattern = "dd/MM/yyyy" ) @RequestParam(value = "initDate") String initDateString,
            @NotNull(message = "{date.notNull}") @DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(value = "endDate") String endDateString,
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize)
            throws InvalidDateRange, ParseException {

        final Date initDate = simpleDateFormat.parse(initDateString);

        // It's necessary to set time to the last moment possible of the day
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(endDateString));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        final Date endDate = calendar.getTime();

        final Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        final Page<Transaction> transactionsByDateRange = this.transactionService.getTransactionsByDateRange(initDate, endDate, pageRequest);
        return transactionsByDateRange.map(transactionMapper::transactionToResponseDTO);

    }


}
