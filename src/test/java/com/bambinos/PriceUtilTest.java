package com.bambinos;

import com.bambinos.util.PriceUtil;
import org.junit.Test;

import java.math.BigDecimal;

public class PriceUtilTest {


    @Test
    public void multipleTet(){
//        priceTestBD();
//        priceTestBD();
//        priceTestBD();
//        priceTestBD();
        priceTestInt();
        priceTestInt();
    }

    public void priceTestBD() {
        changeBD("100.0");
        changeBD("200.0");
        changeBD("300.0");
        changeBD("400.0");
        changeBD("150.0");
        changeBD("106.0");
        changeBD("104.4");
        changeBD("100.02");
        changeBD("0.02");
        changeBD("0.03");
        changeBD("0.22");
        changeBD("0.42");
        changeBD("0.6");
    }

    public void priceTestInt() {
        changeInt(1);
        changeInt(10);
        changeInt(23);
        changeInt(0);
        changeInt(200);
        changeInt(19000);
        changeInt(1000);
        changeInt(265);
        changeInt(98);
        changeInt(1875);
        changeInt(999);
        changeInt(1000);
        changeInt(54);
    }



    private void changeBD(String inputStr){
        BigDecimal bd = new BigDecimal(inputStr);
        BigDecimal updated = PriceUtil.getUpdatedBigDecimal(bd);
        System.out.println(bd + " -> " +updated);
    }

    private void changeInt(int input){
//        BigDecimal bd = new BigDecimal(inputStr);
        int updated = PriceUtil.getUpdatedNumber(input);
        System.out.println(input + " -> " +updated);
    }
}
