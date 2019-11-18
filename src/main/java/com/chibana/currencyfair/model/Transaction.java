package com.chibana.currencyfair.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/

@Data
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "{userId.positve}")
    @NotNull(message = "{userId.notNull}")
    private Long userId;

    @NotBlank(message = "{currency.notBlank}")
    @Length(min = 3, max = 3, message = "{currency.invalid}")
    private String currencyFrom;

    @NotBlank(message = "{currency.notBlank}")
    @Length(min = 3, max = 3, message = "{currency.invalid}")
    private String currencyTo;

    @Positive(message = "{amount.positive}")
    private BigDecimal amountSell;

    @Positive(message = "{amount.positive}")
    private BigDecimal amountBuy;

    @Positive(message = "{rate.positive}")
    private Double rate;

    @NotNull(message = "{timePlaced.notNull}")
    @JsonFormat(pattern="dd-MMM-yy HH:mm:ss")
    private Date timePlaced;

    @NotBlank(message = "{originatingCountry.notBlank}")
    @Length(min = 2, max = 2, message = "{originatingCountry.invalid}")
    private String originatingCountry;

    public Transaction(Long userId, String currencyFrom, String currencyTo, BigDecimal amountSell, BigDecimal amountBuy,
                       Double rate, Date timePlaced, String originatingCountry) {

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
