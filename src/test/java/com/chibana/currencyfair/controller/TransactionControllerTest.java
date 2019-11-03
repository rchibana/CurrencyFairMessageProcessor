package com.chibana.currencyfair.controller;

import com.chibana.currencyfair.dto.TransactionRequestDTO;
import com.chibana.currencyfair.dto.TransactionResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String USER_ID = "userId";
    private static final String URL = "/transaction";

    @Test
    void receiveNewTransaction() throws Exception {

        final String requestJson = objectMapper.writeValueAsString(getTransactionRequestDTO());

        final String jsonContent = getContentAsString(performPost(requestJson, status().isCreated()));

        final TransactionResponseDTO responseDTO = objectMapper.readValue(jsonContent, TransactionResponseDTO.class);

        Assertions.assertThat(responseDTO.getId()).isNotNull();
    }

    @Test
    void getAllUserTransactionsInvalidUserId() throws Exception {

        mockMvc.perform(get(URL).param(USER_ID, "0"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllUserTransactions() throws Exception {

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(){{
            add(USER_ID, "1");
        }};

        final String jsonContent = getContentAsString(performGet(params, status().isOk()));

        final List<TransactionResponseDTO> transactionResponseDTOList = objectMapper.readValue(jsonContent, new TypeReference<List<TransactionResponseDTO>>(){});

        Assertions.assertThat(transactionResponseDTOList.size()).isGreaterThan(0);
    }

    @Test
    void getAllUserTransactionsWithoutTransactions() throws Exception {

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(){{
            add(USER_ID, "9999999");
        }};

        final String jsonContent = getContentAsString(performGet(params, status().isOk()));

        final List<TransactionResponseDTO> transactionResponseDTOList = objectMapper.readValue(jsonContent, new TypeReference<List<TransactionResponseDTO>>(){});

        Assertions.assertThat(transactionResponseDTOList.size()).isEqualTo(0);
    }

    private MvcResult performGet(MultiValueMap<String, String> params, ResultMatcher resultMatcher) throws Exception {
        return mockMvc.perform(get(URL).params(params))
                .andDo(print())
                .andExpect(resultMatcher)
                .andReturn();
    }

    private MvcResult performPost(String requestJson, ResultMatcher resultMatcher) throws Exception {
        return mockMvc.perform(post(URL)
                    .content(requestJson)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(resultMatcher)
                .andReturn();
    }

    /**
     * Returns response object in String format
     * @return String
     */
    private String getContentAsString(MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }

    /**
     * Builds an TransactionRequestDTO object
     * @return {@link TransactionRequestDTO}
     */
    private TransactionRequestDTO getTransactionRequestDTO() {
        return TransactionRequestDTO.builder()
                .userId(134256L)
                .currencyFrom("EUR")
                .currencyTo("GBP")
                .amountBuy(new BigDecimal("1000"))
                .amountSell(new BigDecimal("747.10"))
                .rate(0.7471)
                .timePlaced(Calendar.getInstance().getTime())
                .originatingCountry("FR")
                .build();
    }

}