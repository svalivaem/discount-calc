package com.jb.discountcalc.service.discounts;

import com.jb.discountcalc.domain.Constants;
import com.jb.discountcalc.domain.Transaction;
import com.jb.discountcalc.service.discounts.rules.DiscountRule;
import com.jb.discountcalc.service.discounts.rules.LargeShippingDiscountRule;
import com.jb.discountcalc.service.discounts.rules.SmallShippingDiscountRule;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultDiscountProcessor implements DiscountProcessor {
  @Override
  public List<Transaction> process(List<Transaction> transactions) {

    List<DiscountRule> discountRules = new ArrayList<>();
    discountRules.add(new SmallShippingDiscountRule());
    discountRules.add(new LargeShippingDiscountRule());

    Map<Month, List<Transaction>> byMonth = splitByMonth(transactions);
    Map<Long, Transaction> byId = transactions.stream()
        .collect(Collectors.toMap(Transaction::getId, t -> t));

    for (Month month : byMonth.keySet()) {
      BigDecimal remainingDiscountBudget = Constants.MONTHLY_DISCOUNT_BUDGET;
      for (Transaction transaction : byMonth.get(month)) {
        for (DiscountRule rule : discountRules) {
          BigDecimal discount = rule.processTransaction(transaction, byMonth.get(month), remainingDiscountBudget);
          if (discount != null) {
            byId.get(transaction.getId()).applyDiscount(discount);
            remainingDiscountBudget = remainingDiscountBudget.subtract(discount);
          }
        }
      }

    }

    return new ArrayList<>(byId.values());
  }

  private Map<Month, List<Transaction>> splitByMonth(List<Transaction> transactions) {
    Map<Month, List<Transaction>> byMonth = new HashMap<>();
    for (Transaction transaction : transactions) {
      if (transaction.isIgnored()) {
        continue;
      }
      byMonth.computeIfAbsent(transaction.getDate().getMonth(), k -> new ArrayList<>())
          .add(transaction);

    }
    return byMonth;
  }
}
