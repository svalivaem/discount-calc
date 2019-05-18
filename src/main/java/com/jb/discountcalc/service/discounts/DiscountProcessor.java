package com.jb.discountcalc.service.discounts;

import com.jb.discountcalc.domain.Transaction;

import java.util.List;

public interface DiscountProcessor {

  List<Transaction> process(List<Transaction> transactions);

}
