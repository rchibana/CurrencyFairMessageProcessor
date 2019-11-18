package com.chibana.currencyfair.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Rodrigo Chibana
 * Date: 30/10/2019
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {

    @NotNull
    @Positive
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


}
