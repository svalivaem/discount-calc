package com.jb.discountcalc.service.discounts.rules;

import com.jb.discountcalc.domain.Constants;
import com.jb.discountcalc.domain.Transaction;

import java.math.BigDecimal;
import java.util.*;

public class SmallShippingDiscountRule implements DiscountRule {

  @Override
  public BigDecimal processTransaction(Transaction current, List<Transaction> validTransactions, BigDecimal remainingBudget) {
    if (remainingBudget.equals(BigDecimal.ZERO) || current.isIgnored()) {
      return null;
    }

    if ("S".equals(current.getSize())) {

      Optional<BigDecimal> cheapestShippingPrice = Constants.PRICES.values()
          .stream()
          .filter(f -> f.containsKey("S"))
          .map(Map::values)
          .flatMap(Collection::stream)
          .min(Comparator.naturalOrder());

      if (current.getPrice().compareTo(cheapestShippingPrice.get()) > 0) {
        BigDecimal discountNeeded = current.getPrice().subtract(cheapestShippingPrice.get());
        return remainingBudget.compareTo(discountNeeded) > 0
            ? discountNeeded
            : remainingBudget;
      }
    }
    return null;
  }
}
