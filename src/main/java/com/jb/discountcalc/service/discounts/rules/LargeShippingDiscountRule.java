package com.jb.discountcalc.service.discounts.rules;

import com.jb.discountcalc.domain.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class LargeShippingDiscountRule implements DiscountRule {

  @Override
  public BigDecimal processTransaction(Transaction current, List<Transaction> validTransactions, BigDecimal remainingBudget) {
    if (remainingBudget.equals(BigDecimal.ZERO) || current.isIgnored()) {
      return null;
    }

    if ("L".equals(current.getSize()) && "LP".equals(current.getCarrier())) {

      //Collecting all L size transactions by LP carrier
      List<Transaction> filtered = validTransactions.stream()
          .filter(t ->
              "L".equals(t.getSize())
                  && "LP".equals(t.getCarrier())
                  && !t.isIgnored()
          )
          .collect(Collectors.toList());
      if (filtered.size() > 2 && current.equals(filtered.get(2))) {//checking the index of transactions
        BigDecimal discountNeeded = current.getPrice();
        return remainingBudget.compareTo(discountNeeded) > 0
            ? discountNeeded
            : remainingBudget;
      }
    }
    return null;
  }

}
