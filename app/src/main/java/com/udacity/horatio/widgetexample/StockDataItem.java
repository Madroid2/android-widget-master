package com.udacity.horatio.widgetexample;

public class StockDataItem {
    private String mStockName;
    private String mStockType;
    private String mStockChange;
    private int mStockPercentage;

    public String getStockName() {
        return mStockName;
    }

    public void setStockName(String mStockName) {
        this.mStockName = mStockName;
    }

    public String getStockType() {
        return mStockType;
    }

    public void setStockType(String mStockType) {
        this.mStockType = mStockType;
    }

    public String getStockChange() {
        return mStockChange;
    }

    public void setStockChange(String mStockChange) {
        this.mStockChange = mStockChange;
    }

    public int getStockPercentage() {
        return mStockPercentage;
    }

    public void setStockPercentage(int mStockPercentage) {
        this.mStockPercentage = mStockPercentage;
    }
}
