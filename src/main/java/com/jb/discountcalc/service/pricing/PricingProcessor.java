package com.jb.discountcalc.service.pricing;

import com.jb.discountcalc.domain.Transaction;

import java.util.List;

public interface PricingProcessor {

  List<Transaction> process(List<Transaction> transactions);

}
