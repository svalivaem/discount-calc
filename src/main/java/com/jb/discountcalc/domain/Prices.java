package com.jb.discountcalc.domain;

import java.math.BigDecimal;
import java.util.Map;

public class Prices {

  public static Map<String, Map<String, BigDecimal>> PRICES = Map.of(
      "LP", Map.of("S", BigDecimal.valueOf(1.5), "M", BigDecimal.valueOf(4.9), "L", BigDecimal.valueOf(6.9)),
      "MR", Map.of("S", BigDecimal.valueOf(2), "M", BigDecimal.valueOf(3), "L", BigDecimal.valueOf(4))
  );

}
