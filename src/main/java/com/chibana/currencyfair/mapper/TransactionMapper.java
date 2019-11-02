package com.chibana.currencyfair.mapper;

import com.chibana.currencyfair.dto.TransactionRequestDTO;
import com.chibana.currencyfair.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionRequestDTO transactionToTransactionRequestDTO(Transaction transaction);

}
