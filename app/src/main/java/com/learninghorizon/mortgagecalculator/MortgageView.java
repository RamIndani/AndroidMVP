package com.learninghorizon.mortgagecalculator;

/**
 * Created by ramnivasindani on 9/18/15.
 */
public interface MortgageView {

    void renderOutput(final String monthlyPaymentAmount, final String totalInterestPaid, final String totalPropertyTaxPaid, final String payOffDate);
    void reset();
    String getHomeValue();
    String getDownPayment();

    String getInterestRate();

    String getTerm();

    String getPropertyTaxRate();


   void setHomeValueMissingError(int resId);

    void setDownPaymentValueMissingError(int resId);

     void setInterestRateValueMissingError(int resId);

   void setPropertyTaxRateValueMissingError(int resId);
}
