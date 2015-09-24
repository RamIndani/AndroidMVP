package com.learninghorizon.mortgagecalculator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ramnivasindani on 9/18/15.
 */
public class MorgageOutputFragment extends Fragment implements MortgageOutputView{

    View view;
    MortgageViewOutputPresenter mortgageViewOutputPresenter;
    private FragmentManager fragmentManager;
    private Callbacks mCallback;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.mortgage_output_fragment, container, false);
        fragmentManager = getActivity().getFragmentManager();
        mortgageViewOutputPresenter = new MortgageViewOutputPresenter(this);

        Bundle bundle = getArguments();
        String monthlyPaymentAmount = bundle.getString("monthlyPaymentAmount");
        String totalInterestPaid = bundle.getString("totalInterestPaid");
        String totalPropertyTaxPaid = bundle.getString("totalPropertyTaxPaid");
        String payOffDate = bundle.getString("payOffDate");

        displayOutput(monthlyPaymentAmount, totalInterestPaid, totalPropertyTaxPaid, payOffDate);

        return view;
    }

    private void displayOutput(final String monthlyPaymentAmount,final String totalInterestPaid,final String totalPropertyTaxPaid,final String payOffDate ){
        TextView monthlyPayment = (TextView) view.findViewById(R.id.monthly_payment_amount);
        TextView totalInterest = (TextView) view.findViewById(R.id.total_interest_paid);
        TextView totalPropertyTax = (TextView) view.findViewById(R.id.total_property_tax_paid);
        TextView payOffDay = (TextView) view.findViewById(R.id.pay_off_day);

        if(null==monthlyPayment || null==totalInterest || null==totalPropertyTax || null==payOffDay){
            return;
        }

        monthlyPayment.setText(monthlyPaymentAmount);
        totalInterest.setText(totalInterestPaid);
        totalPropertyTax.setText(totalPropertyTaxPaid);
        payOffDay.setText(payOffDate);
    }
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
    }
*/

    @Override
    public void reset(){
        removeFragment("output");
        mCallback.renderInputFragment(R.id.input_fragment, "input");
       // popFragmentBack();
    }

    private void popFragmentBack(){
        fragmentManager.popBackStack("input", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void removeFragment(String tag){
        if(null!=fragmentManager.findFragmentByTag(tag)) {
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentByTag(tag))
                    .commit();
        }
    }
}
