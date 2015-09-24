package com.learninghorizon.mortgagecalculator;

import android.app.Fragment;

/**
 * Created by ramnivasindani on 9/18/15.
 */
public interface Callbacks {
    void onCalculate();
    void onReset();
    void renderOutputFragment(int resId, final String tag,final String monthlyPaymentAmount,final String totalInterestPaid,final String totalPropertyTaxPaid, final String payOffDate);
    void renderInputFragment(int resId, final String tag);
}
