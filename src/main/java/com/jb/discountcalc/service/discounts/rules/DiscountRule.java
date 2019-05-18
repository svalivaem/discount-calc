package com.jb.discountcalc.service.discounts.rules;

import com.jb.discountcalc.domain.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountRule {

  BigDecimal processTransaction(Transaction current, List<Transaction> validTransactions, BigDecimal remainingBudget);

}
