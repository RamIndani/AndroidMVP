package com.learninghorizon.mortgagecalculator;

/**
 * Created by ramnivasindani on 9/18/15.
 */


public class MortgageViewOutputPresenter{

    MortgageOutputView mortgageOutputView;
    public MortgageViewOutputPresenter(MortgageOutputView mortgageView){
        this.mortgageOutputView = mortgageView;
    }


    public void reset(){
        mortgageOutputView.reset();
    }


}
