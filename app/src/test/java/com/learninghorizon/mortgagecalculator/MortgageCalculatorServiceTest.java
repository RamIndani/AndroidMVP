package com.learninghorizon.mortgagecalculator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by ramnivasindani on 9/20/15.
 */
public class MortgageCalculatorServiceTest {

    MortgageCalculatorService mortgageCalculatorServiceNoMock;
    @Before
    public void setUp() throws Exception{
        mortgageCalculatorServiceNoMock = new MortgageCalculatorService();
    }

    @Test
    public void totalMonthlyPayment() throws Exception{
        String output = String.valueOf(mortgageCalculatorServiceNoMock.getTotalMonthlyPayment("1000000", "300000", "3.2", "30", "1"));
        String exceptedResult = "3860.6013898114975";
        Assert.assertEquals(exceptedResult, output);
    }

    @Test
    public void totalTaxPaid() throws Exception{
        String output = String.valueOf(mortgageCalculatorServiceNoMock.getTotalPropertyTax("1000000", "1", "30"));
        String expectedResult = "300000.0";
        Assert.assertEquals(expectedResult, output);
    }

    @Test
    public void totalInterestPaid() throws Exception{
        String output = String.valueOf(mortgageCalculatorServiceNoMock.getTotalInterestPaid("1000000", "300000", "3.2", "30", "1"));
        String expectedResult = "389816.5003321392";
        Assert.assertEquals(expectedResult,output);
    }

    @Test
    public void totalPropertyTaxPaid() throws Exception {
        String output = String.valueOf(mortgageCalculatorServiceNoMock.getTotalPropertyTax("1000000", "1", "30"));
        String expectedResult = "300000.0";
        Assert.assertEquals(expectedResult,output);
    }

    @Test
    public void payOffDateTerm30() throws Exception {
        String payoffDate = mortgageCalculatorServiceNoMock.getPayOffDate("30");
        String expectedResult = "August, 2045";
        Assert.assertEquals(expectedResult, payoffDate);
    }

    @Test
    public void payOffDateTerm15() throws Exception {
        String payoffDate = mortgageCalculatorServiceNoMock.getPayOffDate("15");
        String expectedResult = "August, 2030";
        Assert.assertEquals(expectedResult, payoffDate);
    }
}
