package com.jb.discountcalc.discounts.rules;

import com.jb.discountcalc.domain.Transaction;
import com.jb.discountcalc.service.discounts.rules.LargeShippingDiscountRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LargeShippingDiscountRuleTest {

  private List<Transaction> testTransactions;

  private LargeShippingDiscountRule largeShippingDiscountRule;

  @Before
  public void init() {
    testTransactions = new ArrayList<>();
    largeShippingDiscountRule = new LargeShippingDiscountRule();

    testTransactions.add(
        Transaction.create(
            1L,
            "2015-02-01 L LP",
            LocalDate.of(2015, 2, 1),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            2L,
            "2015-02-02 L LP",
            LocalDate.of(2015, 2, 2),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            3L,
            "2015-02-03 L LP",
            LocalDate.of(2015, 2, 3),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
  }

  @Test
  public void testDiscountEnoughBudget() {
    BigDecimal discountBudget = BigDecimal.TEN;
    BigDecimal discount = largeShippingDiscountRule.processTransaction(testTransactions.get(2), testTransactions, discountBudget);
    Assert.assertEquals(BigDecimal.valueOf(6.9), discount);
  }

  @Test
  public void testDiscountPartiallyEnoughBudget() {
    BigDecimal discountBudget = BigDecimal.valueOf(5);
    BigDecimal discount = largeShippingDiscountRule.processTransaction(testTransactions.get(2), testTransactions, discountBudget);
    Assert.assertEquals(BigDecimal.valueOf(5), discount);
  }

  @Test
  public void testDiscountNotEnoughBudget() {
    BigDecimal discountBudget = BigDecimal.ZERO;
    BigDecimal discount = largeShippingDiscountRule.processTransaction(testTransactions.get(2), testTransactions, discountBudget);
    Assert.assertNull(discount);
  }

  @Test
  public void testDiscountThirdTransaction() {
    BigDecimal discountBudget = BigDecimal.TEN;
    BigDecimal discount = largeShippingDiscountRule.processTransaction(testTransactions.get(2), testTransactions, discountBudget);
    Assert.assertNotNull(discount);
  }

  @Test
  public void testDiscountNotThirdTransaction() {
    BigDecimal discountBudget = BigDecimal.TEN;
    BigDecimal discount = largeShippingDiscountRule.processTransaction(testTransactions.get(1), testTransactions, discountBudget);
    Assert.assertNull(discount);
  }

  @Test
  public void testDiscountThirdTransactionSameDate() {
    testTransactions.forEach(t->t.setDate(LocalDate.of(2015, 2, 1)));
    BigDecimal discountBudget = BigDecimal.TEN;
    BigDecimal discount = largeShippingDiscountRule.processTransaction(testTransactions.get(2), testTransactions, discountBudget);
    Assert.assertEquals(BigDecimal.valueOf(6.9), discount);
  }
}
