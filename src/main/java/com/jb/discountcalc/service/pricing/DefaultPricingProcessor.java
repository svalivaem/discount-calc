package com.jb.discountcalc.service.pricing;

import com.jb.discountcalc.domain.Prices;
import com.jb.discountcalc.domain.Transaction;

import java.util.List;

public class DefaultPricingProcessor implements PricingProcessor {

  @Override
  public List<Transaction> process(List<Transaction> transactions) {

    for (Transaction transaction : transactions) {
      if (transaction.isIgnored()) {
        continue;
      }
      try {
        transaction.setPrice(Prices.PRICES.get(transaction.getCarrier())
            .get(transaction.getSize()));

        if (transaction.getPrice() == null) {
          transaction.ignore();
        }
      } catch (NullPointerException e) {
        transaction.ignore();
      }
    }
    return transactions;
  }

}
