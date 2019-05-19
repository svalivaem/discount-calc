package com.jb.discountcalc.service.discounts;

import com.jb.discountcalc.domain.Transaction;
import com.jb.discountcalc.service.discounts.rules.DiscountRule;
import com.jb.discountcalc.service.discounts.rules.LargeShippingDiscountRule;
import com.jb.discountcalc.service.discounts.rules.MonthlyDiscountBudgetRule;
import com.jb.discountcalc.service.discounts.rules.SmallShippingDiscountRule;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultDiscountProcessor implements DiscountProcessor {

  private List<DiscountRule> discountRules = new ArrayList<>();


  @Override
  public void initDiscountRules() {
    discountRules.add(new SmallShippingDiscountRule());
    discountRules.add(new LargeShippingDiscountRule());
    discountRules.add(new MonthlyDiscountBudgetRule());
  }

  @Override
  public List<Transaction> process(List<Transaction> transactions) {

    Map<Integer, Map<Month, List<Transaction>>> byYearAndMonth = splitByYearAndMonth(transactions);

    Map<Long, Transaction> byId = transactions.stream()
        .collect(Collectors.toMap(Transaction::getId, t -> t));

    for (Integer year : byYearAndMonth.keySet()) {
      for (Month month : byYearAndMonth.get(year).keySet()) {
        for (DiscountRule rule : discountRules) {
          //Sorting by day of month
          byYearAndMonth.get(year).get(month).sort(Comparator.comparingInt(o -> o.getDate().getDayOfMonth()));

          rule.processTransactions(byYearAndMonth.get(year).get(month));
        }
      }
    }

    return new ArrayList<>(byId.values());
  }

  private Map<Integer, Map<Month, List<Transaction>>> splitByYearAndMonth(List<Transaction> transactions) {
    Map<Integer, Map<Month, List<Transaction>>> byYearAndMonth = new HashMap<>();
    for (Transaction transaction : transactions) {
      if (transaction.isIgnored()) {
        continue;
      }

      byYearAndMonth.computeIfAbsent(transaction.getDate().getYear(), k -> new HashMap<>())
          .computeIfAbsent(transaction.getDate().getMonth(), k -> new ArrayList<>())
          .add(transaction);

    }
    return byYearAndMonth;
  }

}
