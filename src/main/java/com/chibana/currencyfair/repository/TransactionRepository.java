package com.chibana.currencyfair.repository;

import com.chibana.currencyfair.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Rodrigo Chibana
 * Date: 29/10/2019
 **/
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
