package com.chibana.currencyfair.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Rodrigo Chibana
 * Date: 30/10/2019
 **/
@Data
public class TransactionResponseDTO {

    @NotNull
    @Positive
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
    @JsonFormat(pattern="dd-MMM-yyyy HH:mm:ss")
    private Date timePlaced;

    @NotNull
    private String originatingCountry;


}
