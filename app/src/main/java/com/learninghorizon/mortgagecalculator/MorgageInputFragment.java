package com.learninghorizon.mortgagecalculator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by ramnivasindani on 9/18/15.
 */
public class MorgageInputFragment extends Fragment implements MortgageView {

    private ViewPresenter viewPresenter;
    private FragmentManager fragmentManager;
    private View view;
    private Callbacks mCallback;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstaceState){
        super.onActivityCreated(savedInstaceState);
        this.fragmentManager = getActivity().getFragmentManager();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.mortgage_input_fragment,parent,false);
        viewPresenter = new ViewPresenter(this,new MortgageCalculatorService());
        Button calculateButton = (Button) view.findViewById(R.id.calculate);
        calculateButton.setOnClickListener(viewPresenter);
        renderTerms();
        return view;
    }

    public void renderTerms(){
        Spinner dropdown = (Spinner)view.findViewById(R.id.terms);
        String[] items = new String[]{
                getActivity().getResources().getString(R.string.option1),
                getActivity().getResources().getString(R.string.option2),
                getActivity().getResources().getString(R.string.option3),
                getActivity().getResources().getString(R.string.option4),
                getActivity().getResources().getString(R.string.option5),
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
    }

    @Override
    public void renderOutput(String monthlyPaymentAmount,String totalInterestPaid,String totalPropertyTaxPaid, String payOffDate){
        mCallback.renderOutputFragment(R.id.input_fragment, "output",monthlyPaymentAmount,totalInterestPaid,totalPropertyTaxPaid,payOffDate);
    }

    @Override
    public void reset(){
        removeFragment("output");
        resetInputFragment();
        //popFragmentBack();
    }

    private void popFragmentBack(){
        fragmentManager.popBackStack("input", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            reset();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (Callbacks) activity;
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mCallback = null;
    }

    private void removeFragment(String tag){
        if(null!=fragmentManager.findFragmentByTag(tag)) {
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentByTag(tag))
                    .commit();
        }
    }

    private void resetInputFragment(){
        EditText homeValue = (EditText) view.findViewById(R.id.home_value);
        if(null!=homeValue) {
            homeValue.setText(null);
        }

        EditText dowmPayment = (EditText) view.findViewById(R.id.down_payment);
        if(null!=dowmPayment) {
            dowmPayment.setText(null);
        }

        EditText interestRate = (EditText) view.findViewById(R.id.interest_rate);
        if(null!=interestRate) {
            interestRate.setText(null);
        }

        Spinner termSpinner = (Spinner) view.findViewById(R.id.terms);
        if(null!=termSpinner) {
            termSpinner.setSelection(0);
        }


        EditText proprtyTaxRate = (EditText) view.findViewById(R.id.property_tax_rate);
        if(null!=proprtyTaxRate) {
            proprtyTaxRate.setText(null);
        }
    }

    @Override
    public String getHomeValue(){
        EditText homeValue = (EditText) view.findViewById(R.id.home_value);
        return homeValue.getText().toString();
    }

    @Override
    public String getDownPayment(){
        EditText downPayment = (EditText) view.findViewById(R.id.down_payment);
        return downPayment.getText().toString();
    }

    @Override
    public String getInterestRate(){
        EditText interestRate = (EditText) view.findViewById(R.id.interest_rate);
        return interestRate.getText().toString();
    }

    @Override
    public String getTerm(){
        Spinner term = (Spinner) view.findViewById(R.id.terms);
        return term.getSelectedItem().toString();
    }

    @Override
    public String getPropertyTaxRate(){
        EditText propertyTaxRate = (EditText) view.findViewById(R.id.property_tax_rate);
        return propertyTaxRate.getText().toString();
    }


    @Override
    public void setHomeValueMissingError(int resId){
        EditText homeValue = (EditText) view.findViewById(R.id.home_value);
        homeValue.setError(getActivity().getResources().getString(resId));
    }

    @Override
    public void setDownPaymentValueMissingError(int resId){
        EditText downPayment = (EditText) view.findViewById(R.id.down_payment);
        downPayment.setError(getActivity().getResources().getString(resId));
    }

    @Override
    public void setInterestRateValueMissingError(int resId){
        EditText interestRate = (EditText) view.findViewById(R.id.interest_rate);
        interestRate.setError(getActivity().getResources().getString(resId));
    }

    @Override
    public void setPropertyTaxRateValueMissingError(int resId){
        EditText propertyTaxRate = (EditText) view.findViewById(R.id.property_tax_rate);
        propertyTaxRate.setError(getActivity().getResources().getString(resId));
    }
}
