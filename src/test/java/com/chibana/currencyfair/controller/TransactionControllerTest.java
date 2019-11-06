package com.chibana.currencyfair.controller;

import com.chibana.currencyfair.dto.TransactionRequestDTO;
import com.chibana.currencyfair.dto.TransactionResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

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
    private ObjectMapper objectMapper;

    @Autowired
    private TestHelper testHelper;


    private static final String USER_ID = "userId";
    private static final String URL = "/transaction";

    @BeforeEach
    public void setUp() {
        this.testHelper.setUrl(URL);
    }

    @Test
    void receiveNewTransactionSuccessfully() throws Exception {

        final String requestJson = objectMapper.writeValueAsString(testHelper.getTransactionRequestDTO());

        final String jsonContent = testHelper.getContentAsString(testHelper.performPost(requestJson, status().isCreated()));

        final TransactionResponseDTO responseDTO = objectMapper.readValue(jsonContent, TransactionResponseDTO.class);

        Assertions.assertThat(responseDTO.getId()).isNotNull();
    }

    @Test
    void receiveNewTransactionNullFields() throws Exception {

        final String requestJson = objectMapper.writeValueAsString(new TransactionRequestDTO());
        testHelper.performPost(requestJson, status().isBadRequest());

    }

    @Test
    void receiveNewTransactionInvalidUserId() throws Exception {

        final TransactionRequestDTO transactionRequestDTO = testHelper.getTransactionRequestDTO();
        transactionRequestDTO.setUserId(-1234L);

        final String requestJson = objectMapper.writeValueAsString(new TransactionRequestDTO());
        testHelper.performPost(requestJson, status().isBadRequest());

    }

    @Test
    void getAllUserTransactionsInvalidUserId() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>() {{
            add(USER_ID, "0");
        }};

        testHelper.performGet(params, status().isBadRequest());

    }

    @Test
    void getAllUserTransactions() throws Exception {

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(){{
            add(USER_ID, "1");
        }};

        final String jsonContent = testHelper.getContentAsString(testHelper.performGet(params, status().isOk()));

        final List<TransactionResponseDTO> transactionResponseDTOList = objectMapper.readValue(jsonContent, new TypeReference<List<TransactionResponseDTO>>(){});

        Assertions.assertThat(transactionResponseDTOList.size()).isGreaterThan(0);
    }

    @Test
    void getAllUserTransactionsWithoutTransactions() throws Exception {

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(){{
            add(USER_ID, "9999999");
        }};

        final String jsonContent = testHelper.getContentAsString(testHelper.performGet(params, status().isOk()));

        final List<TransactionResponseDTO> transactionResponseDTOList = objectMapper.readValue(jsonContent, new TypeReference<List<TransactionResponseDTO>>(){});

        Assertions.assertThat(transactionResponseDTOList.size()).isEqualTo(0);
    }

}