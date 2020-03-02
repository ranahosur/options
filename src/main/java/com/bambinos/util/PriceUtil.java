package com.bambinos.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

public class PriceUtil {
    private static final Random random = new Random();
    private static final MathContext mathContext = new MathContext(2);
    private static final BigDecimal MINUS_ONE = new BigDecimal("-1.0");
    private static final BigDecimal ZERO = new BigDecimal("0.0");
    private static final String BIG_DECIMAL = "BigDecimal";
    private static final String INTEGER = "Integer";
    public static  BigDecimal getUpdatedBigDecimal(BigDecimal seedPrice) {
        return getUpdatedBigDecimal(seedPrice,BIG_DECIMAL);
    }

    private static  BigDecimal getUpdatedBigDecimal(BigDecimal seedPrice,String inputType) {

        if(seedPrice != null) {
            BigDecimal factor = null;
            if(seedPrice.compareTo(ZERO)> 0){
                if(INTEGER.equals(inputType)){
                    if(seedPrice.intValue() < 100){
                        factor = new BigDecimal("0.1");
                    }
                    else {
                        factor = new BigDecimal("0.025");
                    }
                }
                else {
                    factor = new BigDecimal("0.02");
                }
            }
            else{
                factor = new BigDecimal("0.2");
            }
            BigDecimal range = seedPrice.multiply(factor);
            int randInt = random.nextInt(10);
            if(randInt % 2 == 0){
                range = range.multiply(MINUS_ONE);
            }
            BigDecimal priceChange = new BigDecimal(String.valueOf(random.nextDouble() * range.doubleValue()), mathContext);
            BigDecimal updatedPrice = seedPrice.add(priceChange);

            return updatedPrice.setScale(2, RoundingMode.CEILING);

        }
        return new BigDecimal("0.0");
    }


    public static  int getUpdatedNumber(int seedPrice) {
        BigDecimal bd = new BigDecimal(seedPrice);
        BigDecimal updatedBD = getUpdatedBigDecimal(bd,INTEGER);
        return updatedBD.intValue();

    }

    public static  long getUpdatedLong(long seedPrice) {
        BigDecimal bd = new BigDecimal(seedPrice);
        BigDecimal updatedBD = getUpdatedBigDecimal(bd,INTEGER);
        return updatedBD.longValue();

    }
}
