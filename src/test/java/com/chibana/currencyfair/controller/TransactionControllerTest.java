package com.chibana.currencyfair.controller;

import com.chibana.currencyfair.dto.TransactionRequestDTO;
import com.chibana.currencyfair.dto.TransactionResponseDTO;
import com.chibana.currencyfair.page.TransactionPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
    void receiveNewTransactionSuccessfully() throws Exception {

        final String requestJson = objectMapper.writeValueAsString(getTransactionRequestDTO());

        final String jsonContent = getContentAsString(performPost(requestJson, status().isCreated()));

        final TransactionResponseDTO responseDTO = objectMapper.readValue(jsonContent, TransactionResponseDTO.class);

        Assertions.assertThat(responseDTO.getId()).isNotNull();
    }

    @Test
    void receiveNewTransactionNullFields() throws Exception {

        final String requestJson = objectMapper.writeValueAsString(new TransactionRequestDTO());
        performPost(requestJson, status().isBadRequest());

    }

    @Test
    void receiveNewTransactionInvalidUserId() throws Exception {

        final TransactionRequestDTO transactionRequestDTO = getTransactionRequestDTO();
        transactionRequestDTO.setUserId(-1234L);

        final String requestJson = objectMapper.writeValueAsString(new TransactionRequestDTO());
        performPost(requestJson, status().isBadRequest());

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

    @Test
    public void getTransactionsBetweenDates() throws Exception {

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(){{
            add("initDate", "25/01/2016");
            add("endDate", "29/01/2016");
            add("pageNumber", "0");
            add("pageSize", "2");
        }};

        final String contentAsString = getContentAsString(performGet("/date", params, status().isOk()));

        final Page<TransactionResponseDTO> transactionResponseDTOs = objectMapper.readValue(contentAsString, new TypeReference<TransactionPage<TransactionResponseDTO>>() {});

        Assertions.assertThat(transactionResponseDTOs.getTotalElements()).isEqualTo(4);

    }

    @Test
    public void getTransactionsBetweenDatesInvalidDateFormat() throws Exception {

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(){{
            add("initDate", "2016-12-01");
            add("endDate", "2016-12-01");
            add("pageNumber", "0");
            add("pageSize", "2");
        }};

        performGet("/date", params, status().isBadRequest());

    }

    @Test
    public void getTransactionsBetweenDatesNull() throws Exception {

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(){{
            add("initDate", null);
            add("endDate", null);
            add("pageNumber", "0");
            add("pageSize", "2");
        }};

        performGet("/date", params, status().isBadRequest());

    }

    private MvcResult performGet(String path, MultiValueMap<String, String> params, ResultMatcher resultMatcher) throws Exception {
        return mockMvc.perform(get(URL + path).params(params))
                .andDo(print())
                .andExpect(resultMatcher)
                .andReturn();
    }

    private MvcResult performGet(MultiValueMap<String, String> params, ResultMatcher resultMatcher) throws Exception {
        return performGet(StringUtils.EMPTY, params, resultMatcher);
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