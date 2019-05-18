package com.jb.discountcalc.discounts;

import com.jb.discountcalc.domain.Transaction;
import com.jb.discountcalc.service.discounts.DefaultDiscountProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultDiscountProcessorTest {

  private List<Transaction> testTransactions;

  private DefaultDiscountProcessor defaultDiscountProcessor;

  @Before
  public void init() {
    testTransactions = new ArrayList<>();
    defaultDiscountProcessor = new DefaultDiscountProcessor();


  }

  @Test
  public void testEnoughBudgetForAllDiscountsOneMonth(){
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
    testTransactions.add(
        Transaction.create(
            2L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 2),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            10L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 10),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );

    List<Transaction> result = defaultDiscountProcessor.process(testTransactions);

    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(0).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(1).getDiscount());
    Assert.assertNull(result.get(2).getDiscount());
    Assert.assertNull(result.get(3).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(6.9), result.get(4).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(5).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(6).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(7).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(8).getDiscount());
  }

  @Test
  public void testNotEnoughBudgetForAllDiscountsOneMonth(){
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
    testTransactions.add(
        Transaction.create(
            2L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 2),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            10L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 10),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );

    List<Transaction> result = defaultDiscountProcessor.process(testTransactions);

    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(0).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(1).getDiscount());
    Assert.assertNull(result.get(2).getDiscount());
    Assert.assertNull(result.get(3).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(6.9), result.get(4).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(5).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(6).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(7).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(8).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.1), result.get(9).getDiscount());
  }

  @Test
  public void testEnoughBudgetForAllDiscountsTwoMonths() {
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
    testTransactions.add(
        Transaction.create(
            2L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 2),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
        )
    );
//    testTransactions.add(
//        Transaction.create(
//            10L,
//            "2015-02-02 S MR",
//            LocalDate.of(2015, 2, 10),
//            "S",
//            "MR",
//            false,
//            BigDecimal.valueOf(2)
//        )
//    );

    testTransactions.add(
        Transaction.create(
            11L,
            "2015-03-01 S MR",
            LocalDate.of(2015, 3, 1),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            12L,
            "2015-03-02 S MR",
            LocalDate.of(2015, 3, 2),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            13L,
            "2015-03-02 L LP",
            LocalDate.of(2015, 3, 3),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            14L,
            "2015-03-02 L LP",
            LocalDate.of(2015, 3, 4),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            15L,
            "2015-03-02 L LP",
            LocalDate.of(2015, 3, 5),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            16L,
            "2015-03-02 S MR",
            LocalDate.of(2015, 3, 6),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            17L,
            "2015-03-02 S MR",
            LocalDate.of(2015, 3, 7),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            18L,
            "2015-03-02 S MR",
            LocalDate.of(2015, 3, 8),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            19L,
            "2015-03-02 S MR",
            LocalDate.of(2015, 3, 9),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
//    testTransactions.add(
//        Transaction.create(
//            20L,
//            "2015-02-02 S MR",
//            LocalDate.of(2015, 3, 10),
//            "S",
//            "MR",
//            false,
//            BigDecimal.valueOf(2)
//        )
//    );

    List<Transaction> result = defaultDiscountProcessor.process(testTransactions);

    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(0).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(1).getDiscount());
    Assert.assertNull(result.get(2).getDiscount());
    Assert.assertNull(result.get(3).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(6.9), result.get(4).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(5).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(6).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(7).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(8).getDiscount());

    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(9).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(10).getDiscount());
    Assert.assertNull(result.get(11).getDiscount());
    Assert.assertNull(result.get(12).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(6.9), result.get(13).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(14).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(15).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(16).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(17).getDiscount());
  }

  @Test
  public void testNotEnoughBudgetForAllDiscountsTwoMonths(){
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
    testTransactions.add(
        Transaction.create(
            2L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 2),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
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
            BigDecimal.valueOf(2)
        )
    );

    testTransactions.add(
        Transaction.create(
            11L,
            "2015-03-01 S MR",
            LocalDate.of(2015, 3, 1),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            12L,
            "2015-03-02 S MR",
            LocalDate.of(2015, 3, 2),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            13L,
            "2015-03-02 L LP",
            LocalDate.of(2015, 3, 3),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            14L,
            "2015-03-02 L LP",
            LocalDate.of(2015, 3, 4),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            15L,
            "2015-03-02 L LP",
            LocalDate.of(2015, 3, 5),
            "L",
            "LP",
            false,
            BigDecimal.valueOf(6.9)
        )
    );
    testTransactions.add(
        Transaction.create(
            16L,
            "2015-03-02 S MR",
            LocalDate.of(2015, 3, 6),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            17L,
            "2015-03-02 S MR",
            LocalDate.of(2015, 3, 7),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            18L,
            "2015-03-02 S MR",
            LocalDate.of(2015, 3, 8),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            19L,
            "2015-03-02 S MR",
            LocalDate.of(2015, 3, 9),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );
    testTransactions.add(
        Transaction.create(
            20L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 3, 10),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );

    testTransactions.add(
        Transaction.create(
            10L,
            "2015-02-02 S MR",
            LocalDate.of(2015, 2, 10),
            "S",
            "MR",
            false,
            BigDecimal.valueOf(2)
        )
    );

    List<Transaction> result = defaultDiscountProcessor.process(testTransactions);

    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(0).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(1).getDiscount());
    Assert.assertNull(result.get(2).getDiscount());
    Assert.assertNull(result.get(3).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(6.9), result.get(4).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(5).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(6).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(7).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(8).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.1), result.get(9).getDiscount());

    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(10).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(11).getDiscount());
    Assert.assertNull(result.get(12).getDiscount());
    Assert.assertNull(result.get(13).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(6.9), result.get(14).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(15).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(16).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(17).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.5), result.get(18).getDiscount());
    Assert.assertEquals(BigDecimal.valueOf(0.1), result.get(19).getDiscount());

  }
}
