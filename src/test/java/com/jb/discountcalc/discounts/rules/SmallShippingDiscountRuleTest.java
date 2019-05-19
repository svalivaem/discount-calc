package com.jb.discountcalc.discounts.rules;

import com.jb.discountcalc.domain.Transaction;
import com.jb.discountcalc.service.discounts.rules.SmallShippingDiscountRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SmallShippingDiscountRuleTest {

  private List<Transaction> testTransactions;

  private SmallShippingDiscountRule smallShippingDiscountRule;

  @Before
  public void init() {
    testTransactions = new ArrayList<>();
    smallShippingDiscountRule = new SmallShippingDiscountRule();

    testTransactions.add(
        Transaction.create(
            1L,
            "2015-02-01 S MR",
            LocalDate.of(2015, 2, 1),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
  }

  @Test
  public void testDiscount() {
    smallShippingDiscountRule.processTransactions(testTransactions);
    Assert.assertEquals(BigDecimal.valueOf(0.5), testTransactions.get(0).getDiscount());
  }

}
