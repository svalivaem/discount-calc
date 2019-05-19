package com.jb.discountcalc.discounts.rules;

import com.jb.discountcalc.domain.Transaction;
import com.jb.discountcalc.service.discounts.rules.MonthlyDiscountBudgetRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthlyDiscountBudgetRuleTest {

  private List<Transaction> testTransactions;

  private MonthlyDiscountBudgetRule monthlyDiscountBudgetRule;

  @Before
  public void init() {
    testTransactions = new ArrayList<>();
    monthlyDiscountBudgetRule = new MonthlyDiscountBudgetRule();

    testTransactions.add(
        Transaction.create(
            1L,
            "2015-02-01 S MR",
            LocalDate.of(2015, 2, 1),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2),
            BigDecimal.valueOf(0.5)
        )
    );
    testTransactions.add(
        Transaction.create(
            2L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 2),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2),
            BigDecimal.valueOf(0.5)
        )
    );
    testTransactions.add(
        Transaction.create(
            3L,
            "2015-02-02 L LP",
            LocalDate.of(2015, 2, 3),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            4L,
            "2015-02-02 L LP",
            LocalDate.of(2015, 2, 4),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            5L,
            "2015-02-02 L LP",
            LocalDate.of(2015, 2, 5),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9),
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            6L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 6),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2),
            BigDecimal.valueOf(0.5)
        )
    );
    testTransactions.add(
        Transaction.create(
            7L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 7),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2),
            BigDecimal.valueOf(0.5)
        )
    );
    testTransactions.add(
        Transaction.create(
            8L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 8),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2),
            BigDecimal.valueOf(0.5)
        )
    );
    testTransactions.add(
        Transaction.create(
            9L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 9),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2),
            BigDecimal.valueOf(0.5)
        )
    );
  }

  @Test
  public void testEnoughBudgetForAllDiscounts() {
    monthlyDiscountBudgetRule.processTransactions(testTransactions);

    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(0).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(1).getDiscount());
    Assert.assertNull(testTransactions.get(2).getDiscount());
    Assert.assertNull(testTransactions.get(3).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(6.9), testTransactions.get(4).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(5).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(6).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(7).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(8).getDiscount());
  }

  @Test
  public void testNotEnoughBudgetForAllDiscounts() {
    testTransactions.add(
        Transaction.create(
            10L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 10),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2),
            BigDecimal.valueOf(0.1)
        )
    );

    monthlyDiscountBudgetRule.processTransactions(testTransactions);

    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(0).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(1).getDiscount());
    Assert.assertNull(testTransactions.get(2).getDiscount());
    Assert.assertNull(testTransactions.get(3).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(6.9), testTransactions.get(4).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(5).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(6).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(7).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(8).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.1), testTransactions.get(9).getDiscount());
  }

}
