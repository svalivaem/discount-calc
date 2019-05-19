package com.jb.discountcalc.service.discounts.rules;

import com.jb.discountcalc.domain.Prices;
import com.jb.discountcalc.domain.Transaction;

import java.math.BigDecimal;
import java.util.*;

public class SmallShippingDiscountRule implements DiscountRule {

  @Override
  public void processTransactions(List<Transaction> transactions) {
    for (Transaction current : transactions) {
      if ("S".equals(current.getSize())) {

        Optional<BigDecimal> cheapestShippingPrice = Prices.PRICES.values()
            .stream()
            .filter(f -> f.containsKey("S"))
            .map(Map::values)
            .flatMap(Collection::stream)
            .min(Comparator.naturalOrder());

        if (cheapestShippingPrice.isPresent() && current.getPrice().compareTo(cheapestShippingPrice.get()) > 0) {
          current.applyDiscount(current.getPrice().subtract(cheapestShippingPrice.get()));
        }
      }
    }
  }

}
