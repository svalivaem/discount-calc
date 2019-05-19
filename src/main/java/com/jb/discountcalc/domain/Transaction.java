package com.jb.discountcalc.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {

  private long id;

  private String line;

  private LocalDate date;

  private String size;

  private String carrier;

  private BigDecimal price;

  private BigDecimal discount;

  private boolean ignored = false;

  public Transaction(long id, String line) {
    this.id = id;
    this.line = line;
  }

  public static Transaction create(long id, String line, LocalDate date, String size, String carrier, boolean ignore) {
    Transaction transaction = new Transaction(id, line);
    transaction.setDate(date);
    transaction.setSize(size);
    transaction.setCarrier(carrier);
    if (ignore) transaction.ignore();
    return transaction;
  }

  public static Transaction create(long id, String line, LocalDate date, String size, String carrier, boolean ignore, BigDecimal price) {
    Transaction transaction = create(id, line, date, size, carrier, ignore);
    transaction.setPrice(price);
    return transaction;
  }

  public static Transaction create(long id, String line, LocalDate date, String size, String carrier, boolean ignore, BigDecimal price,
                                   BigDecimal discount) {
    Transaction transaction = create(id, line, date, size, carrier, ignore, price);
    transaction.applyDiscount(discount);
    return transaction;
  }

  public long getId() {
    return id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getCarrier() {
    return carrier;
  }

  public void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public boolean isIgnored() {
    return ignored;
  }

  public void ignore() {
    this.ignored = true;
  }

  public String getRepresentation(DateTimeFormatter dateFormat) {
    return ignored
        ? line + " IGNORED"
        : dateFormat.format(date) + " " + carrier + " " + size + " " + price.setScale(2, RoundingMode.UP) +
        " " + (discount == null ? "-" : discount.setScale(2, RoundingMode.UP));
  }

  public void applyDiscount(BigDecimal discount) {
    if (discount == null) return;
    this.discount = discount;
    this.price = this.price.subtract(discount);
  }

  public void reduceDiscount(BigDecimal discount) {
    if (discount == null) return;
    this.discount = this.discount.subtract(discount);
    this.price = this.price.add(discount);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transaction that = (Transaction) o;
    return id == that.id;
  }
}
