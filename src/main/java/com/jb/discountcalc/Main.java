package com.jb.discountcalc;

import com.jb.discountcalc.domain.Transaction;
import com.jb.discountcalc.service.discounts.DefaultDiscountProcessor;
import com.jb.discountcalc.service.discounts.DiscountProcessor;
import com.jb.discountcalc.service.io.FileIOService;
import com.jb.discountcalc.service.io.IOService;
import com.jb.discountcalc.service.pricing.DefaultPricingProcessor;
import com.jb.discountcalc.service.pricing.PricingProcessor;

import java.util.List;


public class Main {

  public static void main(String[] args) {
    String filename = args[0];
    try {
      if (filename == null) {
        System.out.println("Invalid filename!");
      }

      //Initializing components
      IOService ioService = new FileIOService();
      PricingProcessor pricingProcessor = new DefaultPricingProcessor();
      DiscountProcessor discountsProcessor = new DefaultDiscountProcessor();

      //reading data from file
      List<Transaction> transactions = ioService.receiveTransactions(filename);
      if (transactions == null) {
        throw new RuntimeException("No data received!");
      }

      //Setting prices, applying discounts and printing results
      ioService.sendProcessedTransactions(
          discountsProcessor.process(
              pricingProcessor.process(transactions)
          )
      );

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
