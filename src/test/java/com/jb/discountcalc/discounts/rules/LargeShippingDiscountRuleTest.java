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
  public void testDiscount() {
    largeShippingDiscountRule.processTransactions(testTransactions);
    Assert.assertEquals(BigDecimal.valueOf(6.9), testTransactions.get(2).getDiscount());
  }

  @Test
  public void testDiscountThirdTransaction() {
    largeShippingDiscountRule.processTransactions(testTransactions);
    Assert.assertNotNull(testTransactions.get(2).getDiscount());
  }

  @Test
  public void testDiscountNotThirdTransaction() {
    largeShippingDiscountRule.processTransactions(testTransactions);
    Assert.assertNull(testTransactions.get(1).getDiscount());
  }

  @Test
  public void testDiscountThirdTransactionSameDate() {
    testTransactions.forEach(t -> t.setDate(LocalDate.of(2015, 2, 1)));
    largeShippingDiscountRule.processTransactions(testTransactions);
    Assert.assertEquals(BigDecimal.valueOf(6.9), testTransactions.get(2).getDiscount());
  }

}
