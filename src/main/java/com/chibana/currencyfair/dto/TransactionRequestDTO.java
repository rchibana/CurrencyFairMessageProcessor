package com.chibana.currencyfair.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequestDTO {

    @Positive(message = "{userId.positive}")
    @NotNull(message = "{userId.notNull}")
    private Long userId;

    @NotBlank(message = "{currency.notBlank}")
    private String currencyFrom;

    @NotBlank(message = "{currency.notBlank}")
    private String currencyTo;

    @Positive(message = "{amount.positive}")
    private BigDecimal amountSell;

    @Positive(message = "{amount.positive}")
    private BigDecimal amountBuy;

    @Positive(message = "{rate.positive}")
    private Double rate;

    @NotNull(message = "{timePlaced.notNull}")
    @JsonFormat(pattern="dd-MMM-yyyy HH:mm:ss")
    private Date timePlaced;

    @NotBlank(message = "{originatingCountry.notBlank}")
    private String originatingCountry;


}
