package com.chibana.currencyfair.controller;

import com.chibana.currencyfair.dto.TransactionRequestDTO;
import com.chibana.currencyfair.dto.TransactionResponseDTO;
import com.chibana.currencyfair.page.TransactionPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;

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
class TransactionReporterControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestHelper testHelper;

    private static final String URL = "/transaction/reporter";

    @BeforeEach
    public void setUp(){
        this.testHelper.setUrl(URL);
    }

    @Test
    public void getTransactionsBetweenDates() throws Exception {

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(){{
            add("initDate", "25/01/2016");
            add("endDate", "29/01/2016");
            add("pageNumber", "0");
            add("pageSize", "2");
        }};

        final String contentAsString = testHelper.getContentAsString(
                testHelper.performGet("/date", params, status().isOk())
        );

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

        testHelper.performGet("/date", params, status().isBadRequest());

    }

    @Test
    public void getTransactionsBetweenDatesNull() throws Exception {

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(){{
            add("initDate", null);
            add("endDate", null);
            add("pageNumber", "0");
            add("pageSize", "2");
        }};

        testHelper.performGet("/date", params, status().isBadRequest());

    }

}