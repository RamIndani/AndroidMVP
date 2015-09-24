package com.learninghorizon.mortgagecalculator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
/**
 * Created by ramnivasindani on 9/20/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class ViewPresenterTest {

    @Mock
    MorgageInputFragment morgageInputFragment;

    @Mock
    MortgageCalculatorService mortgageCalculatorService;


    MortgageCalculatorService mortgageCalculatorServiceNoMock = new MortgageCalculatorService();
    ViewPresenter viewPresenter;
    @Before
    public void setUp() throws Exception{
        viewPresenter = new ViewPresenter(morgageInputFragment, mortgageCalculatorService);
    }

    @Test
    public void homeValueMissingError() throws Exception{
        when(morgageInputFragment.getHomeValue()).thenReturn("");
        viewPresenter.calculate();
        verify(morgageInputFragment).setHomeValueMissingError(R.string.home_value_required);
    }

    @Test
    public void downPaymentMissingError() throws Exception{
        when(morgageInputFragment.getHomeValue()).thenReturn("1000,000");
        when(morgageInputFragment.getDownPayment()).thenReturn("");
        viewPresenter.calculate();
        verify(morgageInputFragment).setDownPaymentValueMissingError(R.string.down_payment_required);
    }

    @Test
    public void interestRateMissingError() throws Exception {
        when(morgageInputFragment.getHomeValue()).thenReturn("1000,000");
        when(morgageInputFragment.getDownPayment()).thenReturn("300,000");
        when(morgageInputFragment.getInterestRate()).thenReturn("");
        viewPresenter.calculate();
        verify(morgageInputFragment).setInterestRateValueMissingError(R.string.interest_rate_required);
    }

    @Test
    public void propertyTaxRateMissingError() throws Exception {
        when(morgageInputFragment.getHomeValue()).thenReturn("1000,000");
        when(morgageInputFragment.getDownPayment()).thenReturn("300,000");
        when(morgageInputFragment.getInterestRate()).thenReturn("3.2");
        when(morgageInputFragment.getTerm()).thenReturn("30");
        when(morgageInputFragment.getPropertyTaxRate()).thenReturn("");
        viewPresenter.calculate();
        verify(morgageInputFragment).setPropertyTaxRateValueMissingError(R.string.property_tax_required);
    }


    @Test
    public void setTotalPayments() throws Exception {
        when(morgageInputFragment.getHomeValue()).thenReturn("1000,000");
        when(morgageInputFragment.getDownPayment()).thenReturn("300,000");
        when(morgageInputFragment.getInterestRate()).thenReturn("3.2");
        when(morgageInputFragment.getTerm()).thenReturn("30");
        when(morgageInputFragment.getPropertyTaxRate()).thenReturn("1");
        viewPresenter.calculate();

        //monthlyPaymentAmount,totalInterestPaid,totalPropertyTaxPaid,payOffDate
        verify(morgageInputFragment).renderOutput("3860.60", "389816.50", "300000.00", "August, 2045");
    }

}
