package com.trade.util;

import com.trade.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryUtil {

    public static List<Country>  findAllCountries(){

        List<Country> countries = new ArrayList<Country>();
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);

            Country country = new Country();
            country.setCountryCode(obj.getCountry());
            country.setCountryName(obj.getDisplayCountry());
            countries.add(country);
        }
        return countries;
    }



}
