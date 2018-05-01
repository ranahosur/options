package com.trade.model;

import java.util.List;

public class MarketDataView {

    private String selectedDate;
    private String selectedStock;

    List<OptionDetail> optionDetails;

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getSelectedStock() {
        return selectedStock;
    }

    public void setSelectedStock(String selectedStock) {
        this.selectedStock = selectedStock;
    }

    public List<OptionDetail> getOptionDetails() {
        return optionDetails;
    }

    public void setOptionDetails(List<OptionDetail> optionDetails) {
        this.optionDetails = optionDetails;
    }
}
