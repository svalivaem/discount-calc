package com.jb.discountcalc.service.discounts.rules;

import com.jb.discountcalc.domain.Transaction;

import java.util.List;

public interface DiscountRule {

  void processTransactions(List<Transaction> transactions);

}
