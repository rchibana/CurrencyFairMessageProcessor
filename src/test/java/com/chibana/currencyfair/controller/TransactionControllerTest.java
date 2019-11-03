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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

        Date date = Calendar.getInstance().getTime();

        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(134256L, "EUR", "GBP", new BigDecimal("1000"), new BigDecimal("747.10"), 0.7471, date, "FR");

        String requestJson = objectMapper.writeValueAsString(transactionRequestDTO);

        MvcResult mvcResult = mockMvc.perform(post(URL).content(requestJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        final TransactionResponseDTO responseDTO = objectMapper.readValue(content, TransactionResponseDTO.class);

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
        MvcResult mvcResult = mockMvc.perform(get(URL).param(USER_ID, "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        final List<TransactionResponseDTO> transactionResponseDTOList = objectMapper.readValue(content, new TypeReference<List<TransactionResponseDTO>>(){});

        Assertions.assertThat(transactionResponseDTOList.size()).isGreaterThan(0);
    }

    @Test
    void getAllUserTransactionsWithoutTransactions() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(URL).param(USER_ID, "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        final List<TransactionResponseDTO> transactionResponseDTOList = objectMapper.readValue(content, new TypeReference<List<TransactionResponseDTO>>(){});

        Assertions.assertThat(transactionResponseDTOList.size()).isEqualTo(0);
    }



}