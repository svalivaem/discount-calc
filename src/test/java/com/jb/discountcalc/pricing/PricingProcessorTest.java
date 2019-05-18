package com.jb.discountcalc.pricing;

import com.jb.discountcalc.domain.Transaction;
import com.jb.discountcalc.service.pricing.DefaultPricingProcessor;
import com.jb.discountcalc.service.pricing.PricingProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PricingProcessorTest {

  private List<Transaction> testTransactions;

  private PricingProcessor pricingProcessor;

  @Before
  public void init() {
    pricingProcessor = new DefaultPricingProcessor();
    testTransactions = new ArrayList<>();

    testTransactions.add(
        Transaction.create(
            1L,
            "2015-02-01 S MR",
            LocalDate.of(2015, 2, 1),
            "S",
            "MR",
            false
        )
    );
    testTransactions.add(
        Transaction.create(
            2L,
            "2015-02-01 M MR",
            LocalDate.of(2015, 2, 1),
            "M",
            "MR",
            false
        )
    );
    testTransactions.add(
        Transaction.create(
            3L,
            "2015-02-01 L MR",
            LocalDate.of(2015, 2, 1),
            "L",
            "MR",
            false
        )
    );
    testTransactions.add(
        Transaction.create(
            4L,
            "2015-02-01 S LP",
            LocalDate.of(2015, 2, 1),
            "S",
            "LP",
            false
        )
    );
    testTransactions.add(
        Transaction.create(
            5L,
            "2015-02-01 M LP",
            LocalDate.of(2015, 2, 1),
            "M",
            "LP",
            false
        )
    );
    testTransactions.add(
        Transaction.create(
            6L,
            "2015-02-01 L LP",
            LocalDate.of(2015, 2, 1),
            "L",
            "LP",
            false
        )
    );
    testTransactions.add(
        Transaction.create(7L,
            "2015-02-01 XL LP",
            LocalDate.of(2015, 2, 1),
            "XL",
            "LP",
            false
        )
    );
    testTransactions.add(
        Transaction.create(
            8L,
            "2015-02-01 S DHL",
            LocalDate.of(2015, 2, 1),
            "S",
            "DHL",
            false
        )
    );
  }

  @Test
  public void testSettingPrices() {
    List<Transaction> withPrices = pricingProcessor.process(testTransactions);
    Assert.assertEquals(BigDecimal.valueOf(2), withPrices.get(0).getPrice());
    Assert.assertEquals(BigDecimal.valueOf(3), withPrices.get(1).getPrice());
    Assert.assertEquals(BigDecimal.valueOf(4), withPrices.get(2).getPrice());
    Assert.assertEquals(BigDecimal.valueOf(1.5), withPrices.get(3).getPrice());
    Assert.assertEquals(BigDecimal.valueOf(4.90), withPrices.get(4).getPrice());
    Assert.assertEquals(BigDecimal.valueOf(6.90), withPrices.get(5).getPrice());
    Assert.assertTrue(withPrices.get(6).isIgnored());
    Assert.assertTrue(withPrices.get(7).isIgnored());
  }
}
