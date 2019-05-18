package com.jb.discountcalc.service.io;

import com.jb.discountcalc.domain.Constants;
import com.jb.discountcalc.domain.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileIOService implements IOService {

  private DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public List<Transaction> receiveTransactions(Object... params) {
    List<String> lines = new ArrayList<>();
    try {
      if (params[0] == null || !(params[0] instanceof String)) {
        throw new RuntimeException("Invalid filename!");
      }

      Path path = Paths.get((String) params[0]);
      BufferedReader reader = Files.newBufferedReader(path);
      String currentLine = reader.readLine();

      while (currentLine != null) {
        lines.add(currentLine);
        currentLine = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      throw new RuntimeException(e.toString());
    }
    return parseLines(lines);
  }

  private List<Transaction> parseLines(List<String> lines) {
    List<Transaction> transactions = new ArrayList<>();
    //generating unique id's for each transaction
    long id = 1L;
    for (String line : lines) {
      Transaction transaction = new Transaction(id, line);
      id++;
      String[] args = line.split(" ");
      //validating and trying to set Transaction values
      if (args.length < 3
          || !parseAndSetDate(transaction, args[0])
          || !validateAndSetCarrier(transaction, args[2])
          || !validateAndSetPackageSize(transaction, args[2], args[1])) {
        transaction.ignore();
      }
      transactions.add(transaction);
    }
    return transactions;
  }

  @Override
  public void sendProcessedTransactions(List<Transaction> transactions, Object... params) {
    for (Transaction transaction : transactions) {
      System.out.println(transaction.getRepresentation(DATE_FORMAT));
    }
  }

  private boolean parseAndSetDate(Transaction transaction, String dateText) {
    try {
      LocalDate date = LocalDate.parse(dateText, DATE_FORMAT);
      transaction.setDate(date);
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  private boolean validateAndSetCarrier(Transaction transaction, String carrier) {
    if (Constants.PRICES.keySet().contains(carrier)) {
      transaction.setCarrier(carrier);
      return true;
    }
    return false;
  }

  private boolean validateAndSetPackageSize(Transaction transaction, String carries, String size) {
    if (Constants.PRICES.get(carries).keySet().contains(size)) {
      transaction.setSize(size);
      return true;
    }
    return false;
  }
}
