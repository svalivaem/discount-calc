package com.jb.discountcalc.service.io;

import com.jb.discountcalc.domain.Transaction;

import java.util.List;


public interface IOService {

  List<Transaction> receiveTransactions(Object... params);

  void sendProcessedTransactions(List<Transaction> transactions, Object... params);

}
