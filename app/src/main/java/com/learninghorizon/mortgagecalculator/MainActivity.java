package com.learninghorizon.mortgagecalculator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  Callbacks{

    private static final String INPUT_FRAGMENT_TAG = "input";
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        if(null==savedInstanceState) {
            renderInputFragment(R.id.input_fragment, "input");
        }
    }

    /**public void removeFragment(String tag){
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (null != fragment) {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            onReset();
            return true;
        }

        return super.onOptionsItemSelected(item);
        //return false;
    }

    @Override
    public void onCalculate(){
        renderInputFragment(R.id.output_fragment, "output");
    }

    @Override
    public void onReset(){
        if(fragmentManager.getBackStackEntryCount()>0){
            popFragmentBack();
        }
        Fragment mortgageFragment = fragmentManager.findFragmentByTag(INPUT_FRAGMENT_TAG);
        ((MorgageInputFragment)mortgageFragment).reset();
    }

    @Override
    public void onBackPressed(){

       if(fragmentManager.getBackStackEntryCount()==0){
           super.onBackPressed();
       }else{
           popFragmentBack();
       }
    }



    @Override
    public void renderOutputFragment(int fragmentResId, String tag, String monthlyPaymentAmount,String totalInterestPaid,String totalPropertyTaxPaid, String payOffDate){
        View outputFragment = findViewById(R.id.output_fragment);
        Bundle bundle = new Bundle();
        bundle.putString("monthlyPaymentAmount",monthlyPaymentAmount);
        bundle.putString("totalInterestPaid",totalInterestPaid);
        bundle.putString("totalPropertyTaxPaid",totalPropertyTaxPaid);
        bundle.putString("payOffDate",payOffDate);
        if(outputFragment == null){
            Fragment morgageFragmentHolder = fragmentManager.findFragmentByTag(tag);
            if (null == morgageFragmentHolder) {
                morgageFragmentHolder = new MorgageOutputFragment();
                morgageFragmentHolder.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(fragmentResId, morgageFragmentHolder, "output")
                        .addToBackStack("input")
                        .commit();
            }
        }else{
            Fragment morgageFragmentHolder = fragmentManager.findFragmentByTag(tag);
            if (null == morgageFragmentHolder) {
                morgageFragmentHolder = new MorgageOutputFragment();
                morgageFragmentHolder.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .add(R.id.output_fragment, morgageFragmentHolder, "output")
                        .commit();
            }else{

                fragmentManager.beginTransaction()
                        .remove(morgageFragmentHolder)
                        .commit();

                morgageFragmentHolder = new MorgageOutputFragment();
                morgageFragmentHolder.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .add(R.id.output_fragment, morgageFragmentHolder, "output")
                        .commit();
            }
        }

    }

   /** @Override
    public void renderInputFragment(int fragmentResId, String tag){
        View outputFragment = findViewById(R.id.mortgage_output);
        if(outputFragment == null){
            Fragment morgageFragmentHolder = fragmentManager.findFragmentByTag(tag);
            if (null == morgageFragmentHolder) {
                morgageFragmentHolder = new MorgageOutputFragment();
                fragmentManager.beginTransaction()
                        .replace(fragmentResId, morgageFragmentHolder, "output")
                        .commit();
            }
        }

    }*/

    @Override
    public void renderInputFragment(int fragmentResId, String tag){
        Fragment morgageFragmentHolder = fragmentManager.findFragmentByTag(tag);
        if (null == morgageFragmentHolder) {
            morgageFragmentHolder = new MorgageInputFragment();
            fragmentManager.beginTransaction()
                    .add(fragmentResId, morgageFragmentHolder, tag)
                    .commit();
        }else{
            //reset fragment
            popFragmentBack();
            ((MorgageInputFragment)morgageFragmentHolder).reset();

        }
    }

    private void popFragmentBack(){
        fragmentManager.popBackStackImmediate();
    }
}
