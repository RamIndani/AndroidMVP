package com.learninghorizon.mortgagecalculator;

import android.view.View;
import android.widget.EditText;

/**
 * Created by ramnivasindani on 9/18/15.
 */
public class ViewPresenter implements View.OnClickListener{

    MorgageInputFragment mortgageView;
    MortgageCalculatorService mortgageCalculatorService;
    public ViewPresenter(MortgageView mortgageView,MortgageCalculatorService mortgageCalculatorService){
        this.mortgageView = (MorgageInputFragment)mortgageView;
        this.mortgageCalculatorService = mortgageCalculatorService;
    }

    public void calculate(){
        //TODO : calculation methods should be called from here
        //if successful then only renderOutputFragment
        //else fail test cases

        String sHomeValue = mortgageView.getHomeValue();
        if(null==sHomeValue || sHomeValue.isEmpty()){
            mortgageView.setHomeValueMissingError(R.string.home_value_required);
            return;
        }

        String sDownPayment = mortgageView.getDownPayment();
        if(null==sDownPayment || sDownPayment.isEmpty()){
            mortgageView.setDownPaymentValueMissingError(R.string.down_payment_required);
            return;
        }

        String sInterestRate = mortgageView.getInterestRate();
        if(null==sInterestRate || sInterestRate.isEmpty()){
            mortgageView.setInterestRateValueMissingError(R.string.interest_rate_required);
            return;
        }

        String sPropertyTax = mortgageView.getPropertyTaxRate();
        if(null == sPropertyTax || sPropertyTax.isEmpty()){
            mortgageView.setPropertyTaxRateValueMissingError(R.string.property_tax_required);
            return;
        }

        String sTerms = mortgageView.getTerm();
        sHomeValue = sHomeValue.replace(",","");
        sDownPayment = sDownPayment.replace(",","");
        sInterestRate = sInterestRate.replace(",","");
        sPropertyTax = sPropertyTax.replace(",","");

        MortgageCalculatorService mortgageCalculatorService = new MortgageCalculatorService();
        String monthlyPaymentAmount = String.format("%.2f",mortgageCalculatorService.getTotalMonthlyPayment(sHomeValue,sDownPayment, sInterestRate,sTerms,sPropertyTax));
        String totalInterestPaid = String.format("%.2f",mortgageCalculatorService.getTotalInterestPaid(sHomeValue, sDownPayment, sInterestRate, sTerms, sPropertyTax));
        String totalPropertyTaxPaid = String.format("%.2f",mortgageCalculatorService.getTotalPropertyTax(sHomeValue, sPropertyTax, sTerms));
        String payOffDate = mortgageCalculatorService.getPayOffDate(sTerms);

        mortgageView.renderOutput(monthlyPaymentAmount,totalInterestPaid,totalPropertyTaxPaid,payOffDate);

    }

    public void reset(){
        mortgageView.reset();
    }


    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.calculate:{
                calculate();
                break;
            }
        }
    }

}
