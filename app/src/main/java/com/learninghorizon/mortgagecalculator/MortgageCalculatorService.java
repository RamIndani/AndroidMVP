package com.learninghorizon.mortgagecalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ramnivasindani on 9/18/15.
 */
public class MortgageCalculatorService {

    public static final String HUNDREAD = "100";
    public static final String NO_MONTHS_IN_YEARS = "12";
    public MortgageCalculatorService(){

    }


    /*public BigDecimal getTotalMonthlyPayment(String homeValue, String downPayment, String interestRate, String terms, String propertyTax){
        BigDecimal output = null;
        BigDecimal monthlyMortgagePayment = getMonthlyMortgagePayment(homeValue, downPayment, interestRate, terms, propertyTax);
        BigDecimal monthlyPropertyTax = getMonthlyPropertyTax(homeValue, downPayment, interestRate, terms, propertyTax);
        output = monthlyMortgagePayment.add(monthlyPropertyTax);
        output  = output.setScale(2, BigDecimal.ROUND_HALF_UP);
        return output;
    }*/


    /*public BigDecimal getMonthlyMortgagePayment(String homeValue, String downPayment, String interestRate, String terms, String propertyTax){
        BigDecimal output = null;
        BigDecimal bHomeValue = new BigDecimal(homeValue);
        BigDecimal bDownPayment = new BigDecimal(downPayment);
        BigDecimal principalAmount = bHomeValue.subtract(bDownPayment);
        int totalNumberOfMonths = Integer.valueOf(terms) * Integer.valueOf(NO_MONTHS_IN_YEARS);
        BigDecimal monthlyInterest = getMonthlyInterest(interestRate);


        BigDecimal onePlausIToN = (new BigDecimal("1").add(monthlyInterest)).pow(totalNumberOfMonths);
        BigDecimal numerator = principalAmount.multiply(monthlyInterest).multiply(onePlausIToN);
        BigDecimal denominator = onePlausIToN.subtract(new BigDecimal("1"));
        output = numerator.divide(denominator, 5, RoundingMode.HALF_UP);
        return output;
    }*/

   /* public BigDecimal getMonthlyInterest(String interestRate){
        BigDecimal output = null;
        BigDecimal annualInterest = new BigDecimal(interestRate).divide(new BigDecimal(HUNDREAD), 5, RoundingMode.HALF_UP);
        output = annualInterest.divide(new BigDecimal(NO_MONTHS_IN_YEARS),8, RoundingMode.HALF_UP);
        return output;
    }*/

    /*public BigDecimal getMonthlyPropertyTax(String homeValue, String downPayment, String interestRate, String terms, String propertyTax){
        BigDecimal output = null;
        BigDecimal totalNumberOfMonths = new BigDecimal(terms).multiply(new BigDecimal(NO_MONTHS_IN_YEARS));
        BigDecimal bTotalPropertyTax = getTotalPropertyTax(homeValue, propertyTax, terms);
        output = bTotalPropertyTax.divide(totalNumberOfMonths, 2, RoundingMode.HALF_UP);
        return output;
    }*/

    /*public double getTotalPropertyTax(final String homeValue, final String propertyTax, final String terms){
        double output = 0;
        double bHomeValue = Double.valueOf(homeValue);
        double bPropertyTax = Double.valueOf(propertyTax)/100;
        double bTerms = Double.valueOf(terms);
        output = bHomeValue* bPropertyTax*bTerms;
        return output;
    }*/

    /*public BigDecimal getTotalInterestPaid(String homeValue, String downPayment, String interestRate, String terms, String propertyTax){
        BigDecimal monthlyPayment = getTotalMonthlyPayment(homeValue, downPayment, interestRate, terms, propertyTax);
        BigDecimal numberOfMonths = new BigDecimal(terms).multiply(new BigDecimal(NO_MONTHS_IN_YEARS));
        BigDecimal totalAmountPaid = monthlyPayment.multiply(numberOfMonths);
        //BigDecimal loanAmount = new BigDecimal(homeValue).subtract(new BigDecimal(downPayment));
        BigDecimal totalInterestPaid = totalAmountPaid.subtract(new BigDecimal(homeValue));
        return totalInterestPaid;
    }*/

