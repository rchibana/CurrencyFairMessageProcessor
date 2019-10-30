package com.chibana.currencyfair.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/

@Data
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PositiveOrZero
    @NotNull
    private Long userId;

    @NotNull
    private String currencyFrom;

    @NotNull
    private String currencyTo;

    @Positive
    private BigDecimal amountSell;

    @Positive
    private BigDecimal amountBuy;

    @Positive
    private Double rate;

    @NotNull
    private Calendar timePlaced;

    @NotNull
    private String originatingCountry;

    public Transaction(Long userId, String currencyFrom, String currencyTo, BigDecimal amountSell, BigDecimal amountBuy,
                       Double rate, Calendar timePlaced, String originatingCountry) {

        this.userId = userId;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amountSell = amountSell;
        this.amountBuy = amountBuy;
        this.rate = rate;
        this.timePlaced = timePlaced;
        this.originatingCountry = originatingCountry;
    }
}
