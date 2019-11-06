package com.chibana.currencyfair.controller;

import com.chibana.currencyfair.dto.TransactionRequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Rodrigo Chibana
 * Date: 06/11/2019
 **/
@Component
public class TestHelper {

    private MockMvc mockMvc;
    private String url;

    @Autowired
    public TestHelper(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.url = "/transaction";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MvcResult performGet(String path, MultiValueMap<String, String> params, ResultMatcher resultMatcher) throws Exception {
        return mockMvc.perform(get(url + path).params(params))
                .andDo(print())
                .andExpect(resultMatcher)
                .andReturn();
    }

    public MvcResult performGet(MultiValueMap<String, String> params, ResultMatcher resultMatcher) throws Exception {
        return performGet(StringUtils.EMPTY, params, resultMatcher);
    }

    public MvcResult performPost(String requestJson, ResultMatcher resultMatcher) throws Exception {
        return mockMvc.perform(post(url)
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
    public String getContentAsString(MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }

    /**
     * Builds an TransactionRequestDTO object
     * @return {@link TransactionRequestDTO}
     */
    public TransactionRequestDTO getTransactionRequestDTO() {
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
