package com.jb.discountcalc.service.discounts.rules;

import com.jb.discountcalc.domain.Transaction;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MonthlyDiscountBudgetRule implements DiscountRule {

  private static final BigDecimal MONTHLY_BUDGET = BigDecimal.TEN;

  @Override
  public void processTransactions(List<Transaction> transactions) {
    Optional<BigDecimal> usedBudget = transactions.stream()
        .map(Transaction::getDiscount)
        .reduce((b1, b2) -> b1.add(b2 != null ? b2 : BigDecimal.ZERO));

    if (usedBudget.isPresent() && usedBudget.get().compareTo(MONTHLY_BUDGET) > 0) {
      BigDecimal overUsed = usedBudget.get().subtract(MONTHLY_BUDGET);

      //reverting list, processing newest transactions first
      Collections.reverse(transactions);

      //trimming discounts
      for (Transaction transaction : transactions) {
        if (transaction.getDiscount() != null) {
          if (transaction.getDiscount().compareTo(overUsed) >= 0) {
            transaction.reduceDiscount(overUsed);
            break;
          } else if (transaction.getDiscount().compareTo(overUsed) < 0) {
            overUsed = overUsed.subtract(transaction.getDiscount());
            transaction.reduceDiscount(transaction.getDiscount());
          }
        }
      }

      //reverting list back
      Collections.reverse(transactions);
    }
  }

}
