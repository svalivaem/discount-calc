package com.jb.discountcalc.service.discounts.rules;

import com.jb.discountcalc.domain.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class LargeShippingDiscountRule implements DiscountRule {

  @Override
  public void processTransactions(List<Transaction> transactions) {
    for (Transaction current : transactions) {
      if ("L".equals(current.getSize()) && "LP".equals(current.getCarrier())) {

        //Collecting all L size transactions by LP carrier
        List<Transaction> filtered = transactions.stream()
            .filter(t ->
                "L".equals(t.getSize())
                    && "LP".equals(t.getCarrier())
                    && !t.isIgnored()
            )
            .collect(Collectors.toList());

        if (filtered.size() > 2 && current.equals(filtered.get(2))) {//checking the index of transactions
          current.applyDiscount(current.getPrice());
        }
      }
    }
  }

}