    public String getPayOffDate(String term){
        String payOffDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,Integer.parseInt(term));
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM, yyyy");
        payOffDate = simpleDateFormat.format(calendar.getTime());
        return payOffDate;
    }




    //////////////////double output///////////////////

    public double getTotalMonthlyPayment(String homeValue, String downPayment, String interestRate, String terms, String propertyTax){
        double output = 0;
        double monthlyMortgagePayment = getMonthlyMortgagePayment(homeValue, downPayment, interestRate, terms, propertyTax);
        double monthlyPropertyTax = getMonthlyPropertyTax(homeValue, downPayment, interestRate, terms, propertyTax);
        output = monthlyMortgagePayment+ monthlyPropertyTax;

        return output;
    }


    public double getMonthlyMortgagePayment(String homeValue, String downPayment, String interestRate, String terms, String propertyTax){
        double output = 0;
        double bHomeValue = Double.valueOf(homeValue);
        double bDownPayment = Double.valueOf(downPayment);
        double principalAmount = bHomeValue - (bDownPayment);
        int totalNumberOfMonths = Integer.valueOf(terms) * Integer.valueOf(NO_MONTHS_IN_YEARS);
        double monthlyInterest = getMonthlyInterest(interestRate);


        double onePlausIToN = Math.pow(1 + (monthlyInterest), totalNumberOfMonths);
        double numerator = principalAmount*(monthlyInterest)*(onePlausIToN);
        double denominator = onePlausIToN-1;
        output = numerator/denominator;
        return output;
    }

    public double getMonthlyInterest(String interestRate){
        double output = 0;
        double annualInterest = Double.valueOf(interestRate)/Double.valueOf(HUNDREAD);
        output = annualInterest/12;
        return output;
    }

    public double getMonthlyPropertyTax(String homeValue, String downPayment, String interestRate, String terms, String propertyTax){
        double output = 0;
        double totalNumberOfMonths = Double.valueOf(terms)*(Double.valueOf(NO_MONTHS_IN_YEARS));
        double bTotalPropertyTax = getTotalPropertyTax(homeValue, propertyTax, terms);
        output = bTotalPropertyTax/totalNumberOfMonths;
        return output;
    }

    public double getTotalPropertyTax(final String homeValue, final String propertyTax, final String terms){
        double output = 0;
        double bHomeValue = Double.valueOf(homeValue);
        double bPropertyTax = (Double.valueOf(propertyTax)/(Double.valueOf(HUNDREAD)));
        double bTerms =Double.valueOf(terms);
        output = bHomeValue*(bPropertyTax)*(bTerms);
        return output;
    }

    public double getTotalInterestPaid(String homeValue, String downPayment, String interestRate, String terms, String propertyTax){
        double monthlyPayment = getTotalMonthlyPayment(homeValue, downPayment, interestRate, terms, propertyTax);
        double numberOfMonths = Double.valueOf(terms)*(Double.valueOf(NO_MONTHS_IN_YEARS));
        double totalAmountPaid = monthlyPayment*(numberOfMonths);
        //BigDecimal loanAmount = new BigDecimal(homeValue).subtract(new BigDecimal(downPayment));
        double toalPropertyTax = getTotalPropertyTax(homeValue, propertyTax, terms);
        double totalInterestPaid = (totalAmountPaid-(Double.valueOf(homeValue)-Double.valueOf(downPayment))-toalPropertyTax);
        return totalInterestPaid;
    }

   /* public String getPayOffDate(String term){
        String payOffDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,Integer.parseInt(term));
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM, yyyy");
        payOffDate = simpleDateFormat.format(calendar.getTime());
        return payOffDate;
    }
*/

}
