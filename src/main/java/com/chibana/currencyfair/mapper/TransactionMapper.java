package com.chibana.currencyfair.mapper;

import com.chibana.currencyfair.dto.TransactionRequestDTO;
import com.chibana.currencyfair.dto.TransactionResponseDTO;
import com.chibana.currencyfair.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by Rodrigo Chibana
 * Date: 02/11/2019
 **/

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionResponseDTO transactionToResponseDTO(Transaction transaction);

    List<TransactionResponseDTO> transactionsToResponseDTOs(List<Transaction> transactions);

    Transaction requestDTOToTransaction(TransactionRequestDTO transactionRequestDTO);

}
